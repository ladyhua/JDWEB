package HttpClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import Node.Node;

public class SpiderJDForth {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String url = "http://book.jd.com/library/socialscience.html";
		String charset = "gbk";
		List<Node> a = SpiderForth(url,charset);
		return;
	}

	public static List<Node> SpiderForth(String url,String charset) throws Exception{
		HttpClient hc = new DefaultHttpClient();
		HttpGet hg = new HttpGet(url);
		
		HttpResponse response = hc.execute(hg);
		
		HttpEntity entity = response.getEntity();
		
		InputStream input = null;
		BufferedReader buff = null;
		
		List<Node> result = new ArrayList<Node>();
		
		String line = "";
		int num = 0;
		
		try{
			if(entity != null){
				input = entity.getContent();
				buff = new BufferedReader(new InputStreamReader(input, charset));
					
				while((line = buff.readLine()) != null){
					if(num > 200){
						if(line.contains("<h3>")){
							String h2 = new String(line.substring(line.indexOf("title=\"")+7, line.indexOf("\">")));
							Node y = new Node(h2);
							line = buff.readLine();
							while((line = buff.readLine()) != null){
								num++;
								if(line.contains("target=\"_blank\"")){
									String h1 = new String(line.substring(line.indexOf("title=\"")+7, line.indexOf("\" target=")).trim());
									Node x = new Node(h1);
									y.list.add(x);
								}
								if(line.contains("</p>")){
									break;
								}
							}
							result.add(y);
						}
						if(line.contains("<!-- /menu -->")){
							break;
						}
					}
					num++;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			buff.close();
			input.close();
		}
		return result;
	}
}

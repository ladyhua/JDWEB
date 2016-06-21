package HttpClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class JD_Labels {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String url = "http://item.jd.com/10155787643.html";
		String charset = "gbk";
		try {
			List<String> list = getLabels(url,charset);
			System.out.println(list.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static List<String> getLabels(String url,String charset) throws Exception{
		HttpClient hc = new DefaultHttpClient();
		HttpGet hg = new HttpGet(url);
		
		HttpResponse response = hc.execute(hg);
		
		HttpEntity entity = response.getEntity();
		
		InputStream input = null;
		BufferedReader buff = null;
		
		String line = "";
		
		String s = "<div class=\"breadcrumb\">";
		
		List<String> list = new LinkedList<String>();
		
		try{
			if(entity != null){
				input = entity.getContent();
				buff = new BufferedReader(new InputStreamReader(input, charset));
				
				while((line = buff.readLine()) != null){
					if(line.contains(s)){
						while((line = buff.readLine()) != null){
							String[] strs = line.split("</a>");
							for(String a : strs){
								if(a.contains("clstag")){
									String m = new String(a.substring(a.indexOf("\">")+2));
									list.add(m);
								}
							}
							if(line.contains("</div>")){
								break;
							}
						}
						break;
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			buff.close();
			input.close();
		}
		return list;
		
	}

}

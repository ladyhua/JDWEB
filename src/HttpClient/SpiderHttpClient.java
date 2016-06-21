package HttpClient;

/**爬取网页内容，并将其写进html文件，返回文件的路径
 * 
 */
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import Node.Node;

public class SpiderHttpClient {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String url = "http://www.jd.com/allSort.aspx";
		String charset = "utf-8";
		Spider(url,charset);
	}
	
	public static List<Node> Spider(String url,String charset) throws Exception{
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
					System.out.println(line);
//					if(num > 222)
//					{
//						if(line.contains("<i></i><span>")){
//							String h3 = new String(line.substring(line.indexOf("<span>")+6, line.indexOf("</span>")));
////							System.out.println(h3);
//							Node x = new Node(h3);
//							while((line = buff.readLine()) != null){
//								num++;
//								if(line.contains("<dl class=\"clearfix\">")){
//									while(!(line = buff.readLine()).contains("<a href=")){
//										num++;
//									}
//									String h2 = new String(line.substring(line.indexOf("_blank\">")+8, line.indexOf("</a>")));
//									Node y = new Node(h2);
//									x.list.add(y);
//									while((line = buff.readLine()) != null){
//										num++;
//										if(line.contains("<a href=")){
//											String h1 = new String(line.substring(line.indexOf("_blank\">")+8, line.indexOf("</a>")));
//											Node z = new Node(h1);
//											y.list.add(z);
//										}
//										if(line.contains("</dd>")){
//											break;
//										}
//									}
//								}
//								if(line.contains("<!--/ /widget/cat-item/cat-item.vm -->")){
//									break;
//								}
//							}
//							result.add(x);
//						}
//					}
//					num++;
				}
			}
		}catch(StringIndexOutOfBoundsException e){
			System.out.println(line);
			System.out.println(num);
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			buff.close();
			input.close();
		}
		return result;
	}
}


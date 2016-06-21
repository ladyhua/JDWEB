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

public class SuNing_Labels {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String url = "http://product.suning.com/detail_0070121639_140100585.html";
		String charset = "utf-8";
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
							if(line.contains("id=\"category1\"")){
								list.add(new String(line.substring(line.lastIndexOf("\" >")+3,line.indexOf("</a>"))));
								while((line = buff.readLine()) != null){
									if(line.contains("class=\"ft\"")){
										list.add(new String(line.substring(line.lastIndexOf("\">")+2,line.indexOf("</a>"))));
										while((line = buff.readLine()) != null){
											if(line.contains("class=\"ft\"")){
												list.add(new String(line.substring(line.lastIndexOf("\">")+2,line.indexOf("</a>"))));
												while((line = buff.readLine()) != null){
													if(line.contains("class=\"breadcrumb-title\"")){
														list.add(new String(line.substring(line.indexOf("title=\"")+7,line.indexOf("\" id=")).trim()));
														break;
													}
												}
												break;
											}
										}
										break;
									}
								}
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

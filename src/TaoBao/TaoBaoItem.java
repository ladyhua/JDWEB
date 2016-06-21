package TaoBao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;


public class TaoBaoItem {

	public static String GetTaoBaoName(String url,String charset) throws ClientProtocolException, IOException{
		String item_name = "";
		HttpClient hc = new DefaultHttpClient();
		HttpGet hg = new HttpGet(url);
		
		HttpResponse response = hc.execute(hg);
		
		HttpEntity entity = response.getEntity();
		
		InputStream input = null;
		BufferedReader buff = null;
		
		try{
			if(entity != null){
				input = entity.getContent();
				buff = new BufferedReader(new InputStreamReader(input, charset));
				
				String line;
				while((line = buff.readLine()) != null){
					if(line.contains("tb-main-title")){
						item_name = line.substring(line.indexOf("data-title=\"")+12, line.indexOf("\">"));
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
		return item_name;
	}
}

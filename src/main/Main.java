package main;

import java.util.Iterator;
import java.util.List;

import ExcelWriter.WriteToExcel;
import ExcelWriter.WriteToExcelForth;
import HttpClient.SpiderHttpClient;
import HttpClient.SpiderJDForth;
import Node.Node;
import Node.NodeWithUrl;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String url = "http://channel.jd.com/p_wenxuezongheguan.html";
		String charset = "gbk";
		try{
//			List<Node> result = SpiderHttpClient.Spider(url, charset);
//			WriteToExcel.write(result);
			runFor(url,charset);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		
	}
	
	public static void runFor(String url,String charset){
		try{
			List<Node> result = SpiderJDForth.SpiderForth(url, charset);
			WriteToExcelForth.writeForth(result);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}

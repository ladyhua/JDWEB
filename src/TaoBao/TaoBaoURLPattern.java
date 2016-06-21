package TaoBao;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.client.ClientProtocolException;

public class TaoBaoURLPattern {
	static Pattern pattern = null;
	static Matcher matcher = null;

	public static void main(String[] args) throws ClientProtocolException, IOException {
		// TODO Auto-generated method stub
		String url = "http://list.taobao.com/itemlist/acg.htm?cat=56282001&user_type=0&sd=1&viewIndex=1&as=0&spm=a21bi.1289946.1000187.1&hd=1&atype=b&style=grid&q=bjd&ppath=124640632%3A30617&same_info=1&isnew=2&tid=0&_input_charset=utf-8";
		System.out.println(SelectPattern(url,"gbk"));
	}
	
	public static String SelectPattern(String url,String charset) throws ClientProtocolException, IOException{
		String match = "^(http(s)?://)?(www.taobao.com){1}(/)?$";		//主页的正则表达式
		if(TaoBaoGetLabel(url,match)){
			return "首页";
		}
		match = "^(http(s)?://)?(buy){1}";				//详情页的购买
		if(TaoBaoGetLabel(url,match)){
			return "详情页 购买";
		}
		match = "^(http(s)?://)?(cart.taobao.com){1}(/)?(add_cart){1}";//详情页的购物车结算
		if(TaoBaoGetLabel(url,match)){
			return "详情页 购买车结算";
		}
		match = "^(http(s)?://)?(cart){1}(add_cart){0}";	//详情页的加购物车
		if(TaoBaoGetLabel(url,match)){
			return "详情页 加购买车";
		}
		match = "^(http(s)?://)?(s.taobao.com/list){1}";	//分类页的筛选分类
		if(TaoBaoGetLabel(url,match)){
			return "分类页 筛选分类";
		}
//		match = "(item){1}(itemlist){0}";								//商品详情
		if(url.contains("item.taobao.com") && !url.contains("itemlist")){
			
			return "详情页 商品详情 " + TaoBaoItem.GetTaoBaoName(url,charset);
		}
		match = "(list)?(market)?(index)?(taobao.com)?";
		if(TaoBaoGetLabel(url,match)){
			return "分类页 一般分类";
		}
		return "";
	}
	
	public static boolean TaoBaoGetLabel(String url,String match){
		pattern = Pattern.compile(match);
		matcher = pattern.matcher(url);
		return matcher.find();
	}
}

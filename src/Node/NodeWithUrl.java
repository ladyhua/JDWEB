package Node;

import java.util.ArrayList;
import java.util.List;

public class NodeWithUrl extends Node{
	
	private String url;
	
	public String getUrl(){
		return this.url;
	}
	
	public void setUrl(String url){
		this.url = url;
	}

	public List<NodeWithUrl> list = new ArrayList<NodeWithUrl>();
	
	public NodeWithUrl(String h3,String url) {
		super(h3);
		// TODO Auto-generated constructor stub
		this.url = url;
	}

}

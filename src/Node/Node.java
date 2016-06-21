package Node;

import java.util.ArrayList;
import java.util.List;

public class Node{
	private String h3;
	
	public List<Node> list = new ArrayList<Node>();
	
	public void set(String h3){
		this.h3 = h3;
	}
	
	public String get(){
		return this.h3;
	}
	
	public Node(String h3){
		this.h3 = h3;
	}
}

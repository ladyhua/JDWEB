package webcontent;

import java.io.IOException;
import java.net.MalformedURLException;

public class JD_Classify {

	public static void main(String[] args) throws MalformedURLException, IOException {
		// TODO Auto-generated method stub
//		Tika.GetWebContent("http://www.jd.com/allSort.aspx");
		B b = new B();
		C c = new C();
		System.out.println(c.run(3, 4));
	}
	
	
}

class A{
	public int run(int a,int b){
		return a+b;
	}
}

class B extends A{
	public int run(int a,int b,int c){
		return a+b+c;
	}
}

class C extends A{
	public int run(int a,int b){
		return a-b;
	}
}
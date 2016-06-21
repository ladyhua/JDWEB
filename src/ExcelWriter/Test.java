package ExcelWriter;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import Node.Node;
import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static void run(List<Node> node) throws Exception{
		WritableWorkbook wwb = Workbook.createWorkbook(new File("Test1.xls"));
		
		WritableSheet ws = wwb.createSheet("Test Sheet 1",0);
		
		int i = 0,j = 0;
		
		try{
			String word = "";
			if(node != null){
				for(Iterator<Node> it = node.iterator(); it.hasNext();){
					Node temp = it.next();
					
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
		}
	}
}

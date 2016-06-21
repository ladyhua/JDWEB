package ExcelWriter;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import Node.Node;
import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class WriteToExcel {
	
	public static void write(List<Node> result) throws IOException, RowsExceededException, WriteException{

		WritableWorkbook wwb = Workbook.createWorkbook(new File("Test.xls"));
		
		WritableSheet ws = wwb.createSheet("Test Sheet 1",0);
		
		int i = 0,j = 0;
		
		for(Iterator<Node> iter1 = result.iterator();iter1.hasNext();){
			String word = "";
			i = 0;
			Node x = iter1.next();
			word = x.get();
			jxl.write.Label s11=new jxl.write.Label(i, j, word);
			ws.addCell(s11);
			for(Iterator<Node> iter2 = x.list.iterator();iter2.hasNext();){
				i = 1;
				Node y = iter2.next();
				word = y.get();
				jxl.write.Label s21=new jxl.write.Label(i, j, word);
				ws.addCell(s21);
				for(Iterator<Node> iter3 = y.list.iterator();iter3.hasNext();){
					i = 2;
					Node z = iter3.next();
					System.out.println(z.list.size());
					word = z.get();
					jxl.write.Label s31=new jxl.write.Label(i, j, word);
					ws.addCell(s31);
					j++;
				}
			}
		}
		wwb.write();
		wwb.close();
	}
}

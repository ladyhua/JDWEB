package ExcelWriter;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import Node.Node;

public class WriteToExcelForth {

	public static void main(String[] args) {
		
	}
		// TODO Auto-generated method stub
		public static void writeForth(List<Node> result) throws IOException, RowsExceededException, WriteException{

			WritableWorkbook wwb = Workbook.createWorkbook(new File("文学综合馆.xls"));
			
			WritableSheet ws = wwb.createSheet("Test Sheet 1",0);
			
			int i = 0,j = 0;
			
			try{
				String word = "";
				for(Iterator<Node> iter2 = result.iterator();iter2.hasNext();){
					i = 0;
					Node y = iter2.next();
					word = y.get();
					jxl.write.Label s21=new jxl.write.Label(i, j, word);
					ws.addCell(s21);
					j++;
					for(Iterator<Node> iter3 = y.list.iterator();iter3.hasNext();){
						i = 1;
						Node z = iter3.next();
						word = z.get();
						jxl.write.Label s31=new jxl.write.Label(i, j, word);
						ws.addCell(s31);
						j++;
					}
					
				}
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				wwb.write();
				wwb.close();
			}
			
		}
	

}

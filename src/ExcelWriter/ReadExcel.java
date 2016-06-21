package ExcelWriter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import TaoBao.TaoBaoURLPattern;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class ReadExcel {

	public static void main(String[] args) throws RowsExceededException, WriteException {
		// TODO Auto-generated method stub
		try {
			ReadFromExcelFile();
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void ReadFromExcelFile() throws BiffException, IOException, RowsExceededException, WriteException{
		Workbook wb = Workbook.getWorkbook(new FileInputStream("taobaoUrlDemo.xls"));
		
		Sheet sheet = wb.getSheet(0);

		WritableWorkbook book = Workbook.createWorkbook(new File("taobaoUrlDemo.xls"), wb);
		
		WritableSheet sheet2 = book.getSheet(0);
		
		
		int row = sheet.getRows();
		int columns = sheet.getColumns();
		
		List<String> list = new ArrayList<String>();
		
		for(int i = 1; i < row; i++){
			String url = sheet.getCell(0, i).getContents();
			if(url.startsWith("http:https://")){
				url = url.substring(5);
			}
			list.add(url);
		}
		int i = 1;
		for(Iterator<String> it = list.iterator(); it.hasNext();){
			
			String label = TaoBaoURLPattern.SelectPattern(it.next().toString(),"gbk");
			StringTokenizer st = new StringTokenizer(label);
			int j = 1;
			while(st.hasMoreTokens()){
				sheet2.addCell(new Label(j,i,st.nextToken()));
				j++;
			}
			i++;
		}
		
		System.out.println(sheet.getCell(1, 0).getContents());
		try {
			book.write();
			book.close();
			wb.close();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}
}

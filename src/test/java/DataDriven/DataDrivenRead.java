package DataDriven;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataDrivenRead {

	public ArrayList<String> getData(String testCaseName, String sheetName) throws IOException
	{
		ArrayList<String> al = new ArrayList<String>();
		FileInputStream fis = new FileInputStream("C:\\Users\\rahul\\Desktop\\TestData.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		int sheetCount = workbook.getNumberOfSheets();
		for (int i = 0; i < sheetCount; i++) {
			if (workbook.getSheetName(i).equalsIgnoreCase(sheetName))
			{	
				XSSFSheet sheet = workbook.getSheetAt(i);
				Iterator<Row> rows = sheet.iterator();
				Row firstRow = rows.next();
				Iterator<Cell> ce = firstRow.cellIterator();
				int k = 0;
				int colum = 0;
				while (ce.hasNext()) {
					Cell value = ce.next();
					if (value.getStringCellValue().equalsIgnoreCase("TestCases")) {
						colum = k;
					}
					k++;
				}
				while (rows.hasNext()) {
					Row r = rows.next();
					if (r.getCell(colum).getStringCellValue().equalsIgnoreCase(testCaseName)) {
						Iterator<Cell> cv = r.cellIterator();
						while (cv.hasNext()) {
							Cell c = cv.next();
							if(c.getCellType()==CellType.STRING)
							{
								al.add(c.getStringCellValue());
							}
							else
							{
								al.add(NumberToTextConverter.toText(c.getNumericCellValue()));
							}
						}
					}
				}
			}

		}
		return al;
	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub		
	}

}

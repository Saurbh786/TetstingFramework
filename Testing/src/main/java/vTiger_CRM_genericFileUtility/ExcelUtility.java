package vTiger_CRM_genericFileUtility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {
	// Read the data from excel
	public String readDatafromExcel(String sheetName, int rowNum, int cellNum) throws EncryptedDocumentException, IOException {
		FileInputStream efis = new FileInputStream("./TestScriptData/TestScriptData.xlsx");
		Workbook wb = WorkbookFactory.create(efis);
		String data = wb.getSheet(sheetName).getRow(rowNum).getCell(cellNum).getStringCellValue();
		wb.close();
		return data;
		
	}
	// Get the rowCount
	public int getRowCount(String sheetName) throws EncryptedDocumentException, IOException {
		FileInputStream efis = new FileInputStream("./TestScriptData/TestScriptData.xlsx");
		Workbook wb = WorkbookFactory.create(efis);
		int rowCount = wb.getSheet(sheetName).getLastRowNum();
		return rowCount;
		
	}
	// To write the data back to excel
	public void writeDatabackintoExcel(String sheetName, int rowNum, int cellNum) throws EncryptedDocumentException, IOException {
		FileInputStream efis = new FileInputStream("./TestScriptData/TestScriptData.xlsx");
		Workbook wb = WorkbookFactory.create(efis);
		wb.getSheet(sheetName).getRow(rowNum).createCell(cellNum);
		FileOutputStream efos = new FileOutputStream("./TestScriptData/TestScriptData.xlsx");
		wb.write(efos);
		wb.close();
		
		
	}
	
	// Read all the data from the sheet in to 2D string array
	public String[][] readMultipleDataFromExcel(String sheetName) throws EncryptedDocumentException, IOException{
		FileInputStream efis = new FileInputStream("./TestScriptData/TestScriptData.xlsx");
		Workbook wb = WorkbookFactory.create(efis);
		Sheet sheet = wb.getSheet(sheetName);
		int rowCount = sheet.getLastRowNum();
		  short lastCell = sheet.getRow(0).getLastCellNum();
		  String[][] data = new String[rowCount][lastCell];
		  for(int i=1; i<=rowCount; i++) {
			  Row row = sheet.getRow(i);
			  for(int j=0; j<lastCell; j++) {
				  Cell cell = (row != null) ? row.getCell(j) : null;
		            data[i - 1][j] = (cell != null) ? cell.toString() : "";
			  }
			  
		  }
		wb.close();
		return data;
		
		
	}

}

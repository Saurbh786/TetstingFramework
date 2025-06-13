package vTiger_CRM_genericDataProviderUtility;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.DataProvider;

import vTiger_CRM_genericFileUtility.ExcelUtility;

public class DataProviderUtility {

	@DataProvider(name = "productTestData")
	public Object[][] getData() throws EncryptedDocumentException, IOException{
		ExcelUtility exu = new ExcelUtility();
		return exu.readMultipleDataFromExcel("Products");
		
	}
		

}

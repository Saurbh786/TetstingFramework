package vTiger_CRM_TestScriptWith_BaseClass;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import vTiger_CRM_BaseClassUtility.Base_Class;
import vTiger_CRM_ObjectRepository.CampaignInfoPage;
import vTiger_CRM_ObjectRepository.CreateNewCampaign;
import vTiger_CRM_ObjectRepository.HomePage;
import vTiger_CRM_genericFileUtility.ExcelUtility;

public class CreateCampaignUsingBaseClass extends Base_Class {
	@Test(groups = "Smoke Test")
	public void createCampaignTest() throws EncryptedDocumentException, IOException {
		// Generate Random NUmber
		int ranNum = jutil.getRandomNUmber();
		// Read data from Excel
		ExcelUtility eutil = new ExcelUtility();
		String campName = eutil.readDatafromExcel("Campaign", 1, 2)+ranNum;
		String prodName = eutil.readDatafromExcel("Products", 1, 2)+ranNum;
		//Actual Test Script
		HomePage hp = new HomePage(driver);
		hp.goToCampaign();
		CreateNewCampaign cnc = new CreateNewCampaign(driver);
		cnc.getCampaignLookupimgBtn().click();
		cnc.createCampaignwithProduct(campName, prodName, driver);
		System.out.println("Product Name: "+prodName);
		// Verifying Campaign is created with product
		CampaignInfoPage cip = new CampaignInfoPage(driver);
		String headerMsg = cip.getHeaderInfoMsg().getText();
		boolean status = headerMsg.contains(campName);
		Assert.assertEquals(status, true, "Failed to create Campaign");
		Reporter.log("Successfully created Campaign"+campName,true);
	}

	
	@Test(groups = "Regression Test")
	public void createCampaignWithCloseDate() throws EncryptedDocumentException, IOException {
		// Generate Random NUmber
		int ranNum = jutil.getRandomNUmber();
		// Get Expected close date
		String expectedCloseDate = jutil.getRequiredDateyyyyMMdd(20);
		// Read data from Excel
		ExcelUtility eutil = new ExcelUtility();
		String campName = eutil.readDatafromExcel("Campaign", 1, 2)+ranNum;
		//Actual Test Script
		HomePage hp = new HomePage(driver);
		hp.goToCampaign();
		CreateNewCampaign cnc = new CreateNewCampaign(driver);
		cnc.getCampaignLookupimgBtn().click();
		cnc.createCampaignwithCloseDate(campName, expectedCloseDate);
		System.out.println("ExpectedCloseDate is: "+expectedCloseDate);
		// Verify campaign is created with close date
		CampaignInfoPage cip = new CampaignInfoPage(driver);
		String headerMsg = cip.getHeaderInfoMsg().getText();
		boolean status = headerMsg.contains(campName);
		Assert.assertEquals(status, true, "Failed to create Campaign");
		Reporter.log("Successfully created Campaign"+campName,true);
	}

}

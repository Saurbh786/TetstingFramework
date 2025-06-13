package vTiger_CRM_TestScriptWith_BaseClass;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import vTiger_CRM_BaseClassUtility.Base_Class;
import vTiger_CRM_ObjectRepository.CreateNewOrgPage;
import vTiger_CRM_ObjectRepository.HomePage;
import vTiger_CRM_ObjectRepository.OrganizationInfoPage;
import vTiger_CRM_ObjectRepository.OrganizationPage;
import vTiger_CRM_genericFileUtility.ExcelUtility;
import vTiger_CRM_genericJavaUtility.JavaUtility;

public class CreateOrgUsingBaseClass extends Base_Class {
	@Test(groups = "Smoke Test")
	public void createOrgTest() throws EncryptedDocumentException, IOException {
		// Generate Random NUmber
		int ranNum = jutil.getRandomNUmber();
		// Read data from Excel
		String orgName = eutil.readDatafromExcel("Org", 1, 2)+ranNum;
		//Actual Test Script
		// Get org link
		HomePage hp = new HomePage(driver);
        hp.getOrgLink().click();
		OrganizationPage op = new OrganizationPage(driver);
		op.getCreateOrgBtn().click();
		CreateNewOrgPage cno = new CreateNewOrgPage(driver);
		cno.getOrganizationTF().sendKeys(orgName);
		cno.getSaveBtn().click();
		// Verify Org is created 
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String orgInfo = oip.getOrgInfo().getText();
		boolean status = orgInfo.contains(orgName);
		Assert.assertEquals(status, true, "Failed to create Org");
		Reporter.log("Successfully created Org"+orgName,true);
		
	}
	
	@Test(groups = "Regression Test")
	public void createOrgwithMob() throws EncryptedDocumentException, IOException {
		// Generate Random NUmber
		int ranNum = jutil.getRandomNUmber();
		// Read data from Excel
		ExcelUtility eutil = new ExcelUtility();
		String orgName = eutil.readDatafromExcel("Org", 1, 2)+ranNum;
		String mobNo = eutil.readDatafromExcel("Org", 1, 7);
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();
		OrganizationPage op = new OrganizationPage(driver);
		op.getCreateOrgBtn().click();
		CreateNewOrgPage cno = new CreateNewOrgPage(driver);
		cno.getOrganizationTF().sendKeys(orgName);
		cno.getPhoneTF().sendKeys(mobNo);
		System.out.println("Successfully created org with mobNo: "+mobNo);
		cno.getSaveBtn().click();
		// Verify Org is created 
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String orgInfo = oip.getOrgInfo().getText();
		boolean status = orgInfo.contains(orgName);
		Assert.assertEquals(status, true, "Failed to create Org");
		Reporter.log("Successfully created Org"+orgName,true);
	}
	
	@Test(groups = "Regression Test")
	public void createOrgWithIndustryAndType() throws EncryptedDocumentException, IOException {
		// Generate Random NUmber
		JavaUtility jutil = new JavaUtility();
		int ranNum = jutil.getRandomNUmber();
		// Read data from Excel
		ExcelUtility eutil = new ExcelUtility();
		String orgName = eutil.readDatafromExcel("Org", 1, 2)+ranNum;
		String ind = eutil.readDatafromExcel("Org", 1, 5);
		String indType = eutil.readDatafromExcel("Org", 1, 6);
		//Actual Test Script
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();
		OrganizationPage op = new OrganizationPage(driver);
		op.getCreateOrgBtn().click();
		CreateNewOrgPage cno = new CreateNewOrgPage(driver);
		cno.createOrgWithTypeAndIndustry(orgName, ind, indType);
		System.out.println("OrgName is: "+orgName+","+"industry is: "+ind+","+" industryType is: "+indType);
		// Verifying Org is created with industry and type
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String orgInfo = oip.getOrgInfo().getText();
		boolean status = orgInfo.contains(orgName);
		Assert.assertEquals(status, true, "Failed to create Org");
		Reporter.log("Successfully created Org"+orgName,true);
	}
}

package vTiger_CRM_TestScriptWith_BaseClass;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import vTiger_CRM_BaseClassUtility.Base_Class;
import vTiger_CRM_ObjectRepository.ContactInfoPage;
import vTiger_CRM_ObjectRepository.CreateNewContact;
import vTiger_CRM_ObjectRepository.HomePage;
import vTiger_CRM_genericFileUtility.ExcelUtility;

public class CreateContactUsingBaseClass extends Base_Class{
	@Test(groups = "Smoke Test")
	public void createContactTest() throws EncryptedDocumentException, IOException {
		// Generate Random NUmber
		int ranNum = jutil.getRandomNUmber();
		// Read data from Excel
		ExcelUtility eutil = new ExcelUtility();
		String lastName = eutil.readDatafromExcel("Contacts", 1, 2)+ranNum;
		HomePage hp = new HomePage(driver);
		hp.getContactsLink().click();
		CreateNewContact cnc = new CreateNewContact(driver);
		cnc.getCreateNewContactBtn().click();
		cnc.getLastNameTF().sendKeys(lastName);
		cnc.getSaveBtn().click();
		// Verify contact is created lastName
		ContactInfoPage cip = new ContactInfoPage(driver);
		String contactInfo = cip.getHeaderInfo().getText();
		boolean status = contactInfo.contains(lastName);
		Assert.assertEquals(status, true, "Failed to create contact");
		Reporter.log("Successfully created Contact"+lastName,true);
	}
	
	@Test(groups = "Regression Test")
	public void createContactWithOrg() throws EncryptedDocumentException, IOException {
		// Generate random number
		int ranNum = jutil.getRandomNUmber();
		// Read data from Excel
		ExcelUtility eutil = new ExcelUtility();
		String lastName = eutil.readDatafromExcel("Contacts", 1, 2)+ranNum;
		String orgName = eutil.readDatafromExcel("Contacts", 1, 3)+ranNum;
		//Actual test script
		HomePage hp = new HomePage(driver);
		hp.getContactsLink().click();
		CreateNewContact cnc = new CreateNewContact(driver);
		cnc.getCreateNewContactBtn().click();
		cnc.getLastNameTF().sendKeys(lastName);
		cnc.addContactwithOrg(orgName, driver);
		System.out.println("Org created with contact is: "+orgName);
		// Verify contact is created lastName
		ContactInfoPage cip = new ContactInfoPage(driver);
		String contactInfo = cip.getHeaderInfo().getText();
		boolean status = contactInfo.contains(lastName);
		Assert.assertEquals(status, true, "Failed to create Contact");
		Reporter.log("Successfully created Contact"+lastName,true);
	}
	
	@Test(groups = "Regression Test")
	public void createContactWithStartAndEndDate() throws EncryptedDocumentException, IOException {
		// Generate Random NUmber
		int ranNum = jutil.getRandomNUmber();
		//Generate start date
		String startDate = jutil.getSystemDateyyyyMMdd();
		// Generate endDate
		String endDate = jutil.getRequiredDateyyyyMMdd(15);
		// Read data from Excel
		ExcelUtility eutil = new ExcelUtility();
		String lastName = eutil.readDatafromExcel("Contacts", 1, 2)+ranNum;
		// Actual test script
		HomePage hp = new HomePage(driver);
		hp.getContactsLink().click();
		CreateNewContact cnc = new CreateNewContact(driver);
		cnc.getCreateNewContactBtn().click();
		cnc.getLastNameTF().sendKeys(lastName);
		cnc.getSystemDate(startDate, endDate);
		cnc.getSaveBtn().click();
		System.out.println("StartDate is: "+startDate+","+" EndDate is: "+endDate);
		// Verify contact is created
		ContactInfoPage cip = new ContactInfoPage(driver);
		String contactInfo = cip.getHeaderInfo().getText();
		boolean status = contactInfo.contains(lastName);
		Assert.assertEquals(status, true, "Failed to create Contact");
		Reporter.log("Successfully created Contact"+lastName,true);
	}

}

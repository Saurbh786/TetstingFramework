package vTiger_CRM_TestScriptWith_BaseClass;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import vTiger_CRM_BaseClassUtility.Base_Class;
import vTiger_CRM_ObjectRepository.CreateNewProductPage;
import vTiger_CRM_ObjectRepository.HomePage;
import vTiger_CRM_ObjectRepository.ProductInfoPage;
import vTiger_CRM_ObjectRepository.ProductPage;
import vTiger_CRM_genericFileUtility.ExcelUtility;

public class CreateProductUsingBaseClass extends Base_Class {
	@Test(groups = "Smoke Test" )
	public void createProductTest() throws EncryptedDocumentException, IOException {
		// Generate Random NUmber
		int ranNum = jutil.getRandomNUmber();
		// Read data from Excel
		ExcelUtility eutil = new ExcelUtility();
		String prodName = eutil.readDatafromExcel("Products", 1, 2)+ranNum;
		String prodCategory = eutil.readDatafromExcel("Products", 1, 3);
		//Actual Test Script
		//Click on ProductLink in HomePage
		HomePage hp = new HomePage(driver);
		hp.getProdLink().click();
		// Click on productLookup image on product page
		ProductPage pp = new ProductPage(driver);
		pp.getProductLink().click();
		// Create Product with Product Category
		CreateNewProductPage cnpp = new CreateNewProductPage(driver);
		cnpp.getCreateNewProductBtn().click();
		cnpp.createProductwithProductCategory(prodName, prodCategory);
		// Verifying Product is created with Product Category
		ProductInfoPage pip = new ProductInfoPage(driver);
		String headerInfo = pip.getHeaderInfo().getText();
		boolean status = headerInfo.contains(prodName);
		Assert.assertEquals(status, true, "Failed to create Product");
		Reporter.log("Successfully created Product"+prodName,true);
	}
	
	@Test(groups = "Regression Test")
	public void createProductWithSalesStartAndEndDate() throws EncryptedDocumentException, IOException {
		// Generate Random NUmber
		int ranNum = jutil.getRandomNUmber();
		// Read data from Excel
		ExcelUtility eutil = new ExcelUtility();
		String prodName = eutil.readDatafromExcel("Products", 1, 2)+ranNum;
		//Get sale StartDate
		String saleStartDate = jutil.getSystemDateyyyyMMdd();
		System.out.println(saleStartDate);
		//Get saleEndDate
		String saleEndDate = jutil.getRequiredDateyyyyMMdd(5);
		System.out.println(saleEndDate);
		//Actual Test Script
		// Click on ProductLink in HomePage
		HomePage hp = new HomePage(driver);
		hp.getProdLink().click();
		// Click on productLookup image on product page
		ProductPage pp = new ProductPage(driver);
		pp.getProductLink().click();
		// Create Product with sales start and End date
		CreateNewProductPage cnpp = new CreateNewProductPage(driver);
		cnpp.getCreateNewProductBtn().click();
		cnpp.getProductNameTF().sendKeys(prodName);
		cnpp.saleSupportdate(saleStartDate, saleEndDate);
		cnpp.getSaveBtn().click();
		// Verifying Product with sale start and end date
		ProductInfoPage pip = new ProductInfoPage(driver);
		String headerMsg = pip.getHeaderInfo().getText();
		boolean status = headerMsg.contains(prodName);
		Assert.assertEquals(status, true, "Failed to create Product");
		Reporter.log("Successfully created Product"+prodName,true);
	}
	
	@Test(groups = "Regression Test")
	public void createProductWithSupportStartAndEndDate() throws EncryptedDocumentException, IOException {
		// Generate Random NUmber
		int ranNum = jutil.getRandomNUmber();
		// Read data from Excel
		ExcelUtility eutil = new ExcelUtility();
		String prodName = eutil.readDatafromExcel("Products", 1, 2)+ranNum;
		// Get support start date
		String supportStartDate = jutil.getSystemDateyyyyMMdd();
		// Get ExpiryDate
		String supportExpiryDate = jutil.getRequiredDateyyyyMMdd(30);
		// Actual test script
		// Click on ProductLink in HomePage
		HomePage hp = new HomePage(driver);
		hp.getProdLink().click();
		// Click on productLookup image on product page
		ProductPage pp = new ProductPage(driver);
		pp.getProductLink().click();
		// Create Product with support start date and expiry date
		CreateNewProductPage cnpp = new CreateNewProductPage(driver);
		cnpp.getCreateNewProductBtn().click();
		cnpp.getProductNameTF().sendKeys(prodName);
		cnpp.supportStartandExpiryDate(supportStartDate, supportExpiryDate);
		cnpp.getSaveBtn().click();
		System.out.println("Support StartDate is: " + supportStartDate + "," + " Support ExpiryDate is: " + supportExpiryDate);
		// Verifying Product with sale start and end date
		ProductInfoPage pip = new ProductInfoPage(driver);
		String headerMsg = pip.getHeaderInfo().getText();
		boolean status = headerMsg.contains(prodName);
		Assert.assertEquals(status, true, "Failed to create Product");
		Reporter.log("Successfully created Product"+prodName,true);
	}

}

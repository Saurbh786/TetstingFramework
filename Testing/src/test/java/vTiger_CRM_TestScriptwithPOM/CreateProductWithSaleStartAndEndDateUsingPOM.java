package vTiger_CRM_TestScriptwithPOM;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import vTiger_CRM_ObjectRepository.CreateNewProductPage;
import vTiger_CRM_ObjectRepository.HomePage;
import vTiger_CRM_ObjectRepository.Login_Page;
import vTiger_CRM_ObjectRepository.ProductInfoPage;
import vTiger_CRM_ObjectRepository.ProductPage;
import vTiger_CRM_genericFileUtility.ExcelUtility;
import vTiger_CRM_genericFileUtility.FileUtility;
import vTiger_CRM_genericJavaUtility.JavaUtility;
import vTiger_CRM_genericWebdriverUtility.WebdriverUtility;

public class CreateProductWithSaleStartAndEndDateUsingPOM {

	public static void main(String[] args) throws IOException {
		FileUtility putil = new FileUtility();
		String BROWSER = putil.readDataFromProperties("Browser");
		String URL = putil.readDataFromProperties("Url");
		String UNAME = putil.readDataFromProperties("Username");
		String PWD = putil.readDataFromProperties("Password");
		// Cross Browser Testing
		WebDriver driver = null;
		if (BROWSER.equalsIgnoreCase("chrome")) {
				driver = new ChromeDriver();
		} else if (BROWSER.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		} else if (BROWSER.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else {
			driver = new ChromeDriver();
		}
		// Generate Random NUmber
		JavaUtility jutil = new JavaUtility();
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
		WebdriverUtility wutil = new WebdriverUtility();
		driver.manage().window().maximize();
		wutil.waitForPageToLoad(driver);
		driver.get(URL);
		//Login to vtiger
		Login_Page lp = new Login_Page(driver);
		lp.login(UNAME, PWD);
		//Click on ProductLink in HomePage
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
		if(headerMsg.contains(headerMsg)) {
			System.out.println("Successfully created Product with: "+prodName);
		}
		else {
			System.out.println("Failed to create Product with: "+prodName);
		}
		// Logout from Vtiger
		hp.logoutFromCRM(driver);
		driver.quit();
		

	}

}

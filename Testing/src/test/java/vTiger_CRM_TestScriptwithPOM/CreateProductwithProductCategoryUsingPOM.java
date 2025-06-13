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

public class CreateProductwithProductCategoryUsingPOM {

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
				String prodCategory = eutil.readDatafromExcel("Products", 1, 3);
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
				// Create Product with Product Category
				CreateNewProductPage cnpp = new CreateNewProductPage(driver);
				cnpp.getCreateNewProductBtn().click();
				cnpp.createProductwithProductCategory(prodName, prodCategory);
				// Verifying Product is created with Product Category
				ProductInfoPage pip = new ProductInfoPage(driver);
				String categoryName = pip.getCategoryInfo().getText();
				if(categoryName.contains(prodCategory)) {
					System.out.println("Successfully created product with Category: "+prodCategory);
				}
				else {
					System.out.println("Failed to create product with Category: "+prodCategory);
				}
				// Logout from vtiger
				hp.logoutFromCRM(driver);
				driver.quit();

	}

}

package vTiger_CRM_TestScriptwithPOM;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import vTiger_CRM_ObjectRepository.CreateNewOrgPage;
import vTiger_CRM_ObjectRepository.HomePage;
import vTiger_CRM_ObjectRepository.Login_Page;
import vTiger_CRM_ObjectRepository.OrganizationInfoPage;
import vTiger_CRM_ObjectRepository.OrganizationPage;
import vTiger_CRM_genericFileUtility.ExcelUtility;
import vTiger_CRM_genericFileUtility.FileUtility;
import vTiger_CRM_genericJavaUtility.JavaUtility;
import vTiger_CRM_genericWebdriverUtility.WebdriverUtility;

public class Create_OrgusingPOM {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
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
		String orgName = eutil.readDatafromExcel("Org", 1, 2)+ranNum;
		//Actual Test Script
				WebdriverUtility wutil = new WebdriverUtility();
				driver.manage().window().maximize();
				wutil.waitForPageToLoad(driver);
				driver.get(URL);
				//Login to vtiger
				Login_Page lp = new Login_Page(driver);
				lp.login(UNAME, PWD);
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
				if(orgInfo.contains(orgName)) {
					System.out.println("Successfully created org with: "+orgName);
				}
				else {
					System.out.println("Failed to create org with: "+orgName);
				}
				// Logout from vtiger
				hp.logoutFromCRM(driver);
				driver.quit();

	}

}

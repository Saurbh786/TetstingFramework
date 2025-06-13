package vTiger_CRM_TestScriptwithPOM;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import vTiger_CRM_ObjectRepository.ContactInfoPage;
import vTiger_CRM_ObjectRepository.CreateNewContact;
import vTiger_CRM_ObjectRepository.HomePage;
import vTiger_CRM_ObjectRepository.Login_Page;
import vTiger_CRM_genericFileUtility.ExcelUtility;
import vTiger_CRM_genericFileUtility.FileUtility;
import vTiger_CRM_genericJavaUtility.JavaUtility;
import vTiger_CRM_genericWebdriverUtility.WebdriverUtility;

public class CreateContactUsingPOM {

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
	String lastName = eutil.readDatafromExcel("Contacts", 1, 2)+ranNum;
	//Actual test script
	WebdriverUtility wutil = new WebdriverUtility();
	driver.manage().window().maximize();
	wutil.waitForPageToLoad(driver);
	driver.get(URL);
	//Login to vtiger
	Login_Page lp = new Login_Page(driver);
	lp.login(UNAME, PWD);
	HomePage hp = new HomePage(driver);
	hp.getContactsLink().click();
	CreateNewContact cnc = new CreateNewContact(driver);
	cnc.getCreateNewContactBtn().click();
	cnc.getLastNameTF().sendKeys(lastName);
	cnc.getSaveBtn().click();
	// Verify contact is created
	ContactInfoPage cip = new ContactInfoPage(driver);
	String contactInfo = cip.getHeaderInfo().getText();
	if(contactInfo.contains(lastName)) {
		System.out.println("Successfully created contact with: "+lastName);
	}
	else {
		System.out.println("Failed to create contact with: "+lastName);
	}
	// Logout from vtiger
	hp.logoutFromCRM(driver);
	driver.quit();

	}

}

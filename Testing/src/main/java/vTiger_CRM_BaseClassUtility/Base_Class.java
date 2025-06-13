package vTiger_CRM_BaseClassUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import vTiger_CRM_ObjectRepository.HomePage;
import vTiger_CRM_ObjectRepository.Login_Page;
import vTiger_CRM_genericFileUtility.ExcelUtility;
import vTiger_CRM_genericFileUtility.FileUtility;
import vTiger_CRM_genericJavaUtility.JavaUtility;
import vTiger_CRM_genericWebdriverUtility.UtilityClassObject;
import vTiger_CRM_genericWebdriverUtility.WebdriverUtility;
@Listeners(vTiger_CRM_ListneresImplementationUtility.ListenersImplementationClass.class)
public class Base_Class {

	public FileUtility flib = new FileUtility();
	public ExcelUtility eutil = new ExcelUtility();
	public WebdriverUtility wutil = new WebdriverUtility();
	public JavaUtility jutil = new JavaUtility();
	public WebDriver driver;
	//public static WebDriver sDriver;

	@BeforeSuite(groups = { "Smoke Test", "Regression Test" })
	public void dbConn() {
		System.out.println("-- connect to db --");
	}

	@Parameters("BROWSER")
	@BeforeClass(groups = { "Smoke Test", "Regression Test" })
	public void launchBrowser(@Optional("chrome") String browser) throws Throwable {
		//String BROWSER =flib.readDataFromProperties("Browser");
		String BROWSER = browser;
		browser = System.getProperty("BROWSER",flib.readDataFromProperties("Browser"));		
		//String URL = flib.readDataFromProperties("Url");
		String URL = System.getProperty("Url",flib.readDataFromProperties("Url"));
		if (BROWSER.equalsIgnoreCase("Chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equalsIgnoreCase("Firefox")) {
			driver = new FirefoxDriver();
		} else if (BROWSER.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		} else {
			System.out.println("-- invalid browser --");
		}
		//sDriver = driver;
		UtilityClassObject.setDriver(driver);
		driver.manage().window().maximize();
		wutil.waitForPageToLoad(driver);
		driver.get(URL);
		System.out.println("----Browser launched and navigated to Url----");
	}

	@BeforeMethod(groups = { "Smoke Test", "Regression Test" })
	public void loginToApp() throws Throwable {
		//String UN = flib.readDataFromProperties("Username");
		String UN = System.getProperty("Username", flib.readDataFromProperties("Username"));
		//String PWD = flib.readDataFromProperties("Password");
		String PWD = System.getProperty("Password",flib.readDataFromProperties("Password"));
		Login_Page lp = new Login_Page(driver);
		lp.login(UN, PWD);
		System.out.println("----Logged in to the application----");
	}

	@AfterMethod(groups = { "Smoke Test", "Regression Test" })
	public void logout() {
		HomePage hp = new HomePage(driver);
		hp.logoutFromCRM(driver);
		System.out.println("---Logged out of the Application-----");
	}

	@AfterClass(groups = { "Smoke Test", "Regression Test" })
	public void closeBrowser() {
		if (driver != null) {
			driver.quit();
			System.out.println("--Browser closed--");
		}
	}

	@AfterSuite(groups = { "Smoke Test", "Regression Test" })
	public void closeDB() {
		System.out.println("-- close DB --");
	}
}
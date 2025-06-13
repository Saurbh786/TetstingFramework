package vTiger_CRM_Product_Test;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import vTiger_CRM_genericFileUtility.ExcelUtility;
import vTiger_CRM_genericFileUtility.FileUtility;
import vTiger_CRM_genericJavaUtility.JavaUtility;
import vTiger_CRM_genericWebdriverUtility.WebdriverUtility;

public class Create_ProductWithSalesstartAndEndDate {

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
		// Actual test script
		driver.manage().window().maximize();
		WebdriverUtility wutil = new WebdriverUtility();
		wutil.waitForPageToLoad(driver);
		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(UNAME);
		driver.findElement(By.name("user_password")).sendKeys(PWD);
		driver.findElement(By.id("submitButton")).click();
		driver.findElement(By.linkText("Products")).click();
		driver.findElement(By.xpath("//img[@title='Create Product...']")).click();
		driver.findElement(By.xpath("//input[@name='productname']")).sendKeys(prodName);
		// Generate Support Start Date
		String salesStartDate = jutil.getSystemDateyyyyMMdd();
		System.out.println(salesStartDate);
		// Generate closing date
		String salesEndDate = jutil.getRequiredDateyyyyMMdd(5);
		System.out.println(salesEndDate);
		driver.findElement(By.xpath("//input[@id='jscal_field_sales_start_date']")).sendKeys(salesStartDate);
		driver.findElement(By.xpath("//input[@id='jscal_field_sales_end_date']")).sendKeys(salesEndDate);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		// verifying Product is created
		String prodInfo = driver.findElement(By.xpath("//span[@class='lvtHeaderText']")).getText();
		if(prodInfo.contains(prodName)) {
			System.out.println("Successfully created Product with: "+prodName);
		}
		else {
			System.out.println("Failed to create Product with: "+prodName);
		}
		// Logout from vTIger
		WebElement administrator = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		wutil.moveToElement(driver, administrator);
		driver.findElement(By.linkText("Sign Out")).click();
		driver.quit();
	}

}

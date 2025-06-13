package vTiger_CRM_Contact_test;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import vTiger_CRM_genericFileUtility.ExcelUtility;
import vTiger_CRM_genericFileUtility.FileUtility;

public class Create_Contact_using_DDTandGU {

	public static void main(String[] args) throws IOException, InterruptedException {
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
		Random ran = new Random();
		int ranNum = ran.nextInt(3000);

		// Read Data From Excel
		ExcelUtility eutil = new ExcelUtility();
		String orgName = eutil.readDatafromExcel("Contacts", 1, 3)+ran;
		String lastName = eutil.readDatafromExcel("Contacts", 1, 2)+ran;
				
				

		// Actual Test Script
		// Login to V-Tiger CRM
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(UNAME);
		driver.findElement(By.name("user_password")).sendKeys(PWD);
		driver.findElement(By.id("submitButton")).click();
		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		driver.findElement(By.name("accountname")).sendKeys(orgName);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		// Verifying that organization is created
		String headerInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (headerInfo.contains(orgName)) {
			System.out.println("Successfully created Organization");
		} else {
		System.out.println("Failed to create Organization");

		}

		// Navigate to contacts module
		driver.findElement(By.linkText("Contacts")).click();

		// Create new contacts
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		// Enter mandatory details
		driver.findElement(By.name("lastname")).sendKeys(lastName);
		// get create organization button
		driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();
		// to get parent window id
		String parentId = driver.getWindowHandle();
		// to get both parent and child window IDs
		Set<String> allwindowIds = driver.getWindowHandles();
		allwindowIds.remove(parentId);
		for (String windowId : allwindowIds) {
			driver.switchTo().window(windowId);
			Thread.sleep(3000);
		}
		driver.findElement(By.id("search_txt")).sendKeys(orgName);
		Thread.sleep(3000);
		driver.findElement(By.name("search")).click();
		// click the organization link
		driver.findElement(By.xpath("//a[contains(text(),'" + orgName + "')]")).click();
		// Switch back to Parent id
		driver.switchTo().window(parentId);
		// Click on save button
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		// Verifying contact last name
		String headerInfo2 = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (headerInfo2.contains(lastName)) {
			System.out.println("Succesfully created contact with organization");
		} else {
			System.out.println("Failed to create contact with organization");
		}
		// logout from CRM
		WebElement administrator = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions action = new Actions(driver);
		action.moveToElement(administrator).click().perform();
		driver.findElement(By.linkText("Sign Out")).click();
		Thread.sleep(3000);
		driver.quit();


	}

}

package vTiger_CRM_Contact_test;

import java.io.IOException;
import java.time.Duration;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import vTiger_CRM_genericFileUtility.ExcelUtility;
import vTiger_CRM_genericFileUtility.FileUtility;

public class Create_Contact__SelectOrgType {

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
		// Read data from Excel
		ExcelUtility eutil = new ExcelUtility();
		String lastName = eutil.readDatafromExcel("Contacts", 1, 2)+ranNum;
		String orgName = eutil.readDatafromExcel("Contacts", 1, 3)+ranNum;
		// Actual test script
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(UNAME);
		driver.findElement(By.name("user_password")).sendKeys(PWD);
		driver.findElement(By.id("submitButton")).click();
		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		driver.findElement(By.name("accountname")).sendKeys(orgName);
		driver.findElement(By.name("button")).click();
		// verifying Organization is created with orgName
		String headerInfo1 = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(headerInfo1.contains(orgName)) {
			System.out.println("Successfully created organization with: "+orgName);
		}
		else {
			System.out.println("Failed to create Organization with: "+orgName);
		}
		// Navigate to contact page
		driver.findElement(By.linkText("Contacts")).click();
		// Create new contact
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		// Enter mandatory details
		driver.findElement(By.name("lastname")).sendKeys(lastName);
		// get create organization button
		driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();
		// To get the parent window id
		String parentWindowId = driver.getWindowHandle();
		// To get both parent and child window Id's
		Set<String> allWindowIds = driver.getWindowHandles();
		allWindowIds.remove(parentWindowId);
		for(String windowId : allWindowIds ) {
			driver.switchTo().window(windowId);
			Thread.sleep(1000);
		}
		driver.findElement(By.id("search_txt")).sendKeys(orgName);
		driver.findElement(By.name("search")).click();
		driver.findElement(By.id("2")).click();
		//Switch back to parent id
		driver.switchTo().window(parentWindowId);
		// click on save button
		driver.findElement(By.name("button")).click();
		// verifying contact is created with org selected
		String headerInfo2 = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(headerInfo2.contains(lastName)) {
			System.out.println("Successfully created contact with: "+lastName);
		}
		else {
			System.out.println("Failed to create contact with: "+lastName);
		}
		// Logoutr from crm
		WebElement administrator = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions act = new Actions(driver);
		act.moveToElement(administrator).click().perform();
		driver.findElement(By.linkText("Sign Out")).click();
		driver.quit();
		

	}

}

package vTiger_CRM_Org_test;

import java.io.IOException;
import java.time.Duration;
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

public class Create_Org_With_Mobileno {

	public static void main(String[] args) throws IOException, InterruptedException {
		FileUtility putil = new FileUtility();
		String BROWSER = putil.readDataFromProperties("Browser");
		String URL = putil.readDataFromProperties("Url");
		String UNAME = putil.readDataFromProperties("Username");
		String PWD = putil.readDataFromProperties("Password");
		// Cross browser testing
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
		// Read Data from Excel
		ExcelUtility eutil = new ExcelUtility();
		String orgName = eutil.readDatafromExcel("Org", 1, 2)+ranNum;
		String mobNo = eutil.readDatafromExcel("Org", 1, 7);
		// Actual test Script
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys( UNAME);
		driver.findElement(By.name("user_password")).sendKeys(PWD);
		driver.findElement(By.id("submitButton")).click();
		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		driver.findElement(By.name("accountname")).sendKeys(orgName);
		driver.findElement(By.id("phone")).sendKeys(mobNo);
		System.out.println("Phone no is: "+mobNo);
		driver.findElement(By.name("button")).click();
		// Verifying Organization is created with orgname
		String headerInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(headerInfo.contains(orgName)) {
			System.out.println("Succesfully created organization with: "+orgName);
		}
		else {
			System.out.println("Failed to create organization with: "+orgName);
		}
		// Logout from vtiger
		WebElement administrator = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions act = new Actions(driver);
		act.moveToElement(administrator).click().perform();
		driver.findElement(By.linkText("Sign Out")).click();
		Thread.sleep(2000);
		driver.quit();
	 }

}

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

public class CreateOrganizationUsingDDTAndGU
{

	public static void main(String[] args) throws IOException, InterruptedException
	{
		FileUtility putil = new FileUtility();
		String BROWSER = putil.readDataFromProperties("Browser");
		String URL = putil.readDataFromProperties("Url");
		String UNAME = putil.readDataFromProperties("Username");
		String PWD = putil.readDataFromProperties("Password");
		
		// Cross Browser Testing
		WebDriver driver = null;
		if(BROWSER.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		}else if(BROWSER.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		}else if(BROWSER.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		}else {
			driver = new ChromeDriver();
		}
		
		// Generate Random number
		Random rand = new Random();
		int randomNumber = rand.nextInt();
		
		// Read Data From Excel
		ExcelUtility eutil = new ExcelUtility();
		String orgName = eutil.readDatafromExcel("Org", 1, 2)+rand;
		
		//Actual Test Script
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get(URL);
		// login
		driver.findElement(By.name("user_name")).sendKeys(UNAME);
		driver.findElement(By.name("user_password")).sendKeys(PWD);
		driver.findElement(By.id("submitButton")).click();
		// navigate to organization module
		driver.findElement(By.linkText("Organizations")).click();
		// click on create organization lookuo img
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		// enter the details
		driver.findElement(By.name("accountname")).sendKeys(orgName);
		// click on save button
		driver.findElement(By.name("button")).click();
		
		// Verifying that organization is created
		String headerInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (headerInfo.contains(orgName)) {
			System.out.println("Successfully created Organization: "+orgName);
		} else {
			System.out.println("Failed to create Organization: "+orgName);

		}
		
		// Logout From CRM
		WebElement administrator = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions act = new Actions(driver);
		act.moveToElement(administrator).click().perform();
		driver.findElement(By.linkText("Sign Out")).click();
		driver.quit();

	}

}

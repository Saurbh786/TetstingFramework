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
import org.openqa.selenium.support.ui.Select;

import vTiger_CRM_genericFileUtility.ExcelUtility;
import vTiger_CRM_genericFileUtility.FileUtility;

public class CreateOrganizationWithIndustry_TypeUsingDDTAndGU 
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
		
		// Read the data from Excel
		ExcelUtility eutil = new ExcelUtility();
		String orgName = eutil.readDatafromExcel("Org", 1, 2)+rand;
		String ind = eutil.readDatafromExcel("Org", 1, 5);
		String indType = eutil.readDatafromExcel("Org", 1, 6);
		
		//Actual test script
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(UNAME);
		driver.findElement(By.name("user_password")).sendKeys(PWD);
		driver.findElement(By.id("submitButton")).click();
		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		driver.findElement(By.name("accountname")).sendKeys(orgName);
		WebElement indDropdown = driver.findElement(By.name("industry"));
		indDropdown.sendKeys(ind);
		// handle the dropdown
		Select indSel = new Select(indDropdown);
		indSel.selectByValue("Electronics");
		WebElement accTypeDropdown = driver.findElement(By.name("accounttype"));
		accTypeDropdown.sendKeys(indType);
		// handle the dropdown
		Select typeSel = new Select(accTypeDropdown);
		typeSel.selectByValue("Customer");
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		// verifying that orgName with Industry And Type is created
		String headerInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(headerInfo.contains(orgName))
		{
			System.out.println("Successfully ctreated orgName with Industry and Type: "+ind+ " And " +indType);
		}
		else
		{
			System.out.println("Failed to create orgName with Industry and Type: "+ind+ " And " +indType);
		}
		
		// Logout from CRM
		WebElement administrator = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions act = new Actions(driver);
		act.moveToElement(administrator).click().perform();
		driver.findElement(By.linkText("Sign Out")).click();
		driver.quit();


	}

}

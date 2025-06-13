package vTiger_CRM_Contact_test;

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

public class CreateContactWith_StartdateandEnddate {

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
		
		// Generate Support Start Date
		Date date = new Date();
		System.out.println(date);
		SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
		String actDate = simple.format(date);
		System.out.println(actDate);
		// Generate closing date
		Calendar cal = simple.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH, 5);
		String reqDate = simple.format(cal.getTime());
		System.out.println(reqDate);
		
		// Read data from excel
		ExcelUtility eutil = new ExcelUtility();
		String lastName = eutil.readDatafromExcel("Contacts", 1, 2)+ranNum;
		// Actual test script
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(UNAME);
		driver.findElement(By.name("user_password")).sendKeys(PWD);
		driver.findElement(By.id("submitButton")).click();
		driver.findElement(By.linkText("Contacts")).click();
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		driver.findElement(By.name("lastname")).sendKeys(lastName);
		driver.findElement(By.name("support_start_date")).clear();
		driver.findElement(By.name("support_start_date")).sendKeys(actDate);
		driver.findElement(By.name("support_end_date")).clear();
		driver.findElement(By.name("support_end_date")).sendKeys(reqDate);
		driver.findElement(By.name("button")).click();
		//verifying Contact is created with last name
		String headerInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(headerInfo.contains(lastName)) {
			System.out.println("Contact is created with: "+lastName);
		}
		else {
			System.out.println("Failed to create contact with: "+lastName);
		}
		// Logout from vtiger
		WebElement administrator = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions act = new Actions(driver);
		act.moveToElement(administrator).click().perform();
		driver.findElement(By.linkText("Sign Out")).click();
		driver.quit();
		
		
		
		
		 
		
	}

	}



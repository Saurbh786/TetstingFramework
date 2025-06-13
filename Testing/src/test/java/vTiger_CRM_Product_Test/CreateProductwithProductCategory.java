package vTiger_CRM_Product_Test;

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

public class CreateProductwithProductCategory {

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
		Random ran = new Random();
		int ranNum = ran.nextInt(3000);
		// Read data from Excel
		ExcelUtility eutil = new ExcelUtility();
		String prodName = eutil.readDatafromExcel("Products", 1, 2)+ranNum;
		String prodCategory = eutil.readDatafromExcel("Products", 1, 3);
		// Actual test script
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(UNAME);
		driver.findElement(By.name("user_password")).sendKeys(PWD);
		driver.findElement(By.id("submitButton")).click();
		driver.findElement(By.linkText("Products")).click();
		driver.findElement(By.xpath("//img[@title='Create Product...']")).click();
		driver.findElement(By.xpath("//input[@name='productname']")).sendKeys(prodName);
		WebElement prodDropdown = driver.findElement(By.xpath("//select[@name='productcategory']"));
		// Handle the dropdown
		Select prodSel = new Select(prodDropdown);
		prodSel.selectByValue(prodCategory);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		// verifying Product is created with categoryType
		String prodCategoryInfo = driver.findElement(By.xpath("//td[@id='mouseArea_Product Category']")).getText();
		if(prodCategoryInfo.contains(prodCategory)) {
			System.out.println("succesfully created product with: "+prodCategory);
		}
		else {
			System.out.println("Failed to create product with: "+prodCategory);
		}
		// Logout from vtiger
		WebElement administrator = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions act = new Actions(driver);
		act.moveToElement(administrator).click().perform();
		driver.findElement(By.linkText("Sign Out")).click();
		driver.quit();

	}

}

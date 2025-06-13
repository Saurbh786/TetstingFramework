package Practice_CRM_Test;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
@Listeners(vTiger_CRM_ListneresImplementationUtility.ListenersImplementationClass.class)
public class screenshotTest {
	@Test
	public void flipkartTest() throws IOException {
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.flipkart.com/");
		// Step1: Create an Object for EvenFiringWebdriver
		EventFiringWebDriver edriver = new EventFiringWebDriver(driver);
		// Step2: use getScreenshotAs() to get the file type of the Screenshot
		File srcFile = edriver.getScreenshotAs(OutputType.FILE);
		// Step3: Store the screenshot on local driver
		FileUtils.copyFile(srcFile, new File("./Screenshots/flipkart.png"));
	}

}

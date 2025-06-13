package vTiger_CRM_ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OppurtunityPage {
	//WebDriver driver;
	public OppurtunityPage(WebDriver driver) {
		//this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//img[@title='Create Opportunity...']")
	private WebElement oppurtunityLookupBtn;

	public WebElement getOppurtunityLookupBtn() {
		return oppurtunityLookupBtn;
	}
	
	

}

package vTiger_CRM_ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactPage {
	//WebDriver driver;
	public ContactPage(WebDriver driver) {
		//this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(linkText = "Contacts")
	private WebElement contactLInk;
	
	@FindBy(name = "search_text")
	private WebElement searchTF;
	
	@FindBy(id = "bas_searchfield")
	private WebElement searchDropdown;
	
	@FindBy(name = "submit")
	private WebElement searchBtn;
	
	@FindBy(xpath = "//input[@value='Send Mail']")
	private WebElement sendMailBtn;

	public WebElement getContactLInk() {
		return contactLInk;
	}

	public WebElement getSearchTF() {
		return searchTF;
	}

	public WebElement getSearchDropdown() {
		return searchDropdown;
	}

	public WebElement getSearchBtn() {
		return searchBtn;
	}

	public WebElement getSendMailBtn() {
		return sendMailBtn;
	}

}

package vTiger_CRM_ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactInfoPage {
	//WebDriver driver;
	public ContactInfoPage(WebDriver driver) {
		//this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//span[@class='dvHeaderText']")
	private WebElement headerInfo;
	
	@FindBy(xpath = "//input[@title='Edit [Alt+E]']")
	private WebElement editBtn;
	
	@FindBy(xpath = "//input[@title='Delete [Alt+D]']")
	private WebElement deleteBtn;
	
	@FindBy(linkText = "Send Mail")
	private WebElement sendMailLink;
	
	@FindBy(xpath = "//input[@title='Locate Map']")
	private WebElement mapBtn;

	public WebElement getHeaderInfo() {
		return headerInfo;
	}

	public WebElement getEditBtn() {
		return editBtn;
	}

	public WebElement getDeleteBtn() {
		return deleteBtn;
	}

	public WebElement getSendMailLink() {
		return sendMailLink;
	}

	public WebElement getMapBtn() {
		return mapBtn;
	}
	

}

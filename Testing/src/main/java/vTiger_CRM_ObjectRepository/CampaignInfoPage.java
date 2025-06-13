package vTiger_CRM_ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CampaignInfoPage {
	//WebDriver driver;
	public CampaignInfoPage(WebDriver driver) {
		//this.driver =driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//span[@class='dvHeaderText']")
	private WebElement headerInfoMsg;
	
	@FindBy(xpath = "//input[@title='Edit [Alt+E]']")
	private WebElement editBtn;
	
	@FindBy(xpath = "//input[@title='Delete [Alt+D]']")
	private WebElement deleteBtn;
	
	@FindBy(xpath = "//input[@title='Duplicate [Alt+U]']")
	private WebElement duplicateBtn;
	
	@FindBy(linkText = "Add Event")
	private WebElement addEventLink;
	//public WebDriver getDriver() {
		//return driver;
	//}

	public WebElement getHeaderInfoMsg() {
		return headerInfoMsg;
	}

	public WebElement getEditBtn() {
		return editBtn;
	}

	public WebElement getDeleteBtn() {
		return deleteBtn;
	}

	public WebElement getDuplicateBtn() {
		return duplicateBtn;
	}

	public WebElement getAddEventLink() {
		return addEventLink;
	}
	
	

}

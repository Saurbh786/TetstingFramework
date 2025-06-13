package vTiger_CRM_ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationInfoPage {
	//WebDriver driver;
	public OrganizationInfoPage(WebDriver driver) {
		//this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath = "//span[@class='dvHeaderText']")
	private WebElement orgInfo;
	
	@FindBy(name = "Edit")
	private WebElement editBtn;
	
	@FindBy(name = "Duplicate")
	private WebElement duplicateBtn;
	
	@FindBy(name = "Delete")
	private WebElement deleteBtn;
	
	@FindBy(linkText = "Send Mail")
	private WebElement sendMailLink;
	
	@FindBy(name = "mapbutton")
	private WebElement locateBtn;

	public WebElement getOrgInfo() {
		return orgInfo;
	}

	public WebElement getEditBtn() {
		return editBtn;
	}

	public WebElement getDuplicateBtn() {
		return duplicateBtn;
	}

	public WebElement getDeleteBtn() {
		return deleteBtn;
	}

	public WebElement getSendMailLink() {
		return sendMailLink;
	}

	public WebElement getLocateBtn() {
		return locateBtn;
	}

}

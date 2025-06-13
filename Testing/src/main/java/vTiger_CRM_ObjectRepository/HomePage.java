package vTiger_CRM_ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import vTiger_CRM_genericWebdriverUtility.WebdriverUtility;

public class HomePage extends WebdriverUtility{
//	WebDriver driver;

	public HomePage(WebDriver driver) {
//		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(linkText = "Contacts")
	private WebElement contactsLink;

	@FindBy(linkText = "Organizations")
	private WebElement orgLink;
	
	@FindBy(linkText = "Products")
	private WebElement prodLink;
	
	@FindBy(linkText = "Opportunities")
	private WebElement oppurtunityLink;

	public WebElement getOppurtunityLink() {
		return oppurtunityLink;
	}

	
	public WebElement getProdLink() {
		return prodLink;
	}

	@FindBy(linkText = "More")
	private WebElement moreLink;

	@FindBy(linkText = "Campaigns")
	private WebElement campLink;

	@FindBy(xpath = "//img[@src='themes/softed/images/user.PNG']")
	private WebElement administrator;

	@FindBy(linkText = "Sign Out")
	private WebElement signOutLink;

	public WebElement getContactsLink() {
		return contactsLink;
	}

	public WebElement getOrgLink() {
		return orgLink;
	}

	public WebElement getMoreLink() {
		return moreLink;
	}

	public WebElement getCampLink() {
		return campLink;
	}

	public WebElement getAdministrator() {
		return administrator;
	}

	public WebElement getSignOutLink() {
		return signOutLink;
	}

	public void logoutFromCRM(WebDriver driver) {
		
		moveToElement(driver, administrator);
		signOutLink.click();
	}



	public void goToCampaign() {
		moreLink.click();
		campLink.click();
	}

}

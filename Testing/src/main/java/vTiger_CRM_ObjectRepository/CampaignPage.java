package vTiger_CRM_ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CampaignPage {
	//WebDriver driver;
	public CampaignPage(WebDriver driver) {
		//this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//img[@src='themes/softed/images/menuDnArrow.gif']")
	private WebElement moreLink;
	
	@FindBy(linkText = "Campaigns")
	private WebElement campaignLink;
	
	@FindBy(name = "search_text")
	private WebElement searchTF;
	
	@FindBy(id = "bas_searchfield")
	private WebElement searchDropdown;
	
	@FindBy(xpath = "//input[@name='submit']")
	private WebElement searchBtn;
	
	@FindBy(xpath = "//input[@class='crmbutton small delete']")
	private WebElement deleteBtn;
	
	public WebElement getDeleteBtn() {
		return deleteBtn;
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

	public WebElement getMoreLink() {
		return moreLink;
	}

	public WebElement getCampaignLink() {
		return campaignLink;
	}
	
	

}

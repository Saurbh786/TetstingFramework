package vTiger_CRM_ObjectRepository;

import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import vTiger_CRM_genericWebdriverUtility.WebdriverUtility;

public class CreateNewCampaign  {
	//WebDriver driver;
	public CreateNewCampaign(WebDriver driver) {
		//this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//img[@title='Create Campaign...']")
	private WebElement campaignLookupimgBtn;
	
	@FindBy(name = "campaignname")
	private WebElement campaignTF;
	
	@FindBy(xpath = "//select[@name='campaigntype']")
	private WebElement campaignTypeTF;
	
	@FindBy(xpath = "//input[@name='product_name']/following-sibling::img")
	private WebElement addProductInCampaignbtn;
	
	@FindBy(id = "search_txt")
	private WebElement searchTF;
	
	@FindBy(id = "1")
	private WebElement productName;
	
	public WebElement getProductName() {
		return productName;
	}

	@FindBy(xpath = "//input[@name='search']")
	private WebElement searchBtn;
	
	@FindBy(id = "jscal_field_closingdate")
	private WebElement closeDateBtn;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;

	public WebElement getCampaignLookupimgBtn() {
		return campaignLookupimgBtn;
	}

	public WebElement getCampaignTF() {
		return campaignTF;
	}

	public WebElement getCampaignTypeTF() {
		return campaignTypeTF;
	}

	public WebElement getAddProductInCampaignbtn() {
		return addProductInCampaignbtn;
	}

	public WebElement getSearchTF() {
		return searchTF;
	}

	public WebElement getSearchBtn() {
		return searchBtn;
	}

	public WebElement getCloseDateBtn() {
		return closeDateBtn;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}
	
	public void createCampaignwithCloseDate(String campaignName, String closeDate) {
		campaignTF.sendKeys(campaignName);
		closeDateBtn.clear();
		closeDateBtn.sendKeys(closeDate);
		 saveBtn.click();
	}
	
	public void createCampaignwithCampaignType(String campaignName, String campaignType) {
		campaignTF.sendKeys(campaignName);
		WebdriverUtility wutil = new WebdriverUtility();
		wutil.select(campaignTypeTF, campaignType);
		saveBtn.click();
	}
	
	public void createCampaignwithProduct(String campaignName, String prodName, WebDriver driver) {
		campaignTF.sendKeys(campaignName);
		addProductInCampaignbtn.click();
		String parentWindowId = driver.getWindowHandle();
		Set<String> allWindowIds = driver.getWindowHandles();
		allWindowIds.remove(parentWindowId);
		for(String windowId : allWindowIds ) {
			driver.switchTo().window(windowId);
		}
		searchTF.sendKeys(prodName);
		searchBtn.click();
		productName.click();
		driver.switchTo().window(parentWindowId);
		saveBtn.click();
	}
	
	

}

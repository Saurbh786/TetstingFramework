package vTiger_CRM_ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationPage {
	//WebDriver driver;

	public OrganizationPage(WebDriver driver) {
		//this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//img[@title='Create Organization...']")
	private WebElement createOrgBtn;
	@FindBy(name = "search_text")
	private WebElement searchBox;
	@FindBy(id = "bas_searchfield")
	private WebElement searchDropdown;
	@FindBy(name = "submit")
	private WebElement searchNowBtn;


	public WebElement getSearchBox() {
		return searchBox;
	}


	public WebElement getSearchDropdown() {
		return searchDropdown;
	}


	public WebElement getSearchNowBtn() {
		return searchNowBtn;
	}


	public WebElement getCreateOrgBtn() {
		return createOrgBtn;
	}

}

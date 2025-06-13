package vTiger_CRM_ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateNewOppurtunityPage {
	//WebDriver driver;
	public CreateNewOppurtunityPage(WebDriver driver) {
		//this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name = "potentialname")
	private WebElement potentialName;
	
	@FindBy(id = "related_to_type")
	private WebElement relatedToDD;
	
	@FindBy(xpath = "//input[@id='related_to_display']/following-sibling::img")
	private WebElement relatedToLookupBtn;
	
	@FindBy(name = "opportunity_type")
	private WebElement oppurtunityTypeDD;
	
	@FindBy(name = "leadsource")
	private WebElement leadSourceDD;
	
	@FindBy(xpath = "//input[@name='campaignname']/following-sibling::img")
	private WebElement camapignSourceBtn;
	
	@FindBy(id = "jscal_field_closingdate")
	private WebElement closeDateTF;
	
	@FindBy(name = "sales_stage")
	private WebElement saleStageTF;

	public WebElement getPotentialName() {
		return potentialName;
	}

	public WebElement getRelatedToDD() {
		return relatedToDD;
	}

	public WebElement getRelatedToLookupBtn() {
		return relatedToLookupBtn;
	}

	public WebElement getOppurtunityTypeDD() {
		return oppurtunityTypeDD;
	}

	public WebElement getLeadSourceDD() {
		return leadSourceDD;
	}

	public WebElement getCamapignSourceBtn() {
		return camapignSourceBtn;
	}

	public WebElement getCloseDateTF() {
		return closeDateTF;
	}

	public WebElement getSaleStageTF() {
		return saleStageTF;
	}
	
	

}

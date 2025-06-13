package vTiger_CRM_ObjectRepository;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateNewContact {
	//WebDriver driver;
	public  CreateNewContact(WebDriver driver) {
		//this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//img[@title='Create Contact...']")
	private WebElement createNewContactBtn;
	
	@FindBy(name = "firstname")
	private WebElement firstNameTF;
	
	@FindBy(name = "lastname")
	private WebElement lastNameTF;
	
	@FindBy(xpath = "//input[@name='account_name']/following-sibling::img")
	private WebElement orgInContactBtn;
	
	@FindBy(id = "search_txt")
	private WebElement searchTF;
	
	@FindBy(name = "search_txt")
	private WebElement searchDropdown;
	
	@FindBy(xpath = "//input[@type='button']")
	private WebElement searchBtn;
	
	@FindBy(id = "mobile")
	private WebElement mobTF;
	
	@FindBy(id = "jscal_field_support_start_date")
	private WebElement supportStratDateTF;
	
	@FindBy(id = "jscal_field_support_end_date")
	private WebElement supportEndDate;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;

	private String[] allWindowIDs;
	
	public void getSystemDate(String start, String end) {
		supportStratDateTF.clear();
		supportStratDateTF.sendKeys(start);
		supportEndDate.clear();
		supportEndDate.sendKeys(end);
	}
	
	public void addContactwithOrg(String orgName, WebDriver driver) {
		String parentID = driver.getWindowHandle();
		 orgInContactBtn.click();
		 // Switch to child window to add Org
		 Set<String> allWindIDs = driver.getWindowHandles();
		 allWindIDs.remove(parentID);
		for(String cuurentWin : allWindIDs) {
			driver.switchTo().window(cuurentWin);
			searchTF.sendKeys(orgName);
			searchBtn.click();
			driver.findElement(By.id("1")).click();
		}
		driver.switchTo().window(parentID);
		 saveBtn.click();
		
	}


	public WebElement getCreateNewContactBtn() {
		return createNewContactBtn;
	}

	public WebElement getFirstNameTF() {
		return firstNameTF;
	}

	public WebElement getLastNameTF() {
		return lastNameTF;
	}

	public WebElement getOrgInContactBtn() {
		return orgInContactBtn;
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

	public WebElement getMobTF() {
		return mobTF;
	}

	public WebElement getSupportStratDateTF() {
		return supportStratDateTF;
	}

	public WebElement getSupportEndDate() {
		return supportEndDate;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}

	public String[] getAllWindowIDs() {
		return allWindowIDs;
	}
	
	

}

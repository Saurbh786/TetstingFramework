package vTiger_CRM_ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Login_Page {

	@FindBy(name = "user_name")
	private WebElement userNameEdit;
	
	public WebElement getUserNameEdit() {
		return userNameEdit;
	}

	@FindBy(name = "user_password")
	private WebElement passwordEdt;

	@FindBy(id = "submitButton")
	private WebElement loginBtn;
	
	//initialization
	public Login_Page(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	public WebElement getUsernameEdt() {
		return userNameEdit;
	}

	public WebElement getPasswordEdt() {
		return passwordEdt;
	}

	public WebElement getLoginBtn() {
		return loginBtn;
	}
	
	//business libraries
	public void login(String UN, String PWD)
	{
		userNameEdit.sendKeys(UN);
		passwordEdt.sendKeys(PWD);
		loginBtn.click();
	}
}

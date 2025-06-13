package vTiger_CRM_ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage {
	//WebDriver driver;
	public ProductPage(WebDriver driver) {
		//this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(linkText = "Products")
	private WebElement productLink;
	
	@FindBy(xpath = "//input[@name='search_text']")
	private WebElement searchTF;
	
	@FindBy(xpath = "(//select[@id='bas_searchfield'])[1]")
	private WebElement productDropdown;
	
	@FindBy(xpath = "//input[@name='submit']")
	private WebElement searchBtn;

	public WebElement getProductLink() {
		return productLink;
	}

	public WebElement getSearchTF() {
		return searchTF;
	}

	public WebElement getProductDropdown() {
		return productDropdown;
	}

	public WebElement getSearchBtn() {
		return searchBtn;
	}
	

}

package vTiger_CRM_ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import vTiger_CRM_genericWebdriverUtility.WebdriverUtility;

public class CreateNewProductPage {
	//WebDriver driver;
	public CreateNewProductPage(WebDriver driver) {
		//this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//img[@title='Create Product...']")
	private WebElement createNewProductBtn;
	
	@FindBy(name = "productname")
	private WebElement productNameTF;
	
	@FindBy(xpath = "//input[@id='jscal_field_sales_start_date']")
	private WebElement saleStartDateTF;
	
	@FindBy(xpath = "//input[@id='jscal_field_sales_end_date']")
	private WebElement saleEndDateTF;
	
	@FindBy(xpath = "//select[@name='productcategory']")
	private WebElement productCategoryTF;
	
	@FindBy(xpath = "//input[@name='vendor_name']/following-sibling::img")
	private WebElement vendorNameInProductTF;
	
	@FindBy(xpath = "//select[@name='manufacturer']")
	private WebElement manufacturerTF;
	
	@FindBy(xpath = "//input[@id='jscal_field_start_date']")
	private WebElement supportStartDateTF;
	
	@FindBy(xpath = "//input[@id='jscal_field_expiry_date']")
	private WebElement supportExpiryDateTF;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;

	public WebElement getCreateNewProductBtn() {
		return createNewProductBtn;
	}

	public WebElement getProductNameTF() {
		return productNameTF;
	}

	public WebElement getSaleStartDateTF() {
		return saleStartDateTF;
	}

	public WebElement getSaleEndDateTF() {
		return saleEndDateTF;
	}

	public WebElement getProductCategoryTF() {
		return productCategoryTF;
	}

	public WebElement getVendorNameInProductTF() {
		return vendorNameInProductTF;
	}

	public WebElement getManufacturerTF() {
		return manufacturerTF;
	}

	public WebElement getSupportStartDateTF() {
		return supportStartDateTF;
	}

	public WebElement getSupportExpiryDateTF() {
		return supportExpiryDateTF;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}
	public void saleSupportdate(String saleStartDate, String saleEndDate) {
		saleStartDateTF.sendKeys(saleStartDate);
		saleEndDateTF.sendKeys(saleEndDate);
	}
	
	public void createProductwithProductCategory(String productName, String productCategory) {
		productNameTF.sendKeys(productName);
		WebdriverUtility wutil = new WebdriverUtility();
		wutil.select(productCategoryTF, productCategory);
		saveBtn.click();
	}
	
	public void supportStartandExpiryDate(String supportStartDate, String supportExpiryDate) {
		supportStartDateTF.sendKeys(supportStartDate);
		supportExpiryDateTF.sendKeys(supportExpiryDate);
		
	}

}

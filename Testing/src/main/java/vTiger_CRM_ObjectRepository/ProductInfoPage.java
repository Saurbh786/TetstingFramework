package vTiger_CRM_ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductInfoPage {
	//WebDriver driver;
	public ProductInfoPage(WebDriver driver) {
		//this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//span[@class='lvtHeaderText']")
	private WebElement headerInfo;
	
	@FindBy(id = "mouseArea_Product Category")
	private WebElement categoryInfo;
	
	@FindBy(xpath = "//input[@title='Edit [Alt+E]']")
	private WebElement editBtn;
	
	@FindBy(xpath = "//input[@title='Duplicate [Alt+U]']")
	private WebElement duplicateBtn;
	
	@FindBy(xpath = "//input[@title='Delete [Alt+D]']")
	private WebElement deleteBtn;
	
	@FindBy(linkText = "Create Quote")
	private WebElement createQuoteLink;
	
	@FindBy(linkText = "Create Invoice")
	private WebElement createInvoiceLink;
	
	@FindBy(linkText = "Create Sales Order")
	private WebElement createSaleOrderLink;
	
	@FindBy(linkText = "Create Purchase Order")
	private WebElement createPurchaseOrderLink;

	public WebElement getHeaderInfo() {
		return headerInfo;
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

	public WebElement getCreateQuoteLink() {
		return createQuoteLink;
	}

	public WebElement getCreateInvoiceLink() {
		return createInvoiceLink;
	}

	public WebElement getCreateSaleOrderLink() {
		return createSaleOrderLink;
	}

	public WebElement getCreatePurchaseOrderLink() {
		return createPurchaseOrderLink;
	}

	public WebElement getCategoryInfo() {
		return categoryInfo;
	}
	

}

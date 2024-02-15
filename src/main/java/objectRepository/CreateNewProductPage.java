package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericUtilities.WebDriverUtility;

public class CreateNewProductPage  extends WebDriverUtility
{
	//Declaration
	@FindAll({@FindBy(name="productname"),@FindBy(name="productname")})		//duplicate locator added just to understand @FindAll
	private WebElement productNameEdt;
	
	@FindAll({@FindBy(xpath="//input[@title='Save [Alt+S]']"),@FindBy(xpath="//input[@class='crmbutton small save']")})
	private WebElement saveBtn;
	
	@FindBy(name="productcategory")
	private WebElement productCategoryDD;
	
	@FindBy(name="manufacturer")
	private WebElement ManufacturerDD;
	
	//Initialization
	public CreateNewProductPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	//Utilization
	public WebElement getProductNameEdt() 
	{
		return productNameEdt;
		
	}
	public WebElement getSaveBtn() 
	{
		return saveBtn;
		
	}
	public WebElement GetProductCategoryDD() 
	{
		return productCategoryDD;
		
	}
	
	public WebElement getManufacturerDD()
	{
		return ManufacturerDD;
	}
	
	
	//Buisiness Library
	/**
	 * This method creates new product by entering product name
	 * @param PRODUCTNAME
	 */
	public void createNewProduct(String PRODUCTNAME)
	{
		productNameEdt.sendKeys(PRODUCTNAME);
		saveBtn.click();
	}
	/**
	 * This method creates new product by selecting product category from category dropdown
	 * @param PRODUCTNAME
	 * @param PRODUCTCATEGORY
	 */
	public void createNewProduct(String PRODUCTNAME, String CATEGORY)
	{
		productNameEdt.sendKeys(PRODUCTNAME);
		selectFromDropdown(productCategoryDD, CATEGORY);
		saveBtn.click();
	}
	/**
	 * This method creates new product by selecting product category and Manufacturer name from respective dropdowns 
	 * @param PRODUCTNAME
	 * @param CATEGORY
	 * @param MANUFACTURER
	 */
	public void createNewProduct(String PRODUCTNAME, String CATEGORY,String MANUFACTURER)
	{
		productNameEdt.sendKeys(PRODUCTNAME);
		selectFromDropdown(productCategoryDD, CATEGORY);
		selectFromDropdown(ManufacturerDD, MANUFACTURER);
		saveBtn.click();
	}
	
}

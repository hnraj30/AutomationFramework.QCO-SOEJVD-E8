package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductsPage 
{
	//Declaration
	@FindBy(xpath="//img[@alt='Create Product...']")
	private WebElement createProdImg;
	
	//Initialization
	public ProductsPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	//Utilization
	public  WebElement getCreateProdBtn()
	{
		return createProdImg;
	}
	
	/**
	 * This method clicks on Products button
	 */
	//Business library
	public  void clickOnCreateProdImg()
	{
		createProdImg.click();
	}
	

}

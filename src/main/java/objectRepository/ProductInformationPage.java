package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductInformationPage 
{
	//Declaration
	@FindBy(xpath="//span[@id='dtlview_Product Name']")
	private WebElement productHeader;
	
	//Initialization
	public ProductInformationPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	//Utilization
	public WebElement getProductHeader()
	{
		return productHeader;
	}
	
	//Buisiness library
	public String getProductHeaderText()
	{
		return productHeader.getText();
	}

}

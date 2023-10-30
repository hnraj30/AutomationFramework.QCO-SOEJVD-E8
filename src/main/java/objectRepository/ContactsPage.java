package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactsPage 
{
	//Declaration
	@FindBy(xpath= "//img[@alt='Create Contact...']") 
	private WebElement createContactBtn;

	//Initialization
	public ContactsPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	

	//Utilization
	public WebElement getCreateContBtn() {
		return createContactBtn;
	}
	
	//Buisiness library
	/**
	 * This method clicks on create contact link
	 */
	public void clickOnCreateContactImg()
	{
		createContactBtn.click();
		
	}
	
}

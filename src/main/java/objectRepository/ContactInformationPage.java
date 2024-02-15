package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactInformationPage 
{
	//Declaration
	@FindBy(id="mouseArea_Last Name") 
	private WebElement contactHeaderText;
	
	//initialization
	public ContactInformationPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	//Utilization
	public WebElement getContactHeaderText1() {
		return contactHeaderText;
	}
	
	//Business library
	/**
	 * This method capture header text from contact header and returns it to the caller
	 * @return
	 */
	public String getContactHeaderText()
	{
		return contactHeaderText.getText();
	}
	
}

package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationInformationPage 
{
	//Declaration
	@FindBy(id="mouseArea_Organization Name") 
	private WebElement orgHeader;
	
	//initialization
	public OrganizationInformationPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	//Utilization
	public WebElement getOrgHeader() {
		return orgHeader;
	}
	
	//Business library
	/**
	 * This method will get header text and return it to caller	
	 * @return
	 */
	public String getHeaderText() {
		return orgHeader.getText();
		
	}
	
}


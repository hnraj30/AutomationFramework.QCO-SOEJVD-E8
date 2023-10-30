package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationsPage 
{
		//Declaration
		@FindBy(xpath="//img[@alt='Create Organization...']") 
		private WebElement createOrgBtn ;
		
		//Initialization
		public OrganizationsPage(WebDriver driver)
		{
			PageFactory.initElements(driver, this);
		}
		
		//Utilization
		public WebElement getCreateOrgBtn() {
			return createOrgBtn;
		}
		
		//Buisiness library
		/**
		 * This method clicks on create organization look up image
		 */
		public void clickOnOrganizationLookUpImg()
		{
			createOrgBtn.click();
		}

}

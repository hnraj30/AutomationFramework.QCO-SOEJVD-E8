package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericUtilities.WebDriverUtility;

public class HomePage extends WebDriverUtility
{
	//Declaration
	@FindBy(xpath="//a[text()='Contacts']") 
	private WebElement contactsTab;
	
	@FindBy(xpath="(//a[text()='Organizations'])[1]") 
	private WebElement OrganizationsTab;
	
	@FindBy(xpath="//img[@src='themes/softed/images/user.PNG']") 
	private WebElement adminIcon;
	
	@FindBy(xpath="//a[text()='Sign Out']") 
	private WebElement signOutBtn;
	

	//initialization
	public HomePage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	//Utilization
	public WebElement getContactsTab() {
		return contactsTab;
	}


	public WebElement getOrganizationsLink() {
		return OrganizationsTab;
	}


	public WebElement getAdminIcon() {
		return adminIcon;
	}


	public WebElement getSignOutBtn() {
		return signOutBtn;
	}
	
	// Buisiness Libray: Generic methods according to the need of project
	
	/**
	 * This method clicks on Organization link
	 */
	public void clickOnOrganizationLink()
	{
		OrganizationsTab.click();
	}
	
	/**
	 * This method clicks on Contacts link
	 */
	public void clickOnContactsLink()
	{
		contactsTab.click();
	}
	
	/**
	 * This method logout of application
	 * @param driver
	 * @throws InterruptedException
	 */
	public void logOut(WebDriver driver) throws InterruptedException
	{
		mouseHoverAction(driver,adminIcon);
		Thread.sleep(2000);
		signOutBtn.click();
		
	}
	
	
}

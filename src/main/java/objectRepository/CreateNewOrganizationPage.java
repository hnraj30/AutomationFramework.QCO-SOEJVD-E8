package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericUtilities.WebDriverUtility;

public class CreateNewOrganizationPage extends WebDriverUtility
{
	//Declaration
	@FindBy(name="accountname") 
	private WebElement orgNameEdt;
	
	@FindBy(name="industry") 
	private WebElement industryDD;
	
	@FindBy(name="accounttype") 
	private WebElement accTypeDD;
	
	@FindBy(xpath = "(//input[@title='Save [Alt+S]'])[1]") 
	private WebElement saveBtn;

	//Initialization
	public CreateNewOrganizationPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	//Utilization
	public WebElement getOrgNameEdt() {
		return orgNameEdt;
	}

	public WebElement getIndustryDD() {
		return industryDD;
	}

	public WebElement getAccTypeDD() {
		return accTypeDD;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}
	
	
	//Buisiness Library
	/**
	 * This method creates organization with mandatory field
	 * @param ORGNAME
	 */
	public void createNewOrg(String ORGNAME)
	{
		orgNameEdt.sendKeys(ORGNAME);
		saveBtn.click();
	}
	/**
	 * This method creates organization by selecting option from the Industry dropdown
	 * @param ORGNAME
	 * @param INDUSTRY
	 */
	public void createNewOrg(String ORGNAME,String INDUSTRY)
	{
		orgNameEdt.sendKeys(ORGNAME);
		selectFromDropdown(industryDD, INDUSTRY);
		saveBtn.click();
	}
	/**
	 * This method creates organization by selecting option from the Industry dropdown and type dropdown
	 * @param ORGNAME
	 * @param INDUSTRY
	 * @param TYPE
	 */
	public void createNewOrg(String ORGNAME,String INDUSTRY,String TYPE)
	{
		orgNameEdt.sendKeys(ORGNAME);
		selectFromDropdown(INDUSTRY, industryDD);
		selectFromDropdown(TYPE, accTypeDD);
		saveBtn.click();
	}
	
}


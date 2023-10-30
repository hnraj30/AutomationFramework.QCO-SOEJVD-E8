package organizationTests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import genericUtilities.BaseClass;
import io.github.bonigarcia.wdm.WebDriverManager;
import objectRepository.CreateNewOrganizationPage;
import objectRepository.HomePage;
import objectRepository.LoginPage;
import objectRepository.OrganizationInformationPage;
import objectRepository.OrganizationsPage;
/**
 * 
 * @author hnraj
 *
 */
public class CreateOrganizationWithIndustryAndTypeUsingBaseClassTest extends BaseClass
{
	@Test
	public void createOrgWithIndAndTypeUsingBaseClass() throws IOException, InterruptedException
	{
		String ORGNAME = eUtil.readDataFromExcelFile("Organization", 7, 2)+jUtil.getRandomNumber();
		String INDUSTRY = eUtil.readDataFromExcelFile("Organization", 7, 3);
		String TYPE = eUtil.readDataFromExcelFile("Organization", 7, 4);
		System.out.println("Organization: "+ ORGNAME);
		System.out.println("Industry: "+ INDUSTRY);
		System.out.println("Type: "+ TYPE);
		
		HomePage hp = new HomePage(driver);
		hp.clickOnOrganizationLink();
		//Click on create organization lookup image
		OrganizationsPage op = new OrganizationsPage(driver);
		op.clickOnOrganizationLookUpImg();
		//create organization 
		CreateNewOrganizationPage cnop = new CreateNewOrganizationPage(driver);
		cnop.createNewOrg(ORGNAME, INDUSTRY, TYPE);
		// capture organization header
		OrganizationInformationPage oip =new OrganizationInformationPage(driver);
		Thread.sleep(3000);
		String orgHeader = oip.getHeaderText();
		Assert.assertTrue(orgHeader.contains(ORGNAME));
		System.out.println(ORGNAME + " organization created successfully");
		
		
		
		
	}
	
}

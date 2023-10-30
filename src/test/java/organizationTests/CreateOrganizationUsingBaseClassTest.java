package organizationTests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import genericUtilities.BaseClass;
import genericUtilities.ExcelFileUtility;
import genericUtilities.JavaUtility;
import genericUtilities.PropertyFileUtility;
import genericUtilities.WebDriverUtility;
import io.github.bonigarcia.wdm.WebDriverManager;
import objectRepository.CreateNewOrganizationPage;
import objectRepository.HomePage;
import objectRepository.LoginPage;
import objectRepository.OrganizationInformationPage;
import objectRepository.OrganizationsPage;

public class CreateOrganizationUsingBaseClassTest extends BaseClass
{
	@Test(groups="RegressionSuite")
	public void createOrganizationUsingBaseClass() throws InterruptedException, IOException 
	{
		String ORGNAME = eUtil.readDataFromExcelFile("Organization", 4, 2)+jUtil.getRandomNumber();
		System.out.println("Organization: "+ORGNAME );
		String INDUSTRY = eUtil.readDataFromExcelFile("Organization", 4, 3);
		System.out.println("Industry: "+INDUSTRY );
		
		//Click on organization
		HomePage hp = new HomePage(driver);
		hp.clickOnOrganizationLink();
		
		//Click on create organization lookup image
		OrganizationsPage op = new OrganizationsPage(driver);
		op.clickOnOrganizationLookUpImg();
		
		//Create organization
		CreateNewOrganizationPage cnop = new CreateNewOrganizationPage(driver);
		cnop.createNewOrg(ORGNAME);
		Thread.sleep(3000);
		
		//Capture header text from organization information
		OrganizationInformationPage oip = new OrganizationInformationPage(driver);
		String headerText = oip.getHeaderText();
		
		//Validation
		Assert.assertTrue(headerText.contains(ORGNAME));
		System.out.println(ORGNAME+ " Organization created successfully");
		
		Thread.sleep(3000);
				
	}

}

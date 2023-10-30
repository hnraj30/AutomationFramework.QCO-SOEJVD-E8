package organizationTests;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import genericUtilities.BaseClass;
import objectRepository.CreateNewOrganizationPage;
import objectRepository.HomePage;
import objectRepository.LoginPage;
import objectRepository.OrganizationInformationPage;
import objectRepository.OrganizationsPage;

public class CreateMultipleOrganizationWithIndustryUsingBaseClass extends BaseClass
{
	
	@Test(dataProvider = "getData")
	
	public void createMultipleOrganizationWithIndustry(String ORG, String IND) throws InterruptedException, IOException
	{
		String ORGNAME = ORG+jUtil.getRandomNumber();
		String INDUSTRY = IND;
		
		//click on organization link
		HomePage hp = new HomePage(driver);
		hp.clickOnOrganizationLink();
		//Click on create organization lookup image
		OrganizationsPage op = new OrganizationsPage(driver);
		op.clickOnOrganizationLookUpImg();
		//create organization 
		CreateNewOrganizationPage cnop = new CreateNewOrganizationPage(driver);
		cnop.createNewOrg(ORGNAME, INDUSTRY);
		// capture organization header
		OrganizationInformationPage oip =new OrganizationInformationPage(driver);
		Thread.sleep(3000);
		wUtil.captureScreenshot(driver, INDUSTRY);
		
		String orgHeader = oip.getHeaderText();
		Assert.assertTrue(orgHeader.contains(ORGNAME));
		System.out.println(ORGNAME + " organization created successfully");
		
		
		
	}
	

	@DataProvider
	public Object[][] getData() throws EncryptedDocumentException, IOException 
	{
		return eUtil.readMultipleData("MultipleOrganizations");
	}

}

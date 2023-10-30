package contactsTests;

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
import objectRepository.ContactInformationPage;
import objectRepository.ContactsPage;
import objectRepository.CreateNewContactPage;
import objectRepository.CreateNewOrganizationPage;
import objectRepository.HomePage;
import objectRepository.LoginPage;
import objectRepository.OrganizationInformationPage;
import objectRepository.OrganizationsPage;

public class CreateContWithOrgUsingBaseClassTest extends BaseClass
{
	@Test(groups="SmokeSuite")
	public void createContactWithOrgTest() throws IOException, InterruptedException 
	{
		//Read required data from excel file
		String LASTNAME = eUtil.readDataFromExcelFile("Contacts", 7, 2)+jUtil.getRandomNumber();
		System.out.println("Last Name : "+LASTNAME);
		String ORGNAME = eUtil.readDataFromExcelFile("Contacts", 7, 3)+jUtil.getRandomNumber();
		System.out.println("Org.Name : "+ORGNAME);
		
		
		//click on organization link
		HomePage hp = new HomePage(driver);
		hp.clickOnOrganizationLink();
		
		//click on create organization look up image
		OrganizationsPage op = new OrganizationsPage(driver);
		op.clickOnOrganizationLookUpImg();
		
		//create org with mandatory field
		CreateNewOrganizationPage cnop =  new CreateNewOrganizationPage(driver);
		cnop.createNewOrg(ORGNAME);
		
		
		//validation
		Thread.sleep(3000);
		OrganizationInformationPage oip = new OrganizationInformationPage(driver);
		 String orgHeader = oip.getHeaderText();
		if (orgHeader.contains(ORGNAME))
		{
			System.out.println("Organization" +orgHeader+" created successfully with " + ORGNAME);
		 }
		 else
		{
			System.out.println("Failed to create contact ");
		}	
	
		//Click on contacts link
		hp.clickOnContactsLink();
		
		//click on create contact look up image
		ContactsPage cp =new ContactsPage(driver);
		cp.clickOnCreateContactImg();
		//create new contact
		CreateNewContactPage cncp = new CreateNewContactPage(driver);
		cncp.createNewContact(LASTNAME, ORGNAME, driver);
		Thread.sleep(3000);
		
		ContactInformationPage cip = new ContactInformationPage(driver);
		String contactHeader = cip.getContactHeaderText();
								
		//Validation
		Assert.assertTrue(contactHeader.contains(LASTNAME));
		System.out.println("Contact" +contactHeader+" created successfully with " + ORGNAME);
			

	}
	
	
	
	
	
	
	
	
	
	
	
	
}

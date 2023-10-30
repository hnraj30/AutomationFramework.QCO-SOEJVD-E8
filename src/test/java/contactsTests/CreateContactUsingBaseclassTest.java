package contactsTests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
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
import objectRepository.HomePage;
import objectRepository.LoginPage;

@Listeners(genericUtilities.ListenersImplementationClass.class)
public class CreateContactUsingBaseclassTest extends BaseClass

{
	@Test()	//groups="SmokeSuite"
	
	public  void createContactTest() throws InterruptedException, IOException 
	{
		
		String LASTNAME = eUtil.readDataFromExcelFile("Contacts", 1, 2)+jUtil.getRandomNumber();
		System.out.print("Last name: "+LASTNAME);
	
		//click on contacts link
		HomePage hp = new HomePage(driver);
		hp.clickOnContactsLink();
		Reporter.log("Clicked on contacts link");
		
		//click on create new contact look up image
		ContactsPage cp = new ContactsPage(driver);
		cp.clickOnCreateContactImg();
		
		Reporter.log("Clicked on create new contact look up image");
		
		//create new contact
		CreateNewContactPage cncp = new CreateNewContactPage(driver);
		cncp.createNewContact(LASTNAME);
		
		//capture header text from contact information page
		ContactInformationPage cip = new ContactInformationPage(driver);
		String contactHeader = cip.getContactHeaderText();
		Reporter.log("Header text captured from contact information page");

		//Validation
		Assert.assertTrue(contactHeader.contains(LASTNAME));
		System.out.println("Contact"+ contactHeader + " created successfully");
		//Assert.fail();
		Reporter.log("Header validated");

		
		Thread.sleep(3000);
		

	}
	@Test()	//dependsOnMethods = "createContactTest"
	public void Demo()
	{
		System.out.println("----From demo---- ");
	}

	
	
	
	
	
	
	
	
}

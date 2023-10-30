package contactsTests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

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

public class CreateContactWithOrganizationTest
{
/**
 * This method creates contact with organization by using data from excel file and property file
 * @param args
 * @throws IOException
 * @throws InterruptedException
 */
	@Test
	public void createContactWithOrgTest() throws IOException, InterruptedException 
	{
		//Create object for all required utility
		PropertyFileUtility pUtil = new PropertyFileUtility();
		ExcelFileUtility eUtil =  new ExcelFileUtility();
		JavaUtility jUtil = new JavaUtility();
		WebDriverUtility wUtil = new WebDriverUtility();

		//Read required data from property file
		String BROWSER = pUtil.readDataFromPropertyFile("browser");
		String URL = pUtil.readDataFromPropertyFile("url");
		String USERNAME = pUtil.readDataFromPropertyFile("username");
		String PASSWORD = pUtil.readDataFromPropertyFile("password");
		
		//Read required data from excel file
		String LASTNAME = eUtil.readDataFromExcelFile("Contacts", 7, 2)+jUtil.getRandomNumber();
		System.out.println("Last Name : "+LASTNAME);
		String ORGNAME = eUtil.readDataFromExcelFile("Contacts", 7, 3)+jUtil.getRandomNumber();
		System.out.println("Org.Name : "+ORGNAME);
		
		WebDriver driver = null;
		//Launch Browser // Runtime Polymorphism - driver
		if(BROWSER.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			System.out.println(BROWSER+ " launched");
		}
		else if(BROWSER.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			System.out.println(BROWSER+ " launched");
		}
		else if(BROWSER.equalsIgnoreCase("edge"))
		{
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			System.out.println(BROWSER+ " launched");
		}
		else
		{
			System.out.println("Invalid browser name");
		}
		
		wUtil.maximizeWindow(driver);
		wUtil.waitForPageLoad(driver);
		driver.get(URL);
		//Login to application
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(USERNAME, PASSWORD);
		
		
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
		if (contactHeader.contains(LASTNAME))
		{
			System.out.println("Contact" +contactHeader+" created successfully with " + ORGNAME);
		 }
		 else
		{
			System.out.println("Failed to create contact ");
		}	
	
		//Logout of application
		hp.logOut(driver);
		Thread.sleep(3000);
		driver.close();				

	}

}

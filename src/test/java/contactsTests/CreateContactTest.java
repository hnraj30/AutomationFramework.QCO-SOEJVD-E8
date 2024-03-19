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
import objectRepository.HomePage;
import objectRepository.LoginPage;

public class CreateContactTest {
	@Test
	public  void createContactTest() throws InterruptedException, IOException 
	{
		//Create object for all required utility
		PropertyFileUtility pUtil = new PropertyFileUtility();
		ExcelFileUtility eUtil =  new ExcelFileUtility();
		JavaUtility jUtil = new JavaUtility();
		WebDriverUtility wUtil = new WebDriverUtility();
		
		
		
		
		//Read required data
		String BROWSER = pUtil.readDataFromPropertyFile("browser");
		String URL = pUtil.readDataFromPropertyFile("url");
		String USERNAME = pUtil.readDataFromPropertyFile("username");
		String PASSWORD = pUtil.readDataFromPropertyFile("password");
		
		String LASTNAME = eUtil.readDataFromExcelFile("Contacts", 1, 2)+jUtil.getRandomNumber();
		System.out.print("Last name: "+LASTNAME);
		
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
		
		//click on contacts link
		HomePage hp = new HomePage(driver);
		hp.clickOnContactsLink();
		
		//click on create new contact look up image
		ContactsPage cp = new ContactsPage(driver);
		cp.clickOnCreateContactImg();
		
		//create new contact
		CreateNewContactPage cncp = new CreateNewContactPage(driver);
		cncp.createNewContact(LASTNAME);
		
		//capture header text from contact information page
		ContactInformationPage cip = new ContactInformationPage(driver);
		String contactHeader = cip.getContactHeaderText();
		
		//Validation
		if (contactHeader.contains(LASTNAME))
		{
			System.out.println("Contact"+ contactHeader + " created successfully");
		 }
		 else
		{
			System.out.println("Fail");
		}
		Thread.sleep(3000);
		
		//Logout application
		hp.logOut(driver);
		
		driver.close();		

	}

}

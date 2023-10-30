package organizationTests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

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

public class CreateOrganizationWithIndustryTest {
/**
 * This method creates organization by reading organization name and industry 
 * @param args
 * @throws IOException
 * @throws InterruptedException
 */
	public void createOrganizationWithIndustryTest() throws IOException, InterruptedException 
	{		
		//Create object for all required utility
		ExcelFileUtility eUtil = new ExcelFileUtility();
		JavaUtility jUtil = new JavaUtility();
		PropertyFileUtility pUtil = new PropertyFileUtility();
		WebDriverUtility wUtil = new WebDriverUtility();
		
		//Read all required data
		String USERNAME = pUtil.readDataFromPropertyFile("username");
		String PASSWORD = pUtil.readDataFromPropertyFile("password");
		String URL = pUtil.readDataFromPropertyFile("url");
		String BROWSER = pUtil.readDataFromPropertyFile("browser");

		String ORGNAME = eUtil.readDataFromExcelFile("Organization", 4, 2)+jUtil.getRandomNumber();
		String INDUSTRY = eUtil.readDataFromExcelFile("Organization", 4, 3);
		System.out.println("Organization: "+ ORGNAME);
		System.out.println("Industry: "+ INDUSTRY);

		//Launch browser
		WebDriver driver = null;
		if(BROWSER.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			System.out.println(BROWSER + " browser launched");
		}
		else if(BROWSER.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			System.out.println(BROWSER + " browser launched");
		}
		else if(BROWSER.equalsIgnoreCase("edge"))
		{
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			System.out.println(BROWSER + " browser launched");
		}
		else
		{
			System.out.println("Invalid browser ");

		}
		//maximize window
		wUtil.maximizeWindow(driver);
		wUtil.waitForPageLoad(driver);
		//Open application
		driver.get(URL);
		//Login to the application
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(USERNAME, PASSWORD);
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
		String orgHeader = oip.getHeaderText();
		if(orgHeader.contains(ORGNAME))
		{
			System.out.println(ORGNAME + " organization created successfully");
		}
		else
		{
			System.out.println(" organization not created ");
		}
		//Logout
		hp.logOut(driver);
		Thread.sleep(3000);
		driver.close();
		
		
		
		
		
		
	}

}

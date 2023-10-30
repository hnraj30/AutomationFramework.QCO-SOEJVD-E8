package organizationTests;

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
import objectRepository.CreateNewOrganizationPage;
import objectRepository.HomePage;
import objectRepository.LoginPage;
import objectRepository.OrganizationInformationPage;
import objectRepository.OrganizationsPage;

public class CreateOrganizationTest {
/**
 * This method creates organization by reading organization name and industry from excel file
 * @param args
 * @throws IOException
 * @throws InterruptedException
 */
	@Test
	
	public void createOrganizationTest() throws IOException, InterruptedException 
	{
		//Create object for all utilities
		ExcelFileUtility eUtil = new ExcelFileUtility();
		JavaUtility jUtil = new JavaUtility();
		PropertyFileUtility pUtil = new PropertyFileUtility();
		WebDriverUtility wUtil = new WebDriverUtility();
		
		//Read all required data
		String USERNAME = pUtil.readDataFromPropertyFile("username");
		String PASSWORD = pUtil.readDataFromPropertyFile("password");
		String BROWSER = pUtil.readDataFromPropertyFile("browser");
		String URL = pUtil.readDataFromPropertyFile("url");
		
		String ORGNAME = eUtil.readDataFromExcelFile("Organization", 4, 2)+jUtil.getRandomNumber();
		System.out.println("Organization: "+ORGNAME );
		String INDUSTRY = eUtil.readDataFromExcelFile("Organization", 4, 3);
		System.out.println("Industry: "+INDUSTRY );
		
		//Launch browser
		WebDriver driver = null ;
		if (BROWSER.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			System.out.println(BROWSER +" browser launched");
		}
		else if (BROWSER.equalsIgnoreCase("edge"))
		{
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			System.out.println(BROWSER +" browser launched");
		}
		else if (BROWSER.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			System.out.println(BROWSER +" browser launched");
		}
		else
		{
			System.out.println(" Invalid browser name");
		}
		
		//Maximize window
		wUtil.maximizeWindow(driver);
		wUtil.waitForPageLoad(driver);
		driver.get(URL);
		
		//Login to the application
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(USERNAME, PASSWORD);
		
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
		if (headerText.contains(ORGNAME))
		{
			System.out.print(ORGNAME+ " Organization created successfully");
		}
		else
		{
			System.out.print(ORGNAME+ " Organization not created ");

		}
			
		Thread.sleep(3000);
		//logout application
		hp.logOut(driver);
		
		driver.close();

	}

}

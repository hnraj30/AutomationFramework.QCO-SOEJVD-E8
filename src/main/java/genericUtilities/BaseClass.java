package genericUtilities;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;
import objectRepository.HomePage;
import objectRepository.LoginPage;

/**
 * This class consists of all basic configuration of testNG
 * @author hnraj
 *
 */
public class BaseClass 
{
	public PropertyFileUtility pUtil = new PropertyFileUtility();
	public ExcelFileUtility eUtil =  new ExcelFileUtility();
	public JavaUtility jUtil = new JavaUtility();
	public WebDriverUtility wUtil = new WebDriverUtility();
	public WebDriver driver =  null;
	
	//to use only in Listeners(updated)
	public static WebDriver sdriver;
	
	@BeforeSuite(alwaysRun=true)
	public void bsConfig()
	{
		System.out.println("----------Database connection successful---------------");
	}
	
	//@Parameters("browser")
	//@BeforeTest	//Use only for distributed parallel execution, cross browser execution
	@BeforeClass(alwaysRun=true)
	public void bcConfig(/*String BROWSER*/) throws IOException
	{
		String BROWSER = pUtil.readDataFromPropertyFile("browser");
		String URL = pUtil.readDataFromPropertyFile("url");
		
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
		
		//initialising sdriver with updated driver
		sdriver=driver;
		
		
	}
	@BeforeMethod(alwaysRun=true)
	public void bmConfig() throws IOException
	{
		String USERNAME = pUtil.readDataFromPropertyFile("username");
		String PASSWORD = pUtil.readDataFromPropertyFile("password");
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(USERNAME, PASSWORD);
		System.out.println("Login successfull");
		
	}
	
	@AfterMethod(alwaysRun=true)
	public void amConfig() throws InterruptedException
	{
		HomePage hp = new HomePage(driver);
		hp.logOut(driver);
		System.out.println("Logout successfull");
	}
	
	
	@AfterClass(alwaysRun=true)
	//@AfterTest(alwaysRun=true)//Use only for distributed parallel execution
	public void acConfig()
	{
		driver.quit();
		System.out.println("Browser closed");
		
	}
	@AfterSuite(alwaysRun=true)
	public void asConfig()
	{
		System.out.println("----------Database connection closed---------------");
	}
	

}

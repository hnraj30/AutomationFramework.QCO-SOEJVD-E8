package practice;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

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

public class Scenario1WithGenericUtility 
{

	public static void main(String[] args) throws IOException, InterruptedException
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
		System.out.print(LASTNAME);
		
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
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get(URL);
		/*driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		*/
		LoginPage lp = new LoginPage(driver);
		HomePage hp = new HomePage(driver);
		ContactsPage cp = new ContactsPage(driver);
		CreateNewContactPage cncp = new CreateNewContactPage(driver);
		ContactInformationPage  cip= new ContactInformationPage(driver);
		
		/*lp.getUserNameEdt().sendKeys(USERNAME);
		lp.getPasswordEdt().sendKeys(PASSWORD);
		lp.getLoginBtn().click();
		*/
		lp.loginToApp(USERNAME, PASSWORD);
		
		
		//driver.findElement(By.xpath("//a[text()='Contacts']")).click();
		hp.getContactsTab().click();
		Thread.sleep(3000);
		
		//driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		//driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(LASTNAME);
		//driver.findElement(By.xpath("(//input[@value='  Save  '])[2]")).click();
		cp.getCreateContBtn().click();
		cncp.getLastNameEdt().sendKeys(LASTNAME);
		cncp.getSaveBtn().click();
		
		//String contactHeader = driver.findElement(By.xpath("//td[@id='mouseArea_Last Name']")).getText();
		String contactHeader = cip.getContactHeaderText();
		if (contactHeader.contains(LASTNAME))
		{
			System.out.println("Pass");
		 }
		 else
		{
			System.out.println("Fail");
		}
		Thread.sleep(3000);
		
		//WebElement signOut = driver.findElement(By.xpath("//img[@style='padding: 0px;padding-left:5px']"));
		//WebElement signOut = hp.getAdminIcon();
		//wUtil.mouseHoverAction(driver, signOut);
		//Thread.sleep(2000);
		//driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		//hp.getSignOutBtn().click();
		
		hp.logOut(driver);
		System.out.println("Signed out successfully");
		Thread.sleep(2000);
		
		driver.close();		
		
	}

}

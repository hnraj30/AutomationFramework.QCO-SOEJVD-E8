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

public class Scenario5WithGenericUtility 
{

	public static void main(String[] args) throws IOException, InterruptedException 
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
		
		//Login to the application
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		//navigate to Organization link
		driver.findElement(By.xpath("(//a[text()='Organizations'])[1]")).click();
		//Click on create Organization
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
		//Enter Organization name into text field
		driver.findElement(By.name("accountname")).sendKeys(ORGNAME);
		//click on save
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		//Get Organization header text
		String orgHeader = driver.findElement(By.xpath("//span[@id='dtlview_Organization Name']")).getText();
		System.out.println("Org.Header: "+orgHeader);
		if (orgHeader.contains(ORGNAME))
		{
			System.out.println("Organization "+ORGNAME+" created successfully");
		}
		else
		{
			System.out.print("Organization creation failed");
		}
		Thread.sleep(3000);
	
		//create contact using newly created organization
		//Navigate to contacts
		driver.findElement(By.xpath("//a[text()='Contacts']")).click();
		//Create contact using newly created organization
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		//Enter last name into text field
		driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(LASTNAME);
		//click on organization look up icon 
		driver.findElement(By.xpath("//img[@src='themes/softed/images/select.gif']")).click();
		
		//switch to child window
		wUtil.switchToWindow(driver, "Accounts");
		//enter organization name into search text field
		driver.findElement(By.xpath("//input[@id='search_txt']")).sendKeys(ORGNAME);
		//click on search button
		driver.findElement(By.name("search")).click();
		//select organization name from the list
		driver.findElement(By.xpath("//a[text()='"+ORGNAME+"']")).click();
		//switch to main window
		wUtil.switchToWindow(driver, "Contacts");
		
		//click on save
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		String contactHeader = driver.findElement(By.xpath("//td[@id='mouseArea_Last Name']")).getText();
		System.out.println("Contact header: "+contactHeader);		
		
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
		WebElement icon = driver.findElement(By.xpath("(//img[@style='padding: 0px;padding-left:5px'])[1]"));
		wUtil.mouseHoverAction(driver, icon);
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		
		System.out.println("Sign Out successfull");
		driver.close();

	}

}

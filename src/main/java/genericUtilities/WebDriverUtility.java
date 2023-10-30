package genericUtilities;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;

/**
 * This class consists of all generic methods related to web driver actions
 * @author hnraj
 *
 */
public class WebDriverUtility 
{
	/**
	 * This method will maximize the window
	 * @param driver
	 */
	public void maximizeWindow(WebDriver driver)
	{
		driver.manage().window().maximize();
	}
	/**
	 * This method will minimize window
	 * @param driver
	 */
	public void minimizeWindow(WebDriver driver)
	{
		driver.manage().window().minimize();
	}	
	/**
	 * This method will implicitly wait for 10 seconds
	 * @param driver
	 */
	public void waitForPageLoad(WebDriver driver)
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}	
	/**
	 * This method will wait for the element to be visible in the DOM(Document Object Model)
	 * @param driver
	 * @param element
	 */
	public void waitForElementToBeVisible(WebDriver driver,WebElement element)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(element));
	}	
	/**
	 * This method will wait for the element to be clickable
	 * @param driver
	 * @param element
	 */
	public void waitForElementToBeClickable(WebDriver driver,WebElement element)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	/**
	 * This method will select the option from dropdown using index
	 * @param element
	 * @param text
	 */
	public void selectFromDropdown(WebElement element,int index)
	{
		Select s = new Select(element);
		s.selectByIndex(index);
	}	
	/**
	 * This method will select the option from dropdown using value
	 * @param element
	 * @param value
	 */
	public void selectFromDropdown(WebElement element,String value)
	{
		Select s = new Select(element);
		s.selectByValue(value);
	}	
	/**
	 * This method will select the option from dropdown using visible text
	 * @param text
	 * @param element
	 */
	public void selectFromDropdown(String text,WebElement element)
	{
		Select s = new Select(element);
		s.selectByVisibleText(text);
	}	
	/**
	 * This method will perform mouse hover action
	 * @param driver
	 * @param element
	 */
	public void mouseHoverAction(WebDriver driver,WebElement element)
	{
		Actions a = new Actions(driver);
		a.moveToElement(element).perform();
	}
	/**
	 * This method will do double click on element
	 * @param element
	 * @param driver
	 */
	public void doubleClickAction(WebDriver driver,WebElement element)
	{
		Actions a = new Actions(driver);
		a.doubleClick(element).perform();
	}	
	/**
	 * This method will do right click on element
	 * @param driver
	 * @param element
	 */
	public void rightClickAction(WebDriver driver,WebElement element)
	{
		Actions a = new Actions(driver);
		a.contextClick(element).perform();
	}
	/**
	 * This method will drag and drop click on element
	 * @param driver
	 * @param element1
	 * @param element2
	 */
	public void dragAndDropAction(WebDriver driver,WebElement srcEle,WebElement destEle)
	{
		Actions a = new Actions(driver);
		a.dragAndDrop(srcEle, destEle).perform();
	}
	/**
	 * This method will move the cursor based on offset and click on web page
	 * @param driver
	 * @param element1
	 */
	public void moveAndClick(WebDriver driver,WebElement element1)
	{
		Actions a = new Actions(driver);
		a.moveByOffset(10, 10).click().perform();
	}
	/**
	 * This method will handle frame by index
	 * @param driver
	 */
	public void switchToFrame(WebDriver driver,int index)
	{
		driver.switchTo().frame(index);
	}
	/**
	 * This method will handle frame by name or ID
	 * @param driver
	 */
	public void switchToFrame(WebDriver driver,String NameOrID)
	{
		driver.switchTo().frame(NameOrID);
	}
	/**
	 * This method will handle frame by web element
	 * @param driver
	 */
	public void switchToFrame(WebDriver driver,WebElement element)
	{
		driver.switchTo().frame(element);
	}	
	/**----Check this----
	 * This method will switch control back to default page
	 * @param driver
	 */
	public void switchToNormalPage(WebDriver driver)
	{
		driver.switchTo().defaultContent();
	}	
	/**
	 * This method will handle scroll bar until element
	 * @param driver
	 * @param element
	 */
	public void scrollDownUntilElement(WebDriver driver,WebElement element)
	{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguements[0].scrollIntoView()", element);
	}	  
	/**
	 * This method will scroll down by 500 units
	 * @param driver
	 * @param element
	 */
	public void scrollDownAction(WebDriver driver,WebElement element)
	{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,500);","");
	}	
	/**
	 * This method will scroll up by 500 units
	 * @param driver
	 * @param element
	 */
	public void scrollUpAction(WebDriver driver,WebElement element)
	{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,-500);","");
	}	
	/**
	 * This method will accept alert popup
	 * @param driver
	 */
	public void acceptAlertPopup(WebDriver driver)
	{
		driver.switchTo().alert().accept();
	}
	/**
	 * This method will dismiss alert popup
	 * @param driver
	 */
	public void dismissAlertPopup(WebDriver driver)
	{
		driver.switchTo().alert().dismiss();
	}	
	/**
	 * This method will get test from alert popup
	 * @param driver
	 * @return
	 */
	public String getTextFromAlertPopup(WebDriver driver)
	{
		return driver.switchTo().alert().getText();
	}	
	
	
	
	
	/**
	 * This method will capture screenshot and returns the destination path
	 * @param driver
	 * @return 
	 * @throws IOException 
	 */
	public String captureScreenshot(WebDriver driver,String screenshotName) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File(".\\Screenshot\\"+screenshotName+".png");
		Files.copy(src, dest);
		return dest.getAbsolutePath();//used for extent reports
	}
	/**
	 * This method will switch to window using partial title
	 * @param driver
	 * @param partialTitle
	 */
	public void switchToWindow(WebDriver driver,String partialTitle)
	{
		// Get all the window handles
		Set<String> allWinIDs = driver.getWindowHandles();
		//Navigate through each window
		for(String winID:allWinIDs)
		{
			//Switch to each window and capture the title
			String actTitle = driver.switchTo().window(winID).getTitle();
			// Compare actual title with partial title 
			 if(actTitle.contains(partialTitle))
			 {
				 break;
			 }
		}
		
	}
	
}


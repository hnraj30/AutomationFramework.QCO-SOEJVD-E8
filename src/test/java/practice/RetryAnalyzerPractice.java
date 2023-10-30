package practice;

import static org.testng.Assert.fail;

import org.testng.Assert;
import org.testng.annotations.Test;

public class RetryAnalyzerPractice 
{
	@Test(retryAnalyzer = genericUtilities.RetryAnalyzerImplementation.class)
	
	public void AnalyserPractice()
	{
		System.out.println("--------------trial run---------------");
		Assert.fail();
	}

}

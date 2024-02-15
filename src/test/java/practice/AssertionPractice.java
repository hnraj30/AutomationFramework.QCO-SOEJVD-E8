package practice;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AssertionPractice 
{
	@Test
	
	public void assertionPractice()
	{
		
		
		System.out.println("Step:1 ");
		System.out.println("Step:2 ");
		boolean b = false;
		
		Assert.assertEquals(b, true);
		
		System.out.println("Step:3 ");
		System.out.println("Step:4 ");
		
		
		
		
		
		
	}
	
@Test
	
	public void assertionPractice1()
	{
		SoftAssert sa = new SoftAssert();
		
		System.out.println("Step:1 ");
		System.out.println("Step:2 ");
		boolean b = false;
		
		sa.assertEquals(b, true);
		
		
		System.out.println("Step:3 ");
		System.out.println("Step:4 ");
		sa.assertAll();
		
	}

}

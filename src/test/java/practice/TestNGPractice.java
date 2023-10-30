package practice;

import org.testng.annotations.Test;

	public class TestNGPractice {

	@Test()
	
	public void createCustomerTest()
	{
		System.out.println("Create");
	}
	@Test(dependsOnMethods="createCustomerTest")
	
	public void modifyCustomerTest()
	{
		System.out.println("Modify");
	}
	@Test()

	public void deleteCustomerTest()
	{
		System.out.println("Delete");
	}

	
}

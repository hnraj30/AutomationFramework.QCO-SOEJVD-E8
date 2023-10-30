package practice;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderPractice 
{
	@Test(dataProvider="getData")
	public void addProductToCart(String name,int price,int qty,String model)
	{
		System.out.println("Phone Name: "+name+"Price: "+price+"Qty: "+qty+"Model: "+model);
	}
	
	@DataProvider
	public Object[][] getData()
	{
		Object[][] data = new Object[3][4];
		data[0][0] = "Samsung";
		data[0][1] = 10000;
		data[0][2] = 20;
		data[0][3] = "S22";
		
		data[1][0] = "Nokia";
		data[1][1] = 12000;
		data[1][2] = 30;
		data[1][3] = "5230";
		
		data[2][0] = "iPhone";
		data[2][1] = 15000;
		data[2][2] = 50;
		data[2][3] = "14 pro";
		
		return data;
	}
	                
	
}

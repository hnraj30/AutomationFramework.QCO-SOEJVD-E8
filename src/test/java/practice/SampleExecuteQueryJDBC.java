package practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;



public class SampleExecuteQueryJDBC
{
	public static void main(String[]args) throws SQLException
	{
		Driver driver = new Driver();
		
		//Step1: Register driver
		DriverManager.registerDriver(driver);
				
		//Step2: Get connection with database
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/customerdb", "root", "root");
		
		// Step3: Issue create statement
		Statement state = con.createStatement();
		
		//Step4: Execute a query
		ResultSet result = state.executeQuery("Select * from customerinfo");
		while(result.next())
		{
			String value = result.getString(1)+" "+result.getString(2)+" "+result.getString(3);	//fetch data column wise
			System.out.println(value);
		}
			
		//Step5: Close the database
		con.close();
		
	}
	
}

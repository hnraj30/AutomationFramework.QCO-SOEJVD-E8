package practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class SampleExecuteupdateJDBC 
{
	public static void main(String[]args) throws SQLException
	{
		Driver driver = new Driver();
		// Register the driver
		DriverManager.registerDriver(driver);
		
		//Get connection with database
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/customerdb", "root", "root");
		
		//Issue create statement
		Statement state = con.createStatement();
		
		//Execute query
		String query =  "insert into customerinfo values('WillSmith', 8, 'USA')";
		int result = state.executeUpdate(query);
		if (result==1)
		{
			System.out.println("Data added successfully");
		}
		
		//Close DB
		con.close();
		
	}
	

}

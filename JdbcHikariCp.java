	import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class JdbcHikariCp 
{

	public static void main(String[] args) throws IOException 
	{
		
		Connection connection = null;
		ResultSet resultSet = null;
		Statement statement = null;
		
		
		
		String configFile ="D:\\JavaApplication\\JDBC\\db.properties";
		
		HikariConfig config = new HikariConfig(configFile);
		
		HikariDataSource dataSource = new HikariDataSource(config);
		
		try
		{
		  connection = dataSource.getConnection();
		  statement = connection.createStatement();
			resultSet = statement.executeQuery("select*from bank");

			System.out.println("name\tbalance");
			while(resultSet.next())
			{
				System.out.println(resultSet.getString("name")+"\t"+resultSet.getInt("balance"));
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try 
			{
				resultSet.close();
				statement.close();
				connection.close();
				dataSource.close();
			}
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
			
		}
		
		
	}

}

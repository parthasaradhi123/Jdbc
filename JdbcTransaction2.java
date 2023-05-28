import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;
import java.util.Scanner;

public class JdbcTransaction2 
{

	public static void main(String[] args) 
	{
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		
		//Scanner sc = null;
		
		try 
		{
			connection = UtilityJdbc.getJdbcConnection();
			
			if(connection!=null)
			{
				statement = connection.createStatement();
			}
			 
			
			System.out.println(":: Transaction Begins ::");
			connection.setAutoCommit(false);
			
			statement.executeUpdate("insert into chors(name,party) values('TRS','KCR')");
			statement.executeUpdate("insert into chors(name,party) values('BJP','YOGI')");
			
			Savepoint sp = connection.setSavepoint();
			statement.executeUpdate("insert into chors(name,party) values('CONGRESS','PAPPU')");
			System.out.println("Something went wrong ");
			connection.rollback(sp);
			System.out.println("Operations are rolled out back ");
			connection.commit();

			System.out.println("Transaction Done ");
		}
		catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
		 
			try {
				UtilityJdbc.closeResources(connection, statement, resultSet);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JdbcTransaction {

	public static void main(String[] args) 
	{
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		ResultSet rs = null;
		Scanner sc = null;
		
		
		try 
		{
			System.out.println(":: Data before transaction ::");
			connection = UtilityJdbc.getJdbcConnection();
			if(connection!=null)
			{
				statement = connection.createStatement();
				
				if(statement!=null)
				{
					resultSet = statement.executeQuery("select name,balance from bank");
				}
				if(resultSet!=null)
				{
					System.out.println("name\tbalance");
			
					while(resultSet.next())
					{
						System.out.println(resultSet.getString("name")+"\t"+resultSet.getString("balance"));
					}
				}
			}
			
			System.out.println("===============================================================================");
			sc = new Scanner(System.in);
			
			
			//Transaction begins....
			System.out.println(":: Transaction Begins :: ");
			connection.setAutoCommit(false);
			
			//Prepare the operation as a single unit
			statement.executeUpdate("update bank set balance = balance-5000 where name = 'dhoni'");
			statement.executeUpdate("update bank set balance = balance-5000 where name = 'sachin'");
			System.out.println(":: Could you please confirm the transaction of 5000 INR :: ");
			String opinion = sc.next();
			
			if(opinion.equalsIgnoreCase("yes"))
			{
				connection.commit();
			}
			else
			{
				connection.rollback();
			}
			
			System.out.println(":: data after transaction ::");
			
			 
			rs = statement.executeQuery("select name,balance from bank");
			if(rs!=null)
			{
				System.out.println("name\tbalance");
				while(rs.next())
				{
					System.out.println(rs.getString("name")+"\t"+rs.getString("balance"));

				}
			}
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
			try 
			{
				sc.close();
				UtilityJdbc.closeResources(connection, statement,resultSet);
				UtilityJdbc.closeResources(null, null,rs);
			}
			catch (SQLException e) 
			{
 				e.printStackTrace();
			}
		}

	}

}

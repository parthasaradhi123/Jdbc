import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class JdbcCallable 
{

	public static void main(String[] args)
	{
		Connection connection = null;
		CallableStatement cs  = null;
		ResultSet resultSet = null;
		
		
		
		Scanner sc = null;
		String name = null;
		String name1 = null;
		
		
		
		try 
		{
			connection = UtilityJdbc.getJdbcConnection();
		
			String sqlSearchQuery = "{call get_watch4(?,?)}";
			if(connection!=null)
			{
				sc = new Scanner(System.in);
				if(sc!=null)
				{
					System.out.println(":: Enter the name of the watch :: ");
					name = sc.next();
					
					System.out.println(":: Enter the name of the second watch :: ");
					name1 = sc.next();
				}
				
				cs = connection.prepareCall(sqlSearchQuery);
			}
			if(cs!=null)
			{
				cs.setString(1,name);
				cs.setString(2,name1);
				
				cs.execute();
			}
			resultSet = cs.getResultSet();
			if(resultSet!=null)
			{
				while(resultSet.next())
				{
					System.out.println(resultSet.getInt("id")+"\t"+resultSet.getString("name")+"\t"+
											resultSet.getString("place")+"\t"+resultSet.getInt("age"));
				}
			}
			
		}
		catch (SQLException | IOException e) 
		{
			e.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			sc.close();
			
			try
			{
				UtilityJdbc.closeResources(connection, cs, resultSet);
			} 
			catch (SQLException e) 
			{
 				e.printStackTrace();
			}
		}
	}

}

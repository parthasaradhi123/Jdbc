import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

import com.mysql.cj.xdevapi.Type;

import java.sql.CallableStatement;
 
public class JbcCallable 
{

	public static void main(String[] args) 
	{
		Connection connection = null;
		CallableStatement cs = null;
		
		
		Scanner sc = null;
		Integer id = null;
		
		
		
		try
		{
			connection = UtilityJdbc.getJdbcConnection();
			String sqlCallQuery = "{call get_watch(?,?,?,?)}";
			
			if(connection!=null)
			{
				sc = new Scanner(System.in);
				if(sc!=null)
				{
					System.out.println(" :: Enter the id ::");
					id = sc.nextInt();
				}
				
				cs = connection.prepareCall(sqlCallQuery);
				
				if(cs!=null)
				{
					//assign the id to query;
					cs.setInt(1,id);
				}
				
				if(cs!=null)
				{
					cs.registerOutParameter(2,Types.VARCHAR);
					cs.registerOutParameter(3, Types.VARCHAR);
					cs.registerOutParameter(4, Types.BIGINT);
				}
				
				if(cs!=null)
				{
					cs.execute();
				}
				
				 System.out.println("The name is :: "+cs.getString(2));
				 System.out.println("The place is :: "+cs.getString(3));
				 System.out.println("The age is :: "+cs.getInt(4));
				 

					
							
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
			try {
				sc.close();
				UtilityJdbc.closeResources(connection, cs, null);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}

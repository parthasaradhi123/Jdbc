import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
 import java.sql.Statement;

public class JdbcResultSet 
{

	public static void main(String[] args)
	{
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		

		try 
		{
			connection = UtilityJdbc.getJdbcConnection();
			
			if(connection!=null)
			{
				statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			}
			if(statement!=null) 
			{
				resultSet = statement.executeQuery("select*from chors");
			}
			if(resultSet!=null)
			{
				System.out.println("moving in forward direction");
				System.out.println("name\tparty");
				while(resultSet.next())
				{
					System.out.println(resultSet.getString("name")+"\t"+resultSet.getString("party"));
				}
				
				System.out.println("===================================================");
				System.out.println("Move in backward direction ");
				System.out.println("=======================================================================");
				System.out.println("name\tparty");
				//It will preint the entire data from the backwards or from the last row 
				while(resultSet.previous())
				{
					System.out.println(resultSet.getString("name")+"\t"+resultSet.getString("party"));
				}
				
				//cursor will come to first row and it will fecth the first row data
				System.out.println("==================================================================");
				resultSet.first();
					System.out.println(resultSet.getString("name")+"\t"+resultSet.getString("party"));
				
				//Curosor will come to last row.
				resultSet.last();
					System.out.println(resultSet.getString("name")+"\t"+resultSet.getString("party"));

					//it will print the row 
					System.out.println("----------------------------------------------------------------------");
					resultSet.absolute(3);
					System.out.println(resultSet.getString("name")+"\t"+resultSet.getString("party"));
					
					//It will jump the number of rows from the present cursor to specifies value.
					System.out.println("----------------------------------------------------------------------");
					resultSet.relative(-1);
					System.out.println(resultSet.getString("name")+"\t"+resultSet.getString("party"));
					
					//absolute(-1) it will the curosor to last row.
					
					//relative(-1)it will take the curosor from the present row to before row.
					
					
					
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
		 
			try {
				UtilityJdbc.closeResources(connection, statement, resultSet);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


	}

}

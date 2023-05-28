import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcExcel {

	public static void main(String[] args)
	{
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		
		String url = "jdbc:Excel:///D:\\Java_Full_stack_developer";
		
		try {
			connection = DriverManager.getConnection(url);
			
			if(connection!=null)
			{
				ps = connection.prepareStatement("select*from cric.Student");
			}
			if(ps!=null)
			{
				resultSet = ps.executeQuery();
			}
			System.out.println("ID\t NAME\t PLACE");
			 if(resultSet!=null)
			 {
				while(resultSet.next())
				{
					System.out.println(resultSet.getInt("ID")+"\t"+resultSet.getString("NAME")+"\t"+resultSet.getString("NAME"));
				}
			
			 }
		} 
		catch (SQLException e)
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
				ps.close();
				connection.close();
			} 
			catch (SQLException e)
			{
 				e.printStackTrace();
			}
			
		}
	}

}

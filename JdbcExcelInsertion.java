import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class JdbcExcelInsertion {

	public static void main(String[] args)
	{

		Connection connection = null;
		PreparedStatement ps = null;
 		Scanner sc = null;
		
		Integer id = null;
		String name  = null;
		String team = null;
		
		
		String url = "jdbc:Excel:///D:\\Java_Full_stack_developer";
		
		try {
			connection = DriverManager.getConnection(url);
			
			if(connection!=null)
			{
				
				sc = new Scanner(System.in);
				if(sc!=null)
				{
					System.out.println(":: Enter the id ::");
					id = sc.nextInt();
					
					System.out.println(":: Enter the name :::");
					name = sc.next();
					
					System.out.println(":: Enter the team ::");
					team = sc.next();
					
				}
				
				ps = connection.prepareStatement("insert into cric.Student values(?,?,?)");

				ps.setInt(1, id);
				ps.setString(2, name);
				ps.setString(3, team);
				
				int rowsAffected = ps.executeUpdate();
				
				System.out.println("The number of rows effected are  ::"+rowsAffected);
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
				sc.close();
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

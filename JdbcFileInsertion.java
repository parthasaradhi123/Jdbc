import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class JdbcFileInsertion 
{

	public static void main(String[] args)
	{
		Connection connection =null;
		PreparedStatement pst = null;
		
		Scanner sc = null;
		int empId =0;
		String empName =null;
		String fileLocation = null;
		
		
		
		
		try 
		{
			connection =  UtilityJdbc.getJdbcConnection();
			String sqlInsertQuery = "insert into cities(id,name,history) values(?,?,?)";
			
			if(connection!=null)
			{
				pst = connection.prepareStatement(sqlInsertQuery);
				sc = new Scanner(System.in);
			}
			
			if(pst!=null)
			{
				
				if(sc!=null)
				{
					System.out.println(":: Enter the Id :: ");
					 empId = sc.nextInt();
					
					 System.out.println(":: Enter the name  :: ");
					 empName = sc.next();
					 
					 System.out.println(":: ENter the file Location ::");
					 fileLocation = sc.next();
				}
				
				pst.setInt(1, empId);
				pst.setString(2,empName);
				pst.setCharacterStream(3, new FileReader(new File(fileLocation)));
				
				int rowAffected = pst.executeUpdate();
				
				System.out.println("Number of rows that are effected :: "+rowAffected);
				
			}
			
			
		} 
		catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		catch(Exception  ee)
		{
			ee.printStackTrace();
		}
		finally 
		{
			sc.close();
			try
			{
				UtilityJdbc.closeResources(connection, pst, null);
			}
			catch(SQLException se)
			{
				se.printStackTrace();
			}
			
		}


	}

}

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class JdbcDateInsert 
{

	public static void main(String[] args) 
	{
		Connection connection = null;
		PreparedStatement pst = null;
		
		Scanner sc = null;
		int empId = 0;
		String eName = null;
		String empDob = null;
		String empDom = null;
		
		
		
		
		try
		{
			connection = UtilityJdbc.getJdbcConnection();
			
			String sqlInsertDate = "insert into dateOperations(id,name,dob,dom) values(?,?,?,?)";
			
			if(connection!=null)
			{
				pst = connection.prepareStatement(sqlInsertDate);
				
				if(pst!=null)
				{
					sc = new Scanner(System.in);
					
					if(sc!=null)
					{
						System.out.println();
						
						System.out.println(":: Enter the Id ::");
						empId = sc.nextInt();
						
						System.out.println(":: Enter the name ::");
						 eName = sc.next();
						
						System.out.println(":: Enter your date of birth(dd-MM-yyyy) ::");
						 empDob = sc.next();
						
						System.out.println(":: Enter your date of marriage(yyyy-MM-dd)");
						empDom = sc.next();
		
					}
					
					//trying to convert the user given data of birth to util data to sql data 
					SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
					 
						java.util.Date uDate = sdf.parse(empDob);
						long value = uDate.getTime();
						
						java.sql.Date sqldate = new java.sql.Date(value);
						
						java.sql.Date mdom = java.sql.Date.valueOf(empDom);
					
					
					pst.setInt(1, empId);
					pst.setString(2, eName);
					pst.setDate(3, sqldate);
					pst.setDate(4,mdom);
					
					int rowCount = pst.executeUpdate();
					
					System.out.println("Updation successfull for :: "+rowCount+" row ");
					
					
				}
			}
	
		}
		catch(SQLException |IOException e)
		{
			e.printStackTrace();
		}
		catch (ParseException e)
		{
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		finally
		{
			sc.close();
			try
			{
				System.out.println(":: Closing the resources ::");
				UtilityJdbc.closeResources(connection, pst, null);
			}
			catch(SQLException e )
			{
				e.printStackTrace();
			}
		}
		
	}

}

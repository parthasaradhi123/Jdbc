import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class JdbcBatch 
{

	public static void main(String[] args)
	{
		Connection connection =null;
		PreparedStatement ps = null;
		
		Scanner sc = null;
		Integer id = null;
		String name = null;
		String place = null;
		Integer age = null;
		
		
		try 
		{
			connection =UtilityJdbc.getJdbcConnection();
			String sqlInserQuery = "insert into callable(id,name,place,age) values(?,?,?,?)";
			
			if(connection!=null)
			{
				ps = connection.prepareStatement(sqlInserQuery);
				
				if(ps!=null)
				{
					sc = new Scanner(System.in);
					
					if(sc!=null)
					{
							while(true)
							{
								System.out.println(":: Enter the Id :: ");
								id = sc.nextInt();
								
								System.out.println(":: Enter the name ::");
								name = sc.next();
								
								System.out.println(":: Enter the place ::");
								place = sc.next();
								
								System.out.println(":: Enter the age :: ");
								age = sc.nextInt();
								
								System.out.println("Do you wanna add one more record  type YES/NO  ::");
								String opinion = sc.next();
								
								ps.setInt(1, id);
								ps.setString(2, name);
								ps.setString(3, place);
								ps.setInt(4, age);
								
								
								ps.addBatch();
								
								if(opinion.equalsIgnoreCase("no"))
								{
									break;
								}
								
								
							}
							ps.executeBatch();
							System.out.println(":: Records Inserted Successfully :: ");
					}
					
					 
					
 				}
			}
		} 
		catch (SQLException | IOException e)
		{
 			e.printStackTrace();
		}
		catch(Exception e)
		{
			
		}
		finally
		{
			sc.close();
			try {
				UtilityJdbc.closeResources(connection, ps, null);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}

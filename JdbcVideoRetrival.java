import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.commons.io.IOUtils;

public class JdbcVideoRetrival 
{

	public static void main(String[] args) 
	{


		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		InputStream is = null;
		
		Scanner sc = null;
		int id =0;
		
		try
		{
			connection = UtilityJdbc.getJdbcConnection();
			
			String sqlSelectQuery = "select*from imageOperations where id=?";
			
			
			if(connection!=null)
			{
				sc = new Scanner(System.in);
				
				if(sc!=null)
				{
					System.out.println(":: Enter the your Id :: ");
					id = sc.nextInt();
					
				}
			
				ps = connection.prepareStatement(sqlSelectQuery);
				
				if(ps!=null)
				{
					ps.setInt(1, id);
					
					resultSet = ps.executeQuery();
				}
				
				if(resultSet!=null)
				{
					if(resultSet.next())
					{
						int empid = resultSet.getInt("id");
						String empname = resultSet.getString("name");
						is = resultSet.getBinaryStream("image");
						
						File file = new File("sunnyCopy.mp4");
						FileOutputStream fos = new FileOutputStream(file);
						
						IOUtils.copy(is, fos);
						
						System.out.println("id\t name \t videaddress ");
						System.out.println(empid+"\t"+empname+"\t"+file.getAbsolutePath());
					}
					
					
					
					
					
				}
			}
			
		}
		catch(SQLException |IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				UtilityJdbc.closeResources(connection, ps, resultSet);
			}
			catch(SQLException se)
			{
				se.printStackTrace();
			}
		}


	}

}

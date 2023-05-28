import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.commons.io.IOUtils;

public class jdbcImageRetrival {

	public static void main(String[] args)
	{
		Connection connection =null;
		PreparedStatement pst = null;
		ResultSet resultSet = null;
		
		Scanner sc = null;
		int empId =0;
		String empName =null;
		String imgLocation = null;
		
		
		
		
		try 
		{
			connection =  UtilityJdbc.getJdbcConnection();
			String sqlInsertQuery = "select * from imageOperations where id = ?";
			
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
				}
				
				pst.setInt(1, empId);
				 
				 
			    resultSet =  pst.executeQuery();
		
			}
			if(resultSet.next())
			{
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				InputStream is = resultSet.getBinaryStream("image");
				
				
				File file = new File("copied2.jpg");
				FileOutputStream fos = new FileOutputStream(file);
				
				IOUtils.copy(is, fos);
				
				System.out.println(id+"\t"+name+"\t"+file.getAbsolutePath());
				
				fos.close();
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
				UtilityJdbc.closeResources(connection, pst,resultSet);
			}
			catch(SQLException se)
			{
				se.printStackTrace();
			}
			
		}


	}

}

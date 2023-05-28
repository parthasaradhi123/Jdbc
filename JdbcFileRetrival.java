import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.Writer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.commons.io.IOUtils;

public class JdbcFileRetrival {

	public static void main(String[] args) 
	{

		Connection connection =null;
		PreparedStatement pst = null;
		ResultSet resultSet = null;
		
		Scanner sc = null;
		int empId =0;
		 
		
		
		
		
		try 
		{
			connection =  UtilityJdbc.getJdbcConnection();
			String sqlInsertQuery = "select * from cities where id = ?";
			
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
				Reader fr = resultSet.getCharacterStream("history");
				
				
				File file = new File("copyhistory.txt");
				FileWriter fw = new FileWriter(file);
				
				IOUtils.copy(fr, fw);
				
				System.out.println(id+"\t"+name+"\t"+file.getAbsolutePath());
				
				fw.close();
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

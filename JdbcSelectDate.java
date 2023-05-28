import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class JdbcSelectDate {

	public static void main(String[] args)
	{
		Connection connection = null;
		PreparedStatement pst = null;
		ResultSet resultSet = null;
		
		
		Scanner sc = null;
		int empId = 0;
		
		try
		{
			connection = UtilityJdbc.getJdbcConnection();
			
			String sqlSelectQuery = "select * from dateOperations where id = ?";
			
			if(connection!=null)
			{
				sc = new Scanner(System.in);
				
				if(sc!=null)
				{
					System.out.println(":: Enter the Id to see your details ::");
					 empId = sc.nextInt();
				}
				
				pst = connection.prepareStatement(sqlSelectQuery);
				
				if(pst!=null)
				{
					pst.setInt(1, empId);
				}
				
				resultSet = pst.executeQuery();
				
				if(resultSet!=null)
				{
					if(resultSet.next())
					{
						int id = resultSet.getInt(1);
						String name = resultSet.getString(2);
						java.sql.Date udate = resultSet.getDate(3);
						java.sql.Date sdate = resultSet.getDate(4);
						
						
						//We can customize the output date based on our country
						
						SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
						String strdate = sdf.format(udate);
						String sstrdate = sdf.format(sdate);
						
						System.out.println(id+"\t"+name+"\t"+strdate+"\t"+sstrdate);
						

					}
				}
				
				
			}
		}
		catch(SQLException |IOException e)
		{
			e.printStackTrace();
		}
		
	}

}

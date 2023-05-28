import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class UtilityJdbc 
{
	
	public static Connection getJdbcConnection()throws SQLException,IOException 
	{
		FileInputStream fis = new FileInputStream("D:\\JavaApplication\\JDBC\\src\\jdbc.properties");
		Properties prop = new Properties();
		prop.load(fis);
		
		Connection connection = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("user"), prop.getProperty("password"));
		
		return connection;
		
	}
	
	public static void closeResources(Connection con,Statement pst,ResultSet rs) throws SQLException
	{
		if(con!=null)
		{
			con.close();
		}
		if(pst!=null)
		{
			pst.close();
		}
		if(rs!=null)
		{
			rs.close();
		}
	}
}

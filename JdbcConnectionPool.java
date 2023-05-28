import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;

public class JdbcConnectionPool {

	public static void main(String[] args) throws Exception
	{
		Connection connection = null;
		Statement st = null;
		ResultSet resultSet = null;
		
		
		System.out.println("Connection started.....");
		MysqlConnectionPoolDataSource datasource = new MysqlConnectionPoolDataSource();
		datasource.setURL("jdbc:mysql:///started");
		datasource.setUser("root");
		datasource.setPassword("Root@143");
		System.out.println("connection established successfully");
		
		
			connection = datasource.getConnection();
			if(connection!=null)
			{
				st = connection.createStatement();
			}
			if(st!=null)
			{
				resultSet = st.executeQuery("select*from bank");
			}
			System.out.println("name \t salary ");
			if(resultSet!=null)
			{
				while(resultSet.next())
				{
					System.out.println(resultSet.getString(1)+"\t"+resultSet.getInt(2));
				}
			}
		 	
			connection.close();

	}

}

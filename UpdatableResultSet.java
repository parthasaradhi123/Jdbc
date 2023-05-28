import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;

public class UpdatableResultSet {

	public static void main(String[] args)
	{

		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		
		//Scanner sc = null;
		
		try 
		{
			connection = UtilityJdbc.getJdbcConnection();
			
			if(connection!=null)
			{
				statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			}
			
			if(statement!=null)
			{
				resultSet = statement.executeQuery("select*from bank");
			}
			
			System.out.println(":: before Insertion ::");
			if(resultSet!=null)
			{
				while(resultSet.next())
				{
					System.out.println(resultSet.getString("name")+"\t"+resultSet.getInt("balance"));
				}
			}
			System.out.println("========================================================================");
			
//			resultSet.moveToInsertRow();
//			resultSet.updateString(1,"Gill");
//			resultSet.updateInt(2, 24000);
//			resultSet.insertRow();
			
		
			
			System.out.println("=================================================================");
			System.out.println(":: After Insertion ::");
			resultSet.beforeFirst();
			
			if(resultSet!=null)
			{
				while(resultSet.next())
				{
					 int OriginalSal = resultSet.getInt(2);
					 if(OriginalSal<15000)
					 {
						 int salInc = OriginalSal+1000;
						 resultSet.updateInt(2,salInc);
						 resultSet.updateRow();
						 
					 }
				}
			}
			
			resultSet.beforeFirst();
			if(resultSet!=null)
			{
				while(resultSet.next())
				{
					System.out.println(resultSet.getString("name")+"\t"+resultSet.getInt("balance"));
				}
			}
			
			
			
			 
		}
		catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
		 
			try {
				UtilityJdbc.closeResources(connection, statement, resultSet);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


	}

}

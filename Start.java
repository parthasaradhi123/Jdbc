import java.sql.*;

public class Start {

	public static void main(String[] args) 
	{
		Connection connection = null;
		Statement statment = null;
		ResultSet resultSet = null;
		
		try
		{
			//step-1 load and register the Driver.
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver Loaded Succesfully");
			
		//	Establish the connection
				String url ="jdbc:mysql://localhost:3306/started";
				String user = "root";
				String password ="Root@143";
			 connection = DriverManager.getConnection(url, user, password);
			 
			 System.out.println("Connection Established Sucessfully");
			 System.out.println("The object name which is refrece of connection is :: "+connection.getClass().getName());
			 
			 //3. create statment Object and send the Query
			 
			 String sqlQuery = "select *from employee";
			 statment = connection.createStatement();
			 System.out.println("The object name which is refrece of statment is :: "+statment.getClass().getName());

			 resultSet = statment.executeQuery(sqlQuery);
			 System.out.println("The object name which is refrece of resultSet is :: "+resultSet.getClass().getName());

			 //Open  the result Set
			 System.out.println("sid \t    sfirstname    \t smiddlename    \t slastname \t sSalary \t Aage");
			 while(resultSet.next())
			 {
				Integer sid =  resultSet.getInt(1);
				String sFirstName = resultSet.getString(2);
				String smiddelName = resultSet.getString(3);
				String sLastName  = resultSet.getString(4);
				Integer sSalary = resultSet.getInt(5);
				Integer sAge = resultSet.getInt(6);
				
				 System.out.println(sid+"\t"+sFirstName+"\t"+smiddelName+"\t"+sLastName+"\t"+sSalary+"\t"+sAge);

			 }
			 
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
			catch(Exception e1)
		{
			e1.printStackTrace();
		}
		finally
		{
			//Closing the Resources.
			
			if(connection!=null)
			{
				try
				{
					connection.close();
				}
				catch(SQLException fe)
				{
					fe.printStackTrace();
				}
			}
		}
	}

}

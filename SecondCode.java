import java.sql.*;
import java.util.Scanner;

public class SecondCode {

	public static void main(String[] args) 
	{
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		
		try
		{
			//step1.Load and register the Driver.
			System.out.println(":: Driver loading started ::");
			//Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println(":: Driver Loading successful :: ");
			
			System.out.println();
			
			//Step.2 Establish the connection to the database using connection Interface.
			System.out.println(":: Trying to Establish the connection with data base:: ");
			
			// url--> "jdbc:mysl://localhost:3306/started"
			//Jdk can easily found that mysql is present in the same machine as java present so localhost no needed
			// and you installed the mysql on default port number so port number not needed.
			
			String url = "jdbc:mysql:///started";
			String user = "root";
			String password = "Root@143";
			connection = DriverManager.getConnection(url, user, password);
			System.out.println(":: COnnection is successful ::");
			
			System.out.println();
			
			//step.3 Create the Statment object and send the Query
			statement=connection.createStatement();
			
			System.out.println();
			
			//Step 4. Execute the Query
			System.out.println(":: Trying to execute the Query and store data in resultSet :: ");
			
			
			Scanner sc = new Scanner(System.in);
			
			
			System.out.println("Enter the id  you wanna insert into the table  :: ");
			int sid = sc.nextInt();
			
			System.out.println("Ente the firstname you wanna insert into the table ::");
			String sfirstname = sc.next();
			
			System.out.println("Ente the firstname you wanna insert into the table ::");
			String smiddlename = sc.next();
			
			System.out.println("Ente the firstname you wanna insert into the table ::");
			String slastname = sc.next();
			
			System.out.println("Enter the age you wanna assign to him :: ");
			int sage = sc.nextInt();
			
			System.out.println("Enter the salary you wanna assign to him :: ");
			int sSalary = sc.nextInt();
			
			
			String sqlStringQuery  = String.format("insert into employee(id,firstname,middlename,lastname,salary,age) values(%d,'%s','%s','%s','%s',%d)",sid,sfirstname,smiddlename,slastname,sSalary,sage);
			
			int m = statement.executeUpdate(sqlStringQuery);
			
			
			
			
			
			
			
			
			
			//String sqlDeleteQuery = "delete from employee where id = 2";
			//String sqlInsertQuery = "insert into employee values(3,'jhanvi','bhargavi','reddy',21,45000)";
		//	String sqlUpdateQuery = "update employee set firstname = 'jhanvi' where id = 3";
			 
			
		//	int x = statement.executeUpdate(sqlDeleteQuery);
		//	int y = statement.executeUpdate(sqlInsertQuery);
		//	int z = statement.executeUpdate(sqlUpdateQuery);
			
//			String sql = "select id,firstname from employee";
//			 resultSet = statement.executeQuery(sql);

			System.out.println(":: Query Execution is successful :: ");
			
			System.out.println();
			
			
			
			
			
			//Printing the data present in the table
	//		System.out.println("The number of rows effected or deleted is :: "+x);
	//		System.out.println("The numbe of rows inserted :: "+y);
//			System.out.println("The numbe of rows updated :: "+z);
			
			System.out.println(" The number of rows got affected :: "+m);
			
//			while(resultSet.next())
//			{
//				Integer id = resultSet.getInt("id");
//				String firstname = resultSet.getString("firstname");
//				Integer age = resultSet.getInt("age");
//				
//				System.out.println(id+"\t"+firstname+"\t\t"+age);
//				
//				
//			}
			
		}
		//step 5. handle the exceptions 
//		catch(ClassNotFoundException ce)
//		{
//			ce.printStackTrace();
//		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		//step6. close the resources.
		finally
		{
			try
			{
				//resultSet.close();
				statement.close();
				connection.close();
				
			}
			catch(SQLException se1)
			{
				se1.printStackTrace();
			}
		}

	}

}

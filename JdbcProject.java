import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class JdbcProject
{
	//let me try my level best to develop a console based appliction using jdbc.

	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		//Create a  menu for the user to perfom crud operation.
		
		//Here the user should see 5 options 
		
		System.out.println(":*******: Hello, Lovely Customer Please Type the one of the option from the below Menu :******:");
		System.out.println();
		System.out.println("Type 1 :- For the Insert Operations... ");
		System.out.println("Type 2 :- For the Select Operations...");
		System.out.println("Type 3 :- For the Update Operations...");
		System.out.println("Type 4 :- For the Delete Operations...");
		System.out.println("Type 5 :- To close the Application...");
		System.out.println();
		
		System.out.println(":: Please Enter your selected value here ::");
		int userInput = sc.nextInt();
		
		if(userInput==5)
		{
			System.out.println(":: Application is Closed ::");
			System.exit(0);
		}
		
		if(userInput<1 || userInput>5)
		{
			System.out.println(" :: Invalid Operation ::");
			System.exit(0);
		}
		
		System.out.println(":: We have recevied your input :: ");
		
		Connection connection = null;
		PreparedStatement pst = null;
		ResultSet resultSet = null;
		
		
		try
		{
			
			connection = UtilityJdbc.getJdbcConnection();
			
			if(connection!=null)
			{
				if(userInput ==1)
				{
					
					//Now we have to take data from the user
					System.out.println(":: Now you have to enter the following data ::");
					System.out.println();
					
					System.out.println("Please enter the employee Id :: ");
					int empid = sc.nextInt();
				
					System.out.println("Please enter the employee name :: ");
					String empName = sc.next();
					
					System.out.println("Please enter the employee age :: ");
					int empAge = sc.nextInt();
					
					System.out.println("Please enter the employee salary :: ");
					int empSalary = sc.nextInt();
					
					String sqlInsertQuery = "insert into employee(id,firstname,age,salary) values(?,?,?,?)";
					
					pst = connection.prepareStatement(sqlInsertQuery);
					
					if(pst!=null)
					{
						pst.setInt(1, empid);
						pst.setString(2, empName);
						pst.setInt(3,empAge);
						pst.setInt(4,empSalary);
						
						int rowsEffected = pst.executeUpdate();
						
						System.out.println();
						
						System.out.println(":: Congratulaions, You have successfully Inserted the data into the database :: ");
					}
					
					
				}
				
				if(userInput == 2)
				{
					System.out.println();
					System.out.println(":: Please Enter your Employee Id to see your data ::");
					int empId = sc.nextInt();
					
					String sqlSelectQuery = "select*from employee where id = ? ";
					
					pst = connection.prepareStatement(sqlSelectQuery);
					if(pst!=null)
					{
						pst.setInt(1, empId);
					}
					
					resultSet = pst.executeQuery();
					System.out.println();
					System.out.println("id\tfirstname\tage\tsalary");
					if(resultSet!=null)
					{
						if(resultSet.next())
						{
							System.out.println(resultSet.getInt("id")+"\t"+resultSet.getString("firstname")
									           +"\t"+resultSet.getInt("age")+"\t"+resultSet.getInt("salary"));
						}
						
					
					}
					
				}
				
				if(userInput == 3)
				{
					System.out.println(" :: Please enter your employee Id to see your data ::");
					int empId = sc.nextInt();
					
					String sqlSearchQuery = "select*from employee where id =?";
					pst = connection.prepareStatement(sqlSearchQuery);
					pst.setInt(1, empId);
					
					resultSet = pst.executeQuery();
					
					System.out.println();
					System.out.println(":: Your present data :: ");
					System.out.println();
					System.out.println("id\tfirstname\tsalary\tage");
					
					if(resultSet.next())
					{
						System.out.println(resultSet.getInt("id")+"\t"+resultSet.getString("firstname")
				           +"\t"+"\t"+resultSet.getInt("salary")+resultSet.getInt("age"));
					}
					System.out.println();
					System.out.println(":: Type the column name u wanna update ::");
					String PresentColName = sc.next();
					
					if(PresentColName.equalsIgnoreCase("id"))
					{
						
						System.out.println("The present data present in your ID :: "+resultSet.getInt("id"));
						System.out.println(":: Enter the new Id ::");
						int newId = sc.nextInt();
						
						String sqlUpdateQuery = "update employee set id = ? where id="+empId;
						
						pst= connection.prepareStatement(sqlUpdateQuery);
						pst.setInt(1, newId);
						
						pst.executeUpdate();
						System.out.println(":: Record Updation is successful ::");
					}
					
					if(PresentColName.equalsIgnoreCase("firstname"))
					{
						
						System.out.println("The present data present in your firstname :: "+resultSet.getString("firstname"));
						System.out.println(":: Enter the new value you::");
						String newName = sc.next();
						
						String sqlUpdateQuery = "update employee set firstname = ? where id="+empId;
						
						pst= connection.prepareStatement(sqlUpdateQuery);
						pst.setString(1, newName);
						
						pst.executeUpdate();
						System.out.println(":: Record Updation is successful ::");
					}
					if(PresentColName.equalsIgnoreCase("age"))
					{
						
						System.out.println("The present data present in your ID :: "+resultSet.getInt("age"));
						System.out.println(":: Enter the new age ::");
						int newAge = sc.nextInt();
						
						String sqlUpdateQuery = "update employee set age = ? where id="+empId;
						
						pst= connection.prepareStatement(sqlUpdateQuery);
						pst.setInt(1, newAge);
						
						pst.executeUpdate();
						System.out.println(":: Record Updation is successful ::");
					}
					if(PresentColName.equalsIgnoreCase("salary"))
					{
						
						System.out.println("The present data present in your ID :: "+resultSet.getInt("salary"));
						System.out.println(":: Enter the salary you wanna request  ::");
						int newSalary = sc.nextInt();
						
						String sqlUpdateQuery = "update employee set salary = ? where id="+empId;
						
						pst= connection.prepareStatement(sqlUpdateQuery);
						pst.setInt(1, newSalary);
						
						pst.executeUpdate();
						System.out.println(":: Record Updation is successful ::");
					}
					
					
				}
				
				if(userInput == 4)
				{
					System.out.println(":: Enter the employee id you wanna delete :: ");
					int empId = sc.nextInt();
					
					String sqlDeleteQuery = "delete from employee where id =?";
					pst = connection.prepareStatement(sqlDeleteQuery);
					
					pst.setInt(1, empId);
					
					int x = pst.executeUpdate();
					
					System.out.println(x+":: Row got successfully deleted ::");
					
				}
				
			}
			
			
			
		}
		catch(IOException ie)
		{
			ie.printStackTrace();
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally 
		{
			sc.close();
			System.out.println(" :: Closing the resources :: ");
			try
			{
				UtilityJdbc.closeResources(connection, pst, resultSet);
			}
			catch(SQLException se)
			{
				se.printStackTrace();
			}
		}
		 
	}

}

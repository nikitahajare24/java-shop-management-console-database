package usermanagement;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import db_operation.DBUtils;


public class UserManagement 
{

	public static void userManagement()throws IOException
	{

		Scanner scanner = new Scanner(System.in); 

		boolean canIKeepRunningTheProgram = true; 
		while (canIKeepRunningTheProgram == true) 
		{ 
			System.out.println("**** Welcome to User Management *****");
			System.out.println("\n");
			System.out.println("What would you like to do ?");
			System.out.println("1. Add User");
			System.out.println("2. Search User");
			System.out.println("3. Edit User");
			System.out.println("4. Delete User");
			System.out.println("5. Quit");

			int optionSelectedByUser = scanner.nextInt();

			if (optionSelectedByUser == UserOptions.QUIT)
			{
				System.out.println("!!! Program closed");
				canIKeepRunningTheProgram = false;

			}
			else if (optionSelectedByUser == UserOptions.ADD_USER) 
			{
				addUser();
				System.out.println("\n");
			} 
			else if (optionSelectedByUser == UserOptions.SEARCH_USER)
			{
				System.out.print("Enter User Name to search: ");
				scanner.nextLine(); 
				String sn = scanner.nextLine();
				searchUser(sn);
				System.out.println("\n");
			} 
			else if (optionSelectedByUser == UserOptions.DELETE_USER) 
			{
				System.out.print("Enter User Name to delete: ");
				scanner.nextLine(); 
				String deleteUserName = scanner.nextLine();
				deleteUser(deleteUserName);
				System.out.println("\n");
			} 
			else if (optionSelectedByUser == UserOptions.EDIT_USER)
			{
				System.out.print("Enter User Name to edit: ");
				scanner.nextLine(); 
				String editUserName = scanner.nextLine();
				editUser(editUserName);
				System.out.println("\n");
			}

		}
		System.out.println("\n");
	}


	public static void addUser()
	{
		Scanner scanner = new Scanner(System.in);

		User userObject = new User(); 

		System.out.print("User Name: ");
		userObject.userName = scanner.nextLine();

		System.out.print("Login Name: ");
		userObject.login_name = scanner.nextLine();

		System.out.print("Password: ");
		userObject.pasword = scanner.nextLine();

		System.out.print("confirm Password: ");
		userObject.confirmPassword = scanner.nextLine();

		System.out.print("User Role: ");
		userObject.userRole = scanner.nextLine();

		System.out.println("User Name: " + userObject.userName);
		System.out.println("Login Name: " + userObject.login_name);
		System.out.println("Password: " + userObject.pasword);
		System.out.println(" confirm Password: " + userObject.confirmPassword);
		System.out.println("User Role: " + userObject.userRole);

		String query = "insert into user(UserName, LoginName, pasword, confirmPassword, userRole) values ('"+ userObject.userName + "', '" + userObject.login_name + "', '" + userObject.pasword + "','"+ userObject.confirmPassword + "','" + userObject.userRole + "')";

		DBUtils.excuteQuery(query);

	}

	public static void searchUser(String userName) 
	{

		String query = "select * from User where UserName='" + userName + "' ";

		ResultSet rs = DBUtils.executeQueryGetResult(query);

		try {
			while (rs.next())
			
			{ 
				if (rs.getString("username").equalsIgnoreCase(userName))
				{
					System.out.println("User Name: " + rs.getString("username"));
					System.out.println("Login Name: " + rs.getString("login_name"));
					System.out.println("Password: " + rs.getString("pasword"));
					System.out.println("User Role: " + rs.getString("userRole"));
				
					return;
				}
			}
		}
		catch (Exception e)
		{
			System.out.println("User not found.");
		}

	}


	public static void deleteUser(String userName) 
	{
		String query = "delete from user where UserName='" + userName + "' ";
		DBUtils.excuteQuery(query);
	}


	public static void editUser(String userName)
	{
		
		String query = "select * from user where UserName='" + userName + "' ";
		ResultSet rs =DBUtils.executeQueryGetResult(query);
		
		try {
			while (rs.next())
			{ 
				if (rs.getString("User_Name").equalsIgnoreCase(userName)) 
				{
					Scanner scanner = new Scanner(System.in);
					User user = new User();
					
					System.out.println("Editing user: " + user.userName);

					System.out.print("New User Name: ");
					user.userName = scanner.nextLine();

					System.out.print("New Login Name: ");
					user.login_name = scanner.nextLine();

					System.out.print("New Password: ");
					user.pasword = scanner.nextLine();

					System.out.print("New confirmPassword: ");
					user.confirmPassword = scanner.nextLine();

					System.out.print("New User Role: ");
					user.userRole = scanner.nextLine();
					
					String updateQuery = "update users set "+ "UserName='"+user.userName+"', LoginName = '"+user.login_name+"', "+ "Pasword='"+user.pasword+"', confirmPassword='"+user.confirmPassword+"', "+ "Role='"+user.userRole+"' where userid='"+rs.getString("userid")+"'";
					
					DBUtils.excuteQuery(updateQuery);

					System.out.println("User information updated.");

					return;
				}
			}
		} 
		catch (Exception e) 
		{
			System.out.println("User not found.");
		}

		
	}

	public static boolean validateUserAndPassword(String login_name, String pasword) throws IOException 
	{

		String query = "select * from user where login_name='" + login_name + "' and pasword='" + pasword + "' ";

		ResultSet rs = DBUtils.executeQueryGetResult(query);

		try {
			if (rs.next()) 
			{
			
				return true;
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}

		return false;
	}

}
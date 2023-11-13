package shopmanagement;

import java.io.IOException;
import java.util.Scanner;

import productmanagement.ProductManagement;
import usermanagement.UserManagement;



public class ApplicationMain {

	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(System.in);

		boolean canIKeepRunningTheProgram = true;

		
		
		
		System.out.println("**** Welcome to Shop Management *****");
		
		System.out.println("\n");
		System.out.println("Enter login name : ");
		String login_name = scanner.nextLine();
		System.out.println("Enter password : ");
		String pasword = scanner.nextLine();

		if(!UserManagement.validateUserAndPassword(login_name,pasword))
		{
			System.out.println("!!!!!!!! Login failed. Closing the application");
			return;
		}
		else
		{
			System.out.println("Login is Successful");
		}

		while (canIKeepRunningTheProgram == true) {

			System.out.println("**** Welcome to Shop Management *****");
			System.out.println("\n");
			
			System.out.println("What would you like to do ?");
			System.out.println("1. User Management");
			System.out.println("2. Product Management");
			System.out.println("5. Quit");

			int optionSelectedByUser = scanner.nextInt();

			if (optionSelectedByUser == 1) {				
				UserManagement.userManagement();
				
			} else if (optionSelectedByUser == 2) {
				ProductManagement.productManagement();

			} else if	(optionSelectedByUser == 3) {
				break;
			}

		}
	}
}
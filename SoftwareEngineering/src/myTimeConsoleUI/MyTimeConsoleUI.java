package myTimeConsoleUI;

import java.util.Scanner;

public class MyTimeConsoleUI
{
	static Scanner in = new Scanner(System.in);
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		int choice = 0;
		
		
		System.out.printf("\nPlease Choose An Option: (zero(0) to quit)\n");
		System.out.printf("   1. Start Time\n");
		System.out.printf("   2. Stop Time\n");
		System.out.printf("   3. Pause Time\n");
		System.out.printf("   4. Reset Time\n");
		System.out.printf("   5. Client Menu\n");
		System.out.printf("   6. Project Menu\n");
		System.out.printf("   7. Time Tracking\n");
		System.out.printf("   8. Settings\n");
		
		choice = in.nextInt();
		
		while(choice != 0)
		{
			switch(choice)
			{
				case 1:
					System.out.printf("Not Functional Yet\n");
					break;
				case 2:
					System.out.printf("Not Functional Yet\n");
					break;
				case 3:
					System.out.printf("Not Functional Yet\n");
					break;
				case 4:
					System.out.printf("Not Functional Yet\n");
					break;
				case 5:
					clientMenu();
					break;
				case 6:
					projectMenu();
					break;
				case 7:
					timeTrackingMenu();
					break;
				case 8:
					settingsMenu();
					break;
			}
		}
		
		System.out.printf("\nGoodbye!\n");
	}
	
	public static void clientMenu()
	{
		int m_clientMenuChoice = 0;
		String m_clientName;
		String m_clientDescription;
		
		System.out.printf("\nPlease Choose An Option: (zero(0) to quit)\n");
		System.out.printf("   1. Add Client\n");
		System.out.printf("   2. Get Client Info\n");
		System.out.printf("   3. Edit Client Info\n");
		System.out.printf("   4. Display All Clients\n");
		System.out.printf("   5. Back to Main Menu\n");
		
		m_clientMenuChoice = in.nextInt();
		
		while(m_clientMenuChoice != 0)
		{
			switch(m_clientMenuChoice)
			{
				case 1:
					System.out.printf("Please input Client Name.\n");
					m_clientName = in.next();
					System.out.printf("Please input Client Description.\n");
					m_clientDescription = in.next();
					//CreateClientMethod(m_clientName, m_clientDescription);
					break;
				case 2:
					System.out.printf("Please input which Client name you would like information for.\n");
					m_clientName = in.next();
					System.out.printf("Info for %s", m_clientName);
					break;
				case 3:
					System.out.printf("Please input which Client name you would like to edit.\n");
					m_clientName = in.next();
					//FindClientMethod(m_clientName);
					System.out.printf("Please input new Client name.\n");
					m_clientName = in.next();
					//client.setName(m_clientName);
					System.out.printf("Please input new Client Description\n");
					m_clientDescription = in.next();
					//client.setDescription(m_clientDescription);
					break;
				case 4:
					//prints list of clients from the manager
					break;
				case 5:
					m_clientMenuChoice = 0;
					break;
			}
		}
	}
	
	
	public static void projectMenu()
	{
		
	}
	
	public static void timeTrackingMenu()
	{
	}
	
	public static void settingsMenu()
	{
		
	}
}

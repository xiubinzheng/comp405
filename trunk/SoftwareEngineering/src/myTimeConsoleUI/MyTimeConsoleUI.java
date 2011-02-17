package myTimeConsoleUI;

import java.util.ArrayList;
import java.util.Scanner;

import project.Project;

import client.Client;

import exceptions.MyTimeException;

import Controls.Manager;

public class MyTimeConsoleUI
{
	static Scanner g_in = new Scanner(System.in);
	static Manager g_uiManager = new Manager();
	static ArrayList<Client> g_clientList = new ArrayList<Client>();
	static ArrayList<Project> g_projectList = new ArrayList<Project>();
	
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
		
		choice = g_in.nextInt();
		
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
			
			System.out.printf("\nPlease Choose An Option: (zero(0) to quit)\n");
			System.out.printf("   1. Start Time\n");
			System.out.printf("   2. Stop Time\n");
			System.out.printf("   3. Pause Time\n");
			System.out.printf("   4. Reset Time\n");
			System.out.printf("   5. Client Menu\n");
			System.out.printf("   6. Project Menu\n");
			System.out.printf("   7. Time Tracking\n");
			System.out.printf("   8. Settings\n");
			
			choice = g_in.nextInt();
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
		
		m_clientMenuChoice = g_in.nextInt();
		
		while(m_clientMenuChoice != 0)
		{
			switch(m_clientMenuChoice)
			{
				case 1: //creates a new client
					System.out.printf("Please input Client Name.\n");
					m_clientName = g_in.next();
					System.out.printf("Please input Client Description.\n");
					m_clientDescription = g_in.next();
					try
					{
						g_uiManager.addClient(new Client( -1, m_clientName, m_clientDescription));
					}
					catch (MyTimeException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					assert(g_uiManager.getClientByName(m_clientName).getClientID() != -1);
					break;
				case 2: //returns information on client name given by user.
					System.out.printf("Please input which Client name you would like information for.\n");
					m_clientName = g_in.next();
					System.out.printf("Info for %s", g_uiManager.getClientByName(m_clientName).toString());
					break;
				case 3: //Takes client name and edits both name and description.
					System.out.printf("Please input which Client name you would like to edit.\n");
					m_clientName = g_in.next();
					/*try
					{
						g_uiManager.getClientByName(m_clientName);
					}catch(MyTimeException e)
					{
						System.out.printf("Customer does not exist please retype name and try again.\n");
						m_clientName = g_in.next();
						try
						{
							g_uiManager.getClientByName(m_clientName);
						}catch(MyTimeException e)
						{
							System.out.printf("Invalid client name returning to Client Menu.\n");
							break;
						}
					}*/
					System.out.printf("Please input new name for:", m_clientName);
					String m_clientNameNew = g_in.next();
					g_uiManager.getClientByName(m_clientName).setClientName(m_clientNameNew);
					System.out.printf("Please input new description for:", m_clientNameNew);
					m_clientDescription = g_in.next();
					g_uiManager.getClientByName(m_clientNameNew).setClientDescription(m_clientNameNew);
					break;
				case 4:
					g_clientList.clear();
					g_uiManager.getClients(g_clientList);
					for(Client c : g_clientList)
						System.out.printf("%s\n", c.toString());
					break;
				case 5:
					return;
			}
			
			System.out.printf("\nPlease Choose An Option: (zero(0) to quit)\n");
			System.out.printf("   1. Add Client\n");
			System.out.printf("   2. Get Client Info\n");
			System.out.printf("   3. Edit Client Info\n");
			System.out.printf("   4. Display All Clients\n");
			System.out.printf("   5. Back to Main Menu\n");
			
			m_clientMenuChoice = g_in.nextInt();
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

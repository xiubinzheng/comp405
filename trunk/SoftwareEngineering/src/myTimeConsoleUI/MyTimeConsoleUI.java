package myTimeConsoleUI;

import java.util.ArrayList;
import java.util.Scanner;

import project.Project;

import client.Client;

import exceptions.MyTimeException;

import Controls.Manager;

public class MyTimeConsoleUI
{
	static Scanner g_inString = new Scanner(System.in);
	static Scanner g_inInt = new Scanner(System.in);
	static Manager g_uiManager = null;
	static ArrayList<Client> g_clientList = new ArrayList<Client>();
	static ArrayList<Project> g_projectList = new ArrayList<Project>();
	
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		g_uiManager = new Manager();
		
		int m_mainMenuChoice = 0;
		
		
		while(true)
		{
			System.out.printf("\nMAIN MENU\n");
			System.out.printf("Please Choose An Option: (zero(0) to quit)\n");
			System.out.printf("   1. Start Time\n");
			System.out.printf("   2. Stop Time\n");
			System.out.printf("   3. Pause Time\n");
			System.out.printf("   4. Reset Time\n");
			System.out.printf("   5. Client Menu\n");
			System.out.printf("   6. Project Menu\n");
			System.out.printf("   7. Time Tracking\n");
			System.out.printf("   8. Settings\n");
			
			m_mainMenuChoice = g_inInt.nextInt();
			
			switch(m_mainMenuChoice)
			{
				case 0:
					System.out.printf("\nGoodbye!\n");
					System.exit(0);
				
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
				default:
					System.out.printf("\nInvalid choice please enter a new option.\n");
					break;
			}
		}
	}
	
	public static void clientMenu()
	{
		int m_clientMenuChoice = 0;
		String m_clientName;
		String m_clientDescription;
		
		while(true)
		{			
			System.out.printf("\nCLIENT MENU\n");
			System.out.printf("Please Choose An Option: (zero(0) to quit)\n");
			System.out.printf("   1. Add Client\n");
			System.out.printf("   2. Get Client Info\n");
			System.out.printf("   3. Edit Client Info\n");
			System.out.printf("   4. Display All Clients\n");
			System.out.printf("   5. Back to Main Menu\n");
		
			m_clientMenuChoice = g_inInt.nextInt();
			
			switch(m_clientMenuChoice)
			{
				case 0:
					System.out.printf("\nGoodbye!\n");
					System.exit(0);
				
				case 1: //creates a new client
					System.out.printf("Please input Client Name.\n");
					m_clientName = g_inString.next();
					
					System.out.printf("Please input Client Description.\n");
					m_clientDescription = g_inString.next();
					
					try
					{
						g_uiManager.addClient(new Client( -1, m_clientName, m_clientDescription));
					}catch (MyTimeException e)
					{
						e.printStackTrace();
					}
					
					try
					{
						assert(g_uiManager.getClientByName(m_clientName).getClientID() != -1);
					}
					catch (MyTimeException e3)
					{
						e3.printStackTrace();
					}
					break;
					
				case 2: //returns information on client name given by user.
					System.out.printf("Please input which Client name you would like information for.\n");
					m_clientName = g_inString.next();
					
					try
					{
						System.out.printf("\nInfo for Client:%s\n", g_uiManager.getClientByName(m_clientName).toString());
					}
					catch (MyTimeException e)
					{
						e.printStackTrace();
					}
					break;
					
				case 3: //Takes client name and edits both name and description.
					System.out.printf("Please input which Client name you would like to edit.\n");
					m_clientName = g_inString.next();
					
					try
					{
						g_uiManager.getClientByName(m_clientName);
					}catch(MyTimeException e)
					{
						System.out.printf("Invalid client name returning to Project Menu.\n");
						e.printStackTrace();
						break;
					}
					
					System.out.printf("Please input new name for:", m_clientName);
					String m_clientNameNew = g_inString.next();
					
					try
					{
						g_uiManager.getClientByName(m_clientName).setClientName(m_clientNameNew);
					}
					catch (MyTimeException e1)
					{
						e1.printStackTrace();
					}
					System.out.printf("Please input new description for:\n", m_clientNameNew);
					m_clientDescription = g_inString.next();
					
					try
					{
						g_uiManager.getClientByName(m_clientNameNew).setClientDescription(m_clientNameNew);
					}
					catch (MyTimeException e)
					{
						e.printStackTrace();
					}
					break;
					
				case 4:
					g_clientList.clear();
					g_uiManager.getClients(g_clientList);
					
					for(Client c : g_clientList)
						System.out.printf("%s\n", c.toString());
					break;
					
				case 5:
					return;
				default:
					System.out.printf("\nInvalid choice please enter a new option.\n");
					break;	
			}
		}
	}
	
	
	public static void projectMenu()
	{
		int m_projectMenuChoice = 0;
		String m_clientName;
		String m_projectName;
		String m_projectDescription;
		boolean m_projectStatus = true;
		boolean m_found = false;
		
		while(true)
		{	
			System.out.printf("\nPROJECT MENU\n");
			System.out.printf("Please Choose An Option: (zero(0) to quit)\n");
			System.out.printf("   1. Add Project\n");
			System.out.printf("   2. Get Project Info\n");
			System.out.printf("   3. Edit Project Info\n");
			System.out.printf("   4. Display All Projects for Client\n");
			System.out.printf("   5. Mark Project Done.\n");
			System.out.printf("   6. Get All Active Projects\n");
			System.out.printf("   7. Back to Main Menu\n");
			
			m_projectMenuChoice = g_inInt.nextInt();
			
			switch(m_projectMenuChoice)
			{
				case 0:
					System.out.printf("\nGoodbye!\n");
					System.exit(0);
					
				case 1:
					System.out.printf("\nInput client name for Project you would like to add.\n");
					m_clientName = g_inString.next();
					
					try
					{
						g_uiManager.getClientByName(m_clientName);
					}catch(MyTimeException e)
					{
						System.out.printf("Invalid client name returning to Project Menu.\n");
						e.printStackTrace();
						break;
					}
					
					System.out.printf("\nPlease input project name for %s: \n", m_clientName);
					m_projectName = g_inString.next();
					
					System.out.printf("\nPlease input project description: \n");
					m_projectDescription = g_inString.next();
					
					System.out.printf("\nIs this project hourly? (y or n)\n");
					char m_in = g_inString.next().charAt(0);
					boolean m_input = true;
					while(m_input)
					{
						switch(m_in)
						{
							case 'y':
							case 'Y':
								m_projectStatus = true;
								m_input = false;
								break;
							
							case 'n':
							case 'N':
								m_projectStatus = false;
								m_input = false;
								break;
								
							default:
								System.out.printf("\nInvalid input\nIs this project hourly? (y or n)");
								m_in = g_inString.next().charAt(0);
								break;
								
						}
					}

					try
					{
						g_uiManager.addProject(new Project(-1, m_projectName, m_projectDescription, 0, 
												g_uiManager.getClientByName(m_clientName).getClientID(), m_projectStatus));
					}
					catch (MyTimeException e)
					{
						e.printStackTrace();
					}
					
					
					break;
					
				case 2: 
					System.out.printf("\nInput client name for Project you would like info for: \n");
					m_clientName = g_inString.next();
					
					try
					{
						g_uiManager.getClientByName(m_clientName);
					}catch(MyTimeException e)
					{
						System.out.printf("Invalid client name returning to Project Menu.\n");
						e.printStackTrace();
						break;
					}
					
					System.out.printf("\nPlease input project name for %s: ", m_clientName);
					m_projectName = g_inString.next();
					
					try
					{
						g_uiManager.getClientByName(m_clientName).getProjectList(g_projectList);
					}
					catch (MyTimeException e)
					{
						e.printStackTrace();
					}
					
					m_found = false;
					
					for(Project p : g_projectList)
					{
						if(p.getName().equals(m_projectName))
						{
							m_found = true;
							System.out.printf("%s", p.toString());
							break;
						}
					}
					
					if(!m_found)
						System.out.printf("Project not found");
					break;
					
				case 3: //edit project info
					System.out.printf("\nNot Funtional Yet.");
					break;
					
				case 4:
					System.out.printf("\nInput client name for Project you would like info for: \n");
					m_clientName = g_inString.next();
					
					try
					{
						g_uiManager.getClientByName(m_clientName);
					}catch(MyTimeException e)
					{
						System.out.printf("Invalid client name returning to Project Menu.\n");
						e.printStackTrace();
						break;
					}
					
					System.out.printf("\nPlease input project name for %s: \n", m_clientName);
					m_projectName = g_inString.next();
					
					try
					{
						g_uiManager.getClientByName(m_clientName).getProjectList(g_projectList);
					}
					catch (MyTimeException e)
					{
						e.printStackTrace();
					}
					
					System.out.printf("\n");
					
					for(Project p : g_projectList)
					{
						System.out.printf("%s\n", p.toString());
					}			
					break;
					
				case 5:
					System.out.printf("\nInput client name for Project you would like info for: \n");
					m_clientName = g_inString.next();
					
					try
					{
						g_uiManager.getClientByName(m_clientName);
					}catch(MyTimeException e)
					{
						System.out.printf("Invalid client name returning to Project Menu.\n");
						e.printStackTrace();
						break;
					}
					
					System.out.printf("\nPlease input project name for %s: ", m_clientName);
					m_projectName = g_inString.next();
					
					try
					{
						g_uiManager.getClientByName(m_clientName).getProjectList(g_projectList);
					}
					catch (MyTimeException e)
					{
						e.printStackTrace();
					}
					
					m_found = false;
					
					for(Project p : g_projectList)
					{
						if(p.getName().equals(m_projectName))
						{
							m_found = true;
							p.complete();
							break;
						}
					}
					
					if(!m_found)
						System.out.printf("Project not found");
					break;
				case 6:
					System.out.printf("\nInput client name for Project you would like info for: \n");
					m_clientName = g_inString.next();
					
					try
					{
						g_uiManager.getClientByName(m_clientName);
					}catch(MyTimeException e)
					{
						System.out.printf("Invalid client name returning to Project Menu.\n");
						e.printStackTrace();
						break;
					}
					
					System.out.printf("\nPlease input project name for %s: ", m_clientName);
					m_projectName = g_inString.next();
					
					try
					{
						g_uiManager.getClientByName(m_clientName).getProjectList(g_projectList);
					}
					catch (MyTimeException e)
					{
						e.printStackTrace();
					}
					
					
					for(Project p : g_projectList)
					{
						if(!p.isComplete())
						{
							System.out.printf("%s", p.toString());
						}
					}

					break;
				case 7:
					return;
				default:
					System.out.printf("\nInvalid choice please enter a new option.\n");
					break;
			}
		}
		
	}
	
	public static void timeTrackingMenu()
	{
		System.out.printf("Not Functional Yet.");
		return;
	}
	
	public static void settingsMenu()
	{
		System.out.printf("Not Functional Yet.");
		return;
	}
}

package tests;


import java.util.ArrayList;

import utilities.*;

import models.*;


import junit.framework.TestCase;

public class ManagerTest extends TestCase
{
	ClientDBManager				testManager;
	ArrayList<Client>	clients = new ArrayList<Client>();
	ArrayList<Project>	projects = new ArrayList<Project>();
	Client client = new Client();
	Project project = new Project();

	final int m_clientListSize = 5;
	
	final String m_client0Name                    = "Ron";
	final String m_client0Desc                    = "Janitor - Not Engineer!";
	final int    m_client0ProjectSize             = 2;
	final String m_client0ProjectName             = "Dell";
	final String m_client0TestProjectName         = "Ron's test project";
	final String m_client0TestProjectDesc         = "Press button, receive bacon.";
	final int    m_client0ProjectTimeIntervalSize = 4;
	
	final String m_client1Name            = "Liane";
	final String m_client1Desc            = "Real Software Engineer - Unlike Ron";
	final int    m_client1ProjectSize     = 0; // unused
	final String m_client1TestProjectName = "Liane's test project";
	final String m_client1TestProjectDesc = ""; // unused
	
	final String m_client2Name            = "Cohen";
	final String m_client2Desc            = ""; // unused
	final int    m_client2ProjectSize     = 0; // unused

	protected void setUp() throws Exception
	{
		testManager = ClientDBManager.getInstance();
	}

	protected void tearDown() throws Exception
	{

	}

	public void testClient()
	{
		boolean caughtException = false;

		try
		{
			testManager.initializeDB();
		}
		catch (MyTimeException e)
		{
			e.printStackTrace();
			caughtException = true;
		}
		assertFalse(caughtException);

		// test the manager to see if we can get the list of clients
		clients = new ArrayList<Client>();
		
		testManager.getClients(clients);
		if (clients.size() != m_clientListSize)
		{
			System.err.println("ERROR: Client size should be: "+ m_clientListSize +"\nIt is actually:"+clients.size());
		}
		assertTrue(clients.size() == m_clientListSize);

		try
		{
			client = testManager.getClientByName(m_client0Name);
			client.setClientDescription(m_client0Desc);
			testManager.updateClient(client);

			Client b = testManager.getClientByName("Cohen");
//			b.getProjectList(projects);
//			//assertTrue(projects.size() == 4);
			
			Client c = new Client(-1, m_client1Name, m_client1Desc);
			testManager.updateClient(c);
			
			b = testManager.getClientByName(m_client1Name);
			if (!b.getClientDescription().equals(m_client1Desc))
				System.err.println("ERROR: Client description should be:"+m_client1Desc+
						"\nit is actually:"+b.getClientDescription());
			assertTrue(b.getClientDescription().equals(m_client1Desc));
		}
		catch (MyTimeException e)
		{
			e.printStackTrace();
			caughtException = true;
		}
		assertFalse(caughtException);
	}

	public void testProject()
	{
		boolean caughtException = false;
		
		try
		{
			testManager.initializeDB();
			Client ron = testManager.getClientByName(m_client0Name);
			ron.getProjectList(projects);
			if (projects.size() != m_client0ProjectSize)
			{
				System.err.println("ERROR: number of projects for "+m_client0Name+" should be:"+m_client0ProjectSize+
						"\nit is actually:"+projects.size());
			}
			assertTrue(projects.size() == m_client0ProjectSize);
		}
		catch (MyTimeException e)
		{
			e.printStackTrace();
			caughtException = true;
		}
		assertFalse(caughtException);
		
		try
		{
			Client l = testManager.getClientByName(m_client1Name);
			Project p = new Project(m_client1TestProjectName, m_client1TestProjectDesc, l.getClientID(),
					true);
			l.addProject(p);
			testManager.updateClient(l);
		}
		catch(MyTimeException e)
		{
			e.printStackTrace();
			caughtException = true;
		}
		assertFalse(caughtException);
	}
	
	public void testTimeInterval()
	{
		boolean caughtException = false;

		try
		{
			testManager.initializeDB();
		}
		catch (MyTimeException e)
		{
			e.printStackTrace();
			caughtException = true;
		}
		assertFalse(caughtException);

		try
		{
			project = testManager.getProject(m_client0Name, m_client0ProjectName);
		}

		catch (MyTimeException e)
		{
			caughtException = true;
			e.printStackTrace();
			System.err.println("ERROR: Cannot retrieve project:"+m_client0ProjectName+" for client:"+m_client0Name);
		}
		assertFalse(caughtException);

		try
		{
			ArrayList<TimeInterval> T = testManager.getTimeIntervals(project
					.getName());
			if (T.size() != m_client0ProjectTimeIntervalSize)
			{
				System.err.println("ERROR: Time interval size should be:"+m_client0ProjectTimeIntervalSize+
						"\nit is actually:"+T.size());
			}
			assertTrue(T.size() == m_client0ProjectTimeIntervalSize);
		}

		catch (MyTimeException e)
		{
			caughtException = true;
			e.printStackTrace();
			System.err.println("ERROR: Can't retrive time intervals.");
		}
		assertFalse(caughtException);
	}
}
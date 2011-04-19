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
		//assertTrue(clients.size() == 4);

		try
		{
			client = testManager.getClientByName("Ron");
			client.setClientDescription("Janitor - Not Engineer !");
			testManager.updateClient(client);

			Client b = testManager.getClientByName("Cohen");
			b.getProjectList(projects);
			assertTrue(projects.size() == 4);
			
			Client c = new Client(-1, "Liane", "Real Software Engineer - Unlike Ron");
			testManager.updateClient(c);
			
			b = testManager.getClientByName("Liane");
			assertTrue(b.getClientDescription().equals("Real Software Engineer - Unlike Ron"));
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
			Client ron = testManager.getClientByName("Ron");
			ron.getProjectList(projects);
			assertTrue(projects.size() == 2);
		}
		catch (MyTimeException e)
		{
			e.printStackTrace();
			caughtException = true;
		}
		assertFalse(caughtException);
		
		try
		{
			Client l = testManager.getClientByName("Liane");
			Project p = new Project("DummyProject", "LianesDummyProject", l.getClientID(),
					true);
			l.addProject(p);
			testManager.updateClient(l);
		}
		catch(MyTimeException e)
		{
			e.printStackTrace();
		}
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
			project = testManager.getProject("Ron", "Dell");
		}

		catch (MyTimeException e)
		{
			caughtException = true;
			e.printStackTrace();
			System.out.println("Cannot retrieve project.");
		}
		assertFalse(caughtException);

		try
		{
			ArrayList<TimeInterval> T = testManager.getTimeIntervals(project
					.getName());
			assertTrue(T.size() == 4);
		}

		catch (MyTimeException e)
		{
			caughtException = true;
			e.printStackTrace();
			System.out.println("Can't retrive time intervals.");
		}
		assertFalse(caughtException);
	}
}
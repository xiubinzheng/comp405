package tests;

import client.Client;
import project.Project;
import project.TimeInterval;

import java.util.ArrayList;
import Controls.Manager;

import exceptions.MyTimeException;

import junit.framework.TestCase;

public class ManagerTest extends TestCase
{
	Manager				testManager;
	ArrayList<Client>	clients;
	ArrayList<Project>	projects;
	Client client = new Client();
	Project project = new Project();

	protected void setUp() throws Exception
	{
		testManager = new Manager();
	}

	protected void tearDown() throws Exception
	{

	}

	public void testClient()
	{
		boolean caughtException = false;
		// create new manager test initialize
		// if DB doesn't have 4 entities in the table assert will throw the
		// exception
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
		assertTrue(clients.size() == 4);

		ArrayList<Project> projects = new ArrayList<Project>();

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
			assertTrue(b.getClientDescription() == "Real Software Engineer - Unlike Ron");
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
			client.getProjectList(projects);
			assertTrue(projects.size() == 2);
		}
		catch (MyTimeException e)
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
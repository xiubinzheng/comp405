package tests;

import client.Client;
import project.Project;
import java.util.ArrayList;
import java.util.LinkedList;

import Controls.Manager;

import exceptions.MyTimeException;

import junit.framework.TestCase;

public class ManagerTest extends TestCase
{
	Manager				testManager;
	ArrayList<Client>	clients;
	ArrayList<Project>	projects;

	protected void setUp() throws Exception
	{
		/*
		 * clients = new ArrayList<Client>(); clients.add(new Client(0, "FRED.",
		 * "MANAGER.")); clients.add(new Client(1, "YEORGE.", "BAWSS."));
		 * clients.add(new Client(2, "JEFF.", "BOSS.")); clients.add(new
		 * Client(3, "RYAN.", "BOSS.")); clients.add(new Client(4, "JON.",
		 * "JANITOR."));
		 * 
		 * projects.add(new Project(0, "GENGIS KHAN", "ruled half the wurld",
		 * 500000, 0, true)); projects.add(new Project(1, "DONKEY KONG",
		 * "likes bananas", 2, 1, false)); projects.add(new Project(2, "T-REX",
		 * "8 the world", 9001, 2, true)); projects.add(new Project(3,
		 * "GEORGE W BUSH", "dick cheney", 999999999, 3, true));
		 * projects.add(new Project(4, "MAGIC 8 BALL", "ASK AGAIN LATER", -1, 4,
		 * false));
		 */
		// manager = new Manager();
	}

	protected void tearDown() throws Exception
	{

	}

	public void testMyCode()
	{

		// create new manager test initialize
		// if DB doesn't have 4 entities in the table assert will throw the
		// exception
		try
		{
			testManager = new Manager();
			testManager.initializeDB();
		}
		catch (MyTimeException e)
		{
			e.printStackTrace();
		}

		// test the manager to see if we can get the list of clients
		clients = new ArrayList<Client>();

		testManager.getClients(clients);
		assertTrue(clients.size() == 4);

		ArrayList<Project> projects = new ArrayList<Project>();
		ArrayList<Project> projects2 = new ArrayList<Project>();

		try
		{
			Client c = testManager.getClientByName("Ron");
			c.getProjectList(projects);
			assertTrue(projects.size() == 2);

			Project dummyProject = new Project();
			
			Client b = testManager.getClientByName("Cohen");
			b.getProjectList(projects);
			assertTrue(projects.size() == 4);
		}
		catch (MyTimeException e)
		{

		}

		// test will see if addProjectsToClient will add a linked list of
		// projects to a client

		String clientName;
		Project project = new Project();
		// LinkedList<Project> returned_projectList = new LinkedList<Project>();

		// testManager.addProjectToClient(clientName, project);

		// testManager.getProjectFromClient(clientName, returned_projectList);

		// int i = 0;
		// for(Project p : returned_projectList)
		{
			// assertTrue(p == projectList.get(i++));
		}

		// User tries to add a client
		// String s1 = "-1"; //Would be System.in
		// String s2 = "FRED";
		// String s3 = "MANAGER";
		// Attempt to add a client
		/*
		 * try { manager.addClient(new Client(Integer.parseInt(s1), s2, s3)); }
		 * catch(MyTimeException e) { System.err.println(e);
		 * e.printStackTrace(); //assert false; }
		 */
		// Client test = null;// = new Client(3, "RYAN.", "BOSS.");
		// test = manager.getClientByID(53);
		/*
		 * try { test = manager.getClientByName("FRED"); } catch
		 * (MyTimeException e) { e.printStackTrace(); }
		 * assertTrue(test.getClientName().equals("FRED"));
		 */

		// User tries to add a project
		/*
		 * s1 = "0"; s2 = "GENGIS KHAN"; s3 = "ruled half the wurld"; String s4
		 * = "500000"; String s5 = "0"; String s6 = "true"; //Attempt to add a
		 * project try { manager.addProject(new Project(Integer.parseInt(s1),
		 * s2, s3, Integer.parseInt(s4), Integer.parseInt(s5),
		 * Boolean.parseBoolean(s6))); Project test2 = new Project(0,
		 * "GENGIS KHAN", "ruled half the wurld", 500000, 0, true);
		 * manager.getProject(0, test2); assertTrue(test2.getID() == 0); }
		 * catch(MyTimeException e) { assertTrue(false); }
		 * 
		 * 
		 * //Test for multiple clients //1, "YEORGE.", "BAWSS." s1 = "1"; s2 =
		 * "YEORGE"; s3 = "JAWS."; try { manager.addClient(new
		 * Client(Integer.parseInt(s1), s2, s3)); } catch(MyTimeException e) {
		 * System.err.println(e); assert false; } ArrayList<Client> test3 = new
		 * ArrayList<Client>(); manager.getClients(test3);
		 * //assertTrue(test3.size() == 2);
		 * 
		 * //Test for multiple projects of one client //1, "DONKEY KONG",
		 * "likes bananas", 2, 1, false s1 = "1"; s2 = "DONKEY KONG"; s3 =
		 * "likes bananas"; s4 = "2"; s5 = "0"; s6 = "false"; try {
		 * manager.addProject(new Project(Integer.parseInt(s1), s2, s3,
		 * Integer.parseInt(s4), Integer.parseInt(s5),
		 * Boolean.parseBoolean(s6))); ArrayList<Project> test4 = new
		 * ArrayList<Project>(); manager.getProjects(0, test4);
		 * //assertTrue(test4.size() == 2); } catch(MyTimeException e) {
		 * assertTrue(false); }
		 */

	}
}
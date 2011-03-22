/*
 * This represents an easier interface for the DatabaseConnect
 * class, which will hide the SQL statements from the rest of
 * the program.
 */

package Controls;

import client.Client;
import project.Project;
import exceptions.MyTimeException;
import database.DatabaseConnect;
import java.util.*;
import java.sql.*;

/**
 * This class manages clients and projects in memory.
 */
public class Manager
{
	// TODO: Lots of hard-coded string here
	// we need to move these to a property file...
	private final static String			m_clientTableName		= "myTimeClients";
	private final static String			m_projectTableName		= "myTimeProjects";

	// SQL Format for Inserts and Selects
	private final static String			m_insertClient_CMDFMT	= "INSERT INTO %s VALUES (%s, \'%s\', \'%s\')";
	private final static String			m_selectClient_CMDFMT	= "SELECT * FROM %s WHERE %s = %s";
	private final static String			m_insertProject_CMDFMT	= "INSERT INTO %s VALUES(%s, %s, \'%s\', \'%s\', %s, \'%s\')";
	private final static String			m_selectProject_CMDFMT	= "SELECT * FROM %s WHERE %s = %s";
	private final static String			m_updateClient_CMDFMT	= "UPDATE %s SET \'%s\'=\'%s\', \'%s\'=\'%s\' WHERE \'%s\'=%d";
	private final static String			m_updateProject_CMDFMT	= "UPDATE %s SET \'%s\'=\'%s\', \'%s\'=\'%s\', \'%s\'=%s, \'%s\'=%s WHERE \'%s\'=%d";

	private String						m_databaseName			= "myTimeDB.s3db";

	private SQLGenerator				m_clientTableGen		= new SQLGenerator(
																		m_clientTableName);
	private SQLGenerator				m_projectTableGen		= new SQLGenerator(
																		m_projectTableName);

	private DatabaseConnect				m_database;
	private HashMap<Integer, Client>	m_clients;
	private HashMap<Integer, Project>   m_projects;

	/**
	 * This method creates a manager with default attributes. Manager needs to
	 * be initialized before use.
	 */
	public Manager()
	{
		m_clients = new HashMap<Integer, Client>();
		m_projects = new HashMap<Integer, Project>();

		m_database = DatabaseConnect.getDatabaseInstance(m_databaseName);
	}

	/**
	 * Manager initializer, pulls all data from the DB and loads them into memory so they
	 * can be accessed
	 * 
	 * @throws MyTimeException
	 */
	public void initializeDB() throws MyTimeException
	{
		int clientID;
		String clientName;
		String clientDescription;

		int projectID;
		String projectName;
		String projectDescription;
		// reuse clientID
		boolean projectHourly;
		boolean projectComplete;

		try
		{
			m_database.open();

			ResultSet result = m_database.execute(m_clientTableGen.select("*",
					null));
			// "SELECT * FROM "+ m_clientTableName);

			while (result.next())
			{
				clientID = result.getInt("Client_ID");
				clientName = result.getString("Client_Name");
				clientDescription = result.getString("Client_Description");

				Client c = new Client(clientID, clientName, clientDescription);
				m_clients.put(clientID, c);
			}

			// works so long as the test table has 4 values in it to test if
			// working
			// assert(m_clients.size() == 4);

			result = m_database.execute(m_projectTableGen.select("*", null));

			while (result.next())
			{
				projectID = result.getInt("Project_ID");
				clientID = result.getInt("Client_ID");
				projectName = result.getString("project_Name");
				projectDescription = result.getString("project_Description");
				projectHourly = result.getBoolean("project_Pay_Type_Hourly");
				projectComplete = result.getBoolean("project_Complete_Flag");

				// TODO: going to want to add time intervals to the project here
				// at some point

				Project p = new Project(projectID, projectName,
						projectDescription, clientID, projectHourly);
				if (projectComplete)
				{
					p.complete();
				}
				m_clients.get(clientID).addProject(p);
				m_projects.put(p.getProjectID(), p);
			}
		}
		catch (MyTimeException e)
		{
			throw new MyTimeException("Failure to open database", e);
		}
		catch (SQLException e)
		{
			throw new MyTimeException("Bad SQL Statement", e);
		}
	}

	// should be the only public method for now maybe more later gonna need to
	// TODO: finish up all this
	/**
	 * updates any changes made to a client and their projects to the database
	 * RUN ME OFTEN!
	 * 
	 * @param c
	 * @throws MyTimeException
	 */
	public void updateClient(Client c) throws MyTimeException
	{
		if (!m_clients.containsKey(c.getClientID()))
		{
			addClientToDB(c);
			updateProjects(c);
		}
		else
		{
			updateClientToDB(c);
		}
	}
	
	/**
	 * Returns client from database by Name if it exists
	 * 
	 * @param clientName
	 * @return Client
	 * @throws MyTimeException
	 */
	public Client getClientByName(String clientName) throws MyTimeException
	{
		return findClient(clientName);
	}
	
	/**
	 * Returns an array list of all clients
	 * 
	 * @param clientList
	 */
	public void getClients(ArrayList<Client> clientList)
	{
		Collection<Client> collection = m_clients.values();

		// Keeping this just in case the addAll doesn't work
		for (Client c : collection)
		{
			clientList.add(c);
		}
	}
	
	/**
	 * Returns a project when given the client and project name.
	 * 
	 * @param clientName
	 * @param projectName
	 * @return
	 * @throws MyTimeException
	 */
	public Project getProject(String clientName , String projectName)
			throws MyTimeException
	{
		ArrayList<Project> projectList = new ArrayList<Project>();
		int clientID = getClientByName(clientName).getClientID();

		m_clients.get(clientID).getProjectList(projectList);

		for (Project p : projectList)
		{
			if (p.getName().equals(projectName))
			{
				return p;
			}
		}

		return null;
	}

	/**
	 * adds a new Client to database
	 * 
	 * @param c
	 * @throws MyTimeException
	 */
	private void addClientToDB(Client c) throws MyTimeException
	{
		try
		{
			String cmd = String.format(m_insertClient_CMDFMT,
					m_clientTableName, "null", c.getClientName(),
					c.getClientDescription());

			// insert new client into DB
			m_database.update(cmd);

			// Query back for the ID assigned by the the DB for the new client
			ResultSet result = m_database.execute(String.format(
					"SELECT seq from SQLITE_SEQUENCE where name = '%s'",
					"myTimeClients"));

			int ID = -1;

			if (result.next())
			{
				ID = result.getInt("seq");
			}
			if (ID == -1)
			{
				throw new MyTimeException("Could not add client");
			}

			// set client ID to the new client class and add it to the client
			// hash map
			c.setClientID(ID);
			m_clients.put(c.getClientID(), c);
		}
		catch (SQLException e)
		{
			throw new MyTimeException("Add Client Error", e);
		}
	}
	
	/**
	 * Takes an existing client and updates all information to the Database
	 * 
	 * @param c
	 */
	private void updateClientToDB(Client c) throws MyTimeException
	{
		try
		{
			String cmd = String.format(m_updateClient_CMDFMT,
					m_clientTableName, "Client_Name", c.getClientName(),
					"Client_Description", c.getClientDescription(),
					"Client_ID", c.getClientID());
			
			// insert new client into DB
			m_database.update(cmd);
			
		}
		catch (MyTimeException e)
		{
			throw new MyTimeException("Update Client Error", e);
		}
	}
	
	/**
	 *Runs through projects for a client if they arn't in the DB runs an insert
	 *if the project is in the DB then it runs an update, we are doing this because it would be faster
	 *to update all the client's projects rather than search them for individual changes
	 * 
	 * @param c
	 */
	private void updateProjects(Client c)
	{
		//TODO: will run through projects for a client if they arn't in the DB runs an insert
		//if the project is in the DB then it runs an update, we are doing this because it would be faster
		//to update all the client's projects rather than search them for individual changes
		
		ArrayList<Project> projects = new ArrayList<Project>();
		
		c.getProjectList(projects);
		
		for(Project p : projects)
			if(m_projects.containsKey(p.getProjectID()))
			{
				try
				{
					updateProjectToDB(p);
				}
				catch (MyTimeException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else
			{
				try
				{
					addProjectToDB(p);
				}
				catch (MyTimeException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	}
	
	/**
	 * Adds a new project to the DB then queries the DB for that projects permanent ID
	 * 
	 * @param p
	 * @throws MyTimeException 
	 */
	private void addProjectToDB(Project p) throws MyTimeException
	{
		try
		{
			String cmd = String.format(m_insertProject_CMDFMT,
			m_projectTableName, p.getProjectID(), p.getClientID(), p.getName()
			, p.isComplete(), p.isHourly());

			// insert new client into DB
			m_database.update(cmd);

			// Query back for the ID assigned by the the DB for the new client
			ResultSet result = m_database.execute(String.format(
					"SELECT seq from SQLITE_SEQUENCE where name = '%s'",
					"myTimeProjects"));

			int ID = -1;

			if (result.next())
			{
				ID = result.getInt("seq");
			}
			if (ID == -1)
			{
				throw new MyTimeException("Could not add project");
			}
			p.setID(ID);
			m_projects.put(p.getProjectID(), p);
		}
		catch(SQLException e)
		{
			throw new MyTimeException("Add Project Error", e);
		}
	}
	
	/**
	 * private class that updates a single project in the DB
	 * 
	 * @param p
	 * @throws MyTimeException
	 */
	private void updateProjectToDB(Project p) throws MyTimeException
	{
		try
		{
			String cmd = String.format(m_updateClient_CMDFMT,
					m_clientTableName, "project_Name", p.getName(),
					"Project_Description", p.getDescription(),
					"Project_complete_flag", p.isComplete(),
					"Project_Pay_Type_hourly", p.isHourly(),
					"Project_ID", p.getProjectID());
			
			// insert new client into DB
			m_database.update(cmd);
			
		}
		catch (MyTimeException e)
		{
			throw new MyTimeException("Update Project Error", e);
		}
	}

	/**
	 * private class to find the a client by name
	 * 
	 * @param clientName
	 * @return
	 */
	private Client findClient(String clientName)// throws MyTimeException
	{
		Client client = null;
		Collection<Client> collection = m_clients.values();

		for (Client c : collection)
			if (c.getClientName().equals(clientName))
			{
				client = c;
				break;
			}
		return client;
	}

}

/*
 * This represents an easier interface for the DatabaseConnect
 * class, which will hide the SQL statements from the rest of
 * the program.
 */

package Controls;

import client.Client;
import project.Project;
import project.TimeInterval;
import exceptions.MyTimeException;
import database.DatabaseConnect;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.sql.*;
import java.util.Date;

/**
 * This class manages clients and projects in memory.
 */
public class Manager
{
	// TODO: Lots of hard-coded string here
	// we need to move these to a property file...
	private final static String				m_clientTableName		= "myTimeClients";
	private final static String				m_projectTableName		= "myTimeProjects";
	private final static String				m_timeTableName			= "myTimeInterval";

	// SQL Format for Inserts and Selects
	private final static String				m_insertClient_CMDFMT	= "INSERT INTO %s VALUES (%s, \'%s\', \'%s\')";
	// private final static String m_selectClient_CMDFMT =
	// "SELECT * FROM %s WHERE %s = %s";
	private final static String				m_insertProject_CMDFMT	= "INSERT INTO %s VALUES(%s, %s, \'%s\', \'%s\', %s, \'%s\')";
	// private final static String m_selectProject_CMDFMT =
	// "SELECT * FROM %s WHERE %s = %s";
	private final static String				m_insertTimeInterval_CMDFMT = "INSERT INTO %s VALUES(%s, %s, \'%s\', \'%s\', %s, \'%s\')";
	private final static String				m_updateClient_CMDFMT	= "UPDATE %s SET \'%s\'=\'%s\', \'%s\'=\'%s\' WHERE \'%s\'=%d";
	private final static String				m_updateProject_CMDFMT	= "UPDATE %s SET \'%s\'=\'%s\', \'%s\'=\'%s\', \'%s\'=%s, \'%s\'=%s WHERE \'%s\'=%d";

	private String							m_databaseName			= "myTimeDB.s3db";

	private SQLGenerator					m_clientTableGen		= new SQLGenerator(
																			m_clientTableName);
	private SQLGenerator					m_projectTableGen		= new SQLGenerator(
																			m_projectTableName);
	private SQLGenerator					m_timeTableGen			= new SQLGenerator(
																			m_timeTableName);

	private DatabaseConnect					m_database;
	private HashMap<Integer, Client>		m_clients;
	private HashMap<Integer, Project>		m_projects;
	private HashMap<Integer, TimeInterval>	m_timeIntervals;

	// Formatters for the date and time
	DateFormat								m_dateParser			= new SimpleDateFormat(
																			"yyyy-MM-dd hh:mm:ss");
	DateFormat								m_date					= new SimpleDateFormat(
																			"yyyy-MM-dd");
	DateFormat								m_time					= new SimpleDateFormat(
																			"hh:mm:ss");

	/**
	 * This method creates a manager with default attributes. Manager needs to
	 * be initialized before use.
	 */
	public Manager()
	{
		m_clients = new HashMap<Integer, Client>();
		m_projects = new HashMap<Integer, Project>();
		m_timeIntervals = new HashMap<Integer, TimeInterval>();

		m_database = DatabaseConnect.getDatabaseInstance(m_databaseName);
	}

	/**
	 * Manager initializer, pulls all data from the DB and loads them into
	 * memory so they can be accessed
	 * 
	 * @throws MyTimeException
	 * @throws ParseException
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

		int timeIntervalID;
		Date start;
		Date stop;

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

			result = m_database.execute(m_projectTableGen.select("*", null));
			while (result.next())
			{
				projectID = result.getInt("Project_ID");
				clientID = result.getInt("Client_ID");
				projectName = result.getString("project_Name");
				projectDescription = result.getString("project_Description");
				projectHourly = result.getBoolean("project_Pay_Type_Hourly");
				projectComplete = result.getBoolean("project_Complete_Flag");

				Project p = new Project(projectID, projectName,
						projectDescription, clientID, projectHourly);
				if (projectComplete)
				{
					p.complete();
				}
				m_clients.get(clientID).addProject(p);
				m_projects.put(p.getProjectID(), p);
			}

			result = m_database.execute(m_timeTableGen.select("*", null));
			while (result.next())
			{
				// TODO: time and date might break....
				timeIntervalID = result.getInt("Time_ID");
				projectID = result.getInt("Project_ID");

				String startTime = result.getString("Project_Start_Time");
				String stopTime = result.getString("Project_End_Time");
				String startDate = result.getString("Start_Date");
				String stopDate = result.getString("End_Date");
				start = m_dateParser.parse(startDate + " " + startTime);
				stop = m_dateParser.parse(stopDate + " " + stopTime);

				TimeInterval tInterval = new TimeInterval(timeIntervalID,
						projectID, start, stop);
				m_timeIntervals.put(timeIntervalID, tInterval);
				m_projects.get(projectID).addTime(tInterval);

				// System.out.println(m_timeIntervals.get(timeIntervalID).getProjectID()
				// + ", " + m_timeIntervals.get(timeIntervalID).getStart() +
				// ", " + m_timeIntervals.get(timeIntervalID).getStop());
			}
		}
		catch (MyTimeException e)
		{
			throw new MyTimeException("Failure to open database ", e);
		}
		catch (SQLException e)
		{
			throw new MyTimeException("Bad SQL Statement ", e);
		}
		catch (ParseException e)
		{
			throw new MyTimeException(
					"Failed to parse date and time fetched from database ", e);
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
	 * returns array list of time intervals
	 * 
	 * @param p
	 * @return
	 * @throws MyTimeException
	 */
	public ArrayList<TimeInterval> getTimeIntervals(String projectName)
			throws MyTimeException
	{
		ArrayList<TimeInterval> timeIntervals = new ArrayList<TimeInterval>();

		Project project = findProject(projectName);
		project.getTimeIntervals(timeIntervals);

		return timeIntervals;
	}
	
	/**
	 * 
	 * @param time
	 */
	private void addTimeIntervalToDB(TimeInterval time) throws MyTimeException
	{
		//add stuff
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
	 * Runs through projects for a client if they arn't in the DB runs an insert
	 * if the project is in the DB then it runs an update, we are doing this
	 * because it would be faster to update all the client's projects rather
	 * than search them for individual changes
	 * 
	 * @param c
	 */
	private void updateProjects(Client c)
	{
		// TODO: will run through projects for a client if they arn't in the DB
		// runs an insert
		// if the project is in the DB then it runs an update, we are doing this
		// because it would be faster
		// to update all the client's projects rather than search them for
		// individual changes

		ArrayList<Project> projects = new ArrayList<Project>();

		c.getProjectList(projects);

		for (Project p : projects)
			if (m_projects.containsKey(p.getProjectID()))
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
	 * Adds a new project to the DB then queries the DB for that projects
	 * permanent ID
	 * 
	 * @param p
	 * @throws MyTimeException
	 */
	private void addProjectToDB(Project p) throws MyTimeException
	{
		try
		{
			//TODO: Need project description?
			String cmd = String.format(m_insertProject_CMDFMT,
					m_projectTableName, p.getProjectID(), p.getClientID(),
					p.getName(), p.isComplete(), p.isHourly());

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
		catch (SQLException e)
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
			String cmd = String.format(m_updateProject_CMDFMT,
					m_projectTableName, "Project_Name", p.getName(),
					"Project_Description", p.getDescription(),
					"Project_Complete_Flag", p.isComplete(),
					"Project_Pay_Type_Hourly", p.isHourly(), "Project_ID",
					p.getProjectID());

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
		{
			if (c.getClientName().equals(clientName))
			{
				client = c;
				break;
			}
		}
		return client;
	}

	/**
	 * 
	 * @param projectName
	 * @return
	 */
	private Project findProject(String projectName)// throws MyTimeException
	{
		Project project = null;
		Collection<Project> collection = m_projects.values();

		for (Project p : collection)
		{
			if (p.getName().equals(projectName))
			{
				project = p;
				break;
			}
		}
		return project;
	}
}

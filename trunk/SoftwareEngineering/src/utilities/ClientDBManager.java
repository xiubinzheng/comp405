/*
 * This represents an easier interface for the DatabaseConnect
 * class, which will hide the SQL statements from the rest of
 * the program.
 */

package utilities;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.awt.image.DataBufferInt;
import java.sql.*;
import java.util.Date;

import models.*;

/**
 * This class manages clients and projects in memory.
 */
public class ClientDBManager
{
	// TODO: Lots of hard-coded string here
	// we need to move these to a property file...
	static private ClientDBManager DBSingleton;
	
	private boolean 						m_DBInitialized;
	private final static String				m_clientTableName	= "myTimeClients";
	private final static String				m_projectTableName	= "myTimeProjects";
	private final static String				m_timeTableName		= "myTimeInterval";

	private String							m_databaseName		= "myTimeDB.s3db";
	private SQLGenerator					m_clientTableGen	= new SQLGenerator(
																		m_clientTableName);
	private SQLGenerator					m_projectTableGen	= new SQLGenerator(
																		m_projectTableName);
	private SQLGenerator					m_timeTableGen		= new SQLGenerator(
																		m_timeTableName);

	private DBConnector					    m_database;
	private HashMap<Integer, Client>		m_clients;
	private HashMap<Integer, Project>		m_projects;
	private HashMap<Integer, TimeInterval>	m_timeIntervals;

	// Formatters for the date and time
	DateFormat								m_dateParser		= new SimpleDateFormat(
																		"yyyy-MM-dd kk:mm:ss");
	DateFormat								m_date				= new SimpleDateFormat(
																		"yyyy-MM-dd");
	DateFormat								m_time				= new SimpleDateFormat(
																		"kk:mm:ss");

	/**
	 * This method creates a manager with default attributes. Manager needs to
	 * be initialized before use.
	 */
	private ClientDBManager()
	{
		m_clients = new HashMap<Integer, Client>();
		m_projects = new HashMap<Integer, Project>();
		m_timeIntervals = new HashMap<Integer, TimeInterval>();

		m_database = DBConnector.getDatabaseInstance(m_databaseName);
		m_DBInitialized = false;
	}
	/**
	 * There is only one instance of the ClientDBManager.<br>
	 * !!IMPORTANT: INITIALIZE THE DATABASE WITH initialize()!!
	 * 
	 * @return Singleton of the ClientDBManager
	 */
	public static ClientDBManager getInstance()
	{
		if (DBSingleton == null)
		{
			return new ClientDBManager();
		}
		return DBSingleton;
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
		if(m_DBInitialized)
			return;
		m_DBInitialized = true;
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

			ResultSet result = m_database.execute(m_clientTableGen.select("*",null));
			while (result.next())
			{
				clientID = result.getInt("Client_ID");
				clientName = result.getString("Client_Name");
				clientDescription = result.getString("Client_Description");

				Client c = new Client(clientID, clientName, clientDescription);
				m_clients.put(clientID, c);
			}
			System.out.println("DEBUG loaded client list:"+m_clients);

			result = m_database.execute(m_projectTableGen.select("*", null));
			while (result.next())
			{
				projectID = result.getInt("Project_ID");
				clientID = result.getInt("Client_ID");
				projectName = result.getString("Project_Name");
				projectDescription = result.getString("Project_Description");
				projectHourly = result.getBoolean("Project_Pay_Type_Hourly");
				projectComplete = result.getBoolean("Project_Complete_Flag");

				Project p = new Project(projectID, projectName,
						projectDescription, clientID, projectHourly);
				System.out.println("DEBUG adding project: "+p);
				if (projectComplete)
				{
					p.complete();
				}
				
//				//debug
//				System.out.println(m_clients.size());
//				System.out.println(clientID);
//				System.out.println(p);
//				//debug
				
				m_clients.get(clientID).addProject(p);
				m_projects.put(p.getProjectID(), p);
			}
			System.out.println("DEBUG loaded project list:"+m_projects);

			result = m_database.execute(m_timeTableGen.select("*", null));
			while (result.next())
			{
				timeIntervalID = result.getInt("Time_ID");
				projectID = result.getInt("Project_ID");

				String startTime = result.getString("Project_Start_Time");
				String stopTime = result.getString("Project_End_Time");
				String startDate = result.getString("Start_Date");
				String stopDate = result.getString("End_Date");
//				System.out.println("DEBUG "+startDate + " " + startTime);
//				System.out.println("DEBUG "+stopDate);
				start = m_dateParser.parse(startDate + " " + startTime);
				stop = m_dateParser.parse(stopDate + " " + stopTime);

				TimeInterval tInterval = new TimeInterval(timeIntervalID, projectID, start, stop);
				m_timeIntervals.put(timeIntervalID, tInterval);
				System.out.println("DEBUG: projectID:" + projectID + " time:"+ tInterval);
				System.out.println(m_projects.containsKey(projectID));
				m_projects.get(projectID).addTime(tInterval);
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
		System.out.println("DEBUG loaded time intervals:"+m_timeIntervals);
//		try {
//			this.wait(1000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			System.exit(-1);
//		}
	}

	// should be the only public method for now maybe more later gonna need to
	/**
	 * updates any changes made to a client and their projects to the database
	 * RUN ME OFTEN!
	 * 
	 * @param c
	 * @throws MyTimeException
	 */
	public void updateClient(Client c) throws MyTimeException
	{
		assert(m_DBInitialized);
		if (!m_clients.containsKey(c.getClientID()))
		{
			addClientToDB(c);
			updateProjects(c);
		}
		else
		{
			updateClientToDB(c);
			updateProjects(c);
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
		assert(m_DBInitialized);
		return findClient(clientName);
	}

	/**
	 * Returns an array list of all clients
	 * 
	 * @param clientList
	 */
	public void getClients(ArrayList<Client> clientList)
	{
		assert(m_DBInitialized);
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
		assert(m_DBInitialized);
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
		assert(m_DBInitialized);
		ArrayList<TimeInterval> timeIntervals = new ArrayList<TimeInterval>();

		Project project = findProject(projectName);
		project.getTimeIntervals(timeIntervals);

		return timeIntervals;
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

			String cmd = m_clientTableGen.insert(
					"Client_Name, Client_Description", "'"+esc(c.getClientName()) + "','"
							+ c.getClientDescription()+"'");

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
			String cmd = m_clientTableGen.update(
					"Client_Name, Client_Description",
					"'"+esc(c.getClientName()) + "','"+ esc(c.getClientDescription())+"'",
					"Client_ID = " + c.getClientID());

			// insert new client into DB
//			System.out.println("DEBUG "+cmd);
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
		{
			if (m_projects.containsKey(p.getProjectID()))
			{
				try
				{
					updateProjectToDB(p);
					updateTimeIntervals(p);
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
					updateTimeIntervals(p);
				}
				catch (MyTimeException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
			String cmd = m_projectTableGen
					.insert("Client_ID, Project_Name, Project_Description, Project_Complete_Flag, Project_Pay_Type_Hourly",
							p.getClientID() + ",'" + esc(p.getName()) + "','"
									+ esc(p.getDescription()) + "','"
									+ esc(Boolean.toString(p.isComplete())) + "','" + esc(Boolean.toString(p.isHourly()))+"'");

			// insert new client into DB
			m_database.update(cmd);

			// Query back for the ID assigned by the the DB for the new time interval
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
			String cmd = m_projectTableGen
					.update("Client_ID, Project_Name, Project_Description, Project_Complete_Flag, Project_Pay_Type_Hourly",
							p.getClientID() + ",'"+ esc(p.getName()) + "','" + esc(p.getDescription()) + "','" + esc(Boolean.toString(p.isComplete())) + "','" + esc(Boolean.toString(p.isHourly()))+"'",
							"Project_ID ="+ p.getProjectID());

			// insert new project into DB
			m_database.update(cmd);

		}
		catch (MyTimeException e)
		{
			throw new MyTimeException("Update Project Error", e);
		}
	}

	/**
	 * 
	 * @param project
	 * @throws MyTimeException
	 */
	private void updateTimeIntervals(Project project) throws MyTimeException
	{
		ArrayList<TimeInterval> times = new ArrayList<TimeInterval>();
		project.getTimeIntervals(times);

		for (TimeInterval t : times)
		{
			if (m_timeIntervals.containsKey(t.getTimeID()))
			{
				try
				{
					updateTimeIntervalToDB(t);
				}
				catch (MyTimeException e)
				{
					e.printStackTrace();
				}
			}
			else
			{
				try
				{
					addTimeIntervalToDB(t);
				}
				catch (MyTimeException e)
				{
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 
	 * @param time
	 */
	private void addTimeIntervalToDB(TimeInterval time) throws MyTimeException
	{
		try
		{
			String cmd = m_timeTableGen
					.insert(" Project_ID , Project_Start_Time , Project_End_Time , Start_Date , End_Date",
							time.getProjectID() + ",'"
									+ m_time.format(time.getStart()) + "','"
									+ m_time.format(time.getStop()) + "','"
									+ m_date.format(time.getStart()) + "','"
									+ m_date.format(time.getStop()) + "'");
			m_database.update(cmd);

			ResultSet result = m_database.execute(String.format(
					"SELECT seq from SQLITE_SEQUENCE where name = '%s'",
					"myTimeInterval"));

			int ID = -1;

			if (result.next())
			{
				ID = result.getInt("seq");
			}
			if (ID == -1)
			{
				throw new MyTimeException("Could not add time interval");
			}

			time.setTimeID(ID);
			m_timeIntervals.put(time.getTimeID(), time);
		}
		catch (SQLException e)
		{
			throw new MyTimeException("Add time interval error ", e);
		}

	}

	/**
	 * 
	 * @param time
	 * @throws MyTimeException
	 */
	private void updateTimeIntervalToDB(TimeInterval time)
			throws MyTimeException
	{
		try
		{
			String cmd = m_timeTableGen
					.update("Project_Start_Time, Project_End_Time, Start_Date, End_Date",
							"'"+m_time.format(time.getStart()) + "','"
									+ m_time.format(time.getStop()) + "','"
									+ m_date.format(time.getStart()) + "','"
									+ m_date.format(time.getStop())+"'",
							"Project_ID=" + time.getProjectID());

			// update time into DB
			m_database.update(cmd);
		}
		catch (MyTimeException e)
		{
			throw new MyTimeException("Update Time Interval Error", e);
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
	
	/**
	 * Prevent bad parses when a single quote is used in a string
	 * @param replace string to have all of its single quotes replaced with "''"
	 * @return
	 */
	private String esc(String replace)
	{
		return replace.replace("'", "''");
	}
}

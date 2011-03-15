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
	private final static String m_clientTableName = "myTimeClients";
	private final static String m_projectTableName = "myTimeProjects";
	
	//SQL Format for Inserts and Selects
	private final static String m_insertClient_CMDFMT = 
		"INSERT INTO %s VALUES (%s, \'%s\', \'%s\')";
	private final static String m_selectClient_CMDFMT =
		"SELECT * FROM %s WHERE %s = %s";
	private final static String m_insertProject_CMDFMT = 
		"INSERT INTO %s VALUES(%s, %s, \'%s\', \'%s\', %s, \'%s\')";
	private final static String m_selectProject_CMDFMT =
		"SELECT * FROM %s WHERE %s = %s";

	private String m_databaseName = "myTimeDB.s3db";
	
	private SQLGenerator m_clientTableGen = new SQLGenerator(m_clientTableName);
	private SQLGenerator m_projectTableGen = new SQLGenerator(m_projectTableName);
	
	private DatabaseConnect m_database;
	private HashMap<Integer, Client> m_clients;
	
	/**
	 * This method creates a manager with default attributes.
	 * Manager needs to be initialized before use.
	 */
	public Manager()
	{
		m_clients = new HashMap<Integer, Client>();
		
		m_database = DatabaseConnect.getDatabaseInstance(m_databaseName);		
	} 
	
	public void initializeDB() throws MyTimeException
	{
		int clientID;
		String clientName;
		String clientDescription;
		
		int projectID;
		String projectName;
		String projectDescription;
		int projectHours;
		//reuse clientID
		boolean projectHourly;
		boolean projectComplete;
		
		try 
		{
			m_database.open();
			
			ResultSet result = m_database.execute(m_clientTableGen.select("*", null));
					//"SELECT * FROM "+ m_clientTableName);
			
			while(result.next())
			{
				clientID = result.getInt("Client_ID");
				clientName = result.getString("Client_Name");
				clientDescription = result.getString("Client_Description");
				
				Client c = new Client(clientID, clientName, clientDescription);
				m_clients.put(clientID, c);
			}
			
			//works so long as the test table has 4 values in it to test if working
			//assert(m_clients.size() == 4);
			
			result = m_database.execute(m_projectTableGen.select("*", null));
			
			while(result.next())
			{
				projectID = result.getInt("Project_ID");
				clientID = result.getInt("Client_ID");
				projectName = result.getString("project_Name");
				projectDescription = result.getString("project_Description");
				projectHourly = result.getBoolean("project_Pay_Type_Hourly");
				projectComplete = result.getBoolean("project_Complete_Flag");
				
				//TODO: going to want to add time intervals to the project here at some point
				
				Project p = new Project(projectID, projectName, projectDescription, clientID, projectComplete);
				m_clients.get(clientID).addProject(p);
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
	
	//should be the only public method for now maybe more later gonna need to change all this
	public void updateClient(Client c) throws MyTimeException
	{
		addClient(c);
	}
	
	/**
	 * adds Client to database
	 * @param c
	 * @throws MyTimeException
	 */
	public void addClient(Client c) throws MyTimeException
	{
		if(!m_clients.containsKey(c.getClientID()))
		{
			try
			{
				String cmd = String.format(
						m_insertClient_CMDFMT,
						m_clientTableName,
						"null",
						c.getClientName(),
						c.getClientDescription());		
				
				//no longer need to check the DB for an existing client because of the nature of the hash map
				/*
				ResultSet rs = m_database.execute(String.format(m_selectClient_CMDFMT, m_clientTableName, "Client_Name", "'"+c.getClientName()+"'"));

				if(rs.next())
					throw new MyTimeException("Client already exists!");
				*/
				
				//insert new client into DB
				m_database.update(cmd);
				
				//Query back for the ID assigned by the the DB for the new client
				ResultSet result = m_database.execute(
														String.format(
														"SELECT seq from SQLITE_SEQUENCE where name = '%s'",
														"myTimeClients"));
				
				int ID = -1;
				
				if(result.next())
				{
					ID = result.getInt("seq");
				}
				if(ID==-1)
				{
					throw new MyTimeException("Could not add client");
				}
				
				//set client ID to the new client class  and add it to the client hash map
				c.setClientID(ID);
				m_clients.put(c.getClientID(), c);
			}
			catch(SQLException e)
			{
				throw new MyTimeException("Add Client Error", e);
			}
		}
		else
		{
			throw new MyTimeException("Client already exists: " + c.toString());
		}
	}
	
	public void addProject(Project p)
	{
		
	}
	
	/**
	 * Returns client from database by Name if it exists
	 * @param clientName
	 * @return Client
	 * @throws MyTimeException
	 */
	public Client getClientByName(String clientName) throws MyTimeException
	{
		return findClient(clientName);
	}
	
	
	public Project getProject(String clientName, String projectName) throws MyTimeException
	{
		ArrayList<Project> projectList = new ArrayList<Project>();
		int clientID = getClientByName(clientName).getClientID();
		
		m_clients.get(clientID).getProjectList(projectList);
		
		for(Project p : projectList)
		{
			if(p.getName().equals(projectName))
			{
				return p;
			}
		}

		return null;
	}
	
	/*private final static String m_insertClient_CMDFMT = 
		"INSERT INTO %s VALUES (%s, \'%s\', \'%s\')";
	private final static String m_selectClient_CMDFMT =
		"SELECT * FROM %s WHERE %s = %s";*/
	
	private String generateInsert(String table_names, String values)
	{
		return null;
	}
	
	public void getClients(ArrayList<Client> clientList)
	{	
		Collection<Client> collection = m_clients.values();
		
		//Keeping this just in case the addAll doesn't work
		for(Client c : collection)
		{	
			clientList.add(c);
		}
	}
	
	public void getProjects(int clientID, ArrayList<Project> projectList)
	{
		//I N C E P T I O N level 2 we need to go deeper!
		//passes back the project list held by the client class
		m_clients.get(clientID).getProjectList(projectList);
	}

	public void addProjectToClient(String clientName, Project project) throws MyTimeException
	{
		Client client = findClient(clientName);
		client.addProject(project);
/*		try
		{
			
		}
		catch (MyTimeException e)
		{
			throw new MyTimeException("Couldn't find client to add project", e);
		}
*/		
	}
	
	private Client findClient(String clientName)// throws MyTimeException
	{
		Client client = null;
		Collection<Client> collection = m_clients.values();
		
		for(Client c : collection)
			if(c.getClientName().equals(clientName))
			{
				client = c;
				break;
			}
/*		if(client == null)
		{
			try
			{
				String cmd = String.format(
						m_selectClient_CMDFMT,
						m_clientTableName,
						"Client_Name",
						"'"+clientName+"'");
				ResultSet result = m_database.execute(cmd);
				int ID;
				String name;
				String desc;
				if(result.next())
				{
					ID = result.getInt("Client_ID");
					name = result.getString("Client_Name");
					desc = result.getString("Client_Description");
					client = new Client(ID, name, desc);
					m_clients.put(client.getClientID(), client);
				}
			}
			catch(MyTimeException e)
			{
				throw new MyTimeException("findClient(String):Could not execute SQL command", e);
			}
			catch(SQLException e)
			{
				throw new MyTimeException("findClient(String):Could not get result set",e);
			}
		}
*/
		return client;
	}
	private void addToDatabase(Client client) throws MyTimeException
	{
		try
		{
			String cmd = String.format(
					m_insertClient_CMDFMT,
					m_clientTableName,
					"null",
					client.getClientName(),
					client.getClientDescription());

			ResultSet rs = m_database.execute(String.format(m_selectClient_CMDFMT, m_clientTableName, "Client_Name", "'"+client.getClientName()+"'"));

			if(rs.next())
				throw new MyTimeException("Client already exists!");

			m_database.update(cmd);
			ResultSet result = m_database.execute(
					String.format(
							"SELECT seq from SQLITE_SEQUENCE where name = '%s'",
							"myTimeClients"));
			int ID = -1;
			if(result.next())
			{
				ID = result.getInt("seq");
			}
			if(ID==-1)
				throw new MyTimeException("Could not add client");
			client.setClientID(ID);
			m_clients.put(client.getClientID(), client);
		}
		catch(SQLException e)
		{
			throw new MyTimeException("Add Client Error", e);
		}
	}
	private void addToDatabase(Project project) throws MyTimeException
	{
		try
		{
			String cmd = String.format(
					m_insertClient_CMDFMT,
					m_clientTableName,
					"null",
					project.getName(),
					project.getDescription());
			
			//don't need right now delete later when we are sure we don't need it
			/*
			ResultSet rs = m_database.execute(m_projectTableGen.select("*", null));
			//		String.format(m_selectClient_CMDFMT, m_clientTableName, "Client_Name", "'"+client.getClientName()+"'"));

			if(rs.next())
				throw new MyTimeException("Client already exists!");
			*/
			
			m_database.update(cmd);
			ResultSet result = m_database.execute(
					String.format(
							"SELECT seq from SQLITE_SEQUENCE where name = '%s'",
							"myTimeClients"));
			int ID = -1;
			if(result.next())
			{
				ID = result.getInt("seq");
			}
			if(ID==-1)
				throw new MyTimeException("Could not add client");
			//client.setClientID(ID);
			//m_clients.put(client.getClientID(), client);
		}
		catch(SQLException e)
		{
			throw new MyTimeException("Add Client Error", e);
		}

	}
}

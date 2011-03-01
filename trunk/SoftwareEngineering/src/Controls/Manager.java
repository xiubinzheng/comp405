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

	private String m_databaseName = "myTimeDB.s3db";
	
	private HashMap<Integer, Project> m_projects;
	private DatabaseConnect m_database;
	private HashMap<Integer, Client> m_clients;
	
	/**
	 * This method creates a manager with default attributes.
	 * Manager needs to be initialized before use.
	 */
	public Manager()
	{
		m_clients = new HashMap<Integer, Client>();
		m_projects = new HashMap<Integer, Project>();
		
		m_database = DatabaseConnect.getDatabaseInstance(m_databaseName);		
	} 
	
	public void initializeDB() throws MyTimeException
	{
		int clientID;
		String clientName;
		String clientDescription;
		
		// TODO:  we should read in all clients(now reads clients) and projects when we 
		// are initialized
		try 
		{
			m_database.open();
			
			ResultSet result = m_database.execute("SELECT * FROM "+ m_clientTableName);
			while(result.next())
			{
				clientID = result.getInt("Client_ID");
				clientName = result.getString("Client_Name");
				clientDescription = result.getString("Client_Description");
				
				Client c = new Client(clientID, clientName, clientDescription);
				m_clients.put(clientID, c);
			}
			//works so long as the test table has 4 values in it something something something
			assert(m_clients.size() == 4);
			
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
	/**
	 * adds Client to database
	 * @param Client
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

				ResultSet rs = m_database.execute(String.format(m_selectClient_CMDFMT, m_clientTableName, "Client_Name", "'"+c.getClientName()+"'"));

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
				c.setClientID(ID);
				m_clients.put(c.getClientID(), c);
			}
			catch(SQLException e)
			{
				throw new MyTimeException("Add Client Error", e);
			}
		}
		throw new MyTimeException("Client already exists: " + c.toString());
	}
	
	public void addProject(Project p)
	{
		m_projects.put(p.getID(), p);
	}
	
	/**
	 * Returns client from database by ID if it exists
	 * @param id
	 * @return Client
	 * @throws MyTimeException
	 */
	public Client getClientByID(int id) throws MyTimeException
	{
		Client client = null;
		
		if(m_clients.containsKey(id))
		{
			return m_clients.get(id);
		}
		else
		{
			try
			{
				String cmd = String.format(
						m_selectClient_CMDFMT,
						m_clientTableName,
						"Client_ID",
						Integer.toString(id));
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
				throw new MyTimeException("Could not execute SQL command", e);
			}
			catch(SQLException e)
			{
				throw new MyTimeException("Could not get result set",e);
			}
		}
		return client;
	}
	
	/**
	 * Returns client from database by Name if it exists
	 * @param clientName
	 * @return Client
	 * @throws MyTimeException
	 */
	public Client getClientByName(String clientName) throws MyTimeException
	{
		Client client = null;
		//TODO: Fix this so it works with the new initializer 
		/*for(Client c : m_clients)
			if(c.getClientName().equals(clientName))
			{
				client = c;
				break;
			}*/
		if(client == null)
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
				throw new MyTimeException("Could not execute SQL command", e);
			}
			catch(SQLException e)
			{
				throw new MyTimeException("Could not get result set",e);
			}
		}
		return client;
	}
	
	public void getProject(int id, Project project)
	{
		//TODO: Fix this so it works with the new initializer 
		/*for(Project p : m_projects)
			if(p.getID()==id)
			{
				project = p;
				break;
			}*/
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
		clientList = new ArrayList<Client>();
		clientList.addAll(m_clients.values());
		//TODO: Fix this so it works with the new initializer 
		//for(Client c : m_clients)
		//	clientList.add(c);
	}
	
	public void getProjects(int clientID, ArrayList<Project> projectList)
	{
		projectList = new ArrayList<Project>(); 
		//TODO: Fix this so it works with the new initializer 
		//for(Project p : m_projects)
		{
		//	if(p.getClientID()==clientID)
		//		projectList.add(p);
		}
	}
}

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
 * 
 *
 */
public class Manager 
{
	private final static String m_clientTableName = "myTimeClients";
	private final static String m_projectTableName = "myTimeProjects";
	
	private final static String m_insertClient_CMDFMT = 
		"INSERT INTO %s VALUES (%s, \'%s\', \'%s\')";
	private final static String m_selectClient_CMDFMT =
		"SELECT * FROM %s WHERE %s = %s";

	private String m_databaseName = "myTimeDB.s3db";
	Set<Project> m_projects;
	DatabaseConnect m_database;
	Set<Client> m_clients;
	
	/**
	 * This method creates defaults.
	 */
	public Manager() throws MyTimeException
	{
		m_clients = new HashSet<Client>();
		m_projects = new HashSet<Project>();
		m_database = DatabaseConnect.getDatabaseInstance(m_databaseName);
		try 
		{
			m_database.open();
		} 
		catch (MyTimeException e)
		{
			throw new MyTimeException("Failure to open database", e);
		}
	}
	public void addClient(Client c) throws MyTimeException
	{
		if(!m_clients.contains(c))
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
				if(rs!=null)
					throw new MyTimeException("Client Name must be unique");
				m_database.update(cmd);
				ResultSet result = m_database.execute(
						String.format(
								"SELECT seq from SQLITE_SEQUENCE where name = '%s'",
								"myTimeClients"));
				int ID = -1;
				try 
				{
					if(result.next())
					{
						ID = result.getInt("seq");
					}
				} 
				catch (SQLException e) 
				{
					throw new MyTimeException("Could not get Result Set", e);
				}
				if(ID==-1)
					throw new MyTimeException("Could not add client");
				c.setClientID(ID);
				m_clients.add(c);
			}
			catch(MyTimeException e)
			{
				throw new MyTimeException("Add Client Error", e);
			}
		}
	}
	public void addProject(Project p)
	{
		m_projects.add(p);
	}
	public Client getClientByID(int id) throws MyTimeException
	{
		Client client = null;
		for(Client c : m_clients)
			if(c.getClientID()==id)
			{
				client = c;
				break;
			}
		if(client == null)
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
					m_clients.add(client);
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
	
	public Client getClientByName(String clientName) throws MyTimeException
	{
		Client client = null;
		for(Client c : m_clients)
			if(c.getClientName().equals(clientName))
			{
				client = c;
				break;
			}
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
					m_clients.add(client);
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
		for(Project p : m_projects)
			if(p.getID()==id)
			{
				project = p;
				break;
			}
	}
	public void getClients(ArrayList<Client> clientList)
	{	
		clientList = new ArrayList<Client>();
		for(Client c : m_clients)
			clientList.add(c);
	}
	
	public void getProjects(int clientID, ArrayList<Project> projectList)
	{
		projectList = new ArrayList<Project>(); 
		
		for(Project p : m_projects)
		{
			if(p.getClientID()==clientID)
				projectList.add(p);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString()
	{
		//to be completed
		return null;
	}
}

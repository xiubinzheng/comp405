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
	public Manager()
	{
		m_clients = new HashSet<Client>();
		m_projects = new HashSet<Project>();
		m_database = DatabaseConnect.getDatabaseInstance(m_databaseName);
		try {
			m_database.open();
		} catch (MyTimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
				System.out.println(cmd);
				m_database.execute(cmd);
				ResultSet result = m_database.execute(
						String.format(
								"SELECT seq from SQLITE_SEQUENCE where name = '%s'",
								"myTimeClients"));
				
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
	public Client getClientByID(int id)
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
				System.out.println("RESULT : "+result);
				System.out.println(cmd);
				int ID;
				String name;
				String desc;
				System.out.println("RESULT is "+(result.next()));
				if(result.next())
				{
					ID = result.getInt("ClientID");
					name = result.getString("ClientName");
					desc = result.getString("ClientDesc");
					System.out.println("Client = "+ID+" "+name+" "+desc);
					client = new Client(ID, name, desc);
					m_clients.add(client);
				}
			}
			catch(MyTimeException e)
			{
				
			}
			catch(SQLException e)
			{
				
			}
		}
		return client;
	}
	
	public Client getClientByName(String clientName)
	{
		return null;
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
	
	/*
	 * 
	 */
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

package client;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

import project.Project;

import java.util.Set;

public class Client
{
	private int		m_clientID			= 0;
	private String	m_clientName		= "";
	private String	m_clientDescription	= "";
	private Set<Project> m_projects = new HashSet<Project>();
	
	/**
	 * Create a new Client with default attributes.
	 */
	public Client()
	{
		
	}
	
	/**
	 * Create a client with the provided arguments.
	 * @param clientID ID of the client.
	 * @param name Name of the client.
	 * @param description Description of the client.
	 */
	public Client(int clientID, String name, String description)
	{
		m_clientID = clientID;
		m_clientName = name;
		m_clientDescription = description;
	}

	/**
	 * Create a client with the provided arguments.
	 * @param clientID ID of the client.
	 * @param name Name of the client.
	 * @param description Description of the client.
	 * @param projects Linked List of projects.
	 */
	public Client(int clientID, String name, String description, LinkedList<Project> projects)
	{
		m_clientID = clientID;
		m_clientName = name;
		m_clientDescription = description;
		for(Project project : projects)
			m_projects.add(project);
	}
	
	/**
	 * Add project. Duplicates are ignored.
	 * @param project
	 */
	public void addProject(Project project)
	{
		m_projects.add(project);
	}
	
	/**
	 * Remove project.
	 * @param projID The ID number of the project to be removed.
	 * @return True if removed project existed and was removed, false if not.
	 */
	public boolean removeProject(int projID)
	{
		for(Project project : m_projects)
		{
			if(project.getID() == projID)
			{
				m_projects.remove(project);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * populates a passed list with a client's projects.
	 * @param projectList
	 */
	public void getProjectList(ArrayList<Project> projectList)
	{
		projectList.addAll(m_projects);
	}

	public void setClientID(int clientID)
	{
		m_clientID = clientID;
	}

	public int getClientID()
	{
		return m_clientID;
	}

	public void setClientName(String name)
	{
		m_clientName = name;
	}

	public String getClientName()
	{
		return m_clientName;
	}

	public void setClientDescription(String description)
	{
		m_clientDescription = description;
	}

	public String getClientDescription()
	{
		return m_clientDescription;
	}

	/**
	 * Returns a string containing the client ID, client name, and client
	 * description.
	 */
	public String toString()
	{
		return m_clientID + " " + m_clientName + " " + m_clientDescription;
	}
}

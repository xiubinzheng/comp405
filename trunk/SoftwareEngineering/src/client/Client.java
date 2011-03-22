package client;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

import project.Project;

import java.util.Set;

public class Client
{
	private int		m_clientID			= 0;
	private String	m_clientName		= "";
	private String	m_clientDescription	= "";
	private HashMap<Integer, Project> m_projects = new HashMap<Integer, Project>();
	
	int m_tempProjectKey = -1; //dec with each use
	
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
	 * @param projects should already have an ID set
	 */
	public Client(int clientID, String name, String description, LinkedList<Project> projects)
	{
		m_clientID = clientID;
		m_clientName = name;
		m_clientDescription = description;
		for(Project project : projects)
			m_projects.put(project.getID(), project);
	}
	
	/**
	 * Add project. Duplicates are ignored.
	 * @param project
	 */
	public boolean addProject(Project project)
	{
		if(!m_projects.containsKey(project.getID()))
		{
			if(project.getID() < 0)
			{
				m_projects.put(m_tempProjectKey, project);
				--m_tempProjectKey;
			}
			else
			{
				m_projects.put(project.getID(), project);
			}
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * Remove project.
	 * @param projID The ID number of the project to be removed.
	 * @return True if removed project existed and was removed, false if not.
	 */
	public boolean removeProject(int projID)
	{
		if(m_projects.containsKey(projID))
		{
			m_projects.remove(projID);
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * populates a passed list with a client's projects.
	 * @param projectList
	 */
	public void getProjectList(ArrayList<Project> projectList)
	{
		//I N C E P T I O N level 3 watch out for limbo!
		Collection<Project> projects = m_projects.values();
		projectList.clear();
		projectList.addAll(projects);
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

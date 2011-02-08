package Controls;

import client.Client;
import project.Project;
import exceptions.MyTimeException;
import java.util.*;

public class Manager 
{
	ArrayList<Client> m_clients;
	ArrayList<Project> m_projects;
	
	public Manager()
	{
		m_clients = new ArrayList();
		m_projects = new ArrayList();
		
	}
	
	public void addClient(Client c)
	{
		m_clients.add(c);
	}
	
	public void addProject(Project p)
	{
		m_projects.add(p);
	}
	
	public void getClient(int id, Client client)
	{
		Client c = m_clients.get(id);
		client = new Client(c.getClientID(), c.getClientName(), c.getClientDescription());
	}
	
	public void getProject(int id, Project project)
	{
		Project p = m_projects.get(id);
		
		try
		{
			project = new Project(p.getID(), p.getName(), p.getDescription(), p.getHours(), p.getClientID(), p.isHourly());
		}
		catch(MyTimeException e)
		{
			e.getMessage();
		}
	}
	
	public void getClients(ArrayList<Client> client)
	{	
		client = new ArrayList<Client>();
		
		for(int x = 0; x < m_clients.size(); x++)
		{
			Client c = m_clients.get(x);
			client.add(new Client(c.getClientID(), c.getClientName(), c.getClientDescription()));
		}
	}
	
	public void getProjects(int id, ArrayList<Project> project)
	{
		project = new ArrayList<Project>(); 
		
		for(int y = 0; y < m_projects.size(); y++)
		{
			Project p = m_projects.get(y);
			if(p.getClientID() == id)
			{
				try
				{
					project.add (new Project(p.getID(), p.getName(), p.getDescription(), p.getHours(), p.getClientID(), p.isHourly()));
				}
				catch(MyTimeException e)
				{
					e.getMessage();
				}
			}
		}
	}
	
}

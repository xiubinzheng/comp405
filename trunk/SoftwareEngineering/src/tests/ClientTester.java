package tests;

import java.awt.List;
import java.util.ArrayList;

import project.Project;

import client.Client;

import junit.framework.TestCase;

public class ClientTester extends TestCase 
{
	private ArrayList<Client> m_clientList = new ArrayList<Client>();
	private ArrayList<Project> m_projectList = new ArrayList<Project>();
	
	protected void setUp() throws Exception 
	{
		
		m_clientList.add(new Client(1234, "Drugco", "Evil corp bent on world domination."));
		m_clientList.add(new Client(1235, "Medex", "Almost Evil corp bent on world domination."));
		m_clientList.add(new Client(1236, "Pillshere", "Very Evil corp bent on world domination."));
		m_clientList.add(new Client(1237, "IneedPills", "Slightly Evil corp bent on world domination."));
		
		
		Project m_testProject1 = new Project();
		Project m_testProject2 = new Project();
		Project m_testProject3 = new Project();
		Project m_testProject4 = new Project();
		
		for(int i = 0; i < 4; i++)
		{
			m_clientList.get(i).addProject(m_testProject1);
			m_clientList.get(i).addProject(m_testProject2);
			m_clientList.get(i).addProject(m_testProject3);
			m_clientList.get(i).addProject(m_testProject4);
		}
	}

	protected void tearDown() throws Exception 
	{
		super.tearDown();
		m_clientList.clear();
	}

	public void testMyCode() 
	{
		int testVal = 1234;
		for(int i = 0; i < m_clientList.size(); i++)
		{
			assertTrue(m_clientList.get(i).getClientID() == testVal);
			testVal++;
		}
		
		for(int j = 0; j < m_clientList.size(); j++)
		{
			m_clientList.get(j).getProjectList(m_projectList);
			assertTrue(!m_projectList.isEmpty());
		}
		
		assertTrue(m_clientList.get(2).toString().equals("1236 Pillshere Very Evil corp bent on world domination."));
		
	}
}

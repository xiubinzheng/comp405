package tests;

import java.util.ArrayList;

import client.Client;

import junit.framework.TestCase;

public class ClientTester extends TestCase 
{
	private ArrayList<Client> m_clientList = new ArrayList<Client>();
	
	protected void setUp() throws Exception 
	{
		
		m_clientList.add(new Client(1234, "Drugco", "Evil corp bent on world domination."));
		m_clientList.add(new Client(1235, "Medex", "Almost Evil corp bent on world domination."));
		m_clientList.add(new Client(1236, "Pillshere", "Very Evil corp bent on world domination."));
		m_clientList.add(new Client(1237, "IneedPills", "Slightly Evil corp bent on world domination."));
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
		
		assertTrue(m_clientList.get(0).getClientName().equals("Drugco"));
		assertTrue(m_clientList.get(1).getClientName().equals("Medex"));
		assertTrue(m_clientList.get(2).getClientName().equals("Pillshere"));
		assertTrue(m_clientList.get(3).getClientName().equals("IneedPills"));
		
		assertTrue(m_clientList.get(0).getClientDescription().equals("Evil corp bent on world domination."));
		assertTrue(m_clientList.get(1).getClientDescription().equals("Almost Evil corp bent on world domination."));
		assertTrue(m_clientList.get(2).getClientDescription().equals("Very Evil corp bent on world domination."));
		assertTrue(m_clientList.get(3).getClientDescription().equals("Slightly Evil corp bent on world domination."));
	}
}

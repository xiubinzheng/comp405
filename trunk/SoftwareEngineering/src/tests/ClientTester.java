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
			assertTrue(m_clientList.get(0).getClientID() == testVal);
			testVal++;
		}
	}
}

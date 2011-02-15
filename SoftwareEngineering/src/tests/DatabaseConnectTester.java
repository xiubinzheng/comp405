package tests;

import junit.framework.TestCase;
import database.DatabaseConnect;
import exceptions.MyTimeException;

public class DatabaseConnectTester extends TestCase
{
	String m_database = "myTimeDB.s3db";
	DatabaseConnect m_connect;

	protected void setUp() throws Exception
	{
		super.setUp();
		m_connect = DatabaseConnect.getDatabaseInstance(m_database);
	}

	protected void tearDown() throws Exception
	{
		super.tearDown();
	}
	
	public void testConnection() throws MyTimeException
	{
		assertTrue(m_connect != null);
		
		m_connect.open();
		assertTrue(m_connect.isOpen()== true);
		m_connect.close();
		assertTrue(m_connect.isOpen() == false);
	}
	
	
	
}

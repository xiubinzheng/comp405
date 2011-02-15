package tests;

import java.sql.ResultSet;

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
	
	public void testConnection()
	{
		assertTrue(m_connect != null);
		
		try
		{
			m_connect.open();
		}
		catch(Exception e)
		{
			assertTrue(false);
		}
		assertTrue(m_connect.isOpen()== true);
		
		//m_connect.update("");
		ResultSet s;
		try
		{
			s = m_connect.execute("SELECT * FROM myTimeClients;");
		}
		catch (Exception e)
		{
			assertTrue(false);
		}
		
		try
		{
			m_connect.close();
		}
		catch(Exception e)
		{
			assertTrue(false);
		}
		assertTrue(m_connect.isOpen() == false);
	}
	
	
	
}

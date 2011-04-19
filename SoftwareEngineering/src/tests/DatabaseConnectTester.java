package tests;


import java.sql.ResultSet;

import models.Client;

import junit.framework.TestCase;
import database.DBConnector;
import exceptions.MyTimeException;


public class DatabaseConnectTester extends TestCase
{
	String m_database = "myTimeDB.s3db";
	DBConnector m_connect;
	
	Client testClient;

	protected void setUp() throws Exception
	{
		super.setUp();
		m_connect = DBConnector.getDatabaseInstance(m_database);
		testClient = new Client(1001, "Cohen", "Proffesor");
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
			m_connect.update("INSERT INTO myTimeClients VALUES(1001, 'Cohen', 'Proffesor')");
		}
		catch (Exception e)
		{
			assertTrue(false);
		}
		try
		{
			m_connect.update("DELETE FROM myTimeClients WHERE client_ID = 1001");
		}
		catch(Exception e)
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

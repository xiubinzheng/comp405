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
	}

	protected void tearDown() throws Exception
	{
		super.tearDown();
	}

	public void testConnection() throws MyTimeException
	{
		m_connect.open();
		m_connect.close();
	}
}

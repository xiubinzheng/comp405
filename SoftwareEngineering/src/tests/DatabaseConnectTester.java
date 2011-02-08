package tests;

import junit.framework.TestCase;
import database.DatabaseConnect;

public class DatabaseConnectTester extends TestCase
{
	String m_database = "myTimeDB.s3db";
	DatabaseConnect m_connect = new DatabaseConnect();

	protected void setUp() throws Exception
	{
		super.setUp();
	}

	protected void tearDown() throws Exception
	{
		super.tearDown();
	}

	public void testConnection()
	{
		assertTrue(DatabaseConnect.open());
		assertTrue(DatabaseConnect.close());
	}
}

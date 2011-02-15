package tests;

import client.Client;
import database.DatabaseConnect;
import junit.framework.TestCase;

public class DatabaseReporterTest extends TestCase {
	
	DatabaseConnect m_databaseConnection;
	
	protected void setUp() throws Exception
	{
		super.setUp();
		m_databaseConnection = DatabaseConnect.getDatabaseInstance("myTimeDB.s3db");
		//table populated
	}

	protected void tearDown() throws Exception
	{
		super.tearDown();
		
	}
	
	public void test()
	{
		
	}
}

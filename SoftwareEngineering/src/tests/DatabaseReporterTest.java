package tests;

import client.Client;
import database.DatabaseConnect;
import junit.framework.TestCase;

import Controls.DatabaseReporter;
public class DatabaseReporterTest extends TestCase {
	
	DatabaseReporter dr; 
	
	protected void setUp() throws Exception
	{
		super.setUp();
		//table populated
		dr = DatabaseReporter.getReporterInstance("myTimeDB.s3db");
	}

	protected void tearDown() throws Exception
	{
		super.tearDown();
		
	}
	
	public void test()
	{
		String s = "";
		try
		{
			s = dr.reportClient("testing.css", "smiley.jpg"); 
			//System.out.println(s);
			assertTrue(s.contains("html"));
		}
		catch(Exception e)
		{
			 assertTrue(false);
		}
		
		
	}
	
	
}

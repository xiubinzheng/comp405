package tests;

import client.Client;
import database.DatabaseConnect;
import junit.framework.TestCase;
import java.io.*;

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
			Writer writer = null;
			
			s = dr.reportClient("cssFile.css", "smiley.jpg");
			
			File f = new File("testReport.html");
			writer = new BufferedWriter(new FileWriter(f));
			writer.write(s);
			
			writer.close();
			assertTrue(s.contains("html"));
		}
		catch(Exception e)
		{
			 assertTrue(false);
		}
		
	}
	
}

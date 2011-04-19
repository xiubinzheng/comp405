package tests;

import database.DBConnector;
import junit.framework.TestCase;
import java.io.*;
import java.util.Date;

import models.Client;


import Controls.DatabaseReporter;
public class DatabaseReporterTest extends TestCase {
	
	DatabaseReporter dr; 
	
	protected void setUp() throws Exception
	{
		Date d1 = new Date();
		Date d2 = new Date();
		
		super.setUp();
		//table populated
		dr = DatabaseReporter.getReporterInstance(d1, d2);
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
			
			s = dr.generateReport("cssFile.css", "smiley.jpg");
			
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

package tests;

import junit.framework.TestCase;
import java.io.*;
import java.util.Date;

import utilities.*;


public class DatabaseReporterTest extends TestCase {
	
	HTMLReporter dr; 
	HTMLClientReporter cr;
	
	@SuppressWarnings("deprecation")
	protected void setUp() throws Exception
	{
		Date d1 = new Date();
		Date d2 = new Date();
		d1.setYear(60);
		d2.setYear(120);
		
		
		super.setUp();
		//table populated
		dr = HTMLReporter.getReporterInstance(d1, d2);
		cr = HTMLClientReporter.getReporterInstance();
	}

	protected void tearDown() throws Exception
	{
		super.tearDown();
		
	}
	
	public void test()
	{
		String s = "";
		String clientReport = "";
		try
		{
			Writer writer = null;
			
			s = dr.generateReport("cssFile.css", "smiley.jpg");
			
			File f = new File("testReport.html");
			writer = new BufferedWriter(new FileWriter(f));
			writer.write(s);
			
			writer.close();
			assertTrue(s.contains("html"));
			
			File clients = new File("testClientReport.html");
			clientReport = cr.generateReport("cssFile.css", "smiley.jpg");
			Writer writer2 = new BufferedWriter(new FileWriter(clients));
			writer2.write(clientReport);
			writer2.close();
		}
		catch(Exception e)
		{
			 assertTrue(false);
		}
		
	}
	
}

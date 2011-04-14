package tests;

import models.TimeInterval;
import junit.framework.TestCase;

public class IntervalTest extends TestCase
{
	protected void setUp() throws Exception
	{
		super.setUp();
		
	}
	protected void tearDown() throws Exception
	{
		super.tearDown();
	}

	public void testMyCode()
	{
		TimeInterval ti = new TimeInterval();
		ti.start();
		try
		{
			Thread.sleep(1000);
		}
		catch(InterruptedException ie)
		{
			assert false;
		}
		ti.stop();
		if(ti.getStart().compareTo(ti.getStop()) <= 0)
			assert false;
	}
}
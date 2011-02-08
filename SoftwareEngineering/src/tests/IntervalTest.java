package tests;

import junit.framework.TestCase;
import project.TimeInterval;
import java.util.*;

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
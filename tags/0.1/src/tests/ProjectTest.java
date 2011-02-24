package tests;

import exceptions.MyTimeException;
import project.Project;
import project.TimeInterval;
import junit.framework.TestCase;

/**
 * A tester for the Project class that adds a project and reads a project from
 * the database.
 */
public class ProjectTest extends TestCase
{
	Project	proj	= new Project();
	TimeInterval tInt1 = new TimeInterval();
	TimeInterval tInt2 = new TimeInterval();
	TimeInterval tInt3 = new TimeInterval();
	TimeInterval tInt4 = new TimeInterval();

	protected void setUp() throws Exception
	{
		super.setUp();

		// to test the deleteTime() method
		tInt1.start();
		Thread.sleep(800);
		tInt1.stop();
		proj.addTime(tInt1);

		tInt2.start();
		Thread.sleep(1000);
		tInt2.stop();
		proj.addTime(tInt2);

		tInt3.start();
		Thread.sleep(1200);
		tInt3.stop();
		proj.addTime(tInt3);

		tInt4.start();
		Thread.sleep(1400);
		tInt4.stop();
		proj.addTime(tInt4);
	}

	protected void tearDown() throws Exception
	{
		super.tearDown();
	}

	/**
	 * Test for the project description and name length.
	 */
	public void testMyCode()
	{
		String name = "";
		for (int x = 0; x < 50; x++)
			name += "a";
		String desc = "";
		for (int x = 0; x < 255; x++)
			desc += "a";
		boolean caughtException = false;
		try
		{
			proj = new Project(0, name, desc, 877618735, 98734989, true);
			proj.toString();
		}
		catch (MyTimeException mte)
		{
			caughtException = true;
			System.out.println(mte);
		}

		assert caughtException;

	}

	/**
	 * Test for the addTime() method and its features.
	 */
	public void testAddTimeInterval() throws InterruptedException
	{
		boolean caughtException = false;
		TimeInterval tInterval = new TimeInterval();

		try
		{
			proj.addTime(tInterval);
		}
		catch (MyTimeException mte)
		{
			caughtException = true;
			System.out.println(mte);
		}
		assert caughtException;

		caughtException = false;
		tInterval.start();
		try
		{
			proj.addTime(tInterval);
		}
		catch (MyTimeException mte)
		{
			caughtException = true;
			System.out.println(mte);
		}
		assert caughtException;

		caughtException = false;
		Thread.sleep(1000);
		tInterval.stop();
		try
		{
			proj.addTime(tInterval);
		}
		catch (MyTimeException mte)
		{
			caughtException = true;
			System.out.println(mte);
		}
		assert caughtException;
	}

	/**
	 * Test for deleteTime() method.
	 */
	public void testDeleteTimeInterval() throws MyTimeException
	{
		boolean caughtException = false;
		try
		{
			proj.deleteTime(tInt2);
		}
		catch (MyTimeException mte)
		{
			caughtException = true;
			System.out.println(mte);
		}
		assert caughtException;

		caughtException = false;
		try
		{
			proj.deleteTime(tInt2);
		}
		catch (MyTimeException mte)
		{
			caughtException = true;
			System.out.println(mte);
		}
		assert caughtException;

		caughtException = false;
		Project emptyProj = new Project();
		try
		{
			emptyProj.deleteTime(tInt3);
		}
		catch (MyTimeException mte)
		{
			caughtException = true;
			System.out.println(mte);
		}
		assert caughtException;

	}
}
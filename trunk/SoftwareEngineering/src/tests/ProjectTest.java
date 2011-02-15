package tests;

import exceptions.MyTimeException;
import project.Project;
import junit.framework.TestCase;

/*
 * A tester for the Project class that adds a project
 * and reads a project from the database.
 */
public class ProjectTest extends TestCase
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
		String name = "";
		for (int x = 0; x < 50; x++)
			name += "a";
		String desc = "";
		for (int x = 0; x < 256; x++)
			desc += "a";
		boolean caughtException = false;
		try
		{
			Project proj = new Project(0, name, desc, 877618735, 98734989, true);
			System.out.println(proj.toString());
		}
		catch (MyTimeException mte)
		{
			caughtException = true;
			System.out.println(mte);
		}
		
		assert caughtException;
	
	}
}
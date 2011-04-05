package project;

import java.util.ArrayList;

import exceptions.MyTimeException;

/**
 * {@code Project} creates a Project class to track specific information about a
 * single project as related to a single client.
 */

public class Project
{
	private int						m_ID;
	private String					m_name;
	private String					m_desc;
	private boolean					m_complete;
	private int						m_clientID;
	private boolean					m_hourly;
	private ArrayList<TimeInterval>	m_timeList		= new ArrayList<TimeInterval>();

	private final int				m_nameLength	= 50;
	private final int				m_descLength	= 255;
	
	private static int m_defaultID = -1;

	public Project()
	{
		m_ID = 0;
		m_name = "";
		m_desc = "";
		m_complete = false;
		m_clientID = 0;
		m_hourly = false;
	}

	public Project(String name, String desc, int clientID,
			boolean hourly) throws MyTimeException
	{
		m_ID = m_defaultID--;
		if (name.length() <= m_nameLength)
			m_name = name;
		else
			throw new MyTimeException("Name Too Long");
		if (desc.length() <= m_descLength)
			m_desc = desc;
		else
			throw new MyTimeException("Description Too Long");
		m_clientID = clientID;
		m_hourly = hourly;
		m_complete = false;
	}
	
	public Project(int id, String name, String desc, int clientID,
			boolean hourly) throws MyTimeException
	{
		m_ID = id;
		if (name.length() <= m_nameLength)
			m_name = name;
		else
			throw new MyTimeException("Name Too Long");
		if (desc.length() <= m_descLength)
			m_desc = desc;
		else
			throw new MyTimeException("Description Too Long");
		m_clientID = clientID;
		m_hourly = hourly;
		m_complete = false;
	}

	public String getDescription()
	{
		return m_desc;
	}

	/**
	 * Changes the project's description also checks if the description is
	 * within the required length of 255 characters
	 * 
	 * @param desc
	 * @throws MyTimeException
	 */
	public void setDescription(String desc) throws MyTimeException
	{
		if (desc.length() <= m_descLength)
			m_desc = desc;
		else
			throw new MyTimeException("Description Too Long");
	}

	public void getTimeIntervals(ArrayList<TimeInterval> in)
	{
		in.clear();
		in.addAll(m_timeList);
	}

	public int getClientID()
	{
		return m_clientID;
	}

	public boolean isHourly()
	{
		return m_hourly;
	}

	public int getProjectID()
	{
		return m_ID;
	}

	public String getName()
	{
		return m_name;
	}

	/**
	 * Sets Project name with a String and checks if the name is within the
	 * length of 50 characters.
	 * 
	 * @param name
	 * @throws MyTimeException
	 */
	public void setName(String name) throws MyTimeException
	{
		if (name.length() <= m_nameLength)
			m_name = name;
		else
			throw new MyTimeException("Name Too Long");
	}
	
	/*
	 * Sets project ID should only be used by the manager.
	 */
	public void setID(int id)
	{
		assert(m_ID <= 0);	
		m_ID = id;
	}

	public boolean isComplete()
	{
		return m_complete;
	}

	/**
	 * When function is called project status is changed from incomplete to
	 * complete.
	 */
	public void complete()
	{
		m_complete = true;
	}
	
	public void addTimeWorkDamnYou(TimeInterval tInterval)
	{
		m_timeList.add(tInterval);
	}

	/**
	 * Adds an interval of time to the list.
	 * 
	 * @throws MyTimeException
	 */
	public void addTime(TimeInterval tInterval) throws MyTimeException
	{
		if (tInterval.getStart() == null)
		{
			throw new MyTimeException(
					"TimeInterval does not contain a start time!");
		}
		else if (tInterval.getStop() == null)
		{
			throw new MyTimeException(
					"TimeInterval does not contain an end time!");
		}
		else if (tInterval.getStart().compareTo(tInterval.getStop()) >= 0)
		{
			throw new MyTimeException(
					"End time does not come after the Start time!");
		}
		else
		{
			m_timeList.add(tInterval);
		}
	}

	/**
	 * Used to manually delete a time from the interval list.*
	 * 
	 * @throws MyTimeException
	 * @param tInterval
	 */
	public void deleteTime(TimeInterval tInterval) throws MyTimeException
	{
		if (m_timeList.isEmpty())
		{
			throw new MyTimeException("There are no time intervals to delete!");
		}

		else
		{
			int index = -1;
			for (int i = 0; i < m_timeList.size(); i++)
			{
				if (tInterval == m_timeList.get(i))
				{
					index = i;
					m_timeList.remove(index);
					break;
				}
			}

			if (index == -1)
			{
				throw new MyTimeException(
						"Time Interval does not exist in the list!");
			}
		}
	}

	/**
	 * Overrides the toString() method to return all the project attributes as a
	 * string.
	 * 
	 * @return projectAttributes
	 */
	public String toString()
	{
		String projectAttributes = "";
		projectAttributes += "ID: " + m_ID + "\n";
		projectAttributes += "Name: " + m_name + "\n";
		projectAttributes += "Description: " + m_desc + "\n";
		if (m_complete)
		{
			projectAttributes += "Status: complete \n";
		}
		else
		{
			projectAttributes += "Status: incomplete  \n";
		}
		projectAttributes += "Client ID: " + m_clientID + "  \n";
		if (m_hourly)
		{
			projectAttributes += "Rate: hourly  \n";
		}
		else
		{
			projectAttributes += "Rate: fixed  \n";
		}

		return projectAttributes;
	}

}
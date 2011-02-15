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
	private int						m_hours;
	private int						m_clientID;
	private boolean					m_hourly;
	private ArrayList<TimeInterval>	m_timeList		= new ArrayList<TimeInterval>();

	private final int				m_nameLength	= 50;
	private final int				m_descLength	= 255;

	public Project()
	{
		m_ID = 0;
		m_name = "";
		m_desc = "";
		m_complete = false;
		m_hours = 0;
		m_clientID = 0;
		m_hourly = false;
	}

	public Project(int id, String name, String desc, int hours, int clientID,
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
		m_hours = hours;
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

	public int getClientID()
	{
		return m_clientID;
	}

	public boolean isHourly()
	{
		return m_hourly;
	}

	public int getHours()
	{
		return m_hours;
	}

	public int getID()
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
}
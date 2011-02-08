package project;

import exceptions.MyTimeException;


public class Project
{
	private int m_ID;
	private String m_name;
	private String m_desc;
	private boolean m_complete;
	private int m_hours;
	private int m_clientID;
	private boolean m_hourly;
	
	private final int m_nameLength = 50;
	private final int m_descLength = 255;
	
	
	public Project(int id, String name, String desc, int hours, int clientID, boolean hourly) throws MyTimeException
	{
		m_ID=id;
		if(name.length()<=m_nameLength)
			m_name=name;
		else
			throw new MyTimeException("Name Too Long");
		if(desc.length()<=m_descLength)
			m_desc=desc;
		else
			throw new MyTimeException("Description Too Long");
		m_hours=hours;
		m_clientID=clientID;
		m_hourly=hourly;
	}
	public String getDescription()
	{
		return m_desc;
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
	public void complete()
	{
		m_complete=true;
	}
}
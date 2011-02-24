package project;

import java.util.Date;

public class TimeInterval
{
	Date m_start;
	Date m_stop;
	
	public TimeInterval()
	{
		m_start = null;
		m_stop = null;
	}
	
	public void start()
	{
		m_start = new Date();
	}
	
	public void stop()
	{
		m_stop = new Date();
	}
	
	public Date getStart()
	{
		return m_start;
	}
	
	public Date getStop()
	{
		return m_stop;
	}
}
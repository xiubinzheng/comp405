/**
 * TODO: Javadocs class description
 */

package project;

import java.util.Date;

public class TimeInterval
{
	private int			m_timeID;
	private int			m_projectID;
	Date				m_start;
	Date				m_stop;

	private static int	m_defaultTimeID	= -1;

	public TimeInterval()
	{
		m_start = null;
		m_stop = null;
		m_timeID = m_defaultTimeID--;

		// revisit later
		m_projectID = 0;
	}

	public TimeInterval(int projectID)
	{
		m_start = null;
		m_stop = null;
		m_timeID = m_defaultTimeID--;
		m_projectID = projectID;
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

	public int getTimeID()
	{
		return m_timeID;
	}

	public int getProjectID()
	{
		return m_projectID;
	}

	/**
	 * 
	 * @param timeID
	 */
	public void setTimeID(int timeID)
	{
		assert m_timeID <= 0;
		m_timeID = timeID;
	}

	/**
	 * 
	 * @param projectID
	 */
	public void setProjectID(int projectID)
	{
		assert m_projectID <= 0;
		m_projectID = projectID;
	}
	public String toString()
	{
		return "Start : "+m_start.toString()+" End "+m_stop.toString();
	}
}
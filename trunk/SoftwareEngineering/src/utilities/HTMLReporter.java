package utilities;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.sql.ResultSet;

import models.*;

/**
 * 
 * Database Reporter Class
 * 
 * Given a Date range, compile an html string which is a report on every client,
 * project, and time interval within that date range.
 * 
 * This class returns a String filled with all sorts of useful HTML information
 * for all of your various reporting needs.
 */

public class HTMLReporter
{
	// private DatabaseConnect m_databaseConnection;
	private static HTMLReporter	m_singleton;

	private String				g_htmlString	= "";

	private Date				g_dateSpanStart	= new Date();
	private Date				g_dateSpanStop	= new Date();
	private long				g_totalSeconds	= 0;			// measured in
																// seconds

	public HTMLReporter()
	{
		// construct

		g_dateSpanStart.getTime();
		g_dateSpanStart.setYear(80); // set to a time before all other times

		g_dateSpanStop.getTime();

	}

	public HTMLReporter(Date start, Date stop)
	{
		// construct
		g_dateSpanStart = start;
		g_dateSpanStop = stop;
	}

	/**
	 * 
	 * Returns a singleton instance of the DatabaseReporter class.
	 * 
	 */
	public static HTMLReporter getReporterInstance(Date start , Date stop)
	{
		if (m_singleton == null)
		{
			m_singleton = new HTMLReporter(start, stop);
		}
		return m_singleton;
	}

	public void setTimes(Date start , Date stop)
	{
		g_dateSpanStart = start;
		g_dateSpanStop = stop;
	}

	/**
	 * Returns a String with HTML formatting of the current entries in
	 * myTimeClients.
	 * 
	 * @param cssFile The location to the css File
	 * @param imageFile The location to the image File
	 */
	public String generateReport(String cssFile , String imageFile)
	{
		g_htmlString += "<html>";
		g_htmlString += "<head>";
		g_htmlString += "<link rel = \"stylesheet\" href = \"" + cssFile
				+ "\" type = \"text/css\">\n";
		g_htmlString += "<title>Database Reporter</title>";
		g_htmlString += "</head>";
		g_htmlString += "<img class = \"logo\" src = \"" + imageFile
				+ "\" alt = \"\"/>\n";
		g_htmlString += "<H2 class=\"r_Title\">Client Report</H2>";
		g_htmlString += "<table class=\"report\">";

		generateClientTable();

		g_htmlString += "</table>\n";
		g_htmlString += "</html>\n";
		// prints the HTML for the Report to the console
		// System.out.print(g_htmlString);
		return g_htmlString;
	}

	/**
	 * Collects the client information
	 */
	private void generateClientTable()
	{
		ClientDBManager m_manage = ClientDBManager.getInstance();
		try
		{
			m_manage.initializeDB();
		}
		catch (MyTimeException e)
		{
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

		ArrayList<Client> m_client = new ArrayList<Client>();

		m_client.clear();
		m_manage.getClients(m_client);
		filterClients(m_client);

		System.out.println(m_client.size());
		for (Client c : m_client)
		{
			outputClientHeader();
			outputClient(c);
			generateProjectTable(c);

		}
	}

	/**
	 * Collects the project information for this client
	 * 
	 * @param c The client object
	 */
	private void generateProjectTable(Client c)
	{
		ArrayList<Project> m_project = new ArrayList<Project>();
		ArrayList<TimeInterval> t = new ArrayList<TimeInterval>();
		long m_totalProjectSeconds = 0;// measured in seconds

		m_project.clear();
		c.getProjectList(m_project);

		for (Project p : m_project)
		{
			outputProject(p);

			p.getTimeIntervals(t);
			if (!t.isEmpty())
			{
				outputProjectHeader();
				m_totalProjectSeconds = generateTimeTable(p);
				outputTotalTimeInterval(m_totalProjectSeconds);
				g_totalSeconds += m_totalProjectSeconds;
			}
		}

		// TODO output total hours
		g_totalSeconds += m_totalProjectSeconds;
	}

	/**
	 * Collects the Time Interval information for this project
	 * 
	 * @param p The project object
	 */
	private long generateTimeTable(Project p)
	{
		ArrayList<TimeInterval> m_time = new ArrayList<TimeInterval>();
		long i = 0;

		m_time.clear();
		p.getTimeIntervals(m_time);

		outputTimeHeader();

		for (TimeInterval t : m_time)
		{
			System.out.println("Range: " + g_dateSpanStart + " "
					+ g_dateSpanStop);
			System.out.println(t.getStart() + " " + t.getStop());
			if (t.getStart().getTime() >= g_dateSpanStart.getTime()
					&& t.getStop().getTime() <= g_dateSpanStop.getTime())
			{
				outputTimeInterval(t);
				i += (t.getStop().getTime() - t.getStart().getTime());
			}
		}
		return i / 1000;// return the number of seconds
	}

	// the purpose of this comment is to waste precious time so i dont have to
	// think about
	// doing the above method any more than i have to currently, that is to say
	// that i don't
	// really want to start it with 5 minutes left in the class period.
	private void outputTotalHours(String time)
	{
		g_htmlString += "<td>" + time + "</td>\n";
	}

	private void outputLeftSpan(int span)
	{
		if (span > 0)
			g_htmlString += "<td colspan=\"" + span + "\"/>\n";
	}

	private void outputClientHeader()// int rightSpan
	{
		g_htmlString += "<tr><td class=\"header\"><b>Client ID</b></td>";
		g_htmlString += "<td class=\"header\"><b>Client Name</b></td>";
		g_htmlString += "<td class=\"header\" colspan=\"20\"><b>Client Description</b></td></tr>";
	}

	private void outputClient(Client c)
	{
		g_htmlString += "<tr><td class=\"c_ID\">" + c.getClientID() + "</td>";
		g_htmlString += "<td class=\"c_Name\">" + c.getClientName() + "</td>";
		g_htmlString += "<td class=\"c_Description\" colspan=\"7\">"
				+ c.getClientDescription() + "</td></tr>";
	}

	private void outputProjectHeader()
	{
		g_htmlString += "<tr><td class=\"header\">  </td>";
		g_htmlString += "<td class=\"header\">Project ID</td>";
		g_htmlString += "<td class=\"header\" colspan=\"5\">Project Name</td>";
		g_htmlString += "<td class=\"header\" colspan=\"10\">Project Description</td></tr>";
	}

	private void outputProject(Project p)// TODO
	{
		g_htmlString += "<tr><td>  </td>";
		g_htmlString += "<td class=\"p_ID\">" + p.getProjectID() + "</td>\n";
		g_htmlString += "<td class=\"p_Name\" colspan=\"5\">" + p.getName()
				+ "</td>\n";
		g_htmlString += "<td class=\"p_Description\" colspan=\"5\">"
				+ p.getDescription() + "</td></tr>";
	}

	private void outputTimeHeader()
	{
		g_htmlString += "<tr><td class=\"header\">  </td>";
		g_htmlString += "<td>  </td>";
		g_htmlString += "<td class=\"header\" colspan=\"5\">Start</td>";
		g_htmlString += "<td class=\"header\" colspan=\"5\">Stop</td>";
		g_htmlString += "<td class=\"header\" colspan=\"5\">Duration</td>\n</tr>";
	}

	private void outputTimeInterval(TimeInterval t)
	{
		g_htmlString += "<tr><td>  </td>";
		g_htmlString += "<td>  </td>";
		g_htmlString += "<td class=\"t_From\" + colspan=\"5\"" + "\">"
				+ formatTime(t.getStart()) + "</td>\n";
		g_htmlString += "<td class=\"t_To\" + colspan=\"5\"" + "\">"
				+ formatTime(t.getStop()) + "</td>\n";
		g_htmlString += "<td class=\"t_Duration" + "\">"
				+ getDuration(t.getStart(), t.getStop()) + "</td>\n</tr>";
	}

	private void outputTotalTimeInterval(long tmSeconds)
	{
		g_htmlString += "<tr><td>  </td>";
		g_htmlString += "<td>  </td>";
		g_htmlString += "<td>  </td>";
		g_htmlString += "<td>  </td>";
		g_htmlString += "<td class=\"t_totalTime" + "\">"
				+ "Project Total Time: " + (tmSeconds / 3600) + ":"
				+ ((tmSeconds % 3600) / 60) + "</td>\n</tr>";
	}

	// DD/MM/YYYY HH:MM
	private String formatTime(Date d)
	{
		String ret = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");

		ret += sdf.format(d);
		return ret;
	}

	private String getDuration(Date d1 , Date d2)
	{
		String ret = "";
		int d2min, d1min, d2h, d1h;
		d2h = d2.getHours();
		d1h = d1.getHours();
		d2min = d2.getMinutes();
		d1min = d1.getMinutes();

		long start = d1.getTime();
		long stop = d2.getTime();

		long hours = (stop - start) / 3600000;
		if (hours != 0)
		{
			ret += (hours + " h : ");
		}

		long minutes = (stop - start) / 60000 - (hours * 60);
		if (minutes != 0)
		{
			ret += (minutes + " min");
		}

		return ret;
	}
	
	/**
	 * 
	 * @param clients
	 */
	private void filterClients(ArrayList<Client> clients)
	{
		ArrayList<Project> projects = new ArrayList<Project>();
		ArrayList<TimeInterval> times = new ArrayList<TimeInterval>();
		for(int i=0; i<clients.size(); )
		{
			clients.get(i).getProjectList(projects);
			for(int j=0; j<projects.size(); j++)
			{
				projects.get(j).getTimeIntervals(times);
				if (times.isEmpty())
				{
					clients.get(i).removeProject(projects.get(j).getProjectID());
				}
			}
			clients.get(i).getProjectList(projects);
			if (projects.isEmpty())
			{
				clients.remove(i);
			}
			else
			{
				i++;
			}
		}
	}

	public void setStartDate(Date start)
	{
		g_dateSpanStart = start;
	}

	public void setStopDate(Date stop)
	{
		g_dateSpanStop = stop;
	}

	public Date getStartDate()
	{
		return g_dateSpanStart;
	}

	public Date getStopDate()
	{
		return g_dateSpanStop;
	}

}
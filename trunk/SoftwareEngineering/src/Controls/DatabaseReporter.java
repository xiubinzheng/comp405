package Controls;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.sql.ResultSet;

import project.Project;
import project.TimeInterval;

import client.Client;
import exceptions.MyTimeException;

import database.DatabaseConnect;

/**
 * 
 * Database Reporter Class
 * 
 * This class connects to the database and returns a String filled with all sorts of useful
 * HTML information for all of your various reporting needs.
 */

public class DatabaseReporter 
{
	private DatabaseConnect m_databaseConnection;
	private static DatabaseReporter m_singleton;
	
	
	private DatabaseReporter(String databaseName)
	{
		m_databaseConnection = DatabaseConnect.getDatabaseInstance(databaseName);
	}
	
	/**
	 * 
	 *  Returns a singleton instance of the DatabaseReporter class.
	 *  
	 */
	
	public static DatabaseReporter getReporterInstance(String databaseName)
	{
		if(m_singleton == null)
		{
			m_singleton = new DatabaseReporter(databaseName);
		}
		return m_singleton;
	}
	
	/**
	 * 	
	 * Returns a String with HTML formatting of the current entries in myTimeClients.
	 * 
	 */
	
	public String reportClient(String cssFile, String imageFile) throws MyTimeException
	{
		String s = "";
		s += "<html>";
		s += "<head>";
		s += "<link rel = \"stylesheet\" href = \"" + cssFile + "\" type = \"text/css\">\n";
		s += "<title>Database Reporter</title>";
		s += "</head>";
		s += "<img class = \"logo\" src = \"" + imageFile + "\" alt = \"\"/>\n";
		s += "<H2 class=\"r_Title\">Client Report</H2>";
		s += "<table class=\"report\">";

		try 
		{	
			Manager m = new Manager();
			m.initializeDB();
			ArrayList<Client> clientList = new ArrayList<Client>();
			m.getClients(clientList);
			//s += generateClientTable(clientList, 0 );
			
			for (Client c : clientList)
			{
				ArrayList<Project> projectList = new ArrayList<Project>();
				c.getProjectList(projectList);
				//String pString = generateProjectTable(projectList, 1); //to fix
				
				for (Project p : projectList)
				{
					ArrayList<TimeInterval> intervalList = new ArrayList<TimeInterval>();
					p.getTimeIntervals(intervalList);
					//String tString = generateTimeTable(intervalList, 2);
					//pString.replaceFirst("%timetable%", tString);
				}
				
				//s.replaceFirst("%project%", pString);
			}
			
		}
		catch(Exception e)
		{
			throw new MyTimeException("Could Not Obtain Result Set",e);
		}
		m_databaseConnection.close();		
		
		s += "</table>\n";
		s += "</html>\n";
		return s;
	}

	/*
	 * This Method compares a start_time and end_time.
	 * Generates the duration between time intervals along with total hours worked.
	 * Generates the proper time table format for the Database Report HTML file
	 */
	
	private void generateClientTable()
	{
		Manager m_manage = new Manager();
		ArrayList<Client> m_client = new ArrayList<Client>();
		
		m_client.clear();
		m_manage.getClients(m_client);
		
		for(Client c : m_client)
		{
			outputClient(c);
			generateProjectTable(c);
		}
	}
	
	private void generateProjectTable(Client c)
	{
		ArrayList<Project> m_project = new ArrayList<Project>();
		
		m_project.clear();
		c.getProjectList(m_project);
		
		for(Project p : m_project)
		{
			outputProject(p);
			generateTimeTable(p);
		}
	}
	
	private void generateTimeTable(Project p)
	{
		ArrayList<TimeInterval> m_time = new ArrayList<TimeInterval>();
		
		m_time.clear();
		p.getTimeIntervals(m_time);
		
		for(TimeInterval t : m_time)
		{
			//outputTimeInterval(t);
		}	
	}
	
	/*private String generateTimeTable(ArrayList<String> t_Start, ArrayList<String> t_End, String Date, int leftSpan) throws MyTimeException
	{
		String s = "";
		DateFormat df = DateFormat.getDateInstance();
		Date d1 = new Date();
	 	Date d2 = new Date();
		long minDif = 0;
		int totalTime = 0;
		
		
		try 
		{
			for(int i=0; i<t_Start.size(); i++)
			{
				s += "<tr>";
				s += outputLeftSpan(leftSpan);
				
				d1.setTime(df.parse(t_Start.get(i)).getTime());
				d2.setTime(df.parse(t_End.get(i)).getTime());
				minDif=d2.getTime()-d1.getTime();
				int minutes = (int)(minDif / 1000) / 60;
				totalTime += minutes;
				int hours = minutes/60;
				minutes %= 60;
				
				s += outputTimeHours( 	Date,
										t_Start.get(i),
										t_End.get(i), 
										formatHoursAndMinutes(hours, minutes),
										(i % 2 == 0));
				s += "</tr>";
			}
			s += outputLeftSpan(leftSpan) + outputLeftSpan(3);
			s += outputTotalHours(formatHoursAndMinutes(totalTime / 60, totalTime % 60));
		} 
		catch (ParseException e) 
		{
			throw new MyTimeException("Unable to read Start Times and End Times", e);
		}
		//Use for later implementing Time Hours row
		//s = outputTimeDate(Date, formatHoursAndMinutes(totalTime/60, totalTime%60)) + s;
		return s;
	}
	*/
	
	/*private String generateTimeTable(ArrayList<TimeInterval> intervalList, int leftSpan) throws MyTimeException
	{
		String s = "";
		DateFormat df = DateFormat.getDateInstance();
		Date d1 = new Date();
	 	Date d2 = new Date();
		long minDif = 0;
		int totalTime = 0;
		
		
		try 
		{
			for(int i=0; i < intervalList.size(); i++)
			{
				s += "<tr>";
				s += outputLeftSpan(leftSpan);
				
				d1.setTime(intervalList.get(i).getStart().getTime());
				d2.setTime(intervalList.get(i).getStop().getTime());
				minDif=d2.getTime()-d1.getTime();
				int minutes = (int)(minDif / 1000) / 60;
				totalTime += minutes;
				int hours = minutes/60;
				minutes %= 60;
				
				s += outputTimeHours( 	"Ronday",
										df.format(intervalList.get(i).getStart()),
										df.format(intervalList.get(i).getStop()), 
										formatHoursAndMinutes(hours, minutes),
										(i % 2 == 0));
				s += "</tr>";
			}
			s += outputLeftSpan(leftSpan) + outputLeftSpan(3);
			s += outputTotalHours(formatHoursAndMinutes(totalTime / 60, totalTime % 60));
		} 
		catch (Exception e) 
		{
			throw new MyTimeException("Unable to read Start Times and End Times", e);
		}
		//Use for later implementing Time Hours row
		//s = outputTimeDate(Date, formatHoursAndMinutes(totalTime/60, totalTime%60)) + s;
		return s;
	}
	*/
	
	//the purpose of this comment is to waste precious time so i dont have to think about doing the above method any more than i have to currently, that is to say that i don't really want to start it with 5 minutes left in the class period.
	private String outputTotalHours(String time)
	{
		return "<td>" + time + "</td>\n";
	}
	private String outputLeftSpan(int span)
	{
		String s = "";
		if(span > 0)
			s += "<td colspan=\"" + span + "\"/>\n";
		return s;
	}
	private String outputClientHeader(int rightSpan)
	{
		String s = "";
		s += "<td class=\"header\" >ID</td>\n";
		s += "<td class=\"header\">Name</td>\n";
		s += "<td class=\"header\" colspan=\"2\">Description</td>\n";
		return s;
	}
	private String outputClient(Client c)
	{
		String s = "";
		s += "<td class=\"c_ID\">" + c.getClientID() + "</td>\n";
		s += "<td class=\"c_Name\">" + c.getClientName() + "</td>\n";
		s += "<td class=\"c_Description\" colspan=\"2\">" + c.getClientDescription() + "</td>\n";
		return s;
	}
	private String outputProjectHeader()
	{
		String s = "";
		s+="<td class=\"header\">p_ID</td>\n";
		s+="<td class=\"header\">p_Type</td>\n";
		s+="<td class=\"header\" colspan=\"2\">p_Desc</td>\n";
		return s;
	}
	private String outputProject(Project p)//TODO
	{
		String s = "";
		//s+="<td colspan=\"1\"/>";
		s+="<td class=\"p_ID\">" + p.getProjectID() + "</td>\n";
		s+="<td class=\"p_Type\">"+ "Hourly or Salary" +"</td>\n";
		s+="<td class=\"p_Description\" colspan=\"2\">" + p.getDescription() + "</td>\n";
		return s;
	}
	private String outputTimeHoursHeader()
	{
		String s = "";
		s+="<td class=\"header\">Date</td>\n";
		s+="<td class=\"header\">From</td>\n";
		s+="<td class=\"header\">To</td>\n";
		s+="<td class=\"header\">Duration</td>\n";
		return s;
	}
	/*private String outputTimeIntveral(TimeInterval t)
	{
		
		String s = "";
		s += "<td class=\"t_Date"+"\">" + t+ "</td>\n";
		s += "<td class=\"t_From"+"\">" + t.getStart() + "</td>\n";
		s += "<td class=\"t_To"+"\">" + t.getStop() + "</td>\n";
		s += "<td class=\"t_Duration"+"\">" + (t.getStart() - t.getStop()) + "</td>\n";
		return s;
	}
	*/
	private String formatHoursAndMinutes(int hours, int minutes)
	{
		String t = "";
		if (hours != 0)
			if (hours == 1)
				t += "1 hour "; 
			else
				t += hours + " hours ";
		if (hours != 0 && minutes != 0)
			t += "and ";
		if (minutes != 0)
			if ( minutes == 1)
				t += "1 minute";
			else
				t += minutes + " minutes";
		return t;
	}
}


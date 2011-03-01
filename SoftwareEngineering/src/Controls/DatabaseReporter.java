package Controls;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.sql.ResultSet;
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
		s += "<html>\n";
		
		s += "<head>\n";
		s += "<link rel = \"stylesheet\" href = \"" + cssFile + "\" type = \"text/css\">\n";
		s += "</head>\n";


		s += "<img class = \"logo\" src = \"" + imageFile + "\" alt = \"\"/>\n";
		s += "<H2>Client Report</H2>";
		
		s += "<table class=\"report\">\n";
		ResultSet rs;
		m_databaseConnection.open();
		try 
		{
			rs = m_databaseConnection.execute("SELECT * FROM myTimeClients");
			//System.out.print(rs.getFetchSize()+ "\n");
			
			s += "<tr>\n";
			s += "<th class=\"header\">ID</th>\n";
			s += "<th class=\"header\">Name</th>\n";
			s += "<th class=\"header\">Description</th>\n";
			s += "</tr>\n";
			
			while(rs.next())
			{
				s += "<tr>\n";
				s += "<td class=\"ID\">" + rs.getString("Client_ID") + "</td>\n";
				s += "<td class=\"name\">" + rs.getString("Client_Name") + "</td>\n";
				s += "<td class=\"description\">" + rs.getString("Client_Description") + "</td>\n";
				s += "</tr>\n";
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
	private String outputClient(String c_Name, String c_Desc)
	{
		String s = "";
		s += "<td class=\"name\">" + c_Name + "</td>\n";
		s += "<td class=\"description\">" + c_Desc + "</td>\n";
		return s;
	}
	private String outputProject(String p_ID, String p_Type, String p_Desc, String p_Complete, double totalHours, String p_Start, String p_End)
	{
		String s = "";
		s += "<td class=\"ID\">" + p_ID + "</td>\n";
		s += "<td class=\"type\">" + p_Type + "</td>\n";
		s += "<td class=\"description\">" + p_Desc + "</td>\n";
		s += "<td class=\"complete\">" + p_Complete + "</td>\n";
		s += "<td class=\"total hours\">" + totalHours + "</td>\n";
		s += "<td class=\"start\">" + p_Start + "</td>\n";
		s += "<td class=\"end\">" + p_End + "</td>\n";
		return s;
	}
	private String outputTimeDate(String t_Date, String t_Total)
	{
		String s = "";
		s += "<td class=\"Date\">" + t_Date+ "</td>\n";
		s += "<td class=\"Total Hours\">" + t_Total + "</td>\n";
		return s;
	}
	private String outputTimeHours(String t_Start, String t_End, String t_Dur)
	{
		String s = "";
		s += "<td class=\"From\">" + t_Start + "</td>\n";
		s += "<td class=\"To\">" + t_End + "</td>\n";
		s += "<td class=\"Duration\">" + t_Dur + "</td>\n";
		return s;
	}
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
	/*
	 * This Method compares a start_time and end_time.
	 * Generates the duration between time intervals along with total hours worked.
	 * Generates the proper time table format for the Database Report HTML file
	 */
	private String generateTimeTable(ArrayList<String> t_Start, ArrayList<String> t_End, String Date) throws MyTimeException
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
				d1.setTime(df.parse(t_Start.get(i)).getTime());
				d2.setTime(df.parse(t_End.get(i)).getTime());
				minDif=d2.getTime()-d1.getTime();
				int minutes = (int)(minDif / 1000) / 60;
				totalTime += minutes;
				int hours = minutes/60;
				minutes %= 60;
				s += outputTimeHours( 	t_Start.get(i),
										t_End.get(i), 
										formatHoursAndMinutes(hours, minutes));
			}
		} 
		catch (ParseException e) 
		{
			throw new MyTimeException("Unable to read Start Times and End Times", e);
		}
		s = outputTimeDate(Date, formatHoursAndMinutes(totalTime/60, totalTime%60)) + s;
		return s;
	}
}


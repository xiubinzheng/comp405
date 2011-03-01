package Controls;

import java.util.ArrayList;
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
			throw new MyTimeException("Helpful Error Message");
		}
		m_databaseConnection.close();		
		
		s += "</table>\n";
		s += "</html>\n";
		return s;
	}
	private String outputClient(String c_Name, String c_Desc)
	{
		String s = "";
		s += "<tr>\n";
		s += "<td class=\"name\">" + c_Name + "</td>\n";
		s += "<td class=\"description\">" + c_Desc + "</td>\n";
		s += "</tr>\n";
		return s;
	}
	private String outputProject(String p_ID, String p_Type, String p_Desc, String p_Complete, double totalHours, String p_Start, String p_End)
	{
		String s = "";
		s += "<tr>\n";
		s += "<td class=\"ID\">" + p_ID + "</td>\n";
		s += "<td class=\"type\">" + p_Type + "</td>\n";
		s += "<td class=\"description\">" + p_Desc + "</td>\n";
		s += "<td class=\"complete\">" + p_Complete + "</td>\n";
		s += "<td class=\"total hours\">" + totalHours + "</td>\n";
		s += "<td class=\"start\">" + p_Start + "</td>\n";
		s += "<td class=\"end\">" + p_End + "</td>\n";
		s += "</tr>\n";
		return s;
	}
	private String outputTimeDate(String t_Date, String t_Total)
	{
		String s = "";
		s += "<tr>\n";
		s += "<td class=\"Date\">" + t_Date+ "</td>\n";
		s += "<td class=\"Total Hours\">" + t_Total + "</td>\n";
		return s;
	}
	private String outputTimeHours(String t_Start, String t_End, String t_Dir)
	{
		String s = "";
		s += "<tr>\n";
		s += "<td class=\"From\">" + t_Start + "</td>\n";
		s += "<td class=\"To\">" + t_End + "</td>\n";
		s += "<td class=\"Duration\">" + t_Dir + "</td>\n";
		return s;
	}
	private String generateTimeTable(ArrayList<String> t_Start, ArrayList<String> t_End, ArrayList<String> t_Dur, String Date)
	{
		String s = "";
		//Date d = Date.
		//t_Start.get(0);
		//outputTimeDate(Date,"" );
		return s;
	}
}


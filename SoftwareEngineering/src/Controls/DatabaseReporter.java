package Controls;

import java.sql.ResultSet;
import exceptions.MyTimeException;

import database.DatabaseConnect;

public class DatabaseReporter 
{
	private DatabaseConnect m_databaseConnection;
	private static DatabaseReporter m_singleton;
	
	private DatabaseReporter(String databaseName)
	{
		m_databaseConnection = DatabaseConnect.getDatabaseInstance(databaseName);
	}
	
	public static DatabaseReporter getReporterInstance(String databaseName)
	{
		if(m_singleton == null)
		{
			m_singleton = new DatabaseReporter(databaseName);
		}
		return m_singleton;
	}
	
	public String reportClient() throws MyTimeException
	{

		
		String s = "";
		s += "<Style Type = text/css>";
		s += "<td.strong{font-weight: bolder; color: black;}>";
		s += "</Style>";
		s += "<html>";
		s += "<table border=\"1\">";
		ResultSet rs;
		m_databaseConnection.open();
		try 
		{
			rs = m_databaseConnection.execute("SELECT * FROM myTimeClients");
			//System.out.print(rs.getFetchSize()+ "\n");
			
			s += "<tr>\n";
			s += "<th>ID</th><th>Name</th><th>Description</th>\n";
			s += "</tr>\n";
			
			while(rs.next())
			{
				s += "<tr>\n";
				s += "<td>" + rs.getString("Client_ID") + "</td>\n";
				s += "<td class=\"strong\">" + rs.getString("Client_Name") + "</td>\n";
				s += "<td>" + rs.getString("Client_Description") + "</td>\n";
				s += "</tr>\n";
			}
		}
		catch(Exception e)
		{
			throw new MyTimeException("Helpful Error Message");
		}
		m_databaseConnection.close();		
		
		s += "</table>";
		s += "</html>";
		
		return s;
	}
	
	private String queryClient()
	{
		
		return "";
	}
	
}


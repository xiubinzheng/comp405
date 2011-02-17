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
		
		s += "<html>";
		
		
		ResultSet rs;
		m_databaseConnection.open();
		try 
		{
			rs = m_databaseConnection.execute("SELECT * FROM myTimeClients;");
			while(rs.next())
			{
				//table formatting jank
				s += rs.getString("Client_ID") + " ";
				s += rs.getString("Client_Name") + " ";
				s += rs.getString("Client_Description") + " ";
			}
		}
		catch(Exception e)
		{
			throw new MyTimeException("Helpful Error Message");
		}
		m_databaseConnection.close();		
		
		
		s += "</html>";
		
		return s;
	}
	
	private String queryClient()
	{
		
		return "";
	}
	
}


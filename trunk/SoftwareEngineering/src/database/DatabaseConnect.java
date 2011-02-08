package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseConnect throws MyTimeException
{
	private String m_databaseName = "";
	
	public DatabaseConnect()
	{
	}
	
	public DatabaseConnect(String databaseName)
	{
		m_databaseName = databaseName;
	}
				
	public static open()
	{
		//Connect
		Class.forName("org.sqlite.JDBC");
		Connection dbConnection = DriverManager.getConnection("jdbc:sqlite:" + m_databaseName);
		
	}

	public static boolean close()
	{
		// TODO Auto-generated method stub
		return false;
	} 
	
	/*
	 * 
	//Execute update
	Statement s = null;
	              try
	              {
	                     s = c.createStatement();
	                     s.executeUpdate(cmd);
	              }
	              finally
	              {
	                     if ( s != null)
	                           s.close();
	              }

	//Execute Query

	              ResultSet rs = null;       
	              Statement s = null;
	              try
	              {
	                     s = c.createStatement();
	                     rs = s.executeQuery(cmd);
	              }
	              finally
	              {
	                     if ( s != null)
	                           s.close();
	              }
	                    return rs;
	 */
}

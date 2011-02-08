package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import exceptions.MyTimeException;

public class DatabaseConnect
{
	private String m_databaseName = "";
	private static DatabaseConnect m_singleton;
	
	private DatabaseConnect(String databaseName)
	{
		m_databaseName = databaseName;
	}
	
	public static DatabaseConnect getDatabaseInstance(String databaseName)
	{
		if(m_singleton == null)
		{
			m_singleton = new DatabaseConnect(databaseName);
		}
		return m_singleton;
	}
	
	public void open() throws MyTimeException
	{
		//Connect
		try
		{
			Class.forName("org.sqlite.JDBC");
		}
		catch (ClassNotFoundException e)
		{
			//to be completed
		}
		try
		{
			Connection dbConnection = DriverManager.getConnection("jdbc:sqlite:" + m_databaseName);
		}
		catch (SQLException e)
		{
			//to be completed
		}
		
	}

	public void close()
	{
		// TODO Auto-generated method stub
	
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

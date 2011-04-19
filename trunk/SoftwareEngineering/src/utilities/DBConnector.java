package utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 
 * DatabaseConnect
 * 
 * This is the class that communicates with the database supplied by the user. All of the exception handling
 * is handled within the class itself so that any other class that communicates with this one does not need
 * any extra try/catch statement shenanigans. This class can also only exist once in memory thanks to controlled
 * instance handling.
 * 
 */
public class DBConnector
{
	private String m_databaseName = "";
	private static DBConnector m_singleton;
	private Connection m_dbConnection;
	private boolean m_open;
	
	private DBConnector(String databaseName)
	{
		m_databaseName = databaseName;
		m_open = false;
	}
	/**
	 * 
	 * Creates an instance of the DatabaseConnect class if none exists. This method controls all instances
	 * of the DatabaseConnect class by making it so that only one exists at one time.
	 * 
	 */
	public static DBConnector getDatabaseInstance(String databaseName)
	{
		if(m_singleton == null)
		{
			m_singleton = new DBConnector(databaseName);
		}
		return m_singleton;
	}
	/**
	 * 
	 * Connects the DatabaseConnect class to the databaseName provided by the getDatabaseInstance method.
	 * @throws MyTimeException
	 */
	public void open() throws MyTimeException
	{
		assert (!m_open);
		
		if (!m_open)
		{
			//Connect
			try
			{
				Class.forName("org.sqlite.JDBC");
			}
			catch (ClassNotFoundException e) 
			{
				throw new MyTimeException("Drive failed to load: add sql-driver to build path!");
			}
			try
			{
				m_dbConnection = DriverManager.getConnection("jdbc:sqlite:" + m_databaseName);
				m_open = true;
			}
			catch (SQLException e)
			{
				throw new MyTimeException("Database " + m_databaseName + " could not be found.");
			}
		}
	}
	
	/**
	 * 
	 * Updates the database with an SQL statement. (ex. INSERT, DELETE)
	 * @throws MyTimeException
	 */
	
	public void update(String cmd) throws MyTimeException
	{
		Statement s = null;
        try
        {
        	s = m_dbConnection.createStatement();
        	s.executeUpdate(cmd);
        } 
        catch (SQLException e) 
        {
			throw new MyTimeException("Update Error: " + cmd, e);
		}
	}
	
	/**
	 * 
	 * Executes a search on the database with an SQL statement. (ex. SELECT)
	 * @throws MyTimeException
	 */
	
	public ResultSet execute(String cmd) throws MyTimeException
	{
		 ResultSet rs = null;       
         Statement s = null;
         try
         {
        	 s = m_dbConnection.createStatement();
        	 rs = s.executeQuery(cmd);
         }
         catch(SQLException e)
         {
        	 throw new MyTimeException("Excecute failed: " + cmd, e);
        	 
         }
         return rs;
	}
	
	/**
	 * 
	 * Closes the connection to the database.
	 * 
	 * @throws MyTimeException
	 */
	
	public void close() throws MyTimeException 
	{
		assert (m_open);
		if (m_open)
		{
			try 
			{
				m_dbConnection.close();
				m_open = false;
			} 
			catch (SQLException e) 
			{
				throw new MyTimeException("DB Close failed", e);
			}
		}
	}
	/**
	 * Returns a boolean. True means the database is connected; false means the database is not connected.
	 * 
	 */
	
	public boolean isOpen() 
	{
		return m_open;
	}

	/**
	 * Returns a String representation of data in the class.
	 */
	
	public String toString()
	{
		String s = "";
		s += m_databaseName + " ";
		s += isOpen();
		return s;
	}




}

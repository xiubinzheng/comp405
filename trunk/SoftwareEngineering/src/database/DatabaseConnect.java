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
	private Connection m_dbConnection;
	private boolean open;
	
	private DatabaseConnect(String databaseName)
	{
		m_databaseName = databaseName;
		open = false;
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
			throw new MyTimeException("Something Happened!");
		}
		try
		{
			m_dbConnection = DriverManager.getConnection("jdbc:sqlite:" + m_databaseName);
			open = true;
		}
		catch (SQLException e)
		{
			throw new MyTimeException("Something not Happened!");
		}
		
	}
	
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
			throw new MyTimeException("Update Error");
		}
        finally
        {
               if ( s != null)
				try
                {
					s.close();
				} 
               	catch (SQLException e) 
               	{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        }
	}
	
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
        	 throw new MyTimeException("This is bad");
         }
         finally
         {
        	 try
        	 {
                if ( s != null)
                      s.close();
        	 }
        	 catch (SQLException e)
        	 {
        		 throw new MyTimeException("Cohen will probably not appreciate the 'helpfulness' of these error messages");
        	 }
         }
         return rs;
	}
	
	public void close() throws MyTimeException 
	{
		try 
		{
			m_dbConnection.close();
			open = false;
		} 
		catch (SQLException e) 
		{
			throw new MyTimeException("It's A Microsoft Thing");
		}
	}
	public boolean isOpen() 
	{
		return open;
	}
}

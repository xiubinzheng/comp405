package Controls;

/**
 * This class provides a means to easily generate SQL statements.
 *
 */
class SQLGenerator
{
	String m_tableName;
	
	/**
	 * Create an SQLGenerator using a table.
	 * @param tableName Name of the table that this SQLGenerator generates for.
	 */
	public SQLGenerator( String tableName )
	{
		m_tableName = tableName;
	}
	
	/**
	 * Create a select statement.
	 * @param columnNames eg "column1, column2, ...".
	 * @param whereClause (Optional) eg "column1 = 42".
	 * @return
	 */
	public String select( String columnNames, 
	                      String whereClause )
	{
		String cmd = null;
		if 
		( columnNames != null )
		{
			cmd = "SELECT " + columnNames + " FROM " + m_tableName;
			if 
			( whereClause != null  )
			{
				cmd += " WHERE " + whereClause;
			}
		}
		return cmd;
	}
	
	/**
	 * Generate an insert statement.
	 * @param columns (Optional) "column1, column2, ..."
	 * @param values "value1, value2, ..."
	 * @return
	 */
	public String insert( String columns,
	                      String values )
	{
		String cmd = null;
		if 
		( values != null )
		{
			cmd = "INSERT INTO " + m_tableName;
			if ( columns != null )
				cmd += " ( "+ columns +" ) ";
			cmd += " VALUES ( " + values + " )";
		}
		return cmd;
	}
}
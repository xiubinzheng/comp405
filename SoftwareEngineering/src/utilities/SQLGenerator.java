package utilities;

/**
 * This class provides a means to easily generate SQL statements.
 * 
 */
class SQLGenerator
{
	String	m_tableName;

	/**
	 * Create an SQLGenerator using a table.
	 * 
	 * @param tableName Name of the table that this SQLGenerator generates for.
	 */
	public SQLGenerator(String tableName)
	{
		m_tableName = tableName;
	}

	/**
	 * Create a select statement.
	 * 
	 * @param columnNames eg "column1, column2, ...".
	 * @param whereClause (Optional) eg "column1 = 42".
	 * @return
	 */
	public String select(String columnNames , String whereClause)
	{
		String cmd = null;
		if (columnNames != null)
		{
			cmd = "SELECT " + columnNames + " FROM " + m_tableName;
			if (whereClause != null)
			{
				cmd += " WHERE " + whereClause;
			}
			cmd += ";";
		}
		return cmd;
	}

	/**
	 * Generate an insert statement.
	 * 
	 * @param columns (Optional) "column1, column2, ..."
	 * @param values "value1, \"value2\", ..."
	 * @return
	 */
	public String insert(String columns , String values)
	{
		String cmd = null;
		if (values != null)
		{
			cmd = "INSERT INTO " + m_tableName;
			if (columns != null)
			{
				cmd += " (" + columns + ") ";
			}
			cmd += "\nVALUES ("+values+");";
		}
		return cmd;
	}

	/**
	 * TODO: finish up and change Generate an update statement.
	 * 
	 * @param columns "column1, column2, ..."
	 * @param values "value1, \"value2\", ..."
	 * @param where "name = \"Jones\""
	 * @return
	 */
	public String update(String columns , String values, String where)
	{
		String[] updateColumns = columns.split(",");
		String[] updateValues = values.split(",");
		String cmd = null;
		if (values != null)
		{
			cmd = "UPDATE " + m_tableName + " \nSET " + updateColumns[0] + "="
					+ updateValues[0];
			for (int i = 1; i < updateValues.length; i++)
			{
				cmd += ", " + updateColumns[i] + "=" + updateValues[i];
			}
			cmd += " \nWHERE " + where + ";";
		}
		return cmd;
	}	
}
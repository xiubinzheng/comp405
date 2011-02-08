package client;

public class Client
{
	private int		m_clientID			= 0;
	private String	m_clientName		= "";
	private String	m_clientDescription	= "";

	public Client()
	{
		
	}

	public Client(int clientID, String name, String description)
	{
		m_clientID = clientID;
		m_clientName = name;
		m_clientDescription = description;
	}

	public void setClientID(int clientID)
	{
		m_clientID = clientID;
	}

	public int getClientID()
	{
		return m_clientID;
	}

	public void setClientName(String name)
	{
		m_clientName = name;
	}

	public String getClientName()
	{
		return m_clientName;
	}

	public void setClientDescription(String description)
	{
		m_clientDescription = description;
	}

	public String getClientDescription()
	{
		return m_clientDescription;
	}

}

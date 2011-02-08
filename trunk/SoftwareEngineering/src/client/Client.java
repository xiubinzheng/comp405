package client;

public class Client
{
	private String	m_clientName		= "";
	private String	m_clientDescription	= "";

	public Client()
	{
	}

	public Client(String name, String description)
	{
		m_clientName = name;
		m_clientDescription = description;
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

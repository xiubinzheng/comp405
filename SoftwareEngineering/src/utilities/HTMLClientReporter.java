package utilities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import models.*;

/**
 * 
 * Client/Project Reporter Class
 * 
 * Compile a complete html string which is a report on every client and project.
 * 
 * This class returns a String filled with all sorts of useful HTML information
 * for all of your various reporting needs.
 */

public class HTMLClientReporter
{
	// private DatabaseConnect m_databaseConnection;
	private static HTMLClientReporter	m_singleton;

	private String						g_htmlString	= "";

	/**
	 * 
	 * Returns a singleton instance of the DatabaseReporter class.
	 * 
	 */
	public static HTMLClientReporter getReporterInstance()
	{
		if (m_singleton == null)
		{
			m_singleton = new HTMLClientReporter();
		}
		return m_singleton;
	}

	/**
	 * Returns a String with HTML formatting of the current entries in
	 * myTimeClients.
	 * 
	 * @param cssFile The location to the css File
	 * @param imageFile The location to the image File
	 */
	public String generateReport(String cssFile , String imageFile)
	{
		g_htmlString = "";
		g_htmlString += "<html>";
		g_htmlString += "<head>";
		g_htmlString += "<link rel = \"stylesheet\" href = \"" + cssFile
				+ "\" type = \"text/css\">\n";
		g_htmlString += "<title>Client Reporter</title>";
		g_htmlString += "</head>";
		g_htmlString += "<body class=\"bodyAllClients\">";
		g_htmlString += "<img class = \"logo\" src = \"" + imageFile
				+ "\" alt = \"\"/>\n";
		g_htmlString += "<H2 class=\"r_Title\">Client Report</H2>";
		g_htmlString += "<table class=\"report\">";

		generateClientTable();

		g_htmlString += "</table>\n";
		g_htmlString += "</body>";
		g_htmlString += "</html>\n";
		// prints the HTML for the Report to the console
		// System.out.print(g_htmlString);
		return g_htmlString;
	}

	private HTMLClientReporter()
	{
	}

	/**
	 * Collects the client information
	 */
	private void generateClientTable()
	{
		ClientDBManager m_manage = ClientDBManager.getInstance();
		try
		{
			m_manage.initializeDB();
		}
		catch (MyTimeException e)
		{
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

		ArrayList<Client> m_client = new ArrayList<Client>();

		m_client.clear();
		m_manage.getClients(m_client);
		sortClients(m_client);

		System.out.println(m_client.size());
		for (Client c : m_client)
		{
			outputClientHeader();
			outputClient(c);
			// long cTime =
			generateProjectTable(c);
			// outputTotalClientTime(cTime);
		}
	}

	/**
	 * Collects the project information for this client
	 * 
	 * @param c The client object
	 */
	private void generateProjectTable(Client c)
	{
		ArrayList<Project> m_project = new ArrayList<Project>();

		m_project.clear();
		c.getProjectList(m_project);
		outputProjectHeader();

		for (Project p : m_project)
		{
			outputProject(p);
		}
	}

	// the purpose of this comment is to waste precious time so i dont have to
	// think about
	// doing the above method any more than i have to currently, that is to say
	// that i don't
	// really want to start it with 5 minutes left in the class period.

	private void outputClientHeader()// int rightSpan
	{
		g_htmlString += "<tr><td class=\"header\"><b>Client Name</b></td>";
		g_htmlString += "<td class=\"header\" colspan=\"20\"><b>Client Description</b></td></tr>";
	}

	private void outputClient(Client c)
	{
		g_htmlString += "<tr><td class=\"c_Name\">" + c.getClientName()
				+ "</td>";
		g_htmlString += "<td class=\"c_Description\" colspan=\"7\">"
				+ c.getClientDescription() + "</td></tr>";
	}

	private void outputProjectHeader()
	{
		g_htmlString += "<tr><td class=\"header\">  </td>";
		g_htmlString += "<td class=\"header\" colspan=\"5\">Project Name</td>";
		g_htmlString += "<td class=\"header\" colspan=\"10\">Project Description</td></tr>";
	}

	private void outputProject(Project p)
	{
		g_htmlString += "<tr><td>  </td>";
		g_htmlString += "<td class=\"p_Name\" colspan=\"5\">" + p.getName()
				+ "</td>\n";
		g_htmlString += "<td class=\"p_Description\" colspan=\"5\">"
				+ p.getDescription() + "</td></tr>";
	}

	/**
	 * 
	 * @param clients
	 */
	private void sortClients(ArrayList<Client> clients)
	{
		Collections.sort(clients, new Comparator<Client>()
		{
			public int compare(Client c1 , Client c2)
			{
				return c1.getClientName().compareToIgnoreCase(
						c2.getClientName());
			}
		});
	}

}
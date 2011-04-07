package gui.displayPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import project.Project;
import client.Client;

/**
 * This is a ComboBox that holds a list of Clients.
 *
 */
public class CBClient extends JComboBox 
{
	private static final long serialVersionUID = 1L; // we have no clue what this is for
	
	ClientProjectPanel m_clientProjectPanelParent;
	DefaultComboBoxModel m_model;
	DefaultComboBoxModel m_projectModel;
	CBClientListener listener;
	
	/**
	 * A CBClient will communicate with a cbProject about what the
	 * cbProject will contain, so initialize the CBProject first.
	 * @param parent The parent ClientProjectPanel of this ComboBox.
	 * @param cbProject The CBProject that this CBClient will communicate with.
	 */
	public CBClient(ClientProjectPanel parent, CBProject cbProject)
	{
		m_clientProjectPanelParent = parent;
		m_projectModel = cbProject.getDefaultModel();
		m_model = new DefaultComboBoxModel();
		setModel(m_model);
		ArrayList<Client> clientList = new ArrayList<Client>();
		
		try
		{
			m_clientProjectPanelParent.getManager().getClients(clientList);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		for(Client c:clientList)
		{
			System.out.println(c);
			m_model.addElement(c);
		}		
		listener = new CBClientListener();
		addActionListener(listener);
	}
	private class CBClientListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			CBClient cbClient = (CBClient) e.getSource();
			Client client = (Client) cbClient.getSelectedItem();
			System.out.println("DEBUG:"+client);
			ArrayList<Project> projectList = new ArrayList<Project>();
			client.getProjectList(projectList);
			m_projectModel.removeAllElements();
			for (Project p : projectList)
			{
				m_projectModel.addElement(p);
				System.out.println("DEBUG:"+p);
			}
		}
	}
}
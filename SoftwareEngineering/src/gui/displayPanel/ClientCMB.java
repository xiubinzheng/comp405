package gui.displayPanel;

import gui.MainGUI;

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
public class ClientCMB extends JComboBox 
{
	private static final long serialVersionUID = 1L; // we have no clue what this is for
	
	ClientProjectPNL m_clientProjectPanelParent;
	DefaultComboBoxModel m_model;
	DefaultComboBoxModel m_projectModel;
	//ListenerCBClient listener;
	
	/**
	 * A CBClient will communicate with a cbProject about what the
	 * cbProject will contain, so initialize the CBProject first.
	 * @param parent The parent ClientProjectPanel of this ComboBox.
	 * @param cbProject The CBProject that this CBClient will communicate with.
	 */
	public ClientCMB(ClientProjectPNL parent, ProjectCMB cbProject)
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
		//listener = new ListenerCBClient();
		//addActionListener(listener);
	}
	
	//TODO: Future refactor: implement some kind of getParent thing here
	
	public MainGUI getMainGui() 
	{
		return m_clientProjectPanelParent.getMainGui();
	}
	
	public DefaultComboBoxModel getProjectModel() 
	{
		return m_projectModel;
	}
}
package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import project.Project;

import client.Client;

public class CBClient extends JComboBox 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	MainGUI m_gui;
	DefaultComboBoxModel model; 
	CBClientListener listener;
	public CBClient(MainGUI gui)
	{
		listener= new CBClientListener();
		addActionListener(listener);
		m_gui = gui;
		model = new DefaultComboBoxModel();
		setModel(model);
		ArrayList<Client> clientList = new ArrayList<Client>();
		m_gui.getManager().getClients(clientList);
		
		for(Client c:clientList)
		{
			System.out.println(c);
			model.addElement(c);
		}
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
			DefaultComboBoxModel projectModel = m_gui.getCBProject().getDefaultModel();
			projectModel.removeAllElements();
			System.out.println("DEBUG:"+projectList);
			for (Project p : projectList)
			{
				projectModel.addElement(p);
				System.out.println("DEBUG:"+p);
			}
		}
		
	}
	
}
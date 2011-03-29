package gui.displayPanel;

import gui.MainGUI;

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
		m_gui = gui;
		model = new DefaultComboBoxModel();
		setModel(model);
		ArrayList<Client> clientList = new ArrayList<Client>();
		
		try
		{
			m_gui.getManager().getClients(clientList);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		for(Client c:clientList)
		{
			System.out.println(c);
			model.addElement(c);
		}		
		
		listener= new CBClientListener();
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
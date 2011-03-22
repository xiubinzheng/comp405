package gui;

import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import client.Client;

public class CBClient extends JComboBox 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	MainGUI m_gui;
	DefaultComboBoxModel model; 
	public CBClient(MainGUI gui)
	{
		m_gui = gui;
		model = new DefaultComboBoxModel();
		ArrayList<Client> clientList = new ArrayList<Client>();
		m_gui.getManager().getClients(clientList);
		for(Client c:clientList)
		{
			model.addElement(c);
		}
	}
	
	
}
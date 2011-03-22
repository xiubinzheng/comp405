package gui;


import java.awt.event.ActionEvent;


import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class MenuClient extends JMenu
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//TODO implement code for when the File Menu is used
	MainGUI m_gui;
	JFrame m_popUp;
	JMenuItem m_addClient, m_editClient, m_viewClient, m_viewAllClients, m_deleteClient;
	
	public MenuClient(MainGUI gui)
	{
		m_gui = gui;
		m_popUp = new JFrame(); 
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		
	}
}
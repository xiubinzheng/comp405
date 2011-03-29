package gui.displayPanel;

import java.awt.GridLayout;

import gui.MainGUI;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ClientProjectPanel extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CBClient m_cbClient;
	private CBProject m_cbProject;

	public ClientProjectPanel(MainGUI gui)
	{
		m_cbProject = new CBProject();
		m_cbClient = new CBClient(gui);
		add(new JLabel("Client"));
		add(m_cbClient);
		add(new JLabel("Project"));
		add(m_cbProject);
//		add(new JComboBox());
//		add(new JComboBox());
	}
}
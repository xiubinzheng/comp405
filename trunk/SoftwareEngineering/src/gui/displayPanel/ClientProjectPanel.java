package gui.displayPanel;

import gui.GuiUtilities;
import gui.MainGUI;

import javax.swing.*;

import Controls.Manager;

public class ClientProjectPanel extends JPanel
{
	public static void main(String[] args)
	{
		ClientProjectPanel panel = new ClientProjectPanel(null);
		JFrame frame = new JFrame();
		frame.setBounds(0, 0, 640, 480);
		panel.setVisible(true);
		frame.add(panel);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CBClient m_cbClient;
	private CBProject m_cbProject;
	private MainGUI m_mainGUIParent;
	
	public ClientProjectPanel(MainGUI parent)
	{
		m_mainGUIParent = parent;
		m_cbProject = new CBProject();
		m_cbClient = new CBClient(this, m_cbProject);
		JComponent[][] components = 
		{	
			{new JLabel("Client "), m_cbClient},
			{new JLabel("Project "), m_cbProject}
		};
		GuiUtilities.generateGridPanel(this, components, true, false);
		m_cbClient.setSelectedIndex(0);
	}
	public CBProject getCBProject()
	{
		return m_cbProject;
	}
	public CBClient getCBClient()
	{
		return m_cbClient;
	}
	public Manager getManager()
	{
		return m_mainGUIParent.getManager();
	}
}
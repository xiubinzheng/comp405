package views;

import gui.GuiUtilities;

import javax.swing.*;


import Controls.Manager;

/**
 * This class provides abstraction between two ComboBoxes contained within it.
 *
 */
public class ClientProjectPNL extends JPanel
{
	// this can be removed
	public static void main(String[] args)
	{
		ClientProjectPNL panel = new ClientProjectPNL(null);
		JFrame frame = new JFrame();
		frame.setBounds(0, 0, 640, 480);
		panel.setVisible(true);
		frame.add(panel);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	private static final long serialVersionUID = 1L; // what does this do!?
	private ClientCMB m_cbClient;
	private ProjectCMB m_cbProject;
	private MainGUI m_mainGUIParent;
	/**
	 * The MainGui parent of this class will provide methods for other classes to
	 * communicate with it.
	 * @param parent MainGUI object that will provide communication methods.
	 */
	public ClientProjectPNL(MainGUI parent)
	{
		m_mainGUIParent = parent;
		m_cbProject = new ProjectCMB(this);
		m_cbClient = new ClientCMB(this, m_cbProject);
		JComponent[][] components = 
		{	
			{new JLabel("Client "), m_cbClient},
			{new JLabel("Project "), m_cbProject}
		};
		GuiUtilities.generateGridPanel(this, components, true, false);
		m_cbClient.setSelectedIndex(0);
	}
	
	public MainGUI getMainGui()
	{
		return m_mainGUIParent;
	}
	
	public ProjectCMB getCBProject()
	{
		return m_cbProject;
	}
	public ClientCMB getCBClient()
	{
		return m_cbClient;
	}
	public Manager getManager()
	{
		return m_mainGUIParent.getManager();
	}
}
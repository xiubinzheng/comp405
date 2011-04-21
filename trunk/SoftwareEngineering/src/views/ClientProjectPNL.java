package views;


import javax.swing.*;

import controllers.StartStopBTNController;

import utilities.*;



/**
 * This class provides abstraction between two ComboBoxes contained within it.
 *
 */
public class ClientProjectPNL extends JPanel
{

	private static final long serialVersionUID = 1L; // what does this do!?
	private ClientCMB 			m_cbClient;
	private ProjectCMB 			m_cbProject;
	private MainGUI 			m_mainGUIParent;
	private JButton 			m_startStopButton;
	private JPanel				m_cbProjectPanel;
	private JPanel				m_buttonPanel;
	/**
	 * The MainGui parent of this class will provide methods for other classes to
	 * communicate with it.
	 * @param parent MainGUI object that will provide communication methods.
	 */
	public ClientProjectPNL(MainGUI parent)
	{
		m_mainGUIParent = parent;
		m_startStopButton = new JButton();
		m_cbProject = new ProjectCMB(this);
		m_cbClient = new ClientCMB(this, m_cbProject);
		m_cbProjectPanel = new JPanel();
		m_buttonPanel = new JPanel();
		JComponent[][] components = 
		{	
			{new JLabel("Client "), m_cbClient},
			{new JLabel("Project "), m_cbProject}
		};
		JComponent[] fullComponents = {m_startStopButton, m_cbProjectPanel };
		GUIUtilities.generateGridPanel(m_cbProjectPanel, components, true, false);
		GUIUtilities.generateGridPanel(this, fullComponents, true, false);
		if(m_cbClient.getItemCount() != 0)
			m_cbClient.setSelectedIndex(0);
	}
	
	public MainGUI getMainGui()
	{
		return m_mainGUIParent;
	}
	
	public JButton getStartStopButton()
	{
		return m_startStopButton;
	}
	
	public ProjectCMB getCBProject()
	{
		return m_cbProject;
	}
	public ClientCMB getCBClient()
	{
		return m_cbClient;
	}
	public ClientDBManager getManager()
	{
		return m_mainGUIParent.getManager();
	}
}
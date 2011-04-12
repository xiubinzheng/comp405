package gui.displayPanel;

import gui.MainGUI;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

/**
 * This is a combobox that holds a list of Projects.
 *
 */
public class CBProject extends JComboBox
{
	private static final long serialVersionUID = 1L;
	private DefaultComboBoxModel m_model; 
	private ClientProjectPanel m_cbProjectPanelParent;
	
	public CBProject(ClientProjectPanel cbProjectPanel)
	{
		m_cbProjectPanelParent = cbProjectPanel;
		m_model = new DefaultComboBoxModel();
		setModel(m_model);
	}
	
	public MainGUI getMainGui()
	{
		return m_cbProjectPanelParent.getMainGui();
	}
	
	public DefaultComboBoxModel getDefaultModel()
	{
		return m_model;
	}
}
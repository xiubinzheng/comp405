package views;


import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;


/**
 * This is a combobox that holds a list of Projects.
 *
 */
public class ProjectCMB extends JComboBox
{
	private static final long serialVersionUID = 1L;
	private DefaultComboBoxModel m_model; 
	private ClientProjectPNL m_cbProjectPanelParent;
	
	public ProjectCMB(ClientProjectPNL cbProjectPanel)
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
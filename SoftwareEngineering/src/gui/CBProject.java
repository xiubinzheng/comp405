package gui;

import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;



public class CBProject extends JComboBox
{
	private static final long serialVersionUID = 1L;
	
	MainGUI m_gui;
	DefaultComboBoxModel m_model; 
	public CBProject(MainGUI gui)
	{
		m_gui = gui;
		m_model = new DefaultComboBoxModel();
		setModel(m_model);
		
	}
	public DefaultComboBoxModel getDefaultModel()
	{
		return m_model;
	}
}
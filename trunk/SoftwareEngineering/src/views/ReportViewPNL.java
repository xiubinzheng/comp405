package views;

import java.awt.Color;

import javax.swing.JPanel;



public class ReportViewPNL extends JPanel
{
	private static final long serialVersionUID = 1L; // what does this do!?
	private MainGUI m_mainGUIParent;
	private JPanel m_reportPNL = new JPanel();
	
	public ReportViewPNL(MainGUI parent)
	{
		m_mainGUIParent = parent;
		
		setBackground(Color.red);
		

	}
}

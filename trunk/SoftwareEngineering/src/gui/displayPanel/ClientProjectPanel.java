package gui.displayPanel;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import exceptions.MyTimeException;
import gui.GuiUtilities;
import gui.MainGUI;

import javax.swing.*;

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
		
		JComponent[] left = {new JLabel("Client"), new JLabel("Project")};
		JComponent[] right = {m_cbProject, m_cbClient};
		JComponent[][] components = { left, right };
		GuiUtilities.generateGridPanel(this, components, true, false);
			
			/*
		setLayout(new BorderLayout());
		
		
		JPanel vBoxLeft = new JPanel();
		JPanel vBoxRight = new JPanel();
		
		GridLayout layoutLeft = new GridLayout(2,1);
		GridLayout layoutRight = new GridLayout(2,1);
		
		vBoxLeft.setLayout(layoutLeft);
		vBoxRight.setLayout(layoutRight);
		
		m_cbProject = new CBProject();
		m_cbClient = new CBClient(gui);
		
		vBoxLeft.add(new JLabel("Client"));
		vBoxLeft.add(new JLabel("Project"));
		vBoxRight.add(m_cbClient);
		vBoxRight.add(m_cbProject);
		
		add(vBoxLeft, BorderLayout.WEST);
		add(vBoxRight, BorderLayout.CENTER);
		*/
	}
}
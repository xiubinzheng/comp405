package gui.displayPanel;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import gui.MainGUI;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DisplayPanel extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MainGUI m_gui;
	private int m_width;
	private int m_height;
	private ClientProjectPanel m_clientProjectPanel;
	private TotalHoursPanel m_totalHoursPanel;
	
	public DisplayPanel(MainGUI gui, int width, int height)
	{
		super(new FlowLayout());
		setSize(new Dimension(width, height));
		m_gui = gui;
		m_width = width;
		m_height = height;
		m_totalHoursPanel = new TotalHoursPanel();
		m_clientProjectPanel = new ClientProjectPanel(m_gui);
		//setLayout(new GridLayout(2, 2));
		add(m_clientProjectPanel);
		add(m_totalHoursPanel);
		//add(new JButton("fsdfS"));
	}
	
}
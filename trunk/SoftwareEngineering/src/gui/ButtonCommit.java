package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ButtonCommit extends JButton implements ActionListener 
{
	MainGUI m_gui;
	public ButtonCommit(MainGUI gui)
	{
		m_gui = gui;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		// TODO implement code to call a commit of a time interval
		
		
	}
}
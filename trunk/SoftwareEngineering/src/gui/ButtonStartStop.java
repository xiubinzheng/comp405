package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ButtonStartStop implements ActionListener
{
	private MainGUI m_gui;
	private boolean start;
	
	public ButtonStartStop(MainGUI gui)
	{
		m_gui = gui;
		start = false;
	}
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		JButton b = m_gui.getStartStopButton();
		// TODO implement code for when the start/stop button is pressed
		//when the button is pressed and it is labeled start, start the time interval
		if (!start) 
		{
			b.setText("STOP");
			b.setForeground(Color.red);
		}
		//when the button is pressed and it is labeled stop, this will stop the time interval
		else 
		{
			b.setText("START");
			b.setForeground(Color.green);
		}
	}
	
}
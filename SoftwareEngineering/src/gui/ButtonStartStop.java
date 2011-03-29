package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ButtonStartStop extends JButton implements ActionListener, Runnable
{
	private boolean start;
	
	public ButtonStartStop(MainGUI gui)
	{
		start = false;
		gui.frame.add(this);
	}
	public void actionPerformed(ActionEvent e) 
	{
		// TODO implement code for when the start/stop button is pressed
		//when the button is pressed and it is labeled start, start the time interval
		if (!start) 
		{
			setText("STOP");
			setForeground(Color.red);
		}
		//when the button is pressed and it is labeled stop, this will stop the time interval
		else 
		{
			setText("START");
			setForeground(Color.green);
		}
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
}
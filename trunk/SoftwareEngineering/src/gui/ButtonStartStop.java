package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class ButtonStartStop extends JButton implements ActionListener
{
	private boolean start;
	private Timer clock;
	
	public ButtonStartStop(MainGUI gui)
	{
		start = false;
		gui.frame.add(this);
		clock = new Timer(1000,this.actionListener);
	}
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource()==clock)
		{
			//increment timer
		}
		else //Button Pressed
		{
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
	}
}
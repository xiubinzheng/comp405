package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class ButtonStartStop extends JButton implements ActionListener
{
	private boolean start;
	private Timer clock;
	private int i = 0;
	
	public ButtonStartStop(JFrame gui)
	{
		start = false;
		gui.getContentPane().add(this);
		addActionListener(this);
		setText("START");
		setBackground(Color.green);
		clock = new Timer(1000, this);
	}
	public int getTime()
	{
		return i;
	}
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource()==clock)
		{
			//increment timer
			i++;
			System.out.println("Time: "+i);
		}
		if(e.getSource()==this)
		{
			if (!start) 
			{
				setText("STOP");
				setBackground(Color.red);
				clock.start();
				start = true;
			}
			//when the button is pressed and it is labeled stop, this will stop the time interval
			else 
			{
				setText("START");
				setBackground(Color.green);
				clock.stop();
				start = false;
			}
		}
	}
}
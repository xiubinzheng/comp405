package gui;

import java.awt.Color;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.Timer;

import project.TimeInterval;

@SuppressWarnings("serial")
public class ButtonStartStop extends JButton implements ActionListener
{
	private boolean start;
	private Timer clock;
	private int i = 0;
	private TimeInterval time;
	private TextField m_outputField;
	
	public ButtonStartStop(JFrame gui)
	{
		start = false;
		time = new TimeInterval();
		gui.getContentPane().add(this);
		addActionListener(this);
		setText("START");
		m_outputField = new TextField();
		setBackground(Color.green);
		clock = new Timer(1000, this);
	}
	public int getTime()
	{
		return i;
	}
	public void setOutput(TextField t)
	{
		m_outputField = t;
	}
	public TextField getTextField()
	{
		return m_outputField;
	}
	public TimeInterval getTimeInterval()
	{
		if(!start)
			return time;
		return null;
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
				time.start();
				start = true;
			}
			//when the button is pressed and it is labeled stop, this will stop the time interval
			else 
			{
				setText("START");
				setBackground(Color.green);
				clock.stop();
				time.stop();
				m_outputField.setText(time.toString());
				start = false;
			}
		}
	}
}
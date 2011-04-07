package gui.controllers;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;

import exceptions.MyTimeException;
import gui.TestFrame;

import project.Project;
import project.TimeInterval;

public class StartStopController implements ActionListener
{
	private TestFrame myFrame;
	boolean start = false;
	private TimeInterval time;
	private ArrayList<TimeInterval> temp = new ArrayList<TimeInterval>();
	private int i = 0;
	
	public StartStopController(TestFrame jf)
	{
		myFrame=jf;
		
		//StartStop Button stuff
		myFrame.getButton().setText("START");
		myFrame.getButton().setBackground(Color.green);
		time = new TimeInterval();
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()== myFrame.getClock())
		{
			//increment timer
			i++;
			myFrame.getTimerField().setText(CounterUpper());
		}
		if(e.getSource()==myFrame.getButton())
		{	
			
			//this if else controls the start stop button turning it red 
			//and changing the text when clicked.
			if (!start) 
			{
				myFrame.getButton().setText("STOP");
				myFrame.getButton().setBackground(Color.red);
				//clock.start();
				time.start();
				myFrame.getTimerField().setText(CounterUpper());
				start = true;
				
				myFrame.getClock().start();
			}
			//when the button is pressed and it is labeled stop, 
			//this will stop the time interval
			else //stop button
			{
				myFrame.getButton().setText("START");
				myFrame.getButton().setBackground(Color.green);
				time.stop();
				i = 0;
				myFrame.getClock().stop();
				
				try 
				{
					myFrame.getCurrentProject().addTime(time);
				} 
				catch (MyTimeException e1) 
				{
					e1.printStackTrace();
				}

				start = false;
			}
		}
	}
	
	private String CounterUpper()
	{
		String fCounter = "";
		
		int hours, minutes, seconds;
		
		hours = i / 360;
		minutes = (i % 360) / 60;
		seconds = (i % 360) % 60;
		
		fCounter = String.format("%02d : %02d : %02d", hours, minutes, seconds);
		
		return fCounter;
	}
}
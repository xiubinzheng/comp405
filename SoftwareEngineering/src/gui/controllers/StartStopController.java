package gui.controllers;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.Timer;

import exceptions.MyTimeException;
import gui.MainGUI;
import gui.TestFrame;

import project.Project;
import project.TimeInterval;

public class StartStopController implements ActionListener
{
	private MainGUI myGUI;
	boolean start = false;
	private TimeInterval time;
	private int i = 0;
	private Timer clock;
	
	public StartStopController(MainGUI gui)
	{
		myGUI=gui;
		
		//StartStop Button stuff
		myGUI.getStartStopBtn().setText("START");
		myGUI.getStartStopBtn().setBackground(Color.green);
		time = new TimeInterval();
		clock = new Timer(1000, this);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()== clock)
		{
			//increment timer
			i++;
			myGUI.getTimerField().setText(CounterUpper());
		}
		if(e.getSource()==myGUI.getStartStopBtn())
		{	
			
			//this if else controls the start stop button turning it red 
			//and changing the text when clicked.
			if (!start) 
			{
				myGUI.getStartStopBtn().setText("STOP");
				myGUI.getStartStopBtn().setBackground(Color.red);
				//clock.start();
				time.start();
				myGUI.getTimerField().setText(CounterUpper());
				start = true;
				
				clock.start();
			}
			//when the button is pressed and it is labeled stop, 
			//this will stop the time interval
			else //stop button
			{
				myGUI.getStartStopBtn().setText("START");
				myGUI.getStartStopBtn().setBackground(Color.green);
				time.stop();
				i = 0;
				clock.stop();
				
				try 
				{
					myGUI.getCurrentProject().addTime(time);
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
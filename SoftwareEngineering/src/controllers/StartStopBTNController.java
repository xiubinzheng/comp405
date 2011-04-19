package controllers;

import java.awt.Color;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.Timer;

import views.MainGUI;

import models.*;

import exceptions.MyTimeException;


public class StartStopBTNController implements ActionListener
{
	private MainGUI m_myGUI;
	private boolean m_isStarted = false;
	private TimeInterval m_timeInt;
	private int m_seconds = 0;
	private Timer m_clock;
	
	public StartStopBTNController(MainGUI gui)
	{
		m_myGUI=gui;
		
		//Mutates the Start Stop Button provided by the GUI...
		m_myGUI.getStartStopBtn().setText("START");
		m_myGUI.getStartStopBtn().setBackground(Color.green);
		m_timeInt = new TimeInterval();
		//Creates a new timer with a one second interval...
		m_clock = new Timer(1000, this);
	}
	
	/**
	 * Controls all the actions for the GUI components
	 */
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()== m_clock)
		{
			//Increment seconds counter...
			m_seconds++;
			//Sends the new string value to the GUI TextField...
			m_myGUI.getTimerField().setText(CounterUpper());
		}
		if(e.getSource()==m_myGUI.getStartStopBtn())
		{	
			
			//this if else controls the start stop button turning it red 
			//and changing the text when clicked.
			if (!m_isStarted) //Start Button
			{
				//Mutates the button to Red and changes the text to stop...
				m_myGUI.getStartStopBtn().setText("STOP");
				m_myGUI.getStartStopBtn().setBackground(Color.red);
				m_timeInt.start();
				m_myGUI.getTimerField().setText(CounterUpper());
				m_isStarted = true;
				
				m_clock.start();
			}
			//when the button is pressed and it is labeled stop, 
			//this will stop the time interval
			else //Stop Button
			{
				m_myGUI.getStartStopBtn().setText("START");
				m_myGUI.getStartStopBtn().setBackground(Color.green);
				m_timeInt.stop();
				m_seconds = 0;
				m_clock.stop();

				
				try 
				{
					m_myGUI.getCurrentProject().addTime(m_timeInt);
					m_myGUI.getManager().updateClient(m_myGUI.getCurrentClient());
				} 
				catch (MyTimeException e1) 
				{
					e1.printStackTrace();
				}

				m_isStarted = false;
			}
		}
	}
	
	//Formats the current number of seconds(i) into a string of the format hh : mm : ss
	private String CounterUpper()
	{
		String fCounter = "";
		
		int hours, minutes, seconds;
		
		hours = m_seconds / 3600;
		minutes = (m_seconds % 3600) / 60;
		seconds = (m_seconds % 3600) % 60;
		
		fCounter = String.format("%02d : %02d : %02d", hours, minutes, seconds);
		
		return fCounter;
	}
}
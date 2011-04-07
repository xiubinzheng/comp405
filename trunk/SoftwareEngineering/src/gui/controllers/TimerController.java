package gui.controllers;

import gui.TestFrame;

import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class TimerController extends JButton implements ActionListener
{
	private int i = 0;
	TestFrame myFrame;
	
	public TimerController(TestFrame gui)
	{
		myFrame = gui;
		myFrame.getClock().setDelay(1000);
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource()== myFrame.getClock())
		{
			//increment timer
			i++;
			myFrame.getTimerField().setText(CounterUpper());
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
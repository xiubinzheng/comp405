package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;

import exceptions.MyTimeException;

import project.Project;
import project.TimeInterval;

public class StarStopController implements ActionListener
{
	private TestFrame myframe;
	boolean start = false;
	private TimeInterval time;
	private ArrayList<TimeInterval> temp = new ArrayList<TimeInterval>();
	
	public StarStopController(TestFrame jf)
	{
		myframe=jf;
		
		//StartStop Button stuff
		myframe.getButton().setText("START");
		myframe.getButton().setBackground(Color.green);
		time = new TimeInterval();
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==myframe.getButton())
		{	
			//this if else controls the start stop button turning it red 
			//and changing the text when clicked.
			if (!start) 
			{
				myframe.getButton().setText("STOP");
				myframe.getButton().setBackground(Color.red);
				//clock.start();
				time.start();
				start = true;
			}
			//when the button is pressed and it is labeled stop, 
			//this will stop the time interval
			else //stop button
			{
				myframe.getButton().setText("START");
				myframe.getButton().setBackground(Color.green);
				//clock.stop();
				time.stop();
				
				try 
				{
					myframe.getCurrentProject().addTime(time);
				} 
				catch (MyTimeException e1) 
				{
					e1.printStackTrace();
				}
				
				myframe.getCurrentProject().getTimeIntervals(temp);
				
				for(int i = 0; i < temp.size(); i++)
				{
					System.out.println("index = "+i + temp.get(i).toString());
				}
				
				myframe.getTimer().setText(time.toString());
				start = false;
			}
		}
	}
}
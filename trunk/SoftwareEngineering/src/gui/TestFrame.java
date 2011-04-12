package gui;

import gui.controllers.MasterControl;
import gui.controllers.StartStopController;
import gui.controllers.TimerController;

import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.TextField;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.Timer;

import project.Project;

import client.Client;

public class TestFrame extends MainGUI 
{
	private JButton b;
	private TextField time;
	private StartStopController btn;
	private Client testClient;
	private Project testProject;
	private Timer clock;
	
	
	
	public TestFrame()
	{
		
		b = new JButton("Test Button");
		b.setBounds(100,100,100,20);
		b.setVisible(true);
		btn = new StartStopController(this);
		b.addActionListener(btn);
		
		
		time = new TextField();
		time.setBounds(100,200,75,20);
		time.setVisible(true);
		
		testClient = new Client();
		testProject = new Project();
		testClient.addProject(testProject);
	}
	
	public static void main(String[] args)
	{
		TestFrame t = new TestFrame();
		JFrame f = new JFrame();
		f.setLayout(null);		
		f.add(t.getButton());
		f.add(t.getTimerField());
		f.setBounds(100,100,500,500);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	public JButton getButton()
	{
		return b;
	}
	
	public Project getCurrentProject()
	{
		return testProject;
	}
	public Timer getClock()
	{
		return clock;
	}

}
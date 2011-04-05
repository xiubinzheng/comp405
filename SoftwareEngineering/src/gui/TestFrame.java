package gui;

import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.TextField;

import javax.swing.JButton;
import javax.swing.JFrame;

import project.Project;

import client.Client;

public class TestFrame
{
	private JButton b;
	private TextField time;
	StarStopController btn;
	private Client testClient;
	private Project testProject;
	
	
	
	public TestFrame()
	{
		
		b = new JButton("Test Button");
		b.setBounds(100,100,100,20);
		b.setVisible(true);
		btn = new StarStopController(this);
		b.addActionListener(btn);
		time = new TextField();
		time.setBounds(100,200,400,20);
		time.setVisible(true);
		time.addActionListener(btn);
		testClient = new Client();
		testProject = new Project();
		testClient.addProject(testProject);
	}
	
	public static void main(String[] args)
	{
		TestFrame t = new TestFrame();
		JFrame f = new JFrame();
		f.setLayout(null);
		/*ButtonStartStop b = new ButtonStartStop(f);
		TextField t = new TextField();
		t.setBounds(100,200,100,30);
		t.setVisible(true);
		f.add(t);
		b.setBounds(100,100,100,50);
		b.setVisible(true);
		b.setOutput(t);*/
		
		
		f.add(t.getButton());
		f.add(t.getTimer());
		f.setBounds(100,100,500,500);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	public JButton getButton()
	{
		return b;
	}
	public TextField getTimer()
	{
		return time;
	}
	public Project getCurrentProject()
	{
		return testProject;
	}
}
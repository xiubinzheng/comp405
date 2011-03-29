package gui;

import java.awt.Color;
import java.awt.Frame;

import javax.swing.JFrame;

public class TestFrame
{
	public static void main(String[] args)
	{
		JFrame f = new JFrame();
		f.setLayout(null);
		ButtonStartStop b = new ButtonStartStop(f);
		b.setBounds(100,100,100,50);
		b.setVisible(true);
		f.setBounds(100,100,500,500);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
package gui;

import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.TextField;

import javax.swing.JButton;
import javax.swing.JFrame;

public class TestFrame
{
	private JButton b = new JButton();
	BtnController btn = new BtnController(this);
	public static void main(String[] args)
	{
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
		
		JButton b = new JButton("Test Button");
		b.setBounds(100,100,100,20);
		b.setVisible(true);
		f.add(b);
		f.setBounds(100,100,500,500);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public JButton getButton()
	{
		return b;
	}
}
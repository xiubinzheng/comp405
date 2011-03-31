package gui;

import javax.swing.*;

import exceptions.MyTimeException;

public class GuiUtilities {
	
	public static JPanel createColumnLabelComponent(JComponent[] left, JComponent[] right) throws MyTimeException
	{
		JPanel panel = new JPanel();
		
		if(left.length != right.length)
		{
			throw new MyTimeException("Number of Left Components and Right Components do not match");
		}
		
		return panel;
	}
}

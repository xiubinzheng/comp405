package gui;

import javax.swing.*;
import java.awt.*;

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
	
	/**
	 * This method generates a panel of the gridLayout and adds components from the provided parameter array.
	 * The panel parameter can be null. If it's null, the method will generate a new panel and return that one.
	 * 
	 * @param panel
	 * @param components
	 * @return
	 */
	
	public static JPanel generateGridPanel(JPanel panel, JComponent[][] components, boolean leftmostToWest, boolean rightmostToEast)
	{
		JPanel gridPanel;
		if (panel == null)
			gridPanel = new JPanel();
		else
			gridPanel = panel;
		
		gridPanel.setLayout(new BorderLayout());
		
		if(leftmostToWest)
		{
			JPanel p1 = new JPanel();
			p1.setLayout(new GridLayout(components[0].length,1));
			
			for ( int x = 0; x < components[0].length; x++)
			{
				p1.add(components[x][0]);
			}			
			
			gridPanel.add(p1);
		}
		
		JPanel p2 = new JPanel();

		GridLayout gridLayout = new GridLayout();
		gridLayout.setRows(components.length);
		gridLayout.setColumns(components[0].length);
		p2.setLayout(gridLayout);
		
		int start = 0;
		int end = components.length;
		
		if (leftmostToWest) start++;
		if (rightmostToEast) end--;
		
		for( int y = start; y < end; y++ ) 
		{
			for ( int x = 0; x < components[0].length; x++)
			{
				p2.add(components[x][y]);
			}
		}
		gridPanel.add(p2, BorderLayout.CENTER);
		
		if(rightmostToEast)
		{
			JPanel p3 = new JPanel();
			p3.setLayout(new GridLayout(components[0].length,1));
			
			for ( int x = 0; x < components[0].length; x++)
			{
				p3.add(components[x][components[0].length - 1]);
			}			
			
			gridPanel.add(p3, BorderLayout.EAST);
		}
		
		return gridPanel;
	}
}

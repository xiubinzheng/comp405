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
		{
			gridPanel = new JPanel();
		}
		else
		{
			gridPanel = panel;
		}
		
		gridPanel.setLayout(new BorderLayout());
		
		int xOffset = 0;
		int centerWidth = components[0].length;

		if(leftmostToWest)
		{
			JPanel westPanel = new JPanel();
			westPanel.setLayout(new GridLayout(components.length,1));
			for ( int x = 0; x < components.length; x++)
			{
				westPanel.add(components[x][0]);
			}			
			gridPanel.add(westPanel, BorderLayout.WEST);
			xOffset++;
			centerWidth--;
		}
		
		if(rightmostToEast)
		{
			JPanel eastPanel = new JPanel();
			eastPanel.setLayout(new GridLayout(components[0].length,1));
			for ( int x = 0; x < components[0].length; x++)
			{
				eastPanel.add(components[x][components[0].length - 1]);
			}			
			gridPanel.add(eastPanel, BorderLayout.EAST);
			centerWidth--;
		}
		GridLayout gridLayout = new GridLayout();
		gridLayout.setRows(components.length);
		gridLayout.setColumns(centerWidth);
		JPanel centerPanel = new JPanel();		
		centerPanel.setLayout(gridLayout);
		for( int y = 0; y < components.length; y++ ) 
		{
			for( int x = 0; x < centerWidth; x++)
			{
				centerPanel.add(components[y][x + xOffset]);
			}
		}
		gridPanel.add(centerPanel, BorderLayout.CENTER);
		return gridPanel;
	}
}

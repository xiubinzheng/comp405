package gui.controllers;

import gui.HTMLViewer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ListenerHTMLViewer implements ActionListener
{

	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			HTMLViewer htmlViewer = new HTMLViewer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}

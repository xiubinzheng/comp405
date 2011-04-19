package controllers;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import views.ReportViewDLG;

public class ReportBTNController implements ActionListener
{

	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			ReportViewDLG htmlViewer = new ReportViewDLG();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}

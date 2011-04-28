package controllers;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import views.MainGUI;
import views.ProjectCMB;

import models.Project;


public class ProjectCMBController implements ActionListener 
{
	private MainGUI m_myGUI;
	
	public ProjectCMBController(MainGUI mainGUI)
	{
		m_myGUI = mainGUI;
	}

	public void actionPerformed(ActionEvent e) 
	{
		ProjectCMB cbProject = (ProjectCMB) e.getSource();
		Project project = (Project) cbProject.getSelectedItem();
		cbProject.getMainGui().setCurrentProject(project);
		try
		{
			m_myGUI.getReportViewPNL().refreshReport();
		}
		catch (IOException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

}

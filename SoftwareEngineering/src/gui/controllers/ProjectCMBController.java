package gui.controllers;

import gui.displayPanel.ProjectCMB;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import models.Project;


public class ProjectCMBController implements ActionListener 
{
	public void actionPerformed(ActionEvent e) 
	{
		ProjectCMB cbProject = (ProjectCMB) e.getSource();
		Project project = (Project) cbProject.getSelectedItem();
		cbProject.getMainGui().setCurrentProject(project);
	}

}

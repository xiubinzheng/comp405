package gui.controllers;

import gui.displayPanel.CBProject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import project.Project;

public class ProjectCMBController implements ActionListener 
{
	public void actionPerformed(ActionEvent e) 
	{
		CBProject cbProject = (CBProject) e.getSource();
		Project project = (Project) cbProject.getSelectedItem();
		cbProject.getMainGui().setCurrentProject(project);
	}

}

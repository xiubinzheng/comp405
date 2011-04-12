package gui.displayPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import project.Project;

public class ListenerCBProject implements ActionListener 
{
	public void actionPerformed(ActionEvent e) 
	{
		CBProject cbProject = (CBProject) e.getSource();
		Project project = (Project) cbProject.getSelectedItem();
		cbProject.getMainGui().setCurrentProject(project);
	}

}

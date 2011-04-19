package controllers;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;

import views.ClientCMB;

import models.*;



public class ClientCMBController implements ActionListener
{
	public void actionPerformed(ActionEvent e) 
	{
		ClientCMB cbClient = (ClientCMB) e.getSource();
		Client client = (Client) cbClient.getSelectedItem();
		cbClient.getMainGui().setCurrentClient(client);
		System.out.println("DEBUG:"+client);
		ArrayList<Project> projectList = new ArrayList<Project>();
		client.getProjectList(projectList);
		DefaultComboBoxModel projectModel = cbClient.getProjectModel();
		projectModel.removeAllElements();
		for (Project p : projectList)
		{
			projectModel.addElement(p);
			System.out.println("DEBUG:"+p);
		}
	}
}
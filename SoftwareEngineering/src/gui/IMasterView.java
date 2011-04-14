package gui;

import java.awt.Button;
import java.awt.TextField;

import client.Client;

import project.Project;

public interface IMasterView 
{
	public Button getStartStopBtn();
	public TextField getTimerField();
	public Project getCurrentProject();
	public Client getCurrentClient();
}

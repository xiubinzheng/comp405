package gui.controllers;

import java.awt.Button;
import java.awt.TextField;

import client.Client;

import project.Project;

public interface MasterControl 
{
	public Button getStartStopBtn();
	public TextField getTimerField();
	public Project getCurrentProject();
	public Client getCurrentClient();
}

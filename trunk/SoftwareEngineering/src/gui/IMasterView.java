package gui;

import java.awt.Button;
import java.awt.TextField;

import models.*;



public interface IMasterView 
{
	public Button getStartStopBtn();
	public TextField getTimerField();
	public Project getCurrentProject();
	public Client getCurrentClient();
}

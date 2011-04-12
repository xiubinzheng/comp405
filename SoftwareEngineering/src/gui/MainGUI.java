package gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.UIManager;
import java.awt.SystemColor;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.UIManager.*;
import javax.swing.SwingConstants;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Color;
import java.awt.TextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComboBox;

import exceptions.MyTimeException;
import gui.controllers.StartStopController;
import gui.displayPanel.CBClient;
import gui.displayPanel.CBProject;
import gui.displayPanel.ClientProjectPanel;
import gui.displayPanel.ListenerCBClient;
import gui.displayPanel.ListenerCBProject;

import Controls.Manager;
import javax.swing.JList;

import project.Project;

import client.Client;

/**
 * The MainGUI will provide methods of communication between its components.
 *
 */
public class MainGUI
{
	// components
	public        	JFrame    			frame;
	private final 	Action    			action = new SwingAction();
	private			ClientProjectPanel	m_clientProjectPanel;
	private       	ButtonCommit   		m_btnCommit;
	private       	JButton			   	m_btnStartStop;
	private       	Manager   			m_dbManager;

	// action listenters
	ListenerCBClient cbClientListener;
	ListenerCBProject cbProjectListener;
	
	// models
	DefaultComboBoxModel cbClientModel;
	DefaultComboBoxModel cbProjectModel;
	
	Client 			m_currentClient;
	Project 		m_currentProject;
	TextField 		m_timerField;
	
	// miscellaneous
	static final Color m_colorGreen = new Color(0, 153, 51);
	static final Color m_colorRed = new Color(255, 0, 0);
	static final Color m_colorWhite = new Color(255, 255, 255);
	static final Color m_colorYellow = new Color(255, 204, 0);
	static final Color m_colorGrey = new Color(128, 128, 128);
	static final Color m_colorLightGrey = new Color(200, 200, 200);
	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(
		new Runnable()
		{
			public void run()
			{
				try
				{
					MainGUI window = new MainGUI();
					window.frame.setVisible(true);

					for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				        if ("Nimbus".equals(info.getName())) {
				            UIManager.setLookAndFeel(info.getClassName());
				            break;
				        }
				    }
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainGUI()
	{
		// ideally the contents of initialize() would be inside here instead, but oh well
		initialize();
	}
	
	public Manager getManager()
	{
		return m_dbManager;
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		
		m_dbManager = new Manager();
		
		try 
		{
			m_dbManager.initializeDB();
		} 
		catch (MyTimeException e) 
		{
			e.printStackTrace();
		}
		
		
		frame = new JFrame();
		frame.setMinimumSize(new Dimension(450, 300));
		frame.setBounds(100, 100, 640, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		m_clientProjectPanel = new ClientProjectPanel(this);
		m_clientProjectPanel.setBounds(0, 0, 640, 100);
		m_clientProjectPanel.setVisible(true);
		
		cbClientListener = new ListenerCBClient();
		cbProjectListener = new ListenerCBProject();
		
		m_clientProjectPanel.getCBClient().addActionListener(cbClientListener);
		m_clientProjectPanel.getCBProject().addActionListener(cbProjectListener);
		
		frame.add(m_clientProjectPanel, BorderLayout.NORTH);
		
		m_btnStartStop = new JButton();
		frame.add(m_btnStartStop, BorderLayout.SOUTH);
		
		m_timerField = new TextField();
		frame.add(m_timerField, BorderLayout.CENTER);
		
		StartStopController startStopController = new StartStopController(this);
		m_btnStartStop.addActionListener(startStopController);
		
		/*JMenuBar menuBar = new JMenuBar();
		menuBar.setMinimumSize(new Dimension(300, 15));
		frame.setJMenuBar(menuBar);
		
		Component horizontalStrut_6 = Box.createHorizontalStrut(8);
		menuBar.add(horizontalStrut_6);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				System.exit(0);
			}
		});

		mnFile.add(mntmExit);
		
		Component horizontalStrut = Box.createHorizontalStrut(8);
		menuBar.add(horizontalStrut);
		
		JMenu mnClient = new JMenu("Client");
		menuBar.add(mnClient);
		
		//JMenuItem mntmAddClient = new JMenuItem("Add Client");
		//mnClient.add(mntmAddClient);
		
		JMenuItem mntmEditClient = new JMenuItem("Edit Client");
		mnClient.add(mntmEditClient);
		
		JMenuItem mntmDeleteClient = new JMenuItem("Delete Client");
		mnClient.add(mntmDeleteClient);
		
		JMenuItem mntmViewClient = new JMenuItem("View Client");
		mnClient.add(mntmViewClient);
		
		JMenuItem mntmViewAllClients = new JMenuItem("View All Clients");
		mnClient.add(mntmViewAllClients);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(8);
		menuBar.add(horizontalStrut_1);
		
		JMenu mnProject = new JMenu("Project");
		menuBar.add(mnProject);
		
		JMenuItem mntmAddProject = new JMenuItem("Add Project");
		mnProject.add(mntmAddProject);
		
		JMenuItem mntmEditProject = new JMenuItem("Edit Project");
		mnProject.add(mntmEditProject);
		
		JMenuItem mntmDeleteProject = new JMenuItem("Delete Project");
		mnProject.add(mntmDeleteProject);
		
		JMenuItem mntmViewProject = new JMenuItem("View Project");
		mnProject.add(mntmViewProject);
		
		JMenuItem mntmViewCurrentProjects = new JMenuItem("View Current Projects");
		mnProject.add(mntmViewCurrentProjects);
		
		JMenuItem mntmViewAllProjects = new JMenuItem("View All Projects");
		mnProject.add(mntmViewAllProjects);
		
		JMenuItem mntmMarkProjectAs = new JMenuItem("Mark Project As Complete");
		mnProject.add(mntmMarkProjectAs);
		
		Component horizontalStrut_2 = Box.createHorizontalStrut(8);
		menuBar.add(horizontalStrut_2);
		
		JMenu mnReport = new JMenu("Report");
		menuBar.add(mnReport);
		
		Component horizontalStrut_3 = Box.createHorizontalStrut(8);
		menuBar.add(horizontalStrut_3);
		
		JMenu mnTimer = new JMenu("Timer");
		menuBar.add(mnTimer);
		
		JMenuItem mntmStartpause = new JMenuItem("Start/Pause");
		mnTimer.add(mntmStartpause);
		
		JMenuItem mntmStop = new JMenuItem("Stop");
		mnTimer.add(mntmStop);
		
		JMenuItem mntmCommit = new JMenuItem("Commit");
		mnTimer.add(mntmCommit);
		
		JMenuItem mntmResetTimer = new JMenuItem("Reset Timer");
		mnTimer.add(mntmResetTimer);
		
		JMenuItem mntmEditTime = new JMenuItem("Edit Time");
		mnTimer.add(mntmEditTime);
		
		Component horizontalStrut_4 = Box.createHorizontalStrut(8);
		menuBar.add(horizontalStrut_4);
		
		JMenu mnSettings = new JMenu("Settings");
		menuBar.add(mnSettings);
		
		JMenuItem mntmAdjustReminders = new JMenuItem("Adjust Reminders");
		mnSettings.add(mntmAdjustReminders);
		
		JMenuItem mntmTheme = new JMenuItem("Theme");
		mnSettings.add(mntmTheme);
		
		Component horizontalStrut_5 = Box.createHorizontalStrut(8);
		menuBar.add(horizontalStrut_5);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmAbout = new JMenuItem("About");
		mnHelp.add(mntmAbout);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		*/
/*
		JPanel Project_panel = new JPanel();
		Project_panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		Project_panel.setAlignmentY(1.0f);
		Project_panel.setPreferredSize(new Dimension(100, 100));
		Project_panel.setMinimumSize(new Dimension(65, 15));
		Project_panel.setBackground(SystemColor.inactiveCaptionBorder);
		Project_panel.setForeground(SystemColor.textHighlight);
		frame.getContentPane().add(Project_panel, BorderLayout.NORTH);
		Project_panel.setPreferredSize(new Dimension(30,100));
		GridBagConstraints gbc_lblProjectName_1_1 = new GridBagConstraints();
		gbc_lblProjectName_1_1.fill = GridBagConstraints.BOTH;
		gbc_lblProjectName_1_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblProjectName_1_1.gridx = 0;
		gbc_lblProjectName_1_1.gridy = 2;
		
		JLabel lblProjectHoursDisplay = new JLabel("02:30:00");
		GridBagConstraints gbc_lblProjectHours_1_1 = new GridBagConstraints();
		gbc_lblProjectHours_1_1.fill = GridBagConstraints.BOTH;
		gbc_lblProjectHours_1_1.gridx = 0;
		gbc_lblProjectHours_1_1.gridy = 4;
		Project_panel.setLayout(new BorderLayout(0, 0));
		
		JPanel project1_panel = new JPanel();
		Project_panel.add(project1_panel, BorderLayout.WEST);
		
		JLabel lblClientName = new JLabel("Client Name: ");
		project1_panel.setLayout(new GridLayout(0, 1, 0, 0));
		project1_panel.add(lblClientName);
		
		JLabel lblProjectName = new JLabel("Project Name: ");
		project1_panel.add(lblProjectName);
		
		JLabel lblProjectHours = new JLabel("Total Hours: ");
		project1_panel.add(lblProjectHours);
		
		JLabel lblTotalHoursweek = new JLabel("Total Hours/Week:    ");
		project1_panel.add(lblTotalHoursweek);
		
		JLabel lblTotalHoursday = new JLabel("Total Hours/Day: ");
		project1_panel.add(lblTotalHoursday);
		
		
		PanelProjectInfo Action_panel = new PanelProjectInfo(this);
		//TODO put all adds() to the PanelProjectInfo Class constructor
		Action_panel.setBorder(new EmptyBorder(0, 0, 0, 1));
		Project_panel.add(Action_panel, BorderLayout.CENTER);
		Action_panel.setLayout(new GridLayout(0, 1, 0, 0));
		
//		m_cbProject = new CBProject();
//		m_cbClient = new CBClient(this);
//		
//		Action_panel.add(m_cbClient);
//		Action_panel.add(m_cbProject);
		
		JLabel label_5 = new JLabel("02:30:00");
		Action_panel.add(label_5);
		
		JLabel lblNewLabel = new JLabel("00:55:00");
		Action_panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("00:15:00");
		Action_panel.add(lblNewLabel_1);
		
		JPanel totalHoursCluster = new JPanel();
		Project_panel.add(totalHoursCluster, BorderLayout.NORTH);
		
		JPanel Main_panel = new JPanel();
		frame.getContentPane().add(Main_panel, BorderLayout.CENTER);
		Main_panel.setLayout(new BorderLayout(0, 0));
		
		PanelDisplay Display_panel = new PanelDisplay(this);
		Main_panel.add(Display_panel, BorderLayout.CENTER);
		Display_panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblCurrentTime = new JLabel("Current Time");
		lblCurrentTime.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblCurrentTime.setHorizontalAlignment(SwingConstants.CENTER);
		Display_panel.add(lblCurrentTime);
		
		JLabel lblMarchst = new JLabel("December 1st, 2011 12:15:39 pm");
		lblMarchst.setHorizontalAlignment(SwingConstants.CENTER);
		lblMarchst.setFont(new Font("Dialog", Font.BOLD, 14));
		Display_panel.add(lblMarchst);
		
		JLabel lblTimeElapsed = new JLabel("Elapsed Time");
		lblTimeElapsed.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblTimeElapsed.setHorizontalAlignment(SwingConstants.CENTER);
		Display_panel.add(lblTimeElapsed);
		
		JLabel label_3 = new JLabel("00:20:00");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setFont(new Font("Dialog", Font.BOLD, 20));
		Display_panel.add(label_3);
		
//		m_btnStartStop = new ButtonStartStop(this);
		m_btnStartStop.addActionListener(
				
				new ActionListener() 
				{
					public void actionPerformed(ActionEvent e) 
					{
						
					}
				});
		m_btnStartStop.setAction(action);
		m_btnStartStop.setFocusPainted(false);
		m_btnStartStop.setFont(new Font("Tahoma", Font.BOLD, 20));
		m_btnStartStop.setForeground(new Color(255, 255, 255));
		m_btnStartStop.setBackground(m_colorGreen);
		Display_panel.add(m_btnStartStop);
		
		m_btnCommit = new ButtonCommit(this);
		m_btnCommit.setFocusPainted(false);
		m_btnCommit.setEnabled(false);
		m_btnCommit.setBackground(m_colorGrey);
		m_btnCommit.setForeground(new Color(255, 255, 255));
		m_btnCommit.setFont(new Font("Tahoma", Font.BOLD, 20));
		Display_panel.add(m_btnCommit);
	*/
	}
	
	public void setCurrentClient(Client client)
	{
		m_currentClient = client;
	}
	
	public void setCurrentProject(Project project)
	{
		m_currentProject = project;
	}
	
	public Client getCurrentClient()
	{
		return m_currentClient;
	}
	
	public Project getCurrentProject()
	{
		return m_currentProject;
	}
	
	public CBProject getCBProject()
	{
		return  m_clientProjectPanel.getCBProject();
	}
	
	public CBClient getClientComboBox()
	{
		return m_clientProjectPanel.getCBClient();
	}
	
	public JButton getStartStopBtn()
	{
		return m_btnStartStop;
	}
	
	public TextField getTimerField()
	{
		return m_timerField;
	}
	
	public ButtonCommit getCommitButton()
	{
		return m_btnCommit;
	}
	
//	public TotalHoursCluster getTotalHoursCluster()
	{
		
	}
	
//	public CurrentHoursCluster getCurrentHoursCluster()
	{
		
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "START");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		
		public void actionPerformed(ActionEvent e) 
		{
//			JButton pressedButton = (JButton)e.getSource();
//			if (pressedButton == m_btnStartStop)
//			{
//				if (m_btnStartStop.getText().equals("START"))
//				{
//					m_btnStartStop.setBackground(m_colorRed);
//					m_btnStartStop.setText("STOP");
//					m_btnCommit.setEnabled(true);
//					m_btnCommit.setBackground(m_colorYellow);
//				}
//				else
//				{
//					m_btnStartStop.setBackground(m_colorGreen);
//					m_btnStartStop.setText("START");
//					m_btnCommit.setEnabled(false);
//					m_btnCommit.setBackground(m_colorGrey);
//				}
//			}
//			else
//			{
//				
//			}
		}
	}
}

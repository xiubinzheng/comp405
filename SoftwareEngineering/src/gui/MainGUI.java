package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Component;
import javax.swing.Box;
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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainGUI
{

	private JFrame	frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
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
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		frame = new JFrame();
		frame.setMinimumSize(new Dimension(450, 300));
		frame.setBounds(100, 100, 600, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
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
		
		JMenuItem mntmAddClient = new JMenuItem("Add Client");
		mnClient.add(mntmAddClient);
		
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
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(100, 20));
		panel.setMinimumSize(new Dimension(65, 15));
		panel.setBackground(SystemColor.inactiveCaptionBorder);
		panel.setAlignmentY(Component.CENTER_ALIGNMENT);
		panel.setForeground(SystemColor.textHighlight);
		frame.getContentPane().add(panel, BorderLayout.WEST);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblProjectInfo = new JLabel("Project Info");
		lblProjectInfo.setMaximumSize(new Dimension(75, 20));
		lblProjectInfo.setMinimumSize(new Dimension(65, 15));
		lblProjectInfo.setBackground(SystemColor.controlLtHighlight);
		lblProjectInfo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblProjectInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblProjectInfo.setPreferredSize(new Dimension(70, 15));
		panel.add(lblProjectInfo);
		
		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2, BorderLayout.SOUTH);
		panel_2.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblCurrentTime = new JLabel("Current Time");
		lblCurrentTime.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblCurrentTime.setHorizontalAlignment(SwingConstants.CENTER);
		panel_3.add(lblCurrentTime);
		
		JLabel lblMarchst = new JLabel("March 1st, 2011 12:15:39 pm");
		lblMarchst.setHorizontalAlignment(SwingConstants.CENTER);
		lblMarchst.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel_3.add(lblMarchst);
		
		JLabel lblTimeElapsed = new JLabel("Elapsed Time");
		lblTimeElapsed.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblTimeElapsed.setHorizontalAlignment(SwingConstants.CENTER);
		panel_3.add(lblTimeElapsed);
		
		JLabel label_3 = new JLabel("00:20:00");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel_3.add(label_3);
		
		JButton btnStartstop = new JButton("Start/Stop");
		btnStartstop.setFocusPainted(false);
		btnStartstop.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnStartstop.setForeground(new Color(255, 255, 255));
		btnStartstop.setBackground(new Color(0, 153, 51));
		panel_3.add(btnStartstop);
		
		JButton btnStop = new JButton("Commit");
		btnStop.setFocusPainted(false);
		btnStop.setBackground(new Color(255, 204, 0));
		btnStop.setForeground(new Color(255, 255, 255));
		btnStop.setFont(new Font("Tahoma", Font.BOLD, 20));
		panel_3.add(btnStop);
	}
}

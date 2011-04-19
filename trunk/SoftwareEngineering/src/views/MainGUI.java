package views;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.UIManager;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.UIManager.*;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.Action;

import utilities.*;

import controllers.*;

import models.*;



/**
 * The MainGUI will provide methods of communication between its components.
 *
 */
public class MainGUI
{
	// components
	private        	JFrame    			frame;
	private final 	Action    			action = new SwingAction();
	private			ClientProjectPNL	m_clientProjectPanel;
	private       	JButton			   	m_btnStartStop;
	private       	ClientDBManager   	m_dbManager;
	private			ReportViewDLG		m_htmlViewer;
	
	private JPanel m_startStopPanel;
	private JButton m_btnHTMLViewer;

	// action listenters
	ClientCMBController cbClientListener;
	ProjectCMBController cbProjectListener;
	ReportBTNController m_htmlViewerListener;
	// models
	DefaultComboBoxModel cbClientModel;
	DefaultComboBoxModel cbProjectModel;
	
	Client 			m_currentClient;
	Project 		m_currentProject;
	JTextField 		m_timerField;
	
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
	
	public ClientDBManager getManager()
	{
		return m_dbManager;
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		
		m_dbManager = new ClientDBManager();
		
		try 
		{
			m_dbManager.initializeDB();
		} 
		catch (MyTimeException e) 
		{
			e.printStackTrace();
		}
		// Main Frame initialization is here
		frame = new JFrame();
		frame.setMinimumSize(new Dimension(450, 300));
		frame.setBounds(100, 100, 640, 480);
		frame.setIconImage(new ImageIcon("hourglass.ico").getImage());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout());
		
		
		// Construct GUI components
		m_timerField = new JTextField();
		m_clientProjectPanel = new ClientProjectPNL(this);
		m_btnStartStop = new JButton();
		m_btnHTMLViewer = new JButton();
		m_startStopPanel = new JPanel();

		// Set GUI component attributes
		m_clientProjectPanel.setBounds(0, 0, 640, 100);
		m_clientProjectPanel.setVisible(true);
		
		// Construct Listeners
		cbClientListener = new ClientCMBController();
		cbProjectListener = new ProjectCMBController();
		StartStopBTNController startStopController = new StartStopBTNController(this);
		m_htmlViewerListener = new ReportBTNController();
				
		// Add Managers to GUI components
		
		// Link ActionListeners to the GUI components
		m_clientProjectPanel.getCBClient().addActionListener(cbClientListener);
		m_clientProjectPanel.getCBProject().addActionListener(cbProjectListener);
		m_btnStartStop.addActionListener(startStopController);
		m_btnHTMLViewer.addActionListener(m_htmlViewerListener);
		
		// Add Components to sub-components
		
		// Add Components to the main Frame
		frame.getContentPane().add(m_clientProjectPanel, BorderLayout.NORTH);
		frame.getContentPane().add(m_timerField, BorderLayout.CENTER);
		frame.getContentPane().add(m_btnStartStop, BorderLayout.SOUTH);
		frame.getContentPane().add(m_btnHTMLViewer, BorderLayout.CENTER);
		
		JButton htmlButton_test = new JButton();
		htmlButton_test.addActionListener(m_htmlViewerListener);
		frame.getContentPane().add(htmlButton_test);
		
		
		m_clientProjectPanel.getCBClient().setSelectedIndex(0);
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
	
	public ProjectCMB getCBProject()
	{
		return  m_clientProjectPanel.getCBProject();
	}
	
	public ClientCMB getClientComboBox()
	{
		return m_clientProjectPanel.getCBClient();
	}
	
	public JButton getStartStopBtn()
	{
		return m_btnStartStop;
	}
	
	public JTextField getTimerField()
	{
		return m_timerField;
	}
	
	private class SwingAction extends AbstractAction 
	{
		public SwingAction() 
		{
			putValue(NAME, "START");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		
		public void actionPerformed(ActionEvent e) 
		{
			
		}
	}
}

package views;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.GridLayout;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URL;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.text.EditorKit;

import com.jgoodies.forms.factories.Borders;

import utilities.HTMLReporter;



public class ReportViewPNL extends JPanel
{
	private static final long serialVersionUID = 1L; // what does this do!?
	private MainGUI 		m_mainGUIParent;
	private JEditorPane 	m_pane = new JEditorPane();
	private JButton 		m_beginBtn = new JButton();
	private JButton 		m_endBtn = new JButton();
	private Date 			m_beginDate = new Date();
	private Date 			m_endDate = new Date();
	private JPanel			m_btnFrame = new JPanel();
	
	public ReportViewPNL(MainGUI parent) throws IOException
	{
		m_mainGUIParent = parent;
		
		//just here so that if the panel does not show the frame will be red and we'll know
		setBackground(Color.red);
		
		
		setLayout(new BorderLayout());
		m_btnFrame.setLayout(new GridLayout(1,2));
		
		Writer writer = null;
		
		HTMLReporter dbr = HTMLReporter.getReporterInstance(m_beginDate, m_endDate);
		String s = dbr.generateReport("cssFile.css", "smiley.jpg");
		
		File f = new File("testReport.html");
		writer = new BufferedWriter(new FileWriter(f));
		writer.write(s);
		
		writer.close();
		
		EditorKit kit = m_pane.getEditorKitForContentType("text/html");
		m_pane.setEditorKit(kit);
		m_pane.setEditable(false);
		//m_btnFrame.getContentPane().add(jlbempty, BorderLayout.CENTER);
		m_btnFrame.setVisible(true);
		m_beginBtn.setText("Begin Date");
		m_endBtn.setText("End Date");
		JScrollPane sp = new JScrollPane(m_pane);
		
		add(sp, BorderLayout.CENTER);
		this.add(m_btnFrame, BorderLayout.SOUTH);
		m_btnFrame.add(m_beginBtn, BorderLayout.SOUTH);
		m_btnFrame.add(m_endBtn, BorderLayout.SOUTH);
		
		String fullPath = "file:///"+System.getProperty("user.dir")+"/testReport.html";
		System.out.println(fullPath);
		URL helpURL;

		helpURL = new URL(fullPath);
		m_pane.setPage(helpURL);  
		setVisible(true);
	}
}

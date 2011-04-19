package views;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URL;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.text.EditorKit;


import utilities.GUIUtilities;
import utilities.HTMLReporter;

public class ReportViewPNL extends JPanel
{
	private static final long serialVersionUID = 1L; // what does this do!?
	private MainGUI m_mainGUIParent;
	private JButton m_ok = new JButton("OK");
	private JEditorPane m_pane = new JEditorPane();
	
	public ReportViewPNL(MainGUI parent) throws IOException
	{
		m_mainGUIParent = parent;
		
		Date d1 = new Date();
		Date d2 = new Date();
		Writer writer = null;
		//promptForDate(d1, d2);
		
		HTMLReporter dbr = new HTMLReporter();
		String s = dbr.generateReport("cssFile.css", "smiley.jpg");
		
		File f = new File("testReport.html");
		writer = new BufferedWriter(new FileWriter(f));
		writer.write(s);
		
		writer.close();
		
		create();
	}
	
	public void create() throws IOException 
	{
		//TODO I Don't Know
		//super(Main.WIN, "Report Viewer");
		//setModal(true);
		getRootPane().setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		EditorKit kit = m_pane.getEditorKitForContentType("text/html");
		m_pane.setEditorKit(kit);
		m_pane.setEditable(false);
		JScrollPane sp = new JScrollPane(m_pane);
		setLayout(new BorderLayout());
		//Box buttons = Box.createHorizontalBox();
		//buttons.add(Box.createGlue());
		//buttons.add(m_ok);
		
		//add(buttons, BorderLayout.SOUTH);
		add(sp, BorderLayout.CENTER);
		//m_ok.addActionListener(this);
		
		//Utils.changeFontStyleSizeAndFamily(m_pane, Font.PLAIN, 14, "Arial");
		setSize(950, 750);
		//this.setLocationRelativeTo(getRootPane());
		String fullPath = "file:///"+System.getProperty("user.dir")+"/testReport.html";
		System.out.println(fullPath);
		URL helpURL;
	
		helpURL = new URL(fullPath);
		m_pane.setPage(helpURL);  
		setVisible(true);
	}
}

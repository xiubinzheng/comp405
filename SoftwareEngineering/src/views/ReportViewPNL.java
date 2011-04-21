package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URL;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.text.EditorKit;

import com.jgoodies.forms.factories.Borders;

import utilities.HTMLReporter;



public class ReportViewPNL extends JPanel
{
	private static final long serialVersionUID = 1L; // what does this do!?
	private MainGUI m_mainGUIParent;
	private JPanel m_reportPNL = new JPanel();
	private JEditorPane m_pane = new JEditorPane();
	
	public ReportViewPNL(MainGUI parent) throws IOException
	{
		m_mainGUIParent = parent;
		
		setBackground(Color.red);
		setLayout(new BorderLayout());
		
		Date d1 = new Date();
		Date d2 = new Date();
		Writer writer = null;
		
		HTMLReporter dbr = HTMLReporter.getReporterInstance(d1, d2);
		String s = dbr.generateReport("cssFile.css", "smiley.jpg");
		
		File f = new File("testReport.html");
		writer = new BufferedWriter(new FileWriter(f));
		writer.write(s);
		
		writer.close();
		
		//this.add(m_pane);
		
		EditorKit kit = m_pane.getEditorKitForContentType("text/html");
		m_pane.setEditorKit(kit);
		m_pane.setEditable(false);
		JScrollPane sp = new JScrollPane(m_pane);
		
		add(sp, BorderLayout.CENTER);
		
		String fullPath = "file:///"+System.getProperty("user.dir")+"/testReport.html";
		System.out.println(fullPath);
		URL helpURL;

		helpURL = new URL(fullPath);
		m_pane.setPage(helpURL);  
		setVisible(true);
	}
}

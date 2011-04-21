package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.IOException;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.text.EditorKit;

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
		
		HTMLReporter dbr = new HTMLReporter();
		String s = dbr.generateReport("cssFile.css", "smiley.jpg");
		
		setLayout(new BorderLayout());
		
		this.add(m_pane);
		
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

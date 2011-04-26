package views;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.GridLayout;
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
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.text.EditorKit;

import com.jgoodies.forms.factories.Borders;

import utilities.HTMLReporter;



public class ReportViewPNL extends JPanel implements ActionListener
{
	private MainGUI 		m_mainGUIParent;
	private JEditorPane 	m_pane = new JEditorPane();
	private JButton 		m_dateBtn = new JButton();
	private JButton 		m_endBtn = new JButton();
	DateSelectDLG 			m_datePrompt = new DateSelectDLG();
	//private JPanel			m_btnFrame = new JPanel();
	
	public ReportViewPNL(MainGUI parent) throws IOException
	{
		m_mainGUIParent = parent;
		
		//just here so that if the panel does not show the frame will be red and we'll know
		setBackground(Color.red);
		setLayout(new BorderLayout());
		
		m_datePrompt.getBeginDate().setYear(80);

		m_dateBtn.setText("Set Date");
		m_dateBtn.addActionListener(this);
		
		JScrollPane sp = new JScrollPane(m_pane);
		EditorKit kit = m_pane.getEditorKitForContentType("text/html");
		m_pane.setEditorKit(kit);
		m_pane.setEditable(false);
		add(sp, BorderLayout.CENTER);
		
		refreshReport();

		add(m_dateBtn, BorderLayout.SOUTH);
		
		setVisible(true);
	}
	
	public void refreshReport() throws IOException
	{
		Writer writer = null;
		HTMLReporter dbr = HTMLReporter.getReporterInstance(m_datePrompt.getBeginDate(), m_datePrompt.getEndDate());
		String s = dbr.generateReport("cssFile.css", "smiley.jpg");
		File f = new File("testReport.html");
		writer = new BufferedWriter(new FileWriter(f));
		writer.write(s);
		writer.close();

		
		String fullPath = "file:///"+System.getProperty("user.dir")+"/testReport.html";
		System.out.println(fullPath);
		URL helpURL;

		helpURL = new URL(fullPath);
		m_pane.setPage(helpURL);  
		m_pane.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == m_dateBtn)
		{

			m_datePrompt.setVisible(true);
			try
			{
				refreshReport();
			}
			catch (IOException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}
	
}

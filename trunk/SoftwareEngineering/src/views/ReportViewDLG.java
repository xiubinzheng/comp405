package views;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.EditorKit;

import utilities.HTMLHoursReporter;


//import com.toedter.calendar.*;


public class ReportViewDLG extends JDialog implements ActionListener
{
    /**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private JButton m_ok = new JButton("OK");
	private JEditorPane m_pane = new JEditorPane();

	public static void main(String[] args)
	{
		try
		{
			ReportViewDLG htmlv = new ReportViewDLG();
			htmlv.setVisible(true);
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ReportViewDLG() throws IOException
	{
		Date d1 = new Date();
		Date d2 = new Date();
		Writer writer = null;
		//promptForDate(d1, d2);
		
		HTMLHoursReporter dbr = HTMLHoursReporter.getReporterInstance(d1, d2);
		String s = dbr.generateReport("cssFile.css", "smiley.jpg");
		
		File f = new File("testReport.html");
		writer = new BufferedWriter(new FileWriter(f));
		writer.write(s);
		
		writer.close();
		
		create();
	}
	
   
	/*public void promptForDate(Date d1, Date d2)
	{
	   
		setTitle("Date Range");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

	    Container contentPane = new Container();
	    contentPane.setLayout(new GridLayout(10, 10, 10, 10));

	    // Create a border for all calendars

	    Border etchedBorder = BorderFactory.createEtchedBorder();
	    Border emptyBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
	    Border compoundBorder = BorderFactory.createCompoundBorder(etchedBorder, emptyBorder);

	    // Create a date listener to be used for all calendars

	    //MyDateListener listener = new MyDateListener();

	    // Display date and time using the default calendar and locale.
	    // Display today's date at the bottom.

	    JCalendar calendar1 = new JCalendar();
	    //calendar1.addDateListener(listener);
	   
	    calendar1.setBorder(compoundBorder);
	  
	    JCalendar calendar2 = new JCalendar();
	    //calendar2.addDateListener(listener);
	    JLabel cal1 = new JLabel("Date From: ");
	    JLabel cal2 = new JLabel("Date To: ");
	    calendar2.setBorder(compoundBorder);

	    JPanel panel1 = new JPanel(new FlowLayout());
	    panel1.add(cal1);
	    panel1.add(calendar1);
	    contentPane.add(panel1);
	    
	    JPanel panel2 = new JPanel(new FlowLayout());
	    panel2.add(cal2);
	    panel2.add(calendar2);
	    contentPane.add(panel2);
	    JButton cancel = new JButton("Cancel");
	
	    JButton okay = new JButton("Ok");
	    contentPane.add(cancel);
	    contentPane.add(okay);
	    pack();
	    setVisible(true);
   }*/
   
   public void getChangedDate()
   {
	   /*Calendar c = e.getSelectedDate();
	    if (c != null) {
		System.out.println(c.getTime());
	    }
	    else {
		System.out.println("No time selected.");
	    }*/
   }
   
   public void create() throws IOException
   {
	 //TODO I Don't Know
	   //super(Main.WIN, "Report Viewer");
	   setModal(true);
	   getRootPane().setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
	   EditorKit kit = m_pane.getEditorKitForContentType("text/html");
	   m_pane.setEditorKit(kit);
	   m_pane.setEditable(false);
	   JScrollPane sp = new JScrollPane(m_pane);
	   setLayout(new BorderLayout());
	   Box buttons = Box.createHorizontalBox();
	   buttons.add(Box.createGlue());
	   buttons.add(m_ok);

       add(buttons, BorderLayout.SOUTH);
       add(sp, BorderLayout.CENTER);
       m_ok.addActionListener(this);

       //Utils.changeFontStyleSizeAndFamily(m_pane, Font.PLAIN, 14, "Arial");
       setSize(950, 750);
       this.setLocationRelativeTo(getRootPane());
       String fullPath = "file:///"+System.getProperty("user.dir")+"/testReport.html";
       System.out.println(fullPath);
       URL helpURL;

		helpURL = new URL(fullPath);
		m_pane.setPage(helpURL);  
		setVisible(true);
   }

    public void actionPerformed(ActionEvent arg0)
    {
    	if (arg0.getSource() == m_ok)
    	{
    		dispose();
    	}
    }
}
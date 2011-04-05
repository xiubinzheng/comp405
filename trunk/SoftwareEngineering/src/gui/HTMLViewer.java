package gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

import javax.swing.*;
import javax.swing.text.EditorKit;

import com.toedter.calendar.JCalendar;

public class HTMLViewer extends JDialog implements ActionListener
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
			HTMLViewer htmlv = new HTMLViewer();
			htmlv.setVisible(true);
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
   public HTMLViewer() throws IOException
   {
	   
   }
   
   public void promptForDate(Date d1, Date d2)
   {
	   JPanel p = new JCalendar();
   }
   
   public void create() throws IOException
   {
	 //TODO I Don't Know
	   // super(Main.WIN, "Report Viewer");
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

       
   }

    public void actionPerformed(ActionEvent arg0)
    {
    	if (arg0.getSource() == m_ok)
    	{
    		dispose();
    	}
    }
}
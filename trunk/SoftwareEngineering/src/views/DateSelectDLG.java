package views;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.Border;

import com.toedter.calendar.JCalendar;

public class DateSelectDLG extends JDialog implements ActionListener
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private JPanel 				m_panel = new JPanel();
	private JButton 			m_okBtn = new JButton();
    private JCalendar 			m_beginCal = new JCalendar();
    private JCalendar 			m_endCal = new JCalendar();
    private Date				m_beginDate = new Date();
    private Date				m_endDate = new Date();
    private JButton 			m_cancel = new JButton("Cancel");
    private JButton 			m_ok = new JButton("Ok");
	
	
	public DateSelectDLG()
	{
		setModal(true);
		setLayout(new BorderLayout());
		this.setSize(900, 320);
		this.setLocationRelativeTo(getRootPane());
		this.add(m_panel, BorderLayout.CENTER);
			
		setTitle("Date Range");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	
	    // Create a border for all calendars
	
	    Border etchedBorder = BorderFactory.createEtchedBorder();
	    Border emptyBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
	    Border compoundBorder = BorderFactory.createCompoundBorder(etchedBorder, emptyBorder);
	
	    // Create a date listener to be used for all calendars
	
	    //MyDateListener listener = new MyDateListener();
	
	    // Display date and time using the default calendar and locale.
	    // Display today's date at the bottom.
	    
	    m_beginCal.setDate(m_beginDate);
	    m_endCal.setDate(m_endDate);
	    
	    m_beginCal.setBorder(compoundBorder);
	    m_endCal.setBorder(compoundBorder);

	    m_panel.add(m_beginCal, BorderLayout.WEST);
	    m_panel.add(m_endCal, BorderLayout.EAST);
	    
	    m_panel.add(m_ok, BorderLayout.SOUTH);
	    m_panel.add(m_cancel, BorderLayout.SOUTH);
	    
	    m_ok.addActionListener(this);
	    m_cancel.addActionListener(this);

	    //pack();
	    m_panel.setVisible(true);
	}
	
	public Date getBeginDate()
	{
		return m_beginDate;
	}
	
	public Date getEndDate()
	{
		return m_endDate;
	}


	@Override
	public void actionPerformed(ActionEvent e)
	{
		// TODO Auto-generated method stub
		if(e.getSource() == m_ok)
		{
			m_beginDate = m_beginCal.getDate();
			m_endDate = m_endCal.getDate();
			this.dispose();
		}
		if(e.getSource() == m_cancel)
			this.dispose();
	}
}

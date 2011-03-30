package Controls;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.sql.ResultSet;

import project.Project;
import project.TimeInterval;

import client.Client;
import exceptions.MyTimeException;

import database.DatabaseConnect;

/**
 * 
 * Database Reporter Class
 * 
 * Given a Date range, compile an html string which is a report on
 * every client, project, and time interval within that date range.
 * 
 * This class returns a String filled with all sorts of useful
 * HTML information for all of your various reporting needs.
 */

public class DatabaseReporter 
{
        //private DatabaseConnect m_databaseConnection;
        private static DatabaseReporter m_singleton;
        
        private String g_htmlString = "";
        
        private Date g_dateSpanStart;
        private Date g_dateSpanStop;
        private int g_totalHours = 0;//measured in seconds
        
        
        private DatabaseReporter()
        {
            //construct
            g_dateSpanStart.getTime();
            g_dateSpanStart.setYear(1980); //set to a time before all other times
            
            g_dateSpanStop.getTime();
            
        }
        
        private DatabaseReporter(Date start, Date stop)
        {
            //construct
            g_dateSpanStart = start;
            g_dateSpanStop = stop;
        }
        
        /**
         * 
         *  Returns a singleton instance of the DatabaseReporter class.
         *  
         */
        public static DatabaseReporter getReporterInstance()
        {
                if(m_singleton == null)
                {
                        m_singleton = new DatabaseReporter();
                }
                return m_singleton;
        }
        
        /**  
         * Returns a String with HTML formatting of the current entries in myTimeClients.
         * 
         * @param cssFile The location to the css File
         * @param imageFile The location to the image File
         */
        public String generateReport(String cssFile, String imageFile)
        {
            g_htmlString += "<html>";
            g_htmlString += "<head>";
            g_htmlString += "<link rel = \"stylesheet\" href = \"" + cssFile + "\" type = \"text/css\">\n";
            g_htmlString += "<title>Database Reporter</title>";
            g_htmlString += "</head>";
            g_htmlString += "<img class = \"logo\" src = \"" + imageFile + "\" alt = \"\"/>\n";
            g_htmlString += "<H2 class=\"r_Title\">Client Report</H2>";
            g_htmlString += "<table class=\"report\">";
            
            generateClientTable();
            
            g_htmlString += "</table>\n";
            g_htmlString += "</html>\n";
            
            return g_htmlString;
        }
        
        
        /**
         * Collects the client information
         */
        private void generateClientTable()
        {
                Manager m_manage = new Manager();
                ArrayList<Client> m_client = new ArrayList<Client>();
                
                m_client.clear();
                m_manage.getClients(m_client);
                
                for(Client c : m_client)
                {
                        outputClientHeader();
                        outputClient(c);
                        generateProjectTable(c);
                }
        }
        
        /**
         * Collects the project information for this client
         * 
         * @param c The client object
         */
        private void generateProjectTable(Client c)
        {
                ArrayList<Project> m_project = new ArrayList<Project>();
                int m_totalProjectHours = 0;//measured in seconds
                
                m_project.clear();
                c.getProjectList(m_project);
                
                for(Project p : m_project)
                {
                        outputProjectHeader();
                        outputProject(p);
                        m_totalProjectHours += generateTimeTable(p);
                }
                
                //TODO output total hours
                g_totalHours += m_totalProjectHours;
        }
        
        /**
         * Collects the Time Interval information for this project
         * 
         * @param p The project object
         */
        private int generateTimeTable(Project p)
        {
                ArrayList<TimeInterval> m_time = new ArrayList<TimeInterval>();
                int i = 0;
                
                m_time.clear();
                p.getTimeIntervals(m_time);
                
                for(TimeInterval t : m_time)
                {
                    if(t.getStart().compareTo(g_dateSpanStart) < 0 
                    		&& t.getStop().compareTo(g_dateSpanStop) < 0)
                    {
                        outputTimeHeader();
                        //outputTimeInterval(t);
                        i += t.getStop().getTime() - t.getStart().getTime();
                    }
                }     
                
                return i/1000;//return the number of seconds
        }
        
        
        
        //the purpose of this comment is to waste precious time so i dont have to think about 
        //doing the above method any more than i have to currently, that is to say that i don't 
        //really want to start it with 5 minutes left in the class period.
        private void outputTotalHours(String time)
        {
                g_htmlString += "<td>" + time + "</td>\n";
        }
        private void outputLeftSpan(int span)
        {
                if(span > 0)
                        g_htmlString += "<td colspan=\"" + span + "\"/>\n";
        }
        private void outputClientHeader()//int rightSpan
        {
            g_htmlString += "<td class=\"header\">Client ID</td>\n";
            g_htmlString += "<td class=\"header\">Client Name</td>\n";
            g_htmlString += "<td class=\"header\" colspan=\"2\">Client Description</td>\n";
        }
        private void outputClient(Client c)
        {
            g_htmlString += "<td class=\"c_ID\">" + c.getClientID() + "</td>\n";
            g_htmlString += "<td class=\"c_Name\">" + c.getClientName() + "</td>\n";
            g_htmlString += "<td class=\"c_Description\" colspan=\"2\">" + c.getClientDescription() + "</td>\n";
        }
        private void outputProjectHeader()
        {
            g_htmlString+="<td class=\"header\">Project ID</td>\n";
            g_htmlString+="<td class=\"header\">Project Name</td>\n";
            g_htmlString+="<td class=\"header\" colspan=\"2\">Project Description</td>\n";
        }
        private void outputProject(Project p)//TODO
        {
                //g_htmlString+="<td colspan=\"1\"/>";
            g_htmlString+="<td class=\"p_ID\">" + p.getProjectID() + "</td>\n";
            g_htmlString+="<td class=\"p_Name\">"+ p.getName() +"</td>\n";
            g_htmlString+="<td class=\"p_Description\" colspan=\"2\">" + p.getDescription() + "</td>\n";
        }
        private void outputTimeHeader()
        {
            //g_htmlString+="<td class=\"header\">Date</td>\n";
            g_htmlString+="<td class=\"header\">Start</td>\n";
            g_htmlString+="<td class=\"header\">Stop</td>\n";
            g_htmlString+="<td class=\"header\">Duration</td>\n";
        }
        private void outputTimeIntveral(TimeInterval t)
        {
            g_htmlString += "<td class=\"t_From"+"\">" + t.getStart().toString() + "</td>\n";
            g_htmlString += "<td class=\"t_To"+"\">" + t.getStop().toString()  + "</td>\n";
            g_htmlString += "<td class=\"t_Duration"+"\">" + (t.getStop().getHours() - t.getStart().getHours()) + "</td>\n";
        }
        
        //DD/MM/YYYY HH:MM
        private String formatTime(Date d)
        {
            return d.getDay() + "/" + d.getMonth() + "/" + d.getYear() + " " + d.getHours() + ":" + d.getMinutes();
        }

        
        /*public String reportClient(String cssFile, String imageFile) throws MyTimeException
        {
                String s = "";
                s += "<html>";
                s += "<head>";
                s += "<link rel = \"stylesheet\" href = \"" + cssFile + "\" type = \"text/css\">\n";
                s += "<title>Database Reporter</title>";
                s += "</head>";
                s += "<img class = \"logo\" src = \"" + imageFile + "\" alt = \"\"/>\n";
                s += "<H2 class=\"r_Title\">Client Report</H2>";
                s += "<table class=\"report\">";

                try 
                {       
                        Manager m = new Manager();
                        //m.initializeDB();
                        ArrayList<Client> clientList = new ArrayList<Client>();
                        m.getClients(clientList);
                        //s += generateClientTable(clientList, 0 );
                        
                        for (Client c : clientList)
                        {
                                ArrayList<Project> projectList = new ArrayList<Project>();
                                c.getProjectList(projectList);
                                //String pString = generateProjectTable(projectList, 1); //to fix
                                
                                for (Project p : projectList)
                                {
                                        ArrayList<TimeInterval> intervalList = new ArrayList<TimeInterval>();
                                        p.getTimeIntervals(intervalList);
                                        //String tString = generateTimeTable(intervalList, 2);
                                        //pString.replaceFirst("%timetable%", tString);
                                }
                                
                                //s.replaceFirst("%project%", pString);
                        }
                        
                }
                catch(Exception e)
                {
                        throw new MyTimeException("Could Not Obtain Result Set",e);
                }
                //m_databaseConnection.close();           
                
                s += "</table>\n";
                s += "</html>\n";
                return s;
        }*/
        
        /*private String generateTimeTable(ArrayList<TimeInterval> intervalList, int leftSpan) throws MyTimeException
        {
                String s = "";
                DateFormat df = DateFormat.getDateInstance();
                Date d1 = new Date();
                Date d2 = new Date();
                long minDif = 0;
                int totalTime = 0;
                
                
                try 
                {
                        for(int i=0; i < intervalList.size(); i++)
                        {
                                s += "<tr>";
                                s += outputLeftSpan(leftSpan);
                                
                                d1.setTime(intervalList.get(i).getStart().getTime());
                                d2.setTime(intervalList.get(i).getStop().getTime());
                                minDif=d2.getTime()-d1.getTime();
                                int minutes = (int)(minDif / 1000) / 60;
                                totalTime += minutes;
                                int hours = minutes/60;
                                minutes %= 60;
                                
                                s += outputTimeHours(   "Ronday",
                                                                                df.format(intervalList.get(i).getStart()),
                                                                                df.format(intervalList.get(i).getStop()), 
                                                                                formatHoursAndMinutes(hours, minutes),
                                                                                (i % 2 == 0));
                                s += "</tr>";
                        }
                        s += outputLeftSpan(leftSpan) + outputLeftSpan(3);
                        s += outputTotalHours(formatHoursAndMinutes(totalTime / 60, totalTime % 60));
                } 
                catch (Exception e) 
                {
                        throw new MyTimeException("Unable to read Start Times and End Times", e);
                }
                //Use for later implementing Time Hours row
                //s = outputTimeDate(Date, formatHoursAndMinutes(totalTime/60, totalTime%60)) + s;
                return s;
        }
        */
        
}
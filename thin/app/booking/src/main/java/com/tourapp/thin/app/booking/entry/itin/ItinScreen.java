/**
 * TextPane.java
 *
 * Created on October 23, 2000, 7:19 PM
 */
 
package com.tourapp.thin.app.booking.entry.itin;

import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLFrameHyperlinkEvent;

import org.jbundle.model.util.Util;
import org.jbundle.thin.base.db.Constants;
import org.jbundle.thin.base.db.FieldList;
import org.jbundle.thin.base.db.Params;
import org.jbundle.thin.base.screen.cal.grid.CalendarThinTableModel;
import org.jbundle.util.calendarpanel.event.MyListSelectionEvent;
import org.jbundle.thin.base.screen.util.html.JHtmlView;

import com.tourapp.thin.app.booking.entry.TourAppScreen;

/** 
 *
 * @author  Administrator
 * @version 1.0.0
 */
public class ItinScreen extends JHtmlView
{
	private static final long serialVersionUID = 1L;

	public static String ITIN_DESC = "ItinDesc";
    public static String DEFAULT_TEXT = "<html><body><center><font color=\"blue\">TourApp</font>.<br>"
            + "No current itinerary."
            + "</center></body></html>";

    /**
     * Creates new TextPane
     */
    public ItinScreen()
    {
        super();
    }
    /**
     * Creates new TextPane 
     */
    public ItinScreen(Object obj, Object obj2)
    {
        this();
        this.init(obj, obj2);
    }
    /**
     * Creates new TextPane 
     */
    public void init(Object obj, Object obj2)
    {
        super.init(obj, obj2);
        
        this.doAction(Constants.RESET, 0);
    }
    /**
     * Display the itinerary for this booking.
     * @param strBookingID The booking number.
     */
    public void displayItinerary(String strBookingID)
    {
        if ((strBookingID == null)
            || (strBookingID.length() == 0))
        {
            String strText = this.getBaseApplet().getString(ITIN_DESC);
            if ((strText == null)
                || (strText.length() == 0)
                || (strText.equals(ITIN_DESC)))
                    strText = DEFAULT_TEXT;
            m_editorPane.setHTMLText(strText);
        }
        else
        {
            String strURL = Constants.DEFAULT_XHTML_SERVLET;
            strURL = Util.addURLParam(strURL, Params.SCREEN, "com.tourapp.tour.booking.report.itinerary.ItineraryReportScreen");
            strURL = Util.addURLParam(strURL, "forms", "display");
            strURL = Util.addURLParam(strURL, "template", "itinerary");
            strURL = Util.addURLParam(strURL, "command", "Submit");
            strURL = Util.addURLParam(strURL, "BookingID", strBookingID);
            strURL = Util.addURLParam(strURL, "uniqueURL", Double.toString(Math.random()));
            if (this.getBaseApplet() != null)
            	if (this.getBaseApplet().getApplication() != null)
            		strURL = this.getBaseApplet().getApplication().addUserParamsToURL(strURL);

            this.linkActivated(JHtmlView.getURLFromPath(strURL, this.getBaseApplet()));
            if (this.getBaseApplet() != null)
                this.getBaseApplet().popHistory();      // This should not be considered part of the history
        }
    }
    /**
     * Create the hyperlink listener.
     * @return The hyperlink listener.
     */
    public HyperlinkListener createHyperLinkListener()
    {
        return new HyperlinkListener()
        {
            public void hyperlinkUpdate(HyperlinkEvent e)
            {
                if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED)
                {
                    if (e instanceof HTMLFrameHyperlinkEvent)
                    {
                        ((HTMLDocument)m_editorPane.getDocument()).processHTMLFrameHyperlinkEvent((HTMLFrameHyperlinkEvent)e);
                    } else {
                        String strURL = e.getURL().toString();
                        if ((strURL != null)
                            && (strURL.indexOf("BookingDetail") != -1))
                        {
                            int iStart = strURL.indexOf(Constants.OBJECT_ID + '=') + Constants.OBJECT_ID.length() + 1;
                            if (iStart != -1)
                            {
                                int iEnd = strURL.indexOf('&', iStart);
                                if (iEnd == -1)
                                    iEnd = strURL.length();
                                String strObjectID = strURL.substring(iStart, iEnd);
                                CalendarThinTableModel model = (CalendarThinTableModel)((TourAppScreen)getTargetScreen(TourAppScreen.class)).getCalendarModel();
                                int iRows = model.getRowCount();
                                for (int iRowIndex = 0; iRowIndex < iRows; iRowIndex++)
                                {
                                    FieldList record = model.makeRowCurrent(iRowIndex,  false);
                                    if (record != null)
                                        if (strObjectID.equalsIgnoreCase(record.getCounterField().getString()))
                                        {   // Here it is, tell the model I want to display the detail screen.
                                            model.fireTableRowSelected(this, iRowIndex, MyListSelectionEvent.CONTENT_SELECT | MyListSelectionEvent.CONTENT_CLICK);     // Notify the model of the new selection
                                            break;
                                        }
                                }
                            }
                        }
                        else
                            linkActivated(e.getURL());
                    }
                }
            }
        };
    }
    /**
     * Process this action.
     * @param strAction The command to execute.
     * @return True if handled.
     */
    public boolean doAction(String strAction, int iOptions)
    {
        boolean bAction = false;
        
        if (Constants.RESET.equalsIgnoreCase(strAction))
        {
            TourAppScreen screenMain = (TourAppScreen)this.getTargetScreen(TourAppScreen.class);
            FieldList record = screenMain.getFieldList();
            String strID = record.getField(Params.ID).toString();
            this.displayItinerary(strID);
            return true;
        }
        
        return bAction;
    }
}

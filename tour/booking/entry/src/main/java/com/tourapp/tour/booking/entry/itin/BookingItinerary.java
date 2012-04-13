/**
 * @(#)BookingItinerary.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.booking.entry.itin;

import java.awt.*;
import java.util.*;

import org.jbundle.base.db.*;
import org.jbundle.thin.base.util.*;
import org.jbundle.thin.base.db.*;
import org.jbundle.base.db.event.*;
import org.jbundle.base.db.filter.*;
import org.jbundle.base.field.*;
import org.jbundle.base.field.convert.*;
import org.jbundle.base.field.event.*;
import org.jbundle.base.screen.model.*;
import org.jbundle.base.screen.model.util.*;
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import com.tourapp.tour.booking.entry.base.*;
import com.tourapp.tour.booking.db.*;
import java.net.*;
import org.jbundle.thin.base.screen.*;
import org.jbundle.main.msg.db.*;
import org.jbundle.base.message.core.trx.*;
import org.jbundle.base.message.core.trx.internal.*;
import org.jbundle.thin.base.message.*;
import com.tourapp.tour.booking.detail.db.*;
import com.tourapp.tour.profile.db.*;
import org.jbundle.base.message.trx.processor.*;
import javax.swing.*;

/**
 *  BookingItinerary - Booking itinerary display.
 */
public class BookingItinerary extends BookingSubScreen
{
    public static final String ITINERARY_REPORT_SCREEN = "com.tourapp.tour.booking.report.itinerary.ItineraryReportScreen";
    protected ScreenField m_sHtmlView = null;
    protected Date m_timeLastUpdateDisplayed = null;
    public static final String DEFAULT_TEXT = "<html><body><center><br/>"
                + "No current itinerary."
                + "</center></body></html>";
    public static final String WAIT_TEXT = "<html><head></head><body><center><br/>"
                    + "Please wait... loading itinerary"
                    + "</center></body></html>";
    public static final String ITIN_DESC = "ItinDesc";
    public static final String PRODUCT_DETAIL_PARAM = "productdetail";
    public static final String SEND = "Send";
    public static final String SAVE = "Save";
    /**
     * Default constructor.
     */
    public BookingItinerary()
    {
        super();
    }
    /**
     * Constructor.
     * @param record The main record for this screen.
     * @param itsLocation The location of this component within the parent.
     * @param parentScreen The parent screen.
     * @param fieldConverter The field this screen field is linked to.
     * @param iDisplayFieldDesc Do I display the field desc?
     * @param properties Addition properties to pass to the screen.
     */
    public BookingItinerary(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
    {
        this();
        this.init(record, itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, properties);
    }
    /**
     * Initialize class fields.
     */
    public void init(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
    {
        super.init(record, itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, properties);
    }
    /**
     * Get the screen display title.
     */
    public String getTitle()
    {
        return "Booking itinerary display";
    }
    /**
     * Free Method.
     */
    public void free()
    {
        m_sHtmlView.free();
        super.free();
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        
        ReferenceField fldMessageProcessInfoID = (ReferenceField)this.getScreenRecord().getField(BookingItineraryScreenRecord.MESSAGE_PROCESS_INFO_ID);
        Record recMessageProcessInfo = fldMessageProcessInfoID.getReferenceRecord();
        fldMessageProcessInfoID.addListener(new MoveOnChangeHandler(this.getScreenRecord().getField(BookingItineraryScreenRecord.ITINERARY_STYLE_SHEET), recMessageProcessInfo.getField(MessageProcessInfo.PROPERTIES), false, true)
        {   // Special logic to move the "template" property to the stylesheet field
            /**
             * Do the physical move operation.
             */
            public int moveSourceToDest(boolean bDisplayOption, int iMoveMode)
            {
                String strTemplate = ((PropertiesField)m_fldSource).getProperty(DBParams.TEMPLATE);
                if ((strTemplate == null) || (strTemplate.length() == 0))
                    return DBConstants.NORMAL_RETURN;
                return m_fldDest.setString(strTemplate, bDisplayOption, iMoveMode);   // Move dependent field to here
            }
        });
        
        fldMessageProcessInfoID.addListener(new MoveOnChangeHandler(this.getScreenRecord().getField(BookingItineraryScreenRecord.MESSAGE_TRANSPORT_ID), recMessageProcessInfo.getField(MessageProcessInfo.DEFAULT_MESSAGE_TRANSPORT_ID), false, true));
        
        this.getScreenRecord().getField(BookingItineraryScreenRecord.MESSAGE_TRANSPORT_ID).addListener(new ItineraryActionHandler(null));
        
        if (this.getRecord(Booking.BOOKING_FILE) != null)
        {   // Set up initial default message transport.
            Profile recProfile = (Profile)((ReferenceField)this.getRecord(Booking.BOOKING_FILE).getField(Booking.PROFILE_ID)).getReference();
            if (recProfile != null)
            {
                this.getScreenRecord().getField(BookingItineraryScreenRecord.MESSAGE_TRANSPORT_ID).moveFieldToThis(recProfile.getField(Profile.MESSAGE_TRANSPORT_ID));
                if (this.getScreenRecord().getField(BookingItineraryScreenRecord.MESSAGE_TRANSPORT_ID).isNull())
                {
                    MessageDetailTarget recMessageDetailTarget = recProfile.getNextMessageDetailTarget();
                    if (recMessageDetailTarget != null)
                    {
                        MessageTransport recMessageTransport = recMessageDetailTarget.getMessageTransport(null);
                        if (recMessageTransport != null)
                            this.getScreenRecord().getField(BookingItineraryScreenRecord.MESSAGE_TRANSPORT_ID).moveFieldToThis(recMessageTransport.getField(MessageTransport.ID));
                    }
                }
            }
            
        }
        this.refreshText(null);
        
        if (!ScreenConstants.HTML_SCREEN_TYPE.equalsIgnoreCase(this.getViewFactory().getViewSubpackage()))
            this.getRecord(Tour.TOUR_FILE).setupRecordListener(this, false, false);   // I need to listen for record changes
    }
    /**
     * A record with this datasource handle changed, notify any behaviors that are checking.
     * NOTE: Be very careful as this code is running in an independent thread
     * (synchronize to the task before calling record calls).
     * NOTE: For now, you are only notified of the main record changes.
     * @param message The message to handle.
     * @return The error code.
     */
    public int handleMessage(BaseMessage message)
    {
        int iErrorCode = super.handleMessage(message); // Have the view handle this message.
        class RefreshItin implements Runnable
        {
            BookingItinerary m_screen = null;
            Date m_timeRequested = null;
            public RefreshItin(BookingItinerary screen)
            {
                m_screen = screen;
                m_timeRequested = new Date();
            }
            public void run()
            {
                m_screen.refreshText(m_timeRequested);
            }
        }
        RefreshItin doLater = new RefreshItin(this);
        SwingUtilities.invokeLater(doLater);
        return iErrorCode; // Override this to process change
    }
    /**
     * Add the screen fields.
     * Override this to create (and return) the screen record for this recordowner.
     * @return The screen record.
     */
    public Record addScreenRecord()
    {
        return new BookingItineraryScreenRecord(this);
    }
    /**
     * Set up all the screen fields.
     */
    public void setupSFields()
    {
        ScreenLocation itsLocation = null;
        itsLocation = this.getNextLocation(ScreenConstants.FLUSH_LEFT, ScreenConstants.FILL_REMAINDER);
        // NOTE NOTE NOTE. fieldConverter is only used to create the control, since the actual text is created from reading the URL
        BaseField fieldConverter = this.getScreenRecord().getField(BookingItineraryScreenRecord.ITINERARY_TEXT);
        m_sHtmlView = new SHtmlView(itsLocation, this, fieldConverter, ScreenConstants.DONT_DISPLAY_FIELD_DESC, null);
        ScreenFieldView sView = m_sHtmlView.getScreenFieldView();
        if (sView instanceof org.jbundle.base.screen.view.swing.VScreenField)
        {   // Swing - get rid of scrollers and make transparent and get rid of the border.
            javax.swing.JEditorPane htmlPane = (javax.swing.JEditorPane)sView.getControl();
            htmlPane.setEditable(false);
            ((org.jbundle.base.screen.view.swing.VHtmlView)sView).setupHyperLinkListener(this);
            htmlPane.setOpaque(false);
        
            java.awt.Component component = (java.awt.Component)sView.getControl(DBConstants.CONTROL_TOP);
            ((javax.swing.JScrollPane)component).setBorder(null);
            ((javax.swing.JScrollPane)component).setOpaque(false);
            ((javax.swing.JScrollPane)component).getViewport().setOpaque(false);
        }
        fieldConverter.removeComponent(m_sHtmlView);
        m_sHtmlView.setConverter(null);
    }
    /**
     * Add button(s) to the toolbar.
     */
    public void addToolbarButtons(ToolScreen toolScreen)
    {
        ResourceBundle resources = ((BaseApplication)this.getTask().getApplication()).getResources(ResourceConstants.BOOKING_RESOURCE, true);
        new SCannedBox(toolScreen.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), toolScreen, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, null, resources.getString(MenuConstants.REFRESH), MenuConstants.REFRESH, MenuConstants.REFRESH, null);
        new SCannedBox(toolScreen.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), toolScreen, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, null, resources.getString(SEND), SEND, SEND, null);
        new SCannedBox(toolScreen.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), toolScreen, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, null, resources.getString(MenuConstants.DISPLAY), MenuConstants.DISPLAY, MenuConstants.DISPLAY, null);
        new SCannedBox(toolScreen.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), toolScreen, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, null, resources.getString(MenuConstants.PRINT), MenuConstants.PRINT, MenuConstants.PRINT, null);
        new SCannedBox(toolScreen.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), toolScreen, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, null, resources.getString(SAVE), SAVE, SAVE, null);
        
        this.getScreenRecord().getField(BookingItineraryScreenRecord.ITINERARY_STYLE_SHEET).setupDefaultView(toolScreen.getNextLocation(ScreenConstants.NEXT_INPUT_LOCATION, ScreenConstants.ANCHOR_DEFAULT), toolScreen, ScreenConstants.DEFAULT_DISPLAY);
        new SCannedBox(toolScreen.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), toolScreen, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, null, null, MenuConstants.REFRESH, MenuConstants.REFRESH, resources.getString(MenuConstants.REFRESH));
        this.getScreenRecord().getField(BookingItineraryScreenRecord.MESSAGE_PROCESS_INFO_ID).setupDefaultView(toolScreen.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), toolScreen, ScreenConstants.DONT_DISPLAY_DESC);
        
        this.getScreenRecord().getField(BookingItineraryScreenRecord.ACTION_TARGET).setupDefaultView(toolScreen.getNextLocation(ScreenConstants.NEXT_INPUT_LOCATION, ScreenConstants.ANCHOR_DEFAULT), toolScreen, ScreenConstants.DEFAULT_DISPLAY);
        new SCannedBox(toolScreen.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), toolScreen, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, null, null, SEND, SEND, resources.getString(SEND));
        this.getScreenRecord().getField(BookingItineraryScreenRecord.MESSAGE_TRANSPORT_ID).setupDefaultView(toolScreen.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), toolScreen, ScreenConstants.DONT_DISPLAY_DESC);
    }
    /**
     * RefreshText Method.
     */
    public void refreshText(Date timeUpdateRequested)
    {
        if (timeUpdateRequested == null)
            timeUpdateRequested = new Date();
        if (m_timeLastUpdateDisplayed != null)
            if (timeUpdateRequested.before(m_timeLastUpdateDisplayed))
                return;  // Already showing the current itinerary
        m_timeLastUpdateDisplayed = new Date();
        Record recBooking = this.getRecord(Booking.BOOKING_FILE);
        String strBookingID = recBooking.getCounterField().toString();
        
        if ((strBookingID == null)
            || (strBookingID.length() == 0))
        {
            String strText = null;
            ResourceBundle resources = ((BaseApplication)this.getTask().getApplication()).getResources(ResourceConstants.BOOKING_RESOURCE, true);
            strText = resources.getString(ITIN_DESC);
            if ((strText == null)
                || (strText.length() == 0)
                || (strText.equals(ITIN_DESC)))
                    strText = DEFAULT_TEXT;
                BaseField fieldConverter = this.getScreenRecord().getField(BookingItineraryScreenRecord.ITINERARY_TEXT);
                fieldConverter.addComponent(m_sHtmlView);
                m_sHtmlView.setConverter(fieldConverter);
                m_sHtmlView.setSFieldValue(strText, DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
                fieldConverter.removeComponent(m_sHtmlView);
                m_sHtmlView.setConverter(null);
        }
        else
        {            
            String strTemplate = this.getScreenRecord().getField(BookingItineraryScreenRecord.ITINERARY_STYLE_SHEET).toString();
        
            String strURL = this.getDisplayURL(strBookingID, null, strTemplate, null, null, null);
            
            ScreenFieldView sView = m_sHtmlView.getScreenFieldView();
            if (sView instanceof org.jbundle.base.screen.view.swing.VScreenField)
            {   // Swing - Display new link
                org.jbundle.base.screen.view.swing.VHtmlView sHtml = (org.jbundle.base.screen.view.swing.VHtmlView)sView;
                sHtml.setControlValue(WAIT_TEXT);
                sHtml.linkActivated(this.getURLFromPath(strURL, true));
            }
        }
    }
    /**
     * GetDisplayURL Method.
     */
    public String getDisplayURL(String strBookingID, String strTourID, String strTemplate, String strSendBy, String strDestination, String strFileOut)
    {
        String strURL = Constants.DEFAULT_XHTML_SERVLET;
        strURL = Utility.addURLParam(strURL, DBParams.SCREEN, ITINERARY_REPORT_SCREEN);
        strURL = Utility.addURLParam(strURL, "forms", "display");
        if ((strTemplate == null) || (strTemplate.length() == 0))
            strTemplate = "itinerary";
        strURL = Utility.addURLParam(strURL, DBParams.TEMPLATE, strTemplate);
        strURL = Utility.addURLParam(strURL, DBParams.COMMAND, "Submit");
        if ((strBookingID != null) && (strBookingID.length() > 0))
            strURL = Utility.addURLParam(strURL, "BookingID", strBookingID);
        if ((strTourID != null) && (strTourID.length() > 0))
            strURL = Utility.addURLParam(strURL, "TourID", strTourID); // TourID is the screenfield on the itin report
        strURL = Utility.addURLParam(strURL, "makeUnique", Double.toString(Math.random()));   // Hack, force editor to display new URL
        if (this.getTask() != null)
            if (this.getTask().getApplication() != null)
                strURL = this.getTask().getApplication().addUserParamsToURL(strURL);
        
        // For now, these are handled on the client.
        //if ((strSendBy != null) && (strSendBy.length() > 0))
        //    strURL = Utility.addURLParam(strURL, MessageTransport.SEND_MESSAGE_BY_PARAM, strSendBy);
        //if ((strDestination != null) && (strDestination.length() > 0))
        //    strURL = Utility.addURLParam(strURL, TrxMessageHeader.DESTINATION_PARAM, strDestination);
        //if ((strFileOut != null) && (strFileOut.length() > 0))
        //    strURL = Utility.addURLParam(strURL, DBParams.FILEOUT, strFileOut);
        return strURL;
    }
    /**
     * Get the URL from the path.
     * @param The resource path.
     * @return The URL.
     */
    public URL getURLFromPath(String path, boolean bAddBaseURL)
    {
        URL url = null;
        try {
            if ((url == null) && (path != null))
                url = new URL(path);
        } catch (MalformedURLException ex) {
            BaseApplet applet = (BaseApplet)this.getAppletScreen().getScreenFieldView().getControl();
            BaseApplication app = (BaseApplication)this.getRootScreen().getTask().getApplication();
            if ((url == null) && (path != null))
                url = app.getResourceURL(path, applet);
            else
                ex.printStackTrace();
        }
        if (bAddBaseURL)
        {
            String strURL = url.toString();
            if (strURL.indexOf(path) > 0)   // Make sure the xsl processor creates all the links with a full URL (since emails, etc probably need full URLs)
                path = strURL.substring(0, strURL.indexOf(path));
            else
                path = null;
            if (path != null)
                strURL = Utility.addURLParam(strURL, DBParams.BASE_URL, path);
            try {
                url = new URL(strURL);
            } catch (MalformedURLException e) {
                // Ignore - Leave URL alone
            }
        }
        return url;
    }
    /**
     * Process the command.
     * <br />Step 1 - Process the command if possible and return true if processed.
     * <br />Step 2 - If I can't process, pass to all children (with me as the source).
     * <br />Step 3 - If children didn't process, pass to parent (with me as the source).
     * <br />Note: Never pass to a parent or child that matches the source (to avoid an endless loop).
     * @param strCommand The command to process.
     * @param sourceSField The source screen field (to avoid echos).
     * @param iCommandOptions If this command creates a new screen, create in a new window?
     * @return true if success.
     */
    public boolean doCommand(String strCommand, ScreenField sourceSField, int iCommandOptions)
    {
        Record recBooking = this.getRecord(Booking.BOOKING_FILE);
        String strBookingID = recBooking.getCounterField().toString();
        String strTourID = null;    // For now
        String strTemplate = this.getScreenRecord().getField(BookingItineraryScreenRecord.ITINERARY_STYLE_SHEET).toString();
        
        if (MessageProcessInfoManualField.LOOKUP_WITH_PARAMS.equalsIgnoreCase(strCommand))
        {
            if (this.getTask() != null)
                if (this.getTask().getApplication() != null)
            {
                App application = this.getTask().getApplication();
                BasePanel parentScreen = Screen.makeWindow(application);
                Map<String,Object> properties = new Hashtable<String,Object>();
                ReferenceField fldRequestTypeID = (ReferenceField)this.getScreenRecord().getField(BookingItineraryScreenRecord.REQUEST_TYPE_ID);
                properties.put(fldRequestTypeID.getFieldName(), Integer.toString(fldRequestTypeID.getIDFromCode(RequestType.MANUAL)));
                ReferenceField fldContactTypeID = (ReferenceField)this.getScreenRecord().getField(BookingItineraryScreenRecord.CONTACT_TYPE_ID);
                properties.put(fldContactTypeID.getFieldName(), Integer.toString(fldContactTypeID.getIDFromCode(Profile.PROFILE_FILE)));
                Record record = ((ReferenceField)this.getScreenRecord().getField(BookingItineraryScreenRecord.MESSAGE_PROCESS_INFO_ID)).getReferenceRecord();
                GridScreen screen = (GridScreen)record.makeScreen(null, parentScreen, ScreenConstants.SELECT_MODE, true, true, true, true, properties);
                //x if (record.getScreen() == null)
                    screen.setSelectQuery(record, false); // Since this record isn't linked to the screen, manually link it.
                return true;    // Handled
            }
        }
        
        if ((MenuConstants.REFRESH.equalsIgnoreCase(strCommand))
                || (MenuConstants.PRINT.equalsIgnoreCase(strCommand)))
        {
            this.refreshText(null);
            if (MenuConstants.PRINT.equalsIgnoreCase(strCommand))
            {
                return super.doCommand(MenuConstants.PRINT, m_sHtmlView, iCommandOptions);
            }
            return true;    // Handled
        }
        if (MenuConstants.DISPLAY.equalsIgnoreCase(strCommand))
        {
            String strURL = this.getDisplayURL(strBookingID, strTourID, strTemplate, null, null, null);
            URL url = this.getURLFromPath(strURL, true);
            if (url != null)
                strURL = url.toString();
            ((Application)this.getTask().getApplication()).showTheDocument(strURL, (BaseApplet)this.getAppletScreen().getTask(), ScreenConstants.USE_NEW_WINDOW);
            return true;    // Handled
        }
        if (SAVE.equalsIgnoreCase(strCommand))
        {
            String strFilename = this.getScreenRecord().getField(BookingItineraryScreenRecord.ACTION_TARGET).toString();
        
            String strURL = this.getDisplayURL(strBookingID, strTourID, strTemplate, null, null, strFilename);
            URL url = this.getTask().getApplication().getResourceURL(strURL, (BaseApplet)this.getAppletScreen().getTask());
            strURL = url.toString();
        
            // Now, transfer the URL data to the file or this string.
            String strMessage = Utility.transferURLStream(strURL, strFilename);
            return true;
        }
        if (SEND.equalsIgnoreCase(strCommand))
        {
            MessageProcessInfo recMessageProcessInfo = (MessageProcessInfo)((ReferenceField)this.getScreenRecord().getField(BookingItineraryScreenRecord.MESSAGE_PROCESS_INFO_ID)).getReference();
            Record recMessageTransport = ((ReferenceField)this.getScreenRecord().getField(BookingItineraryScreenRecord.MESSAGE_TRANSPORT_ID)).getReference();
            if ((recMessageTransport == null) || (recMessageTransport.getEditMode() != DBConstants.EDIT_CURRENT))
                if ((recMessageProcessInfo != null) && (recMessageProcessInfo.getEditMode() == DBConstants.EDIT_CURRENT))
                    recMessageTransport = ((ReferenceField)recMessageProcessInfo.getField(MessageProcessInfo.DEFAULT_MESSAGE_TRANSPORT_ID)).getReference();
            String strMessageTransport = null;
            if ((recMessageTransport != null) && (recMessageTransport.getEditMode() == DBConstants.EDIT_CURRENT))
                strMessageTransport = recMessageTransport.getField(MessageTransport.CODE).toString();
            if ((strMessageTransport == null) || (strMessageTransport.length() == 0))
                return false;    // Error
            if (!MessageTransport.SCREEN.equalsIgnoreCase(strMessageTransport))
            {
                String strDestination = this.getScreenRecord().getField(BookingItineraryScreenRecord.ACTION_TARGET).toString();
                
                String strURL = this.getDisplayURL(strBookingID, strTourID, strTemplate, strMessageTransport, strDestination, null);
                {   // Note: properties include SEND_BY and DESTINATION
                    // First see if the use specifies a specific message
                    if (recMessageProcessInfo == null)
                        recMessageProcessInfo = (MessageProcessInfo)((ReferenceField)this.getScreenRecord().getField(BookingItineraryScreenRecord.MESSAGE_PROCESS_INFO_ID)).getReferenceRecord();
                    Profile recProfile = (Profile)((ReferenceField)this.getRecord(Booking.BOOKING_FILE).getField(Booking.PROFILE_ID)).getReference();
                    Map<String,Object> properties =  new Hashtable<String, Object>();
                    properties.put(TrxMessageHeader.REFERENCE_TYPE, recBooking.getTableNames(false));
                    properties.put(TrxMessageHeader.REFERENCE_CLASS, recBooking.getClass().getName());
                    properties.put(TrxMessageHeader.REFERENCE_ID, recBooking.getCounterField().toString());
                    recMessageProcessInfo.createAndSendURLMessage(strMessageTransport, recProfile, strURL, properties);
                }
                return true;
            }
            else
            {
                if (strCommand.indexOf('?') != -1)
                {
                    Map<String,Object> properties = new Hashtable<String,Object>();
                    Utility.parseArgs(properties, strCommand);
                    String strObjectID = (String)properties.get(DBConstants.OBJECT_ID);
                    if ((strObjectID != null) && (strObjectID.length() > 0))
                    {
                        BookingDetail recBookingDetail = new BookingDetail(this);
                        this.removeRecord(recBookingDetail);    // Since I free this screen before I'm done with this record
                        try {
                            Record record = recBookingDetail.setHandle(strObjectID, DBConstants.OBJECT_ID_HANDLE);
                            if (record != null)
                            {   // Switch screens
                                ScreenLocation screenLocation = this.getScreenLocation();
                                if (screenLocation == null)
                                    screenLocation = m_screenParent.getNextLocation(ScreenConstants.FLUSH_LEFT, ScreenConstants.FILL_REMAINDER);
                                BasePanel screenParent = this.getParentScreen();
                                this.free();
                                int iDocMode = ScreenConstants.MAINT_MODE;
                                boolean bCloneThisQuery = true;
                                boolean bReadCurrentRecord = true;
                                boolean bUseBaseTable = false;
                                boolean bLinkGridToQuery = false;
                                record.makeScreen(screenLocation, screenParent, iDocMode, bCloneThisQuery, bReadCurrentRecord, bUseBaseTable, bLinkGridToQuery, null);
                                return true;    // Done!
                            }
                            else
                                return false;   // Not handled?
                        } catch (DBException ex)    {
                            ex.printStackTrace();
                        } finally {
                            recBookingDetail.free();
                        }
                    }
                    return true;    // Handled
                }
            }
        }
        return super.doCommand(strCommand, sourceSField, iCommandOptions);
    }

}

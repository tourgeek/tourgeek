/**
 * @(#)TourEventSchedule.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.product.tour.schedule.db;

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
import org.jbundle.main.db.*;
import com.tourapp.tour.product.tour.schedule.screen.*;
import com.tourapp.tour.profile.db.*;
import com.tourapp.tour.booking.report.itinerary.*;
import org.jbundle.base.message.trx.message.*;
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.booking.db.event.*;
import com.tourapp.tour.product.tour.db.*;
import com.tourapp.tour.product.base.db.*;
import org.jbundle.main.msg.db.*;
import com.tourapp.model.tour.product.tour.schedule.db.*;

/**
 *  TourEventSchedule - Tour operation schedule.
 */
public class TourEventSchedule extends PropertiesRecord
     implements TourEventScheduleModel
{
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor.
     */
    public TourEventSchedule()
    {
        super();
    }
    /**
     * Constructor.
     */
    public TourEventSchedule(RecordOwner screen)
    {
        this();
        this.init(screen);
    }
    /**
     * Initialize class fields.
     */
    public void init(RecordOwner screen)
    {
        super.init(screen);
    }
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(TOUR_EVENT_SCHEDULE_FILE, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the Database Name.
     */
    public String getDatabaseName()
    {
        return "product";
    }
    /**
     * Is this a local (vs remote) file?.
     */
    public int getDatabaseType()
    {
        return DBConstants.LOCAL | DBConstants.USER_DATA;
    }
    /**
     * Make a default screen.
     */
    public ScreenParent makeScreen(ScreenLoc itsLocation, ComponentParent parentScreen, int iDocMode, Map<String,Object> properties)
    {
        ScreenParent screen = null;
        if ((iDocMode & ScreenConstants.MAINT_MODE) == ScreenConstants.MAINT_MODE)
            screen = Record.makeNewScreen(TOUR_EVENT_SCHEDULE_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        if ((iDocMode & ScreenConstants.DISPLAY_MODE) == ScreenConstants.DISPLAY_MODE)
            screen = Record.makeNewScreen(TOUR_EVENT_SCHEDULE_GRID_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        else
            screen = super.makeScreen(itsLocation, parentScreen, iDocMode, properties);
        return screen;
    }
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        //if (iFieldSeq == 0)
        //{
        //  field = new CounterField(this, ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //  field.setHidden(true);
        //}
        //if (iFieldSeq == 1)
        //{
        //  field = new RecordChangedField(this, LAST_CHANGED, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //  field.setHidden(true);
        //}
        //if (iFieldSeq == 2)
        //{
        //  field = new BooleanField(this, DELETED, Constants.DEFAULT_FIELD_LENGTH, null, new Boolean(false));
        //  field.setHidden(true);
        //}
        if (iFieldSeq == 3)
            field = new PropertiesField(this, ACTION_PROPERTIES, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 4)
        {
            field = new TourClassField(this, TOUR_CLASS_ID, 8, null, null);
            field.setHidden(true);
            field.setNullable(false);
        }
        if (iFieldSeq == 5)
        {
            field = new TourEventField(this, TOUR_EVENT_ID, 2, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 6)
            field = new BookingStatusSelect(this, BOOKING_STATUS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 7)
            field = new BooleanField(this, TOUR_CLASS_ONLY, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 8)
        {
            field = new TourActionTypeField(this, TOUR_ACTION_TYPE, 1, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 9)
            field = new TourEventField(this, ACTION_TOUR_EVENT_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 10)
            field = new BookingStatusSelect(this, ACTION_BOOKING_STATUS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 11)
            field = new MessageProcessInfoManualField(this, ACTION_MESSAGE_PROCESS_INFO_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 12)
        {
            field = new MessageTransportManualSelect(this, ACTION_MESSAGE_TRANSPORT_ID, 1, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 13)
            field = new RunProcessInField(this, RUN_PROCESS_IN, Constants.DEFAULT_FIELD_LENGTH, null, "L");
        if (iFieldSeq == 14)
        {
            field = new StringField(this, ACTION_DOCUMENT_NAME, 30, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == 15)
            field = new XmlField(this, ACTION_DOCUMENT_TEXT, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
            field = super.setupField(iFieldSeq);
        return field;
    }
    /**
     * Add this key area description to the Record.
     */
    public KeyArea setupKey(int iKeyArea)
    {
        KeyArea keyArea = null;
        if (iKeyArea == 0)
        {
            keyArea = this.makeIndex(DBConstants.UNIQUE, "ID");
            keyArea.addKeyField(ID, DBConstants.ASCENDING);
        }
        if (iKeyArea == 1)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "TourClassID");
            keyArea.addKeyField(TOUR_CLASS_ID, DBConstants.ASCENDING);
            keyArea.addKeyField(TOUR_EVENT_ID, DBConstants.ASCENDING);
        }
        if (keyArea == null)
            keyArea = super.setupKey(iKeyArea);     
        return keyArea;
    }
    /**
     * AddMasterListeners Method.
     */
    public void addMasterListeners()
    {
        super.addMasterListeners();
        this.addPropertiesFieldBehavior(this.getField(TourEventSchedule.ACTION_DOCUMENT_NAME), DBParams.TEMPLATE);
    }
    /**
     * Do the action for the current record
     * @param recTarget The target booking or tour record for this action.
     */
    public int doAction(Record recTarget)
    {
        Booking recBooking = null;
        Tour recTour = null;
        if (recTarget instanceof Booking)
        {
            recBooking = (Booking)recTarget;
            recTour = (Tour)((ReferenceField)recBooking.getField(Booking.TOUR_ID)).getReference();
        }
        else if (recTarget instanceof Tour)
        {
            recTour = (Tour)recTarget;
        }
        if (TourActionTypeField.TRIGGER_EVENT.equalsIgnoreCase(this.getField(TourEventSchedule.TOUR_ACTION_TYPE).toString()))
            return this.triggerEvent(recBooking, recTour);
        if (TourActionTypeField.CREATE_DOCUMENT.equalsIgnoreCase(this.getField(TourEventSchedule.TOUR_ACTION_TYPE).toString()))
            return this.createDocument(recBooking, recTour);
        return DBConstants.NORMAL_RETURN;
    }
    /**
     * TriggerEvent Method.
     */
    public int triggerEvent(Booking recBooking, Tour recTour)
    {
        switch ((int)this.getField(TourEventSchedule.ACTION_TOUR_EVENT_ID).getValue())
        {
            case TourEvent.BOOKING:
            case TourEvent.BOOKING_STATUS:
            case TourEvent.DEPOSIT_DUE:
            case TourEvent.DEPOSIT_RECEIVED:
            case TourEvent.FINAL_PAY_DUE:
            case TourEvent.FINAL_PAYMENT_RECEIVED:
            case TourEvent.CANCELLATION:
            if (recBooking == null)
                return DBConstants.ERROR_RETURN; // Error = can't do a booking update on a tour action
            default:
            break;  // Okay
        }
        switch ((int)this.getField(TourEventSchedule.ACTION_TOUR_EVENT_ID).getValue())
        {
            case TourEvent.BOOKING:
                recBooking.getField(Booking.BOOKED).setState(true);
                break;
            case TourEvent.BOOKING_STATUS:
                if (!this.getField(TourEventSchedule.ACTION_BOOKING_STATUS_ID).isNull())
                {
                    FieldListener listener = null;
                    if (recBooking.getField(Booking.BOOKING_STATUS_ID).getListener(BookingStatusEventHandler.class) != null)
                        if (!recBooking.getField(Booking.BOOKING_STATUS_ID).getListener(BookingStatusEventHandler.class).isEnabled())
                            recBooking.getField(Booking.BOOKING_STATUS_ID).addListener(listener = new BookingStatusEventHandler(TourEvent.BOOKING_STATUS));
                    recBooking.getField(Booking.BOOKING_STATUS_ID).moveFieldToThis(this.getField(TourEventSchedule.ACTION_BOOKING_STATUS_ID));
                    if (listener != null)
                        listener.free();
                }
                break;
            case TourEvent.DEPOSIT_DUE:
                recBooking.getField(Booking.DEPOSIT_DUE).setState(true);
                break;
            case TourEvent.DEPOSIT_RECEIVED:
                recBooking.getField(Booking.DEPOSIT).setState(true);
                break;
            case TourEvent.FINAL_PAY_DUE:
                recBooking.getField(Booking.FINAL_PAYMENT_DUE).setState(true);
                break;
            case TourEvent.FINAL_PAYMENT_RECEIVED:
                recBooking.getField(Booking.FINAL_PAYMENT_RECEIVED).setState(true);
                break;
            case TourEvent.FINALIZATION:
                recTour.getField(Tour.FINALIZED).setState(true);
                break;
            case TourEvent.TOUR_CLOSED:
                recTour.getField(Tour.CLOSED).setState(true);
                break;
            case TourEvent.ORDER_COMPONENTS:
                recTour.getField(Tour.ORDER_COMPONENTS).setState(true);
                break;
            case TourEvent.FINAL_DOCS:
                recTour.getField(Tour.FINAL_DOCS).setState(true);
                break;
            case TourEvent.TICKETING:
                recTour.getField(Tour.TICKETS).setState(true);
                break;
            case TourEvent.SPECIAL_1:
                recTour.getField(Tour.SP_1).setState(true);
                break;
            case TourEvent.SPECIAL_2:
                recTour.getField(Tour.SP_2).setState(true);
                break;
            case TourEvent.DEPARTURE:
                recTour.getField(Tour.DEPARTED).setState(true);
                break;
            case TourEvent.CANCELLATION:
                recBooking.getField(Booking.CANCELLED).setState(true);
                break;
            case TourEvent.TOUR_CANCELLED:
                recTour.getField(Tour.CANCELLED).setState(true);
                break;
            case TourEvent.SERVICES_CONFIRMED:
                recTour.getField(Tour.SERV_CONF).setState(true);    // todo(don) Shouldn't allow this
                break;
            default:
                return DBConstants.ERROR_RETURN; // Error = can't trigger these!
        }
        // No need to write and refresh - done in TourEventSchedule
        return DBConstants.NORMAL_RETURN;
    }
    /**
     * CreateDocument Method.
     */
    public int createDocument(Booking recBooking, Tour recTour)
    {
        String strBookingID = recBooking.getField(Booking.ID).toString();
        String strTourID = recTour.getField(Tour.ID).toString();
        String strTemplate = ((PropertiesField)this.getField(TourEventSchedule.ACTION_PROPERTIES)).getProperty(DBParams.TEMPLATE);
        MessageProcessInfo recMessageProcessInfo = (MessageProcessInfo)((ReferenceField)this.getField(TourEventSchedule.ACTION_MESSAGE_PROCESS_INFO_ID)).getReference();
        Record recMessageTransport = ((ReferenceField)this.getField(TourEventSchedule.ACTION_MESSAGE_TRANSPORT_ID)).getReference();
        if ((recMessageTransport == null) || (recMessageTransport.getEditMode() != DBConstants.EDIT_CURRENT))
            if ((recMessageProcessInfo != null) && (recMessageProcessInfo.getEditMode() == DBConstants.EDIT_CURRENT))
                recMessageTransport = ((ReferenceField)recMessageProcessInfo.getField(MessageProcessInfo.DEFAULT_MESSAGE_TRANSPORT_ID)).getReference();
        String strMessageTransport = null;
        if ((recMessageTransport != null) && (recMessageTransport.getEditMode() == DBConstants.EDIT_CURRENT))
            strMessageTransport = recMessageTransport.getField(MessageTransport.CODE).toString();
        // If the transport is null, that's okay. The message manager will select a transport
        String strURL = this.getDisplayURL(strBookingID, strTourID, strTemplate);
        {   // Note: properties include SEND_BY and DESTINATION
            // First see if the use specifies a specific message
            if (recMessageProcessInfo == null)
                recMessageProcessInfo = (MessageProcessInfo)((ReferenceField)this.getField(TourEventSchedule.ACTION_MESSAGE_PROCESS_INFO_ID)).getReferenceRecord();
            Profile recProfile = (Profile)((ReferenceField)recBooking.getField(Booking.PROFILE_ID)).getReference();
            if ((recProfile.getEditMode() == DBConstants.EDIT_NONE) || (recProfile.getEditMode() == DBConstants.EDIT_ADD))
                recProfile = null;
            Map<String,Object> properties =  new Hashtable<String, Object>();
            properties.put(TrxMessageHeader.REFERENCE_TYPE, recBooking.getTableNames(false));
            properties.put(TrxMessageHeader.REFERENCE_CLASS, recBooking.getClass().getName());
            properties.put(TrxMessageHeader.REFERENCE_ID, recBooking.getCounterField().toString());
            if (recProfile == null)
            {
                if (recBooking.getField(Booking.EMAIL).isNull())
                    return this.getTask().setLastError(this.getTask().getString("No email address in booking"));
                else
                {
                    properties.put(TrxMessageHeader.DESTINATION_PARAM, recBooking.getField(Booking.EMAIL).toString());
                    properties.put(MessageTransport.SEND_MESSAGE_BY_PARAM, MessageTransport.EMAIL);
                }
            }
            recMessageProcessInfo.createAndSendURLMessage(strMessageTransport, recProfile, strURL, properties);
        }
        return DBConstants.NORMAL_RETURN;
    }
    /**
     * GetDisplayURL Method.
     */
    public String getDisplayURL(String strBookingID, String strTourID, String strTemplate)
    {
        RecordOwner recordOwner = this.getRecordOwner();
        String strURL = DBConstants.DEFAULT_XHTML_SERVLET;
        if (recordOwner instanceof BasePanel)
            strURL = ((BasePanel)recordOwner).getServletPath(DBParams.XHTMLSERVLET);
        strURL = Utility.addURLParam(strURL, DBParams.SCREEN, ItineraryReportScreen.class.getName());
        strURL = Utility.addURLParam(strURL, "forms", "display");
        if ((strTemplate == null) || (strTemplate.length() == 0))
            strTemplate = "itinerary";
        strURL = Utility.addURLParam(strURL, DBParams.TEMPLATE, strTemplate);
        strURL = Utility.addURLParam(strURL, DBParams.COMMAND, "Submit");
        if ((strBookingID != null) && (strBookingID.length() < 0))
            strURL = Utility.addURLParam(strURL, "Booking.ID", strBookingID);
        strURL = Utility.addURLParam(strURL, "TourID", strTourID);  // TourID is the screen field for this report
        if (this.getTask() != null)
            if (this.getTask().getApplication() != null)
                strURL = this.getTask().getApplication().addUserParamsToURL(strURL);
        return strURL;
    }

}

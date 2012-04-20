/**
 * @(#)TourEventSchedule.
 * Copyright © 2012 tourapp.com. All rights reserved.
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
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import org.jbundle.main.db.*;
import org.jbundle.base.message.core.trx.*;
import com.tourapp.model.tour.profile.db.*;
import com.tourapp.model.tour.booking.db.*;
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

    public static final String ITINERARY_REPORT_SCREEN = "com.tourapp.tour.booking.report.itinerary.ItineraryReportScreen";
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
        else if ((iDocMode & ScreenConstants.DISPLAY_MODE) == ScreenConstants.DISPLAY_MODE)
            screen = Record.makeNewScreen(TOUR_EVENT_SCHEDULE_GRID_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        else if ((iDocMode & ScreenConstants.MAINT_MODE) == ScreenConstants.MAINT_MODE)
            screen = Record.makeNewScreen(ITINERARY_REPORT_SCREEN, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
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
        BookingModel recBooking = null;
        TourModel recTour = null;
        if (recTarget instanceof BookingModel)
        {
            recBooking = (BookingModel)recTarget;
            recTour = (TourModel)((ReferenceField)recBooking.getField(BookingModel.TOUR_ID)).getReference();
        }
        else if (recTarget instanceof TourModel)
        {
            recTour = (TourModel)recTarget;
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
    public int triggerEvent(BookingModel recBooking, TourModel recTour)
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
                recBooking.getField(BookingModel.BOOKED).setState(true);
                break;
            case TourEvent.BOOKING_STATUS:
                if (!this.getField(TourEventSchedule.ACTION_BOOKING_STATUS_ID).isNull())
                {
                    FieldListener listener = null;
                    if (((Record)recBooking).getField(BookingModel.BOOKING_STATUS_ID).getListener(BookingStatusEventHandler.class) != null)
                        if (!((Record)recBooking).getField(BookingModel.BOOKING_STATUS_ID).getListener(BookingStatusEventHandler.class).isEnabled())
                            ((BaseField)recBooking.getField(BookingModel.BOOKING_STATUS_ID)).addListener(listener = new BookingStatusEventHandler(TourEvent.BOOKING_STATUS));
                    ((Record)recBooking).getField(BookingModel.BOOKING_STATUS_ID).moveFieldToThis(this.getField(TourEventSchedule.ACTION_BOOKING_STATUS_ID));
                    if (listener != null)
                        listener.free();
                }
                break;
            case TourEvent.DEPOSIT_DUE:
                recBooking.getField(BookingModel.DEPOSIT_DUE).setState(true);
                break;
            case TourEvent.DEPOSIT_RECEIVED:
                recBooking.getField(BookingModel.DEPOSIT).setState(true);
                break;
            case TourEvent.FINAL_PAY_DUE:
                recBooking.getField(BookingModel.FINAL_PAYMENT_DUE).setState(true);
                break;
            case TourEvent.FINAL_PAYMENT_RECEIVED:
                recBooking.getField(BookingModel.FINAL_PAYMENT_RECEIVED).setState(true);
                break;
            case TourEvent.FINALIZATION:
                recTour.getField(TourModel.FINALIZED).setState(true);
                break;
            case TourEvent.TOUR_CLOSED:
                recTour.getField(TourModel.CLOSED).setState(true);
                break;
            case TourEvent.ORDER_COMPONENTS:
                recTour.getField(TourModel.ORDER_COMPONENTS).setState(true);
                break;
            case TourEvent.FINAL_DOCS:
                recTour.getField(TourModel.FINAL_DOCS).setState(true);
                break;
            case TourEvent.TICKETING:
                recTour.getField(TourModel.TICKETS).setState(true);
                break;
            case TourEvent.SPECIAL_1:
                recTour.getField(TourModel.SP_1).setState(true);
                break;
            case TourEvent.SPECIAL_2:
                recTour.getField(TourModel.SP_2).setState(true);
                break;
            case TourEvent.DEPARTURE:
                recTour.getField(TourModel.DEPARTED).setState(true);
                break;
            case TourEvent.CANCELLATION:
                recBooking.getField(BookingModel.CANCELLED).setState(true);
                break;
            case TourEvent.TOUR_CANCELLED:
                recTour.getField(TourModel.CANCELLED).setState(true);
                break;
            case TourEvent.SERVICES_CONFIRMED:
                recTour.getField(TourModel.SERV_CONF).setState(true);    // todo(don) Shouldn't allow this
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
    public int createDocument(BookingModel recBooking, TourModel recTour)
    {
        String strBookingID = recBooking.getField(BookingModel.ID).toString();
        String strTourID = recTour.getField(TourModel.ID).toString();
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
            ProfileModel recProfile = (ProfileModel)((ReferenceField)recBooking.getField(BookingModel.PROFILE_ID)).getReference();
            if ((recProfile.getEditMode() == DBConstants.EDIT_NONE) || (recProfile.getEditMode() == DBConstants.EDIT_ADD))
                recProfile = null;
            Map<String,Object> properties =  new Hashtable<String, Object>();
            properties.put(TrxMessageHeader.REFERENCE_TYPE, recBooking.getTableNames(false));
            properties.put(TrxMessageHeader.REFERENCE_CLASS, recBooking.getClass().getName());
            properties.put(TrxMessageHeader.REFERENCE_ID, recBooking.getCounterField().toString());
            if (recProfile == null)
            {
                if (recBooking.getField(BookingModel.EMAIL).isNull())
                    return this.getTask().setLastError(this.getTask().getString("No email address in booking"));
                else
                {
                    properties.put(TrxMessageHeader.DESTINATION_PARAM, recBooking.getField(BookingModel.EMAIL).toString());
                    properties.put(MessageTransport.SEND_MESSAGE_BY_PARAM, MessageTransport.EMAIL);
                }
            }
            recMessageProcessInfo.createAndSendURLMessage(strMessageTransport, (MessageDetailTarget)recProfile, strURL, properties);
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
        if (recordOwner instanceof ScreenModel)
            strURL = Utility.getServletPath(recordOwner.getTask(), DBParams.XHTMLSERVLET);
        strURL = Utility.addURLParam(strURL, DBParams.SCREEN, ITINERARY_REPORT_SCREEN);
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

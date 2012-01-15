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
import org.jbundle.base.util.*;
import org.jbundle.model.*;
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

    //public static final int kID = kID;
    public static final int kActionProperties = kProperties;
    public static final int kTourClassID = kPropertiesRecordLastField + 1;
    public static final int kTourEventID = kTourClassID + 1;
    public static final int kBookingStatusID = kTourEventID + 1;
    public static final int kTourClassOnly = kBookingStatusID + 1;
    public static final int kTourActionType = kTourClassOnly + 1;
    public static final int kActionTourEventID = kTourActionType + 1;
    public static final int kActionBookingStatusID = kActionTourEventID + 1;
    public static final int kActionMessageProcessInfoID = kActionBookingStatusID + 1;
    public static final int kActionMessageTransportID = kActionMessageProcessInfoID + 1;
    public static final int kRunProcessIn = kActionMessageTransportID + 1;
    public static final int kActionDocumentName = kRunProcessIn + 1;
    public static final int kActionDocumentText = kActionDocumentName + 1;
    public static final int kTourEventScheduleLastField = kActionDocumentText;
    public static final int kTourEventScheduleFields = kActionDocumentText - DBConstants.MAIN_FIELD + 1;

    public static final int kIDKey = DBConstants.MAIN_KEY_FIELD;
    public static final int kTourClassIDKey = kIDKey + 1;
    public static final int kTourEventScheduleLastKey = kTourClassIDKey;
    public static final int kTourEventScheduleKeys = kTourClassIDKey - DBConstants.MAIN_KEY_FIELD + 1;
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

    public static final String kTourEventScheduleFile = "TourEventSchedule";
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(kTourEventScheduleFile, bAddQuotes) : super.getTableNames(bAddQuotes);
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
    public BaseScreen makeScreen(ScreenLocation itsLocation, BasePanel parentScreen, int iDocMode, Map<String,Object> properties)
    {
        BaseScreen screen = null;
        if ((iDocMode & ScreenConstants.MAINT_MODE) == ScreenConstants.MAINT_MODE)
            screen = BaseScreen.makeNewScreen(TOUR_EVENT_SCHEDULE_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        if ((iDocMode & ScreenConstants.DISPLAY_MODE) == ScreenConstants.DISPLAY_MODE)
            screen = BaseScreen.makeNewScreen(TOUR_EVENT_SCHEDULE_GRID_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
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
        //if (iFieldSeq == kID)
        //{
        //  field = new CounterField(this, "ID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //  field.setHidden(true);
        //}
        if (iFieldSeq == kTourClassID)
        {
            field = new TourClassField(this, "TourClassID", 8, null, null);
            field.setHidden(true);
            field.setNullable(false);
        }
        if (iFieldSeq == kTourEventID)
        {
            field = new TourEventField(this, "TourEventID", 2, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kBookingStatusID)
            field = new BookingStatusSelect(this, "BookingStatusID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kTourClassOnly)
            field = new BooleanField(this, "TourClassOnly", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kTourActionType)
        {
            field = new TourActionTypeField(this, "TourActionType", 1, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kActionTourEventID)
            field = new TourEventField(this, "ActionTourEventID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kActionBookingStatusID)
            field = new BookingStatusSelect(this, "ActionBookingStatusID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kActionMessageProcessInfoID)
            field = new MessageProcessInfoManualField(this, "ActionMessageProcessInfoID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kActionMessageTransportID)
        {
            field = new MessageTransportManualSelect(this, "ActionMessageTransportID", 1, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kRunProcessIn)
            field = new RunProcessInField(this, "RunProcessIn", Constants.DEFAULT_FIELD_LENGTH, null, "L");
        if (iFieldSeq == kActionDocumentName)
        {
            field = new StringField(this, "ActionDocumentName", 30, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == kActionDocumentText)
            field = new XmlField(this, "ActionDocumentText", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kActionProperties)
            field = new PropertiesField(this, "ActionProperties", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kTourEventScheduleLastField)
                field = new EmptyField(this);
        }
        return field;
    }
    /**
     * Add this key area description to the Record.
     */
    public KeyArea setupKey(int iKeyArea)
    {
        KeyArea keyArea = null;
        if (iKeyArea == kIDKey)
        {
            keyArea = this.makeIndex(DBConstants.UNIQUE, "PrimaryKey");
            keyArea.addKeyField(kID, DBConstants.ASCENDING);
        }
        if (iKeyArea == kTourClassIDKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "TourClassID");
            keyArea.addKeyField(kTourClassID, DBConstants.ASCENDING);
            keyArea.addKeyField(kTourEventID, DBConstants.ASCENDING);
        }
        if (keyArea == null) if (iKeyArea < kTourEventScheduleLastKey)
        {
            keyArea = super.setupKey(iKeyArea);     
            if (keyArea == null) if (iKeyArea < kTourEventScheduleLastKey)
                keyArea = new EmptyKey(this);
        }
        return keyArea;
    }
    /**
     * AddMasterListeners Method.
     */
    public void addMasterListeners()
    {
        super.addMasterListeners();
        this.addPropertiesFieldBehavior(this.getField(TourEventSchedule.kActionDocumentName), DBParams.TEMPLATE);
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
            recTour = (Tour)((ReferenceField)recBooking.getField(Booking.kTourID)).getReference();
        }
        else if (recTarget instanceof Tour)
        {
            recTour = (Tour)recTarget;
        }
        if (TourActionTypeField.TRIGGER_EVENT.equalsIgnoreCase(this.getField(TourEventSchedule.kTourActionType).toString()))
            return this.triggerEvent(recBooking, recTour);
        if (TourActionTypeField.CREATE_DOCUMENT.equalsIgnoreCase(this.getField(TourEventSchedule.kTourActionType).toString()))
            return this.createDocument(recBooking, recTour);
        return DBConstants.NORMAL_RETURN;
    }
    /**
     * TriggerEvent Method.
     */
    public int triggerEvent(Booking recBooking, Tour recTour)
    {
        switch ((int)this.getField(TourEventSchedule.kActionTourEventID).getValue())
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
        switch ((int)this.getField(TourEventSchedule.kActionTourEventID).getValue())
        {
            case TourEvent.BOOKING:
                recBooking.getField(Booking.kBooked).setState(true);
                break;
            case TourEvent.BOOKING_STATUS:
                if (!this.getField(TourEventSchedule.kActionBookingStatusID).isNull())
                {
                    FieldListener listener = null;
                    if (recBooking.getField(Booking.kBookingStatusID).getListener(BookingStatusEventHandler.class) != null)
                        if (!recBooking.getField(Booking.kBookingStatusID).getListener(BookingStatusEventHandler.class).isEnabled())
                            recBooking.getField(Booking.kBookingStatusID).addListener(listener = new BookingStatusEventHandler(TourEvent.BOOKING_STATUS));
                    recBooking.getField(Booking.kBookingStatusID).moveFieldToThis(this.getField(TourEventSchedule.kActionBookingStatusID));
                    if (listener != null)
                        listener.free();
                }
                break;
            case TourEvent.DEPOSIT_DUE:
                recBooking.getField(Booking.kDepositDue).setState(true);
                break;
            case TourEvent.DEPOSIT_RECEIVED:
                recBooking.getField(Booking.kDeposit).setState(true);
                break;
            case TourEvent.FINAL_PAY_DUE:
                recBooking.getField(Booking.kFinalPaymentDue).setState(true);
                break;
            case TourEvent.FINAL_PAYMENT_RECEIVED:
                recBooking.getField(Booking.kFinalPaymentReceived).setState(true);
                break;
            case TourEvent.FINALIZATION:
                recTour.getField(Tour.kFinalized).setState(true);
                break;
            case TourEvent.TOUR_CLOSED:
                recTour.getField(Tour.kClosed).setState(true);
                break;
            case TourEvent.ORDER_COMPONENTS:
                recTour.getField(Tour.kOrderComponents).setState(true);
                break;
            case TourEvent.FINAL_DOCS:
                recTour.getField(Tour.kFinalDocs).setState(true);
                break;
            case TourEvent.TICKETING:
                recTour.getField(Tour.kTickets).setState(true);
                break;
            case TourEvent.SPECIAL_1:
                recTour.getField(Tour.kSp1).setState(true);
                break;
            case TourEvent.SPECIAL_2:
                recTour.getField(Tour.kSp2).setState(true);
                break;
            case TourEvent.DEPARTURE:
                recTour.getField(Tour.kDeparted).setState(true);
                break;
            case TourEvent.CANCELLATION:
                recBooking.getField(Booking.kCancelled).setState(true);
                break;
            case TourEvent.TOUR_CANCELLED:
                recTour.getField(Tour.kCancelled).setState(true);
                break;
            case TourEvent.SERVICES_CONFIRMED:
                recTour.getField(Tour.kServConf).setState(true);    // todo(don) Shouldn't allow this
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
        String strBookingID = recBooking.getField(Booking.kID).toString();
        String strTourID = recTour.getField(Tour.kID).toString();
        String strTemplate = ((PropertiesField)this.getField(TourEventSchedule.kActionProperties)).getProperty(DBParams.TEMPLATE);
        MessageProcessInfo recMessageProcessInfo = (MessageProcessInfo)((ReferenceField)this.getField(TourEventSchedule.kActionMessageProcessInfoID)).getReference();
        Record recMessageTransport = ((ReferenceField)this.getField(TourEventSchedule.kActionMessageTransportID)).getReference();
        if ((recMessageTransport == null) || (recMessageTransport.getEditMode() != DBConstants.EDIT_CURRENT))
            if ((recMessageProcessInfo != null) && (recMessageProcessInfo.getEditMode() == DBConstants.EDIT_CURRENT))
                recMessageTransport = ((ReferenceField)recMessageProcessInfo.getField(MessageProcessInfo.kDefaultMessageTransportID)).getReference();
        String strMessageTransport = null;
        if ((recMessageTransport != null) && (recMessageTransport.getEditMode() == DBConstants.EDIT_CURRENT))
            strMessageTransport = recMessageTransport.getField(MessageTransport.kCode).toString();
        // If the transport is null, that's okay. The message manager will select a transport
        String strURL = this.getDisplayURL(strBookingID, strTourID, strTemplate);
        {   // Note: properties include SEND_BY and DESTINATION
            // First see if the use specifies a specific message
            if (recMessageProcessInfo == null)
                recMessageProcessInfo = (MessageProcessInfo)((ReferenceField)this.getField(TourEventSchedule.kActionMessageProcessInfoID)).getReferenceRecord();
            Profile recProfile = (Profile)((ReferenceField)recBooking.getField(Booking.kProfileID)).getReference();
            if ((recProfile.getEditMode() == DBConstants.EDIT_NONE) || (recProfile.getEditMode() == DBConstants.EDIT_ADD))
                recProfile = null;
            Map<String,Object> properties =  new Hashtable<String, Object>();
            properties.put(TrxMessageHeader.REFERENCE_TYPE, recBooking.getTableNames(false));
            properties.put(TrxMessageHeader.REFERENCE_CLASS, recBooking.getClass().getName());
            properties.put(TrxMessageHeader.REFERENCE_ID, recBooking.getCounterField().toString());
            if (recProfile == null)
            {
                if (recBooking.getField(Booking.kEmail).isNull())
                    return this.getTask().setLastError(this.getTask().getString("No email address in booking"));
                else
                {
                    properties.put(TrxMessageHeader.DESTINATION_PARAM, recBooking.getField(Booking.kEmail).toString());
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

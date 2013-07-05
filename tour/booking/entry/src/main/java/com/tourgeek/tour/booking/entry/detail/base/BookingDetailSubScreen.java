/**
  * @(#)BookingDetailSubScreen.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.tour.booking.entry.detail.base;

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
import com.tourgeek.tour.booking.entry.base.*;
import com.tourgeek.tour.booking.detail.db.*;
import com.tourgeek.tour.booking.db.*;
import com.tourgeek.tour.product.base.db.*;
import org.jbundle.main.msg.db.*;
import org.jbundle.main.db.base.*;

/**
 *  BookingDetailSubScreen - Base screen for all booking detail screens.
 */
public class BookingDetailSubScreen extends BookingSubScreen
{
    /**
     * Default constructor.
     */
    public BookingDetailSubScreen()
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
    public BookingDetailSubScreen(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
        return "Base screen for all booking detail screens";
    }
    /**
     * Add the screen fields.
     * Override this to create (and return) the screen record for this recordowner.
     * @return The screen record.
     */
    public Record addScreenRecord()
    {
        return new BookingDetailScreenRecord(this); // Typically this is overidden by a screen specific override of this screen record
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        
        BookingDetail recBookingDetail = (BookingDetail)this.getMainRecord();
        Booking recBooking = (Booking)this.getRecord(Booking.BOOKING_FILE);
        Tour recTour = (Tour)this.getRecord(Tour.TOUR_FILE);
        recBookingDetail.addDetailBehaviors(recBooking, recTour);
        // Make sure you have a valid handle before performing any price etc lookups.
        recBookingDetail.setOpenMode(recBookingDetail.getOpenMode() | DBConstants.OPEN_REFRESH_AND_LOCK_ON_CHANGE_STRATEGY);
        
        this.setEnabled(false);
        recBookingDetail.getField(BookingDetail.PRODUCT_ID).setEnabled(true);
        recBookingDetail.getField(BookingDetail.DETAIL_DATE).setEnabled(true);
        recBookingDetail.getField(BookingDetail.RATE_ID).setEnabled(true);
        recBookingDetail.getField(BookingDetail.CLASS_ID).setEnabled(true);
        recBookingDetail.getField(BookingDetail.INFO_MESSAGE_TRANSPORT_ID).setEnabled(true);
        recBookingDetail.getField(BookingDetail.COST_MESSAGE_TRANSPORT_ID).setEnabled(true);
        recBookingDetail.getField(BookingDetail.INVENTORY_MESSAGE_TRANSPORT_ID).setEnabled(true);
        recBookingDetail.getField(BookingDetail.PRODUCT_MESSAGE_TRANSPORT_ID).setEnabled(true);
        recBookingDetail.getField(BookingDetail.ACK_DAYS).setEnabled(true);
        recBookingDetail.getField(BookingDetail.DESCRIPTION).setEnabled(true);
        
        Record recProduct =((ReferenceField)recBookingDetail.getField(BookingDetail.PRODUCT_ID)).getReferenceRecord();
        for (int i = 0; i < this.getSFieldCount(); i++)
        {
            ScreenField sField = this.getSField(i);
            if (sField instanceof SCannedBox)
            {
                String strCommand = ((SCannedBox)sField).getButtonCommand();
                if ((Product.INVENTORY_DETAIL.equals(strCommand))
                    || (Product.PRICING_DETAIL.equals(strCommand)))
                        recProduct.addListener(new EnableOnValidHandler(sField, true, false));
                if (MessageLog.MESSAGE_LOG_FILE.equals(strCommand))
                    recProduct.addListener(new EnableOnValidHandler(sField, true, false));
            }
        }
        
        ((BaseStatusField)recBookingDetail.getField(BookingDetail.INFO_STATUS_ID)).getIconField(null).addListener(new BookingDetailStatusUpdateHandler(null));
        ((BaseStatusField)recBookingDetail.getField(BookingDetail.INVENTORY_STATUS_ID)).getIconField(null).addListener(new BookingDetailStatusUpdateHandler(null));
        ((BaseStatusField)recBookingDetail.getField(BookingDetail.COST_STATUS_ID)).getIconField(null).addListener(new BookingDetailStatusUpdateHandler(null));
        ((BaseStatusField)recBookingDetail.getField(BookingDetail.PRODUCT_STATUS_ID)).getIconField(null).addListener(new BookingDetailStatusUpdateHandler(null));
        
        recBookingDetail.getField(BookingDetail.COST_STATUS_ID).addListener(new DisableOnFieldHandler(recBookingDetail.getField(BookingDetail.MARKUP_FROM_LAST), Integer.toString(BaseStatus.NOT_VALID), false)
        {
            public boolean compareFieldToString()
            {
                boolean bMatch = super.compareFieldToString();
                if (!bMatch)
                    if (!this.getOwner().getRecord().getField(BookingDetail.MARKUP_FROM_LAST).isNull())
                        bMatch = true;
                return bMatch;
            }
        });
        
        String strManualTransportID = Integer.toString(((ReferenceField)recBookingDetail.getField(BookingDetail.COST_MESSAGE_TRANSPORT_ID)).getIDFromCode(MessageTransport.MANUAL));
        
        CheckConverter convCheckMark = new CheckConverter(recBookingDetail.getField(BookingDetail.INFO_MESSAGE_TRANSPORT_ID), strManualTransportID, null, true);
        recBookingDetail.getField(BookingDetail.INFO_MESSAGE_TRANSPORT_ID).addListener(new RemoveConverterOnFreeHandler(convCheckMark));
        recBookingDetail.getField(BookingDetail.INFO_MESSAGE_TRANSPORT_ID).addListener(new CopyStringHandler(recBookingDetail.getField(BookingDetail.INFO_STATUS_ID), Integer.toString(BaseStatus.VALID), convCheckMark));
        
        recBookingDetail.getField(BookingDetail.INFO_MESSAGE_TRANSPORT_ID).addListener(new CopyStringHandler(recBookingDetail.getField(BookingDetail.INVENTORY_MESSAGE_TRANSPORT_ID), strManualTransportID, convCheckMark));
        recBookingDetail.getField(BookingDetail.INFO_MESSAGE_TRANSPORT_ID).addListener(new CopyStringHandler(recBookingDetail.getField(BookingDetail.COST_MESSAGE_TRANSPORT_ID), strManualTransportID, convCheckMark));
        recBookingDetail.getField(BookingDetail.INFO_MESSAGE_TRANSPORT_ID).addListener(new CopyStringHandler(recBookingDetail.getField(BookingDetail.PRODUCT_MESSAGE_TRANSPORT_ID), strManualTransportID, convCheckMark));
        
        convCheckMark = new CheckConverter(recBookingDetail.getField(BookingDetail.INVENTORY_MESSAGE_TRANSPORT_ID), strManualTransportID, null, true);
        recBookingDetail.getField(BookingDetail.INVENTORY_MESSAGE_TRANSPORT_ID).addListener(new RemoveConverterOnFreeHandler(convCheckMark));
        recBookingDetail.getField(BookingDetail.INVENTORY_MESSAGE_TRANSPORT_ID).addListener(new CopyStringHandler(recBookingDetail.getField(BookingDetail.INVENTORY_STATUS_ID), Integer.toString(BaseStatus.VALID), convCheckMark));
        convCheckMark = new CheckConverter(recBookingDetail.getField(BookingDetail.COST_MESSAGE_TRANSPORT_ID), strManualTransportID, null, true);
        recBookingDetail.getField(BookingDetail.COST_MESSAGE_TRANSPORT_ID).addListener(new RemoveConverterOnFreeHandler(convCheckMark));
        recBookingDetail.getField(BookingDetail.COST_MESSAGE_TRANSPORT_ID).addListener(new CopyStringHandler(recBookingDetail.getField(BookingDetail.COST_STATUS_ID), Integer.toString(BaseStatus.VALID), convCheckMark));
        convCheckMark = new CheckConverter(recBookingDetail.getField(BookingDetail.PRODUCT_MESSAGE_TRANSPORT_ID), strManualTransportID, null, true);
        recBookingDetail.getField(BookingDetail.PRODUCT_MESSAGE_TRANSPORT_ID).addListener(new RemoveConverterOnFreeHandler(convCheckMark));
        recBookingDetail.getField(BookingDetail.PRODUCT_MESSAGE_TRANSPORT_ID).addListener(new CopyStringHandler(recBookingDetail.getField(BookingDetail.PRODUCT_STATUS_ID), Integer.toString(BaseStatus.NO_STATUS), convCheckMark));
    }
    /**
     * Set up all the screen fields.
     */
    public void setupSFields()
    {
        // This is usually overidden
        this.getMainRecord().getField(BookingDetail.DETAIL_DATE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getMainRecord().getField(BookingDetail.PRODUCT_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getMainRecord().getField(BookingDetail.PRODUCT_STATUS_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
    }
    /**
     * AddStandardScreenFields Method.
     */
    public void addStandardScreenFields(boolean bTopNext)
    {
        this.getMainRecord().getField(BookingDetail.STATUS_SUMMARY).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getMainRecord().getField(BookingDetail.INFO_STATUS_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getMainRecord().getField(BookingDetail.INFO_MESSAGE_TRANSPORT_ID).setupDefaultView(this.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), this, ScreenConstants.DONT_DISPLAY_FIELD_DESC);
        this.getMainRecord().getField(BookingDetail.INVENTORY_STATUS_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getMainRecord().getField(BookingDetail.INVENTORY_MESSAGE_TRANSPORT_ID).setupDefaultView(this.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), this, ScreenConstants.DONT_DISPLAY_FIELD_DESC);
        this.getMainRecord().getField(BookingDetail.COST_STATUS_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getMainRecord().getField(BookingDetail.COST_MESSAGE_TRANSPORT_ID).setupDefaultView(this.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), this, ScreenConstants.DONT_DISPLAY_FIELD_DESC);
        this.getMainRecord().getField(BookingDetail.PRODUCT_STATUS_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getMainRecord().getField(BookingDetail.PRODUCT_MESSAGE_TRANSPORT_ID).setupDefaultView(this.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), this, ScreenConstants.DONT_DISPLAY_FIELD_DESC);
        this.getMainRecord().getField(BookingDetail.ACK_DAYS).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        FieldConverter converter = new FieldLengthConverter(this.getMainRecord().getField(BookingDetail.REMOTE_BOOKING_NO), 30);
        converter.setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(BookingDetail.BOOKING_DETAIL_FILE).getField(BookingDetail.TOTAL_PRICE_LOCAL).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
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
        Record recProduct = ((ReferenceField)this.getMainRecord().getField(BookingDetail.PRODUCT_ID)).getReferenceRecord();
        Map<String,Object> map = new Hashtable<String,Object>();
        String startDate = ((DateTimeField)this.getMainRecord().getField(BookingDetail.DETAIL_DATE)).toString();
        if (startDate == null)
            startDate = ((DateTimeField)this.getRecord(Tour.TOUR_FILE).getField(Tour.DEPARTURE_DATE)).toString();
        if (startDate != null)
            map.put("StartDate", startDate);
        map.put("ReadOnly", Boolean.TRUE.toString());
        if (strCommand.equalsIgnoreCase(Product.PRICING_DETAIL))
            return (this.onForm(recProduct, Product.PRICING_GRID_SCREEN, true, ScreenConstants.USE_NEW_WINDOW | ScreenConstants.DONT_PUSH_TO_BROWSER, map) != null);
        else if (strCommand.equalsIgnoreCase(Product.INVENTORY_DETAIL))
            return (this.onForm(recProduct, Product.INVENTORY_GRID_SCREEN, true, ScreenConstants.USE_NEW_WINDOW | ScreenConstants.DONT_PUSH_TO_BROWSER, map) != null);
        else if (strCommand.equalsIgnoreCase(MessageLog.MESSAGE_LOG_FILE))
        {
            Record recMessageLog = new MessageLog(this);
            this.removeRecord(recMessageLog); // This will be owned by the new screen
            Map<String,Object> properties = new Hashtable<String,Object>();
            String strReferenceFieldName = recMessageLog.getField(MessageLog.REFERENCE_ID).getFieldName();
            String strReferenceID = this.getMainRecord().getCounterField().toString();
            String strReferenceTypeFieldName = recMessageLog.getField(MessageLog.REFERENCE_TYPE).getFieldName();
            String strReferenceType = this.getMainRecord().getTableNames(false);
            if (strReferenceID != null)
            {
                properties.put(strReferenceFieldName, strReferenceID);
                properties.put(strReferenceTypeFieldName, strReferenceType);
            }
            iCommandOptions = ScreenConstants.USE_NEW_WINDOW | ScreenConstants.DONT_PUSH_TO_BROWSER;
            this.onForm(recMessageLog, ScreenConstants.DISPLAY_MODE, false, iCommandOptions, false, properties);
            return true;
        }
        return super.doCommand(strCommand, sourceSField, iCommandOptions);
    }

}

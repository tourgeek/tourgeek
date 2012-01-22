/**
 * @(#)BookingDetailSubScreen.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.booking.entry.detail.base;

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
import org.jbundle.main.properties.db.*;
import com.tourapp.tour.booking.entry.base.*;
import com.tourapp.tour.booking.detail.db.*;
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.product.base.db.*;
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
        Booking recBooking = (Booking)this.getRecord(Booking.kBookingFile);
        Tour recTour = (Tour)this.getRecord(Tour.kTourFile);
        recBookingDetail.addDetailBehaviors(recBooking, recTour);
        // Make sure you have a valid handle before performing any price etc lookups.
        recBookingDetail.setOpenMode(recBookingDetail.getOpenMode() | DBConstants.OPEN_REFRESH_AND_LOCK_ON_CHANGE_STRATEGY);
        
        this.setEnabled(false);
        recBookingDetail.getField(BookingDetail.kProductID).setEnabled(true);
        recBookingDetail.getField(BookingDetail.kDetailDate).setEnabled(true);
        recBookingDetail.getField(BookingDetail.kRateID).setEnabled(true);
        recBookingDetail.getField(BookingDetail.kClassID).setEnabled(true);
        recBookingDetail.getField(BookingDetail.kInfoMessageTransportID).setEnabled(true);
        recBookingDetail.getField(BookingDetail.kCostMessageTransportID).setEnabled(true);
        recBookingDetail.getField(BookingDetail.kInventoryMessageTransportID).setEnabled(true);
        recBookingDetail.getField(BookingDetail.kProductMessageTransportID).setEnabled(true);
        recBookingDetail.getField(BookingDetail.kAckDays).setEnabled(true);
        recBookingDetail.getField(BookingDetail.kDescription).setEnabled(true);
        
        Record recProduct =((ReferenceField)recBookingDetail.getField(BookingDetail.kProductID)).getReferenceRecord();
        for (int i = 0; i < this.getSFieldCount(); i++)
        {
            ScreenField sField = this.getSField(i);
            if (sField instanceof SCannedBox)
            {
                String strCommand = ((SCannedBox)sField).getButtonCommand();
                if ((Product.INVENTORY_DETAIL.equals(strCommand))
                    || (Product.PRICING_DETAIL.equals(strCommand)))
                        recProduct.addListener(new EnableOnValidHandler(sField, true, false));
                if (MessageLog.kMessageLogFile.equals(strCommand))
                    recProduct.addListener(new EnableOnValidHandler(sField, true, false));
            }
        }
        
        ((BaseStatusField)recBookingDetail.getField(BookingDetail.kInfoStatusID)).getIconField(null).addListener(new BookingDetailStatusUpdateHandler(null));
        ((BaseStatusField)recBookingDetail.getField(BookingDetail.kInventoryStatusID)).getIconField(null).addListener(new BookingDetailStatusUpdateHandler(null));
        ((BaseStatusField)recBookingDetail.getField(BookingDetail.kCostStatusID)).getIconField(null).addListener(new BookingDetailStatusUpdateHandler(null));
        ((BaseStatusField)recBookingDetail.getField(BookingDetail.kProductStatusID)).getIconField(null).addListener(new BookingDetailStatusUpdateHandler(null));
        
        recBookingDetail.getField(BookingDetail.kCostStatusID).addListener(new DisableOnFieldHandler(recBookingDetail.getField(BookingDetail.kMarkupFromLast), Integer.toString(BaseStatus.NOT_VALID), false)
        {
            public boolean compareFieldToString()
            {
                boolean bMatch = super.compareFieldToString();
                if (!bMatch)
                    if (!this.getOwner().getRecord().getField(BookingDetail.kMarkupFromLast).isNull())
                        bMatch = true;
                return bMatch;
            }
        });
        
        String strManualTransportID = Integer.toString(((ReferenceField)recBookingDetail.getField(BookingDetail.kCostMessageTransportID)).getIDFromCode(MessageTransport.MANUAL));
        
        CheckConverter convCheckMark = new CheckConverter(recBookingDetail.getField(BookingDetail.kInfoMessageTransportID), strManualTransportID, null, true);
        recBookingDetail.getField(BookingDetail.kInfoMessageTransportID).addListener(new RemoveConverterOnFreeHandler(convCheckMark));
        recBookingDetail.getField(BookingDetail.kInfoMessageTransportID).addListener(new CopyStringHandler(recBookingDetail.getField(BookingDetail.kInfoStatusID), Integer.toString(BaseStatus.VALID), convCheckMark));
        
        recBookingDetail.getField(BookingDetail.kInfoMessageTransportID).addListener(new CopyStringHandler(recBookingDetail.getField(BookingDetail.kInventoryMessageTransportID), strManualTransportID, convCheckMark));
        recBookingDetail.getField(BookingDetail.kInfoMessageTransportID).addListener(new CopyStringHandler(recBookingDetail.getField(BookingDetail.kCostMessageTransportID), strManualTransportID, convCheckMark));
        recBookingDetail.getField(BookingDetail.kInfoMessageTransportID).addListener(new CopyStringHandler(recBookingDetail.getField(BookingDetail.kProductMessageTransportID), strManualTransportID, convCheckMark));
        
        convCheckMark = new CheckConverter(recBookingDetail.getField(BookingDetail.kInventoryMessageTransportID), strManualTransportID, null, true);
        recBookingDetail.getField(BookingDetail.kInventoryMessageTransportID).addListener(new RemoveConverterOnFreeHandler(convCheckMark));
        recBookingDetail.getField(BookingDetail.kInventoryMessageTransportID).addListener(new CopyStringHandler(recBookingDetail.getField(BookingDetail.kInventoryStatusID), Integer.toString(BaseStatus.VALID), convCheckMark));
        convCheckMark = new CheckConverter(recBookingDetail.getField(BookingDetail.kCostMessageTransportID), strManualTransportID, null, true);
        recBookingDetail.getField(BookingDetail.kCostMessageTransportID).addListener(new RemoveConverterOnFreeHandler(convCheckMark));
        recBookingDetail.getField(BookingDetail.kCostMessageTransportID).addListener(new CopyStringHandler(recBookingDetail.getField(BookingDetail.kCostStatusID), Integer.toString(BaseStatus.VALID), convCheckMark));
        convCheckMark = new CheckConverter(recBookingDetail.getField(BookingDetail.kProductMessageTransportID), strManualTransportID, null, true);
        recBookingDetail.getField(BookingDetail.kProductMessageTransportID).addListener(new RemoveConverterOnFreeHandler(convCheckMark));
        recBookingDetail.getField(BookingDetail.kProductMessageTransportID).addListener(new CopyStringHandler(recBookingDetail.getField(BookingDetail.kProductStatusID), Integer.toString(BaseStatus.NO_STATUS), convCheckMark));
    }
    /**
     * Set up all the screen fields.
     */
    public void setupSFields()
    {
        // This is usually overidden
        this.getMainRecord().getField(BookingDetail.kDetailDate).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getMainRecord().getField(BookingDetail.kProductID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getMainRecord().getField(BookingDetail.kProductStatusID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
    }
    /**
     * AddStandardScreenFields Method.
     */
    public void addStandardScreenFields(boolean bTopNext)
    {
        this.getMainRecord().getField(BookingDetail.kStatusSummary).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getMainRecord().getField(BookingDetail.kInfoStatusID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getMainRecord().getField(BookingDetail.kInfoMessageTransportID).setupDefaultView(this.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), this, ScreenConstants.DONT_DISPLAY_FIELD_DESC);
        this.getMainRecord().getField(BookingDetail.kInventoryStatusID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getMainRecord().getField(BookingDetail.kInventoryMessageTransportID).setupDefaultView(this.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), this, ScreenConstants.DONT_DISPLAY_FIELD_DESC);
        this.getMainRecord().getField(BookingDetail.kCostStatusID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getMainRecord().getField(BookingDetail.kCostMessageTransportID).setupDefaultView(this.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), this, ScreenConstants.DONT_DISPLAY_FIELD_DESC);
        this.getMainRecord().getField(BookingDetail.kProductStatusID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getMainRecord().getField(BookingDetail.kProductMessageTransportID).setupDefaultView(this.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), this, ScreenConstants.DONT_DISPLAY_FIELD_DESC);
        this.getMainRecord().getField(BookingDetail.kAckDays).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        FieldConverter converter = new FieldLengthConverter(this.getMainRecord().getField(BookingDetail.kRemoteBookingNo), 30);
        converter.setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(BookingDetail.kBookingDetailFile).getField(BookingDetail.kTotalPriceLocal).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
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
        Record recProduct = ((ReferenceField)this.getMainRecord().getField(BookingDetail.kProductID)).getReferenceRecord();
        Map<String,Object> map = new Hashtable<String,Object>();
        String startDate = ((DateTimeField)this.getMainRecord().getField(BookingDetail.kDetailDate)).toString();
        if (startDate == null)
            startDate = ((DateTimeField)this.getRecord(Tour.kTourFile).getField(Tour.kDepartureDate)).toString();
        if (startDate != null)
            map.put("StartDate", startDate);
        map.put("ReadOnly", Boolean.TRUE.toString());
        if (strCommand.equalsIgnoreCase(Product.PRICING_DETAIL))
            return (this.onForm(recProduct, Product.PRICING_GRID_SCREEN, true, ScreenConstants.USE_NEW_WINDOW | ScreenConstants.DONT_PUSH_TO_BROSWER, map) != null);
        else if (strCommand.equalsIgnoreCase(Product.INVENTORY_DETAIL))
            return (this.onForm(recProduct, Product.INVENTORY_GRID_SCREEN, true, ScreenConstants.USE_NEW_WINDOW | ScreenConstants.DONT_PUSH_TO_BROSWER, map) != null);
        else if (strCommand.equalsIgnoreCase(MessageLog.kMessageLogFile))
        {
            Record recMessageLog = new MessageLog(this);
            this.removeRecord(recMessageLog); // This will be owned by the new screen
            Map<String,Object> properties = new Hashtable<String,Object>();
            String strReferenceFieldName = recMessageLog.getField(MessageLog.kReferenceID).getFieldName();
            String strReferenceID = this.getMainRecord().getCounterField().toString();
            String strReferenceTypeFieldName = recMessageLog.getField(MessageLog.kReferenceType).getFieldName();
            String strReferenceType = this.getMainRecord().getTableNames(false);
            if (strReferenceID != null)
            {
                properties.put(strReferenceFieldName, strReferenceID);
                properties.put(strReferenceTypeFieldName, strReferenceType);
            }
            iCommandOptions = ScreenConstants.USE_NEW_WINDOW | ScreenConstants.DONT_PUSH_TO_BROSWER;
            this.onForm(recMessageLog, ScreenConstants.DISPLAY_MODE, false, iCommandOptions, false, properties);
            return true;
        }
        return super.doCommand(strCommand, sourceSField, iCommandOptions);
    }

}

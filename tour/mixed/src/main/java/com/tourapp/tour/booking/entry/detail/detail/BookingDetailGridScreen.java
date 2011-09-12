/**
 * @(#)BookingDetailGridScreen.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.booking.entry.detail.detail;

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
import com.tourapp.tour.booking.entry.base.*;
import com.tourapp.tour.booking.detail.db.*;
import com.tourapp.tour.product.hotel.db.*;
import com.tourapp.tour.product.land.db.*;
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.booking.entry.detail.base.*;

/**
 *  BookingDetailGridScreen - Product detail display.
 */
public class BookingDetailGridScreen extends BookingSubGridScreen
{
    /**
     * Default constructor.
     */
    public BookingDetailGridScreen()
    {
        super();
    }
    /**
     * Constructor.
     * @param record The main record for this screen.
     * @param itsLocation The location of this component within the parent.
     * @param parentScreen The parent screen.
     * @param fieldConverter The field this screen field is linked to.
     * @param iDisplayFieldDesc Do I display the field desc?.
     */
    public BookingDetailGridScreen(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
    {
        this();
        this.init(record, itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, properties);
    }
    /**
     * Constructor.
     * @param record The main record for this screen.
     * @param itsLocation The location of this component within the parent.
     * @param parentScreen The parent screen.
     * @param fieldConverter The field this screen field is linked to.
     * @param iDisplayFieldDesc Do I display the field desc?.
     */
    public void init(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
    {
        parentScreen.setProperty(BookingScreenHandler.SUB_SCREEN_PARAM, Integer.toString(BookingScreenHandler.FIT_SCREEN));
        super.init(record, itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, properties);
    }
    /**
     * Get the screen display title.
     */
    public String getTitle()
    {
        return "Product detail display";
    }
    /**
     * Override this to open the main file.
     * <p />You should pass this record owner to the new main file (ie., new MyNewTable(thisRecordOwner)).
     * @return The new record.
     */
    public Record openMainRecord()
    {
        return new BookingDetail(this);
    }
    /**
     * Add the screen fields.
     * Override this to create (and return) the screen record for this recordowner.
     * @return The screen record.
     */
    public Record addScreenRecord()
    {
        return new BookingDetailScreenRecord(this);
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        Booking recBooking = (Booking)this.getRecord(Booking.kBookingFile);
        Tour recTour = (Tour)this.getRecord(Tour.kTourFile);
        
        BookingDetail record = (BookingDetail)this.getMainRecord();
        record.setKeyArea(BookingDetail.kBookingIDKey);
        record.addDetailBehaviors(recBooking, recTour);
        
        this.setAppending(false);
    }
    /**
     * SetupSFields Method.
     */
    public void setupSFields()
    {
        Record mergeTable = this.getMainRecord();
        this.addColumn(new MultipleTableFieldConverter(mergeTable, BookingDetail.kStatusSummary));
        
        this.addColumn(new MultipleTableFieldConverter(mergeTable, BookingDetail.kDetailDate));
        this.addColumn(new ProductDescConverter(mergeTable));
        
        this.addColumn(new MultipleTableFieldConverter(mergeTable, BookingDetail.kTotalCostLocal));
    }
    /**
     * Make a sub-screen.
     * @return the new sub-screen.
     */
    public BasePanel makeSubScreen()
    {
        return null; //+return new BookingCostingScreen(null, null, null, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC);
    }

}

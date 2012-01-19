/**
 * @(#)BookingTourDetailGridScreen.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.booking.entry.detail.tour;

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
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import com.tourapp.tour.booking.entry.detail.base.*;
import com.tourapp.tour.booking.detail.db.*;
import com.tourapp.tour.product.base.db.*;
import com.tourapp.tour.base.db.*;
import com.tourapp.tour.product.tour.db.*;
import com.tourapp.tour.acctpay.db.*;
import com.tourapp.thin.app.booking.entry.*;
import com.tourapp.tour.booking.entry.base.*;
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.booking.entry.tour.*;
import com.tourapp.tour.product.tour.screen.*;

/**
 *  BookingTourDetailGridScreen - Booking tour header detail.
 */
public class BookingTourDetailGridScreen extends BookingDetailSubGridScreen
{
    /**
     * Default constructor.
     */
    public BookingTourDetailGridScreen()
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
    public BookingTourDetailGridScreen(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
        parentScreen.setProperty(BookingScreenHandler.SUB_SCREEN_PARAM, Integer.toString(BookingScreenHandler.TOUR_DETAIL_SCREEN));
        super.init(record, itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, properties);
    }
    /**
     * Get the screen display title.
     */
    public String getTitle()
    {
        return "Booking tour header detail";
    }
    /**
     * Override this to open the main file.
     * <p />You should pass this record owner to the new main file (ie., new MyNewTable(thisRecordOwner)).
     * @return The new record.
     */
    public Record openMainRecord()
    {
        return new BookingTour(this);
    }
    /**
     * Override this to open the other files in the query.
     */
    public void openOtherRecords()
    {
        if (this.getRecord(BookingControl.kBookingControlFile) == null)
            new BookingControl(this);
        super.openOtherRecords();
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
    }
    /**
     * SetupSFields Method.
     */
    public void setupSFields()
    {
        this.getRecord(BookingTour.kBookingTourFile).getField(BookingTour.kDetailDate).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(BookingTour.kBookingTourFile).getField(BookingTour.kProductID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(BookingTour.kBookingTourFile).getField(BookingTour.kStatusSummary).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        for (int i = 0; i < this.getSFieldCount(); i++)
        {
            if (this.getSField(i) instanceof SSelectBox)
            {   // Make sure the tour header display displays only the modules
                String strCommand = ((SSelectBox)this.getSField(i)).getButtonCommand();
                strCommand = Utility.addURLParam(null, DBParams.COMMAND, strCommand);
                Record recBookingControl = this.getRecord(BookingControl.kBookingControlFile);
                strCommand = Utility.addURLParam(strCommand, TourHeaderScreenRecord.TOUR_TYPE, recBookingControl.getField(BookingControl.kModuleTourType).toString());
                ((SSelectBox)this.getSField(i)).setButtonCommand(strCommand);
            }
        }
    }

}

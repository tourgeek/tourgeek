/**
 * @(#)TourField.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.booking.db;

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
import com.tourapp.tour.product.tour.db.*;
import java.util.*;
import com.tourapp.tour.acctpay.db.*;
import com.tourapp.tour.booking.detail.db.*;
import com.tourapp.tour.product.hotel.db.*;
import com.tourapp.tour.product.land.db.*;
import com.tourapp.tour.booking.lookup.*;
import com.tourapp.tour.booking.detail.event.*;
import com.tourapp.tour.product.tour.other.screen.*;
import com.tourapp.tour.product.tour.schedule.db.*;
import com.tourapp.tour.booking.db.event.*;
import com.tourapp.tour.acctpay.screen.findepest.*;
import org.jbundle.main.db.base.*;

/**
 *  TourField - .
 */
public class TourField extends ReferenceField
{
    /**
     * Default constructor.
     */
    public TourField()
    {
        super();
    }
    /**
     * Constructor.
     * @param record The parent record.
     * @param strName The field name.
     * @param iDataLength The maximum string length (pass -1 for default).
     * @param strDesc The string description (usually pass null, to use the resource file desc).
     * @param strDefault The default value (if object, this value is the default value, if string, the string is the default).
     */
    public TourField(Record record, String strName, int iDataLength, String strDesc, Object strDefault)
    {
        this();
        this.init(record, strName, iDataLength, strDesc, strDefault);
    }
    /**
     * Initialize class fields.
     */
    public void init(Record record, String strName, int iDataLength, String strDesc, Object strDefault)
    {
        super.init(record, strName, iDataLength, strDesc, strDefault);
    }
    /**
     * Get (or make) the current record for this reference.
     */
    public Record makeReferenceRecord(RecordOwner recordOwner)
    {
        Tour recTour = new Tour(recordOwner);
        if (Booking.BOOKING_FILE.equals(this.getRecord().getTableNames(false)))
        {    // Make sure that these are moved back to the booking if the user changes them
            recTour.getField(Tour.DESCRIPTION).addListener(new SyncTourFieldHandler((Booking)this.getRecord(), Booking.DESCRIPTION));
            recTour.getField(Tour.CODE).addListener(new SyncTourFieldHandler((Booking)this.getRecord(), Booking.CODE));
        }
        return recTour;
    }
    /**
     * Set up the default screen control for this field.
     * @param itsLocation Location of this component on screen (ie., GridBagConstraint).
     * @param targetScreen Where to place this component (ie., Parent screen or GridBagLayout).
     * @param converter The converter to set the screenfield to.
     * @param iDisplayFieldDesc Display the label? (optional).
     * @param properties Extra properties
     * @return Return the component or ScreenField that is created for this field.
     */
    public ScreenComponent setupDefaultView(ScreenLoc itsLocation, ComponentParent targetScreen, Convert converter, int iDisplayFieldDesc, Map<String, Object> properties)
    {
        Record recTour = this.getReferenceRecord(null, false);
        boolean bUpdateRecord = false;
        if (recTour != null)
            bUpdateRecord = true;
        else
            recTour = this.makeReferenceRecord();   // Only set read-only if this is created by me.
        BaseField fldDepartureDate = recTour.getField(Tour.DEPARTURE_DATE);
        fldDepartureDate.removeListener(fldDepartureDate.getListener(InitOnceFieldHandler.class.getName()), true);
        BaseField fldTourDesc = recTour.getField(Tour.DESCRIPTION);
        BaseField fldCode = recTour.getField(Tour.CODE);
        
        recTour.setKeyArea(Tour.CODE_KEY);
        fldCode.addListener(new MainReadOnlyHandler(Tour.CODE_KEY));
        Converter conv = new FieldDescConverter((Converter)fldCode, (Converter)converter); // Use the description for this field
        conv = new FieldLengthConverter(conv, 10);
        ScreenComponent sfDesc = createScreenComponent(ScreenModel.EDIT_TEXT, itsLocation, targetScreen, conv, iDisplayFieldDesc, properties);
        
        boolean bIncludeBlankOption = true;
        ReadSecondaryHandler pBehavior2 = new ReadSecondaryHandler(recTour, DBConstants.MAIN_FIELD, DBConstants.CLOSE_ON_FREE, bUpdateRecord, bIncludeBlankOption);
        this.addListener(pBehavior2);
        
        ScreenField screenField = (ScreenField)fldDepartureDate.setupDefaultView(targetScreen.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), targetScreen, fldDepartureDate, ScreenConstants.DONT_DISPLAY_FIELD_DESC, null);
        fldDepartureDate.setEnabled(false);
        
        conv = new FieldLengthConverter(fldTourDesc, 30);
        this.setupTableLookup(targetScreen.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), targetScreen, converter, ScreenConstants.DONT_DISPLAY_FIELD_DESC, recTour, -1, conv, false, true);
        
        return sfDesc;
    }

}

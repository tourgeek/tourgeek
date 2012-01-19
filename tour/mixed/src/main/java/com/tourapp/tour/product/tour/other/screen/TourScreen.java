/**
 * @(#)TourScreen.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.product.tour.other.screen;

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
import com.tourapp.tour.product.base.screen.*;
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.product.tour.db.*;
import com.tourapp.tour.booking.entry.tour.*;
import com.tourapp.tour.product.tour.screen.*;

/**
 *  TourScreen - Tour Maint.
 */
public class TourScreen extends ProductDetailScreen
{
    /**
     * Default constructor.
     */
    public TourScreen()
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
    public TourScreen(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
        return "Tour Maint";
    }
    /**
     * TourScreen Method.
     */
    public TourScreen(Record recHeader, Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object>
     properties)
    {
        this();
        this.init(recHeader, record, itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, properties);
    }
    /**
     * Initialize class fields.
     */
    public void init(Record recHeader, Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object>
     properties)
    {
        super.init(recHeader, record, itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, properties);
    }
    /**
     * Override this to open the main file.
     * <p />You should pass this record owner to the new main file (ie., new MyNewTable(thisRecordOwner)).
     * @return The new record.
     */
    public Record openMainRecord()
    {
        return new Tour(this);
    }
    /**
     * Open the header record.
     * @return The new header record.
     */
    public Record openHeaderRecord()
    {
        return new TourHeader(this);
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        Tour recTour = (Tour)this.getMainRecord();
        BaseField fldDepartureDate = recTour.getField(Tour.kDepartureDate);
        TourHeader recTourHeader = (TourHeader)this.getHeaderRecord();
        TourClass recTourClass= (TourClass)((ReferenceField)recTourHeader.getField(TourHeader.kTourClassID)).getReferenceRecord();
        Booking recBooking = null;
        BaseField fldTourDesc = recTour.getField(Tour.kDescription);
        fldDepartureDate.addListener(new ChangeTourHeaderHandler(recTourHeader, recTourClass, recTour, recBooking, null, fldDepartureDate, fldTourDesc));
        
        this.getHeaderRecord().addListener(new FileListener(null)
        {
            public void doValidRecord(boolean bDisplayOption)
            {
                super.doValidRecord(bDisplayOption);
                boolean bEnabledState = false;
                if (this.getOwner().getField(TourHeader.kTourSeries).getState() == true)
                    bEnabledState = true;
                if (this.getOwner().getField(TourHeader.kTourSeries).isNull())  // Unknown type
                    bEnabledState = true;
                setAppending(bEnabledState);
            }
        });
        
        this.getMainRecord().addListener(new FileListener(null)
        {
            public int doRecordChange(FieldInfo field, int iChangeType, boolean bDisplayOption)
            {
                int iErrorCode = super.doRecordChange(field, iChangeType, bDisplayOption);
                if (iErrorCode != DBConstants.NORMAL_RETURN)
                    return iErrorCode;
                if (iChangeType == DBConstants.FIELD_CHANGED_TYPE)
                {
                    Record recTourHeader = getHeaderRecord();
                    if (recTourHeader.getField(TourHeader.kTourSeries).getState() != true)
                    {
                        try {
                            recTourHeader.edit();
                            recTourHeader.getField(TourHeader.kTourSeries).setState(true);
                            int iSeriesCode = ((int)recTourHeader.getField(TourHeader.kTourType).getValue() | TourType.SERIES);
                            recTourHeader.getField(TourHeader.kTourType).setValue(iSeriesCode);
                            recTourHeader.writeAndRefresh();
                        } catch (DBException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
                return iErrorCode;
            }
        });
    }
    /**
     * Add the screen fields.
     * Override this to create (and return) the screen record for this recordowner.
     * @return The screen record.
     */
    public Record addScreenRecord()
    {
        return new TourHeaderScreenRecord(this);
    }
    /**
     * Set up all the screen fields.
     */
    public void setupSFields()
    {
        this.getRecord(Tour.kTourFile).getField(Tour.kCode).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Tour.kTourFile).getField(Tour.kDepartureDate).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Tour.kTourFile).getField(Tour.kDescription).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Tour.kTourFile).getField(Tour.kTourStatusID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Tour.kTourFile).getField(Tour.kManualTourStatus).setupDefaultView(this.getNextLocation(ScreenConstants.RIGHT_WITH_DESC, ScreenConstants.DONT_SET_ANCHOR), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Tour.kTourFile).getField(Tour.kMinToOp).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Tour.kTourFile).getField(Tour.kFinalizeDate).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Tour.kTourFile).getField(Tour.kFinalized).setupDefaultView(this.getNextLocation(ScreenConstants.RIGHT_WITH_DESC, ScreenConstants.DONT_SET_ANCHOR), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Tour.kTourFile).getField(Tour.kOrderCompDate).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Tour.kTourFile).getField(Tour.kOrderComponents).setupDefaultView(this.getNextLocation(ScreenConstants.RIGHT_WITH_DESC, ScreenConstants.DONT_SET_ANCHOR), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Tour.kTourFile).getField(Tour.kClosedDate).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Tour.kTourFile).getField(Tour.kClosed).setupDefaultView(this.getNextLocation(ScreenConstants.RIGHT_WITH_DESC, ScreenConstants.DONT_SET_ANCHOR), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Tour.kTourFile).getField(Tour.kFinalDocDate).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Tour.kTourFile).getField(Tour.kFinalDocs).setupDefaultView(this.getNextLocation(ScreenConstants.RIGHT_WITH_DESC, ScreenConstants.DONT_SET_ANCHOR), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Tour.kTourFile).getField(Tour.kTicketDate).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Tour.kTourFile).getField(Tour.kTickets).setupDefaultView(this.getNextLocation(ScreenConstants.RIGHT_WITH_DESC, ScreenConstants.DONT_SET_ANCHOR), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Tour.kTourFile).getField(Tour.kSp1Date).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Tour.kTourFile).getField(Tour.kSp1).setupDefaultView(this.getNextLocation(ScreenConstants.RIGHT_WITH_DESC, ScreenConstants.DONT_SET_ANCHOR), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Tour.kTourFile).getField(Tour.kSp2Date).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Tour.kTourFile).getField(Tour.kSp2).setupDefaultView(this.getNextLocation(ScreenConstants.RIGHT_WITH_DESC, ScreenConstants.DONT_SET_ANCHOR), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Tour.kTourFile).getField(Tour.kServConf).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Tour.kTourFile).getField(Tour.kDeparted).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Tour.kTourFile).getField(Tour.kCancelled).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Tour.kTourFile).getField(Tour.kFileNo).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Tour.kTourFile).getField(Tour.kAirRateID).setupDefaultView(this.getNextLocation(ScreenConstants.TOP_NEXT, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Tour.kTourFile).getField(Tour.kAirClassID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Tour.kTourFile).getField(Tour.kHotelRateID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Tour.kTourFile).getField(Tour.kHotelClassID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Tour.kTourFile).getField(Tour.kLandRateID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Tour.kTourFile).getField(Tour.kLandClassID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Tour.kTourFile).getField(Tour.kPMCCutoff).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Tour.kTourFile).getField(Tour.kCarRateID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Tour.kTourFile).getField(Tour.kCarClassID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Tour.kTourFile).getField(Tour.kTransportationRateID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Tour.kTourFile).getField(Tour.kTransportationClassID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Tour.kTourFile).getField(Tour.kCruiseRateID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Tour.kTourFile).getField(Tour.kCruiseClassID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Tour.kTourFile).getField(Tour.kItemRateID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Tour.kTourFile).getField(Tour.kItemClassID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
    }
    /**
     * Make a sub-screen.
     * @return the new sub-screen.
     */
    public BasePanel makeSubScreen()
    {
        return new ProductHeaderScreen(null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC);
    }

}

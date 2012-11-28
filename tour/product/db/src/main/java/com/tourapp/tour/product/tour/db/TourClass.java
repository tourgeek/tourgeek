/**
  * @(#)TourClass.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.tour.product.tour.db;

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
import com.tourapp.tour.product.tour.schedule.db.*;
import com.tourapp.tour.profile.db.*;
import com.tourapp.tour.product.air.db.*;
import com.tourapp.tour.product.hotel.db.*;
import com.tourapp.tour.product.land.db.*;
import com.tourapp.tour.product.car.db.*;
import com.tourapp.tour.product.trans.db.*;
import com.tourapp.tour.product.cruise.db.*;
import com.tourapp.tour.product.item.db.*;
import com.tourapp.tour.product.base.db.*;
import com.tourapp.model.tour.product.tour.db.*;

/**
 *  TourClass - Tour class.
 */
public class TourClass extends VirtualRecord
     implements TourClassModel
{
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor.
     */
    public TourClass()
    {
        super();
    }
    /**
     * Constructor.
     */
    public TourClass(RecordOwner screen)
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
        return (m_tableName == null) ? Record.formatTableNames(TOUR_CLASS_FILE, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "Tour class";
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
        return DBConstants.LOCAL;
    }
    /**
     * MakeScreen Method.
     */
    public ScreenParent makeScreen(ScreenLoc itsLocation, ComponentParent parentScreen, int iDocMode, Map<String,Object> properties)
    {
        ScreenParent screen = null;
        if ((iDocMode & ScreenConstants.DETAIL_MODE) == ScreenConstants.DETAIL_MODE)
            screen = Record.makeNewScreen(TourEventSchedule.TOUR_EVENT_SCHEDULE_GRID_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        else if ((iDocMode & ScreenConstants.MAINT_MODE) == ScreenConstants.MAINT_MODE)
            screen = Record.makeNewScreen(TOUR_CLASS_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        else if ((iDocMode & ScreenConstants.DISPLAY_MODE) != 0)
            screen = Record.makeNewScreen(TOUR_CLASS_GRID_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
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
        if (iFieldSeq == 0)
        {
            field = new CounterField(this, ID, 8, null, null);
            field.setHidden(true);
        }
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
            field = new StringField(this, CLASS_NAME, 15, null, null);
        if (iFieldSeq == 4)
        {
            field = new TourClassSelect(this, BASED_CLASS_ID, 8, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 5)
        {
            field = new ShortField(this, DEPOSIT_DUE_DAYS, 3, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 6)
        {
            field = new ShortField(this, FINAL_PAY_DAYS, 3, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 7)
        {
            field = new ShortField(this, FINALIZE_DAYS, 3, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 8)
        {
            field = new ShortField(this, ORDER_COMP_DAYS, 3, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 9)
        {
            field = new ShortField(this, CLOSED_DAYS, 3, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 10)
        {
            field = new ShortField(this, FINAL_DOC_DAYS, 3, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 11)
        {
            field = new ShortField(this, TICKET_DAYS, 3, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 12)
        {
            field = new ShortField(this, SP_1_DAYS, 3, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 13)
        {
            field = new StringField(this, SP_1_DESC, 15, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 14)
        {
            field = new ShortField(this, SP_2_DAYS, 3, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 15)
        {
            field = new StringField(this, SP_2_DESC, 15, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 16)
        {
            field = new BooleanField(this, ADD_DEPOSIT, 1, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 17)
        {
            field = new CurrencyField(this, DEPOSIT_AMOUNT, 10, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 18)
        {
            field = new CurrencyField(this, CANCEL_CHARGE, 10, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 19)
        {
            field = new CurrencyField(this, MOD_BEFORE_CHARGE, 10, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 20)
        {
            field = new CurrencyField(this, MOD_AFTER_CHARGE, 10, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 21)
            field = new ProfileStatusField(this, UPGRADE_PROFILE_STATUS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 22)
        {
            field = new AirRateSelect(this, AIR_RATE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 23)
            field = new AirClassSelect(this, AIR_CLASS_ID, 4, null, null);
        if (iFieldSeq == 24)
            field = new HotelRateSelect(this, HOTEL_RATE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 25)
            field = new HotelClassSelect(this, HOTEL_CLASS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 26)
        {
            field = new LandRateSelect(this, LAND_RATE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 27)
            field = new LandClassSelect(this, LAND_CLASS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 28)
        {
            field = new ShortField(this, PMC_CUTOFF, 3, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 29)
            field = new CarClassSelect(this, CAR_CLASS_ID, 4, null, null);
        if (iFieldSeq == 30)
            field = new CarRateSelect(this, CAR_RATE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 31)
        {
            field = new TransportationRateSelect(this, TRANSPORTATION_RATE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 32)
        {
            field = new TransportationClassSelect(this, TRANSPORTATION_CLASS_ID, 4, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 33)
        {
            field = new CruiseRateSelect(this, CRUISE_RATE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 34)
            field = new CruiseClassSelect(this, CRUISE_CLASS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 35)
            field = new ItemRateSelect(this, ITEM_RATE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 36)
            field = new ItemClassSelect(this, ITEM_CLASS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 37)
            field = new BookingStatusSelect(this, INITIAL_BOOKING_STATUS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 38)
            field = new TourStatusSelect(this, INITIAL_TOUR_STATUS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 39)
            field = new PercentField(this, MARKUP, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 40)
            field = new PricingTypeSelect(this, TOUR_PRICING_TYPE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 41)
            field = new PricingTypeSelect(this, NON_TOUR_PRICING_TYPE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
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
            keyArea = this.makeIndex(DBConstants.UNIQUE, ID_KEY);
            keyArea.addKeyField(ID, DBConstants.ASCENDING);
        }
        if (iKeyArea == 1)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, CLASS_NAME_KEY);
            keyArea.addKeyField(CLASS_NAME, DBConstants.ASCENDING);
        }
        if (keyArea == null)
            keyArea = super.setupKey(iKeyArea);     
        return keyArea;
    }
    /**
     * Update this record to reflect all the data in the based tour classes.
     */
    public void fixBasedFields()
    {
        if (this.getField(TourClass.BASED_CLASS_ID).isNull())
            return;
        if ((this.getEditMode() != DBConstants.EDIT_CURRENT) && (this.getEditMode() != DBConstants.EDIT_IN_PROGRESS))
            return; // Must be a current record
        if (this.getEditMode() == DBConstants.EDIT_IN_PROGRESS)
        {   // Unlikely
            if (this.isModified())
                return; // Can't be modified
            try {   // Change to EDIT_CURRENT
                Object bookmark = this.getHandle(DBConstants.BOOKMARK_HANDLE);
                this.setHandle(bookmark, DBConstants.BOOKMARK_HANDLE);
            } catch (DBException ex) {
                ex.printStackTrace();
            }
        }
        int iOldOpenMode = this.getOpenMode();
        if ((iOldOpenMode | DBConstants.OPEN_LOCK_ON_CHANGE_STRATEGY) == DBConstants.OPEN_LOCK_ON_CHANGE_STRATEGY)
            this.setOpenMode(iOldOpenMode & ~DBConstants.OPEN_REFRESH_AND_LOCK_ON_CHANGE_STRATEGY);    // Make sure I don't change this
        Object[] fieldListenerStatus = this.setEnableFieldListeners(false);
        boolean[] listenerStatus = this.setEnableListeners(false);
        
        Object bookmark = this.getField(TourClass.BASED_CLASS_ID).getData();
        TourClass recTourClass = new TourClass(this.findRecordOwner());
        try {
            while (bookmark != null)
            {
                if (recTourClass.setHandle(bookmark, DBConstants.BOOKMARK_HANDLE) == null)
                    return;
                // Now, move the default fields up to the main record
                for (int iFieldSeq = this.getFieldSeq(TourClass.DEPOSIT_DUE_DAYS); iFieldSeq < this.getFieldCount(); iFieldSeq++)
                {
                    if (this.getField(iFieldSeq).isNull())
                        this.getField(iFieldSeq).moveFieldToThis(recTourClass.getField(iFieldSeq));
                }
                bookmark = recTourClass.getField(TourClass.BASED_CLASS_ID).getData();
            }
        } catch (DBException ex) {
            ex.printStackTrace();
        } finally {
            this.setOpenMode(iOldOpenMode);
            this.setEnableFieldListeners(fieldListenerStatus);
            this.setEnableListeners(listenerStatus);
            recTourClass.free();
        }
    }

}

/**
 * @(#)TourClass.
 * Copyright © 2011 tourapp.com. All rights reserved.
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
import org.jbundle.base.screen.model.*;
import org.jbundle.base.screen.model.util.*;
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import com.tourapp.tour.product.tour.screen.*;
import com.tourapp.tour.product.tour.schedule.screen.*;
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

    //public static final int kID = kID;
    public static final int kClassName = kVirtualRecordLastField + 1;
    public static final int kBasedClassID = kClassName + 1;
    public static final int kDepositDueDays = kBasedClassID + 1;
    public static final int kFinalPayDays = kDepositDueDays + 1;
    public static final int kFinalizeDays = kFinalPayDays + 1;
    public static final int kOrderCompDays = kFinalizeDays + 1;
    public static final int kClosedDays = kOrderCompDays + 1;
    public static final int kFinalDocDays = kClosedDays + 1;
    public static final int kTicketDays = kFinalDocDays + 1;
    public static final int kSp1Days = kTicketDays + 1;
    public static final int kSp1Desc = kSp1Days + 1;
    public static final int kSp2Days = kSp1Desc + 1;
    public static final int kSp2Desc = kSp2Days + 1;
    public static final int kAddDeposit = kSp2Desc + 1;
    public static final int kDepositAmount = kAddDeposit + 1;
    public static final int kCancelCharge = kDepositAmount + 1;
    public static final int kModBeforeCharge = kCancelCharge + 1;
    public static final int kModAfterCharge = kModBeforeCharge + 1;
    public static final int kUpgradeProfileStatusID = kModAfterCharge + 1;
    public static final int kAirRateID = kUpgradeProfileStatusID + 1;
    public static final int kAirClassID = kAirRateID + 1;
    public static final int kHotelRateID = kAirClassID + 1;
    public static final int kHotelClassID = kHotelRateID + 1;
    public static final int kLandRateID = kHotelClassID + 1;
    public static final int kLandClassID = kLandRateID + 1;
    public static final int kPMCCutoff = kLandClassID + 1;
    public static final int kCarClassID = kPMCCutoff + 1;
    public static final int kCarRateID = kCarClassID + 1;
    public static final int kTransportationRateID = kCarRateID + 1;
    public static final int kTransportationClassID = kTransportationRateID + 1;
    public static final int kCruiseRateID = kTransportationClassID + 1;
    public static final int kCruiseClassID = kCruiseRateID + 1;
    public static final int kItemRateID = kCruiseClassID + 1;
    public static final int kItemClassID = kItemRateID + 1;
    public static final int kInitialBookingStatusID = kItemClassID + 1;
    public static final int kInitialTourStatusID = kInitialBookingStatusID + 1;
    public static final int kMarkup = kInitialTourStatusID + 1;
    public static final int kTourPricingTypeID = kMarkup + 1;
    public static final int kNonTourPricingTypeID = kTourPricingTypeID + 1;
    public static final int kTourClassLastField = kNonTourPricingTypeID;
    public static final int kTourClassFields = kNonTourPricingTypeID - DBConstants.MAIN_FIELD + 1;

    public static final int kIDKey = DBConstants.MAIN_KEY_FIELD;
    public static final int kClassNameKey = kIDKey + 1;
    public static final int kTourClassLastKey = kClassNameKey;
    public static final int kTourClassKeys = kClassNameKey - DBConstants.MAIN_KEY_FIELD + 1;
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

    public static final String kTourClassFile = "TourClass";
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(kTourClassFile, bAddQuotes) : super.getTableNames(bAddQuotes);
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
            screen = new TourEventScheduleGridScreen(this, null, (ScreenLocation)itsLocation, (BasePanel)parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if ((iDocMode & ScreenConstants.MAINT_MODE) == ScreenConstants.MAINT_MODE)
            screen = new TourClassScreen(this, (ScreenLocation)itsLocation, (BasePanel)parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if ((iDocMode & ScreenConstants.DISPLAY_MODE) != 0)
            screen = new TourClassGridScreen(this, (ScreenLocation)itsLocation, (BasePanel)parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
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
        if (iFieldSeq == kID)
        {
            field = new CounterField(this, "ID", 8, null, null);
            field.setHidden(true);
        }
        if (iFieldSeq == kClassName)
            field = new StringField(this, "ClassName", 15, null, null);
        if (iFieldSeq == kBasedClassID)
        {
            field = new TourClassSelect(this, "BasedClassID", 8, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kDepositDueDays)
        {
            field = new ShortField(this, "DepositDueDays", 3, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kFinalPayDays)
        {
            field = new ShortField(this, "FinalPayDays", 3, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kFinalizeDays)
        {
            field = new ShortField(this, "FinalizeDays", 3, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kOrderCompDays)
        {
            field = new ShortField(this, "OrderCompDays", 3, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kClosedDays)
        {
            field = new ShortField(this, "ClosedDays", 3, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kFinalDocDays)
        {
            field = new ShortField(this, "FinalDocDays", 3, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kTicketDays)
        {
            field = new ShortField(this, "TicketDays", 3, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kSp1Days)
        {
            field = new ShortField(this, "Sp1Days", 3, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kSp1Desc)
        {
            field = new StringField(this, "Sp1Desc", 15, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kSp2Days)
        {
            field = new ShortField(this, "Sp2Days", 3, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kSp2Desc)
        {
            field = new StringField(this, "Sp2Desc", 15, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kAddDeposit)
        {
            field = new BooleanField(this, "AddDeposit", 1, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kDepositAmount)
        {
            field = new CurrencyField(this, "DepositAmount", 10, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kCancelCharge)
        {
            field = new CurrencyField(this, "CancelCharge", 10, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kModBeforeCharge)
        {
            field = new CurrencyField(this, "ModBeforeCharge", 10, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kModAfterCharge)
        {
            field = new CurrencyField(this, "ModAfterCharge", 10, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kUpgradeProfileStatusID)
            field = new ProfileStatusField(this, "UpgradeProfileStatusID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kAirRateID)
        {
            field = new AirRateSelect(this, "AirRateID", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kAirClassID)
            field = new AirClassSelect(this, "AirClassID", 4, null, null);
        if (iFieldSeq == kHotelRateID)
            field = new HotelRateSelect(this, "HotelRateID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kHotelClassID)
            field = new HotelClassSelect(this, "HotelClassID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kLandRateID)
        {
            field = new LandRateSelect(this, "LandRateID", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kLandClassID)
            field = new LandClassSelect(this, "LandClassID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kPMCCutoff)
        {
            field = new ShortField(this, "PMCCutoff", 3, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kCarClassID)
            field = new CarClassSelect(this, "CarClassID", 4, null, null);
        if (iFieldSeq == kCarRateID)
            field = new CarRateSelect(this, "CarRateID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kTransportationRateID)
        {
            field = new TransportationRateSelect(this, "TransportationRateID", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kTransportationClassID)
        {
            field = new TransportationClassSelect(this, "TransportationClassID", 4, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kCruiseRateID)
        {
            field = new CruiseRateSelect(this, "CruiseRateID", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kCruiseClassID)
            field = new CruiseClassSelect(this, "CruiseClassID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kItemRateID)
            field = new ItemRateSelect(this, "ItemRateID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kItemClassID)
            field = new ItemClassSelect(this, "ItemClassID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kInitialBookingStatusID)
            field = new BookingStatusSelect(this, "InitialBookingStatusID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kInitialTourStatusID)
            field = new TourStatusSelect(this, "InitialTourStatusID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kMarkup)
            field = new PercentField(this, "Markup", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kTourPricingTypeID)
            field = new PricingTypeSelect(this, "TourPricingTypeID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kNonTourPricingTypeID)
            field = new PricingTypeSelect(this, "NonTourPricingTypeID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kTourClassLastField)
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
        if (iKeyArea == kClassNameKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "ClassName");
            keyArea.addKeyField(kClassName, DBConstants.ASCENDING);
        }
        if (keyArea == null) if (iKeyArea < kTourClassLastKey)
        {
            keyArea = super.setupKey(iKeyArea);     
            if (keyArea == null) if (iKeyArea < kTourClassLastKey)
                keyArea = new EmptyKey(this);
        }
        return keyArea;
    }
    /**
     * Update this record to reflect all the data in the based tour classes.
     */
    public void fixBasedFields()
    {
        if (this.getField(TourClass.kBasedClassID).isNull())
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
        
        Object bookmark = this.getField(TourClass.kBasedClassID).getData();
        TourClass recTourClass = new TourClass(this.findRecordOwner());
        try {
            while (bookmark != null)
            {
                if (recTourClass.setHandle(bookmark, DBConstants.BOOKMARK_HANDLE) == null)
                    return;
                // Now, move the default fields up to the main record
                for (int iFieldSeq = TourClass.kDepositDueDays; iFieldSeq < this.getFieldCount(); iFieldSeq++)
                {
                    if (this.getField(iFieldSeq).isNull())
                        this.getField(iFieldSeq).moveFieldToThis(recTourClass.getField(iFieldSeq));
                }
                bookmark = recTourClass.getField(TourClass.kBasedClassID).getData();
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

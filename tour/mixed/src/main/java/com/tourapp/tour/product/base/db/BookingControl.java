/**
 * @(#)BookingControl.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.product.base.db;

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
import com.tourapp.tour.profile.db.*;
import com.tourapp.tour.product.tour.db.*;
import com.tourapp.model.tour.product.base.db.*;

/**
 *  BookingControl - Control file.
 */
public class BookingControl extends ControlRecord
     implements BookingControlModel
{
    private static final long serialVersionUID = 1L;

    //public static final int kID = kID;
    public static final int kAutoBookingCode = kControlRecordLastField + 1;
    public static final int kAgencyComm = kAutoBookingCode + 1;
    public static final int kDepositDays = kAgencyComm + 1;
    public static final int kAcceptDays = kDepositDays + 1;
    public static final int kFinalDays = kAcceptDays + 1;
    public static final int kFinalizationDays = kFinalDays + 1;
    public static final int kFinalDocDays = kFinalizationDays + 1;
    public static final int kTicketingDays = kFinalDocDays + 1;
    public static final int kProfileTypeID = kTicketingDays + 1;
    public static final int kBookingStatusID = kProfileTypeID + 1;
    public static final int kXldBookingStatusID = kBookingStatusID + 1;
    public static final int kPaxCategoryID = kXldBookingStatusID + 1;
    public static final int kTourStatusID = kPaxCategoryID + 1;
    public static final int kXldTourStatusID = kTourStatusID + 1;
    public static final int kTourClassID = kXldTourStatusID + 1;
    public static final int kProductCategoryID = kTourClassID + 1;
    public static final int kTourHeaderID = kProductCategoryID + 1;
    public static final int kThinTourHeaderID = kTourHeaderID + 1;
    public static final int kRemoteTourHeaderID = kThinTourHeaderID + 1;
    public static final int kRemoteWaitTime = kRemoteTourHeaderID + 1;
    public static final int kPax = kRemoteWaitTime + 1;
    public static final int kSinglePax = kPax + 1;
    public static final int kDoublePax = kSinglePax + 1;
    public static final int kNights = kDoublePax + 1;
    public static final int kMarkup = kNights + 1;
    public static final int kTourPricingTypeID = kMarkup + 1;
    public static final int kNonTourPricingTypeID = kTourPricingTypeID + 1;
    public static final int kSeriesTourType = kNonTourPricingTypeID + 1;
    public static final int kTourHeaderTourType = kSeriesTourType + 1;
    public static final int kModuleTourType = kTourHeaderTourType + 1;
    public static final int kThinTourType = kModuleTourType + 1;
    public static final int kBookingControlLastField = kThinTourType;
    public static final int kBookingControlFields = kThinTourType - DBConstants.MAIN_FIELD + 1;

    public static final int kIDKey = DBConstants.MAIN_KEY_FIELD;
    public static final int kBookingControlLastKey = kIDKey;
    public static final int kBookingControlKeys = kIDKey - DBConstants.MAIN_KEY_FIELD + 1;
    /**
     * Default constructor.
     */
    public BookingControl()
    {
        super();
    }
    /**
     * Constructor.
     */
    public BookingControl(RecordOwner screen)
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

    public static final String kBookingControlFile = "BookingControl";
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(kBookingControlFile, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "Control";
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
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        if (iFieldSeq == kID)
        {
            field = new CounterField(this, "ID", 4, null, null);
            field.setHidden(true);
        }
        if (iFieldSeq == kAutoBookingCode)
            field = new BooleanField(this, "AutoBookingCode", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kAgencyComm)
            field = new PercentField(this, "AgencyComm", 5, null, new Float(0.10));
        if (iFieldSeq == kDepositDays)
            field = new ShortField(this, "DepositDays", 3, null, new Short((short)10));
        if (iFieldSeq == kAcceptDays)
            field = new ShortField(this, "AcceptDays", Constants.DEFAULT_FIELD_LENGTH, null, new Short((short)10));
        if (iFieldSeq == kFinalDays)
            field = new ShortField(this, "FinalDays", 3, null, new Short((short)30));
        if (iFieldSeq == kFinalizationDays)
            field = new ShortField(this, "FinalizationDays", 3, null, new Short((short)30));
        if (iFieldSeq == kFinalDocDays)
            field = new ShortField(this, "FinalDocDays", 3, null, new Short((short)15));
        if (iFieldSeq == kTicketingDays)
            field = new ShortField(this, "TicketingDays", 3, null, new Short((short)5));
        if (iFieldSeq == kProfileTypeID)
            field = new ProfileTypeFilter(this, "ProfileTypeID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kBookingStatusID)
            field = new BookingStatusField(this, "BookingStatusID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kXldBookingStatusID)
            field = new BookingStatusField(this, "XldBookingStatusID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kPaxCategoryID)
            field = new PaxCategoryField(this, "PaxCategoryID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kTourStatusID)
            field = new TourStatusField(this, "TourStatusID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kXldTourStatusID)
            field = new TourStatusField(this, "XldTourStatusID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kTourClassID)
            field = new TourClassField(this, "TourClassID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kProductCategoryID)
            field = new ProductCategoryField(this, "ProductCategoryID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kTourHeaderID)
            field = new TourHeaderField(this, "TourHeaderID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kThinTourHeaderID)
            field = new TourHeaderField(this, "ThinTourHeaderID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kRemoteTourHeaderID)
            field = new TourHeaderField(this, "RemoteTourHeaderID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kRemoteWaitTime)
            field = new IntegerField(this, "RemoteWaitTime", Constants.DEFAULT_FIELD_LENGTH, null, new Integer(10));
        if (iFieldSeq == kPax)
            field = new ShortField(this, "Pax", 2, null, null);
        if (iFieldSeq == kSinglePax)
            field = new ShortField(this, "SinglePax", 2, null, null);
        if (iFieldSeq == kDoublePax)
            field = new ShortField(this, "DoublePax", 2, null, null);
        if (iFieldSeq == kNights)
            field = new ShortField(this, "Nights", 2, null, new Short((short)1));
        if (iFieldSeq == kMarkup)
            field = new PercentField(this, "Markup", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kTourPricingTypeID)
            field = new PricingTypeField(this, "TourPricingTypeID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kNonTourPricingTypeID)
            field = new PricingTypeField(this, "NonTourPricingTypeID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kSeriesTourType)
            field = new TourTypeField(this, "SeriesTourType", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kTourHeaderTourType)
            field = new TourTypeField(this, "TourHeaderTourType", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kModuleTourType)
            field = new TourTypeField(this, "ModuleTourType", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kThinTourType)
            field = new TourTypeField(this, "ThinTourType", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kBookingControlLastField)
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
        if (keyArea == null) if (iKeyArea < kBookingControlLastKey)
        {
            keyArea = super.setupKey(iKeyArea);     
            if (keyArea == null) if (iKeyArea < kBookingControlLastKey)
                keyArea = new EmptyKey(this);
        }
        return keyArea;
    }

}

/**
 * @(#)VendorScreenRecord.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.acctpay.screen.trx;

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
import com.tourapp.tour.base.db.*;
import com.tourapp.tour.acctpay.db.*;
import com.tourapp.tour.booking.db.*;

/**
 *  VendorScreenRecord - Fields for Vendor display.
 */
public class VendorScreenRecord extends ScreenRecord
{
    private static final long serialVersionUID = 1L;

    public static final int kVendorKey = kScreenRecordLastField + 1;
    public static final int kVendorName = kVendorKey + 1;
    public static final int kVendorCountry = kVendorName + 1;
    public static final int kBalance = kVendorCountry + 1;
    public static final int kVendorID = kBalance + 1;
    public static final int kTourID = kVendorID + 1;
    public static final int kTotal = kTourID + 1;
    public static final int kSelectFlag = kTotal + 1;
    public static final int kTotalSelected = kSelectFlag + 1;
    public static final int kDisplayType = kTotalSelected + 1;
    public static final int kDisplayActive = kDisplayType + 1;
    public static final int kVendorScreenRecordLastField = kDisplayActive;
    public static final int kVendorScreenRecordFields = kDisplayActive - DBConstants.MAIN_FIELD + 1;
    /**
     * Default constructor.
     */
    public VendorScreenRecord()
    {
        super();
    }
    /**
     * Constructor.
     */
    public VendorScreenRecord(RecordOwner screen)
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

    public static final String kVendorScreenRecordFile = null;  // Screen field
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        if (iFieldSeq == kVendorKey)
            field = new ShortField(this, "VendorKey", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kVendorName)
            field = new StringField(this, "VendorName", 10, null, null);
        if (iFieldSeq == kVendorCountry)
            field = new CountryField(this, "VendorCountry", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kBalance)
            field = new FullCurrencyField(this, "Balance", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kVendorID)
            field = new VendorField(this, "VendorID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kTourID)
            field = new TourField(this, "TourID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kTotal)
            field = new FullCurrencyField(this, "Total", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kSelectFlag)
            field = new BooleanField(this, "SelectFlag", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kTotalSelected)
            field = new FullCurrencyField(this, "TotalSelected", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kDisplayType)
            field = new ApTrxClassField(this, "DisplayType", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kDisplayActive)
            field = new BooleanField(this, "DisplayActive", Constants.DEFAULT_FIELD_LENGTH, null, new Boolean(true));
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kVendorScreenRecordLastField)
                field = new EmptyField(this);
        }
        return field;
    }

}

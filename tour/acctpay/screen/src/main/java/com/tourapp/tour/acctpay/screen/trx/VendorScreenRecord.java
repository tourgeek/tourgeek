/**
 * @(#)VendorScreenRecord.
 * Copyright © 2012 tourapp.com. All rights reserved.
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
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import com.tourapp.tour.base.db.*;
import com.tourapp.tour.acctpay.db.*;
import com.tourapp.tour.base.field.*;

/**
 *  VendorScreenRecord - Fields for Vendor display.
 */
public class VendorScreenRecord extends ScreenRecord
{
    private static final long serialVersionUID = 1L;

    public static final String VENDOR_KEY = "VendorKey";
    public static final String VENDOR_NAME = "VendorName";
    public static final String VENDOR_COUNTRY = "VendorCountry";
    public static final String BALANCE = "Balance";
    public static final String VENDOR_ID = "VendorID";
    public static final String TOUR_ID = "TourID";
    public static final String TOTAL = "Total";
    public static final String SELECT_FLAG = "SelectFlag";
    public static final String TOTAL_SELECTED = "TotalSelected";
    public static final String DISPLAY_TYPE = "DisplayType";
    public static final String DISPLAY_ACTIVE = "DisplayActive";
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

    public static final String VENDOR_SCREEN_RECORD_FILE = null;    // Screen field
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        if (iFieldSeq == 0)
            field = new ShortField(this, VENDOR_KEY, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 1)
            field = new StringField(this, VENDOR_NAME, 10, null, null);
        if (iFieldSeq == 2)
            field = new CountryField(this, VENDOR_COUNTRY, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 3)
            field = new FullCurrencyField(this, BALANCE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 4)
            field = new VendorField(this, VENDOR_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 5)
            field = new TourField(this, TOUR_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 6)
            field = new FullCurrencyField(this, TOTAL, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 7)
            field = new BooleanField(this, SELECT_FLAG, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 8)
            field = new FullCurrencyField(this, TOTAL_SELECTED, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 9)
            field = new ApTrxClassField(this, DISPLAY_TYPE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 10)
            field = new BooleanField(this, DISPLAY_ACTIVE, Constants.DEFAULT_FIELD_LENGTH, null, new Boolean(true));
        if (field == null)
            field = super.setupField(iFieldSeq);
        return field;
    }

}

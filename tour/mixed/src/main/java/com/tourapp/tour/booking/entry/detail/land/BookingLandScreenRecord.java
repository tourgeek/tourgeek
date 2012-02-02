/**
 * @(#)BookingLandScreenRecord.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.booking.entry.detail.land;

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
import com.tourapp.tour.booking.entry.detail.base.*;

/**
 *  BookingLandScreenRecord - Fields for use in the BookingLandScreen.
 */
public class BookingLandScreenRecord extends BookingDetailScreenRecord
{
    private static final long serialVersionUID = 1L;

    //public static final int kPPCostLocal = kPPCostLocal;
    //public static final int kMarkupFromLast = kMarkupFromLast;
    public static final String SIC_COST_LOCAL = "SICCostLocal";
    public static final int kSICCostLocal = kBookingDetailScreenRecordLastField + 1;
    public static final String PMC_COST_LOCAL = "PMCCostLocal";
    public static final int kPMCCostLocal = kSICCostLocal + 1;
    public static final String LAND_INFO_FLAG = "LandInfoFlag";
    public static final int kLandInfoFlag = kPMCCostLocal + 1;
    public static final String VARY_QTY = "VaryQty";
    public static final int kVaryQty = kLandInfoFlag + 1;
    public static final int kBookingLandScreenRecordLastField = kVaryQty;
    public static final int kBookingLandScreenRecordFields = kVaryQty - DBConstants.MAIN_FIELD + 1;
    /**
     * Default constructor.
     */
    public BookingLandScreenRecord()
    {
        super();
    }
    /**
     * Constructor.
     */
    public BookingLandScreenRecord(RecordOwner screen)
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

    public static final String kBookingLandScreenRecordFile = null;   // Screen field
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        //if (iFieldSeq == kPPCostLocal)
        //  field = new CurrencyField(this, "PPCostLocal", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kSICCostLocal)
            field = new CurrencyField(this, "SICCostLocal", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kPMCCostLocal)
            field = new CurrencyField(this, "PMCCostLocal", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kMarkupFromLast)
        //  field = new (this, "MarkupFromLast", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kLandInfoFlag)
            field = new BooleanField(this, "LandInfoFlag", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kVaryQty)
            field = new ShortField(this, "VaryQty", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kBookingLandScreenRecordLastField)
                field = new EmptyField(this);
        }
        return field;
    }

}

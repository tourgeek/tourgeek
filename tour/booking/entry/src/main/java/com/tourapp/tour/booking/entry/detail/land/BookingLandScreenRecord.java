/**
  * @(#)BookingLandScreenRecord.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
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

    //public static final String PP_COST_LOCAL = PP_COST_LOCAL;
    //public static final String MARKUP_FROM_LAST = MARKUP_FROM_LAST;
    public static final String SIC_COST_LOCAL = "SICCostLocal";
    public static final String PMC_COST_LOCAL = "PMCCostLocal";
    public static final String LAND_INFO_FLAG = "LandInfoFlag";
    public static final String VARY_QTY = "VaryQty";
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

    public static final String BOOKING_LAND_SCREEN_RECORD_FILE = null;  // Screen field
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        //if (iFieldSeq == 0)
        //  field = new CurrencyField(this, PP_COST_LOCAL, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 1)
            field = new CurrencyField(this, SIC_COST_LOCAL, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 2)
            field = new CurrencyField(this, PMC_COST_LOCAL, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 3)
        //  field = new StringField(this, MARKUP_FROM_LAST, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 4)
            field = new BooleanField(this, LAND_INFO_FLAG, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 5)
            field = new ShortField(this, VARY_QTY, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
            field = super.setupField(iFieldSeq);
        return field;
    }

}

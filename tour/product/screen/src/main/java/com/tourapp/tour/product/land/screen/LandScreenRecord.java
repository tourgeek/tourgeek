/**
  * @(#)LandScreenRecord.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.tour.product.land.screen;

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
import com.tourapp.tour.product.base.screen.*;
import com.tourapp.tour.product.base.db.*;
import com.tourapp.tour.acctpay.db.*;
import com.tourapp.tour.base.db.*;
import com.tourapp.tour.product.land.db.*;
import org.jbundle.base.screen.model.*;

/**
 *  LandScreenRecord - Screen Fields.
 */
public class LandScreenRecord extends ProductScreenRecord
{
    private static final long serialVersionUID = 1L;

    //public static final String PRODUCT_ID = PRODUCT_ID;
    //public static final String START_DATE = START_DATE;
    //public static final String END_DATE = END_DATE;
    //public static final String DESCRIPTION = DESCRIPTION;
    //public static final String CITY_ID = CITY_ID;
    //public static final String TO_CITY_ID = TO_CITY_ID;
    //public static final String CONTINENT_ID = CONTINENT_ID;
    //public static final String REGION_ID = REGION_ID;
    //public static final String COUNTRY_ID = COUNTRY_ID;
    //public static final String STATE_ID = STATE_ID;
    //public static final String VENDOR_ID = VENDOR_ID;
    //public static final String RATE_ID = RATE_ID;
    //public static final String CLASS_ID = CLASS_ID;
    //public static final String DETAIL_DATE = DETAIL_DATE;
    //public static final String PAX = PAX;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String REMOTE_QUERY_ENABLED = REMOTE_QUERY_ENABLED;
    //public static final String BLOCKED = BLOCKED;
    //public static final String OVERSELL = OVERSELL;
    //public static final String CLOSED = CLOSED;
    //public static final String DELETE = DELETE;
    //public static final String READ_ONLY = READ_ONLY;
    //public static final String PRODUCT_SEARCH_TYPE_ID = PRODUCT_SEARCH_TYPE_ID;
    //public static final String PRODUCT_TYPE_ID = PRODUCT_TYPE_ID;
    public static final String LAND_CLASS_ID = "LandClassID";
    /**
     * Default constructor.
     */
    public LandScreenRecord()
    {
        super();
    }
    /**
     * Constructor.
     */
    public LandScreenRecord(RecordOwner screen)
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

    public static final String LAND_SCREEN_RECORD_FILE = null;  // Screen field
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        if (iFieldSeq == 0)
            field = new LandField(this, PRODUCT_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 1)
        //  field = new DateField(this, START_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 2)
        //  field = new DateField(this, END_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 3)
        //  field = new StringField(this, DESCRIPTION, 10, null, null);
        //if (iFieldSeq == 4)
        //  field = new CityField(this, CITY_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 5)
        //  field = new CityField(this, TO_CITY_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 6)
        //  field = new ContinentField(this, CONTINENT_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 7)
        //  field = new RegionField(this, REGION_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 8)
        //  field = new CountryField(this, COUNTRY_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 9)
        //  field = new StateField(this, STATE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 10)
        //  field = new VendorField(this, VENDOR_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 11)
        //  field = new BaseRateField(this, RATE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 12)
        //  field = new BaseClassField(this, CLASS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 13)
        //  field = new DateField(this, DETAIL_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 14)
        //  field = new ShortField(this, PAX, Constants.DEFAULT_FIELD_LENGTH, null, new Short((short)2));
        //if (iFieldSeq == 15)
        //  field = new LandScreenRecord_LastChanged(this, LAST_CHANGED, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 16)
        //  field = new BooleanField(this, REMOTE_QUERY_ENABLED, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 17)
        //  field = new ShortField(this, BLOCKED, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 18)
        //  field = new ShortField(this, OVERSELL, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 19)
        //  field = new BooleanField(this, CLOSED, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 20)
        //  field = new BooleanField(this, DELETE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 21)
        //  field = new BooleanField(this, READ_ONLY, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 22)
        //  field = new ProductSearchTypeField(this, PRODUCT_SEARCH_TYPE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 23)
        //  field = new ProductTypeField(this, PRODUCT_TYPE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 24)
            field = new LandClassField(this, LAND_CLASS_ID, 1, null, null);
        if (field == null)
            field = super.setupField(iFieldSeq);
        return field;
    }

}

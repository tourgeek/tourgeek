/**
  * @(#)BookingScreenRecord.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.tour.booking.db.event;

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
import com.tourgeek.tour.base.db.*;

/**
 *  BookingScreenRecord - Screen Fields for BkBooking.
 */
public class BookingScreenRecord extends ScreenRecord
{
    private static final long serialVersionUID = 1L;

    public static final String BK_SUB_SCREEN = "BkSubScreen";
    public static final String BK_HDR_CUR_BK = "BkHdrCurBk";
    public static final String OLD_PAX = "OldPax";
    public static final String OLD_SINGLE = "OldSingle";
    public static final String OLD_DOUBLE = "OldDouble";
    public static final String OLD_TRIPLE = "OldTriple";
    public static final String OLD_QUAD = "OldQuad";
    public static final String OLD_TOUR_NO = "OldTourNo";
    public static final String OLD_TOUR_STATUS = "OldTourStatus";
    public static final String OLD_ACTION_TYPE = "OldActionType";
    public static final String OLD_ACTION_DATE = "OldActionDate";
    public static final String OLD_MIN_TO_OP = "OldMinToOp";
    public static final String OLD_BK_NET_PRICE = "OldBkNetPrice";
    public static final String BK_CANCEL_CHG = "BkCancelChg";
    public static final String BK_SAVE_SINGLE_COST = "BkSaveSingleCost";
    public static final String BK_SAVE_DOUBLE_COST = "BkSaveDoubleCost";
    public static final String BK_SAVE_TRIPLE_COST = "BkSaveTripleCost";
    public static final String BK_SAVE_QUAD_COST = "BkSaveQuadCost";
    public static final String OLD_FOC = "OldFoc";
    public static final String OLD_DEP_DATE = "OldDepDate";
    public static final String LAST_LAST_NAME = "LastLastName";
    public static final String LAST_CITY_ID = "LastCityID";
    public static final String LAST_DATE = "LastDate";
    /**
     * Default constructor.
     */
    public BookingScreenRecord()
    {
        super();
    }
    /**
     * Constructor.
     */
    public BookingScreenRecord(RecordOwner screen)
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

    public static final String BOOKING_SCREEN_RECORD_FILE = null; // Screen field
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        if (iFieldSeq == 0)
            field = new ShortField(this, BK_SUB_SCREEN, 2, null, new Short((short)0));
        if (iFieldSeq == 1)
            field = new IntegerField(this, BK_HDR_CUR_BK, 6, null, null);
        if (iFieldSeq == 2)
            field = new ShortField(this, OLD_PAX, 2, null, null);
        if (iFieldSeq == 3)
            field = new ShortField(this, OLD_SINGLE, 2, null, null);
        if (iFieldSeq == 4)
            field = new ShortField(this, OLD_DOUBLE, 2, null, null);
        if (iFieldSeq == 5)
            field = new ShortField(this, OLD_TRIPLE, 2, null, null);
        if (iFieldSeq == 6)
            field = new ShortField(this, OLD_QUAD, 2, null, null);
        if (iFieldSeq == 7)
            field = new IntegerField(this, OLD_TOUR_NO, 6, null, null);
        if (iFieldSeq == 8)
            field = new StringField(this, OLD_TOUR_STATUS, 2, null, null);
        if (iFieldSeq == 9)
            field = new ShortField(this, OLD_ACTION_TYPE, 2, null, null);
        if (iFieldSeq == 10)
            field = new DateField(this, OLD_ACTION_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 11)
            field = new ShortField(this, OLD_MIN_TO_OP, 2, null, null);
        if (iFieldSeq == 12)
            field = new DoubleField(this, OLD_BK_NET_PRICE, 10, null, null);
        if (iFieldSeq == 13)
            field = new DoubleField(this, BK_CANCEL_CHG, 10, null, null);
        if (iFieldSeq == 14)
            field = new DoubleField(this, BK_SAVE_SINGLE_COST, 10, null, null);
        if (iFieldSeq == 15)
            field = new DoubleField(this, BK_SAVE_DOUBLE_COST, 10, null, null);
        if (iFieldSeq == 16)
            field = new DoubleField(this, BK_SAVE_TRIPLE_COST, 10, null, null);
        if (iFieldSeq == 17)
            field = new DoubleField(this, BK_SAVE_QUAD_COST, 10, null, null);
        if (iFieldSeq == 18)
            field = new ShortField(this, OLD_FOC, 2, null, null);
        if (iFieldSeq == 19)
            field = new DateField(this, OLD_DEP_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 20)
            field = new StringField(this, LAST_LAST_NAME, 20, null, null);
        if (iFieldSeq == 21)
            field = new CityField(this, LAST_CITY_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 22)
            field = new DateTimeField(this, LAST_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
            field = super.setupField(iFieldSeq);
        return field;
    }

}

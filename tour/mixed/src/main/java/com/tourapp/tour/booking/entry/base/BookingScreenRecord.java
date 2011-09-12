/**
 * @(#)BookingScreenRecord.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.booking.entry.base;

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
import com.tourapp.tour.product.tour.db.*;
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.product.base.db.*;
import com.tourapp.tour.profile.db.*;
import com.tourapp.tour.booking.db.event.*;
import com.tourapp.tour.booking.entry.acctrec.*;
import com.tourapp.tour.booking.entry.pax.*;
import com.tourapp.tour.booking.entry.tour.*;
import com.tourapp.tour.booking.entry.detail.hotel.*;
import com.tourapp.tour.booking.entry.detail.land.*;
import com.tourapp.tour.booking.entry.acctpay.*;
import com.tourapp.tour.booking.entry.calendar.*;
import com.tourapp.tour.booking.entry.detail.detail.*;
import com.tourapp.tour.booking.entry.itin.*;
import com.tourapp.tour.booking.entry.detail.car.*;
import com.tourapp.tour.booking.entry.detail.trans.*;
import com.tourapp.tour.booking.entry.detail.cruise.*;
import com.tourapp.tour.booking.entry.detail.air.*;
import com.tourapp.tour.booking.entry.detail.item.*;
import com.tourapp.tour.booking.entry.detail.tour.*;
import com.tourapp.tour.base.db.*;

/**
 *  BookingScreenRecord - Screen Fields for BkBooking.
 */
public class BookingScreenRecord extends ScreenRecord
{
    private static final long serialVersionUID = 1L;

    public static final int kBkSubScreen = kScreenRecordLastField + 1;
    public static final int kBkHdrCurBk = kBkSubScreen + 1;
    public static final int kOldPax = kBkHdrCurBk + 1;
    public static final int kOldSingle = kOldPax + 1;
    public static final int kOldDouble = kOldSingle + 1;
    public static final int kOldTriple = kOldDouble + 1;
    public static final int kOldQuad = kOldTriple + 1;
    public static final int kOldTourNo = kOldQuad + 1;
    public static final int kOldTourStatus = kOldTourNo + 1;
    public static final int kOldActionType = kOldTourStatus + 1;
    public static final int kOldActionDate = kOldActionType + 1;
    public static final int kOldMinToOp = kOldActionDate + 1;
    public static final int kOldBkNetPrice = kOldMinToOp + 1;
    public static final int kBkCancelChg = kOldBkNetPrice + 1;
    public static final int kBkSaveSingleCost = kBkCancelChg + 1;
    public static final int kBkSaveDoubleCost = kBkSaveSingleCost + 1;
    public static final int kBkSaveTripleCost = kBkSaveDoubleCost + 1;
    public static final int kBkSaveQuadCost = kBkSaveTripleCost + 1;
    public static final int kOldFoc = kBkSaveQuadCost + 1;
    public static final int kOldDepDate = kOldFoc + 1;
    public static final int kLastLastName = kOldDepDate + 1;
    public static final int kLastCityID = kLastLastName + 1;
    public static final int kLastDate = kLastCityID + 1;
    public static final int kBookingScreenRecordLastField = kLastDate;
    public static final int kBookingScreenRecordFields = kLastDate - DBConstants.MAIN_FIELD + 1;
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

    public static final String kBookingScreenRecordFile = null;   // Screen field
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        if (iFieldSeq == kBkSubScreen)
            field = new ShortField(this, "BkSubScreen", 2, null, new Short((short)0));
        if (iFieldSeq == kBkHdrCurBk)
            field = new IntegerField(this, "BkHdrCurBk", 6, null, null);
        if (iFieldSeq == kOldPax)
            field = new ShortField(this, "OldPax", 2, null, null);
        if (iFieldSeq == kOldSingle)
            field = new ShortField(this, "OldSingle", 2, null, null);
        if (iFieldSeq == kOldDouble)
            field = new ShortField(this, "OldDouble", 2, null, null);
        if (iFieldSeq == kOldTriple)
            field = new ShortField(this, "OldTriple", 2, null, null);
        if (iFieldSeq == kOldQuad)
            field = new ShortField(this, "OldQuad", 2, null, null);
        if (iFieldSeq == kOldTourNo)
            field = new IntegerField(this, "OldTourNo", 6, null, null);
        if (iFieldSeq == kOldTourStatus)
            field = new StringField(this, "OldTourStatus", 2, null, null);
        if (iFieldSeq == kOldActionType)
            field = new ShortField(this, "OldActionType", 2, null, null);
        if (iFieldSeq == kOldActionDate)
            field = new DateField(this, "OldActionDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kOldMinToOp)
            field = new ShortField(this, "OldMinToOp", 2, null, null);
        if (iFieldSeq == kOldBkNetPrice)
            field = new DoubleField(this, "OldBkNetPrice", 10, null, null);
        if (iFieldSeq == kBkCancelChg)
            field = new DoubleField(this, "BkCancelChg", 10, null, null);
        if (iFieldSeq == kBkSaveSingleCost)
            field = new DoubleField(this, "BkSaveSingleCost", 10, null, null);
        if (iFieldSeq == kBkSaveDoubleCost)
            field = new DoubleField(this, "BkSaveDoubleCost", 10, null, null);
        if (iFieldSeq == kBkSaveTripleCost)
            field = new DoubleField(this, "BkSaveTripleCost", 10, null, null);
        if (iFieldSeq == kBkSaveQuadCost)
            field = new DoubleField(this, "BkSaveQuadCost", 10, null, null);
        if (iFieldSeq == kOldFoc)
            field = new ShortField(this, "OldFoc", 2, null, null);
        if (iFieldSeq == kOldDepDate)
            field = new DateField(this, "OldDepDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kLastLastName)
            field = new StringField(this, "LastLastName", 20, null, null);
        if (iFieldSeq == kLastCityID)
            field = new CityField(this, "LastCityID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kLastDate)
            field = new DateTimeField(this, "LastDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kBookingScreenRecordLastField)
                field = new EmptyField(this);
        }
        return field;
    }

}

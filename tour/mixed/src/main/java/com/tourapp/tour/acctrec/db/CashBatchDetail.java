/**
 * @(#)CashBatchDetail.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.acctrec.db;

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
import com.tourapp.tour.acctrec.screen.cash.*;
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.product.tour.db.*;
import com.tourapp.model.tour.acctrec.db.*;

/**
 *  CashBatchDetail - Cash Receipts.
 */
public class CashBatchDetail extends VirtualRecord
     implements CashBatchDetailModel
{
    private static final long serialVersionUID = 1L;

    //public static final int kID = kID;
    public static final int kCashBatchID = kVirtualRecordLastField + 1;
    public static final int kBookingID = kCashBatchID + 1;
    public static final int kCheckNo = kBookingID + 1;
    public static final int kCheckABA = kCheckNo + 1;
    public static final int kAmount = kCheckABA + 1;
    public static final int kComments = kAmount + 1;
    public static final int kCashBatchDetailLastField = kComments;
    public static final int kCashBatchDetailFields = kComments - DBConstants.MAIN_FIELD + 1;

    public static final int kIDKey = DBConstants.MAIN_KEY_FIELD;
    public static final int kCashBatchIDKey = kIDKey + 1;
    public static final int kCashBatchDetailLastKey = kCashBatchIDKey;
    public static final int kCashBatchDetailKeys = kCashBatchIDKey - DBConstants.MAIN_KEY_FIELD + 1;
    /**
     * Default constructor.
     */
    public CashBatchDetail()
    {
        super();
    }
    /**
     * Constructor.
     */
    public CashBatchDetail(RecordOwner screen)
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

    public static final String kCashBatchDetailFile = "CashBatchDetail";
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(kCashBatchDetailFile, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "Cash Receipts";
    }
    /**
     * Get the Database Name.
     */
    public String getDatabaseName()
    {
        return "acctrec";
    }
    /**
     * Is this a local (vs remote) file?.
     */
    public int getDatabaseType()
    {
        return DBConstants.LOCAL | DBConstants.USER_DATA;
    }
    /**
     * MakeScreen Method.
     */
    public BaseScreen makeScreen(ScreenLocation itsLocation, BasePanel parentScreen, int iDocMode, Map<String,Object> properties)
    {
        BaseScreen screen = null;
        if ((iDocMode & ScreenConstants.DOC_MODE_MASK) == ScreenConstants.DETAIL_MODE)
            screen = new CashBatchDistGridScreen(this, null, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if ((iDocMode & ScreenConstants.DOC_MODE_MASK) == ScreenConstants.POST_MODE)
            screen = new CashBatchPost(this, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if ((iDocMode & ScreenConstants.MAINT_MODE) == ScreenConstants.MAINT_MODE)
            screen = new CashBatchDetailScreen(this, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else
            screen = new CashBatchDetailGridScreen(this, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        return screen;
    }
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        //if (iFieldSeq == kID)
        //{
        //  field = new CounterField(this, "ID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //  field.setHidden(true);
        //}
        if (iFieldSeq == kCashBatchID)
        {
            field = new CashBatchField(this, "CashBatchID", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setNullable(false);
        }
        if (iFieldSeq == kBookingID)
            field = new BookingField(this, "BookingID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kCheckNo)
            field = new StringField(this, "CheckNo", 8, null, null);
        if (iFieldSeq == kCheckABA)
            field = new StringField(this, "CheckABA", 10, null, null);
        if (iFieldSeq == kAmount)
            field = new CurrencyField(this, "Amount", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kComments)
            field = new StringField(this, "Comments", 30, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kCashBatchDetailLastField)
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
        if (iKeyArea == kCashBatchIDKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "CashBatchID");
            keyArea.addKeyField(kCashBatchID, DBConstants.ASCENDING);
            keyArea.addKeyField(kID, DBConstants.ASCENDING);
        }
        if (keyArea == null) if (iKeyArea < kCashBatchDetailLastKey)
        {
            keyArea = super.setupKey(iKeyArea);     
            if (keyArea == null) if (iKeyArea < kCashBatchDetailLastKey)
                keyArea = new EmptyKey(this);
        }
        return keyArea;
    }
    /**
     * AddMasterListeners Method.
     */
    public void addMasterListeners()
    {
        super.addMasterListeners();
        
        this.addListener(new SubFileIntegrityHandler(CashBatchDist.class.getName(), true));
    }

}

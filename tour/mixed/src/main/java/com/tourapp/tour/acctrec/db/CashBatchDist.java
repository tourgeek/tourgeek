/**
 * @(#)CashBatchDist.
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
import com.tourapp.tour.assetdr.db.*;
import com.tourapp.tour.acctrec.screen.cash.*;
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.genled.db.*;
import com.tourapp.model.tour.acctrec.db.*;

/**
 *  CashBatchDist - Cash Receipts.
 */
public class CashBatchDist extends BankTrxBatchDist
     implements CashBatchDistModel
{
    private static final long serialVersionUID = 1L;

    //public static final int kID = kID;
    public static final int kCashBatchDetailID = kBankTrxBatchDetailID;
    //public static final int kAccountID = kAccountID;
    //public static final int kAmount = kAmount;
    public static final int kBookingID = kBankTrxBatchDistLastField + 1;
    public static final int kCashBatchDistLastField = kBookingID;
    public static final int kCashBatchDistFields = kBookingID - DBConstants.MAIN_FIELD + 1;

    public static final int kIDKey = DBConstants.MAIN_KEY_FIELD;
    public static final int kCashBatchDetailIDKey = kIDKey + 1;
    public static final int kCashBatchDistLastKey = kCashBatchDetailIDKey;
    public static final int kCashBatchDistKeys = kCashBatchDetailIDKey - DBConstants.MAIN_KEY_FIELD + 1;
    /**
     * Default constructor.
     */
    public CashBatchDist()
    {
        super();
    }
    /**
     * Constructor.
     */
    public CashBatchDist(RecordOwner screen)
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

    public static final String kCashBatchDistFile = "CashBatchDist";
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(kCashBatchDistFile, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "Distribution";
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
     * Make a default screen.
     */
    public BaseScreen makeScreen(ScreenLocation itsLocation, BasePanel parentScreen, int iDocMode, Map<String,Object> properties)
    {
        BaseScreen screen = null;
        if ((iDocMode & ScreenConstants.MAINT_MODE) == ScreenConstants.MAINT_MODE)
            screen = BaseScreen.makeNewScreen(CASH_BATCH_DIST_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        if ((iDocMode & ScreenConstants.DISPLAY_MODE) == ScreenConstants.DISPLAY_MODE)
            screen = BaseScreen.makeNewScreen(CASH_BATCH_DIST_GRID_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
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
        //if (iFieldSeq == kID)
        //{
        //  field = new CounterField(this, "ID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //  field.setHidden(true);
        //}
        if (iFieldSeq == kCashBatchDetailID)
        {
            field = new ReferenceField(this, "CashBatchDetailID", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setNullable(false);
        }
        if (iFieldSeq == kBookingID)
            field = new BookingField(this, "BookingID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kAccountID)
        //  field = new AccountField(this, "AccountID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kAmount)
        //  field = new CurrencyField(this, "Amount", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kCashBatchDistLastField)
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
        if (iKeyArea == kCashBatchDetailIDKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "CashBatchDetailID");
            keyArea.addKeyField(kCashBatchDetailID, DBConstants.ASCENDING);
        }
        if (keyArea == null) if (iKeyArea < kCashBatchDistLastKey)
        {
            keyArea = super.setupKey(iKeyArea);     
            if (keyArea == null) if (iKeyArea < kCashBatchDistLastKey)
                keyArea = new EmptyKey(this);
        }
        return keyArea;
    }

}

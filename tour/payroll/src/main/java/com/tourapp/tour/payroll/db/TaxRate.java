/**
 * @(#)TaxRate.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.payroll.db;

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
import com.tourapp.tour.payroll.screen.tax.*;
import com.tourapp.model.tour.payroll.db.*;

/**
 *  TaxRate - Tax Rates.
 */
public class TaxRate extends VirtualRecord
     implements TaxRateModel
{
    private static final long serialVersionUID = 1L;

    //public static final int kID = kID;
    public static final int kTaxCode = kVirtualRecordLastField + 1;
    public static final int kMaritalStatus = kTaxCode + 1;
    public static final int kCutOffAmount = kMaritalStatus + 1;
    public static final int kTaxRate = kCutOffAmount + 1;
    public static final int kTaxRateLastField = kTaxRate;
    public static final int kTaxRateFields = kTaxRate - DBConstants.MAIN_FIELD + 1;

    public static final int kIDKey = DBConstants.MAIN_KEY_FIELD;
    public static final int kTaxCodeKey = kIDKey + 1;
    public static final int kTaxRateLastKey = kTaxCodeKey;
    public static final int kTaxRateKeys = kTaxCodeKey - DBConstants.MAIN_KEY_FIELD + 1;
    /**
     * Default constructor.
     */
    public TaxRate()
    {
        super();
    }
    /**
     * Constructor.
     */
    public TaxRate(RecordOwner screen)
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

    public static final String kTaxRateFile = "TaxRate";
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(kTaxRateFile, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "Tax rate";
    }
    /**
     * Get the Database Name.
     */
    public String getDatabaseName()
    {
        return "payroll";
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
        if ((iDocMode & ScreenConstants.MAINT_MODE) != 0)
            screen = new TaxRateScreen(this, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if ((iDocMode & ScreenConstants.DISPLAY_MODE) != 0)
            screen = new TaxRateGridScreen(this, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
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
        if (iFieldSeq == kTaxCode)
            field = new StringField(this, "TaxCode", 2, null, null);
        if (iFieldSeq == kMaritalStatus)
        {
            field = new MaritalStatusField(this, "MaritalStatus", 1, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kCutOffAmount)
            field = new CurrencyField(this, "CutOffAmount", 8, null, null);
        if (iFieldSeq == kTaxRate)
            field = new PercentField(this, "TaxRate", 5, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kTaxRateLastField)
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
        if (iKeyArea == kTaxCodeKey)
        {
            keyArea = this.makeIndex(DBConstants.UNIQUE, "TaxCode");
            keyArea.addKeyField(kTaxCode, DBConstants.ASCENDING);
            keyArea.addKeyField(kMaritalStatus, DBConstants.ASCENDING);
            keyArea.addKeyField(kCutOffAmount, DBConstants.ASCENDING);
        }
        if (keyArea == null) if (iKeyArea < kTaxRateLastKey)
        {
            keyArea = super.setupKey(iKeyArea);     
            if (keyArea == null) if (iKeyArea < kTaxRateLastKey)
                keyArea = new EmptyKey(this);
        }
        return keyArea;
    }

}

/**
  * @(#)Currencys.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.tour.base.db;

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
import org.jbundle.main.user.db.*;
import com.tourapp.tour.base.screen.*;
import com.tourapp.model.tour.base.db.*;

/**
 *  Currencys - Currencies.
 */
public class Currencys extends VirtualRecord
     implements CurrencysModel
{
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor.
     */
    public Currencys()
    {
        super();
    }
    /**
     * Constructor.
     */
    public Currencys(RecordOwner screen)
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
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(CURRENCYS_FILE, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "Currency";
    }
    /**
     * Get the Database Name.
     */
    public String getDatabaseName()
    {
        return "assetdr";
    }
    /**
     * Is this a local (vs remote) file?.
     */
    public int getDatabaseType()
    {
        return DBConstants.LOCAL | DBConstants.SHARED_DATA | DBConstants.LOCALIZABLE;
    }
    /**
     * Make a default screen.
     */
    public ScreenParent makeScreen(ScreenLoc itsLocation, ComponentParent parentScreen, int iDocMode, Map<String,Object> properties)
    {
        ScreenParent screen = null;
        if ((iDocMode & ScreenConstants.MAINT_MODE) == ScreenConstants.MAINT_MODE)
            screen = Record.makeNewScreen(CURRENCYS_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        else if ((iDocMode & ScreenConstants.DISPLAY_MODE) == ScreenConstants.DISPLAY_MODE)
            screen = Record.makeNewScreen(CURRENCYS_GRID_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
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
        //if (iFieldSeq == 0)
        //{
        //  field = new CounterField(this, ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //  field.setHidden(true);
        //}
        //if (iFieldSeq == 1)
        //{
        //  field = new RecordChangedField(this, LAST_CHANGED, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //  field.setHidden(true);
        //}
        //if (iFieldSeq == 2)
        //{
        //  field = new BooleanField(this, DELETED, Constants.DEFAULT_FIELD_LENGTH, null, new Boolean(false));
        //  field.setHidden(true);
        //}
        if (iFieldSeq == 3)
            field = new StringField(this, DESCRIPTION, 25, null, null);
        if (iFieldSeq == 4)
            field = new StringField(this, CURRENCY_CODE, 3, null, null);
        if (iFieldSeq == 5)
            field = new RealField(this, LAST_RATE, 10, null, null);
        if (iFieldSeq == 6)
            field = new DateField(this, RATE_CHANGED_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 7)
            field = new UserField(this, RATE_CHANGED_BY, 16, null, null);
        if (iFieldSeq == 8)
            field = new RealField(this, COSTING_RATE, 10, null, null);
        if (iFieldSeq == 9)
            field = new DateField(this, COSTING_CHANGED_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 10)
            field = new UserField(this, COSTING_CHANGED_BY, 16, null, null);
        if (iFieldSeq == 11)
        {
            field = new ShortField(this, ROUND_AT, 1, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 12)
            field = new StringField(this, INTEGER_DESC, 20, null, "Dollar");
        if (iFieldSeq == 13)
            field = new StringField(this, FRACTION_DESC, 20, null, null);
        if (iFieldSeq == 14)
            field = new IntegerField(this, FRACTION_AMOUNT, Constants.DEFAULT_FIELD_LENGTH, null, new Integer(100));
        if (iFieldSeq == 15)
            field = new StringField(this, SIGN, 3, null, "$");
        if (iFieldSeq == 16)
            field = new LanguageField(this, LANGUAGE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 17)
            field = new StringField(this, NATURAL_INTEGER, 20, null, null);
        if (iFieldSeq == 18)
            field = new StringField(this, NATURAL_FRACTION, 20, null, null);
        if (field == null)
            field = super.setupField(iFieldSeq);
        return field;
    }
    /**
     * Add this key area description to the Record.
     */
    public KeyArea setupKey(int iKeyArea)
    {
        KeyArea keyArea = null;
        if (iKeyArea == 0)
        {
            keyArea = this.makeIndex(DBConstants.UNIQUE, "ID");
            keyArea.addKeyField(ID, DBConstants.ASCENDING);
        }
        if (iKeyArea == 1)
        {
            keyArea = this.makeIndex(DBConstants.SECONDARY_KEY, "CurrencyCode");
            keyArea.addKeyField(CURRENCY_CODE, DBConstants.ASCENDING);
        }
        if (iKeyArea == 2)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "Description");
            keyArea.addKeyField(DESCRIPTION, DBConstants.ASCENDING);
        }
        if (keyArea == null)
            keyArea = super.setupKey(iKeyArea);     
        return keyArea;
    }
    /**
     * AddMasterListeners Method.
     */
    public void addMasterListeners()
    {
        DateUpdatedHandler update1 = new DateUpdatedHandler(Currencys.RATE_CHANGED_DATE);
        this.getField(Currencys.LAST_RATE).addListener(update1);
        ChangedByHandler name1 = new ChangedByHandler(Currencys.RATE_CHANGED_BY);
        this.getField(Currencys.LAST_RATE).addListener(name1);
        DateUpdatedHandler update2 = new DateUpdatedHandler(Currencys.COSTING_CHANGED_DATE);
        this.getField(Currencys.COSTING_RATE).addListener(update2);
        ChangedByHandler name2 = new ChangedByHandler(Currencys.COSTING_CHANGED_BY);
        this.getField(Currencys.COSTING_RATE).addListener(name2);
    }
    /**
     * Convert to USD.
     */
    public double convertCostToUSD(double dCost, boolean bCostingExchange)
    {
        double dExchange;
        if ((bCostingExchange) && (!this.getField(Currencys.COSTING_RATE).isNull()))
            dExchange = this.getField(Currencys.COSTING_RATE).getValue();
        else
            dExchange = this.getField(Currencys.LAST_RATE).getValue();
        dCost = Math.floor(dCost * dExchange * 100.00 + 0.5) / 100.00;
        return dCost;
    }

}

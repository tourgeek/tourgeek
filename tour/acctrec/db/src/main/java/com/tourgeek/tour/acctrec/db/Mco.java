/**
  * @(#)Mco.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.tour.acctrec.db;

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
import com.tourgeek.tour.acctrec.db.event.*;
import com.tourgeek.tour.base.field.*;
import com.tourgeek.model.tour.acctrec.db.*;

/**
 *  Mco - MCOs.
 */
public class Mco extends BaseArPay
     implements McoModel
{
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor.
     */
    public Mco()
    {
        super();
    }
    /**
     * Constructor.
     */
    public Mco(RecordOwner screen)
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
        return (m_tableName == null) ? Record.formatTableNames(MCO_FILE, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "MCO";
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
        return DBConstants.REMOTE | DBConstants.USER_DATA;
    }
    /**
     * MakeScreen Method.
     */
    public ScreenParent makeScreen(ScreenLoc itsLocation, ComponentParent parentScreen, int iDocMode, Map<String,Object> properties)
    {
        ScreenParent screen = null;
        if ((iDocMode & BaseArPay.DISTRIBUTION_SCREEN) == BaseArPay.DISTRIBUTION_SCREEN)
            screen = Record.makeNewScreen(MCO_DIST_GRID_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        else if ((iDocMode & ScreenConstants.DOC_MODE_MASK) == ScreenConstants.POST_MODE)
            screen = Record.makeNewScreen(MCO_POST_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        else if (iDocMode == Mco.ENTRY_SCREEN)
            screen = Record.makeNewScreen(MCO_ENTRY_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        else if (iDocMode == Mco.ENTRY_GRID_SCREEN)
            screen = Record.makeNewScreen(MCO_ENTRY_GRID_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        else if (iDocMode == Mco.COLL_POST)
            screen = Record.makeNewScreen(MCO_COLL_POST_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        else if (iDocMode == Mco.COLL_SCREEN)
            screen = Record.makeNewScreen(MCO_COLL_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        else if ((iDocMode & ScreenConstants.DOC_MODE_MASK) == ScreenConstants.DETAIL_MODE)
            screen = Record.makeNewScreen(MCO_BATCH_DIST_GRID_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        else if ((iDocMode & ScreenConstants.MAINT_MODE) == ScreenConstants.MAINT_MODE)
            screen = Record.makeNewScreen(MCO_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        else
            screen = Record.makeNewScreen(MCO_GRID_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
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
        //if (iFieldSeq == 3)
        //  field = new TrxStatusField(this, TRX_STATUS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 4)
        //  field = new Mco_TrxUserID(this, TRX_USER_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 5)
        //  field = new Mco_TrxDate(this, TRX_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 6)
        //  field = new CurrencyField(this, AMT_APPLY, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 7)
        //  field = new Mco_TrxEntry(this, TRX_ENTRY, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 8)
        //  field = new BookingField(this, BOOKING_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 9)
            field = new CurrencyField(this, GROSS, 10, null, null);
        //if (iFieldSeq == 10)
        //{
        //  field = new PercentField(this, SVC_PER, 5, null, null);
        //  field.addListener(new InitOnceFieldHandler(null));
        //}
        //if (iFieldSeq == 11)
        //  field = new CurrencyField(this, SVC_AMT, 8, null, null);
        if (iFieldSeq == 12)
            field = new CurrencyField(this, NET, 10, null, null);
        //if (iFieldSeq == 13)
        //  field = new StringField(this, COMMENTS, 30, null, null);
        //if (iFieldSeq == 14)
        //  field = new DateTimeField(this, DATE_SUBMITTED, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 15)
        //  field = new DateTimeField(this, DATE_PAID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 16)
        //  field = new CurrencyField(this, AMOUNT_PAID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 17)
        {
            field = new BooleanField(this, PAID, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setVirtual(true);
        }
        //if (iFieldSeq == 18)
        //  field = new DateTimeField(this, PAYMENT_ENTERED, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 19)
            field = new StringField(this, MCO_NO, 16, null, null);
        if (iFieldSeq == 20)
        {
            field = new AirlineField(this, AIRLINE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 21)
            field = new PercentField(this, COMM_PER, 5, null, new Float(0.10));
        if (iFieldSeq == 22)
        {
            field = new CurrencyField(this, COMM_AMT, 10, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 23)
        {
            field = new PercentField(this, TAX_PER, 5, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 24)
        {
            field = new CurrencyField(this, TAX_AMT, 10, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 25)
            field = new PercentField(this, CARRIER_SVC_PER, Constants.DEFAULT_FIELD_LENGTH, null, null);
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
            keyArea = this.makeIndex(DBConstants.UNIQUE, ID_KEY);
            keyArea.addKeyField(ID, DBConstants.ASCENDING);
        }
        if (iKeyArea == 1)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, TRX_STATUS_ID_KEY);
            keyArea.addKeyField(TRX_STATUS_ID, DBConstants.ASCENDING);
        }
        if (iKeyArea == 2)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, MCO_NO_KEY);
            keyArea.addKeyField(MCO_NO, DBConstants.ASCENDING);
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
        super.addMasterListeners();
        
        this.getField(Mco.GROSS).addListener(new CalcBalanceHandler(this.getField(Mco.COMM_AMT), this.getField(Mco.GROSS), this.getField(Mco.COMM_PER), "*", false));
        this.getField(Mco.COMM_PER).addListener(new CalcBalanceHandler(this.getField(Mco.COMM_AMT), this.getField(Mco.GROSS), this.getField(Mco.COMM_PER), "*", false));
        this.getField(Mco.GROSS).addListener(new CalcBalanceHandler(this.getField(Mco.SVC_AMT), this.getField(Mco.GROSS), this.getField(Mco.SVC_PER), "*", false));
        this.getField(Mco.SVC_PER).addListener(new CalcBalanceHandler(this.getField(Mco.SVC_AMT), this.getField(Mco.GROSS), this.getField(Mco.SVC_PER), "*", false));
        this.getField(Mco.GROSS).addListener(new CalcBalanceHandler(this.getField(Mco.TAX_AMT), this.getField(Mco.GROSS), this.getField(Mco.TAX_PER), "*", false));
        this.getField(Mco.TAX_PER).addListener(new CalcBalanceHandler(this.getField(Mco.TAX_AMT), this.getField(Mco.GROSS), this.getField(Mco.TAX_PER), "*", false));
        this.getField(Mco.GROSS).addListener(new CalcMcoHandler(null));
        this.getField(Mco.COMM_AMT).addListener(new CalcMcoHandler(null));
        this.getField(Mco.SVC_AMT).addListener(new CalcMcoHandler(null));
        this.getField(Mco.TAX_AMT).addListener(new CalcMcoHandler(null));
        this.getField(Mco.NET).addListener(new MoveOnChangeHandler(this.getField(Mco.AMT_APPLY), this.getField(Mco.NET)));
    }

}

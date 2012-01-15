/**
 * @(#)AccountBudget.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.genled.db;

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
import com.tourapp.tour.genled.screen.misc.*;
import com.tourapp.model.tour.genled.db.*;

/**
 *  AccountBudget - Budgets and Comparatives..
 */
public class AccountBudget extends VirtualRecord
     implements AccountBudgetModel
{
    private static final long serialVersionUID = 1L;

    //public static final int kID = kID;
    public static final int kAccountID = kVirtualRecordLastField + 1;
    public static final int kBudComCode = kAccountID + 1;
    public static final int kDetailDate = kBudComCode + 1;
    public static final int kAmount = kDetailDate + 1;
    public static final int kAccountBudgetLastField = kAmount;
    public static final int kAccountBudgetFields = kAmount - DBConstants.MAIN_FIELD + 1;

    public static final int kIDKey = DBConstants.MAIN_KEY_FIELD;
    public static final int kBudComCodeKey = kIDKey + 1;
    public static final int kAccountBudgetLastKey = kBudComCodeKey;
    public static final int kAccountBudgetKeys = kBudComCodeKey - DBConstants.MAIN_KEY_FIELD + 1;
    /**
     * Default constructor.
     */
    public AccountBudget()
    {
        super();
    }
    /**
     * Constructor.
     */
    public AccountBudget(RecordOwner screen)
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

    public static final String kAccountBudgetFile = "AccountBudget";
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(kAccountBudgetFile, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "Budget & comparatives";
    }
    /**
     * Get the Database Name.
     */
    public String getDatabaseName()
    {
        return "genled";
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
            screen = BaseScreen.makeNewScreen(ACCOUNT_BUDGET_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        if ((iDocMode & ScreenConstants.DISPLAY_MODE) == ScreenConstants.DISPLAY_MODE)
            screen = BaseScreen.makeNewScreen(ACCOUNT_BUDGET_GRID_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
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
        if (iFieldSeq == kAccountID)
            field = new AccountField(this, "AccountID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kBudComCode)
            field = new BudgetCompField(this, "BudComCode", Constants.DEFAULT_FIELD_LENGTH, null, "B");
        if (iFieldSeq == kDetailDate)
        {
            field = new DateField(this, "DetailDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kAmount)
            field = new DrCrField(this, "Amount", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kAccountBudgetLastField)
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
        if (iKeyArea == kBudComCodeKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "BudComCode");
            keyArea.addKeyField(kBudComCode, DBConstants.ASCENDING);
            keyArea.addKeyField(kAccountID, DBConstants.ASCENDING);
            keyArea.addKeyField(kDetailDate, DBConstants.ASCENDING);
        }
        if (keyArea == null) if (iKeyArea < kAccountBudgetLastKey)
        {
            keyArea = super.setupKey(iKeyArea);     
            if (keyArea == null) if (iKeyArea < kAccountBudgetLastKey)
                keyArea = new EmptyKey(this);
        }
        return keyArea;
    }

}

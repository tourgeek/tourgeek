/**
 * @(#)Account.
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
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import com.tourapp.tour.genled.screen.misc.*;
import com.tourapp.tour.genled.screen.detail.*;
import com.tourapp.model.tour.genled.db.*;

/**
 *  Account - Chart of Accounts.
 */
public class Account extends VirtualRecord
     implements AccountModel
{
    private static final long serialVersionUID = 1L;

    //public static final int kID = kID;
    public static final int kDescription = kVirtualRecordLastField + 1;
    public static final int kAccountNo = kDescription + 1;
    public static final int kTypicalBalance = kAccountNo + 1;
    public static final int kSectionSubTotal = kTypicalBalance + 1;
    public static final int kCounterAccountID = kSectionSubTotal + 1;
    public static final int kAutoDistID = kCounterAccountID + 1;
    public static final int kCloseYearEnd = kAutoDistID + 1;
    public static final int kDiscontinued = kCloseYearEnd + 1;
    public static final int kAccountLastField = kDiscontinued;
    public static final int kAccountFields = kDiscontinued - DBConstants.MAIN_FIELD + 1;

    public static final int kIDKey = DBConstants.MAIN_KEY_FIELD;
    public static final int kAccountNoKey = kIDKey + 1;
    public static final int kDescriptionKey = kAccountNoKey + 1;
    public static final int kAccountLastKey = kDescriptionKey;
    public static final int kAccountKeys = kDescriptionKey - DBConstants.MAIN_KEY_FIELD + 1;
    public static final int ACCT_DETAIL_GRID_SCREEN = ScreenConstants.DETAIL_MODE;
    /**
     * Default constructor.
     */
    public Account()
    {
        super();
    }
    /**
     * Constructor.
     */
    public Account(RecordOwner screen)
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

    public static final String kAccountFile = "Account";
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(kAccountFile, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "Chart of accounts";
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
     * MakeScreen Method.
     */
    public ScreenParent makeScreen(ScreenLoc itsLocation, ComponentParent parentScreen, int iDocMode, Map<String,Object> properties)
    {
        ScreenParent screen = null;
        if ((iDocMode & ScreenConstants.DOC_MODE_MASK) == Account.ACCT_DETAIL_GRID_SCREEN)
            screen = new AcctDetailGridScreen(this, null, (ScreenLocation)itsLocation, (BasePanel)parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if ((iDocMode & ScreenConstants.MAINT_MODE) == ScreenConstants.MAINT_MODE)
            screen = new AccountScreen(this, (ScreenLocation)itsLocation, (BasePanel)parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else
            screen = new AccountGridScreen(this, (ScreenLocation)itsLocation, (BasePanel)parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
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
        if (iFieldSeq == kDescription)
            field = new StringField(this, "Description", 30, null, null);
        if (iFieldSeq == kAccountNo)
            field = new AccountNoField(this, "AccountNo", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kTypicalBalance)
            field = new TypicalBalanceField(this, "TypicalBalance", 1, null, "D");
        if (iFieldSeq == kSectionSubTotal)
        {
            field = new BooleanField(this, "SectionSubTotal", 1, null, new Boolean(false));
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kCounterAccountID)
            field = new AccountField(this, "CounterAccountID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kAutoDistID)
            field = new AutoDistField(this, "AutoDistID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kCloseYearEnd)
            field = new BooleanField(this, "CloseYearEnd", 1, null, null);
        if (iFieldSeq == kDiscontinued)
            field = new BooleanField(this, "Discontinued", 1, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kAccountLastField)
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
        if (iKeyArea == kAccountNoKey)
        {
            keyArea = this.makeIndex(DBConstants.SECONDARY_KEY, "AccountNo");
            keyArea.addKeyField(kAccountNo, DBConstants.ASCENDING);
        }
        if (iKeyArea == kDescriptionKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "Description");
            keyArea.addKeyField(kDescription, DBConstants.ASCENDING);
        }
        if (keyArea == null) if (iKeyArea < kAccountLastKey)
        {
            keyArea = super.setupKey(iKeyArea);     
            if (keyArea == null) if (iKeyArea < kAccountLastKey)
                keyArea = new EmptyKey(this);
        }
        return keyArea;
    }
    /**
     * AddMasterListeners Method.
     */
    public void addMasterListeners()
    {
        this.setKeyArea(Account.kAccountNoKey);   // Default key order
        
        this.addListener(new SoftDeleteDetailHandler(this.getField(Account.kDiscontinued), null)
        {
            public Record getDetailRecord(RecordOwner screen)
            {
                Record record = super.getDetailRecord(screen);
                if (record == null)
                {
                    record = new AcctDetail(screen);
                    record.addListener(new SubFileFilter(this.getOwner()));
                    this.setDetailRecord(record);
                }
                return record;
            }
        });
        
        super.addMasterListeners();
    }

}

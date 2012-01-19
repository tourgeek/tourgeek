/**
 * @(#)Card.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.profile.db;

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
import com.tourapp.tour.genled.db.*;
import com.tourapp.model.tour.profile.db.*;

/**
 *  Card - Credit card type.
 */
public class Card extends VirtualRecord
     implements CardModel
{
    private static final long serialVersionUID = 1L;

    //public static final int kID = kID;
    public static final int kDescription = kVirtualRecordLastField + 1;
    public static final int kServiceCharge = kDescription + 1;
    public static final int kCreditCardRecAccountID = kServiceCharge + 1;
    public static final int kCreditCardVarAccountID = kCreditCardRecAccountID + 1;
    public static final int kCardCode = kCreditCardVarAccountID + 1;
    public static final int kCardLastField = kCardCode;
    public static final int kCardFields = kCardCode - DBConstants.MAIN_FIELD + 1;

    public static final int kIDKey = DBConstants.MAIN_KEY_FIELD;
    public static final int kCardLastKey = kIDKey;
    public static final int kCardKeys = kIDKey - DBConstants.MAIN_KEY_FIELD + 1;
    /**
     * Default constructor.
     */
    public Card()
    {
        super();
    }
    /**
     * Constructor.
     */
    public Card(RecordOwner screen)
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

    public static final String kCardFile = "Card";
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(kCardFile, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "Card Type";
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
        return DBConstants.TABLE | DBConstants.SHARED_DATA | DBConstants.LOCALIZABLE;
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
        if (iFieldSeq == kServiceCharge)
            field = new PercentField(this, "ServiceCharge", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kCreditCardRecAccountID)
            field = new AccountField(this, "CreditCardRecAccountID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kCreditCardVarAccountID)
            field = new AccountField(this, "CreditCardVarAccountID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kCardCode)
            field = new StringField(this, "CardCode", 2, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kCardLastField)
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
        if (keyArea == null) if (iKeyArea < kCardLastKey)
        {
            keyArea = super.setupKey(iKeyArea);     
            if (keyArea == null) if (iKeyArea < kCardLastKey)
                keyArea = new EmptyKey(this);
        }
        return keyArea;
    }

}

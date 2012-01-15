/**
 * @(#)ProfileCreditCard.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.profile.detail;

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
import com.tourapp.tour.profile.db.*;
import com.tourapp.model.tour.profile.detail.*;

/**
 *  ProfileCreditCard - Credit cards.
 */
public class ProfileCreditCard extends VirtualRecord
     implements ProfileCreditCardModel
{
    private static final long serialVersionUID = 1L;

    //public static final int kID = kID;
    public static final int kProfileID = kVirtualRecordLastField + 1;
    public static final int kCCCode = kProfileID + 1;
    public static final int kCCHolderName = kCCCode + 1;
    public static final int kCCNumber = kCCHolderName + 1;
    public static final int kCCBeginDate = kCCNumber + 1;
    public static final int kCCExpire = kCCBeginDate + 1;
    public static final int kProfileCreditCardLastField = kCCExpire;
    public static final int kProfileCreditCardFields = kCCExpire - DBConstants.MAIN_FIELD + 1;

    public static final int kIDKey = DBConstants.MAIN_KEY_FIELD;
    public static final int kProfileIDKey = kIDKey + 1;
    public static final int kCCNumberKey = kProfileIDKey + 1;
    public static final int kProfileCreditCardLastKey = kCCNumberKey;
    public static final int kProfileCreditCardKeys = kCCNumberKey - DBConstants.MAIN_KEY_FIELD + 1;
    /**
     * Default constructor.
     */
    public ProfileCreditCard()
    {
        super();
    }
    /**
     * Constructor.
     */
    public ProfileCreditCard(RecordOwner screen)
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

    public static final String kProfileCreditCardFile = "ProfileCreditCard";
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(kProfileCreditCardFile, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "Credit Card";
    }
    /**
     * Get the Database Name.
     */
    public String getDatabaseName()
    {
        return "profile";
    }
    /**
     * Is this a local (vs remote) file?.
     */
    public int getDatabaseType()
    {
        return DBConstants.REMOTE | DBConstants.USER_DATA;
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
        if (iFieldSeq == kProfileID)
            field = new ProfileField(this, "ProfileID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kCCCode)
            field = new CardField(this, "CCCode", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kCCHolderName)
            field = new StringField(this, "CCHolderName", 65, null, null);
        if (iFieldSeq == kCCNumber)
            field = new PasswordField(this, "CCNumber", 20, null, null);
        if (iFieldSeq == kCCBeginDate)
            field = new DateField(this, "CCBeginDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kCCExpire)
            field = new DateField(this, "CCExpire", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kProfileCreditCardLastField)
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
        if (iKeyArea == kProfileIDKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "ProfileID");
            keyArea.addKeyField(kProfileID, DBConstants.ASCENDING);
        }
        if (iKeyArea == kCCNumberKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "CCNumber");
            keyArea.addKeyField(kCCNumber, DBConstants.ASCENDING);
        }
        if (keyArea == null) if (iKeyArea < kProfileCreditCardLastKey)
        {
            keyArea = super.setupKey(iKeyArea);     
            if (keyArea == null) if (iKeyArea < kProfileCreditCardLastKey)
                keyArea = new EmptyKey(this);
        }
        return keyArea;
    }

}

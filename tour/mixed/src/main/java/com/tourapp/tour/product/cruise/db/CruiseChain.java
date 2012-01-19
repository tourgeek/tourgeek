/**
 * @(#)CruiseChain.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.product.cruise.db;

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
import com.tourapp.tour.product.base.db.*;
import com.tourapp.model.tour.product.cruise.db.*;

/**
 *  CruiseChain - Cruise line chain.
 */
public class CruiseChain extends ProductChain
     implements CruiseChainModel
{
    private static final long serialVersionUID = 1L;

    //public static final int kID = kID;
    public static final int kCruiseChainLastField = kProductChainLastField;
    public static final int kCruiseChainFields = kProductChainLastField - DBConstants.MAIN_FIELD + 1;

    public static final int kIDKey = DBConstants.MAIN_KEY_FIELD;
    public static final int kCodeKey = kIDKey + 1;
    public static final int kDescriptionKey = kCodeKey + 1;
    public static final int kCruiseChainLastKey = kDescriptionKey;
    public static final int kCruiseChainKeys = kDescriptionKey - DBConstants.MAIN_KEY_FIELD + 1;
    /**
     * Default constructor.
     */
    public CruiseChain()
    {
        super();
    }
    /**
     * Constructor.
     */
    public CruiseChain(RecordOwner screen)
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

    public static final String kCruiseChainFile = "CruiseChain";
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(kCruiseChainFile, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the Database Name.
     */
    public String getDatabaseName()
    {
        return "product";
    }
    /**
     * Is this a local (vs remote) file?.
     */
    public int getDatabaseType()
    {
        return DBConstants.LOCAL | DBConstants.SHARED_DATA | DBConstants.LOCALIZABLE;
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
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kCruiseChainLastField)
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
        if (iKeyArea == kCodeKey)
        {
            keyArea = this.makeIndex(DBConstants.SECONDARY_KEY, "Code");
            keyArea.addKeyField(kCode, DBConstants.ASCENDING);
        }
        if (iKeyArea == kDescriptionKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "Description");
            keyArea.addKeyField(kDescription, DBConstants.ASCENDING);
        }
        if (keyArea == null) if (iKeyArea < kCruiseChainLastKey)
        {
            keyArea = super.setupKey(iKeyArea);     
            if (keyArea == null) if (iKeyArea < kCruiseChainLastKey)
                keyArea = new EmptyKey(this);
        }
        return keyArea;
    }

}

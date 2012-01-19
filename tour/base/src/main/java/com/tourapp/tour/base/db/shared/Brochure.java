/**
 * @(#)Brochure.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.base.db.shared;

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
import com.tourapp.model.tour.base.db.shared.*;

/**
 *  Brochure - Brochures.
 */
public class Brochure extends VirtualRecord
     implements BrochureModel
{
    private static final long serialVersionUID = 1L;

    //public static final int kID = kID;
    public static final int kDescription = kVirtualRecordLastField + 1;
    public static final int kStartDate = kDescription + 1;
    public static final int kEndDate = kStartDate + 1;
    public static final int kBrochureClassID = kEndDate + 1;
    public static final int kDiscontinued = kBrochureClassID + 1;
    public static final int kRequest = kDiscontinued + 1;
    public static final int kInventory = kRequest + 1;
    public static final int kLink = kInventory + 1;
    public static final int kBrochureLastField = kLink;
    public static final int kBrochureFields = kLink - DBConstants.MAIN_FIELD + 1;

    public static final int kIDKey = DBConstants.MAIN_KEY_FIELD;
    public static final int kDescriptionKey = kIDKey + 1;
    public static final int kBrochureClassIDKey = kDescriptionKey + 1;
    public static final int kBrochureLastKey = kBrochureClassIDKey;
    public static final int kBrochureKeys = kBrochureClassIDKey - DBConstants.MAIN_KEY_FIELD + 1;
    /**
     * Default constructor.
     */
    public Brochure()
    {
        super();
    }
    /**
     * Constructor.
     */
    public Brochure(RecordOwner screen)
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

    public static final String kBrochureFile = "Brochure";
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(kBrochureFile, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "Brochure";
    }
    /**
     * Get the Database Name.
     */
    public String getDatabaseName()
    {
        return "request";
    }
    /**
     * Is this a local (vs remote) file?.
     */
    public int getDatabaseType()
    {
        return DBConstants.LOCAL | DBConstants.USER_DATA;
    }
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        if (iFieldSeq == kID)
        {
            field = new CounterField(this, "ID", 4, null, null);
            field.setHidden(true);
        }
        if (iFieldSeq == kDescription)
            field = new StringField(this, "Description", 30, null, null);
        if (iFieldSeq == kStartDate)
            field = new DateField(this, "StartDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kEndDate)
            field = new DateField(this, "EndDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kBrochureClassID)
            field = new BrochureClassField(this, "BrochureClassID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kDiscontinued)
            field = new BooleanField(this, "Discontinued", 1, null, null);
        if (iFieldSeq == kRequest)
            field = new BooleanField(this, "Request", 1, null, new Boolean(true));
        if (iFieldSeq == kInventory)
            field = new IntegerField(this, "Inventory", 9, null, null);
        if (iFieldSeq == kLink)
            field = new URLField(this, "Link", 255, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kBrochureLastField)
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
        if (iKeyArea == kDescriptionKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "Description");
            keyArea.addKeyField(kDescription, DBConstants.ASCENDING);
        }
        if (iKeyArea == kBrochureClassIDKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "BrochureClassID");
            keyArea.addKeyField(kBrochureClassID, DBConstants.ASCENDING);
        }
        if (keyArea == null) if (iKeyArea < kBrochureLastKey)
        {
            keyArea = super.setupKey(iKeyArea);     
            if (keyArea == null) if (iKeyArea < kBrochureLastKey)
                keyArea = new EmptyKey(this);
        }
        return keyArea;
    }

}

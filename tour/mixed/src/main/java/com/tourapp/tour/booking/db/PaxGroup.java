/**
 * @(#)PaxGroup.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.booking.db;

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
import com.tourapp.model.tour.booking.db.*;

/**
 *  PaxGroup - Booking Pax Group.
 */
public class PaxGroup extends VirtualRecord
     implements PaxGroupModel
{
    private static final long serialVersionUID = 1L;

    //public static final int kID = kID;
    public static final int kBookingID = kVirtualRecordLastField + 1;
    public static final int kGroupNo = kBookingID + 1;
    public static final int kGroupDescription = kGroupNo + 1;
    public static final int kPaxID = kGroupDescription + 1;
    public static final int kPaxGroupLastField = kPaxID;
    public static final int kPaxGroupFields = kPaxID - DBConstants.MAIN_FIELD + 1;

    public static final int kIDKey = DBConstants.MAIN_KEY_FIELD;
    public static final int kBookingIDKey = kIDKey + 1;
    public static final int kPaxGroupLastKey = kBookingIDKey;
    public static final int kPaxGroupKeys = kBookingIDKey - DBConstants.MAIN_KEY_FIELD + 1;
    /**
     * Default constructor.
     */
    public PaxGroup()
    {
        super();
    }
    /**
     * Constructor.
     */
    public PaxGroup(RecordOwner screen)
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

    public static final String kPaxGroupFile = "PaxGroup";
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(kPaxGroupFile, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "Passenger group";
    }
    /**
     * Get the Database Name.
     */
    public String getDatabaseName()
    {
        return "booking";
    }
    /**
     * Is this a local (vs remote) file?.
     */
    public int getDatabaseType()
    {
        return DBConstants.LOCAL;
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
        if (iFieldSeq == kBookingID)
            field = new ReferenceField(this, "BookingID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kGroupNo)
            field = new ShortField(this, "GroupNo", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kGroupDescription)
            field = new StringField(this, "GroupDescription", 30, null, null);
        if (iFieldSeq == kPaxID)
            field = new ReferenceField(this, "PaxID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kPaxGroupLastField)
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
        if (iKeyArea == kBookingIDKey)
        {
            keyArea = this.makeIndex(DBConstants.UNIQUE, "BookingID");
            keyArea.addKeyField(kBookingID, DBConstants.ASCENDING);
            keyArea.addKeyField(kGroupNo, DBConstants.ASCENDING);
            keyArea.addKeyField(kPaxID, DBConstants.ASCENDING);
        }
        if (keyArea == null) if (iKeyArea < kPaxGroupLastKey)
        {
            keyArea = super.setupKey(iKeyArea);     
            if (keyArea == null) if (iKeyArea < kPaxGroupLastKey)
                keyArea = new EmptyKey(this);
        }
        return keyArea;
    }

}

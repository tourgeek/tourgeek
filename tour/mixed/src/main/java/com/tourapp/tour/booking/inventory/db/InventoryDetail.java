/**
 * @(#)InventoryDetail.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.booking.inventory.db;

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
import com.tourapp.tour.product.base.screen.*;
import com.tourapp.tour.booking.detail.db.*;
import com.tourapp.model.tour.booking.inventory.db.*;

/**
 *  InventoryDetail - The inventory detail record.
 */
public class InventoryDetail extends VirtualRecord
     implements InventoryDetailModel
{
    private static final long serialVersionUID = 1L;

    //public static final int kID = kID;
    public static final int kInventoryID = kVirtualRecordLastField + 1;
    public static final int kBookingDetailID = kInventoryID + 1;
    public static final int kType = kBookingDetailID + 1;
    public static final int kAmount = kType + 1;
    public static final int kInventoryDetailLastField = kAmount;
    public static final int kInventoryDetailFields = kAmount - DBConstants.MAIN_FIELD + 1;

    public static final int kIDKey = DBConstants.MAIN_KEY_FIELD;
    public static final int kInventoryIDKey = kIDKey + 1;
    public static final int kBookingDetailIDKey = kInventoryIDKey + 1;
    public static final int kInventoryDetailLastKey = kBookingDetailIDKey;
    public static final int kInventoryDetailKeys = kBookingDetailIDKey - DBConstants.MAIN_KEY_FIELD + 1;
    /**
     * Default constructor.
     */
    public InventoryDetail()
    {
        super();
    }
    /**
     * Constructor.
     */
    public InventoryDetail(RecordOwner screen)
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

    public static final String kInventoryDetailFile = "InventoryDetail";
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(kInventoryDetailFile, bAddQuotes) : super.getTableNames(bAddQuotes);
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
        return DBConstants.REMOTE | DBConstants.USER_DATA;
    }
    /**
     * MakeScreen Method.
     */
    public ScreenParent makeScreen(ScreenLoc itsLocation, ComponentParent parentScreen, int iDocMode, Map<String,Object> properties)
    {
        ScreenParent screen = null;
        if ((iDocMode & ScreenConstants.MAINT_MODE) == ScreenConstants.MAINT_MODE)
            screen = new InventoryDetailScreen(this, (ScreenLocation)itsLocation, (BasePanel)parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if ((iDocMode & ScreenConstants.DETAIL_MODE) == ScreenConstants.DETAIL_MODE)
        {
            if ((this.getEditMode() == DBConstants.EDIT_CURRENT) || (this.getEditMode() == DBConstants.EDIT_IN_PROGRESS))
            {
                Record recBookingDetail = new BookingDetail(Utility.getRecordOwner(this));
                recBookingDetail.getField(BookingDetail.kID).moveFieldToThis(this.getField(InventoryDetail.kBookingDetailID));
                try {
                    if (recBookingDetail.seek(null))
                    {
                        recBookingDetail = recBookingDetail.getTable().getCurrentTable().getRecord();
                        screen = recBookingDetail.makeScreen((ScreenLocation)itsLocation, (BasePanel)parentScreen, ScreenConstants.MAINT_MODE, properties);
                    }
                } catch (DBException ex) {
                    ex.printStackTrace();
                }
            }
        }
        else if ((iDocMode & ScreenConstants.DISPLAY_MODE) == ScreenConstants.DISPLAY_MODE)
            screen = new InventoryDetailGridScreen(this, (ScreenLocation)itsLocation, (BasePanel)parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else
            screen = super.makeScreen((ScreenLocation)itsLocation, (BasePanel)parentScreen, iDocMode, properties);
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
        if (iFieldSeq == kInventoryID)
        {
            field = new InventoryField(this, "InventoryID", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setNullable(false);
        }
        if (iFieldSeq == kBookingDetailID)
        {
            field = new BookingDetailField(this, "BookingDetailID", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setNullable(false);
        }
        if (iFieldSeq == kType)
        {
            field = new IntegerField(this, "Type", Constants.DEFAULT_FIELD_LENGTH, null, new Integer(0));
            field.setNullable(false);
        }
        if (iFieldSeq == kAmount)
            field = new IntegerField(this, "Amount", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kInventoryDetailLastField)
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
        if (iKeyArea == kInventoryIDKey)
        {
            keyArea = this.makeIndex(DBConstants.UNIQUE, "InventoryID");
            keyArea.addKeyField(kInventoryID, DBConstants.ASCENDING);
            keyArea.addKeyField(kBookingDetailID, DBConstants.ASCENDING);
            keyArea.addKeyField(kType, DBConstants.ASCENDING);
        }
        if (iKeyArea == kBookingDetailIDKey)
        {
            keyArea = this.makeIndex(DBConstants.UNIQUE, "BookingDetailID");
            keyArea.addKeyField(kBookingDetailID, DBConstants.ASCENDING);
            keyArea.addKeyField(kInventoryID, DBConstants.ASCENDING);
            keyArea.addKeyField(kType, DBConstants.ASCENDING);
        }
        if (keyArea == null) if (iKeyArea < kInventoryDetailLastKey)
        {
            keyArea = super.setupKey(iKeyArea);     
            if (keyArea == null) if (iKeyArea < kInventoryDetailLastKey)
                keyArea = new EmptyKey(this);
        }
        return keyArea;
    }

}

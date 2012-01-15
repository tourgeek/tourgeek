/**
 * @(#)HotelInventory.
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
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.base.db.shared.*;
import com.tourapp.tour.product.base.db.*;
import com.tourapp.tour.product.hotel.db.*;
import com.tourapp.model.tour.booking.inventory.db.*;

/**
 *  HotelInventory - Inventory file.
 */
public class HotelInventory extends Inventory
     implements HotelInventoryModel
{
    private static final long serialVersionUID = 1L;

    //public static final int kID = kID;
    //public static final int kProductTypeID = kProductTypeID;
    //public static final int kProductID = kProductID;
    //public static final int kRateID = kRateID;
    //public static final int kClassID = kClassID;
    //public static final int kOtherID = kOtherID;
    //public static final int kInvDate = kInvDate;
    //public static final int kBlocked = kBlocked;
    //public static final int kUsed = kUsed;
    //public static final int kAvailable = kAvailable;
    //public static final int kOversell = kOversell;
    //public static final int kClosed = kClosed;
    public static final int kHotelInventoryLastField = kInventoryLastField;
    public static final int kHotelInventoryFields = kInventoryLastField - DBConstants.MAIN_FIELD + 1;
    /**
     * Default constructor.
     */
    public HotelInventory()
    {
        super();
    }
    /**
     * Constructor.
     */
    public HotelInventory(RecordOwner screen)
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

    public static final String kHotelInventoryFile = "Inventory";
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(kHotelInventoryFile, bAddQuotes) : super.getTableNames(bAddQuotes);
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
        return DBConstants.REMOTE | DBConstants.SHARED_TABLE | DBConstants.USER_DATA;
    }
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        //if (iFieldSeq == kID)
        //{
        //  field = new CounterField(this, "ID", 10, null, null);
        //  field.setHidden(true);
        //}
        if (iFieldSeq == kProductTypeID)
        {
            field = new ProductTypeField(this, "ProductTypeID", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setNullable(false);
        }
        if (iFieldSeq == kProductID)
        {
            field = new HotelField(this, "ProductID", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setNullable(false);
        }
        if (iFieldSeq == kRateID)
        {
            field = new HotelRateSelect(this, "RateID", Constants.DEFAULT_FIELD_LENGTH, null, new Integer(Inventory.NO_RATE));
            field.setNullable(false);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kClassID)
        {
            field = new HotelClassSelect(this, "ClassID", Constants.DEFAULT_FIELD_LENGTH, null, new Integer(Inventory.NO_CLASS));
            field.setNullable(false);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kOtherID)
        {
            field = new PaxCategorySelect(this, "OtherID", Constants.DEFAULT_FIELD_LENGTH, null, new Integer(Inventory.NO_OTHER));
            field.setNullable(false);
            field.addListener(new InitOnceFieldHandler(null));
        }
        //if (iFieldSeq == kInvDate)
        //  field = new DateField(this, "InvDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kBlocked)
        //{
        //  field = new ShortField(this, "Blocked", 3, null, null);
        //  field.addListener(new InitOnceFieldHandler(null));
        //}
        //if (iFieldSeq == kUsed)
        //  field = new ShortField(this, "Used", 3, null, new Short((short)0));
        //if (iFieldSeq == kAvailable)
        //  field = new ShortField(this, "Available", 3, null, null);
        //if (iFieldSeq == kOversell)
        //  field = new ShortField(this, "Oversell", 3, null, null);
        //if (iFieldSeq == kClosed)
        //  field = new BooleanField(this, "Closed", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kHotelInventoryLastField)
                field = new EmptyField(this);
        }
        return field;
    }
    /**
     * Add all standard file & field behaviors.
     * Override this to add record listeners and filters.
     */
    public void addListeners()
    {
        super.addListeners();
        
        this.addListener(new SharedFileHandler(Inventory.kProductTypeID, ProductType.HOTEL_ID));
    }

}

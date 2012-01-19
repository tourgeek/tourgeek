/**
 * @(#)HotelAvail.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.util.test.hotel.db;

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
import com.tourapp.model.tour.util.test.hotel.db.*;

/**
 *  HotelAvail - Test hotel avail using Soap table type.
 */
public class HotelAvail extends VirtualRecord
     implements HotelAvailModel
{
    private static final long serialVersionUID = 1L;

    //public static final int kID = kID;
    public static final int kHotelCode = kVirtualRecordLastField + 1;
    public static final int kChainCode = kHotelCode + 1;
    public static final int kCurrencyCode = kChainCode + 1;
    public static final int kAmountBeforeTax = kCurrencyCode + 1;
    public static final int kAmountAfterTax = kAmountBeforeTax + 1;
    public static final int kHotelAvailLastField = kAmountAfterTax;
    public static final int kHotelAvailFields = kAmountAfterTax - DBConstants.MAIN_FIELD + 1;

    public static final int kIDKey = DBConstants.MAIN_KEY_FIELD;
    public static final int kHotelAvailLastKey = kIDKey;
    public static final int kHotelAvailKeys = kIDKey - DBConstants.MAIN_KEY_FIELD + 1;
    /**
     * Default constructor.
     */
    public HotelAvail()
    {
        super();
    }
    /**
     * Constructor.
     */
    public HotelAvail(RecordOwner screen)
    {
        this();
        this.init(screen);
    }
    /**
     * Initialization.
     */
    public void init(RecordOwner screen)
    {
        if ((this.getTableNames(false) == null) || (this.getTableNames(false).length() == 0))
            this.setTableNames("HotelAvail" + Double.toString(Math.random()));
        super.init(screen);
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
        return DBConstants.REMOTE_MEMORY;
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
        if (iFieldSeq == kHotelCode)
            field = new StringField(this, "HotelCode", 20, null, null);
        if (iFieldSeq == kChainCode)
            field = new StringField(this, "ChainCode", 30, null, null);
        if (iFieldSeq == kCurrencyCode)
            field = new StringField(this, "CurrencyCode", 3, null, null);
        if (iFieldSeq == kAmountBeforeTax)
            field = new CurrencyField(this, "AmountBeforeTax", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kAmountAfterTax)
            field = new CurrencyField(this, "AmountAfterTax", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kHotelAvailLastField)
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
        if (keyArea == null) if (iKeyArea < kHotelAvailLastKey)
        {
            keyArea = super.setupKey(iKeyArea);     
            if (keyArea == null) if (iKeyArea < kHotelAvailLastKey)
                keyArea = new EmptyKey(this);
        }
        return keyArea;
    }

}

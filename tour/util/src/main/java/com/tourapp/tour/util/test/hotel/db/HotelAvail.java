/**
  * @(#)HotelAvail.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
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
import org.jbundle.base.model.*;
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
        //if (iFieldSeq == 0)
        //{
        //  field = new CounterField(this, ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //  field.setHidden(true);
        //}
        //if (iFieldSeq == 1)
        //{
        //  field = new RecordChangedField(this, LAST_CHANGED, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //  field.setHidden(true);
        //}
        //if (iFieldSeq == 2)
        //{
        //  field = new BooleanField(this, DELETED, Constants.DEFAULT_FIELD_LENGTH, null, new Boolean(false));
        //  field.setHidden(true);
        //}
        if (iFieldSeq == 3)
            field = new StringField(this, HOTEL_CODE, 20, null, null);
        if (iFieldSeq == 4)
            field = new StringField(this, CHAIN_CODE, 30, null, null);
        if (iFieldSeq == 5)
            field = new StringField(this, CURRENCY_CODE, 3, null, null);
        if (iFieldSeq == 6)
            field = new CurrencyField(this, AMOUNT_BEFORE_TAX, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 7)
            field = new CurrencyField(this, AMOUNT_AFTER_TAX, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
            field = super.setupField(iFieldSeq);
        return field;
    }
    /**
     * Add this key area description to the Record.
     */
    public KeyArea setupKey(int iKeyArea)
    {
        KeyArea keyArea = null;
        if (iKeyArea == 0)
        {
            keyArea = this.makeIndex(DBConstants.UNIQUE, ID_KEY);
            keyArea.addKeyField(ID, DBConstants.ASCENDING);
        }
        if (keyArea == null)
            keyArea = super.setupKey(iKeyArea);     
        return keyArea;
    }

}

/**
 * @(#)TransportProduct.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.product.base.db;

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
import com.tourapp.tour.base.db.*;
import com.tourapp.model.tour.product.base.db.*;

/**
 *  TransportProduct - A travel product that takes you from one location to another..
 */
public class TransportProduct extends Product
     implements TransportProductModel
{
    private static final long serialVersionUID = 1L;

    //public static final int kID = kID;
    //public static final int kCityID = kCityID;
    public static final int kCityCode = kProductLastField + 1;
    public static final int kToCityID = kCityCode + 1;
    public static final int kToCityCode = kToCityID + 1;
    public static final int kTransportProductLastField = kToCityCode;
    public static final int kTransportProductFields = kToCityCode - DBConstants.MAIN_FIELD + 1;
    /**
     * Default constructor.
     */
    public TransportProduct()
    {
        super();
    }
    /**
     * Constructor.
     */
    public TransportProduct(RecordOwner screen)
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

    public static final String kTransportProductFile = "TransportProduct";
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(kTransportProductFile, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "product";
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
        return DBConstants.LOCAL | DBConstants.USER_DATA;
    }
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        //if (iFieldSeq == kID)
        //{
        //  field = new CounterField(this, "ID", 8, null, null);
        //  field.setHidden(true);
        //}
        //if (iFieldSeq == kCityID)
        //  field = new CityField(this, "CityID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kCityCode)
        {
            field = new StringField(this, "CityCode", 3, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kToCityID)
            field = new CityField(this, "ToCityID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kToCityCode)
        {
            field = new StringField(this, "ToCityCode", 3, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kTransportProductLastField)
                field = new EmptyField(this);
        }
        return field;
    }
    /**
     * Override this to add record listeners and filters to every screen where this is the main record.
     * @param screen The screen these listeners will be in.
     */
    public void addScreenListeners(RecordOwner screen)
    {
        super.addScreenListeners(screen);
        
        Record recCity = ((ReferenceField)this.getField(TransportProduct.kCityID)).getReferenceRecord();
        recCity.getField(City.kCityCode).addListener(new MainReadOnlyHandler(City.kCityCodeKey));
        this.getField(TransportProduct.kCityID).addListener(new MoveOnChangeHandler(this.getField(TransportProduct.kCityCode), recCity.getField(City.kCityCode)));
        this.getField(TransportProduct.kCityCode).addListener(new MoveOnChangeHandler(recCity.getField(City.kCityCode), this.getField(TransportProduct.kCityCode)));
        
        recCity = ((ReferenceField)this.getField(TransportProduct.kToCityID)).getReferenceRecord();
        recCity.getField(City.kCityCode).addListener(new MainReadOnlyHandler(City.kCityCodeKey));
        this.getField(TransportProduct.kToCityID).addListener(new MoveOnChangeHandler(this.getField(TransportProduct.kToCityCode), recCity.getField(City.kCityCode)));
        this.getField(TransportProduct.kToCityCode).addListener(new MoveOnChangeHandler(recCity.getField(City.kCityCode), this.getField(TransportProduct.kToCityCode)));
    }

}

/**
 * @(#)ProductControl.
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
import com.tourapp.tour.product.air.db.*;
import com.tourapp.tour.product.hotel.db.*;
import com.tourapp.tour.product.land.db.*;
import com.tourapp.tour.product.trans.db.*;
import com.tourapp.tour.product.car.db.*;
import com.tourapp.tour.product.cruise.db.*;
import com.tourapp.tour.product.item.db.*;
import com.tourapp.model.tour.product.base.db.*;

/**
 *  ProductControl - .
 */
public class ProductControl extends ControlRecord
     implements ProductControlModel
{
    private static final long serialVersionUID = 1L;

    //public static final int kID = kID;
    public static final int kAirRateID = kControlRecordLastField + 1;
    public static final int kAirClassID = kAirRateID + 1;
    public static final int kHotelRateID = kAirClassID + 1;
    public static final int kHotelClassID = kHotelRateID + 1;
    public static final int kLandRateID = kHotelClassID + 1;
    public static final int kLandClassID = kLandRateID + 1;
    public static final int kVariesOn = kLandClassID + 1;
    public static final int kPMCCutoff = kVariesOn + 1;
    public static final int kTransportationRateID = kPMCCutoff + 1;
    public static final int kTransportationClassID = kTransportationRateID + 1;
    public static final int kCarRateID = kTransportationClassID + 1;
    public static final int kCarClassID = kCarRateID + 1;
    public static final int kCruiseRateID = kCarClassID + 1;
    public static final int kCruiseClassID = kCruiseRateID + 1;
    public static final int kItemRateID = kCruiseClassID + 1;
    public static final int kItemClassID = kItemRateID + 1;
    public static final int kProductTermsID = kItemClassID + 1;
    public static final int kProductControlLastField = kProductTermsID;
    public static final int kProductControlFields = kProductTermsID - DBConstants.MAIN_FIELD + 1;

    public static final int kIDKey = DBConstants.MAIN_KEY_FIELD;
    public static final int kProductControlLastKey = kIDKey;
    public static final int kProductControlKeys = kIDKey - DBConstants.MAIN_KEY_FIELD + 1;
    /**
     * Default constructor.
     */
    public ProductControl()
    {
        super();
    }
    /**
     * Constructor.
     */
    public ProductControl(RecordOwner screen)
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

    public static final String kProductControlFile = "ProductControl";
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(kProductControlFile, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "Control";
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
        //  field = new CounterField(this, "ID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //  field.setHidden(true);
        //}
        if (iFieldSeq == kAirRateID)
            field = new AirRateSelect(this, "AirRateID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kAirClassID)
            field = new AirClassSelect(this, "AirClassID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kHotelRateID)
            field = new HotelRateSelect(this, "HotelRateID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kHotelClassID)
            field = new HotelClassSelect(this, "HotelClassID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kLandRateID)
            field = new LandRateSelect(this, "LandRateID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kLandClassID)
            field = new LandClassSelect(this, "LandClassID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kVariesOn)
            field = new LandVariesField(this, "VariesOn", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kPMCCutoff)
            field = new ShortField(this, "PMCCutoff", 2, null, null);
        if (iFieldSeq == kTransportationRateID)
            field = new TransportationRateSelect(this, "TransportationRateID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kTransportationClassID)
            field = new TransportationClassSelect(this, "TransportationClassID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kCarRateID)
            field = new CarRateSelect(this, "CarRateID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kCarClassID)
            field = new CarClassSelect(this, "CarClassID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kCruiseRateID)
            field = new CruiseRateSelect(this, "CruiseRateID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kCruiseClassID)
            field = new CruiseClassSelect(this, "CruiseClassID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kItemRateID)
            field = new ItemRateSelect(this, "ItemRateID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kItemClassID)
            field = new ItemClassSelect(this, "ItemClassID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kProductTermsID)
            field = new ProductTermsField(this, "ProductTermsID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kProductControlLastField)
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
        if (keyArea == null) if (iKeyArea < kProductControlLastKey)
        {
            keyArea = super.setupKey(iKeyArea);     
            if (keyArea == null) if (iKeyArea < kProductControlLastKey)
                keyArea = new EmptyKey(this);
        }
        return keyArea;
    }

}

/**
 * @(#)ProductField.
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
import com.tourapp.tour.booking.detail.db.*;

/**
 *  ProductField - Base product field.
 */
public class ProductField extends ReferenceField
{
    /**
     * Default constructor.
     */
    public ProductField()
    {
        super();
    }
    /**
     * Constructor.
     * @param record The parent record.
     * @param strName The field name.
     * @param iDataLength The maximum string length (pass -1 for default).
     * @param strDesc The string description (usually pass null, to use the resource file desc).
     * @param strDefault The default value (if object, this value is the default value, if string, the string is the default).
     */
    public ProductField(Record record, String strName, int iDataLength, String strDesc, Object strDefault)
    {
        this();
        this.init(record, strName, iDataLength, strDesc, strDefault);
    }
    /**
     * Initialize class fields.
     */
    public void init(Record record, String strName, int iDataLength, String strDesc, Object strDefault)
    {
        super.init(record, strName, iDataLength, strDesc, strDefault);
    }
    /**
     * Set up the default screen control for this field.
     * @param itsLocation Location of this component on screen (ie., GridBagConstraint).
     * @param targetScreen Where to place this component (ie., Parent screen or GridBagLayout).
     * @param converter The converter to set the screenfield to.
     * @param iDisplayFieldDesc Display the label? (optional).
     * @param properties Extra properties
     * @return Return the component or ScreenField that is created for this field.
     */
    public ScreenComponent setupDefaultView(ScreenLoc itsLocation, ComponentParent targetScreen, Convert converter, int iDisplayFieldDesc, Map<String, Object> properties)
    {
        Record record = this.makeReferenceRecord();
        if (record != null)
        {
            Converter fldProductDesc = record.getField(Product.kDescription);
            fldProductDesc = new FieldLengthConverter(fldProductDesc, 30);
            int iKeyArea = Product.kCodeKey;
            if (targetScreen instanceof GridScreen)
                iKeyArea = -1;
            return this.setupTableLookup(itsLocation, targetScreen, converter, iDisplayFieldDesc, record, iKeyArea, fldProductDesc, true, true);
        }
        else
            return super.setupDefaultView(itsLocation, targetScreen, converter, iDisplayFieldDesc, properties);
    }
    /**
     * Get (or make) the current record for this reference.
     */
    public Record makeReferenceRecord(RecordOwner recordOwner)
    {
        if (m_recordReference == null)
            if (this.getRecord() instanceof BookingDetail)
        {
            ProductTypeField fldProductType = (ProductTypeField)this.getRecord().getField(BookingDetail.kProductTypeID);
            ProductType recProductType = (ProductType)fldProductType.getReference();
            String strProductType = recProductType.getField(ProductType.kDescription).toString();
            if ("Tour".equalsIgnoreCase(strProductType))
                strProductType = "TourHeader";
            if (recProductType != null)
                return Product.getProductRecord(strProductType, recordOwner);
        }
        return super.makeReferenceRecord(recordOwner);  // Return current reference
    }

}

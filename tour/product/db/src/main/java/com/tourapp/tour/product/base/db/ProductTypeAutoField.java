/**
  * @(#)ProductTypeAutoField.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
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
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;

/**
 *  ProductTypeAutoField - Product type popup field.
 */
public class ProductTypeAutoField extends StringPopupField
{
    protected ProductType m_recProductType = null;
    /**
     * Default constructor.
     */
    public ProductTypeAutoField()
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
    public ProductTypeAutoField(Record record, String strName, int iDataLength, String strDesc, Object strDefault)
    {
        this();
        this.init(record, strName, iDataLength, strDesc, strDefault);
    }
    /**
     * Constructor.
     * @param record The parent record.
     * @param strName The field name.
     * @param iDataLength The maximum string length (pass -1 for default).
     * @param strDesc The string description (usually pass null, to use the resource file desc).
     * @param strDefault The default value (if object, this value is the default value, if string, the string is the default).
     */
    public void init(Record record, String strName, int iDataLength, String strDesc, Object strDefault)
    {
        super.init(record, strName, iDataLength, strDesc, strDefault);
        if (iDataLength == DBConstants.DEFAULT_FIELD_LENGTH)
            m_iMaxLength = 1;
    }
    /**
     * Free Method.
     */
    public void free()
    {
        m_recProductType = null;
        super.free();
    }
    /**
     * Initialize this to the actual product type code of this file
     * (if it isn't already set).
     */
    public int initField(boolean bDisplayOption)
    {
        int iErrorCode = super.initField(bDisplayOption);
        if (iErrorCode == DBConstants.NORMAL_RETURN)
            if (DBConstants.BLANK.equals(this.toString()))
                if (this.getRecord() instanceof Product)
        {
            this.getProductType();
            String strProductCode = m_recProductType.getField(ProductType.CODE).toString();
            if (strProductCode != null)
                if (strProductCode.length() > 0)
                    this.setString(strProductCode, bDisplayOption, DBConstants.INIT_MOVE);
        }
        return iErrorCode;
    }
    /**
     * GetProductType Method.
     */
    public ProductType getProductType()
    {
        Product recProduct = (Product)this.getRecord();
        if (m_recProductType == null)
        {
            RecordOwner recordOwner = recProduct.findRecordOwner();
            m_recProductType = new ProductType(recordOwner);
            if (recordOwner != null)
                recordOwner.removeRecord(m_recProductType);
            recProduct.addListener(new FreeOnFreeHandler(m_recProductType));
        }
        m_recProductType.getProductTypeID(recProduct);
        return m_recProductType;
    }
    /**
     * MakeReferenceRecord Method.
     */
    public void makeReferenceRecord()
    {
        // Not a reference: return new ProductType(recordOwner);
    }
    /**
     * Get the conversion Map.
     */
    public String[][] getPopupMap()
    {
        String string[][] = {
            {ProductType.TOUR_CODE, ProductType.TOUR}, 
            {ProductType.AIR_CODE, ProductType.AIR}, 
            {ProductType.HOTEL_CODE, ProductType.HOTEL}, 
            {ProductType.LAND_CODE, ProductType.LAND}, 
            {ProductType.TRANSPORTATION_CODE, ProductType.TRANSPORTATION}, 
            {ProductType.CAR_CODE, ProductType.CAR},
            {ProductType.CRUISE_CODE, ProductType.CRUISE},
            {ProductType.ITEM_CODE, ProductType.ITEM}, 
            {ProductType.UNKNOWN_CODE, ProductType.UNKNOWN}, 
        };
        return string;  // Never (hopefully)
    }

}

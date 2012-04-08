/**
 * @(#)ProductMerge.
 * Copyright © 2012 tourapp.com. All rights reserved.
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
import com.tourapp.tour.base.db.*;
import com.tourapp.tour.acctpay.db.*;
import com.tourapp.tour.product.hotel.db.*;
import com.tourapp.tour.product.land.db.*;
import com.tourapp.model.tour.product.tour.db.*;
import com.tourapp.model.tour.product.base.db.*;

/**
 *  ProductMerge - Source for Product Tree.
 */
public class ProductMerge extends QueryRecord
     implements ProductMergeModel
{
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor.
     */
    public ProductMerge()
    {
        super();
    }
    /**
     * Constructor.
     */
    public ProductMerge(RecordOwner screen)
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
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(PRODUCT_MERGE_FILE, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "Product";
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
        return DBConstants.LOCAL;
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
        if (field == null)
            field = super.setupField(iFieldSeq);
        return field;
    }
    /**
     * Override this to Setup all the records for this query.
     * Only used for querys and abstract-record queries.
     * Actually adds records not tables, but the records aren't physically
     * added here, the record's tables are added to my table.
     * @param The recordOwner to pass to the records that are added.
     * @see addTable.
     */
    public void addTables(RecordOwner recordOwner)
    {
        Product pProduct = new Product(recordOwner);
        
        //x   Record pQueryCore = (Record)this.getRecord(kLandFile);
        // Move this to AddFields?!?
        BaseField pField = new StringField(pProduct, "Type", 20, null, null);
        pField.setVirtual(true);
        pField = new StringField(pProduct, "Product", 20, null, null);
        pField.setVirtual(true);
        
        this.addTable(pProduct);
        
        City pTkAirport = new City(recordOwner);                      
        this.addTable(pTkAirport);
        
        Country pTkCountry = new Country(recordOwner);                      
        this.addTable(pTkCountry);
        
        Vendor pApVendor = new Vendor(recordOwner);
        this.addTable(pApVendor);
        // The following files are not included in the query, but they are returned in the GetBaseRecord query
        Land pBkLand = new Land(recordOwner);
        this.addTable(pBkLand);
        
        Hotel pBkHotel = new Hotel(recordOwner);
        this.addTable(pBkHotel);
        
        Record tourHdr = Record.makeRecordFromClassName(TourHeaderModel.THICK_CLASS, recordOwner);
        this.addTable(tourHdr);
    }
    /**
     * Get the actual record to add/edit/etc
     * Usually used in queryrecords.
     * @return The physical record.
     */
    public Record getBaseRecord()
    {
        String strProduct;
        Record pQueryTable = null;
        BaseField pField = this.getField("Product");
        if (pField != null)
        {
            strProduct = pField.getString();
            if (strProduct.equalsIgnoreCase("Tour"))
                strProduct = "TourHeader";
            pQueryTable = (Record)this.getRecord(strProduct);
            if (pQueryTable != null)
            {   // Make sure I can read the current record from the right file
                pQueryTable.getField(Product.ID).moveFieldToThis(this.getField(Product.ID), DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
            }
        }
        return pQueryTable;
    }
    /**
     * This causes the SQL statements to include only the field name and not record.field.
     */
    public boolean isQueryRecord()
    {
        return false;
    }
    /**
     * SelectFields Method.
     */
    public void selectFields()
    {
        Product pProduct = (Product)this.getRecord(Product.PRODUCT_FILE);
        this.setSelected(false);
        super.selectFields();
        
        pProduct.getField(Product.ID).setSelected(true);
        pProduct.getField(Product.DESCRIPTION).setSelected(true);
        pProduct.getField(Product.CITY_ID).setSelected(true);
        this.getField(City.CITY_FILE, City.TICKET_CITY_DESC).setSelected(true);
        this.getField(City.CITY_FILE, City.COUNTRY_ID).setSelected(true);
        
        this.getField(Country.COUNTRY_FILE, Country.DESCRIPTION).setSelected(true);
        
        pProduct.getField("Type").setSelected(true);
        
        pProduct.getField(Vendor.ID).setSelected(true);
        this.getField(Vendor.VENDOR_FILE, Vendor.VENDOR_NAME).setSelected(true);
        
        pProduct.getField(Product.OPERATORS_CODE).setSelected(true);
        pProduct.getField(Product.CODE).setSelected(true);
        
        pProduct.getField("Product").setSelected(true);
        
        pProduct.getField(Product.DESC_SORT).setSelected(true);
        this.getField(Vendor.VENDOR_FILE, Vendor.NAME_SORT).setSelected(true);
        this.getField(City.CITY_FILE, City.DESCRIPTION).setSelected(true);
    }
    /**
     * SetupRelationships Method.
     */
    public void setupRelationships()
    {
        this.addRelationship(DBConstants.LEFT_OUTER, this.getRecord(Product.PRODUCT_FILE), this.getRecord(City.CITY_FILE), Product.CITY_ID, City.ID);
        this.addRelationship(DBConstants.LEFT_OUTER, this.getRecord(City.CITY_FILE), this.getRecord(Country.COUNTRY_FILE), City.COUNTRY_ID, Country.ID);
        this.addRelationship(DBConstants.LEFT_OUTER,  this.getRecord(Product.PRODUCT_FILE), this.getRecord(Vendor.VENDOR_FILE), Product.VENDOR_ID, Vendor.ID);
    }

}

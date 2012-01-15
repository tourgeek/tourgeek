/**
 * @(#)Hotel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.thin.tour.product.hotel.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

import com.tourapp.thin.tour.product.base.db.*;
import com.tourapp.model.tour.product.hotel.db.*;

public class Hotel extends Product
    implements HotelModel
{

    public Hotel()
    {
        super();
    }
    public Hotel(Object recordOwner)
    {
        this();
        this.init(recordOwner);
    }
    public static final String HOTEL_FILE = "Hotel";
    /**
     *  Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Hotel.HOTEL_FILE : super.getTableNames(bAddQuotes);
    }
    /**
     *  Get the Database Name.
     */
    public String getDatabaseName()
    {
        return "product";
    }
    /**
     *  Is this a local (vs remote) file?.
     */
    public int getDatabaseType()
    {
        return Constants.LOCAL | Constants.USER_DATA;
    }
    /**
    * Set up the screen input fields.
    */
    public void setupFields()
    {
        FieldInfo field = null;
        field = new FieldInfo(this, "ID", 8, null, null);
        field.setDataClass(Integer.class);
        field.setHidden(true);
        field = new FieldInfo(this, "LastChanged", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Date.class);
        field.setHidden(true);
        field = new FieldInfo(this, "Deleted", 10, null, new Boolean(false));
        field.setDataClass(Boolean.class);
        field.setHidden(true);
        field = new FieldInfo(this, "Description", 50, null, null);
        field = new FieldInfo(this, "Code", 10, null, null);
        field = new FieldInfo(this, "VendorID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "OperatorsCode", 20, null, null);
        field = new FieldInfo(this, "ProductChainID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "CityID", 3, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "Etd", 10, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.TIME_ONLY);
        field = new FieldInfo(this, "AckDays", 2, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, "Comments", 32767, null, null);
        field.setDataClass(Object.class);
        field = new FieldInfo(this, "Properties", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Object.class);
        field = new FieldInfo(this, "ItineraryDesc", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Object.class);
        field = new FieldInfo(this, "DescSort", 10, null, null);
        //field = new FieldInfo(this, "ProductType", 15, null, null);
        //field = new FieldInfo(this, "ProductCost", 18, null, null);
        //field.setDataClass(Double.class);
        //field = new FieldInfo(this, "ProductCostLocal", 18, null, null);
        //field.setDataClass(Double.class);
        field = new FieldInfo(this, "ProductMessageTransportID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        //field = new FieldInfo(this, "DisplayInventoryStatusID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //field.setDataClass(Integer.class);
        //field = new FieldInfo(this, "InventoryAvailability", 5, null, null);
        //field.setDataClass(Short.class);
        //field = new FieldInfo(this, "CurrencyCode", 3, null, null);
        //field = new FieldInfo(this, "CurrencyCodeLocal", 3, null, null);
        //field = new FieldInfo(this, "VendorName", 30, null, null);
        //field = new FieldInfo(this, "DisplayCostStatusID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //field.setDataClass(Integer.class);
        //field = new FieldInfo(this, "PPCost", 18, null, null);
        //field.setDataClass(Double.class);
        //field = new FieldInfo(this, "PPCostLocal", 18, null, null);
        //field.setDataClass(Double.class);
        //field = new FieldInfo(this, "RateID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //field.setDataClass(Integer.class);
        //field = new FieldInfo(this, "ClassID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //field.setDataClass(Integer.class);
        //field = new FieldInfo(this, "ProductPriceLocal", 18, null, null);
        //field.setDataClass(Double.class);
        //field = new FieldInfo(this, "PPPriceLocal", 18, null, null);
        //field.setDataClass(Double.class);
        field = new FieldInfo(this, "CheckOut", 10, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.TIME_ONLY);
        field = new FieldInfo(this, "SameAsVendor", 1, null, null);
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, "Contact", 30, null, null);
        field = new FieldInfo(this, "ContactTitle", 30, null, null);
        field = new FieldInfo(this, "AddressLine1", 40, null, null);
        field = new FieldInfo(this, "AddressLine2", 40, null, null);
        field = new FieldInfo(this, "CityOrTown", 15, null, null);
        field = new FieldInfo(this, "StateOrRegion", 15, null, null);
        field = new FieldInfo(this, "PostalCode", 10, null, null);
        field = new FieldInfo(this, "Country", 15, null, null);
        field = new FieldInfo(this, "Tel", 20, null, null);
        field = new FieldInfo(this, "Fax", 20, null, null);
        field = new FieldInfo(this, "Email", 40, null, null);
        field = new FieldInfo(this, "Rooms", 4, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, "GeneralManager", 20, null, null);
        field = new FieldInfo(this, "SalesManager", 20, null, null);
        field = new FieldInfo(this, "LocalContact", 20, null, null);
        field = new FieldInfo(this, "LocalPhone", 20, null, null);
        field = new FieldInfo(this, "TollFreePhone", 20, null, null);
        field = new FieldInfo(this, "AltPhone", 20, null, null);
        field = new FieldInfo(this, "OneFree", 2, null, new Short((short)15));
        field.setDataClass(Short.class);
        field = new FieldInfo(this, "FreeType", 1, null, "S");
        field = new FieldInfo(this, "ChildAge", 2, null, null);
        field.setDataClass(Short.class);
        //field = new FieldInfo(this, "SingleCost", 18, null, null);
        //field.setDataClass(Double.class);
        //field = new FieldInfo(this, "DoubleCost", 18, null, null);
        //field.setDataClass(Double.class);
        //field = new FieldInfo(this, "TripleCost", 18, null, null);
        //field.setDataClass(Double.class);
        //field = new FieldInfo(this, "QuadCost", 18, null, null);
        //field.setDataClass(Double.class);
        //field = new FieldInfo(this, "RoomCost", 18, null, null);
        //field.setDataClass(Double.class);
        //field = new FieldInfo(this, "MealCost", 18, null, null);
        //field.setDataClass(Double.class);
        //field = new FieldInfo(this, "SingleCostLocal", 18, null, null);
        //field.setDataClass(Double.class);
        //field = new FieldInfo(this, "DoubleCostLocal", 18, null, null);
        //field.setDataClass(Double.class);
        //field = new FieldInfo(this, "TripleCostLocal", 18, null, null);
        //field.setDataClass(Double.class);
        //field = new FieldInfo(this, "QuadCostLocal", 18, null, null);
        //field.setDataClass(Double.class);
        //field = new FieldInfo(this, "RoomCostLocal", 18, null, null);
        //field.setDataClass(Double.class);
        //field = new FieldInfo(this, "MealCostLocal", 18, null, null);
        //field.setDataClass(Double.class);
        //field = new FieldInfo(this, "SinglePriceLocal", 18, null, null);
        //field.setDataClass(Double.class);
        //field = new FieldInfo(this, "DoublePriceLocal", 18, null, null);
        //field.setDataClass(Double.class);
        //field = new FieldInfo(this, "TriplePriceLocal", 18, null, null);
        //field.setDataClass(Double.class);
        //field = new FieldInfo(this, "QuadPriceLocal", 18, null, null);
        //field.setDataClass(Double.class);
        //field = new FieldInfo(this, "RoomPriceLocal", 18, null, null);
        //field.setDataClass(Double.class);
        //field = new FieldInfo(this, "MealPriceLocal", 18, null, null);
        //field.setDataClass(Double.class);
    }
    /**
    * Set up the key areas.
    */
    public void setupKeys()
    {
        KeyAreaInfo keyArea = null;
        keyArea = new KeyAreaInfo(this, Constants.UNIQUE, "PrimaryKey");
        keyArea.addKeyField("ID", Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.SECONDARY_KEY, "Code");
        keyArea.addKeyField("Code", Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, "DescSort");
        keyArea.addKeyField("DescSort", Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, "VendorID");
        keyArea.addKeyField("VendorID", Constants.ASCENDING);
        keyArea.addKeyField("DescSort", Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, "CityID");
        keyArea.addKeyField("CityID", Constants.ASCENDING);
        keyArea.addKeyField("DescSort", Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, "OperatorsCode");
        keyArea.addKeyField("OperatorsCode", Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, "ProductChainID");
        keyArea.addKeyField("ProductChainID", Constants.ASCENDING);
        keyArea.addKeyField("DescSort", Constants.ASCENDING);
    }

}

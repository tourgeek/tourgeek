/**
 *  @(#)ProductScreenRecord.
 *  Copyright © 2010 tourapp.com. All rights reserved.
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
import com.tourapp.tour.booking.db.*;
import org.jbundle.thin.base.message.*;
import com.tourapp.tour.product.land.db.*;
import com.tourapp.tour.booking.detail.db.*;
import com.tourapp.tour.message.base.request.*;
import com.tourapp.tour.message.base.request.data.*;
import com.tourapp.tour.base.db.*;
import com.tourapp.tour.acctpay.db.*;
import com.tourapp.tour.product.base.search.db.*;

/**
 *  ProductScreenRecord - Screen Record for Hotel search variables.
 */
public class ProductScreenRecord extends ScreenRecord
{
    private static final long serialVersionUID = 1L;

    public static final int kProductID = kScreenRecordLastField + 1;
    public static final int kStartDate = kProductID + 1;
    public static final int kEndDate = kStartDate + 1;
    public static final int kDescription = kEndDate + 1;
    public static final int kCityID = kDescription + 1;
    public static final int kToCityID = kCityID + 1;
    public static final int kContinentID = kToCityID + 1;
    public static final int kRegionID = kContinentID + 1;
    public static final int kCountryID = kRegionID + 1;
    public static final int kStateID = kCountryID + 1;
    public static final int kVendorID = kStateID + 1;
    public static final int kRateID = kVendorID + 1;
    public static final int kClassID = kRateID + 1;
    public static final int kDetailDate = kClassID + 1;
    public static final int kPax = kDetailDate + 1;
    public static final int kLastChanged = kPax + 1;
    public static final int kRemoteQueryEnabled = kLastChanged + 1;
    public static final int kBlocked = kRemoteQueryEnabled + 1;
    public static final int kOversell = kBlocked + 1;
    public static final int kClosed = kOversell + 1;
    public static final int kDelete = kClosed + 1;
    public static final int kReadOnly = kDelete + 1;
    public static final int kProductSearchTypeID = kReadOnly + 1;
    public static final int kProductTypeID = kProductSearchTypeID + 1;
    public static final int kProductScreenRecordLastField = kProductTypeID;
    public static final int kProductScreenRecordFields = kProductTypeID - DBConstants.MAIN_FIELD + 1;
    /**
     * Default constructor.
     */
    public ProductScreenRecord()
    {
        super();
    }
    /**
     * Constructor.
     */
    public ProductScreenRecord(RecordOwner screen)
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

    public static final String kProductScreenRecordFile = null;   // Screen field
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        if (iFieldSeq == kProductID)
            field = new ProductField(this, "ProductID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kStartDate)
            field = new DateField(this, "StartDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kEndDate)
            field = new DateField(this, "EndDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kDescription)
            field = new StringField(this, "Description", 10, null, null);
        if (iFieldSeq == kCityID)
            field = new CityField(this, "CityID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kToCityID)
            field = new CityField(this, "ToCityID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kContinentID)
            field = new ContinentField(this, "ContinentID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kRegionID)
            field = new RegionField(this, "RegionID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kCountryID)
            field = new CountryField(this, "CountryID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kStateID)
            field = new StateField(this, "StateID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kVendorID)
            field = new VendorField(this, "VendorID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kRateID)
            field = new BaseRateField(this, "RateID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kClassID)
            field = new BaseClassField(this, "ClassID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kDetailDate)
            field = new DateField(this, "DetailDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kPax)
            field = new ShortField(this, "Pax", Constants.DEFAULT_FIELD_LENGTH, null, new Short((short)2));
        if (iFieldSeq == kLastChanged)
            field = new ProductScreenRecord_LastChanged(this, "LastChanged", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kRemoteQueryEnabled)
            field = new BooleanField(this, "RemoteQueryEnabled", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kBlocked)
            field = new ShortField(this, "Blocked", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kOversell)
            field = new ShortField(this, "Oversell", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kClosed)
            field = new BooleanField(this, "Closed", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kDelete)
            field = new BooleanField(this, "Delete", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kReadOnly)
            field = new BooleanField(this, "ReadOnly", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kProductSearchTypeID)
            field = new ProductSearchTypeField(this, "ProductSearchTypeID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kProductTypeID)
            field = new ProductTypeField(this, "ProductTypeID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kProductScreenRecordLastField)
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
        this.getField(ProductScreenRecord.kDescription).addListener(new FieldToUpperHandler(null));
        this.getField(ProductScreenRecord.kDescription).addListener(new CheckForTheHandler(null));
        this.addListener(new DateChangedHandler(ProductScreenRecord.kLastChanged));
    }
    /**
     * Set up the properties for a price lookup.
     */
    public void setPriceProperties(BaseMessage message, Record recProduct)
    {
        ProductRequest productRequest = (ProductRequest)message.getMessageDataDesc(null);
        ProductMessageData productMessageData = (ProductMessageData)productRequest.getMessageDataDesc(ProductRequest.PRODUCT_MESSAGE);
        PassengerMessageData passengerMessageData = (PassengerMessageData)productRequest.getMessageDataDesc(ProductRequest.PASSENGER_MESSAGE);
        
        this.addMapProperty(productMessageData, BookingDetail.DETAIL_DATE, this, ProductScreenRecord.kDetailDate);
        this.addMapProperty(productMessageData, BookingDetail.PRODUCT_ID, recProduct, Product.kID);
        this.addMapProperty(passengerMessageData, Product.PAX_PARAM, this, ProductScreenRecord.kPax);
        if (this.getField(ProductScreenRecord.kPax).getValue() == 2)  // Default
            passengerMessageData.put(Product.ROOM_TYPE_PARAM + Integer.toString(PaxCategory.DOUBLE_ID), new Short((short)2));   // Two pax in a double room
        
        try   {
            productRequest.put(DBConstants.STRING_OBJECT_ID_HANDLE, recProduct.getHandle(DBConstants.OBJECT_ID_HANDLE));
        } catch (DBException ex)    {
            ex.printStackTrace();
        }
        // Land only
        productMessageData.put(Land.SIC_PMC_PARAM, LandClass.PRIVATE_VEHICLE_CODE);
        // Others
        productRequest.put(DBParams.RECORD, recProduct.getTableNames(false));
        productRequest.put(DBParams.TIMESTAMP, Double.toString(this.getField(ProductScreenRecord.kLastChanged).getValue()));
        this.addMapProperty(productMessageData, BookingDetail.RATE_ID, this, ProductScreenRecord.kRateID);
        this.addMapProperty(productMessageData, BookingDetail.CLASS_ID, this, ProductScreenRecord.kClassID);
    }
    /**
     * Is this the correct message for this screen.
     * (Double-check to make sure the user still wants this price information)
     * @return True if the properties are correct.
     */
    public boolean checkPriceProperties(BaseMessage map, Record recProduct)
    {
        Object objData = map.get(DBParams.TIMESTAMP);
        Object objLast = Double.toString(this.getField(ProductScreenRecord.kLastChanged).getValue());
        if (objData != null)
            if (objData.equals(objLast))
                return true;    // Same
        return false;
    }
    /**
     * AddMapProperty Method.
     */
    public void addMapProperty(MessageRecordDesc map, String strKey, Record record, int iFieldSeq)
    {
        if (record.getField(iFieldSeq).getData() != null)
            map.put(strKey, record.getField(iFieldSeq).getData());
    }

}

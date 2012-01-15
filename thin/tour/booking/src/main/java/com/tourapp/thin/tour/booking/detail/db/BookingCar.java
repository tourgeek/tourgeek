/**
 * @(#)BookingCar.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.thin.tour.booking.detail.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

import com.tourapp.thin.tour.booking.detail.db.*;
import com.tourapp.model.tour.booking.detail.db.*;

public class BookingCar extends BookingDetail
    implements BookingCarModel
{

    public BookingCar()
    {
        super();
    }
    public BookingCar(Object recordOwner)
    {
        this();
        this.init(recordOwner);
    }
    public static final String BOOKING_CAR_FILE = "BookingDetail";
    /**
     *  Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? BookingCar.BOOKING_CAR_FILE : super.getTableNames(bAddQuotes);
    }
    /**
     *  Get the Database Name.
     */
    public String getDatabaseName()
    {
        return "booking";
    }
    /**
     *  Is this a local (vs remote) file?.
     */
    public int getDatabaseType()
    {
        return Constants.REMOTE | Constants.SHARED_TABLE | Constants.USER_DATA;
    }
    /**
    * Set up the screen input fields.
    */
    public void setupFields()
    {
        FieldInfo field = null;
        field = new FieldInfo(this, "ID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field.setHidden(true);
        field = new FieldInfo(this, "LastChanged", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Date.class);
        field.setHidden(true);
        field = new FieldInfo(this, "Deleted", 10, null, new Boolean(false));
        field.setDataClass(Boolean.class);
        field.setHidden(true);
        field = new FieldInfo(this, "BookingID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "BookingPaxID", Constants.DEFAULT_FIELD_LENGTH, null, new Integer(0));
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "ModuleID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "TourHeaderDetailID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "TourHeaderOptionID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "ModuleStartDate", 25, null, null);
        field.setDataClass(Date.class);
        field = new FieldInfo(this, "Description", 60, null, null);
        //field = new FieldInfo(this, "ProductType", 15, null, null);
        field = new FieldInfo(this, "RemoteReferenceNo", 60, null, null);
        field = new FieldInfo(this, "ProductTypeID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "DetailDate", 25, null, null);
        field.setDataClass(Date.class);
        field = new FieldInfo(this, "GMTDate", 25, null, null);
        field.setDataClass(Date.class);
        field = new FieldInfo(this, "GMTOffset", 18, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, "ProductID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "RateID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "ClassID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "MarkupFromLast", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, "VendorID", 8, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "TourID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "ApTrxID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "PricingDetailKey", 128, null, null);
        field = new FieldInfo(this, "TotalCost", 18, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, "Exchange", 10, null, null);
        field.setDataClass(Double.class);
        field.setScale(-1);
        //field = new FieldInfo(this, "CurrencyCode", 3, null, null);
        field = new FieldInfo(this, "TotalCostLocal", 18, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, "TotalPriceLocal", 18, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, "InfoMessageTransportID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "InfoStatusID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "InfoRequestKey", 128, null, null);
        //field = new FieldInfo(this, "InfoStatusRequest", 10, null, null);
        //field.setDataClass(Boolean.class);
        field = new FieldInfo(this, "CostMessageTransportID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "CostStatusID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "CostRequestKey", 128, null, null);
        //field = new FieldInfo(this, "CostStatusRequest", 10, null, null);
        //field.setDataClass(Boolean.class);
        field = new FieldInfo(this, "InventoryMessageTransportID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "InventoryStatusID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "InventoryRequestKey", 128, null, null);
        //field = new FieldInfo(this, "InventoryStatusRequest", 10, null, null);
        //field.setDataClass(Boolean.class);
        field = new FieldInfo(this, "ProductMessageTransportID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "ProductStatusID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, "ProductRequestKey", 128, null, null);
        //field = new FieldInfo(this, "ProductStatusRequest", 10, null, null);
        //field.setDataClass(Boolean.class);
        field = new FieldInfo(this, "RemoteBookingNo", 127, null, null);
        field = new FieldInfo(this, "AckDays", 4, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, "DetailEndDate", 25, null, null);
        field.setDataClass(Date.class);
        field = new FieldInfo(this, "GMTEndDate", 25, null, null);
        field.setDataClass(Date.class);
        //field = new FieldInfo(this, "MealSummary", 255, null, null);
        //field = new FieldInfo(this, "StatusSummary", 20, null, null);
        //field.setDataClass(Integer.class);
        field = new FieldInfo(this, "ItineraryDesc", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Object.class);
        field = new FieldInfo(this, "Properties", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Object.class);
        field = new FieldInfo(this, "ErrorProperties", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Object.class);
        field = new FieldInfo(this, "PPCost", 18, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, "PPPriceLocal", 18, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, "Nights", 2, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, "MealPlan1ID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Object.class);
        field = new FieldInfo(this, "MealPlan1Qty", 2, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, "MealPlan1Days", 9, null, null);
        field.setDataClass(Object.class);
        field = new FieldInfo(this, "MealPlan2ID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Object.class);
        field = new FieldInfo(this, "MealPlan2Qty", 2, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, "MealPlan2Days", 9, null, null);
        field.setDataClass(Object.class);
        field = new FieldInfo(this, "MealPlan3ID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Object.class);
        field = new FieldInfo(this, "MealPlan3Qty", 2, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, "MealPlan3Days", 9, null, null);
        field.setDataClass(Object.class);
        field = new FieldInfo(this, "MealPlan4ID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Object.class);
        field = new FieldInfo(this, "MealPlan4Qty", 2, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, "MealPlan4Days", 9, null, null);
        field.setDataClass(Object.class);
        //field = new FieldInfo(this, "SinglePax", 4, null, null);
        //field.setDataClass(Short.class);
        //field = new FieldInfo(this, "DoublePax", 2, null, null);
        //field.setDataClass(Short.class);
        //field = new FieldInfo(this, "TriplePax", 2, null, null);
        //field.setDataClass(Short.class);
        //field = new FieldInfo(this, "QuadPax", 2, null, null);
        //field.setDataClass(Short.class);
        field = new FieldInfo(this, "Children", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, "SingleCost", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Object.class);
        field = new FieldInfo(this, "DoubleCost", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Object.class);
        field = new FieldInfo(this, "TripleCost", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Object.class);
        field = new FieldInfo(this, "QuadCost", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Object.class);
        field = new FieldInfo(this, "ChildrenCost", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Object.class);
        field = new FieldInfo(this, "RoomCost", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Object.class);
        field = new FieldInfo(this, "MealCost", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Object.class);
        //field = new FieldInfo(this, "RoomCostLocal", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //field.setDataClass(Double.class);
        //field = new FieldInfo(this, "MealCostLocal", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //field.setDataClass(Double.class);
        field = new FieldInfo(this, "VariesCode", 1, null, null);
        field = new FieldInfo(this, "VariesQty", 2, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, "VariesCost", 9, null, null);
        field.setDataClass(Object.class);
        field = new FieldInfo(this, "PMCCutoff", 3, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, "PMCCost", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Object.class);
        field = new FieldInfo(this, "SICCost", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Object.class);
        field = new FieldInfo(this, "BookingAirHeaderID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Object.class);
        field = new FieldInfo(this, "GMTTime", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Date.class);
        field = new FieldInfo(this, "Etd", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Date.class);
        field = new FieldInfo(this, "XO", 1, null, null);
        field = new FieldInfo(this, "CityCode", 3, null, null);
        field = new FieldInfo(this, "CityDesc", 17, null, null);
        field = new FieldInfo(this, "AirlineID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Object.class);
        field = new FieldInfo(this, "Carrier", 2, null, null);
        field = new FieldInfo(this, "FlightNo", 4, null, null);
        field = new FieldInfo(this, "FlightClass", 1, null, null);
        field = new FieldInfo(this, "ToCityCode", 3, null, null);
        field = new FieldInfo(this, "ToCityDesc", 17, null, null);
        field = new FieldInfo(this, "ArriveTime", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Date.class);
        field = new FieldInfo(this, "AddDays", 2, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, "ARCStatus", 2, null, null);
        field = new FieldInfo(this, "FareBasis", 15, null, null);
        field = new FieldInfo(this, "StartDate", 5, null, null);
        field.setDataClass(Date.class);
        field = new FieldInfo(this, "EndDate", 5, null, null);
        field.setDataClass(Date.class);
        field = new FieldInfo(this, "Allow", 3, null, null);
        field = new FieldInfo(this, "DetFare", 10, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, "SegmentConfNo", 128, null, null);
        field = new FieldInfo(this, "SegmentConfirmedBy", 50, null, null);
        field = new FieldInfo(this, "Coupon", 1, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, "Seat", 5, null, null);
        field = new FieldInfo(this, "Gate", 5, null, null);
        field = new FieldInfo(this, "SeatPref", 1, null, null);
        field = new FieldInfo(this, "Smoking", 1, null, null);
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, "Meals", 2, null, null);
        field = new FieldInfo(this, "Days", 8, null, new Float(1));
        field.setDataClass(Float.class);
        field = new FieldInfo(this, "Quantity", 5, null, new Short((short)1));
        field.setDataClass(Short.class);
        field = new FieldInfo(this, "AskForAnswer", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Boolean.class);
        //field = new FieldInfo(this, "AlwaysResolve", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //field.setDataClass(Boolean.class);
        field = new FieldInfo(this, "PricingTypeID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Object.class);
    }
    /**
    * Set up the key areas.
    */
    public void setupKeys()
    {
        KeyAreaInfo keyArea = null;
        super.setupKeys();
    }

}

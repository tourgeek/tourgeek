/**
 * @(#)TourHeaderDetail.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.product.tour.detail.db;

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
import com.tourapp.tour.product.base.db.*;
import com.tourapp.tour.product.tour.db.*;
import org.jbundle.main.msg.db.*;
import com.tourapp.tour.base.db.*;
import com.tourapp.tour.product.air.db.*;

/**
 *  TourHeaderDetail - Tour Header detail.
 */
public class TourHeaderDetail extends TourSub
{
    private static final long serialVersionUID = 1L;

    //public static final int kID = kID;
    //public static final int kTourHeaderOptionID = kTourHeaderOptionID;
    //public static final int kModifyCode = kModifyCode;
    //public static final int kModifyID = kModifyID;
    public static final int kDay = kTourSubLastField + 1;
    public static final int kEtd = kDay + 1;
    public static final int kProductTypeID = kEtd + 1;
    public static final int kProductID = kProductTypeID + 1;
    public static final int kRateID = kProductID + 1;
    public static final int kClassID = kRateID + 1;
    public static final int kInfoMessageTransportID = kClassID + 1;
    public static final int kInfoStatusID = kInfoMessageTransportID + 1;
    public static final int kCostMessageTransportID = kInfoStatusID + 1;
    public static final int kCostStatusID = kCostMessageTransportID + 1;
    public static final int kInventoryMessageTransportID = kCostStatusID + 1;
    public static final int kInventoryStatusID = kInventoryMessageTransportID + 1;
    public static final int kProductMessageTransportID = kInventoryStatusID + 1;
    public static final int kProductStatusID = kProductMessageTransportID + 1;
    public static final int kAckDays = kProductStatusID + 1;
    public static final int kComments = kAckDays + 1;
    public static final int kItineraryDesc = kComments + 1;
    public static final int kNights = kItineraryDesc + 1;
    public static final int kMeal1 = kNights + 1;
    public static final int kMealQty1 = kMeal1 + 1;
    public static final int kMealDays1 = kMealQty1 + 1;
    public static final int kMeal2 = kMealDays1 + 1;
    public static final int kMealQty2 = kMeal2 + 1;
    public static final int kMealDays2 = kMealQty2 + 1;
    public static final int kMeal3 = kMealDays2 + 1;
    public static final int kMealQty3 = kMeal3 + 1;
    public static final int kMealDays3 = kMealQty3 + 1;
    public static final int kMeal4 = kMealDays3 + 1;
    public static final int kMealQty4 = kMeal4 + 1;
    public static final int kMealDays4 = kMealQty4 + 1;
    public static final int kPMCCutoff = kMealDays4 + 1;
    public static final int kCityID = kPMCCutoff + 1;
    public static final int kCityCode = kCityID + 1;
    public static final int kToCityID = kCityCode + 1;
    public static final int kToCityCode = kToCityID + 1;
    public static final int kTourHeaderAirHeaderID = kToCityCode + 1;
    public static final int kGMTTime = kTourHeaderAirHeaderID + 1;
    public static final int kCityDesc = kGMTTime + 1;
    public static final int kToCityDesc = kCityDesc + 1;
    public static final int kTicketID = kToCityDesc + 1;
    public static final int kXO = kTicketID + 1;
    public static final int kCarrier = kXO + 1;
    public static final int kAirlineID = kCarrier + 1;
    public static final int kFlightNo = kAirlineID + 1;
    public static final int kFlightClass = kFlightNo + 1;
    public static final int kArriveTime = kFlightClass + 1;
    public static final int kAddDays = kArriveTime + 1;
    public static final int kARCStatus = kAddDays + 1;
    public static final int kFareBasis = kARCStatus + 1;
    public static final int kStartDate = kFareBasis + 1;
    public static final int kEndDate = kStartDate + 1;
    public static final int kAllow = kEndDate + 1;
    public static final int kDetFare = kAllow + 1;
    public static final int kConfirmedBy = kDetFare + 1;
    public static final int kConfirmationNo = kConfirmedBy + 1;
    public static final int kCoupon = kConfirmationNo + 1;
    public static final int kSeat = kCoupon + 1;
    public static final int kGate = kSeat + 1;
    public static final int kSeatPerf = kGate + 1;
    public static final int kSmoking = kSeatPerf + 1;
    public static final int kMeals = kSmoking + 1;
    public static final int kDays = kMeals + 1;
    public static final int kPricingTypeID = kDays + 1;
    public static final int kTourHeaderDetailLastField = kPricingTypeID;
    public static final int kTourHeaderDetailFields = kPricingTypeID - DBConstants.MAIN_FIELD + 1;

    public static final int kIDKey = DBConstants.MAIN_KEY_FIELD;
    public static final int kTourHeaderOptionIDKey = kIDKey + 1;
    public static final int kProductIDKey = kTourHeaderOptionIDKey + 1;
    public static final int kTourHeaderDetailLastKey = kProductIDKey;
    public static final int kTourHeaderDetailKeys = kProductIDKey - DBConstants.MAIN_KEY_FIELD + 1;
    /**
     * Default constructor.
     */
    public TourHeaderDetail()
    {
        super();
    }
    /**
     * Constructor.
     */
    public TourHeaderDetail(RecordOwner screen)
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

    public static final String kTourHeaderDetailFile = "TourHeaderDetail";
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(kTourHeaderDetailFile, bAddQuotes) : super.getTableNames(bAddQuotes);
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
        return DBConstants.REMOTE | DBConstants.BASE_TABLE_CLASS | DBConstants.SHARED_TABLE | DBConstants.USER_DATA;
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
        if (iFieldSeq == kTourHeaderOptionID)
        {
            field = new TourHeaderOptionField(this, "TourHeaderOptionID", 8, null, null);
            field.setNullable(false);
        }
        //if (iFieldSeq == kModifyCode)
        //  field = new ModifyCodeField(this, "ModifyCode", 1, null, null);
        //if (iFieldSeq == kModifyID)
        //  field = new ModifyTourSubField(this, "ModifyID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kDay)
            field = new ShortField(this, "Day", 2, null, new Short((short)1));
        if (iFieldSeq == kEtd)
            field = new TimeField(this, "Etd", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kProductTypeID)
            field = new ProductTypeField(this, "ProductTypeID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kProductID)
            field = new ReferenceField(this, "ProductID", 8, null, null);
        if (iFieldSeq == kRateID)
            field = new BaseRateField(this, "RateID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kClassID)
            field = new BaseClassField(this, "ClassID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kInfoMessageTransportID)
            field = new MessageTransportSelect(this, "InfoMessageTransportID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kInfoStatusID)
            field = new InfoStatusSelect(this, "InfoStatusID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kCostMessageTransportID)
            field = new MessageTransportSelect(this, "CostMessageTransportID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kCostStatusID)
            field = new CostStatusSelect(this, "CostStatusID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kInventoryMessageTransportID)
            field = new MessageTransportSelect(this, "InventoryMessageTransportID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kInventoryStatusID)
            field = new InventoryStatusSelect(this, "InventoryStatusID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kProductMessageTransportID)
            field = new MessageTransportSelect(this, "ProductMessageTransportID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kProductStatusID)
            field = new ProductStatusSelect(this, "ProductStatusID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kAckDays)
            field = new ShortField(this, "AckDays", 2, null, null);
        if (iFieldSeq == kComments)
            field = new MemoField(this, "Comments", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kItineraryDesc)
            field = new XmlField(this, "ItineraryDesc", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kNights)
            field = new ShortField(this, "Nights", 2, null, new Short((short)1));
        if (iFieldSeq == kMeal1)
            field = new MealPlanField(this, "Meal1", 2, null, null);
        if (iFieldSeq == kMealQty1)
            field = new ShortField(this, "MealQty1", 2, null, null);
        if (iFieldSeq == kMealDays1)
            field = new MealDays(this, "MealDays1", 2, null, null);
        if (iFieldSeq == kMeal2)
            field = new MealPlanField(this, "Meal2", 2, null, null);
        if (iFieldSeq == kMealQty2)
            field = new ShortField(this, "MealQty2", 2, null, null);
        if (iFieldSeq == kMealDays2)
            field = new MealDays(this, "MealDays2", 2, null, null);
        if (iFieldSeq == kMeal3)
            field = new MealPlanField(this, "Meal3", 2, null, null);
        if (iFieldSeq == kMealQty3)
            field = new ShortField(this, "MealQty3", 2, null, null);
        if (iFieldSeq == kMealDays3)
            field = new MealDays(this, "MealDays3", 2, null, null);
        if (iFieldSeq == kMeal4)
            field = new MealPlanField(this, "Meal4", 2, null, null);
        if (iFieldSeq == kMealQty4)
            field = new ShortField(this, "MealQty4", 2, null, null);
        if (iFieldSeq == kMealDays4)
            field = new MealDays(this, "MealDays4", 2, null, null);
        if (iFieldSeq == kPMCCutoff)
            field = new ShortField(this, "PMCCutoff", 3, null, null);
        if (iFieldSeq == kCityID)
            field = new CityField(this, "CityID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kCityCode)
            field = new StringField(this, "CityCode", 3, null, null);
        if (iFieldSeq == kToCityID)
            field = new CityField(this, "ToCityID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kToCityCode)
            field = new StringField(this, "ToCityCode", 3, null, null);
        if (iFieldSeq == kTourHeaderAirHeaderID)
            field = new TourHeaderAirHeaderField(this, "TourHeaderAirHeaderID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kGMTTime)
        {
            field = new TimeField(this, "GMTTime", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kCityDesc)
        {
            field = new StringField(this, "CityDesc", 17, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kToCityDesc)
        {
            field = new StringField(this, "ToCityDesc", 17, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kTicketID)
            field = new ReferenceField(this, "TicketID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kXO)
        {
            field = new StringField(this, "XO", 1, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kCarrier)
        {
            field = new StringField(this, "Carrier", 2, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kAirlineID)
            field = new AirlineField(this, "AirlineID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kFlightNo)
        {
            field = new StringField(this, "FlightNo", 4, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kFlightClass)
        {
            field = new StringField(this, "FlightClass", 1, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kArriveTime)
        {
            field = new TimeField(this, "ArriveTime", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kAddDays)
            field = new ShortField(this, "AddDays", 2, null, null);
        if (iFieldSeq == kARCStatus)
            field = new StringField(this, "ARCStatus", 2, null, "OK");
        if (iFieldSeq == kFareBasis)
        {
            field = new StringField(this, "FareBasis", 15, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kStartDate)
        {
            field = new DateField(this, "StartDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kEndDate)
        {
            field = new DateField(this, "EndDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kAllow)
        {
            field = new StringField(this, "Allow", 3, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kDetFare)
        {
            field = new DoubleField(this, "DetFare", 10, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kConfirmedBy)
        {
            field = new StringField(this, "ConfirmedBy", 16, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kConfirmationNo)
        {
            field = new StringField(this, "ConfirmationNo", 20, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kCoupon)
        {
            field = new ShortField(this, "Coupon", 1, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kSeat)
        {
            field = new StringField(this, "Seat", 5, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kGate)
        {
            field = new StringField(this, "Gate", 5, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kSeatPerf)
        {
            field = new StringField(this, "SeatPerf", 1, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kSmoking)
        {
            field = new BooleanField(this, "Smoking", 1, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kMeals)
        {
            field = new StringField(this, "Meals", 2, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kDays)
        {
            field = new FloatField(this, "Days", Constants.DEFAULT_FIELD_LENGTH, null, new Float(1));
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kPricingTypeID)
            field = new PricingTypeSelect(this, "PricingTypeID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kTourHeaderDetailLastField)
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
        if (iKeyArea == kTourHeaderOptionIDKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "TourHeaderOptionID");
            keyArea.addKeyField(kTourHeaderOptionID, DBConstants.ASCENDING);
            keyArea.addKeyField(kDay, DBConstants.ASCENDING);
            keyArea.addKeyField(kEtd, DBConstants.ASCENDING);
        }
        if (iKeyArea == kProductIDKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "ProductID");
            keyArea.addKeyField(kProductID, DBConstants.ASCENDING);
            keyArea.addKeyField(kDay, DBConstants.ASCENDING);
            keyArea.addKeyField(kEtd, DBConstants.ASCENDING);
        }
        if (keyArea == null) if (iKeyArea < kTourHeaderDetailLastKey)
        {
            keyArea = super.setupKey(iKeyArea);     
            if (keyArea == null) if (iKeyArea < kTourHeaderDetailLastKey)
                keyArea = new EmptyKey(this);
        }
        return keyArea;
    }
    /**
     * Get the record type from the field that specifies the record type.
     * (Override this).
     * @return The record type (as an object).
     */
    public BaseField getSharedRecordTypeKey()
    {
        return this.getField(TourHeaderDetail.kProductTypeID);
    }
    /**
     * Get the shared record that goes with this key.
     * (Always override this).
     * @param objKey The value that specifies the record type.
     * @return The correct (new) record for this type (or null if none).
     */
    public Record createSharedRecord(Object objKey, RecordOwner recordOwner)
    {
        if (objKey instanceof Integer)
        {
            int iProductType = ((Integer)objKey).intValue();
            if (iProductType == ProductType.HOTEL_ID)
                return new TourHeaderHotel(recordOwner);
            if (iProductType == ProductType.LAND_ID)
                return new TourHeaderLand(recordOwner);
            if (iProductType == ProductType.AIR_ID)
                return new TourHeaderAir(recordOwner);
            if (iProductType == ProductType.CAR_ID)
                return new TourHeaderCar(recordOwner);
            if (iProductType == ProductType.CRUISE_ID)
                return new TourHeaderCruise(recordOwner);
            if (iProductType == ProductType.ITEM_ID)
                return new TourHeaderItem(recordOwner);
            if (iProductType == ProductType.TOUR_ID)
                return new TourHeaderTour(recordOwner);
            if (iProductType == ProductType.TRANSPORTATION_ID)
                return new TourHeaderTransportation(recordOwner);
        }
        return null;
    }

}

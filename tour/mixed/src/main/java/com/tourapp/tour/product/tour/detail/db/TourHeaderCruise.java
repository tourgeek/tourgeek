/**
 * @(#)TourHeaderCruise.
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
import com.tourapp.tour.product.tour.detail.screen.*;
import org.jbundle.base.db.shared.*;
import com.tourapp.tour.product.base.db.*;
import com.tourapp.tour.product.tour.db.*;
import com.tourapp.tour.product.cruise.db.*;
import org.jbundle.main.msg.db.*;
import com.tourapp.tour.base.db.*;
import com.tourapp.model.tour.product.tour.detail.db.*;

/**
 *  TourHeaderCruise - .
 */
public class TourHeaderCruise extends TourHeaderTransport
     implements TourHeaderCruiseModel
{
    private static final long serialVersionUID = 1L;

    //public static final int kID = kID;
    //public static final int kTourHeaderOptionID = kTourHeaderOptionID;
    //public static final int kModifyCode = kModifyCode;
    //public static final int kModifyID = kModifyID;
    //public static final int kDay = kDay;
    //public static final int kEtd = kEtd;
    //public static final int kProductTypeID = kProductTypeID;
    //public static final int kProductID = kProductID;
    //public static final int kRateID = kRateID;
    //public static final int kClassID = kClassID;
    //public static final int kInfoMessageTransportID = kInfoMessageTransportID;
    //public static final int kInfoStatusID = kInfoStatusID;
    //public static final int kCostMessageTransportID = kCostMessageTransportID;
    //public static final int kCostStatusID = kCostStatusID;
    //public static final int kInventoryMessageTransportID = kInventoryMessageTransportID;
    //public static final int kInventoryStatusID = kInventoryStatusID;
    //public static final int kProductMessageTransportID = kProductMessageTransportID;
    //public static final int kProductStatusID = kProductStatusID;
    //public static final int kAckDays = kAckDays;
    //public static final int kComments = kComments;
    //public static final int kItineraryDesc = kItineraryDesc;
    //public static final int kNights = kNights;
    //public static final int kMeal1 = kMeal1;
    //public static final int kMealQty1 = kMealQty1;
    //public static final int kMealDays1 = kMealDays1;
    //public static final int kMeal2 = kMeal2;
    //public static final int kMealQty2 = kMealQty2;
    //public static final int kMealDays2 = kMealDays2;
    //public static final int kMeal3 = kMeal3;
    //public static final int kMealQty3 = kMealQty3;
    //public static final int kMealDays3 = kMealDays3;
    //public static final int kMeal4 = kMeal4;
    //public static final int kMealQty4 = kMealQty4;
    //public static final int kMealDays4 = kMealDays4;
    //public static final int kPMCCutoff = kPMCCutoff;
    //public static final int kCityID = kCityID;
    //public static final int kCityCode = kCityCode;
    //public static final int kToCityID = kToCityID;
    //public static final int kToCityCode = kToCityCode;
    //public static final int kTourHeaderAirHeaderID = kTourHeaderAirHeaderID;
    //public static final int kGMTTime = kGMTTime;
    //public static final int kCityDesc = kCityDesc;
    //public static final int kToCityDesc = kToCityDesc;
    //public static final int kTicketID = kTicketID;
    //public static final int kXO = kXO;
    //public static final int kCarrier = kCarrier;
    //public static final int kAirlineID = kAirlineID;
    //public static final int kFlightNo = kFlightNo;
    //public static final int kFlightClass = kFlightClass;
    //public static final int kArriveTime = kArriveTime;
    //public static final int kAddDays = kAddDays;
    //public static final int kARCStatus = kARCStatus;
    //public static final int kFareBasis = kFareBasis;
    //public static final int kStartDate = kStartDate;
    //public static final int kEndDate = kEndDate;
    //public static final int kAllow = kAllow;
    //public static final int kDetFare = kDetFare;
    //public static final int kConfirmedBy = kConfirmedBy;
    //public static final int kConfirmationNo = kConfirmationNo;
    //public static final int kCoupon = kCoupon;
    //public static final int kSeat = kSeat;
    //public static final int kGate = kGate;
    //public static final int kSeatPerf = kSeatPerf;
    //public static final int kSmoking = kSmoking;
    //public static final int kMeals = kMeals;
    //public static final int kDays = kDays;
    //public static final int kPricingTypeID = kPricingTypeID;
    public static final int kTourHeaderCruiseLastField = kTourHeaderTransportLastField;
    public static final int kTourHeaderCruiseFields = kTourHeaderTransportLastField - DBConstants.MAIN_FIELD + 1;
    /**
     * Default constructor.
     */
    public TourHeaderCruise()
    {
        super();
    }
    /**
     * Constructor.
     */
    public TourHeaderCruise(RecordOwner screen)
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

    public static final String kTourHeaderCruiseFile = "TourHeaderDetail";
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(kTourHeaderCruiseFile, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "Tour cruise detail";
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
        return DBConstants.REMOTE | DBConstants.SHARED_TABLE | DBConstants.USER_DATA;
    }
    /**
     * Make a default screen.
     */
    public BaseScreen makeScreen(ScreenLocation itsLocation, BasePanel parentScreen, int iDocMode, Map<String,Object> properties)
    {
        BaseScreen screen = null;
        if ((iDocMode & ScreenConstants.MAINT_MODE) != 0)
            screen = new TourHeaderCruiseScreen(this, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if ((iDocMode & ScreenConstants.DISPLAY_MODE) != 0)
            screen = new TourHeaderCruiseGridScreen(this, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else
            screen = super.makeScreen(itsLocation, parentScreen, iDocMode, properties);
        return screen;
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
        //if (iFieldSeq == kDay)
        //  field = new ShortField(this, "Day", 2, null, new Short((short)1));
        //if (iFieldSeq == kEtd)
        //  field = new TimeField(this, "Etd", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kProductTypeID)
        //  field = new ProductTypeField(this, "ProductTypeID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kProductID)
            field = new CruiseField(this, "ProductID", 8, null, null);
        if (iFieldSeq == kRateID)
            field = new CruiseRateSelect(this, "RateID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kClassID)
            field = new CruiseClassSelect(this, "ClassID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kInfoMessageTransportID)
        //  field = new MessageTransportSelect(this, "InfoMessageTransportID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kInfoStatusID)
        //  field = new InfoStatusSelect(this, "InfoStatusID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kCostMessageTransportID)
        //  field = new MessageTransportSelect(this, "CostMessageTransportID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kCostStatusID)
        //  field = new CostStatusSelect(this, "CostStatusID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kInventoryMessageTransportID)
        //  field = new MessageTransportSelect(this, "InventoryMessageTransportID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kInventoryStatusID)
        //  field = new InventoryStatusSelect(this, "InventoryStatusID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kProductMessageTransportID)
        //  field = new MessageTransportSelect(this, "ProductMessageTransportID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kProductStatusID)
        //  field = new ProductStatusSelect(this, "ProductStatusID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kAckDays)
        //  field = new ShortField(this, "AckDays", 2, null, null);
        //if (iFieldSeq == kComments)
        //  field = new MemoField(this, "Comments", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kItineraryDesc)
        //  field = new XmlField(this, "ItineraryDesc", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kNights)
            field = new UnusedField(this, "Nights", 2, null, null);
        if (iFieldSeq == kMeal1)
            field = new UnusedField(this, "Meal1", 2, null, null);
        if (iFieldSeq == kMealQty1)
            field = new UnusedField(this, "MealQty1", 2, null, null);
        if (iFieldSeq == kMealDays1)
            field = new UnusedField(this, "MealDays1", 2, null, null);
        if (iFieldSeq == kMeal2)
            field = new UnusedField(this, "Meal2", 2, null, null);
        if (iFieldSeq == kMealQty2)
            field = new UnusedField(this, "MealQty2", 2, null, null);
        if (iFieldSeq == kMealDays2)
            field = new UnusedField(this, "MealDays2", 2, null, null);
        if (iFieldSeq == kMeal3)
            field = new UnusedField(this, "Meal3", 2, null, null);
        if (iFieldSeq == kMealQty3)
            field = new UnusedField(this, "MealQty3", 2, null, null);
        if (iFieldSeq == kMealDays3)
            field = new UnusedField(this, "MealDays3", 2, null, null);
        if (iFieldSeq == kMeal4)
            field = new UnusedField(this, "Meal4", 2, null, null);
        if (iFieldSeq == kMealQty4)
            field = new UnusedField(this, "MealQty4", 2, null, null);
        if (iFieldSeq == kMealDays4)
            field = new UnusedField(this, "MealDays4", 2, null, null);
        if (iFieldSeq == kPMCCutoff)
            field = new UnusedField(this, "PMCCutoff", 3, null, null);
        //if (iFieldSeq == kCityID)
        //  field = new CityField(this, "CityID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kCityCode)
        //  field = new StringField(this, "CityCode", 3, null, null);
        //if (iFieldSeq == kToCityID)
        //  field = new CityField(this, "ToCityID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kToCityCode)
        //  field = new StringField(this, "ToCityCode", 3, null, null);
        if (iFieldSeq == kTourHeaderAirHeaderID)
            field = new UnusedField(this, "TourHeaderAirHeaderID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kGMTTime)
            field = new UnusedField(this, "GMTTime", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kCityDesc)
            field = new UnusedField(this, "CityDesc", 17, null, null);
        if (iFieldSeq == kToCityDesc)
            field = new UnusedField(this, "ToCityDesc", 17, null, null);
        if (iFieldSeq == kTicketID)
            field = new UnusedField(this, "TicketID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kXO)
            field = new UnusedField(this, "XO", 1, null, null);
        if (iFieldSeq == kCarrier)
            field = new UnusedField(this, "Carrier", 2, null, null);
        if (iFieldSeq == kAirlineID)
            field = new UnusedField(this, "AirlineID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kFlightNo)
            field = new UnusedField(this, "FlightNo", 4, null, null);
        if (iFieldSeq == kFlightClass)
            field = new UnusedField(this, "FlightClass", 1, null, null);
        if (iFieldSeq == kArriveTime)
            field = new UnusedField(this, "ArriveTime", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kAddDays)
            field = new UnusedField(this, "AddDays", 2, null, null);
        if (iFieldSeq == kARCStatus)
            field = new UnusedField(this, "ARCStatus", 2, null, null);
        if (iFieldSeq == kFareBasis)
            field = new UnusedField(this, "FareBasis", 15, null, null);
        if (iFieldSeq == kStartDate)
            field = new UnusedField(this, "StartDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kEndDate)
            field = new UnusedField(this, "EndDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kAllow)
            field = new UnusedField(this, "Allow", 3, null, null);
        if (iFieldSeq == kDetFare)
            field = new UnusedField(this, "DetFare", 10, null, null);
        if (iFieldSeq == kConfirmedBy)
            field = new UnusedField(this, "ConfirmedBy", 16, null, null);
        if (iFieldSeq == kConfirmationNo)
            field = new UnusedField(this, "ConfirmationNo", 20, null, null);
        if (iFieldSeq == kCoupon)
            field = new UnusedField(this, "Coupon", 1, null, null);
        if (iFieldSeq == kSeat)
            field = new UnusedField(this, "Seat", 5, null, null);
        if (iFieldSeq == kGate)
            field = new UnusedField(this, "Gate", 5, null, null);
        if (iFieldSeq == kSeatPerf)
            field = new UnusedField(this, "SeatPerf", 1, null, null);
        if (iFieldSeq == kSmoking)
            field = new UnusedField(this, "Smoking", 1, null, null);
        if (iFieldSeq == kMeals)
            field = new UnusedField(this, "Meals", 2, null, null);
        if (iFieldSeq == kDays)
            field = new UnusedField(this, "Days", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kPricingTypeID)
            field = new UnusedField(this, "PricingTypeID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kTourHeaderCruiseLastField)
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
        
        this.addListener(new SharedFileHandler(TourHeaderDetail.kProductTypeID, ProductType.CRUISE_ID));
    }

}

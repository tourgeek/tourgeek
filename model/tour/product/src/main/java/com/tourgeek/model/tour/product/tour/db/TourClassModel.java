/**
  * @(#)TourClassModel.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.model.tour.product.tour.db;

import org.jbundle.model.db.*;

public interface TourClassModel extends Rec
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    public static final String CLASS_NAME = "ClassName";
    public static final String BASED_CLASS_ID = "BasedClassID";
    public static final String DEPOSIT_DUE_DAYS = "DepositDueDays";
    public static final String FINAL_PAY_DAYS = "FinalPayDays";
    public static final String FINALIZE_DAYS = "FinalizeDays";
    public static final String ORDER_COMP_DAYS = "OrderCompDays";
    public static final String CLOSED_DAYS = "ClosedDays";
    public static final String FINAL_DOC_DAYS = "FinalDocDays";
    public static final String TICKET_DAYS = "TicketDays";
    public static final String SP_1_DAYS = "Sp1Days";
    public static final String SP_1_DESC = "Sp1Desc";
    public static final String SP_2_DAYS = "Sp2Days";
    public static final String SP_2_DESC = "Sp2Desc";
    public static final String ADD_DEPOSIT = "AddDeposit";
    public static final String DEPOSIT_AMOUNT = "DepositAmount";
    public static final String CANCEL_CHARGE = "CancelCharge";
    public static final String MOD_BEFORE_CHARGE = "ModBeforeCharge";
    public static final String MOD_AFTER_CHARGE = "ModAfterCharge";
    public static final String UPGRADE_PROFILE_STATUS_ID = "UpgradeProfileStatusID";
    public static final String AIR_RATE_ID = "AirRateID";
    public static final String AIR_CLASS_ID = "AirClassID";
    public static final String HOTEL_RATE_ID = "HotelRateID";
    public static final String HOTEL_CLASS_ID = "HotelClassID";
    public static final String LAND_RATE_ID = "LandRateID";
    public static final String LAND_CLASS_ID = "LandClassID";
    public static final String PMC_CUTOFF = "PMCCutoff";
    public static final String CAR_CLASS_ID = "CarClassID";
    public static final String CAR_RATE_ID = "CarRateID";
    public static final String TRANSPORTATION_RATE_ID = "TransportationRateID";
    public static final String TRANSPORTATION_CLASS_ID = "TransportationClassID";
    public static final String CRUISE_RATE_ID = "CruiseRateID";
    public static final String CRUISE_CLASS_ID = "CruiseClassID";
    public static final String ITEM_RATE_ID = "ItemRateID";
    public static final String ITEM_CLASS_ID = "ItemClassID";
    public static final String INITIAL_BOOKING_STATUS_ID = "InitialBookingStatusID";
    public static final String INITIAL_TOUR_STATUS_ID = "InitialTourStatusID";
    public static final String MARKUP = "Markup";
    public static final String TOUR_PRICING_TYPE_ID = "TourPricingTypeID";
    public static final String NON_TOUR_PRICING_TYPE_ID = "NonTourPricingTypeID";

    public static final String CLASS_NAME_KEY = "ClassName";
    public static final String TOUR_CLASS_SCREEN_CLASS = "com.tourgeek.tour.product.tour.screen.TourClassScreen";
    public static final String TOUR_CLASS_GRID_SCREEN_CLASS = "com.tourgeek.tour.product.tour.screen.TourClassGridScreen";

    public static final String TOUR_CLASS_FILE = "TourClass";
    public static final String THIN_CLASS = "com.tourgeek.thin.tour.product.tour.db.TourClass";
    public static final String THICK_CLASS = "com.tourgeek.tour.product.tour.db.TourClass";

}

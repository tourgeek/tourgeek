/**
 * @(#)BookingDetailModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.booking.detail.db;

import com.tourapp.model.tour.booking.detail.db.*;

public interface BookingDetailModel extends BookingSubModel
{
    public static final String DESCRIPTION = "Description";
    public static final String PRODUCT_TYPE = "ProductType";
    public static final String DETAIL_DATE = "DetailDate";
    public static final String PRODUCT_ID = "ProductID";
    public static final String RATE_ID = "RateID";
    public static final String CLASS_ID = "ClassID";
    public static final String TOTAL_COST = "TotalCost";
    public static final String CURRENCY_CODE = "CurrencyCode";
    public static final String TOTAL_COST_LOCAL = "TotalCostLocal";
    public static final String TOTAL_PRICE_LOCAL = "TotalPriceLocal";
    public static final String REMOTE_BOOKING_NO = "RemoteBookingNo";
    public static final String DETAIL_END_DATE = "DetailEndDate";
    public static final String MEAL_SUMMARY = "MealSummary";
    public static final String STATUS_SUMMARY = "StatusSummary";
    public static final String PP_COST = "PPCost";
    public static final String PP_PRICE_LOCAL = "PPPriceLocal";
    public static final String MESSAGE_PARAM = "message";
    public static final String ERROR_PARAM = "error";
    public static final String INFO_PARAM = "info";
    public static final String COST_PARAM = "cost";
    public static final String INVENTORY_PARAM = "inventory";
    public static final String PRODUCT_PARAM = "product";

    public static final String BOOKING_DETAIL_FILE = "BookingDetail";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.booking.detail.db.BookingDetail";
    public static final String THICK_CLASS = "com.tourapp.tour.booking.detail.db.BookingDetail";

}

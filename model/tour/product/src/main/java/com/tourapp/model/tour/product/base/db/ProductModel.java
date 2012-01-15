/**
 * @(#)ProductModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.product.base.db;

import org.jbundle.model.db.*;

public interface ProductModel extends Rec
{
    public static final String DESCRIPTION = "Description";
    public static final String CODE = "Code";
    public static final String VENDOR_ID = "VendorID";
    public static final String OPERATORS_CODE = "OperatorsCode";
    public static final String CITY_ID = "CityID";
    public static final String ETD = "Etd";
    public static final String ACK_DAYS = "AckDays";
    public static final String COMMENTS = "Comments";
    public static final String PRODUCT_TYPE = "ProductType";
    public static final String PRODUCT_COST = "ProductCost";
    public static final String PRODUCT_COST_LOCAL = "ProductCostLocal";
    public static final String DISPLAY_INVENTORY_STATUS_ID = "DisplayInventoryStatusID";
    public static final String INVENTORY_AVAILABILITY = "InventoryAvailability";
    public static final String CURRENCY_CODE = "CurrencyCode";
    public static final String CURRENCY_CODE_LOCAL = "CurrencyCodeLocal";
    public static final String VENDOR_NAME = "VendorName";
    public static final String DISPLAY_COST_STATUS_ID = "DisplayCostStatusID";
    public static final String PRODUCT_PRICE_LOCAL = "ProductPriceLocal";
    public static final String PP_PRICE_LOCAL = "PPPriceLocal";
    public final static String AVAILABILITY_PARAM = "Availability";
    public static final String PAX_PARAM = "Pax";
    public static final String PRODUCT_NAME_PARAM = "productDesc";
    public static final String RATE_CLASS_DESC_PARAM = "rateClassDesc";
    public static final String RATE_TYPE_DESC_PARAM = "rateTypeDesc";
    public static final String INVENTORY_PARAM = "Inventory";
    public static final String OTHER_ID_PARAM = "otherID";
    public static final String CONFIRMATION_NO_PARAM = "confirmationNo";
    public static final String CONFIRMED_BY_PARAM = "confirmedBy";
    public static final String COST_NOT_FOUND_MSG = "Cost not found";
    public static final String INVENTORY_DETAIL = "Inventory";
    public static final String PRICING_DETAIL = "Price";
    public static final String BOOKING_DETAIL = "BookingDetail";
    public static final String MESSAGE_DETAIL_SCREEN = "Message Detail";
    public static final String MESSAGE_LOG = "MessageLog";
    public static final String PRODUCT_SEARCH_DETAIL = "ProductSearchDetail";

    public static final String PRODUCT_FILE = "Product";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.product.base.db.Product";
    public static final String THICK_CLASS = "com.tourapp.tour.product.base.db.Product";

}

/*
 * BookingConstants.java
 *
 * Created on June 25, 2000, 6:40 AM
 
 * Copyright © 2011 jbundle.org. All rights reserved.
 */
package com.tourapp.thin.app.booking.entry;

import org.jbundle.thin.base.screen.cal.popup.ProductConstants;
import org.jbundle.thin.opt.location.JTreePanel;

/** 
 *
 * @author  Administrator
 * @version 1.0.0
 */
public interface BookingConstants
{
    // Status constants
    public static int INFO_LOOKUP = 1;
    public static int COST_LOOKUP = 2;
    public static int INVENTORY_LOOKUP = 3;
    public static int PRODUCT_LOOKUP = 4;

    public static final String BUTTON_LOCATION = "tour/buttons/";
    
    public static final String SEARCH = "Search";
    public static final String CALENDAR = "Calendar";
    public static final String INFO = "Information";
    public static final String COST = ProductConstants.COST;
    public static final String PRICE = ProductConstants.PRICE;
    public static final String PASSENGER = "Passenger";
    public static final String CONTACT = "Contact";
    public static final String ITINERARY = "Itinerary";

    public static final String DATE = "date";
    public static final String TOUR_TYPE = "tourType";
    public static final String SEARCH_TEXT = "searchText";
    public static final String PAX = "pax";
    public static final String AIRLINE = "airline";
    public static final String HOTEL_CLASS = "hotelClass";
    public static final String AIR_CLASS = "airClass";
    public static final String CAR_CLASS = "carClass";
    public static final String TRANSPORTATION_CLASS = "transClass";
    public static final String CRUISE_CLASS = "cruiseClass";
    public static final String ITEM_CLASS = "itemClass";
    public static final String LAND_CLASS = "landClass";
    public static final String TOUR_CLASS = "tourClass";
    public static final String DATE_DISPLAY = "dateDisplay";

    public static final String PRODUCT_TYPE = "productType";
    
    public static final String SUCCESS = "success";
    
    public static final String MEAL_PLAN = "mealPlanID";
    public final static String MEAL_PLAN_QTY = "mealPlanQty";
    public final static String NIGHTS = "Nights";
    public final static String ROOM_TYPE = "roomType";
    public final static String PRODUCT_ID = "productID";
    public final static String SIC_PMC = "sicPmc";

    public static final String BUTTON = "Button";
    public static final String TIP = "Tip";

    public static final String LOCATION = JTreePanel.LOCATION;
    public static final String LOCATION_TO = JTreePanel.LOCATION + "To";

    public static final String MEAL = ProductConstants.MEAL;
    public static final String INVENTORY = ProductConstants.INVENTORY;
    public static final String NO_INVENTORY = ProductConstants.NO_INVENTORY;
    public static final String BOOKING = ProductConstants.BOOKING;

    public static final String REQUERY_COMMAND = "requery";
    public static final String ADD_COMMAND = "ADD_COMMAND";
    public static final String GET_DETAIL_COMMAND = "getDetail";

    public static final String PRODUCT = "Product";
    public static final String DETAIL = "Detail";

    public static final String SELECTED = "SELECTED";
}

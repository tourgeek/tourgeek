/**
 * BookingConstants.java
 *
 * Created on June 25, 2000, 6:40 AM
 */
 
package com.tourapp.thin.app.booking.entry.search;

import java.awt.Dimension;

import com.tourapp.thin.app.booking.entry.BookingConstants;

/** 
 *
 * @author  Administrator
 * @version 1.0.0
 */
public interface SearchConstants extends BookingConstants
{
    /**
     * So I know this is a search button.
     */
    public static final String SEARCH_BUTTON = "SearchButton";
    /**
     * Add this product to the calendar.
     */
    public static final String ADD = "Add";
    /**
     * Select the fields for the maintenance screen.
     */
    public static final String SELECT_MAINT = "SELECT_MAINT";
    /**
     * Select the fields for the grid screen.
     */
    public static final String SELECT_GRID = "SELECT_GRID";
    /**
     *
     */
    public static final String DEFAULT_PRODUCT_TYPE = "Item";
    /**
     *
     */
    public static final Dimension DEFAULT_BUTTON_SIZE = new Dimension(20, 22);
}

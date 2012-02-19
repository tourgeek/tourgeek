/**
 * @(#)TourEventScheduleModel.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.product.tour.schedule.db;

import org.jbundle.model.main.db.*;

public interface TourEventScheduleModel extends PropertiesRecordModel
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    public static final String ACTION_PROPERTIES = PROPERTIES;
    public static final String TOUR_CLASS_ID = "TourClassID";
    public static final String TOUR_EVENT_ID = "TourEventID";
    public static final String BOOKING_STATUS_ID = "BookingStatusID";
    public static final String TOUR_CLASS_ONLY = "TourClassOnly";
    public static final String TOUR_ACTION_TYPE = "TourActionType";
    public static final String ACTION_TOUR_EVENT_ID = "ActionTourEventID";
    public static final String ACTION_BOOKING_STATUS_ID = "ActionBookingStatusID";
    public static final String ACTION_MESSAGE_PROCESS_INFO_ID = "ActionMessageProcessInfoID";
    public static final String ACTION_MESSAGE_TRANSPORT_ID = "ActionMessageTransportID";
    public static final String RUN_PROCESS_IN = "RunProcessIn";
    public static final String ACTION_DOCUMENT_NAME = "ActionDocumentName";
    public static final String ACTION_DOCUMENT_TEXT = "ActionDocumentText";

    public static final String TOUR_CLASS_ID_KEY = "TourClassID";
    public static final String EVENTS = "Events";
    public static final String TOUR_EVENT_SCHEDULE_SCREEN_CLASS = "com.tourapp.tour.product.tour.schedule.screen.TourEventScheduleScreen";
    public static final String TOUR_EVENT_SCHEDULE_GRID_SCREEN_CLASS = "com.tourapp.tour.product.tour.schedule.screen.TourEventScheduleGridScreen";

    public static final String TOUR_EVENT_SCHEDULE_FILE = "TourEventSchedule";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.product.tour.schedule.db.TourEventSchedule";
    public static final String THICK_CLASS = "com.tourapp.tour.product.tour.schedule.db.TourEventSchedule";

}

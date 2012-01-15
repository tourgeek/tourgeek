/**
 * @(#)TourEventScheduleModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.product.tour.schedule.db;

import org.jbundle.model.main.db.*;

public interface TourEventScheduleModel extends PropertiesRecordModel
{
    public static final String EVENTS = "Events";

    public static final String TOUR_EVENT_SCHEDULE_FILE = "TourEventSchedule";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.product.tour.schedule.db.TourEventSchedule";
    public static final String THICK_CLASS = "com.tourapp.tour.product.tour.schedule.db.TourEventSchedule";

}

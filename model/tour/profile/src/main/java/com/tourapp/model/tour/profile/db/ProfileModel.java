/**
 * @(#)ProfileModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.profile.db;

import org.jbundle.model.main.db.*;

public interface ProfileModel extends CompanyModel
{
    public static final String MESSAGE_LOG_SCREEN = "Message Log";
    public static final String MAINT_SCREEN_CLASS = "com.tourapp.tour.profile.screen.ProfileScreen";
    public static final String GRID_SCREEN_CLASS = "com.tourapp.tour.profile.screen.ProfileGridScreen";
    public static final String MESSAGE_LOG_GRID_SCREEN_CLASS = "org.jbundle.main.msg.screen.MessageLogGridScreen";
    public static final String REQUEST_HISTORY_GRID_SCREEN_CLASS = "com.tourapp.tour.request.screen.detail.RequestHistoryGridScreen";
    public static final String PROFILE_SCREEN_CLASS = "com.tourapp.tour.profile.screen.ProfileScreen";
    public static final String PROFILE_GRID_SCREEN_CLASS = "com.tourapp.tour.profile.screen.ProfileGridScreen";

    public static final String PROFILE_FILE = "Profile";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.profile.db.Profile";
    public static final String THICK_CLASS = "com.tourapp.tour.profile.db.Profile";

}

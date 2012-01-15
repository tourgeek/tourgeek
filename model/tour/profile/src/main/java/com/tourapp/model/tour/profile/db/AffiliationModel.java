/**
 * @(#)AffiliationModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.profile.db;

import org.jbundle.model.db.*;

public interface AffiliationModel extends Rec
{
    public static final String AFFILIATION_SCREEN_CLASS = "com.tourapp.tour.profile.screen.AffiliationScreen";
    public static final String AFFILIATION_GRID_SCREEN_CLASS = "com.tourapp.tour.profile.screen.AffiliationGridScreen";

    public static final String AFFILIATION_FILE = "Affiliation";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.profile.db.Affiliation";
    public static final String THICK_CLASS = "com.tourapp.tour.profile.db.Affiliation";

}

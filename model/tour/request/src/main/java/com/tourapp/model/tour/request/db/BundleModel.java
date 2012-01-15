/**
 * @(#)BundleModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.request.db;

import org.jbundle.model.db.*;

public interface BundleModel extends Rec
{
    public static final String DESCRIPTION = "Description";

    public static final String BUNDLE_FILE = "Bundle";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.request.db.Bundle";
    public static final String THICK_CLASS = "com.tourapp.tour.request.db.Bundle";

}

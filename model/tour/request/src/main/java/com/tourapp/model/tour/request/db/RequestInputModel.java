/**
 * @(#)RequestInputModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.request.db;

import org.jbundle.model.db.*;

public interface RequestInputModel extends Rec
{
    public static final String BROCHURE_QTY = "BrochureQty";
    public static final String BROCHURE_ID = "BrochureID";
    public static final String BROCHURE_DESC = "BrochureDesc";

    public static final String REQUEST_INPUT_FILE = "RequestInput";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.request.db.RequestInput";
    public static final String THICK_CLASS = "com.tourapp.tour.request.db.RequestInput";

}

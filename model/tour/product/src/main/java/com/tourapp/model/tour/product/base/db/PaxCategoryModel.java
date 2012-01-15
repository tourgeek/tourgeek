/**
 * @(#)PaxCategoryModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.product.base.db;

import org.jbundle.model.db.*;

public interface PaxCategoryModel extends Rec
{
    public static final String DESCRIPTION = "Description";
    public static final String SINGLE = "Single";
    public static final String DOUBLE = "Double";
    public static final String TRIPLE = "Triple";
    public static final String QUAD = "Quad";
    public static final String CHILD = "Child";
    public static final String ALL = "All";
    public static final int SINGLE_ID = 1;
    public static final int DOUBLE_ID = 2;
    public static final int TRIPLE_ID = 3;
    public static final int QUAD_ID = 4;
    public static final int CHILD_ID = 5;
    public static final int ALL_ID = 6;

    public static final String PAX_CATEGORY_FILE = "PaxCategory";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.product.base.db.PaxCategory";
    public static final String THICK_CLASS = "com.tourapp.tour.product.base.db.PaxCategory";

}

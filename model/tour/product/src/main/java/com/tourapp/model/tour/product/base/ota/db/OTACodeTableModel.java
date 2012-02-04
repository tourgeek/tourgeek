/**
 * @(#)OTACodeTableModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.product.base.ota.db;

import org.jbundle.model.db.*;

public interface OTACodeTableModel extends Rec
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    public static final String NAME = "Name";
    public static final String NAME_CODE = "NameCode";
    public static final String CREATION_DATE = "CreationDate";
    public static final String DELETION_DATE = "DeletionDate";
    public static final String VERSION_ID = "VersionID";
    public static final String PROPERTIES = "Properties";

    public static final String NAME_CODE_KEY = "NameCode";

    public static final String NAME_KEY = "Name";

    public static final String OTA_CODE_TABLE_FILE = "OTACodeTable";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.product.base.ota.db.OTACodeTable";
    public static final String THICK_CLASS = "com.tourapp.tour.product.base.ota.db.OTACodeTable";

}

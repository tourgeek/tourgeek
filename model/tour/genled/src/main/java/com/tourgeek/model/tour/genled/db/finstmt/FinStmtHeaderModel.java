
package com.tourgeek.model.tour.genled.db.finstmt;

import org.jbundle.model.db.*;

public interface FinStmtHeaderModel extends Rec
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    public static final String DESCRIPTION = "Description";

    public static final String DESCRIPTION_KEY = "Description";
    public static final String FIN_STMT_HEADER_SCREEN_CLASS = "com.tourgeek.tour.genled.finstmt.screen.FinStmtHeaderScreen";
    public static final String FIN_STMT_HEADER_GRID_SCREEN_CLASS = "com.tourgeek.tour.genled.finstmt.screen.FinStmtHeaderGridScreen";

    public static final String FIN_STMT_HEADER_FILE = "FinStmtHeader";
    public static final String THIN_CLASS = "com.tourgeek.thin.tour.genled.db.finstmt.FinStmtHeader";
    public static final String THICK_CLASS = "com.tourgeek.tour.genled.db.finstmt.FinStmtHeader";

}

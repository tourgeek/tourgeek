/**
 * @(#)FinStmtDetailModel.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.genled.db;

import org.jbundle.model.db.*;

public interface FinStmtDetailModel extends Rec
{
    public static final String FIN_STMT_DETAIL_SCREEN_CLASS = "com.tourapp.tour.genled.finstmt.screen.FinStmtDetailScreen";
    public static final String FIN_STMT_DETAIL_GRID_SCREEN_CLASS = "com.tourapp.tour.genled.finstmt.screen.FinStmtDetailGridScreen";
    public static final String RENUMBER = "Renumber";
    public static final String VALIDATE = "Validate";

    public static final String FIN_STMT_DETAIL_FILE = "FinStmtDetail";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.genled.db.FinStmtDetail";
    public static final String THICK_CLASS = "com.tourapp.tour.genled.db.FinStmtDetail";

}

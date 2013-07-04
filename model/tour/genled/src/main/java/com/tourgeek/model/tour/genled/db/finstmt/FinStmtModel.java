
package com.tourgeek.model.tour.genled.db.finstmt;

import org.jbundle.model.db.*;

public interface FinStmtModel extends Rec
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    public static final String STATEMENT_DESC = "StatementDesc";
    public static final String FIN_STMT_HEADER_ID = "FinStmtHeaderID";
    public static final String STMT_SEQUENCE = "StmtSequence";
    public static final String STATEMENT_TYPE = "StatementType";
    public static final String STATEMENT_FORMAT = "StatementFormat";
    public static final String STATEMENT_NUMBER = "StatementNumber";

    public static final String FIN_STMT_HEADER_ID_KEY = "FinStmtHeaderID";
    public static final String FIN_STMT_SCREEN_CLASS = "com.tourgeek.tour.genled.finstmt.screen.FinStmtScreen";
    public static final String FIN_STMT_GRID_SCREEN_CLASS = "com.tourgeek.tour.genled.finstmt.screen.FinStmtGridScreen";

    public static final String FIN_STMT_FILE = "FinStmt";
    public static final String THIN_CLASS = "com.tourgeek.thin.tour.genled.db.finstmt.FinStmt";
    public static final String THICK_CLASS = "com.tourgeek.tour.genled.db.finstmt.FinStmt";

}


package com.tourgeek.tour.genled.finstmt;

import java.util.*;

import org.jbundle.base.db.*;
import org.jbundle.thin.base.util.*;
import org.jbundle.thin.base.db.*;
import org.jbundle.base.db.event.*;
import org.jbundle.base.db.filter.*;
import org.jbundle.base.field.*;
import org.jbundle.base.field.convert.*;
import org.jbundle.base.field.event.*;
import org.jbundle.base.screen.model.*;
import org.jbundle.base.screen.model.util.*;
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import com.tourgeek.tour.genled.db.*;
import org.jbundle.base.screen.model.report.*;
import com.tourgeek.tour.genled.db.finstmt.*;
import com.tourgeek.tour.genled.finstmt.screen.*;
import org.jbundle.main.db.*;

/**
 *  FinStmtDetailReportScreen - The Detail for the financial statement.
 */
public class FinStmtDetailReportScreen extends ReportScreen
{
    /**
     * Default constructor.
     */
    public FinStmtDetailReportScreen()
    {
        super();
    }
    /**
     * Constructor.
     * @param record The main record for this screen.
     * @param itsLocation The location of this component within the parent.
     * @param parentScreen The parent screen.
     * @param fieldConverter The field this screen field is linked to.
     * @param iDisplayFieldDesc Do I display the field desc?
     * @param properties Addition properties to pass to the screen.
     */
    public FinStmtDetailReportScreen(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
    {
        this();
        this.init(record, itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, properties);
    }
    /**
     * Initialize class fields.
     */
    public void init(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
    {
        super.init(record, itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, properties);
    }
    /**
     * Get the screen display title.
     */
    public String getTitle()
    {
        return "The Detail for the financial statement";
    }
    /**
     * Get the main record for this screen.
     * @return The main record.
     */
    public Record getMainRecord()
    {
        return this.getRecord(FinStmtDetail.FIN_STMT_DETAIL_FILE);
    }
    /**
     * SetupSFields Method.
     */
    public void setupSFields()
    {
        this.getRecord(FinStmtDetail.FIN_STMT_DETAIL_FILE).getField(FinStmtDetail.ACCOUNT_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(FinStmtDetail.FIN_STMT_DETAIL_FILE).getField(FinStmtDetail.ACCOUNT_DESC).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(FinStmtDetail.FIN_STMT_DETAIL_FILE).getField(FinStmtDetail.INDENT).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(FinStmtDetail.FIN_STMT_DETAIL_FILE).getField(FinStmtDetail.INVISIBLE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(FinStmtDetail.FIN_STMT_DETAIL_FILE).getField(FinStmtDetail.TYPICAL_BALANCE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(FinStmtDetail.FIN_STMT_DETAIL_FILE).getField(FinStmtDetail.SUB_TOTAL_LEVEL).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(FinStmtDetail.FIN_STMT_DETAIL_FILE).getField(FinStmtDetail.DATA_COLUMN).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(FinStmtDetail.FIN_STMT_DETAIL_FILE).getField(FinStmtDetail.SPECIAL_FORMAT).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(FinStmtDetail.FIN_STMT_DETAIL_FILE).getField(FinStmtDetail.NUMBER_FORMAT).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(FinStmtDetail.FIN_STMT_DETAIL_FILE).getField(FinStmtDetail.SPECIAL_FUNCTION).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(FinStmtReportScreenRecord.FIN_STMT_REPORT_SCREEN_RECORD_FILE).getField(FinStmtReportScreenRecord.START_BALANCE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(FinStmtReportScreenRecord.FIN_STMT_REPORT_SCREEN_RECORD_FILE).getField(FinStmtReportScreenRecord.BALANCE_CHANGE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(FinStmtReportScreenRecord.FIN_STMT_REPORT_SCREEN_RECORD_FILE).getField(FinStmtReportScreenRecord.END_BALANCE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(FinStmtReportScreenRecord.FIN_STMT_REPORT_SCREEN_RECORD_FILE).getField(FinStmtReportScreenRecord.TARGET_AMOUNT).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(FinStmtReportScreenRecord.FIN_STMT_REPORT_SCREEN_RECORD_FILE).getField(FinStmtReportScreenRecord.RATIO_PERCENT).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
    }

}

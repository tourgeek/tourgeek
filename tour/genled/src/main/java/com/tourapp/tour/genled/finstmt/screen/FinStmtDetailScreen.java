/**
 * @(#)FinStmtDetailScreen.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.genled.finstmt.screen;

import java.awt.*;
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
import com.tourapp.tour.genled.db.*;

/**
 *  FinStmtDetailScreen - Financial Statement Detail.
 */
public class FinStmtDetailScreen extends Screen
{
    /**
     * Default constructor.
     */
    public FinStmtDetailScreen()
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
    public FinStmtDetailScreen(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
        return "Financial Statement Detail";
    }
    /**
     * Override this to open the main file.
     * <p />You should pass this record owner to the new main file (ie., new MyNewTable(thisRecordOwner)).
     * @return The new record.
     */
    public Record openMainRecord()
    {
        return new FinStmtDetail(this);
    }
    /**
     * Override this to open the other files in the query.
     */
    public void openOtherRecords()
    {
        super.openOtherRecords();
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
    }
    /**
     * Set up all the screen fields.
     */
    public void setupSFields()
    {
        this.getRecord(FinStmtDetail.FIN_STMT_DETAIL_FILE).getField(FinStmtDetail.SEQUENCE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(FinStmtDetail.FIN_STMT_DETAIL_FILE).getField(FinStmtDetail.ACCOUNT_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        Converter converter = new AccountDescConverter(this.getRecord(FinStmtDetail.FIN_STMT_DETAIL_FILE).getField(FinStmtDetail.ACCOUNT_DESC), this.getRecord(Account.ACCOUNT_FILE).getField(Account.DESCRIPTION));
        converter.setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(FinStmtDetail.FIN_STMT_DETAIL_FILE).getField(FinStmtDetail.INDENT).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(FinStmtDetail.FIN_STMT_DETAIL_FILE).getField(FinStmtDetail.INVISIBLE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(FinStmtDetail.FIN_STMT_DETAIL_FILE).getField(FinStmtDetail.TYPICAL_BALANCE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(FinStmtDetail.FIN_STMT_DETAIL_FILE).getField(FinStmtDetail.SUB_TOTAL_LEVEL).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(FinStmtDetail.FIN_STMT_DETAIL_FILE).getField(FinStmtDetail.DATA_COLUMN).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(FinStmtDetail.FIN_STMT_DETAIL_FILE).getField(FinStmtDetail.SPECIAL_FORMAT).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(FinStmtDetail.FIN_STMT_DETAIL_FILE).getField(FinStmtDetail.NUMBER_FORMAT).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(FinStmtDetail.FIN_STMT_DETAIL_FILE).getField(FinStmtDetail.SPECIAL_FUNCTION).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        Object bookmark = this.getMainRecord().getField(FinStmtDetail.FIN_STMT_ID).getData();
        if (bookmark != null)
        {
        FinStmt recFinStmtPopup = new FinStmt(this);
        this.removeRecord(recFinStmtPopup);    // The queryconverter will free this.
        try {
            recFinStmtPopup.setHandle(bookmark, DBConstants.BOOKMARK_HANDLE);
            String strHeaderID = recFinStmtPopup.getField(FinStmt.FIN_STMT_HEADER_ID).toString();
            recFinStmtPopup.setKeyArea(FinStmt.FIN_STMT_HEADER_ID_KEY);
            recFinStmtPopup.addListener(new StringSubFileFilter(strHeaderID, FinStmt.FIN_STMT_HEADER_ID, null, null, null, null));
            ((ReferenceField)this.getMainRecord().getField(FinStmtDetail.FIN_STMT_ID)).setReferenceRecord(null);
            this.getMainRecord().getField(FinStmtDetail.FIN_STMT_ID).setupTablePopup(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, null, ScreenConstants.DEFAULT_DISPLAY, recFinStmtPopup, FinStmt.FIN_STMT_HEADER_ID_KEY, FinStmt.STATEMENT_DESC, false, false);
        } catch (DBException ex)    {
            ex.printStackTrace();
        }
        }
    }

}

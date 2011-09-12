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
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.main.screen.*;
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
        this.getRecord(FinStmtDetail.kFinStmtDetailFile).getField(FinStmtDetail.kSequence).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(FinStmtDetail.kFinStmtDetailFile).getField(FinStmtDetail.kAccountID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        Converter converter = new AccountDescConverter(this.getRecord(FinStmtDetail.kFinStmtDetailFile).getField(FinStmtDetail.kAccountDesc), this.getRecord(Account.kAccountFile).getField(Account.kDescription));
        converter.setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(FinStmtDetail.kFinStmtDetailFile).getField(FinStmtDetail.kIndent).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(FinStmtDetail.kFinStmtDetailFile).getField(FinStmtDetail.kInvisible).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(FinStmtDetail.kFinStmtDetailFile).getField(FinStmtDetail.kTypicalBalance).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(FinStmtDetail.kFinStmtDetailFile).getField(FinStmtDetail.kSubTotalLevel).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(FinStmtDetail.kFinStmtDetailFile).getField(FinStmtDetail.kDataColumn).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(FinStmtDetail.kFinStmtDetailFile).getField(FinStmtDetail.kSpecialFormat).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(FinStmtDetail.kFinStmtDetailFile).getField(FinStmtDetail.kNumberFormat).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(FinStmtDetail.kFinStmtDetailFile).getField(FinStmtDetail.kSpecialFunction).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        Object bookmark = this.getMainRecord().getField(FinStmtDetail.kFinStmtID).getData();
        if (bookmark != null)
        {
        FinStmt recFinStmtPopup = new FinStmt(this);
        this.removeRecord(recFinStmtPopup);    // The queryconverter will free this.
        try {
            recFinStmtPopup.setHandle(bookmark, DBConstants.BOOKMARK_HANDLE);
            String strHeaderID = recFinStmtPopup.getField(FinStmt.kFinStmtHeaderID).toString();
            recFinStmtPopup.setKeyArea(FinStmt.kFinStmtHeaderIDKey);
            recFinStmtPopup.addListener(new StringSubFileFilter(strHeaderID, FinStmt.kFinStmtHeaderID, null, -1, null, -1));
            ((ReferenceField)this.getMainRecord().getField(FinStmtDetail.kFinStmtID)).setReferenceRecord(null);
            this.getMainRecord().getField(FinStmtDetail.kFinStmtID).setupTablePopup(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, null, ScreenConstants.DEFAULT_DISPLAY, recFinStmtPopup, FinStmt.kFinStmtHeaderIDKey, FinStmt.kStatementDesc, false, false);
        } catch (DBException ex)    {
            ex.printStackTrace();
        }
        }
    }

}

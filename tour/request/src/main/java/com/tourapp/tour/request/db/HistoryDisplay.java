/**
 * @(#)HistoryDisplay.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.request.db;

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
import com.tourapp.tour.base.db.shared.*;
import com.tourapp.tour.request.screen.detail.*;
import com.tourapp.model.tour.request.db.*;

/**
 *  HistoryDisplay - Brochure History Display.
 */
public class HistoryDisplay extends QueryRecord
     implements HistoryDisplayModel
{
    private static final long serialVersionUID = 1L;
    /**
     * Default constructor.
     */
    public HistoryDisplay()
    {
        super();
    }
    /**
     * Constructor.
     */
    public HistoryDisplay(RecordOwner screen)
    {
        this();
        this.init(screen);
    }
    /**
     * Initialize class fields.
     */
    public void init(RecordOwner screen)
    {
        super.init(screen);
    }

    public static final String kHistoryDisplayFile = "HistoryDisplay";
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(kHistoryDisplayFile, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "History";
    }
    /**
     * Get the Database Name.
     */
    public String getDatabaseName()
    {
        return "profile";
    }
    /**
     * Is this a local (vs remote) file?.
     */
    public int getDatabaseType()
    {
        return DBConstants.REMOTE | DBConstants.USER_DATA;
    }
    /**
     * Override this to Setup all the records for this query.
     * Only used for querys and abstract-record queries.
     * Actually adds records not tables, but the records aren't physically
     * added here, the record's tables are added to my table.
     * @param The recordOwner to pass to the records that are added.
     * @see addTable.
     */
    public void addTables(RecordOwner recordOwner)
    {
        this.addTable(new RequestHistoryDetail(recordOwner));
        this.addTable(new Brochure(recordOwner));
    }
    /**
     * SelectFields Method.
     */
    public void selectFields()
    {
        RequestHistoryDetail pAmReqHistory = (RequestHistoryDetail)this.getRecord(RequestHistoryDetail.kRequestHistoryDetailFile);
        this.setGridFile(pAmReqHistory, RequestHistoryDetail.kProfileIDKey);
        
        this.setSelected(false);    // de-select all
        super.selectFields();
        //xthis.getField(RequestHistoryDetail.kRequestHistoryDetailFile, RequestHistoryDetail.kUseAgency).setSelected(true);
        this.getField(RequestHistoryDetail.kRequestHistoryDetailFile, RequestHistoryDetail.kProfileID).setSelected(true);
        this.getField(RequestHistoryDetail.kRequestHistoryDetailFile, RequestHistoryDetail.kMailedOn).setSelected(true);
        this.getField(RequestHistoryDetail.kRequestHistoryDetailFile, RequestHistoryDetail.kBrochureQty).setSelected(true);
        this.getField(Brochure.kBrochureFile, Brochure.kDescription).setSelected(true);
        this.getField(RequestHistoryDetail.kRequestHistoryDetailFile, RequestHistoryDetail.kBrochureID).setSelected(true);
    }
    /**
     * SetupRelationships Method.
     */
    public void setupRelationships()
    {
        this.addRelationship(DBConstants.LEFT_INNER, this.getRecord(RequestHistoryDetail.kRequestHistoryDetailFile), this.getRecord(Brochure.kBrochureFile), RequestHistoryDetail.kBrochureID, Brochure.kID);
    }

}

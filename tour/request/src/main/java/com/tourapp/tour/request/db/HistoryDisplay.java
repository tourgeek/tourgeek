/**
 * @(#)HistoryDisplay.
 * Copyright © 2012 tourapp.com. All rights reserved.
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
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
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
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(HISTORY_DISPLAY_FILE, bAddQuotes) : super.getTableNames(bAddQuotes);
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
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        //if (iFieldSeq == 0)
        //{
        //  field = new CounterField(this, ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //  field.setHidden(true);
        //}
        //if (iFieldSeq == 1)
        //{
        //  field = new RecordChangedField(this, LAST_CHANGED, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //  field.setHidden(true);
        //}
        //if (iFieldSeq == 2)
        //{
        //  field = new BooleanField(this, DELETED, Constants.DEFAULT_FIELD_LENGTH, null, new Boolean(false));
        //  field.setHidden(true);
        //}
        if (field == null)
            field = super.setupField(iFieldSeq);
        return field;
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
        RequestHistoryDetail pAmReqHistory = (RequestHistoryDetail)this.getRecord(RequestHistoryDetail.REQUEST_HISTORY_DETAIL_FILE);
        this.setGridFile(pAmReqHistory, RequestHistoryDetail.PROFILE_ID_KEY);
        
        this.setSelected(false);    // de-select all
        super.selectFields();
        //xthis.getField(RequestHistoryDetail.REQUEST_HISTORY_DETAIL_FILE, RequestHistoryDetail.USE_AGENCY).setSelected(true);
        this.getField(RequestHistoryDetail.REQUEST_HISTORY_DETAIL_FILE, RequestHistoryDetail.PROFILE_ID).setSelected(true);
        this.getField(RequestHistoryDetail.REQUEST_HISTORY_DETAIL_FILE, RequestHistoryDetail.MAILED_ON).setSelected(true);
        this.getField(RequestHistoryDetail.REQUEST_HISTORY_DETAIL_FILE, RequestHistoryDetail.BROCHURE_QTY).setSelected(true);
        this.getField(Brochure.BROCHURE_FILE, Brochure.DESCRIPTION).setSelected(true);
        this.getField(RequestHistoryDetail.REQUEST_HISTORY_DETAIL_FILE, RequestHistoryDetail.BROCHURE_ID).setSelected(true);
    }
    /**
     * SetupRelationships Method.
     */
    public void setupRelationships()
    {
        this.addRelationship(DBConstants.LEFT_INNER, this.getRecord(RequestHistoryDetail.REQUEST_HISTORY_DETAIL_FILE), this.getRecord(Brochure.BROCHURE_FILE), RequestHistoryDetail.BROCHURE_ID, Brochure.ID);
    }

}

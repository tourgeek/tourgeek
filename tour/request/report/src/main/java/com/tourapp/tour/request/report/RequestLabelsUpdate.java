/**
  * @(#)RequestLabelsUpdate.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.tour.request.report;

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
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import org.jbundle.base.thread.*;
import com.tourapp.tour.request.db.*;
import java.util.*;

/**
 *  RequestLabelsUpdate - This is the logic to clear the brochure requests printed
and move them to the history file.
Note: This is a process because there is more than one
label printing program.
Note: This is typically not run as a process, but is usually run
directly (by callin.
 */
public class RequestLabelsUpdate extends BaseProcess
{
    /**
     * Default constructor.
     */
    public RequestLabelsUpdate()
    {
        super();
    }
    /**
     * Constructor.
     */
    public RequestLabelsUpdate(RecordOwnerParent taskParent, Record recordMain, Map<String,Object> properties)
    {
        this();
        this.init(taskParent, recordMain, properties);
    }
    /**
     * Initialize class fields.
     */
    public void init(RecordOwnerParent taskParent, Record recordMain, Map<String, Object> properties)
    {
        super.init(taskParent, recordMain, properties);
    }
    /**
     * Open the main file.
     */
    public Record openMainRecord()
    {
        return new Request(this);
    }
    /**
     * Open the other files.
     */
    public void openOtherRecords()
    {
        super.openOtherRecords();
        new RequestDetail(this);
        new RequestHistory(this);
        new RequestHistoryDetail(this);
    }
    /**
     * Add the screen record.
     */
    public Record addScreenRecord()
    {
        return new RequestLabelsScreenRecord(this);
    }
    /**
     * Add the behaviors.
     */
    public void addListeners()
    {
        super.addListeners();
        this.getScreenRecord().getField(RequestLabelsScreenRecord.SEND_VIA_ID).setData(this.getProperty("sendvia"));
        this.getMainRecord().addListener(new CompareFileFilter(this.getMainRecord().getField(Request.SEND_VIA_CODE), this.getScreenRecord().getField(RequestLabelsScreenRecord.SEND_VIA_ID), "="));
        this.getMainRecord().addListener(new CompareFileFilter(this.getMainRecord().getField(Request.PRINT_NOW), this.getScreenRecord().getField(RequestLabelsScreenRecord.TRUE_FIELD), "="));
        this.getRecord(RequestDetail.REQUEST_DETAIL_FILE).addListener(new SubFileFilter(this.getMainRecord()));
        this.getRecord(RequestHistoryDetail.REQUEST_HISTORY_DETAIL_FILE).addListener(new SubFileFilter(this.getRecord(RequestHistory.REQUEST_HISTORY_FILE)));
    }
    /**
     * Run Method.
     */
    public void run()
    {
        Record recRequest = this.getRecord(Request.REQUEST_FILE);
        Record recRequestDetail = this.getRecord(RequestDetail.REQUEST_DETAIL_FILE);
        Record recRequestHistory = this.getRecord(RequestHistory.REQUEST_HISTORY_FILE);
        Record recRequestHistoryDetail = this.getRecord(RequestHistoryDetail.REQUEST_HISTORY_DETAIL_FILE);
        
        try   {
            recRequest.close();
            while (recRequest.hasNext())
            {
                recRequest.next();
                recRequest.edit();
                if (recRequest.getField(Request.HIST_REPRINT).getState() != true)
                {   // Not reprinted = update history
                    recRequestHistory.addNew();
                    recRequestHistory.moveFields(recRequest, Record.MOVE_BY_NAME, true, DBConstants.SCREEN_MOVE, true, false, false);   // Move all fields to the history record
                    recRequestHistory.add();
                    Object bookmark = recRequestHistory.getLastModified(DBConstants.BOOKMARK_HANDLE);
                    recRequestHistory.setHandle(bookmark, DBConstants.BOOKMARK_HANDLE);
            
                    this.updateHistoryDetail(recRequestDetail, recRequestHistoryDetail, recRequest);
                }
                recRequest.remove();
            }
        } catch (DBException ex)    {
            ex.printStackTrace();
        }
    }
    /**
     * UpdateHistoryDetail Method.
     */
    public void updateHistoryDetail(Record recRequestDetail, Record recRequestHistoryDetail, Record recRequest)
    {
        try   {
            recRequestDetail.close();
            while (recRequestDetail.hasNext())
            {
                recRequestDetail.next();
                recRequestDetail.edit();
        
                recRequestHistoryDetail.addNew();
                recRequestHistoryDetail.moveFields(recRequestDetail, Record.MOVE_BY_NAME, true, DBConstants.SCREEN_MOVE, true, false, false);   // Move all fields to the history record
                recRequestHistoryDetail.getField(RequestHistoryDetail.PROFILE_ID).moveFieldToThis(recRequest.getField(Request.PROFILE_ID));
                recRequestHistoryDetail.add();  
        
                recRequestDetail.remove();
            }
        } catch (DBException ex)    {
            ex.printStackTrace();
        }
    }

}

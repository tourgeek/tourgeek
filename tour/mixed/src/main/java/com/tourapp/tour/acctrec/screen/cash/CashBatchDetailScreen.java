/**
 * @(#)CashBatchDetailScreen.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.acctrec.screen.cash;

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
import com.tourapp.tour.acctrec.db.*;
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.product.tour.db.*;
import com.tourapp.tour.product.base.db.*;
import com.tourapp.tour.acctrec.screen.cash.dist.*;

/**
 *  CashBatchDetailScreen - Cash Receipts.
 */
public class CashBatchDetailScreen extends Screen
{
    /**
     * Default constructor.
     */
    public CashBatchDetailScreen()
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
    public CashBatchDetailScreen(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
        return "Cash Receipts";
    }
    /**
     * Override this to open the main file.
     * <p />You should pass this record owner to the new main file (ie., new MyNewTable(thisRecordOwner)).
     * @return The new record.
     */
    public Record openMainRecord()
    {
        return new CashBatchDetail(this);
    }
    /**
     * Override this to open the other files in the query.
     */
    public void openOtherRecords()
    {
        super.openOtherRecords();
        
        new CashBatch(this);
        
        new ArControl(this);
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        ((ReferenceField)this.getRecord(CashBatchDetail.CASH_BATCH_DETAIL_FILE).getField(CashBatchDetail.CASH_BATCH_ID)).setReferenceRecord(this.getRecord(CashBatch.CASH_BATCH_FILE));
        ((ReferenceField)this.getRecord(CashBatchDetail.CASH_BATCH_DETAIL_FILE).getField(CashBatchDetail.CASH_BATCH_ID)).getReference();
        this.getRecord(CashBatchDetail.CASH_BATCH_DETAIL_FILE).setKeyArea(CashBatchDetail.CASH_BATCH_ID_KEY);
        this.getRecord(CashBatchDetail.CASH_BATCH_DETAIL_FILE).addListener(new SubFileFilter(this.getRecord(CashBatch.CASH_BATCH_FILE)));
        this.getRecord(CashBatch.CASH_BATCH_FILE).addListener(new UpdateOnCloseHandler(null));
        this.getRecord(CashBatchDetail.CASH_BATCH_DETAIL_FILE).addListener(new SubCountHandler(this.getRecord(CashBatch.CASH_BATCH_FILE).getField(CashBatch.BATCH_CHECKS_ACTUAL), false, true));
        this.getRecord(CashBatchDetail.CASH_BATCH_DETAIL_FILE).addListener(new SubCountHandler(this.getRecord(CashBatch.CASH_BATCH_FILE).getField(CashBatch.BATCH_TOTAL_ACTUAL), CashBatchDetail.AMOUNT, false, true));
        
        this.getRecord(CashBatchDetail.CASH_BATCH_DETAIL_FILE).getField(CashBatchDetail.BOOKING_ID).addListener(new BookingDefaultHandler(null));
    }
    /**
     * Set up all the screen fields.
     */
    public void setupSFields()
    {
        Converter converter = this.getRecord(CashBatchDetail.CASH_BATCH_DETAIL_FILE).getField(CashBatchDetail.BOOKING_ID);
        converter = new CashDistConverter(converter);
        converter.setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(CashBatchDetail.kCashBatchDetailFile).getField(CashBatchDetail.kAmount).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(CashBatchDetail.kCashBatchDetailFile).getField(CashBatchDetail.kComments).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(CashBatchDetail.kCashBatchDetailFile).getField(CashBatchDetail.kCheckNo).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(CashBatchDetail.kCashBatchDetailFile).getField(CashBatchDetail.kCheckABA).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
    }
    /**
     * Add button(s) to the toolbar.
     */
    public void addToolbarButtons(ToolScreen toolScreen)
    {
        String strDesc = "Distribution";
        if (this.getTask() != null)
            strDesc = ((BaseApplication)this.getTask().getApplication()).getResources(ResourceConstants.ACCTREC_RESOURCE, true).getString(strDesc);
        new SCannedBox(toolScreen.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), toolScreen, null, ScreenConstants.DEFAULT_DISPLAY, null, strDesc, MenuConstants.FORMDETAIL, MenuConstants.FORMDETAIL, null);
        new SCannedBox(toolScreen.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), toolScreen, null, ScreenConstants.DEFAULT_DISPLAY, null, MenuConstants.POST, MenuConstants.POST, MenuConstants.POST, null);
    }

}

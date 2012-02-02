/**
 * @(#)TrxTypeScreen.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.genled.screen.trx;

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
import org.jbundle.main.screen.*;
import com.tourapp.tour.genled.db.*;

/**
 *  TrxTypeScreen - Transaction type.
 */
public class TrxTypeScreen extends DetailScreen
{
    /**
     * Default constructor.
     */
    public TrxTypeScreen()
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
    public TrxTypeScreen(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
        return "Transaction type";
    }
    /**
     * Override this to open the main file.
     * <p />You should pass this record owner to the new main file (ie., new MyNewTable(thisRecordOwner)).
     * @return The new record.
     */
    public Record openMainRecord()
    {
        return new TransactionType(this);
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        for (int i = 0; ;i++)
        {
            ScreenField sField = (ScreenField)this.getRecord(TransactionType.TRANSACTION_TYPE_FILE).getField(TransactionType.SOURCE_TRX_STATUS_ID).getComponent(i);
            if (sField == null)
                break;
            if (sField instanceof SCannedBox)
            {
                String strCommand = Utility.addURLParam(null, DBParams.COMMAND, ((SCannedBox)sField).getButtonCommand());
                BaseField field = this.getRecord(TransactionType.TRANSACTION_TYPE_FILE).getField(TransactionType.TRX_DESC_ID);
                strCommand = Utility.addURLParam(strCommand, field.getFieldName(), field.toString());
                field = this.getRecord(TransactionType.TRANSACTION_TYPE_FILE).getField(TransactionType.TRX_SYSTEM_ID);
                strCommand = Utility.addURLParam(strCommand, field.getFieldName(), field.toString());
                ((SCannedBox)sField).setButtonCommand(strCommand);
            }
        } 
        
        Record recTrxStatus = ((ReferenceField)this.getMainRecord().getField(TransactionType.SOURCE_TRX_STATUS_ID)).getReferenceRecord(this);
        this.getMainRecord().getField(TransactionType.SOURCE_TRX_STATUS_ID).addListener(new MoveOnChangeHandler(this.getMainRecord().getField(TransactionType.SOURCE_PREFERRED_SIGN), recTrxStatus.getField(TrxStatus.PREFERRED_SIGN)));
        this.getMainRecord().getField(TransactionType.SOURCE_TRX_STATUS_ID).addListener(new MoveOnChangeHandler(this.getMainRecord().getField(TransactionType.SOURCE_TRX_DESC_ID), recTrxStatus.getField(TrxStatus.TRX_DESC_ID)));
    }
    /**
     * Open the header record.
     * @return The new header record.
     */
    public Record openHeaderRecord()
    {
        return new TrxGroup(this);
    }
    /**
     * Set up all the screen fields.
     */
    public void setupSFields()
    {
        this.getRecord(TransactionType.kTransactionTypeFile).getField(TransactionType.kTypeCode).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TransactionType.kTransactionTypeFile).getField(TransactionType.kTypeDesc).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TransactionType.kTransactionTypeFile).getField(TransactionType.kTypicalBalance).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TransactionType.kTransactionTypeFile).getField(TransactionType.kSourceFile).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TransactionType.kTransactionTypeFile).getField(TransactionType.kSourceTrxStatusID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TransactionType.kTransactionTypeFile).getField(TransactionType.kAmountField).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TransactionType.kTransactionTypeFile).getField(TransactionType.kTrxDateField).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TransactionType.kTransactionTypeFile).getField(TransactionType.kEntryDateField).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TransactionType.kTransactionTypeFile).getField(TransactionType.kUserIDField).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TransactionType.kTransactionTypeFile).getField(TransactionType.kTrxIDField).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TransactionType.kTransactionTypeFile).getField(TransactionType.kAccountIDFile).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TransactionType.kTransactionTypeFile).getField(TransactionType.kAccountIDField).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
    }
    /**
     * Make a sub-screen.
     * @return the new sub-screen.
     */
    public BasePanel makeSubScreen()
    {
        return new TrxGroupHeaderScreen(null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, null);
    }

}

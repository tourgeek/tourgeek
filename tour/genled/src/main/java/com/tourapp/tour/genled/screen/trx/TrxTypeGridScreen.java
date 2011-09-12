/**
 * @(#)TrxTypeGridScreen.
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
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.main.screen.*;
import com.tourapp.tour.genled.db.*;

/**
 *  TrxTypeGridScreen - Transaction type.
 */
public class TrxTypeGridScreen extends DetailGridScreen
{
    /**
     * Default constructor.
     */
    public TrxTypeGridScreen()
    {
        super();
    }
    /**
     * Constructor.
     * @param record The main record for this screen.
     * @param itsLocation The location of this component within the parent.
     * @param parentScreen The parent screen.
     * @param fieldConverter The field this screen field is linked to.
     * @param iDisplayFieldDesc Do I display the field desc?.
     */
    public TrxTypeGridScreen(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
     * TrxTypeGridScreen Method.
     */
    public TrxTypeGridScreen(Record recMain, Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object>
     properties)
    {
        this();
        this.init(recMain, record, itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, properties);
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
     * OpenHeaderRecord Method.
     */
    public Record openHeaderRecord()
    {
        return new TrxGroup(this);
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        this.getMainRecord().addListener(new SubFileFilter(this.getHeaderRecord()));
        
        this.getMainRecord().addListener(new UpdateTrxTypeHandler(null));
        
        for (int i = 0; ;i++)
        {
            ScreenField sField = (ScreenField)this.getRecord(TransactionType.kTransactionTypeFile).getField(TransactionType.kSourceTrxStatusID).getComponent(i);
            if (sField == null)
                break;
            if (sField instanceof SCannedBox)
            {
                String strCommand = Utility.addURLParam(null, DBParams.COMMAND, ((SCannedBox)sField).getButtonCommand());
                BaseField field = this.getRecord(TransactionType.kTransactionTypeFile).getField(TransactionType.kTrxDescID);
                BaseField fldSrc = field;
                if (field.isNull())
                    fldSrc = this.getRecord(TrxGroup.kTrxGroupFile).getField(TrxGroup.kID);
                strCommand = Utility.addURLParam(strCommand, field.getFieldName(), fldSrc.toString());
                field = this.getRecord(TransactionType.kTransactionTypeFile).getField(TransactionType.kTrxSystemID);
                strCommand = Utility.addURLParam(strCommand, field.getFieldName(), field.toString());
                ((SCannedBox)sField).setButtonCommand(strCommand);
            }
        }
        
        Record recTrxStatus = ((ReferenceField)this.getMainRecord().getField(TransactionType.kSourceTrxStatusID)).getReferenceRecord(this);
        this.getMainRecord().getField(TransactionType.kSourceTrxStatusID).addListener(new MoveOnChangeHandler(this.getMainRecord().getField(TransactionType.kSourcePreferredSign), recTrxStatus.getField(TrxStatus.kPreferredSign)));
        this.getMainRecord().getField(TransactionType.kSourceTrxStatusID).addListener(new MoveOnChangeHandler(this.getMainRecord().getField(TransactionType.kSourceTrxDescID), recTrxStatus.getField(TrxStatus.kTrxDescID)));
    }
    /**
     * SetupSFields Method.
     */
    public void setupSFields()
    {
        this.getRecord(TransactionType.kTransactionTypeFile).getField(TransactionType.kTypeCode).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TransactionType.kTransactionTypeFile).getField(TransactionType.kTypeDesc).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TransactionType.kTransactionTypeFile).getField(TransactionType.kTypicalBalance).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TransactionType.kTransactionTypeFile).getField(TransactionType.kPostingType).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TransactionType.kTransactionTypeFile).getField(TransactionType.kSourceTrxStatusID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
    }
    /**
     * Make a sub-screen.
     * @return the new sub-screen.
     */
    public BasePanel makeSubScreen()
    {
        return new TrxGroupHeaderScreen(null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC);
    }

}

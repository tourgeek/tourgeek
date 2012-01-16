/**
 * @(#)BankTrxBatchDetailScreen.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.assetdr.screen.batch;

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
import com.tourapp.tour.assetdr.db.*;
import com.tourapp.tour.base.db.*;
import org.jbundle.main.screen.*;
import com.tourapp.tour.assetdr.screen.trx.*;
import com.tourapp.tour.assetdr.screen.batch.dist.*;
import com.tourapp.tour.genled.db.*;

/**
 *  BankTrxBatchDetailScreen - .
 */
public class BankTrxBatchDetailScreen extends DetailScreen
{
    /**
     * Default constructor.
     */
    public BankTrxBatchDetailScreen()
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
    public BankTrxBatchDetailScreen(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
     * Override this to open the main file.
     * <p />You should pass this record owner to the new main file (ie., new MyNewTable(thisRecordOwner)).
     * @return The new record.
     */
    public Record openMainRecord()
    {
        return new BankTrxBatchDetail(this);
    }
    /**
     * Open the header record.
     * @return The new header record.
     */
    public Record openHeaderRecord()
    {
        return new BankTrxBatch(this);
    }
    /**
     * Override this to open the other files in the query.
     */
    public void openOtherRecords()
    {
        super.openOtherRecords();
        new Currencys(this);
        new AssetDrControl(this);
    }
    /**
     * Add the screen fields.
     * Override this to create (and return) the screen record for this recordowner.
     * @return The screen record.
     */
    public Record addScreenRecord()
    {
        return new BankTrxScreenRecord(this);
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        
        Record recAssetDrControl = this.getRecord(AssetDrControl.kAssetDrControlFile);
        this.getMainRecord().getField(BankTrxBatchDetail.kTrxStatusID).addListener(new InitFieldHandler(recAssetDrControl.getField(AssetDrControl.kTrxStatusID)));
        
        this.getMainRecord().setKeyArea(BankTrxBatchDetail.kBankTrxBatchIDKey);
        this.getMainRecord().addListener(new SubFileFilter(this.getRecord(BankTrxBatch.kBankTrxBatchFile)));
        
        Record recCurrencys = this.getRecord(Currencys.kCurrencysFile);
        Record recBankTrxBatchDetail = this.getRecord(BankTrxBatchDetail.kBankTrxBatchDetailFile);
        BankAcct recBankAcct = (BankAcct)((ReferenceField)this.getRecord(BankTrxBatch.kBankTrxBatchFile).getField(BankTrxBatch.kBankAcctID)).getReferenceRecord();
        this.getRecord(BankTrxBatch.kBankTrxBatchFile).getField(BankTrxBatch.kBankAcctID).addListener(new ReadSecondaryHandler(recBankAcct));
        recBankAcct.getField(BankAcct.kCurrencyID).addListener(new ReadSecondaryHandler(recCurrencys));
        
        recBankTrxBatchDetail.getField(BankTrxBatchDetail.kBankAcctID).addListener(new InitFieldHandler(this.getRecord(BankTrxBatch.kBankTrxBatchFile).getField(BankTrxBatch.kBankAcctID)));
        recBankTrxBatchDetail.getField(BankTrxBatchDetail.kExchange).addListener(new InitFieldHandler(recCurrencys.getField(Currencys.kLastRate)));
        recBankTrxBatchDetail.getField(BankTrxBatchDetail.kAmount).addListener(new CalcBalanceHandler(recBankTrxBatchDetail.getField(BankTrxBatchDetail.kAmountLocal), recBankTrxBatchDetail.getField(BankTrxBatchDetail.kAmount), recBankTrxBatchDetail.getField(BankTrxBatchDetail.kExchange), CalcBalanceHandler.MULTIPLY, false));
        recBankTrxBatchDetail.getField(BankTrxBatchDetail.kExchange).addListener(new InitOnceFieldHandler(false));
        recBankTrxBatchDetail.getField(BankTrxBatchDetail.kExchange).addListener(new CalcBalanceHandler(recBankTrxBatchDetail.getField(BankTrxBatchDetail.kAmountLocal), recBankTrxBatchDetail.getField(BankTrxBatchDetail.kAmount), recBankTrxBatchDetail.getField(BankTrxBatchDetail.kExchange), CalcBalanceHandler.MULTIPLY, false));
        recBankTrxBatchDetail.getField(BankTrxBatchDetail.kAmountLocal).addListener(new CalcBalanceHandler(recBankTrxBatchDetail.getField(BankTrxBatchDetail.kExchange), recBankTrxBatchDetail.getField(BankTrxBatchDetail.kAmountLocal), recBankTrxBatchDetail.getField(BankTrxBatchDetail.kAmount), CalcBalanceHandler.DIVIDE, false));
        
        recBankTrxBatchDetail.getField(BankTrxBatchDetail.kAmount).addListener(new DisableOnSignHandler(recBankTrxBatchDetail.getField(BankTrxBatchDetail.kAmountLocal), DisableOnSignHandler.NEGATIVE));
        recBankTrxBatchDetail.getField(BankTrxBatchDetail.kAmount).addListener(new DisableOnSignHandler(recBankTrxBatchDetail.getField(BankTrxBatchDetail.kExchange), DisableOnSignHandler.NEGATIVE));
        
        this.getRecord(BankTrxBatchDetail.kBankTrxBatchDetailFile).getField(BankTrxBatchDetail.kPayeeName).addListener(new FocusOnCheckAmount(null));
    }
    /**
     * Set up all the screen fields.
     */
    public void setupSFields()
    {
        this.getRecord(BankTrxBatchDetail.kBankTrxBatchDetailFile).getField(BankTrxBatchDetail.kBankAcctID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(BankTrxBatchDetail.kBankTrxBatchDetailFile).getField(BankTrxBatchDetail.kTrxStatusID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(BankTrxBatchDetail.kBankTrxBatchDetailFile).getField(BankTrxBatchDetail.kTrxNumber).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(BankTrxBatchDetail.kBankTrxBatchDetailFile).getField(BankTrxBatchDetail.kTrxDate).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(BankTrxBatchDetail.kBankTrxBatchDetailFile).getField(BankTrxBatchDetail.kPayeeName).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        Converter convAmount = this.getRecord(BankTrxBatchDetail.kBankTrxBatchDetailFile).getField(BankTrxBatchDetail.kAmount);
        DrCrConverter converter2 = new DrCrConverter(convAmount, false);
        String strDesc = "Check amount";
        if (this.getTask() != null)
            strDesc = ((BaseApplication)this.getTask().getApplication()).getResources(ResourceConstants.ASSETDR_RESOURCE, true).getString(strDesc);
        converter2.setCreditDesc(strDesc);
        converter2.setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        converter2 = new DrCrConverter(convAmount, true);
        strDesc = "Deposit amount";
        if (this.getTask() != null)
            strDesc = ((BaseApplication)this.getTask().getApplication()).getResources(ResourceConstants.ASSETDR_RESOURCE, true).getString(strDesc);
        converter2.setDebitDesc(strDesc);
        converter2.setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(BankTrxBatchDetail.kBankTrxBatchDetailFile).getField(BankTrxBatchDetail.kExchange).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(BankTrxBatchDetail.kBankTrxBatchDetailFile).getField(BankTrxBatchDetail.kAmountLocal).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(BankTrxBatchDetail.kBankTrxBatchDetailFile).getField(BankTrxBatchDetail.kComments).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(BankTrxBatchDetail.kBankTrxBatchDetailFile).getField(BankTrxBatchDetail.kEFTTrxNo).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        Converter converter = this.getRecord(BankTrxBatchDetail.kBankTrxBatchDetailFile).getField(BankTrxBatchDetail.kDistributionDisplay);
        converter = new DistributionConverter(converter);
        converter.setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
    }
    /**
     * Add button(s) to the toolbar.
     */
    public void addToolbarButtons(ToolScreen toolScreen)
    {
        String strDesc = "Distribution";
        if (this.getTask() != null)
            strDesc = ((BaseApplication)this.getTask().getApplication()).getResources(ResourceConstants.ASSETDR_RESOURCE, true).getString(strDesc);
        new SCannedBox(toolScreen.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), toolScreen, null, ScreenConstants.DEFAULT_DISPLAY, null, strDesc, MenuConstants.FORMDETAIL, MenuConstants.FORMDETAIL, null);
        new SCannedBox(toolScreen.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), toolScreen, null, ScreenConstants.DEFAULT_DISPLAY, null, MenuConstants.POST, MenuConstants.POST, MenuConstants.POST, null);
    }

}

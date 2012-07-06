/**
  * @(#)BankTrxBatchDetailScreen.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
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
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
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
        
        Record recAssetDrControl = this.getRecord(AssetDrControl.ASSET_DR_CONTROL_FILE);
        this.getMainRecord().getField(BankTrxBatchDetail.TRX_STATUS_ID).addListener(new InitFieldHandler(recAssetDrControl.getField(AssetDrControl.TRX_STATUS_ID)));
        
        this.getMainRecord().setKeyArea(BankTrxBatchDetail.BANK_TRX_BATCH_ID_KEY);
        this.getMainRecord().addListener(new SubFileFilter(this.getRecord(BankTrxBatch.BANK_TRX_BATCH_FILE)));
        
        Record recCurrencys = this.getRecord(Currencys.CURRENCYS_FILE);
        Record recBankTrxBatchDetail = this.getRecord(BankTrxBatchDetail.BANK_TRX_BATCH_DETAIL_FILE);
        BankAcct recBankAcct = (BankAcct)((ReferenceField)this.getRecord(BankTrxBatch.BANK_TRX_BATCH_FILE).getField(BankTrxBatch.BANK_ACCT_ID)).getReferenceRecord();
        this.getRecord(BankTrxBatch.BANK_TRX_BATCH_FILE).getField(BankTrxBatch.BANK_ACCT_ID).addListener(new ReadSecondaryHandler(recBankAcct));
        recBankAcct.getField(BankAcct.CURRENCY_ID).addListener(new ReadSecondaryHandler(recCurrencys));
        
        recBankTrxBatchDetail.getField(BankTrxBatchDetail.BANK_ACCT_ID).addListener(new InitFieldHandler(this.getRecord(BankTrxBatch.BANK_TRX_BATCH_FILE).getField(BankTrxBatch.BANK_ACCT_ID)));
        recBankTrxBatchDetail.getField(BankTrxBatchDetail.EXCHANGE).addListener(new InitFieldHandler(recCurrencys.getField(Currencys.LAST_RATE)));
        recBankTrxBatchDetail.getField(BankTrxBatchDetail.AMOUNT).addListener(new CalcBalanceHandler(recBankTrxBatchDetail.getField(BankTrxBatchDetail.AMOUNT_LOCAL), recBankTrxBatchDetail.getField(BankTrxBatchDetail.AMOUNT), recBankTrxBatchDetail.getField(BankTrxBatchDetail.EXCHANGE), CalcBalanceHandler.MULTIPLY, false));
        recBankTrxBatchDetail.getField(BankTrxBatchDetail.EXCHANGE).addListener(new InitOnceFieldHandler(false));
        recBankTrxBatchDetail.getField(BankTrxBatchDetail.EXCHANGE).addListener(new CalcBalanceHandler(recBankTrxBatchDetail.getField(BankTrxBatchDetail.AMOUNT_LOCAL), recBankTrxBatchDetail.getField(BankTrxBatchDetail.AMOUNT), recBankTrxBatchDetail.getField(BankTrxBatchDetail.EXCHANGE), CalcBalanceHandler.MULTIPLY, false));
        recBankTrxBatchDetail.getField(BankTrxBatchDetail.AMOUNT_LOCAL).addListener(new CalcBalanceHandler(recBankTrxBatchDetail.getField(BankTrxBatchDetail.EXCHANGE), recBankTrxBatchDetail.getField(BankTrxBatchDetail.AMOUNT_LOCAL), recBankTrxBatchDetail.getField(BankTrxBatchDetail.AMOUNT), CalcBalanceHandler.DIVIDE, false));
        
        recBankTrxBatchDetail.getField(BankTrxBatchDetail.AMOUNT).addListener(new DisableOnSignHandler(recBankTrxBatchDetail.getField(BankTrxBatchDetail.AMOUNT_LOCAL), DisableOnSignHandler.NEGATIVE));
        recBankTrxBatchDetail.getField(BankTrxBatchDetail.AMOUNT).addListener(new DisableOnSignHandler(recBankTrxBatchDetail.getField(BankTrxBatchDetail.EXCHANGE), DisableOnSignHandler.NEGATIVE));
        
        this.getRecord(BankTrxBatchDetail.BANK_TRX_BATCH_DETAIL_FILE).getField(BankTrxBatchDetail.PAYEE_NAME).addListener(new FocusOnCheckAmount(null));
    }
    /**
     * Set up all the screen fields.
     */
    public void setupSFields()
    {
        this.getRecord(BankTrxBatchDetail.BANK_TRX_BATCH_DETAIL_FILE).getField(BankTrxBatchDetail.BANK_ACCT_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(BankTrxBatchDetail.BANK_TRX_BATCH_DETAIL_FILE).getField(BankTrxBatchDetail.TRX_STATUS_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(BankTrxBatchDetail.BANK_TRX_BATCH_DETAIL_FILE).getField(BankTrxBatchDetail.TRX_NUMBER).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(BankTrxBatchDetail.BANK_TRX_BATCH_DETAIL_FILE).getField(BankTrxBatchDetail.TRX_DATE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(BankTrxBatchDetail.BANK_TRX_BATCH_DETAIL_FILE).getField(BankTrxBatchDetail.PAYEE_NAME).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        Converter convAmount = this.getRecord(BankTrxBatchDetail.BANK_TRX_BATCH_DETAIL_FILE).getField(BankTrxBatchDetail.AMOUNT);
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
        this.getRecord(BankTrxBatchDetail.BANK_TRX_BATCH_DETAIL_FILE).getField(BankTrxBatchDetail.EXCHANGE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(BankTrxBatchDetail.BANK_TRX_BATCH_DETAIL_FILE).getField(BankTrxBatchDetail.AMOUNT_LOCAL).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(BankTrxBatchDetail.BANK_TRX_BATCH_DETAIL_FILE).getField(BankTrxBatchDetail.COMMENTS).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(BankTrxBatchDetail.BANK_TRX_BATCH_DETAIL_FILE).getField(BankTrxBatchDetail.EFT_TRX_NO).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        Converter converter = this.getRecord(BankTrxBatchDetail.BANK_TRX_BATCH_DETAIL_FILE).getField(BankTrxBatchDetail.DISTRIBUTION_DISPLAY);
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

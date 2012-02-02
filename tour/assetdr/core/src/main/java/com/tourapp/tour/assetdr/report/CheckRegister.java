/**
 * @(#)CheckRegister.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.assetdr.report;

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
import org.jbundle.base.screen.model.report.*;
import com.tourapp.tour.assetdr.db.*;
import com.tourapp.tour.genled.db.*;

/**
 *  CheckRegister - Check register.
 */
public class CheckRegister extends ReportScreen
{
    /**
     * Default constructor.
     */
    public CheckRegister()
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
    public CheckRegister(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
        return "Check register";
    }
    /**
     * Override this to open the main file.
     * <p />You should pass this record owner to the new main file (ie., new MyNewTable(thisRecordOwner)).
     * @return The new record.
     */
    public Record openMainRecord()
    {
        return new BankTrx(this);
    }
    /**
     * Override this to open the other files in the query.
     */
    public void openOtherRecords()
    {
        super.openOtherRecords();
        new AssetDrControl(this);
    }
    /**
     * Add the screen fields.
     * Override this to create (and return) the screen record for this recordowner.
     * @return The screen record.
     */
    public Record addScreenRecord()
    {
        return new BankAcctScreenRecord(this);
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        this.getScreenRecord().getField(BankAcctScreenRecord.BANK_ACCT_ID).addListener(new InitFieldHandler(this.getRecord(AssetDrControl.ASSET_DR_CONTROL_FILE).getField(AssetDrControl.BANK_ACCT_ID)));
        
        this.getMainRecord().setKeyArea(BankTrx.TRX_DATE_KEY);
        this.getMainRecord().addListener(new SubFileFilter(this.getScreenRecord().getField(BankAcctScreenRecord.BANK_ACCT_ID), BankTrx.BANK_ACCT_ID, null, null, null, null));
        if (!this.isPrintReport())
        {
            Period recPeriod = new Period(this);
            recPeriod.setPeriodDefaults(this.getScreenRecord(), BankAcctScreenRecord.START_DATE, BankAcctScreenRecord.END_DATE, null);
            recPeriod.free();
        }
        else
        {        // Calc start balance
            this.getMainRecord().addListener(new CompareFileFilter(BankTrx.TRX_DATE, this.getScreenRecord().getField(BankAcctScreenRecord.START_DATE), "<", null, false));
            this.getMainRecord().addListener(new SubCountHandler(this.getScreenRecord().getField(BankAcctScreenRecord.START_BALANCE), BankTrx.AMOUNT, false, true));
            try {
                this.getMainRecord().close();
                while (this.getMainRecord().hasNext())
                {
                    this.getMainRecord().next();
                }
            } catch (DBException ex)    {
                ex.printStackTrace();
            } finally   {
                this.getMainRecord().removeListener(this.getMainRecord().getListener(CompareFileFilter.class.getName()), true);
                this.getMainRecord().removeListener(this.getMainRecord().getListener(SubCountHandler.class.getName()), true);
            }
        }
        
        this.getMainRecord().addListener(new ExtractRangeFilter(BankTrx.TRX_DATE, this.getScreenRecord().getField(BankAcctScreenRecord.START_DATE), this.getScreenRecord().getField(BankAcctScreenRecord.END_DATE), ExtractRangeFilter.PAD_END_FIELD));
        
        this.getMainRecord().addListener(new SubCountHandler(this.getScreenRecord().getField(BankAcctScreenRecord.COUNT), false, true));
        this.getMainRecord().addListener(new SubCountHandler(this.getScreenRecord().getField(BankAcctScreenRecord.BALANCE), BankTrx.AMOUNT, false, true));
        
        this.getScreenRecord().getField(BankAcctScreenRecord.BALANCE).addListener(new CalcBalanceHandler(this.getScreenRecord().getField(BankAcctScreenRecord.END_BALANCE), this.getScreenRecord().getField(BankAcctScreenRecord.START_BALANCE), this.getScreenRecord().getField(BankAcctScreenRecord.BALANCE), CalcBalanceHandler.PLUS, false));
    }
    /**
     * Add the toolbars that belong with this screen.
     * @return The new toolbar.
     */
    public ToolScreen addToolbars()
    {
        return new CheckRegisterToolbar(null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, null);
    }
    /**
     * Get the Heading for this report.
     */
    public BaseScreen addReportHeading()
    {
        return new CheckRegisterHeading(null, null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, null);
    }
    /**
     * Get the footing for this report.
     */
    public BaseScreen addReportFooting()
    {
        return new CheckRegisterFooting(null, null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, null);
    }
    /**
     * SetupSFields Method.
     */
    public void setupSFields()
    {
        this.getRecord(BankTrx.kBankTrxFile).getField(BankTrx.kTrxStatusID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(BankTrx.kBankTrxFile).getField(BankTrx.kTrxNumber).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(BankTrx.kBankTrxFile).getField(BankTrx.kTrxDate).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(BankTrx.kBankTrxFile).getField(BankTrx.kPayeeName).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(BankTrx.kBankTrxFile).getField(BankTrx.kAmount).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(BankTrx.kBankTrxFile).getField(BankTrx.kComments).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
    }

}

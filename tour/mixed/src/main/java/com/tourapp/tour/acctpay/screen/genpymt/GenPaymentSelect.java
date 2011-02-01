/**
 *  @(#)GenPaymentSelect.
 *  Copyright © 2010 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.acctpay.screen.genpymt;

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
import org.jbundle.base.screen.model.report.*;
import com.tourapp.tour.acctpay.db.*;
import com.tourapp.tour.genled.db.*;
import com.tourapp.tour.assetdr.db.*;

/**
 *  GenPaymentSelect - Generalized payment selection.
 */
public class GenPaymentSelect extends ReportScreen
{
    /**
     * Default constructor.
     */
    public GenPaymentSelect()
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
    public GenPaymentSelect(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
        return "Generalized payment selection";
    }
    /**
     * Override this to open the main file.
     * <p />You should pass this record owner to the new main file (ie., new MyNewTable(thisRecordOwner)).
     * @return The new record.
     */
    public Record openMainRecord()
    {
        return new Vendor(this);
    }
    /**
     * Override this to open the other files in the query.
     */
    public void openOtherRecords()
    {
        super.openOtherRecords();
        new ApControl(this);
        new ApTrx(this);
        new TrxStatus(this);
        new PaymentRequest(this);
    }
    /**
     * Add the screen fields.
     * Override this to create (and return) the screen record for this recordowner.
     * @return The screen record.
     */
    public Record addScreenRecord()
    {
        return new GenPaymentScreenRecord(this);
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        Record recApControl = this.getRecord(ApControl.kApControlFile);
        this.getScreenRecord().getField(GenPaymentScreenRecord.kPaymentCodeID).addListener(new InitFieldHandler(recApControl.getField(ApControl.kPaymentCodeID)));
        BaseField fldBankAcctID = this.getScreenRecord().getField(GenPaymentScreenRecord.kBankAcctID);
        if (this.getProperty(fldBankAcctID.getFieldName()) != null)
            fldBankAcctID.setString(this.getProperty(fldBankAcctID.getFieldName()), DBConstants.DISPLAY, DBConstants.INIT_MOVE);
        else
            fldBankAcctID.addListener(new InitFieldHandler(recApControl.getField(ApControl.kApBankAcctID)));
        
        Record recVendor = this.getMainRecord();
        recVendor.setKeyArea(Vendor.kNameSortKey);
        recVendor.addListener(new CompareFileFilter(recVendor.getField(Vendor.kPaymentCodeID), this.getScreenRecord().getField(GenPaymentScreenRecord.kPaymentCodeID), "="));
        recVendor.addListener(new CompareFileFilter(recVendor.getField(Vendor.kCurrencysID), (((ReferenceField)((ReferenceField)this.getScreenRecord().getField(GenPaymentScreenRecord.kBankAcctID)).getReference().getField(BankAcct.kCurrencyID))), "="));
        
        Record recApTrx = this.getRecord(ApTrx.kApTrxFile);
        recApTrx.addListener(new SubFileFilter(recVendor));
    }
    /**
     * Add the toolbars that belong with this screen.
     * @return The new toolbar.
     */
    public ToolScreen addToolbars()
    {
        return new GenPaymentToolbar(null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC);
    }
    /**
     * Add button(s) to the toolbar.
     */
    public void addToolbarButtons(ToolScreen toolScreen)
    {
        new SCannedBox(toolScreen.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), toolScreen, null, ScreenConstants.DEFAULT_DISPLAY, null, MenuConstants.SELECT, MenuConstants.SELECT, MenuConstants.SELECT, null);
        super.addToolbarButtons(toolScreen);
    }
    /**
     * SetupSFields Method.
     */
    public void setupSFields()
    {
        this.getRecord(Vendor.kVendorFile).getField(Vendor.kCode).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Vendor.kVendorFile).getField(Vendor.kVendorName).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Vendor.kVendorFile).getField(Vendor.kCurrencysID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
    }
    /**
     * Get the detail screen for this report item (null = none).
     */
    public BaseScreen addReportDetail()
    {
        return new GenPaymentDetail(null, null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, null);
    }
    /**
     * Get the Heading for this report.
     */
    public BaseScreen addReportHeading()
    {
        return new GenPaymentHeading(null, null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, null);
    }
    /**
     * Get the footing for this report.
     */
    public BaseScreen addReportFooting()
    {
        return new GenPaymentFooting(null, null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, null);
    }
    /**
     * Process the command.
     * <br />Step 1 - Process the command if possible and return true if processed.
     * <br />Step 2 - If I can't process, pass to all children (with me as the source).
     * <br />Step 3 - If children didn't process, pass to parent (with me as the source).
     * <br />Note: Never pass to a parent or child that matches the source (to avoid an endless loop).
     * @param strCommand The command to process.
     * @param sourceSField The source screen field (to avoid echos).
     * @param iCommandOptions If this command creates a new screen, create in a new window?
     * @return true if success.
     */
    public boolean doCommand(String strCommand, ScreenField sourceSField, int iCommandOptions)
    {
        if (strCommand.equalsIgnoreCase(MenuConstants.SELECT))
            return this.genSelect();
        else
            return super.doCommand(strCommand, sourceSField, iCommandOptions);
    }
    /**
     * Generalized Selection logic.
     */
    public boolean genSelect()
    {
        Record recVendor = this.getMainRecord();
        Record recApTrx = this.getRecord(ApTrx.kApTrxFile);
        Record recPaymentRequest = this.getRecord(PaymentRequest.kPaymentRequestFile);
        TrxStatus recTrxStatus = (TrxStatus)this.getRecord(TrxStatus.kTrxStatusFile);
        int iPaymentTrxStatus = recTrxStatus.getTrxStatusID(TransactionType.ACCTPAY, ApTrx.kApTrxFile, ApTrx.PAYMENT);
        try   {
            recVendor.close();
            while (recVendor.hasNext())
            {
                recVendor.next();
                double dTotal = 0;
                recApTrx.close();
                while (recApTrx.hasNext())
                {
                    recApTrx.next();
                    double dBalance = recApTrx.getField(ApTrx.kInvoiceBalance).getValue();
                    double dAmountSelected = recApTrx.getField(ApTrx.kAmountSelected).getValue();
                    if (this.getScreenRecord().getField(GenPaymentScreenRecord.kUseCurrentSelection).getState() == false)
                        if (dBalance != dAmountSelected)
                    {
                        recApTrx.edit();
                        recApTrx.getField(ApTrx.kAmountSelected).setValue(dBalance);
                        recApTrx.set();
                        dAmountSelected = dBalance;
                    }
                    dTotal += dAmountSelected;
                }
                recPaymentRequest.setKeyArea(PaymentRequest.kBankAcctIDKey);
                recPaymentRequest.getField(PaymentRequest.kBankAcctID).moveFieldToThis(this.getScreenRecord().getField(GenPaymentScreenRecord.kBankAcctID));
                recPaymentRequest.getField(PaymentRequest.kVendorID).moveFieldToThis(recVendor.getField(Vendor.kID));
                if (recPaymentRequest.seek("="))
                    recPaymentRequest.remove();
                if (dTotal != 0)
                {
                    recPaymentRequest.addNew();
                    recPaymentRequest.getField(PaymentRequest.kBankAcctID).moveFieldToThis(this.getScreenRecord().getField(GenPaymentScreenRecord.kBankAcctID));
                    recPaymentRequest.getField(PaymentRequest.kVendorID).moveFieldToThis(recVendor.getField(Vendor.kID));
                    recPaymentRequest.getField(PaymentRequest.kAmount).setValue(dTotal);
                    recPaymentRequest.getField(PaymentRequest.kTrxStatusID).setValue(iPaymentTrxStatus);
                    recPaymentRequest.getField(PaymentRequest.kComments).moveFieldToThis(recTrxStatus.getField(TrxStatus.kStatusDesc));
                    recPaymentRequest.add();
                }
            }
        } catch (DBException ex)    {
            ex.printStackTrace();
        }
        return true;
    }

}


package com.tourgeek.tour.acctpay.screen.select;

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
import com.tourgeek.tour.acctpay.screen.trx.*;
import com.tourgeek.tour.acctpay.db.*;

/**
 *  VendorSelectGridScreen - Payment selection.
 */
public class VendorSelectGridScreen extends VendorApTrxGridScreen
{
    /**
     * Default constructor.
     */
    public VendorSelectGridScreen()
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
    public VendorSelectGridScreen(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
        return "Payment selection";
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        this.getMainRecord().addListener(new SubCountHandler(this.getScreenRecord().getField(VendorScreenRecord.TOTAL_SELECTED), ApTrx.AMOUNT_SELECTED, false, true));
        this.getRecord(ApTrx.AP_TRX_FILE).getField(ApTrx.AMOUNT_SELECTED).setEnabled(true);
        
        FilterApTrxHandler listener = (FilterApTrxHandler)this.getMainRecord().getListener(FilterApTrxHandler.class.getName());
        listener.clearFilter();
        listener.addTrxStatusID(ApTrx.INVOICE);
        listener.addTrxStatusID(ApTrx.INVOICE_NON_TOUR);
        listener.addTrxStatusID(ApTrx.PREPAYMENT_REQUEST);
        listener.addTrxStatusID(ApTrx.BROKER_PAYMENT_HEADER);
        listener.addTrxStatusID(ApTrx.BROKER_PAYMENT);
        listener.addTrxStatusID(ApTrx.CREDIT_MEMO);
    }
    /**
     * SetupSFields Method.
     */
    public void setupSFields()
    {
        this.getRecord(ApTrx.AP_TRX_FILE).getField(ApTrx.CODE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.AP_TRX_FILE).getField(ApTrx.INVOICE_BALANCE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.AP_TRX_FILE).getField(ApTrx.AMOUNT_SELECTED).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        String strDesc = "Select?";
        BaseApplication application = (BaseApplication)this.getTask().getApplication();
        strDesc = application.getResources(ResourceConstants.ACCTPAY_RESOURCE, true).getString(strDesc);
        Converter converter = new VendorSelectCheckmark(this.getMainRecord().getField(ApTrx.AMOUNT_SELECTED), null, strDesc, false);
        converter.setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.AP_TRX_FILE).getField(ApTrx.TRX_STATUS_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.AP_TRX_FILE).getField(ApTrx.DESCRIPTION).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.AP_TRX_FILE).getField(ApTrx.TOUR_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
    }
    /**
     * Add button(s) to the toolbar.
     */
    public void addToolbarButtons(ToolScreen toolScreen)
    {
        new SCannedBox(toolScreen.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), toolScreen, null, ScreenConstants.DEFAULT_DISPLAY, null, "Select All", MenuConstants.SELECT, "Select All", null);
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
        if (strCommand.equalsIgnoreCase("Select All"))
        {
            // No select all the amounts for this Vendor
            Record recApTrx = this.getMainRecord();
            try   {
                recApTrx.close();
                while (recApTrx.hasNext())
                {
                    recApTrx.next();
                    recApTrx.edit();
                    recApTrx.getField(ApTrx.AMOUNT_SELECTED).moveFieldToThis(recApTrx.getField(ApTrx.INVOICE_AMOUNT));
                    recApTrx.set();
                }
            RecordOwner screen = recApTrx.getRecordOwner();
            if (screen instanceof GridScreen)
                ((GridScreen)screen).reSelectRecords();
            } catch (DBException ex)    {
                ex.printStackTrace();
            }
        }
        return super.doCommand(strCommand, sourceSField, iCommandOptions);
    }
    /**
     * Make a sub-screen.
     * @return the new sub-screen.
     */
    public BasePanel makeSubScreen()
    {
        return new VendorSelectHeaderScreen(null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, null);
    }

}

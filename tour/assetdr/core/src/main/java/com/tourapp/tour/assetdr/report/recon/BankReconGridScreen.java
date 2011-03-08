/**
 *  @(#)BankReconGridScreen.
 *  Copyright © 2010 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.assetdr.report.recon;

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

/**
 *  BankReconGridScreen - Bank Reconciliation.
 */
public class BankReconGridScreen extends GridScreen
{
    /**
     * Default constructor.
     */
    public BankReconGridScreen()
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
    public BankReconGridScreen(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
        return "Bank Reconciliation";
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
        return new BankReconScreenRecord(this);
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        
        this.setAppending(false);
        this.setEnabled(false);
        this.getRecord(BankTrx.kBankTrxFile).getField(BankTrx.kDateReconciled).setEnabled(true);
        
        this.getScreenRecord().getField(BankReconScreenRecord.kBankAcctID).addListener(new InitFieldHandler(this.getRecord(AssetDrControl.kAssetDrControlFile).getField(AssetDrControl.kBankAcctID)));
        
        this.getScreenRecord().getField(BankReconScreenRecord.kBankAcctID).addListener(new CurrentClearedHandler((DateTimeField)this.getRecord(AssetDrControl.kAssetDrControlFile).getField(AssetDrControl.kDateReconciled), (CurrencyField)this.getScreenRecord().getField(BankReconScreenRecord.kStartCleared)));
        
        this.getMainRecord().setKeyArea(BankTrx.kTrxDateKey);
        this.getMainRecord().addListener(new SubFileFilter(this.getScreenRecord().getField(BankReconScreenRecord.kBankAcctID), BankTrx.kBankAcctID, null, -1, null, -1));
        this.getMainRecord().addListener(new CheckReconDateHandler(this.getMainRecord().getField(BankTrx.kDateReconciled), this.getRecord(AssetDrControl.kAssetDrControlFile).getField(AssetDrControl.kDateReconciled)));
        
        this.getScreenRecord().getField(BankReconScreenRecord.kBankAcctID).addListener(new FieldReSelectHandler(this));
        
        this.getMainRecord().addListener(new CountClearedHandler(this.getScreenRecord().getField(BankReconScreenRecord.kDepositsCleared), BankTrx.kAmount, BankTrx.kDateReconciled, true));
        this.getMainRecord().addListener(new CountClearedHandler(this.getScreenRecord().getField(BankReconScreenRecord.kChecksCleared), BankTrx.kAmount, BankTrx.kDateReconciled, false));
        
        this.getScreenRecord().getField(BankReconScreenRecord.kStartCleared).addListener(new CountNewClearedFieldHandler(BankReconScreenRecord.kNewCleared));
        this.getScreenRecord().getField(BankReconScreenRecord.kChecksCleared).addListener(new CountNewClearedFieldHandler(BankReconScreenRecord.kNewCleared));
        this.getScreenRecord().getField(BankReconScreenRecord.kDepositsCleared).addListener(new CountNewClearedFieldHandler(BankReconScreenRecord.kNewCleared));
    }
    /**
     * Add button(s) to the toolbar.
     */
    public void addToolbarButtons(ToolScreen toolScreen)
    {
        new SCannedBox(toolScreen.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), toolScreen, null, ScreenConstants.DEFAULT_DISPLAY, null, MenuConstants.POST, MenuConstants.POST, MenuConstants.POST, null);
    }
    /**
     * SetupSFields Method.
     */
    public void setupSFields()
    {
        Converter field = this.getRecord(BankTrx.kBankTrxFile).getField(BankTrx.kDateReconciled);
        BaseField fldTargetValue = this.getScreenRecord().getField(BankReconScreenRecord.kReconDate);
        Converter converter = new ReconciledConverter(field, fldTargetValue, "Reconciled?", true);
        new SCheckBox(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, converter, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(BankTrx.kBankTrxFile).getField(BankTrx.kTrxStatusID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(BankTrx.kBankTrxFile).getField(BankTrx.kTrxNumber).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(BankTrx.kBankTrxFile).getField(BankTrx.kTrxDate).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(BankTrx.kBankTrxFile).getField(BankTrx.kPayeeName).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(BankTrx.kBankTrxFile).getField(BankTrx.kAmount).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(BankTrx.kBankTrxFile).getField(BankTrx.kComments).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
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
        if (strCommand.equalsIgnoreCase(MenuConstants.POST))
        {
            int iDocMode = ScreenConstants.POST_MODE;
            Record recordMain = this.getScreenRecord();
            ScreenLocation itsLocation = null;
            BasePanel parentScreen = this.getParentScreen();
            if ((iCommandOptions & ScreenConstants.USE_NEW_WINDOW) == ScreenConstants.USE_SAME_WINDOW)  // Use same window
                itsLocation = this.getScreenLocation();
            else
                parentScreen = Screen.makeWindow(this.getTask().getApplication());
            boolean bUseBaseTable = true;
            boolean bLinkGridToQuery = false;
            boolean bCloneThisQuery = false;
            this.finalizeThisScreen();  // Validate current control, update record, get ready to close screen.
            boolean bReadCurrentRecord = false;
            this.setScreenRecord(null);
            this.removeRecord(recordMain);
            BasePanel pScreen = recordMain.makeScreen(itsLocation, parentScreen, iDocMode, bCloneThisQuery, bReadCurrentRecord, bUseBaseTable, bLinkGridToQuery, null);
            if (pScreen == null)
                return false;
            this.free();
            return true;
        }
        else
            return super.doCommand(strCommand, sourceSField, iCommandOptions);
    }
    /**
     * Create a data entry screen of this type.
     */
    public BasePanel onForm(Record recordMain, int iDocMode, boolean bReadCurrentRecord, int iCommandOptions, Map<String,Object> properties)
    {
        iDocMode = BankTrx.BANK_RECON_SCREEN;
        return super.onForm(recordMain, iDocMode, bReadCurrentRecord, iCommandOptions, properties);
    }
    /**
     * Make a sub-screen.
     * @return the new sub-screen.
     */
    public BasePanel makeSubScreen()
    {
        return new BankReconHeaderScreen(null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC);
    }

}


package com.tourgeek.tour.assetdr.screen.batch;

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
import com.tourgeek.tour.assetdr.screen.trx.*;
import com.tourgeek.tour.assetdr.db.*;

/**
 *  BankTrxBatchScreen - Bank Transaction batch header screen.
 */
public class BankTrxBatchScreen extends Screen
{
    /**
     * Default constructor.
     */
    public BankTrxBatchScreen()
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
    public BankTrxBatchScreen(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
        return "Bank Transaction batch header screen";
    }
    /**
     * Override this to open the main file.
     * <p />You should pass this record owner to the new main file (ie., new MyNewTable(thisRecordOwner)).
     * @return The new record.
     */
    public Record openMainRecord()
    {
        return new BankTrxBatch(this);
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
        return new BankTrxScreenRecord(this);
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        
        this.getMainRecord().getField(BankTrxBatch.BANK_ACCT_ID).addListener(new InitFieldHandler(this.getRecord(AssetDrControl.ASSET_DR_CONTROL_FILE).getField(AssetDrControl.BANK_ACCT_ID)));
        
        this.getScreenRecord().getField(BankTrxScreenRecord.USER_ID).moveFieldToThis(this.getMainRecord().getField(BankTrxBatch.USER_ID));
        this.getMainRecord().setKeyArea(BankTrxBatch.USER_ID_KEY);
        this.getMainRecord().addListener(new SubFileFilter(this.getScreenRecord().getField(BankTrxScreenRecord.USER_ID), BankTrxBatch.USER_ID, null, null, null, null));
    }
    /**
     * Add button(s) to the toolbar.
     */
    public void addToolbarButtons(ToolScreen toolScreen)
    {
        new SCannedBox(toolScreen.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), toolScreen, null, ScreenConstants.DEFAULT_DISPLAY, null, MenuConstants.FORMDETAIL, MenuConstants.FORMDETAIL, MenuConstants.FORMDETAIL, null);
        new SCannedBox(toolScreen.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), toolScreen, null, ScreenConstants.DEFAULT_DISPLAY, null, MenuConstants.POST, MenuConstants.POST, MenuConstants.POST, null);
    }
    /**
     * Set up all the screen fields.
     */
    public void setupSFields()
    {
        this.getRecord(BankTrxBatch.BANK_TRX_BATCH_FILE).getField(BankTrxBatch.BANK_ACCT_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
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
        if (strCommand.equalsIgnoreCase(MenuConstants.FORMDETAIL))
        { // This is special logic to write the current empty record (with the default account id) first
            if (this.getMainRecord().getEditMode() == DBConstants.EDIT_ADD)
            {
                if (this.getMainRecord().getField(BankTrxBatch.BANK_ACCT_ID).isNull())
                    return false;
                try {
                    this.getMainRecord().getField(BankTrxBatch.BANK_ACCT_ID).setModified(true);
                    this.getMainRecord().writeAndRefresh();
                } catch (DBException e) {
                    e.printStackTrace();
                }
            }
        }
        else if (strCommand.equalsIgnoreCase(MenuConstants.POST))
        {
            if (this.getMainRecord().getEditMode() == DBConstants.EDIT_ADD)
                return false;   // No entry or new entry;
        }
        return super.doCommand(strCommand, sourceSField, iCommandOptions);
    }

}

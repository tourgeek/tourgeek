
package com.tourgeek.tour.genled.screen.batch;

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
import com.tourgeek.tour.genled.db.*;
import org.jbundle.main.screen.*;
import java.util.*;
import org.jbundle.thin.base.screen.*;

/**
 *  AcctBatchDetailScreen - Journal Entry Batch Detail.
 */
public class AcctBatchDetailScreen extends DetailScreen
{
    /**
     * Default constructor.
     */
    public AcctBatchDetailScreen()
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
    public AcctBatchDetailScreen(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
        return "Journal Entry Batch Detail";
    }
    /**
     * AcctBatchDetailScreen Method.
     */
    public AcctBatchDetailScreen(Record recAcctBatch, Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object>
     properties)
    {
        this();
        this.init(recAcctBatch, record, itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, properties);
    }
    /**
     * Override this to open the main file.
     * <p />You should pass this record owner to the new main file (ie., new MyNewTable(thisRecordOwner)).
     * @return The new record.
     */
    public Record openMainRecord()
    {
        return new AcctBatch(this);
    }
    /**
     * Open the header record.
     * @return The new header record.
     */
    public Record openHeaderRecord()
    {
        return new AcctBatch(this);
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        
        this.getRecord(AcctBatchDetail.ACCT_BATCH_DETAIL_FILE).addListener(new SubCountHandler(this.getRecord(AcctBatch.ACCT_BATCH_FILE).getField(AcctBatch.BALANCE), AcctBatchDetail.AMOUNT, false, true));
        this.getRecord(AcctBatchDetail.ACCT_BATCH_DETAIL_FILE).addListener(new BatchSequenceHandler(this.getRecord(AcctBatchDetail.ACCT_BATCH_DETAIL_FILE).getField(AcctBatchDetail.SEQUENCE), this.getRecord(AcctBatch.ACCT_BATCH_FILE).getField(AcctBatch.NEXT_SEQUENCE), this.getRecord(AcctBatch.ACCT_BATCH_FILE).getField(AcctBatch.BALANCE)));
        this.getMainRecord().addListener(new AcctBatchValidateBeh(null));
    }
    /**
     * Add button(s) to the toolbar.
     */
    public void addToolbarButtons(ToolScreen toolScreen)
    {
        new SCannedBox(toolScreen.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), toolScreen, null, ScreenConstants.DEFAULT_DISPLAY, null, "Reversal", "Reversal", "Reversal", "Create Accrual-Reversal Entries for the selected transaction");
    }
    /**
     * Set up all the screen fields.
     */
    public void setupSFields()
    {
        this.getRecord(AcctBatchDetail.ACCT_BATCH_DETAIL_FILE).getField(AcctBatchDetail.SEQUENCE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(AcctBatchDetail.ACCT_BATCH_DETAIL_FILE).getField(AcctBatchDetail.ACCOUNT_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(AcctBatchDetail.ACCT_BATCH_DETAIL_FILE).getField(AcctBatchDetail.AMOUNT).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        ScreenField sfSequence = (ScreenField)this.getRecord(AcctBatchDetail.ACCT_BATCH_DETAIL_FILE).getField(AcctBatchDetail.SEQUENCE).getComponent(0);
        sfSequence.setRequestFocusEnabled(false);
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
        if (strCommand.equalsIgnoreCase("Reversal"))
            return ((AcctBatchDetail)this.getRecord(AcctBatchDetail.ACCT_BATCH_DETAIL_FILE)).onReversal();
        else
            return super.doCommand(strCommand, sourceSField, iCommandOptions);
    }
    /**
     * Make a sub-screen.
     * @return the new sub-screen.
     */
    public BasePanel makeSubScreen()
    {
        return new AcctBatchHeaderScreen(null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, null);
    }

}

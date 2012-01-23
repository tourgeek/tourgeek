/**
 * @(#)PrintCheckCanned.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.acctpay.screen.check;

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
import com.tourapp.tour.acctrec.screen.refund.*;
import org.jbundle.base.screen.model.report.*;
import com.tourapp.tour.acctpay.db.*;
import com.tourapp.tour.assetdr.db.*;
import com.tourapp.tour.genled.db.*;

/**
 *  PrintCheckCanned - .
 */
public class PrintCheckCanned extends CheckPrintScreen
{
    /**
     * Default constructor.
     */
    public PrintCheckCanned()
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
    public PrintCheckCanned(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
        return new PaymentRequest(this);
    }
    /**
     * Override this to open the other files in the query.
     */
    public void openOtherRecords()
    {
        super.openOtherRecords();
        new ApControl(this);
        new ApTrx(this);
    }
    /**
     * Add the screen fields.
     * Override this to create (and return) the screen record for this recordowner.
     * @return The screen record.
     */
    public Record addScreenRecord()
    {
        return new PrintCheckScreenRecord(this);
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        if (this.getProperty(this.getScreenRecord().getField(PrintCheckScreenRecord.kBankAcctID).getFieldName()) != null)
            this.getScreenRecord().getField(PrintCheckScreenRecord.kBankAcctID).addListener(new InitFieldHandler(this.getProperty(this.getScreenRecord().getField(PrintCheckScreenRecord.kBankAcctID).getFieldName())));
        else
            this.getScreenRecord().getField(PrintCheckScreenRecord.kBankAcctID).addListener(new InitFieldHandler(this.getRecord(ApControl.kApControlFile).getField(ApControl.kApBankAcctID)));
        Record recBankAcct = ((ReferenceField)this.getScreenRecord().getField(PrintCheckScreenRecord.kBankAcctID)).getReference();  // Make sure this record is referenced
        
        FieldListener listener = new ReadSecondaryHandler(recBankAcct);
        this.getScreenRecord().getField(PrintCheckScreenRecord.kBankAcctID).addListener(listener);
        listener.setRespondsToMode(DBConstants.READ_MOVE, false);
        listener.setRespondsToMode(DBConstants.INIT_MOVE, false);
        
        this.getMainRecord().addListener(new SubFileFilter(recBankAcct));
        this.getMainRecord().addListener(new CompareFileFilter(PaymentRequest.kCheckNo, (String)null, "=", null, false));
        
        this.getMainRecord().addListener(new SubCountHandler(this.getScreenRecord().getField(PrintCheckScreenRecord.kReportCount), false, true));
        this.getMainRecord().addListener(new SubCountHandler(this.getScreenRecord().getField(PrintCheckScreenRecord.kReportTotal), PaymentRequest.kAmount, false, true));
        
        Record recApTrx = this.getRecord(ApTrx.kApTrxFile);
        recApTrx.setKeyArea(ApTrx.kVendorIDKey);
        recApTrx.addListener(new SubFileFilter(this.getMainRecord().getField(PaymentRequest.kVendorID), ApTrx.kVendorID, null, -1, null, -1));
        
        recBankAcct.addListener(new MoveOnValidHandler(this.getScreenRecord().getField(PrintCheckScreenRecord.kNextCheckNo), recBankAcct.getField(BankAcct.kNextCheck)));
        this.getScreenRecord().getField(PrintCheckScreenRecord.kNextCheckNo).setSFieldToProperty();
        this.getScreenRecord().getField(PrintCheckScreenRecord.kCheckDate).setSFieldToProperty();
        this.getScreenRecord().getField(PrintCheckScreenRecord.kChecksToPrint).setSFieldToProperty();
        
        this.getMainRecord().addListener(new BumpCheckNoHandler(this.getScreenRecord().getField(PrintCheckScreenRecord.kCheckNo), this.getScreenRecord().getField(PrintCheckScreenRecord.kNextCheckNo)));
        
        Record recVendor = ((ReferenceField)this.getMainRecord().getField(PaymentRequest.kVendorID)).getReferenceRecord();
        this.getMainRecord().getField(PaymentRequest.kVendorID).addListener(new ReadSecondaryHandler(recVendor));
        recVendor.addListener(new MoveOnValidHandler(this.getScreenRecord().getField(PrintCheckScreenRecord.kPayee), recVendor.getField(Vendor.kVendorName)));
        this.getMainRecord().addListener(new MoveOnValidHandler(this.getScreenRecord().getField(PrintCheckScreenRecord.kCheckAmount), this.getMainRecord().getField(PaymentRequest.kAmount)));
    }
    /**
     * Add the toolbars that belong with this screen.
     * @return The new toolbar.
     */
    public ToolScreen addToolbars()
    {
        return new PrintCheckToolbar(null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, null);
    }
    /**
     * Add button(s) to the toolbar.
     */
    public void addToolbarButtons(ToolScreen toolScreen)
    {
        BaseApplication application = (BaseApplication)this.getTask().getApplication();
        new SCannedBox(toolScreen.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), toolScreen, null, ScreenConstants.DEFAULT_DISPLAY, null, MenuConstants.POST, MenuConstants.POST, MenuConstants.POST, null);
        super.addToolbarButtons(toolScreen);
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
        if ((strCommand.equalsIgnoreCase(MenuConstants.POST))
            || (strCommand.equalsIgnoreCase(PrintCheckJournal.CANNED_CHECKS))
            || (strCommand.equalsIgnoreCase(PrintCheckJournal.XML_CHECKS)))
        {
            String strScreen = PrintCheckPost.class.getName();
            if (strCommand.equalsIgnoreCase(PrintCheckJournal.CANNED_CHECKS))
                strScreen = PrintCheckCanned.class.getName();
            if (strCommand.equalsIgnoreCase(PrintCheckJournal.XML_CHECKS))
                strScreen = PrintCheckXML.class.getName();
        
            strCommand = this.getScreenURL();
            strCommand = this.addScreenParams(this, strCommand);
            this.getParentScreen().popHistory(1, false);
            this.getParentScreen().pushHistory(strCommand, false);
        
            strCommand = Utility.addURLParam(null, DBParams.SCREEN, strScreen);
            strCommand = this.addScreenParams(this, strCommand);
        }
        return super.doCommand(strCommand, sourceSField, iCommandOptions);
    }
    /**
     * Layout the special print control (usually a JPanel).
     */
    public void layoutPrintControl(Component control)
    {
        super.layoutPrintControl(control);
    }

}

/**
  * @(#)CreditCollectGridScreen.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.tour.acctrec.screen.credit;

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
import com.tourgeek.tour.acctrec.db.*;
import com.tourgeek.tour.acctrec.screen.mco.*;

/**
 *  CreditCollectGridScreen - Enter amount collected from credit card company.
 */
public class CreditCollectGridScreen extends CreditBaseGridScreen
{
    /**
     * Default constructor.
     */
    public CreditCollectGridScreen()
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
    public CreditCollectGridScreen(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
        return "Enter amount collected from credit card company";
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        
        TrxStatus recTrxStatus = (TrxStatus)this.getRecord(TrxStatus.TRX_STATUS_FILE);
        
        this.getMainRecord().getField(CreditCard.PAID).addListener(new MoveOnChangeHandler(this.getMainRecord().getField(CreditCard.AMOUNT_PAID), this.getMainRecord().getField(CreditCard.NET)));
        this.getMainRecord().getField(CreditCard.PAID).addListener(new MoveOnChangeHandler(this.getMainRecord().getField(CreditCard.DATE_PAID), this.getScreenRecord().getField(McoScreenRecord.TODAY)));
        
        this.getMainRecord().setKeyArea(CreditCard.TRX_STATUS_ID_KEY);
        recTrxStatus.getTrxStatusID(TransactionType.ACCTREC, CreditCard.CREDIT_CARD_FILE, CreditCard.APPROVED);
        this.getMainRecord().addListener(new SubFileFilter(recTrxStatus, true));
        this.getMainRecord().getField(Mco.PAID).setEnabled(true);
    }
    /**
     * Add button(s) to the toolbar.
     */
    public void addToolbarButtons(ToolScreen toolScreen)
    {
        new SCannedBox(toolScreen.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), toolScreen, null, ScreenConstants.DEFAULT_DISPLAY, null, MenuConstants.POST, MenuConstants.POST, MenuConstants.POST, null);
        super.addToolbarButtons(toolScreen);
    }
    /**
     * SetupSFields Method.
     */
    public void setupSFields()
    {
        super.setupSFields();
        
        this.getRecord(CreditCard.CREDIT_CARD_FILE).getField(CreditCard.DATE_PAID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(CreditCard.CREDIT_CARD_FILE).getField(CreditCard.AMOUNT_PAID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        
        String strDesc = CreditCard.ITEM_PAID;
        if (this.getTask() != null)
            strDesc = ((BaseApplication)this.getTask().getApplication()).getString(strDesc);
        this.getMainRecord().getField(Mco.PAID).setFieldDesc(strDesc);
        new SButtonBox(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, this.getMainRecord().getField(Mco.PAID), ScreenConstants.DISPLAY_FIELD_DESC);
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
            Map<String,Object> map = null;
            try   {
                if (this.getMainRecord().getEditMode() == Constants.EDIT_IN_PROGRESS)
                    this.getMainRecord().set();
                this.getMainRecord().addNew();
                map = new Hashtable<String,Object>();
                BaseField fldCardID = this.getScreenRecord().getField(McoScreenRecord.CARD_ID);
                map.put(fldCardID.getFieldName(), fldCardID.toString());
            } catch (DBException ex)    {
                ex.printStackTrace();
            }
            return (this.onForm(this.getMainRecord(), Mco.COLL_POST, true, iCommandOptions, map) != null);
        }
        else if ((strCommand.equalsIgnoreCase(MenuConstants.FORM)) || (strCommand.equalsIgnoreCase(MenuConstants.FORMLINK)))
            return (this.onForm(this.getMainRecord(), CreditCard.COLL_SCREEN, true, iCommandOptions, null) != null);
        else
            return super.doCommand(strCommand, sourceSField, iCommandOptions);
    }

}

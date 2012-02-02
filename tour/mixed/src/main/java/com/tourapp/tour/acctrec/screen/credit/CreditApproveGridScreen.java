/**
 * @(#)CreditApproveGridScreen.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.acctrec.screen.credit;

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
import com.tourapp.tour.acctrec.db.*;
import com.tourapp.tour.genled.db.*;
import com.tourapp.tour.acctrec.screen.mco.*;

/**
 *  CreditApproveGridScreen - Update the approved credit card charges.
 */
public class CreditApproveGridScreen extends CreditBaseGridScreen
{
    /**
     * Default constructor.
     */
    public CreditApproveGridScreen()
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
    public CreditApproveGridScreen(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
        return "Update the approved credit card charges";
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        
        TrxStatus recTrxStatus = (TrxStatus)this.getRecord(TrxStatus.TRX_STATUS_FILE);
        int iTrxStatusID = recTrxStatus.getTrxStatusID(TransactionType.ACCTREC, CreditCard.CREDIT_CARD_FILE, CreditCard.APPROVED);
        String strTrxClass = Integer.toString(iTrxStatusID);
        
        this.getMainRecord().getField(CreditCard.PAID).addListener(new MoveOnChangeHandler(this.getMainRecord().getField(CreditCard.DATE_APPROVED), this.getScreenRecord().getField(McoScreenRecord.TODAY)));
        this.getMainRecord().getField(CreditCard.PAID).addListener(new CopyStringHandler(this.getMainRecord().getField(CreditCard.TRX_STATUS_ID), strTrxClass, null));
        this.getMainRecord().getField(CreditCard.PAID).addListener(new InitOnChangeHandler(this.getScreenRecord().getField(McoScreenRecord.FLAG))); // Wierd, but since this is a screen field, it would be set the first time and never change again
        
        this.getMainRecord().setKeyArea(CreditCard.TRX_STATUS_ID_KEY);
        recTrxStatus.getTrxStatusID(TransactionType.ACCTREC, CreditCard.CREDIT_CARD_FILE, CreditCard.SUBMITTED);
        this.getMainRecord().addListener(new SubFileFilter(recTrxStatus));
        
        this.getMainRecord().addListener(new SyncArTrxStatusHandler(null));
    }
    /**
     * SetupSFields Method.
     */
    public void setupSFields()
    {
        super.setupSFields();
        
        String strDesc = CreditCard.APPROVED;
        if (this.getTask() != null)
            strDesc = ((BaseApplication)this.getTask().getApplication()).getString(strDesc);
        BaseField fldFlag = this.getMainRecord().getField(CreditCard.PAID);
        fldFlag.setFieldDesc(strDesc);
        new SButtonBox(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, fldFlag, ScreenConstants.DISPLAY_FIELD_DESC);
        this.getRecord(CreditCard.CREDIT_CARD_FILE).getField(CreditCard.TRX_STATUS_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
    }

}

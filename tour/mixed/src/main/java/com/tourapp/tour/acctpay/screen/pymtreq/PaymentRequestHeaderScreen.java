/**
 * @(#)PaymentRequestHeaderScreen.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.acctpay.screen.pymtreq;

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
import com.tourapp.tour.acctpay.db.*;
import com.tourapp.tour.assetdr.db.*;
import com.tourapp.tour.acctpay.screen.genpymt.*;
import com.tourapp.tour.acctpay.screen.check.*;
import org.jbundle.main.screen.*;

/**
 *  PaymentRequestHeaderScreen - .
 */
public class PaymentRequestHeaderScreen extends HeaderScreen
{
    /**
     * Default constructor.
     */
    public PaymentRequestHeaderScreen()
    {
        super();
    }
    /**
     * Constructor.
     * @param itsLocation The location of this component within the parent.
     * @param parentScreen The parent screen.
     * @param fieldConverter The field this screen field is linked to.
     * @param iDisplayFieldDesc Do I display the field desc?.
     */
    public PaymentRequestHeaderScreen(ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
    {
        this();
        this.init(itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, properties);
    }
    /**
     * Initialize class fields.
     */
    public void init(ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
    {
        super.init(itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, properties);
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        this.getScreenRecord().getField(PaymentRequestScreenRecord.BANK_ACCT_ID).setEnabled(true);
        this.getScreenRecord().getField(PaymentRequestScreenRecord.MANUAL_CHECKS).setEnabled(true);
    }
    /**
     * SetupSFields Method.
     */
    public void setupSFields()
    {
        Record recBankAcct = ((ReferenceField)this.getScreenRecord().getField(PaymentRequestScreenRecord.BANK_ACCT_ID)).getReferenceRecord((BaseScreen)this.getParentScreen());
        if (recBankAcct != null)
        {    // Make sure currency is read for LocalCurrencyField(s).
            Record recCurrencys = ((ReferenceField)recBankAcct.getField(BankAcct.CURRENCY_ID)).getReferenceRecord((BaseScreen)this.getParentScreen());
            recBankAcct.getField(BankAcct.CURRENCY_ID).addListener(new ReadSecondaryHandler(recCurrencys));
        }
        this.getRecord(PaymentRequestScreenRecord.PAYMENT_REQUEST_SCREEN_RECORD_FILE).getField(PaymentRequestScreenRecord.BANK_ACCT_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(PaymentRequestScreenRecord.PAYMENT_REQUEST_SCREEN_RECORD_FILE).getField(PaymentRequestScreenRecord.REQUEST_TOTAL).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(PaymentRequestScreenRecord.PAYMENT_REQUEST_SCREEN_RECORD_FILE).getField(PaymentRequestScreenRecord.MANUAL_CHECKS).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
    }

}

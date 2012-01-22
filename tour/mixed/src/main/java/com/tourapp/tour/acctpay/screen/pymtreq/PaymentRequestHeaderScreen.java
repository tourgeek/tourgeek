/**
 * @(#)PaymentRequestHeaderScreen.
 * Copyright © 2011 tourapp.com. All rights reserved.
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
    public PaymentRequestHeaderScreen(ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc)
    {
        this();
        this.init(itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc);
    }
    /**
     * Initialize class fields.
     */
    public void init(ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc)
    {
        super.init(itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc);
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        this.getScreenRecord().getField(PaymentRequestScreenRecord.kBankAcctID).setEnabled(true);
        this.getScreenRecord().getField(PaymentRequestScreenRecord.kManualChecks).setEnabled(true);
    }
    /**
     * SetupSFields Method.
     */
    public void setupSFields()
    {
        Record recBankAcct = ((ReferenceField)this.getScreenRecord().getField(PaymentRequestScreenRecord.kBankAcctID)).getReferenceRecord((BaseScreen)this.getParentScreen());
        if (recBankAcct != null)
        {    // Make sure currency is read for LocalCurrencyField(s).
            Record recCurrencys = ((ReferenceField)recBankAcct.getField(BankAcct.kCurrencyID)).getReferenceRecord((BaseScreen)this.getParentScreen());
            recBankAcct.getField(BankAcct.kCurrencyID).addListener(new ReadSecondaryHandler(recCurrencys));
        }
        this.getRecord(PaymentRequestScreenRecord.kPaymentRequestScreenRecordFile).getField(PaymentRequestScreenRecord.kBankAcctID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(PaymentRequestScreenRecord.kPaymentRequestScreenRecordFile).getField(PaymentRequestScreenRecord.kRequestTotal).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(PaymentRequestScreenRecord.kPaymentRequestScreenRecordFile).getField(PaymentRequestScreenRecord.kManualChecks).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
    }

}

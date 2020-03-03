/**
  * @(#)BankReconScreenRecord.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.tour.assetdr.report.recon;

import java.util.*;

import org.jbundle.base.db.*;
import org.jbundle.thin.base.util.*;
import org.jbundle.thin.base.db.*;
import org.jbundle.base.db.event.*;
import org.jbundle.base.db.filter.*;
import org.jbundle.base.field.*;
import org.jbundle.base.field.convert.*;
import org.jbundle.base.field.event.*;
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import org.jbundle.base.screen.model.*;
import com.tourgeek.tour.assetdr.db.*;
import org.jbundle.base.screen.model.util.*;
import org.bson.*;

/**
 *  BankReconScreenRecord - Fields for Bank Acct Reconciliation.
 */
public class BankReconScreenRecord extends ScreenRecord
{
    private static final long serialVersionUID = 1L;

    public static final String BANK_ACCT_ID = "BankAcctID";
    public static final String RECON_DATE = "ReconDate";
    public static final String START_CLEARED = "StartCleared";
    public static final String DEPOSITS_CLEARED = "DepositsCleared";
    public static final String CHECKS_CLEARED = "ChecksCleared";
    public static final String NEW_CLEARED = "NewCleared";
    /**
     * Default constructor.
     */
    public BankReconScreenRecord()
    {
        super();
    }
    /**
     * Constructor.
     */
    public BankReconScreenRecord(RecordOwner screen)
    {
        this();
        this.init(screen);
    }
    /**
     * Initialize class fields.
     */
    public void init(RecordOwner screen)
    {
        super.init(screen);
    }

    public static final String BANK_RECON_SCREEN_RECORD_FILE = null;    // Screen field
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        if (iFieldSeq == 0)
            field = new BankAcctField(this, BANK_ACCT_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 1)
            field = new BankReconScreenRecord_ReconDate(this, RECON_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 2)
            field = new CurrencyField(this, START_CLEARED, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 3)
            field = new CurrencyField(this, DEPOSITS_CLEARED, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 4)
            field = new CurrencyField(this, CHECKS_CLEARED, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 5)
            field = new CurrencyField(this, NEW_CLEARED, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
            field = super.setupField(iFieldSeq);
        return field;
    }
    /**
     * MakeScreen Method.
     */
    public ScreenParent makeScreen(ScreenLoc itsLocation, ComponentParent parentScreen, int iDocMode, Map<String,Object> properties)
    {
        ScreenParent screen = new BankReconPostScreen(this, (ScreenLocation)itsLocation, (BasePanel)parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        return screen;
    }

}

/**
 * @(#)BankReconScreenRecord.
 * Copyright © 2011 tourapp.com. All rights reserved.
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
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import com.tourapp.tour.assetdr.db.*;

/**
 *  BankReconScreenRecord - Fields for Bank Acct Reconciliation.
 */
public class BankReconScreenRecord extends ScreenRecord
{
    private static final long serialVersionUID = 1L;

    public static final int kBankAcctID = kScreenRecordLastField + 1;
    public static final int kReconDate = kBankAcctID + 1;
    public static final int kStartCleared = kReconDate + 1;
    public static final int kDepositsCleared = kStartCleared + 1;
    public static final int kChecksCleared = kDepositsCleared + 1;
    public static final int kNewCleared = kChecksCleared + 1;
    public static final int kBankReconScreenRecordLastField = kNewCleared;
    public static final int kBankReconScreenRecordFields = kNewCleared - DBConstants.MAIN_FIELD + 1;
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

    public static final String kBankReconScreenRecordFile = null; // Screen field
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        if (iFieldSeq == kBankAcctID)
            field = new BankAcctField(this, "BankAcctID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kReconDate)
            field = new BankReconScreenRecord_ReconDate(this, "ReconDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kStartCleared)
            field = new CurrencyField(this, "StartCleared", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kDepositsCleared)
            field = new CurrencyField(this, "DepositsCleared", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kChecksCleared)
            field = new CurrencyField(this, "ChecksCleared", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kNewCleared)
            field = new CurrencyField(this, "NewCleared", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kBankReconScreenRecordLastField)
                field = new EmptyField(this);
        }
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

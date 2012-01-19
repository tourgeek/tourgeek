/**
 * @(#)PrintCheckScreenRecord.
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
 *  PrintCheckScreenRecord - .
 */
public class PrintCheckScreenRecord extends CheckScreenRecord
{
    private static final long serialVersionUID = 1L;

    public static final int kBankAcctID = kCheckScreenRecordLastField + 1;
    public static final int kNextCheckNo = kBankAcctID + 1;
    public static final int kChecksToPrint = kNextCheckNo + 1;
    public static final int ktemplate = kChecksToPrint + 1;
    public static final int kPrintCheckScreenRecordLastField = ktemplate;
    public static final int kPrintCheckScreenRecordFields = ktemplate - DBConstants.MAIN_FIELD + 1;
    /**
     * Default constructor.
     */
    public PrintCheckScreenRecord()
    {
        super();
    }
    /**
     * Constructor.
     */
    public PrintCheckScreenRecord(RecordOwner screen)
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

    public static final String kPrintCheckScreenRecordFile = null;  // Screen field
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        if (iFieldSeq == kBankAcctID)
            field = new BankAcctField(this, "BankAcctID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kNextCheckNo)
            field = new IntegerField(this, "NextCheckNo", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kChecksToPrint)
            field = new ChecksToPrintField(this, "ChecksToPrint", 1, null, null);
        if (iFieldSeq == ktemplate)
            field = new StringField(this, "template", 60, null, "check");
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kPrintCheckScreenRecordLastField)
                field = new EmptyField(this);
        }
        return field;
    }

}

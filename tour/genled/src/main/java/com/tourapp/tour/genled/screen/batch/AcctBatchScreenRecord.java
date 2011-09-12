/**
 * @(#)AcctBatchScreenRecord.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.genled.screen.batch;

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
import org.jbundle.main.screen.*;
import com.tourapp.tour.genled.db.*;
import java.util.*;
import org.jbundle.thin.base.screen.*;

/**
 *  AcctBatchScreenRecord - .
 */
public class AcctBatchScreenRecord extends ScreenRecord
{
    private static final long serialVersionUID = 1L;

    public static final int kUserID = kScreenRecordLastField + 1;
    public static final int kRecurring = kUserID + 1;
    public static final int kAcctBatchScreenRecordLastField = kRecurring;
    public static final int kAcctBatchScreenRecordFields = kRecurring - DBConstants.MAIN_FIELD + 1;
    /**
     * Default constructor.
     */
    public AcctBatchScreenRecord()
    {
        super();
    }
    /**
     * Constructor.
     */
    public AcctBatchScreenRecord(RecordOwner screen)
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

    public static final String kAcctBatchScreenRecordFile = null; // Screen field
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        if (iFieldSeq == kUserID)
            field = new AcctBatchScreenRecord_UserID(this, "UserID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kRecurring)
            field = new BooleanField(this, "Recurring", Constants.DEFAULT_FIELD_LENGTH, null, new Boolean(false));
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kAcctBatchScreenRecordLastField)
                field = new EmptyField(this);
        }
        return field;
    }

}

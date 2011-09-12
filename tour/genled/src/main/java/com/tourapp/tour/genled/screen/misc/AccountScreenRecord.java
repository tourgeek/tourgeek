/**
 * @(#)AccountScreenRecord.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.genled.screen.misc;

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
import org.jbundle.main.db.*;
import com.tourapp.tour.genled.db.*;

/**
 *  AccountScreenRecord - Account Grid Screen fields.
 */
public class AccountScreenRecord extends ScreenRecord
{
    private static final long serialVersionUID = 1L;

    public static final int kAcctNameSort = kScreenRecordLastField + 1;
    public static final int kAccountKeyArea = kAcctNameSort + 1;
    public static final int kAccountScreenRecordLastField = kAccountKeyArea;
    public static final int kAccountScreenRecordFields = kAccountKeyArea - DBConstants.MAIN_FIELD + 1;
    /**
     * Default constructor.
     */
    public AccountScreenRecord()
    {
        super();
    }
    /**
     * Constructor.
     */
    public AccountScreenRecord(RecordOwner screen)
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

    public static final String kAccountScreenRecordFile = null;   // Screen field
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        if (iFieldSeq == kAcctNameSort)
            field = new StringField(this, "AcctNameSort", 10, null, null);
        if (iFieldSeq == kAccountKeyArea)
            field = new IntegerField(this, "AccountKeyArea", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kAccountScreenRecordLastField)
                field = new EmptyField(this);
        }
        return field;
    }

}

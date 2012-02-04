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
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import org.jbundle.main.db.*;
import com.tourapp.tour.genled.db.*;

/**
 *  AccountScreenRecord - Account Grid Screen fields.
 */
public class AccountScreenRecord extends ScreenRecord
{
    private static final long serialVersionUID = 1L;

    public static final String ACCT_NAME_SORT = "AcctNameSort";
    public static final String ACCOUNT_KEY_AREA = "AccountKeyArea";
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

    public static final String ACCOUNT_SCREEN_RECORD_FILE = null; // Screen field
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        if (iFieldSeq == 0)
            field = new StringField(this, ACCT_NAME_SORT, 10, null, null);
        if (iFieldSeq == 1)
            field = new IntegerField(this, ACCOUNT_KEY_AREA, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
            field = super.setupField(iFieldSeq);
        return field;
    }

}

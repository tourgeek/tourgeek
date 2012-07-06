/**
  * @(#)AcctBatchScreenRecord.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
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
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import org.jbundle.base.screen.model.*;
import com.tourapp.tour.genled.db.*;
import org.jbundle.main.screen.*;
import org.jbundle.base.screen.model.util.*;
import java.util.*;
import org.jbundle.thin.base.screen.*;

/**
 *  AcctBatchScreenRecord - .
 */
public class AcctBatchScreenRecord extends ScreenRecord
{
    private static final long serialVersionUID = 1L;

    public static final String USER_ID = "UserID";
    public static final String RECURRING = "Recurring";
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

    public static final String ACCT_BATCH_SCREEN_RECORD_FILE = null;    // Screen field
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        if (iFieldSeq == 0)
            field = new AcctBatchScreenRecord_UserID(this, USER_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 1)
            field = new BooleanField(this, RECURRING, Constants.DEFAULT_FIELD_LENGTH, null, new Boolean(false));
        if (field == null)
            field = super.setupField(iFieldSeq);
        return field;
    }

}

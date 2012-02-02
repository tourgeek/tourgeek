/**
 * @(#)ArTrxAgentScreenRecord.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.acctrec.screen.display;

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
import com.tourapp.tour.profile.db.*;
import com.tourapp.tour.acctrec.db.*;
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.genled.db.*;
import com.tourapp.tour.booking.detail.db.*;

/**
 *  ArTrxAgentScreenRecord - Fields for Agency display screen.
 */
public class ArTrxAgentScreenRecord extends ScreenRecord
{
    private static final long serialVersionUID = 1L;

    public static final String PROFILE_ID = "ProfileID";
    public static final int kProfileID = kScreenRecordLastField + 1;
    public static final String BALANCE = "Balance";
    public static final int kBalance = kProfileID + 1;
    public static final int kArTrxAgentScreenRecordLastField = kBalance;
    public static final int kArTrxAgentScreenRecordFields = kBalance - DBConstants.MAIN_FIELD + 1;
    /**
     * Default constructor.
     */
    public ArTrxAgentScreenRecord()
    {
        super();
    }
    /**
     * Constructor.
     */
    public ArTrxAgentScreenRecord(RecordOwner screen)
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

    public static final String kArTrxAgentScreenRecordFile = null;  // Screen field
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        if (iFieldSeq == kProfileID)
            field = new ProfileField(this, "ProfileID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kBalance)
            field = new CurrencyField(this, "Balance", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kArTrxAgentScreenRecordLastField)
                field = new EmptyField(this);
        }
        return field;
    }

}

/**
 * @(#)TrxStatusScreenRecord.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.genled.screen.trx;

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
import com.tourapp.tour.genled.db.*;

/**
 *  TrxStatusScreenRecord - .
 */
public class TrxStatusScreenRecord extends ScreenRecord
{
    private static final long serialVersionUID = 1L;

    public static final int kTrxSystemID = kScreenRecordLastField + 1;
    public static final int kTrxDescID = kTrxSystemID + 1;
    public static final int kTrxStatusScreenRecordLastField = kTrxDescID;
    public static final int kTrxStatusScreenRecordFields = kTrxDescID - DBConstants.MAIN_FIELD + 1;
    /**
     * Default constructor.
     */
    public TrxStatusScreenRecord()
    {
        super();
    }
    /**
     * Constructor.
     */
    public TrxStatusScreenRecord(RecordOwner screen)
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

    public static final String kTrxStatusScreenRecordFile = null; // Screen field
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        if (iFieldSeq == kTrxSystemID)
            field = new TrxSystemField(this, "TrxSystemID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kTrxDescID)
            field = new TrxDescField(this, "TrxDescID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kTrxStatusScreenRecordLastField)
                field = new EmptyField(this);
        }
        return field;
    }

}

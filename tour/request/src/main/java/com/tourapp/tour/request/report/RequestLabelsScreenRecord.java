/**
 * @(#)RequestLabelsScreenRecord.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.request.report;

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
import com.tourapp.tour.request.db.*;

/**
 *  RequestLabelsScreenRecord - Screen field for the label printing program..
 */
public class RequestLabelsScreenRecord extends ScreenRecord
{
    private static final long serialVersionUID = 1L;

    public static final int kSendViaID = kScreenRecordLastField + 1;
    public static final int kRestoreFromDate = kSendViaID + 1;
    public static final int kStylesheet = kRestoreFromDate + 1;
    public static final int kFullAddress = kStylesheet + 1;
    public static final int kRequestText = kFullAddress + 1;
    public static final int kTrueField = kRequestText + 1;
    public static final int ktemplate = kTrueField + 1;
    public static final int kRequestLabelsScreenRecordLastField = ktemplate;
    public static final int kRequestLabelsScreenRecordFields = ktemplate - DBConstants.MAIN_FIELD + 1;
    /**
     * Default constructor.
     */
    public RequestLabelsScreenRecord()
    {
        super();
    }
    /**
     * Constructor.
     */
    public RequestLabelsScreenRecord(RecordOwner screen)
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

    public static final String kRequestLabelsScreenRecordFile = null; // Screen field
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        if (iFieldSeq == kSendViaID)
            field = new SendViaFilter(this, "SendViaID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kRestoreFromDate)
            field = new DateTimeField(this, "RestoreFromDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kStylesheet)
            field = new StringField(this, "Stylesheet", 127, null, null);
        if (iFieldSeq == kFullAddress)
            field = new StringField(this, "FullAddress", 255, null, null);
        if (iFieldSeq == kRequestText)
            field = new StringField(this, "RequestText", 255, null, null);
        if (iFieldSeq == kTrueField)
            field = new BooleanField(this, "TrueField", Constants.DEFAULT_FIELD_LENGTH, null, new Boolean(true));
        if (iFieldSeq == ktemplate)
            field = new StringField(this, "template", 40, null, "tour/labels");
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kRequestLabelsScreenRecordLastField)
                field = new EmptyField(this);
        }
        return field;
    }

}

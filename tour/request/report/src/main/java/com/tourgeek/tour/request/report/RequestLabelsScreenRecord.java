/**
  * @(#)RequestLabelsScreenRecord.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.tour.request.report;

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
import com.tourgeek.tour.request.db.*;

/**
 *  RequestLabelsScreenRecord - Screen field for the label printing program..
 */
public class RequestLabelsScreenRecord extends ScreenRecord
{
    private static final long serialVersionUID = 1L;

    public static final String SEND_VIA_ID = "SendViaID";
    public static final String RESTORE_FROM_DATE = "RestoreFromDate";
    public static final String STYLESHEET = "Stylesheet";
    public static final String FULL_ADDRESS = "FullAddress";
    public static final String REQUEST_TEXT = "RequestText";
    public static final String TRUE_FIELD = "TrueField";
    public static final String TEMPLATE = "template";
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

    public static final String REQUEST_LABELS_SCREEN_RECORD_FILE = null;    // Screen field
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        if (iFieldSeq == 0)
            field = new SendViaFilter(this, SEND_VIA_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 1)
            field = new DateTimeField(this, RESTORE_FROM_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 2)
            field = new StringField(this, STYLESHEET, 127, null, null);
        if (iFieldSeq == 3)
            field = new StringField(this, FULL_ADDRESS, 255, null, null);
        if (iFieldSeq == 4)
            field = new StringField(this, REQUEST_TEXT, 255, null, null);
        if (iFieldSeq == 5)
            field = new BooleanField(this, TRUE_FIELD, Constants.DEFAULT_FIELD_LENGTH, null, new Boolean(true));
        if (iFieldSeq == 6)
            field = new StringField(this, TEMPLATE, 40, null, "tour/labels");
        if (field == null)
            field = super.setupField(iFieldSeq);
        return field;
    }

}

/**
 * @(#)BaseProductStatus.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.product.base.db;

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
import org.jbundle.main.msg.db.base.*;

/**
 *  BaseProductStatus - .
 */
public class BaseProductStatus extends BaseStatus
{
    private static final long serialVersionUID = 1L;

    //public static final int kID = kID;
    //public static final int kDescription = kDescription;
    //public static final int kIcon = kIcon;
    public static final int kCode = kBaseStatusLastField + 1;
    public static final int kBaseProductStatusLastField = kCode;
    public static final int kBaseProductStatusFields = kCode - DBConstants.MAIN_FIELD + 1;
    public static final String NO_STATUS_CODE = "NS";
    public static final String PROPOSAL_CODE = "PR";
    public static final String ACCEPTED_CODE = "RQ";
    public static final String OKAY_CODE = "OK";
    public static final String CANCELLED_CODE = "XL";
    /**
     * Default constructor.
     */
    public BaseProductStatus()
    {
        super();
    }
    /**
     * Constructor.
     */
    public BaseProductStatus(RecordOwner screen)
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

    public static final String kBaseProductStatusFile = null; // Screen field
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        //if (iFieldSeq == kID)
        //{
        //  field = new CounterField(this, "ID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //  field.setHidden(true);
        //}
        //if (iFieldSeq == kDescription)
        //  field = new StringField(this, "Description", 20, null, null);
        if (iFieldSeq == kCode)
            field = new StringField(this, "Code", 2, null, null);
        //if (iFieldSeq == kIcon)
        //  field = new ImageField(this, "Icon", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kBaseProductStatusLastField)
                field = new EmptyField(this);
        }
        return field;
    }

}

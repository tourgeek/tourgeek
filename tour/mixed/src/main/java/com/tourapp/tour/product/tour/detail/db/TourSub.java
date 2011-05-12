/**
 *  @(#)TourSub.
 *  Copyright © 2010 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.product.tour.detail.db;

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
import com.tourapp.tour.product.tour.db.*;
import com.tourapp.tour.product.tour.screen.*;
import org.jbundle.thin.base.message.*;
import org.jbundle.thin.base.screen.message.*;
import org.jbundle.base.message.record.*;
import com.tourapp.tour.product.base.db.*;
import org.jbundle.thin.base.screen.*;

/**
 *  TourSub - Tour Sub File.
 */
public class TourSub extends VirtualRecord
{
    private static final long serialVersionUID = 1L;

    //public static final int kID = kID;
    public static final int kTourHeaderOptionID = kVirtualRecordLastField + 1;
    public static final int kModifyCode = kTourHeaderOptionID + 1;
    public static final int kModifyID = kModifyCode + 1;
    public static final int kTourSubLastField = kModifyID;
    public static final int kTourSubFields = kModifyID - DBConstants.MAIN_FIELD + 1;
    /**
     * Default constructor.
     */
    public TourSub()
    {
        super();
    }
    /**
     * Constructor.
     */
    public TourSub(RecordOwner screen)
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

    public static final String kTourSubFile = null;   // Screen field
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
        if (iFieldSeq == kTourHeaderOptionID)
            field = new TourHeaderOptionField(this, "TourHeaderOptionID", 8, null, null);
        if (iFieldSeq == kModifyCode)
            field = new ModifyCodeField(this, "ModifyCode", 1, null, null);
        if (iFieldSeq == kModifyID)
            field = new ModifyTourSubField(this, "ModifyID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kTourSubLastField)
                field = new EmptyField(this);
        }
        return field;
    }

}

/**
 * @(#)TourSub.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.product.tour.detail.db;

import org.jbundle.base.db.VirtualRecord;
import org.jbundle.base.field.BaseField;
import org.jbundle.base.model.RecordOwner;
import org.jbundle.thin.base.db.Constants;

import com.tourapp.model.tour.product.tour.detail.db.TourSubModel;
import com.tourapp.tour.product.tour.db.ModifyCodeField;
import com.tourapp.tour.product.tour.db.TourHeaderOptionField;

/**
 *  TourSub - Tour Sub File.
 */
public class TourSub extends VirtualRecord
     implements TourSubModel
{
    private static final long serialVersionUID = 1L;

    public static final String MODIFY_TOUR_SUB_SCREEN_FIELD_CLASS = "com.tourapp.tour.product.tour.detail.screen.ModifyTourSubScreenField";
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

    public static final String TOUR_SUB_FILE = null;    // Screen field
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        //if (iFieldSeq == 0)
        //{
        //  field = new CounterField(this, ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //  field.setHidden(true);
        //}
        //if (iFieldSeq == 1)
        //{
        //  field = new RecordChangedField(this, LAST_CHANGED, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //  field.setHidden(true);
        //}
        //if (iFieldSeq == 2)
        //{
        //  field = new BooleanField(this, DELETED, Constants.DEFAULT_FIELD_LENGTH, null, new Boolean(false));
        //  field.setHidden(true);
        //}
        if (iFieldSeq == 3)
            field = new TourHeaderOptionField(this, TOUR_HEADER_OPTION_ID, 8, null, null);
        if (iFieldSeq == 4)
            field = new ModifyCodeField(this, MODIFY_CODE, 1, null, null);
        if (iFieldSeq == 5)
            field = new ModifyTourSubField(this, MODIFY_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
            field = super.setupField(iFieldSeq);
        return field;
    }

}

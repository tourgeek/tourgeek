/**
 * @(#)TaxRateScreenRecord.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.payroll.screen.tax;

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
import com.tourapp.tour.payroll.db.*;

/**
 *  TaxRateScreenRecord - .
 */
public class TaxRateScreenRecord extends ScreenRecord
{
    private static final long serialVersionUID = 1L;

    public static final int kTaxCode = kScreenRecordLastField + 1;
    public static final int kMaritalStatus = kTaxCode + 1;
    public static final int kTaxRateScreenRecordLastField = kMaritalStatus;
    public static final int kTaxRateScreenRecordFields = kMaritalStatus - DBConstants.MAIN_FIELD + 1;
    /**
     * Default constructor.
     */
    public TaxRateScreenRecord()
    {
        super();
    }
    /**
     * Constructor.
     */
    public TaxRateScreenRecord(RecordOwner screen)
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

    public static final String kTaxRateScreenRecordFile = null;   // Screen field
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        if (iFieldSeq == kTaxCode)
            field = new StringField(this, "TaxCode", 2, null, "FE");
        if (iFieldSeq == kMaritalStatus)
            field = new MaritalStatusField(this, "MaritalStatus", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kTaxRateScreenRecordLastField)
                field = new EmptyField(this);
        }
        return field;
    }

}

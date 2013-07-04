
package com.tourgeek.tour.payroll.screen.tax;

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
import com.tourgeek.tour.payroll.db.*;

/**
 *  TaxRateScreenRecord - .
 */
public class TaxRateScreenRecord extends ScreenRecord
{
    private static final long serialVersionUID = 1L;

    public static final String TAX_CODE = "TaxCode";
    public static final String MARITAL_STATUS = "MaritalStatus";
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

    public static final String TAX_RATE_SCREEN_RECORD_FILE = null;  // Screen field
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        if (iFieldSeq == 0)
            field = new StringField(this, TAX_CODE, 2, null, "FE");
        if (iFieldSeq == 1)
            field = new MaritalStatusField(this, MARITAL_STATUS, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
            field = super.setupField(iFieldSeq);
        return field;
    }

}

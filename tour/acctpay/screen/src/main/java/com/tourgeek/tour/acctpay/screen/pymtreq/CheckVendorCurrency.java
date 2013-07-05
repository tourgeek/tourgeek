/**
  * @(#)CheckVendorCurrency.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.tour.acctpay.screen.pymtreq;

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
import com.tourgeek.tour.acctpay.db.*;
import com.tourgeek.tour.assetdr.db.*;
import com.tourgeek.tour.acctpay.screen.genpymt.*;
import com.tourgeek.tour.acctpay.screen.check.*;
import org.jbundle.base.screen.model.util.*;
import org.jbundle.main.screen.*;

/**
 *  CheckVendorCurrency - .
 */
public class CheckVendorCurrency extends FieldListener
{
    protected BaseField m_fldBankCurrency = null;
    protected String m_fsVendorCurrency;
    /**
     * Default constructor.
     */
    public CheckVendorCurrency()
    {
        super();
    }
    /**
     * Constructor.
     */
    public CheckVendorCurrency(String fsVendorCurrency, BaseField fldBankCurrency)
    {
        this();
        this.init(fsVendorCurrency, fldBankCurrency);
    }
    /**
     * Initialize class fields.
     */
    public void init(String fsVendorCurrency, BaseField fldBankCurrency)
    {
        m_fldBankCurrency = null;
        m_fsVendorCurrency = "";
        m_fsVendorCurrency = fsVendorCurrency;
        m_fldBankCurrency = fldBankCurrency;
        this.setRespondsToMode(DBConstants.INIT_MOVE, false);
        this.setRespondsToMode(DBConstants.READ_MOVE, false);
        super.init(null);
    }
    /**
     * FieldChanged Method.
     */
    public int fieldChanged(boolean bDisplayOption, int iMoveMode)
    {
        int iErrorCode = super.fieldChanged(bDisplayOption, iMoveMode);
        Record recVendor = ((ReferenceField)this.getOwner()).getReference();
        if (recVendor != null)
        {
            if (!recVendor.getField(m_fsVendorCurrency).equals(m_fldBankCurrency))
            {
                Task task = this.getOwner().getRecord().getRecordOwner().getTask();
                BaseApplication app = (BaseApplication)task.getApplication();
                String strError = app.getResources(ResourceConstants.ACCTPAY_RESOURCE, true).getString("Vendor currency must match bank currency");
                iErrorCode = task.setLastError(strError);
                this.getOwner().setData(null);
            }
        }
        return iErrorCode;
    }

}

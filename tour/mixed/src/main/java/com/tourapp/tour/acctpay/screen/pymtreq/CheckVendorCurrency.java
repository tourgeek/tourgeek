/**
 * @(#)CheckVendorCurrency.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.acctpay.screen.pymtreq;

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
import com.tourapp.tour.acctpay.db.*;
import com.tourapp.tour.assetdr.db.*;
import com.tourapp.tour.acctpay.screen.genpymt.*;
import com.tourapp.tour.acctpay.screen.check.*;
import org.jbundle.main.screen.*;

/**
 *  CheckVendorCurrency - .
 */
public class CheckVendorCurrency extends FieldListener
{
    protected BaseField m_fldBankCurrency = null;
    protected int m_fsVendorCurrency;
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
    public CheckVendorCurrency(int fsVendorCurrency, BaseField fldBankCurrency)
    {
        this();
        this.init(fsVendorCurrency, fldBankCurrency);
    }
    /**
     * Initialize class fields.
     */
    public void init(int fsVendorCurrency, BaseField fldBankCurrency)
    {
        m_fldBankCurrency = null;
        m_fsVendorCurrency = 0;
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

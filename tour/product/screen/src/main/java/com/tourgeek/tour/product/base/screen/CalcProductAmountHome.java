/**
  * @(#)CalcProductAmountHome.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.tour.product.base.screen;

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
import com.tourgeek.tour.product.base.db.*;
import com.tourgeek.tour.acctpay.db.*;
import com.tourgeek.tour.base.db.*;

/**
 *  CalcProductAmountHome - Calculate the Home Currency cost for this product..
 */
public class CalcProductAmountHome extends ChangeOnChangeHandler
{
    /**
     * Default constructor.
     */
    public CalcProductAmountHome()
    {
        super();
    }
    /**
     * CalcProductAmountHome Method.
     */
    public CalcProductAmountHome(BaseField fldTarget)
    {
        this();
        this.init(fldTarget);
    }
    /**
     * Initialize class fields.
     */
    public void init(BaseField fldTarget)
    {
        super.init(null, fldTarget, false);
    }
    /**
     * FieldChanged Method.
     */
    public int fieldChanged(boolean bDisplayOption, int iMoveMode)
    {
        Record recProduct = this.getOwner().getRecord();
        double dProductCost = this.getOwner().getValue();
        double dProductCostHome = this.convertLocalToHome(dProductCost);
        if (dProductCostHome != 0)
            m_fldTarget.setValue(dProductCostHome);
        else
            m_fldTarget.setData(null);
        return super.fieldChanged(bDisplayOption, iMoveMode);
    }
    /**
     * ConvertLocalToHome Method.
     */
    public double convertLocalToHome(double dAmountLocal)
    {
        Record recProduct = this.getOwner().getRecord();
        double dAmountHome = 0.00;
        if (dAmountLocal != 0)
        {
            Record recVendor = ((ReferenceField)recProduct.getField(Product.VENDOR_ID)).getReference();
            if (recVendor != null)
                if ((recVendor.getEditMode() == DBConstants.EDIT_IN_PROGRESS) || (recVendor.getEditMode() == DBConstants.EDIT_CURRENT))
                {
                    Record recCurrencys = ((ReferenceField)recVendor.getField(Vendor.CURRENCYS_ID)).getReference();
                    if (recCurrencys != null)
                        if ((recCurrencys.getEditMode() == DBConstants.EDIT_IN_PROGRESS) || (recCurrencys.getEditMode() == DBConstants.EDIT_CURRENT))
                        {
                            double dExchange = 1.0;
                            if (!recCurrencys.getField(Currencys.COSTING_RATE).isNull())
                                dExchange = recCurrencys.getField(Currencys.COSTING_RATE).getValue();
                            else
                                dExchange = recCurrencys.getField(Currencys.LAST_RATE).getValue();
                            dAmountHome = Math.floor(dAmountLocal * dExchange * 100.00 + 0.5) / 100.00;
                        }
                }
        }
        return dAmountHome;
    }

}

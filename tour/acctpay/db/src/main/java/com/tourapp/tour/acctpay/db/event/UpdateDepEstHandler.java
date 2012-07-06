/**
  * @(#)UpdateDepEstHandler.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.tour.acctpay.db.event;

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
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import com.tourapp.model.tour.product.base.db.*;
import com.tourapp.tour.acctpay.db.*;

/**
 *  UpdateDepEstHandler - Add the finalization estimate G/L posting.
 */
public class UpdateDepEstHandler extends UpdateApTrxHandler
{
    /**
     * Default constructor.
     */
    public UpdateDepEstHandler()
    {
        super();
    }
    /**
     * UpdateDepEstHandler Method.
     */
    public UpdateDepEstHandler(Record record)
    {
        this();
        this.init(record);
    }
    /**
     * Initialize class fields.
     */
    public void init(Record record)
    {
        super.init(record);
    }
    /**
     * Is this a new transaction (or a modification of a current transaction).
     * If it is not new, the system will calculate the current posting and do an adjusting entry.
     */
    public boolean isNewTrx(int iChangeType)
    {
        return (iChangeType == DBConstants.AFTER_ADD_TYPE);
    }
    /**
     * Get the transaction date.
     * @return The transaction date for this type of transaction.
     */
    public BaseField getTrxDate()
    {
        return this.getOwner().getField(ApTrx.DEPARTURE_DATE);
    }
    /**
     * Get the Debit Account field.
     * @return The debit account field.
     */
    public ReferenceField getDrAccount()
    {
        ProductCategoryModel recProductCat = this.getProductCategory();
        if (recProductCat != null)
            return (ReferenceField)recProductCat.getField(ProductCategoryModel.LAND_ACCOUNT_ID);    // Cost of tours
        return (ReferenceField)this.getApControl().getField(ApControl.COST_ACCOUNT_ID);    // Rarely
    }
    /**
     * Get the Credit Account field.
     * @return The credit account field.
     */
    public ReferenceField getCrAccount()
    {
        ProductCategoryModel recProductCat = this.getProductCategory();
        if (recProductCat != null)
            return (ReferenceField)recProductCat.getField(ProductCategoryModel.UNINV_ACCOUNT_ID);    // Uninvoiced est cost of tours
        return null;    // Never?
    }
    /**
     * Get the transaction amount for this type of transaction.
     * @param fldTypicalBalance The typical balance field (Debit/Credit/none).
     * @return The transaction amount.
     */
    public double getTrxAmount(BaseField fldTypicalBalance)
    {
        return this.getOwner().getField(ApTrx.DEPARTURE_ESTIMATE_LOCAL).getValue();
    }

}

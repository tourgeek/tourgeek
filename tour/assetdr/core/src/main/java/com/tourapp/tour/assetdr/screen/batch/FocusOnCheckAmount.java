/**
 * @(#)FocusOnCheckAmount.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.assetdr.screen.batch;

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
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import com.tourapp.tour.assetdr.db.*;
import com.tourapp.tour.genled.db.*;

/**
 *  FocusOnCheckAmount - Focus on the check/deposit check amount depending on the type..
 */
public class FocusOnCheckAmount extends FieldListener
{
    /**
     * Default constructor.
     */
    public FocusOnCheckAmount()
    {
        super();
    }
    /**
     * FocusOnCheckAmount Method.
     */
    public FocusOnCheckAmount(BaseField field)
    {
        this();
        this.init(field);
    }
    /**
     * Initialize class fields.
     */
    public void init(BaseField field)
    {
        super.init(field);
        m_bScreenMove = true;   // Only respond to user change
        m_bInitMove = false;
        m_bReadMove = false;
    }
    /**
     * FieldChanged Method.
     */
    public int fieldChanged(boolean bDisplayOption, int iMoveMode)
    {
        ReferenceField fldType = (ReferenceField)this.getOwner().getRecord().getField(BankTrxBatchDetail.TRX_STATUS_ID);
        Record recTrxStatus = fldType.getReference();
        if (recTrxStatus != null)
        {
            String strSignHint = recTrxStatus.getField(TrxStatus.PREFERRED_SIGN).toString();
            if (PreferredSignField.POSITIVE.equals(strSignHint))
            {
                BaseField fldAmount = this.getOwner().getRecord().getField(BankTrxBatchDetail.AMOUNT);
                ScreenField screenField = (ScreenField)fldAmount.getComponent(1);
                screenField.requestFocus();
            }
        }
        return super.fieldChanged(bDisplayOption, iMoveMode);
    }

}

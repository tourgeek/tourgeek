/**
 * @(#)SetProductTypeHandler.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.product.base.search.db;

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
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import com.tourapp.tour.product.base.search.screen.*;
import com.tourapp.tour.product.base.db.*;

/**
 *  SetProductTypeHandler - .
 */
public class SetProductTypeHandler extends FieldListener
{
    protected BaseField m_fldProductType = null;
    /**
     * Default constructor.
     */
    public SetProductTypeHandler()
    {
        super();
    }
    /**
     * SetProductTypeHandler Method.
     */
    public SetProductTypeHandler(BaseField fldProductType)
    {
        this();
        this.init(fldProductType);
    }
    /**
     * Initialize class fields.
     */
    public void init(BaseField fldProductType)
    {
        m_fldProductType = null;
        m_fldProductType = fldProductType;
        this.setRespondsToMode(DBConstants.READ_MOVE, false);
        super.init(null);
    }
    /**
     * FieldChanged Method.
     */
    public int fieldChanged(boolean bDisplayOption, int iMoveMode)
    {
        if (!this.getOwner().isNull())
        {
            Product recProduct = (Product)((ReferenceField)this.getOwner()).getReferenceRecord();
            ProductType recProductType = (ProductType)((ReferenceField)m_fldProductType).getReferenceRecord();
            if ((recProduct != null) && (recProductType != null))
            {
                int iProductType = recProductType.getProductTypeID(recProduct);
                m_fldProductType.setValue(iProductType, bDisplayOption, iMoveMode);
            }
        }
        return super.fieldChanged(bDisplayOption, iMoveMode);
    }

}

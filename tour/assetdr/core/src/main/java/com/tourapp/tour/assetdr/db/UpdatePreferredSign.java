/**
 * @(#)UpdatePreferredSign.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.assetdr.db;

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
import com.tourapp.tour.genled.db.*;

/**
 *  UpdatePreferredSign - .
 */
public class UpdatePreferredSign extends FieldListener
{
    protected BaseField m_fldSign = null;
    /**
     * Default constructor.
     */
    public UpdatePreferredSign()
    {
        super();
    }
    /**
     * UpdatePreferredSign Method.
     */
    public UpdatePreferredSign(BaseField field, BaseField fldSign)
    {
        this();
        this.init(field, fldSign);
    }
    /**
     * Init Method.
     */
    public void init(BaseField field, BaseField fldSign)
    {
        super.init(field);
        m_fldSign = fldSign;
        this.setRespondsToMode(DBConstants.READ_MOVE, false);
    }
    /**
     * FieldChanged Method.
     */
    public int fieldChanged(boolean bDisplayOption, int iMoveMode)
    {
        if (this.getOwner().getValue() > 0)
            m_fldSign.setString(PreferredSignField.POSITIVE);
        else if (this.getOwner().getValue() < 0)
            m_fldSign.setString(PreferredSignField.NEGATIVE);
        else
            m_fldSign.setString(DBConstants.BLANK);
        return super.fieldChanged(bDisplayOption, iMoveMode);
    }

}

/**
 * @(#)UpdateActiveTrxStatus.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.genled.db;

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

/**
 *  UpdateActiveTrxStatus - If this (TrxStatus) field changes, update the target ActiveTrx
field. This is typically added to BaseTrx records to automatically
retire completed transactions..
 */
public class UpdateActiveTrxStatus extends FieldListener
{
    protected BaseField m_fldActiveTrxTarget = null;
    /**
     * Default constructor.
     */
    public UpdateActiveTrxStatus()
    {
        super();
    }
    /**
     * UpdateActiveTrxStatus Method.
     */
    public UpdateActiveTrxStatus(BaseField fldActiveTrxTarget)
    {
        this();
        this.init(fldActiveTrxTarget);
    }
    /**
     * Initialize class fields.
     */
    public void init(BaseField fldActiveTrxTarget)
    {
        m_fldActiveTrxTarget = null;
        m_fldActiveTrxTarget  = fldActiveTrxTarget;
        this.setRespondsToMode(DBConstants.READ_MOVE, false);
        super.init(null);
    }
    /**
     * FieldChanged Method.
     */
    public int fieldChanged(boolean bDisplayOption, int iMoveMode)
    {
        TrxStatusField fldTrxStatusID = (TrxStatusField)this.getOwner();
        if (!fldTrxStatusID.isNull())
        {
            TrxStatus recTrxStatus = (TrxStatus)fldTrxStatusID.getReference();
            if (recTrxStatus != null)
            {
                m_fldActiveTrxTarget.moveFieldToThis(recTrxStatus.getField(TrxStatus.ACTIVE_TRX));
            }
        }
        return super.fieldChanged(bDisplayOption, iMoveMode);
    }

}

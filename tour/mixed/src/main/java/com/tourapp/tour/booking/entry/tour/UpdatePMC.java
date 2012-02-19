/**
 * @(#)UpdatePMC.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.booking.entry.tour;

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
import com.tourapp.tour.product.land.db.*;

/**
 *  UpdatePMC - .
 */
public class UpdatePMC extends FieldListener
{
    public static final int PMC_MAX = 999;
    protected BaseField m_fldPMC = null;
    /**
     * Default constructor.
     */
    public UpdatePMC()
    {
        super();
    }
    /**
     * UpdatePMC Method.
     */
    public UpdatePMC(BaseField fldPMC)
    {
        this();
        this.init(fldPMC);
    }
    /**
     * Initialize class fields.
     */
    public void init(BaseField fldPMC)
    {
        m_fldPMC = null;
        super.init(null);
        
        m_fldPMC = fldPMC;
        
        this.setRespondsToMode(DBConstants.INIT_MOVE, false);
        this.setRespondsToMode(DBConstants.READ_MOVE, false);
    }
    /**
     * FieldChanged Method.
     */
    public int fieldChanged(boolean bDisplayOption, int iMoveMode)
    {
        ReferenceField fldLandClass = (ReferenceField)this.getOwner();
        if (fldLandClass.isNull())
            m_fldPMC.setData(null, bDisplayOption, iMoveMode);
        else
        {
            Record recLandClass = fldLandClass.getReference();
            if (LandClass.PRIVATE_VEHICLE_CODE.equals(recLandClass.getField(LandClass.CODE).toString()))
                m_fldPMC.setValue(0);
            else if (LandClass.SEAT_IN_COACH_CODE.equals(recLandClass.getField(LandClass.CODE).toString()))
                m_fldPMC.setValue(PMC_MAX);
        }
        return super.fieldChanged(bDisplayOption, iMoveMode);
    }

}

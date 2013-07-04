
package com.tourgeek.tour.booking.entry.tour;

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
import com.tourgeek.tour.product.land.db.*;

/**
 *  UpdateLandClass - .
 */
public class UpdateLandClass extends FieldListener
{
    protected ReferenceField m_fldLandClass = null;
    /**
     * Default constructor.
     */
    public UpdateLandClass()
    {
        super();
    }
    /**
     * UpdateLandClass Method.
     */
    public UpdateLandClass(BaseField fldLandClass)
    {
        this();
        this.init(fldLandClass);
    }
    /**
     * Initialize class fields.
     */
    public void init(BaseField fldLandClass)
    {
        m_fldLandClass = null;
        m_fldLandClass = (ReferenceField)fldLandClass;
        super.init(null);
    }
    /**
     * FieldChanged Method.
     */
    public int fieldChanged(boolean bDisplayOption, int iMoveMode)
    {
        BaseField fldPMC = this.getOwner();
        if ((!fldPMC.isNull()) && (fldPMC.getValue() == 0))
            m_fldLandClass.setValue(m_fldLandClass.getIDFromCode(LandClass.PRIVATE_VEHICLE_CODE));
        else if (fldPMC.getValue() == UpdatePMC.PMC_MAX)
            m_fldLandClass.setValue(m_fldLandClass.getIDFromCode(LandClass.SEAT_IN_COACH_CODE));
        else
            m_fldLandClass.setData(null, bDisplayOption, iMoveMode);
        return super.fieldChanged(bDisplayOption, iMoveMode);
    }
    /**
     * Set the object that owns this listener.
     * @owner The object that this listener is being added to (if null, this listener is being removed).
     */
    public void setOwner(ListenerOwner owner)
    {
        super.setOwner(owner);
        if (this.getOwner() != null)
            this.fieldChanged(true, DBConstants.INIT_MOVE);
    }

}

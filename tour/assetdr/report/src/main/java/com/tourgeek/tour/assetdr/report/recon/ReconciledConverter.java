
package com.tourgeek.tour.assetdr.report.recon;

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

/**
 *  ReconciledConverter - This is a special converter that displays a check if the reconciled date
is non-null and sets it to the date if checked, null if unchecked.
 */
public class ReconciledConverter extends CheckConverter
{
    /**
     * Default constructor.
     */
    public ReconciledConverter()
    {
        super();
    }
    /**
     * Constructor.
     */
    public ReconciledConverter(Converter converter, BaseField fldTargetValue, String strAltFieldDesc, boolean bTrueIfMatch)
    {
        this();
        this.init(converter, fldTargetValue, strAltFieldDesc, bTrueIfMatch);
    }
    /**
     * Initialize class fields.
     */
    public void init(Converter converter, BaseField fldTargetValue, String strAltFieldDesc, boolean bTrueIfMatch)
    {
        super.init(converter, null, fldTargetValue, strAltFieldDesc, bTrueIfMatch, null);
    }
    /**
     * Get the state of this boolean field.
     * Usually overidden.
     * @return True if data is true.
     */
    public boolean getState()
    {
        if ((this.getField().isNull()) || (this.getField().getLength() == 0))
            return false;
        else
            return true;
    }
    /**
     * Set the state of this field for binary fields (don't override this).
     * Usually overidden.
     * @param bState the state to set the data to.
     * @param bDisplayOption Display the data on the screen if true.
     * @param iMoveMode INIT, SCREEN, or READ move mode.
     * @return The error code.
     */
    public int setState(boolean bState, boolean bDisplayOption, int iMoveMode)
    {
        if (bState)
            return ((BaseField)this.getField()).moveFieldToThis((BaseField)m_fldTargetValue, bDisplayOption, iMoveMode);
        else
            return this.getField().setData(null, bDisplayOption, iMoveMode);
    }

}

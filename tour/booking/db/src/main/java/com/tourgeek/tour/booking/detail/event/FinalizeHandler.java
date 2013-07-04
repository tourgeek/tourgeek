
package com.tourgeek.tour.booking.detail.event;

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
import com.tourgeek.tour.booking.db.*;

/**
 *  FinalizeHandler - Set up the ApTrx (fin ests) when the tour is finalized.
 */
public class FinalizeHandler extends FieldListener
{
    /**
     * Default constructor.
     */
    public FinalizeHandler()
    {
        super();
    }
    /**
     * FinalizeHandler Method.
     */
    public FinalizeHandler(BaseField field)
    {
        this();
        this.init(field);
    }
    /**
     * Init Method.
     */
    public void init(BaseField field)
    {
        super.init(field);
        this.setRespondsToMode(DBConstants.INIT_MOVE, false);
        this.setRespondsToMode(DBConstants.READ_MOVE, false);
    }
    /**
     * FieldChanged Method.
     */
    public int fieldChanged(boolean bDisplayOption, int iMoveMode)
    {
        if (this.getOwner().getRecord().getField(Tour.FINALIZED).getState() == true)
            ((Tour)this.getOwner().getRecord()).updateTourApTrx(null, null);
        return super.fieldChanged(bDisplayOption, iMoveMode);
    }

}

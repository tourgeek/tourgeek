
package com.tourgeek.tour.acctrec.db.event;

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
import com.tourgeek.tour.acctrec.db.*;

/**
 *  CalcMcoHandler - Calculate the MCO Net.
 */
public class CalcMcoHandler extends FieldListener
{
    /**
     * Default constructor.
     */
    public CalcMcoHandler()
    {
        super();
    }
    /**
     * CalcMcoHandler Method.
     */
    public CalcMcoHandler(BaseField field)
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
        this.setRespondsToMode(DBConstants.INIT_MOVE, false); // Only on screen change
        this.setRespondsToMode(DBConstants.READ_MOVE, false);
    }
    /**
     * FieldChanged Method.
     */
    public int fieldChanged(boolean bDisplayOption, int iMoveMode)
    {
        double dGross = this.getOwner().getRecord().getField(Mco.GROSS).getValue();
        double dCommAmt = this.getOwner().getRecord().getField(Mco.COMM_AMT).getValue();
        double dSvcAmt = this.getOwner().getRecord().getField(Mco.SVC_AMT).getValue();
        double dTaxAmt = this.getOwner().getRecord().getField(Mco.TAX_AMT).getValue();
        double dNet = Math.floor((dGross - dCommAmt - dSvcAmt - dTaxAmt) * 100.00 + 0.5) / 100.00;
        this.getOwner().getRecord().getField(Mco.NET).setValue(dNet);
        return super.fieldChanged(bDisplayOption, iMoveMode);
    }

}

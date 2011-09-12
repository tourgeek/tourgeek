/**
 * @(#)CalcMcoHandler.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.acctrec.screen.mco;

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
import com.tourapp.tour.acctrec.db.*;
import com.tourapp.tour.acctrec.screen.cash.*;
import com.tourapp.tour.genled.db.*;

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
        double dGross = this.getOwner().getRecord().getField(Mco.kGross).getValue();
        double dCommAmt = this.getOwner().getRecord().getField(Mco.kCommAmt).getValue();
        double dSvcAmt = this.getOwner().getRecord().getField(Mco.kSvcAmt).getValue();
        double dTaxAmt = this.getOwner().getRecord().getField(Mco.kTaxAmt).getValue();
        double dNet = Math.floor((dGross - dCommAmt - dSvcAmt - dTaxAmt) * 100.00 + 0.5) / 100.00;
        this.getOwner().getRecord().getField(Mco.kNet).setValue(dNet);
        return super.fieldChanged(bDisplayOption, iMoveMode);
    }

}

/**
 * @(#)McoSubmitCalcNetBeh.
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
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import org.jbundle.base.screen.model.report.*;
import com.tourapp.tour.acctrec.db.*;
import com.tourapp.tour.genled.db.*;

/**
 *  McoSubmitCalcNetBeh - Calculate the net MCO amount.
 */
public class McoSubmitCalcNetBeh extends FileListener
{
    /**
     * Default constructor.
     */
    public McoSubmitCalcNetBeh()
    {
        super();
    }
    /**
     * McoSubmitCalcNetBeh Method.
     */
    public McoSubmitCalcNetBeh(Record record)
    {
        this();
        this.init(record);
    }
    /**
     * Initialize class fields.
     */
    public void init(Record record)
    {
        super.init(record);
    }
    /**
     * Calculate the MCO net amount.
     */
    public void doValidRecord(boolean bDisplayOption)
    {
        super.doValidRecord(bDisplayOption);
        double dNet = 0;
        Record recMco = this.getOwner();
        double dGross = recMco.getField(Mco.kGross).getValue();
        double dCommission = recMco.getField(Mco.kCommAmt).getValue();
        double dTaxes = recMco.getField(Mco.kTaxAmt).getValue();
        double dServiceChargePer = recMco.getRecordOwner().getScreenRecord().getField(McoScreenRecord.kServiceCharge).getValue();
        double dServiceCharge = (Math.floor(dGross * dServiceChargePer * 100.00 + 0.5)) / 100.00;
        dNet = dGross - dCommission - dTaxes - dServiceCharge;
        recMco.getRecordOwner().getScreenRecord().getField(McoScreenRecord.kNet).setValue(dNet);
    }

}

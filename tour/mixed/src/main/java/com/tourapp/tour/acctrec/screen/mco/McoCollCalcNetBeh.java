/**
 *  @(#)McoCollCalcNetBeh.
 *  Copyright © 2010 tourapp.com. All rights reserved.
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
import com.tourapp.tour.genled.db.*;
import com.tourapp.tour.acctrec.screen.cash.*;
import com.tourapp.tour.product.air.db.*;

/**
 *  McoCollCalcNetBeh - Calc the Service charge and net on this Mco.
 */
public class McoCollCalcNetBeh extends FileListener
{
    /**
     * Default constructor.
     */
    public McoCollCalcNetBeh()
    {
        super();
    }
    /**
     * McoCollCalcNetBeh Method.
     */
    public McoCollCalcNetBeh(Record record)
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
     * Called when a valid record is read from the table/query.
     * @param bDisplayOption If true, display any changes.
     */
    public void doValidRecord(boolean bDisplayOption)
    {
        super.doValidRecord(bDisplayOption);
        double dNet = 0;
        Record recMco = this.getOwner();
        double dGross = recMco.getField(Mco.kGross).getValue();
        double dCommission = recMco.getField(Mco.kCommAmt).getValue();
        double dTaxes = recMco.getField(Mco.kTaxAmt).getValue();
        double dServiceChargePer = recMco.getField(Mco.kCarrierSvcPer).getValue();
        double dServiceCharge = (Math.floor(dGross * dServiceChargePer * 100 + 0.5)) / 100;
        dNet = dGross - dCommission - dTaxes - dServiceCharge;
        recMco.getRecordOwner().getScreenRecord().getField(McoScreenRecord.kServiceCharge).setValue(dServiceCharge);
        recMco.getRecordOwner().getScreenRecord().getField(McoScreenRecord.kNet).setValue(dNet);
    }

}

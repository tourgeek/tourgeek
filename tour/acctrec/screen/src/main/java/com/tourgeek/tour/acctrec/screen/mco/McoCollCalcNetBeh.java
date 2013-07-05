/**
  * @(#)McoCollCalcNetBeh.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.tour.acctrec.screen.mco;

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
import org.jbundle.base.screen.model.*;
import com.tourgeek.tour.acctrec.db.*;
import com.tourgeek.tour.genled.db.*;
import com.tourgeek.tour.acctrec.screen.cash.*;
import com.tourgeek.tour.product.air.db.*;

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
        double dGross = recMco.getField(Mco.GROSS).getValue();
        double dCommission = recMco.getField(Mco.COMM_AMT).getValue();
        double dTaxes = recMco.getField(Mco.TAX_AMT).getValue();
        double dServiceChargePer = recMco.getField(Mco.CARRIER_SVC_PER).getValue();
        double dServiceCharge = (Math.floor(dGross * dServiceChargePer * 100 + 0.5)) / 100;
        dNet = dGross - dCommission - dTaxes - dServiceCharge;
        recMco.getRecordOwner().getScreenRecord().getField(McoScreenRecord.SERVICE_CHARGE).setValue(dServiceCharge);
        recMco.getRecordOwner().getScreenRecord().getField(McoScreenRecord.NET).setValue(dNet);
    }

}

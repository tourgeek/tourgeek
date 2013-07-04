
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
import org.jbundle.base.screen.model.report.*;
import com.tourgeek.tour.acctrec.db.*;
import com.tourgeek.tour.genled.db.*;
import org.jbundle.base.screen.model.util.*;

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
        double dGross = recMco.getField(Mco.GROSS).getValue();
        double dCommission = recMco.getField(Mco.COMM_AMT).getValue();
        double dTaxes = recMco.getField(Mco.TAX_AMT).getValue();
        double dServiceChargePer = recMco.getRecordOwner().getScreenRecord().getField(McoScreenRecord.SERVICE_CHARGE).getValue();
        double dServiceCharge = (Math.floor(dGross * dServiceChargePer * 100.00 + 0.5)) / 100.00;
        dNet = dGross - dCommission - dTaxes - dServiceCharge;
        recMco.getRecordOwner().getScreenRecord().getField(McoScreenRecord.NET).setValue(dNet);
    }

}

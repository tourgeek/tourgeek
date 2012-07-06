/**
  * @(#)McoDistConverter.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.tour.acctrec.screen.mco.dist;

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
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import com.tourapp.tour.acctrec.screen.cash.dist.*;
import com.tourapp.tour.assetdr.db.*;
import com.tourapp.tour.acctrec.db.*;

/**
 *  McoDistConverter - .
 */
public class McoDistConverter extends CashDistConverter
{
    /**
     * Default constructor.
     */
    public McoDistConverter()
    {
        super();
    }
    /**
     * Constructor.
     */
    public McoDistConverter(Converter converter)
    {
        this();
        this.init(converter);
    }
    /**
     * Initialize class fields.
     */
    public void init(BaseField field)
    {
        super.init(field);
    }
    /**
     * Create a new dist record using the detail record information.
     */
    public void createNewDist(BaseField referenceField) throws DBException
    {
        // Now create the new distribution
        Record recCashBatchDetail = this.getDetailRecord();
        m_recBankTrxBatchDist.addNew();
        m_recBankTrxBatchDist.getField(CashBatchDist.BOOKING_ID).moveFieldToThis(referenceField);
        m_recBankTrxBatchDist.getField(CashBatchDist.AMOUNT).moveFieldToThis(recCashBatchDetail.getField(Mco.NET));
        m_recBankTrxBatchDist.add();
    }
    /**
     * Get the detail record (dist's header) for this record.
     */
    public Record getDetailRecord()
    {
        return (Record)((BaseField)this.getField()).getRecord().getRecordOwner().getRecord(Mco.MCO_FILE);
    }
    /**
     * CreateDistRecord Method.
     */
    public CashBatchDist createDistRecord(RecordOwner recordOwner)
    {
        return new McoBatchDist(recordOwner);
    }
    /**
     * Set up the default control for this field.
     * @param  itsLocation     Location of this component on screen (ie., GridBagConstraint).
     * @param  targetScreen    Where to place this component (ie., Parent screen or GridBagLayout).
     * @param  iDisplayFieldDesc Display the label? (optional).
     * @return   Return the component or ScreenField that is created for this field.
     */
    public ScreenComponent setupDefaultView(ScreenLoc itsLocation, ComponentParent targetScreen, Convert converter, int iDisplayFieldDesc, Map<String, Object> properties)
    {
        ScreenComponent sField = super.setupDefaultView(itsLocation, targetScreen, converter, iDisplayFieldDesc, properties);
        ((BaseField)this.getField()).getRecord().removeListener(((BaseField)this.getField()).getRecord().getListener(AddNewCashDistHandler.class.getName()), true);
        ((BaseField)this.getField()).getRecord().addListener(new AddNewMcoDistHandler(null));
        return sField;
    }

}

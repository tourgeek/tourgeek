/**
 * @(#)MoveEstimateHandler.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.acctpay.report.tourdet;

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
import com.tourapp.tour.genled.db.*;
import org.jbundle.base.screen.model.report.*;
import com.tourapp.tour.acctpay.db.*;
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.product.tour.db.*;
import com.tourapp.tour.acctpay.report.*;

/**
 *  MoveEstimateHandler - If this is an estimate, move the values into the invoice fields.
 */
public class MoveEstimateHandler extends FileListener
{
    protected int m_iDepartureEstimate = -1;
    protected int m_iDepartureEstimateManual = -1;
    /**
     * Default constructor.
     */
    public MoveEstimateHandler()
    {
        super();
    }
    /**
     * MoveEstimateHandler Method.
     */
    public MoveEstimateHandler(Record record)
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
        Record recApTrx = this.getOwner();
        if (m_iDepartureEstimate == -1)
        {
            TrxStatus recTrxStatus = new TrxStatus(Utility.getRecordOwner(this.getOwner()));  // Rarely, but if it doesn't exist in the screen, add it!
            m_iDepartureEstimate = recTrxStatus.getTrxStatusID(TransactionType.ACCTPAY, ApTrx.kApTrxFile, ApTrx.DEPARTURE_ESTIMATE);
            m_iDepartureEstimateManual = recTrxStatus.getTrxStatusID(TransactionType.ACCTPAY, ApTrx.kApTrxFile, ApTrx.DEPARTURE_EST_MANUAL);
            recTrxStatus.free();
        }
        if ((recApTrx.getField(ApTrx.kTrxStatusID).getValue() == m_iDepartureEstimate)
            || (recApTrx.getField(ApTrx.kTrxStatusID).getValue() == m_iDepartureEstimateManual))
        {
            recApTrx.getField(ApTrx.kInvoiceAmount).moveFieldToThis(recApTrx.getField(ApTrx.kDepartureEstimate));
            recApTrx.getField(ApTrx.kInvoiceBalance).moveFieldToThis(recApTrx.getField(ApTrx.kDepartureEstimate));
            recApTrx.getField(ApTrx.kInvoiceBalanceLocal).moveFieldToThis(recApTrx.getField(ApTrx.kDepartureEstimateLocal));
        }
    }

}

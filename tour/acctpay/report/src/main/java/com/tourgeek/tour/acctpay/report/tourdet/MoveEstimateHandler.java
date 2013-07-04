
package com.tourgeek.tour.acctpay.report.tourdet;

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
import com.tourgeek.tour.genled.db.*;
import org.jbundle.base.screen.model.report.*;
import com.tourgeek.tour.acctpay.db.*;
import com.tourgeek.tour.booking.db.*;
import com.tourgeek.tour.product.tour.db.*;
import com.tourgeek.tour.acctpay.report.*;
import org.jbundle.base.screen.model.util.*;

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
            TrxStatus recTrxStatus = new TrxStatus(this.getOwner().findRecordOwner());  // Rarely, but if it doesn't exist in the screen, add it!
            m_iDepartureEstimate = recTrxStatus.getTrxStatusID(TransactionType.ACCTPAY, ApTrx.AP_TRX_FILE, ApTrx.DEP_ESTIMATE);
            m_iDepartureEstimateManual = recTrxStatus.getTrxStatusID(TransactionType.ACCTPAY, ApTrx.AP_TRX_FILE, ApTrx.DEPARTURE_EST_MANUAL);
            recTrxStatus.free();
        }
        if ((recApTrx.getField(ApTrx.TRX_STATUS_ID).getValue() == m_iDepartureEstimate)
            || (recApTrx.getField(ApTrx.TRX_STATUS_ID).getValue() == m_iDepartureEstimateManual))
        {
            recApTrx.getField(ApTrx.INVOICE_AMOUNT).moveFieldToThis(recApTrx.getField(ApTrx.DEPARTURE_ESTIMATE));
            recApTrx.getField(ApTrx.INVOICE_BALANCE).moveFieldToThis(recApTrx.getField(ApTrx.DEPARTURE_ESTIMATE));
            recApTrx.getField(ApTrx.INVOICE_BALANCE_LOCAL).moveFieldToThis(recApTrx.getField(ApTrx.DEPARTURE_ESTIMATE_LOCAL));
        }
    }

}

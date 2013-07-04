
package com.tourgeek.tour.acctrec.screen.misc;

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
import com.tourgeek.tour.booking.entry.acctrec.*;
import com.tourgeek.tour.acctrec.db.*;
import com.tourgeek.tour.booking.db.*;
import com.tourgeek.tour.genled.db.*;
import com.tourgeek.tour.acctrec.db.event.*;
import com.tourgeek.tour.product.base.db.*;

/**
 *  SetCrDrTypeHandler - Set this A/R Trx to a Credit or Debit Memo depending on whether it
is positive or negative..
 */
public class SetCrDrTypeHandler extends FieldListener
{
    protected TrxStatus m_recTrxStatus = null;
    /**
     * Default constructor.
     */
    public SetCrDrTypeHandler()
    {
        super();
    }
    /**
     * SetCrDrTypeHandler Method.
     */
    public SetCrDrTypeHandler(BaseField field)
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
     * Free the resources.
     */
    public void free()
    {
        if (m_recTrxStatus != null)
            m_recTrxStatus.free();
        m_recTrxStatus = null;
    }
    /**
     * FieldChanged Method.
     */
    public int fieldChanged(boolean bDisplayOption, int iMoveMode)
    {
        if (m_recTrxStatus == null)
        {
            RecordOwner recordOwner = this.getOwner().getRecord().findRecordOwner();
            m_recTrxStatus = new TrxStatus(recordOwner);
                              if (recordOwner != null)
                                  recordOwner.removeRecord(m_recTrxStatus);
        }
        if (this.getOwner().getValue() < 0)
            m_recTrxStatus.getTrxStatusID(TransactionType.ACCTREC, ArTrx.AR_TRX_FILE, ArTrx.CREDIT_MEMO);
        else
            m_recTrxStatus.getTrxStatusID(TransactionType.ACCTREC, ArTrx.AR_TRX_FILE, ArTrx.DEBIT_MEMO);
        this.getOwner().getRecord().getField(ArTrx.TRX_STATUS_ID).moveFieldToThis(m_recTrxStatus.getField(TrxStatus.ID));
        return super.fieldChanged(bDisplayOption, iMoveMode);
    }

}

/**
 *  @(#)ReadLinkedTrxHandler.
 *  Copyright © 2010 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.acctpay.screen.hist;

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
import org.jbundle.main.screen.*;
import com.tourapp.tour.assetdr.db.*;
import com.tourapp.tour.genled.db.*;
import com.tourapp.tour.acctpay.screen.trx.*;
import com.tourapp.tour.acctpay.db.*;

/**
 *  ReadLinkedTrxHandler - .
 */
public class ReadLinkedTrxHandler extends ReadSecondaryHandler
{
    /**
     * Default constructor.
     */
    public ReadLinkedTrxHandler()
    {
        super();
    }
    /**
     * ReadLinkedTrxHandler Method.
     */
    public ReadLinkedTrxHandler(Record record)
    {
        this();
        this.init(record);
    }
    /**
     * Initialize class fields.
     */
    public void init(Record record)
    {
        super.init(null, record, DBConstants.MAIN_KEY_AREA, true, false, true);
    }
    /**
     * FieldChanged Method.
     */
    public int fieldChanged(boolean bDisplayOption, int iMoveMode)
    {
        Record recTrxDesc = ((ReferenceField)this.getOwner().getRecord().getField(PaymentHistory.kLinkedTrxDescID)).getReference();
        if (recTrxDesc != null)
            if (BankTrx.kBankTrxFile.equalsIgnoreCase(recTrxDesc.getField(TrxDesc.kDescCode).toString()))
                return super.fieldChanged(bDisplayOption, iMoveMode);
        try {
            m_record.addNew();
        } catch (DBException e) {
        }
        return DBConstants.NORMAL_RETURN;
    }

}

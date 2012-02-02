/**
 * @(#)CurrentClearedHandler.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.assetdr.report.recon;

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
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import com.tourapp.tour.assetdr.db.*;

/**
 *  CurrentClearedHandler - When this bank account changes, recalc the starting cleared balance.
 */
public class CurrentClearedHandler extends FieldListener
{
    protected DateTimeField m_fldDateCurrentCleared = null;
    protected CurrencyField m_fldStartCleared = null;
    protected BankTrx m_recBankTrx = null;
    /**
     * Default constructor.
     */
    public CurrentClearedHandler()
    {
        super();
    }
    /**
     * CurrentClearedHandler Method.
     */
    public CurrentClearedHandler(DateTimeField fldDateCurrentCleared, CurrencyField fldStartCleared)
    {
        this();
        this.init(fldDateCurrentCleared, fldStartCleared);
    }
    /**
     * Initialize class fields.
     */
    public void init(DateTimeField fldDateCurrentCleared, CurrencyField fldStartCleared)
    {
        m_fldDateCurrentCleared = null;
        m_fldStartCleared = null;
        m_recBankTrx = null;
        m_fldDateCurrentCleared = fldDateCurrentCleared;
        m_fldStartCleared = fldStartCleared;
        
        super.init(null);
        
        m_bReadMove = false;
        m_bInitMove = false;

    }
    /**
     * Set the object that owns this listener.
     * @owner The object that this listener is being added to (if null, this listener is being removed).
     */
    public void setOwner(ListenerOwner owner)
    {
        super.setOwner(owner);
        if (owner != null)
        {
            if (m_recBankTrx == null)
            {
                RecordOwner recordOwner = this.getOwner().getRecord().findRecordOwner();
                m_recBankTrx = new BankTrx(recordOwner);
                if (recordOwner != null)
                    recordOwner.removeRecord(m_recBankTrx);
                m_recBankTrx.setKeyArea(BankTrx.TRX_DATE_KEY);
                m_recBankTrx.addListener(new SubFileFilter(this.getOwner(), BankTrx.BANK_ACCT_ID, null, null, null, null));
                m_recBankTrx.addListener(new CompareFileFilter(m_recBankTrx.getField(BankTrx.DATE_RECONCILED), m_fldDateCurrentCleared, "<=", null, false));
                m_recBankTrx.addListener(new SubCountHandler(m_fldStartCleared, BankTrx.AMOUNT, true, false));    // Init this field override for other value
                this.fieldChanged(DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
            }
        }
        else
        {
           if (m_recBankTrx != null)
            {
                m_recBankTrx.free();
                m_recBankTrx = null;
            }
        }
    }
    /**
     * The field changed, recalc the starting balance.
     */
    public int fieldChanged(boolean bDisplayOption, int iMoveMode)
    {
        try {
            m_recBankTrx.close();
            while (m_recBankTrx.hasNext())
            {
                m_recBankTrx.next();
            }
        } catch (DBException ex)    {
            ex.printStackTrace();
        }
        return super.fieldChanged(bDisplayOption, iMoveMode);
    }

}

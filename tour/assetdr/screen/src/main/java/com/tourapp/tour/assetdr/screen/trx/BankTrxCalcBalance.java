/**
  * @(#)BankTrxCalcBalance.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.tour.assetdr.screen.trx;

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
import com.tourapp.tour.assetdr.db.*;

/**
 *  BankTrxCalcBalance - Calculate the balance for the bank transactions.
 */
public class BankTrxCalcBalance extends FieldListener
{
    protected BaseField m_fldBalance = null;
    protected BaseField m_fldFlag = null;
    protected Record m_recBankAcct = null;
    protected BankTrx m_recBankTrx = null;
    /**
     * Default constructor.
     */
    public BankTrxCalcBalance()
    {
        super();
    }
    /**
     * Constructor.
     */
    public BankTrxCalcBalance(Record recBankAcct, BaseField fldBalance, BaseField fldFlag)
    {
        this();
        this.init(recBankAcct, fldBalance, fldFlag);
    }
    /**
     * Initialize class fields.
     */
    public void init(Record recBankAcct, BaseField fldBalance, BaseField fldFlag)
    {
        m_fldBalance = null;
        m_fldFlag = null;
        m_recBankAcct = null;
        m_recBankTrx = null;
        m_recBankAcct = recBankAcct;
        m_fldBalance = fldBalance;
        m_fldFlag = fldFlag;
        super.init(null);
    }
    /**
     * Free the resources.
     */
    public void free()
    {
        if (m_recBankTrx != null)
            m_recBankTrx.free();
        m_recBankTrx = null;
        super.free();
    }
    /**
     * Set the object that owns this listener.
     * @owner The object that this listener is being added to (if null, this listener is being removed).
     */
    public void setOwner(ListenerOwner owner)
    {
        super.setOwner(owner);
        if (owner != null)
            this.fieldChanged(DBConstants.DISPLAY, DBConstants.INIT_MOVE);  // Do this
    }
    /**
     * FieldChanged Method.
     */
    public int fieldChanged(boolean bDisplayOption, int iMoveMode)
    {
        int iErrorCode = super.fieldChanged(bDisplayOption, iMoveMode);
        if (iErrorCode != DBConstants.NORMAL_RETURN)
            return iErrorCode;
        if ((m_fldFlag == null) || (m_fldFlag.getState()))
        {
            {
                if (m_recBankTrx == null)
                {
                    RecordOwner recordOwner = this.getOwner().getRecord().findRecordOwner();
                    m_recBankTrx = new BankTrx(recordOwner);
                    if (recordOwner != null)
                        recordOwner.removeRecord(m_recBankTrx);
                    m_recBankTrx.addListener(new SubFileFilter(m_recBankAcct));
        
                    m_recBankTrx.addListener(new SubCountHandler(m_fldBalance, BankTrx.AMOUNT, false, true));   // Init this field override for other value
                }
                m_fldBalance.initField(DBConstants.DISPLAY);
                m_fldBalance.setValue(0.00);
                m_recBankTrx.close();
                try   {
                    while (m_recBankTrx.hasNext())
                    {   // Go through and count
                        m_recBankTrx.next();
                    }
                } catch (DBException ex)    {
                    ex.printStackTrace();
                }
            }
        }
        return iErrorCode;
    }

}

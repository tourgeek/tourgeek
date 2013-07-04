
package com.tourgeek.tour.assetdr.db;

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

/**
 *  CalcNewBal - Add transactions to get bal.
 */
public class CalcNewBal extends SubFileFilter
{
    protected BaseField m_fldDate = null;
    /**
     * Default constructor.
     */
    public CalcNewBal()
    {
        super();
    }
    /**
     * CalcNewBal Method.
     */
    public CalcNewBal(BaseField mainFilesField, BaseField fldDate)
    {
        this();
        this.init(mainFilesField, fldDate);
    }
    /**
     * Initialize class fields.
     */
    public void init(BaseField mainFilesField, BaseField fldDate)
    {
        m_fldDate = null;
        m_fldDate = fldDate;
        super.init(null, null, null, null, null, mainFilesField, BankTrx.BANK_ACCT_ID, null, null, false, false, false);
    }
    /**
     * DoEndKey Method.
     */
    public void doEndKey()
    {
        this.getOwner().getField(BankTrx.TRX_DATE).moveFieldToThis(m_fldDate, DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
        super.doEndKey();
    }
    /**
     * DoKeyOnly Method.
     */
    public boolean doKeyOnly()
    {
        return true;
    }

}

/**
 *  @(#)CalcNewBal.
 *  Copyright © 2010 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.assetdr.db;

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
import com.tourapp.tour.assetdr.screen.*;
import com.tourapp.tour.assetdr.screen.trx.*;

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
        super.init(null, null, -1, mainFilesField, BankTrx.kBankAcctID, null, -1, null, -1, false, false, false);
        m_fldDate = fldDate;
    }
    /**
     * DoEndKey Method.
     */
    public void doEndKey()
    {
        this.getOwner().getField(BankTrx.kTrxDate).moveFieldToThis(m_fldDate, DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
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

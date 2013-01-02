/**
  * @(#)CalcEmpTaxesHandler.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.tour.payroll.db;

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
 *  CalcEmpTaxesHandler - Calc the taxes for this emp.
 */
public class CalcEmpTaxesHandler extends FileListener
{
    protected Record m_EmpControl = null;
    protected Record m_Employee = null;
    protected Record m_PrDedEarn = null;
    protected Record m_PrTaxRates = null;
    protected Record m_QueryInfo = null;
    /**
     * Default constructor.
     */
    public CalcEmpTaxesHandler()
    {
        super();
    }
    /**
     * CalcEmpTaxesHandler Method.
     */
    public CalcEmpTaxesHandler(Record queryInfo, Record employee, Record prDedEarn, Record prTaxRates, Record empControl)
    {
        this();
        this.init(queryInfo, employee, prDedEarn, prTaxRates, empControl);
    }
    /**
     * Initialize class fields.
     */
    public void init(Record queryInfo, Record employee, Record prDedEarn, Record prTaxRates, Record empControl)
    {
        m_EmpControl = null;
        m_Employee = null;
        m_PrDedEarn = null;
        m_PrTaxRates = null;
        m_QueryInfo = null;
        m_QueryInfo = queryInfo;
        m_Employee = employee;
        m_PrDedEarn = prDedEarn;
        m_PrTaxRates = prTaxRates;
        m_EmpControl = empControl;
        super.init(null);
    }
    /**
     * Called when a valid record is read from the table/query.
     * @param bDisplayOption If true, display any changes.
     */
    public void doValidRecord(boolean bDisplayOption)
    {
        super.doValidRecord(bDisplayOption);
        double checkNo = 1234;
        ((NumberField)this.getOwner().getField(TimeTrx.PR_CHECK_NUM)).setValue(checkNo);
        checkNo++;
        ((TimeTrx)this.getOwner()).calcPay(m_QueryInfo, m_Employee, m_PrDedEarn, m_PrTaxRates, m_EmpControl);
        //?   this.getOwner().UpdateQuery();
    }

}

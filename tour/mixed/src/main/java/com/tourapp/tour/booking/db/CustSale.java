/**
 * @(#)CustSale.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.booking.db;

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

/**
 *  CustSale - Customer Sale Base File Class.
 */
public class CustSale extends VirtualRecord
{
    private static final long serialVersionUID = 1L;

    //public static final int kID = kID;
    public static final int kCustSaleDate = kVirtualRecordLastField + 1;
    public static final int kCustSaleAgent = kCustSaleDate + 1;
    public static final int kCustSaleCustID = kCustSaleAgent + 1;
    public static final int kCustSaleCustNo = kCustSaleCustID + 1;
    public static final int kCustSaleLastField = kCustSaleCustNo;
    public static final int kCustSaleFields = kCustSaleCustNo - DBConstants.MAIN_FIELD + 1;
    /**
     * Default constructor.
     */
    public CustSale()
    {
        super();
    }
    /**
     * Constructor.
     */
    public CustSale(RecordOwner screen)
    {
        this();
        this.init(screen);
    }
    /**
     * Initialize class fields.
     */
    public void init(RecordOwner screen)
    {
        super.init(screen);
    }

    public static final String kCustSaleFile = null;    // Screen field
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        if (iFieldSeq == kID)
        {
            field = new CounterField(this, "ID", 6, null, null);
            field.setHidden(true);
        }
        if (iFieldSeq == kCustSaleDate)
            field = new CustSale_CustSaleDate(this, "CustSaleDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kCustSaleAgent)
            field = new ReferenceField(this, "CustSaleAgent", 6, null, null);
        if (iFieldSeq == kCustSaleCustID)
            field = new ReferenceField(this, "CustSaleCustID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kCustSaleCustNo)
            field = new StringField(this, "CustSaleCustNo", 16, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kCustSaleLastField)
                field = new EmptyField(this);
        }
        return field;
    }

}

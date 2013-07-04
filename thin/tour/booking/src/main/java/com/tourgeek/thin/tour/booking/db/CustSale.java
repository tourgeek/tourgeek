/**
  * @(#)CustSale.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.thin.tour.booking.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

import com.tourapp.model.tour.booking.db.*;

public class CustSale extends FieldList
    implements CustSaleModel
{
    private static final long serialVersionUID = 1L;


    public CustSale()
    {
        super();
    }
    public CustSale(Object recordOwner)
    {
        this();
        this.init(recordOwner);
    }

}

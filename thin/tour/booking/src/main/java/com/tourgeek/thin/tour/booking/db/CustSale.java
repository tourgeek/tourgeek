/**
  * @(#)CustSale.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.thin.tour.booking.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

import com.tourgeek.model.tour.booking.db.*;

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

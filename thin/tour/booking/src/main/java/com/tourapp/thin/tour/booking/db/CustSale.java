/**
 * @(#)CustSale.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.thin.tour.booking.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

import com.tourapp.model.tour.booking.db.*;

public class CustSale extends FieldList
    implements CustSaleModel
{

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

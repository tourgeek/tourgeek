/**
 * @(#)BaseProductStatus.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.thin.tour.product.base.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

import org.jbundle.thin.main.db.base.*;
import com.tourapp.model.tour.product.base.db.*;

public class BaseProductStatus extends BaseStatus
    implements BaseProductStatusModel
{
    private static final long serialVersionUID = 1L;


    public BaseProductStatus()
    {
        super();
    }
    public BaseProductStatus(Object recordOwner)
    {
        this();
        this.init(recordOwner);
    }

}

/**
  * @(#)BaseDataStatus.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.thin.tour.product.base.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

import org.jbundle.thin.main.db.base.*;
import com.tourapp.model.tour.product.base.db.*;

public class BaseDataStatus extends BaseStatus
    implements BaseDataStatusModel
{
    private static final long serialVersionUID = 1L;


    public BaseDataStatus()
    {
        super();
    }
    public BaseDataStatus(Object recordOwner)
    {
        this();
        this.init(recordOwner);
    }

}

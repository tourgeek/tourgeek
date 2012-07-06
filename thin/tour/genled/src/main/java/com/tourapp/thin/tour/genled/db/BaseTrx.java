/**
  * @(#)BaseTrx.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.thin.tour.genled.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

import com.tourapp.thin.tour.genled.db.*;
import com.tourapp.model.tour.genled.db.*;

public class BaseTrx extends Trx
    implements BaseTrxModel
{
    private static final long serialVersionUID = 1L;


    public BaseTrx()
    {
        super();
    }
    public BaseTrx(Object recordOwner)
    {
        this();
        this.init(recordOwner);
    }

}

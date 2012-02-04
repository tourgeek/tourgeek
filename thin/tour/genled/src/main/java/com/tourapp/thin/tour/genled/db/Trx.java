/**
 * @(#)Trx.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.thin.tour.genled.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

import com.tourapp.model.tour.genled.db.*;

public class Trx extends FieldList
    implements TrxModel
{
    private static final long serialVersionUID = 1L;


    public Trx()
    {
        super();
    }
    public Trx(Object recordOwner)
    {
        this();
        this.init(recordOwner);
    }

}

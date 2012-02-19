/**
 * @(#)Job.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.thin.tour.booking.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

import com.tourapp.model.tour.booking.db.*;

public class Job extends FieldList
    implements JobModel
{
    private static final long serialVersionUID = 1L;


    public Job()
    {
        super();
    }
    public Job(Object recordOwner)
    {
        this();
        this.init(recordOwner);
    }

}

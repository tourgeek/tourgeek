/**
  * @(#)Job.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.thin.tour.booking.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

import com.tourgeek.model.tour.booking.db.*;

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

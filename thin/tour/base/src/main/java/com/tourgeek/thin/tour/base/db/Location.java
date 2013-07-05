/**
  * @(#)Location.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.thin.tour.base.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

import com.tourgeek.model.tour.base.db.*;

public class Location extends FieldList
    implements LocationModel
{
    private static final long serialVersionUID = 1L;


    public Location()
    {
        super();
    }
    public Location(Object recordOwner)
    {
        this();
        this.init(recordOwner);
    }

}

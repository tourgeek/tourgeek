/**
 * @(#)Location.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.thin.tour.base.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

import com.tourapp.model.tour.base.db.*;

public class Location extends FieldList
    implements LocationModel
{

    //public static final String ID = ID;

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

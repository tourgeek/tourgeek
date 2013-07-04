
package com.tourgeek.thin.tour.product.base.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

import com.tourgeek.model.tour.product.base.db.*;

public class BaseClass extends FieldList
    implements BaseClassModel
{
    private static final long serialVersionUID = 1L;


    public BaseClass()
    {
        super();
    }
    public BaseClass(Object recordOwner)
    {
        this();
        this.init(recordOwner);
    }

}

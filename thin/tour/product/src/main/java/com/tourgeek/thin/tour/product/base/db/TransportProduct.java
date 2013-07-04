
package com.tourgeek.thin.tour.product.base.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

import com.tourgeek.thin.tour.product.base.db.*;
import com.tourgeek.model.tour.product.base.db.*;

public class TransportProduct extends Product
    implements TransportProductModel
{
    private static final long serialVersionUID = 1L;


    public TransportProduct()
    {
        super();
    }
    public TransportProduct(Object recordOwner)
    {
        this();
        this.init(recordOwner);
    }

}

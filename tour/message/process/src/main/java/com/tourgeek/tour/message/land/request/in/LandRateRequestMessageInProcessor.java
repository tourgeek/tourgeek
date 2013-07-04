
package com.tourgeek.tour.message.land.request.in;

import java.util.*;

import org.jbundle.base.db.*;
import org.jbundle.thin.base.util.*;
import org.jbundle.thin.base.db.*;
import org.jbundle.base.db.event.*;
import org.jbundle.base.db.filter.*;
import org.jbundle.base.field.*;
import org.jbundle.base.field.convert.*;
import org.jbundle.base.field.event.*;
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import com.tourgeek.tour.message.base.request.in.*;
import com.tourgeek.tour.message.land.request.*;
import com.tourgeek.tour.product.land.db.*;
import org.jbundle.thin.base.message.*;
import com.tourgeek.tour.product.base.db.*;
import com.tourgeek.tour.message.base.request.*;

/**
 *  LandRateRequestMessageInProcessor - .
 */
public class LandRateRequestMessageInProcessor extends ProductRateRequestMessageInProcessor
{
    /**
     * Default constructor.
     */
    public LandRateRequestMessageInProcessor()
    {
        super();
    }
    /**
     * Constructor.
     */
    public LandRateRequestMessageInProcessor(RecordOwnerParent taskParent, Record recordMain, Map<String,Object> properties)
    {
        this();
        this.init(taskParent, recordMain, properties);
    }
    /**
     * Initialize class fields.
     */
    public void init(RecordOwnerParent taskParent, Record recordMain, Map<String, Object> properties)
    {
        super.init(taskParent, recordMain, properties);
    }
    /**
     * Create the product record.
     * Override in the concrete classes.
     */
    public Product getProductRecord()
    {
        return new Land(this);
    }

}

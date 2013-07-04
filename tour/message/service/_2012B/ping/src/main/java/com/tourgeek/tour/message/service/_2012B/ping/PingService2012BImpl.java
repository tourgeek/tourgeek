
package com.tourgeek.tour.message.service._2012B.ping;

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
import org.jbundle.base.message.service.*;
import org.jibx.schema.org.opentravel._2012B.ping.*;
import org.jibx.schema.org.opentravel._2012B.ping.ws.*;

/**
 *  PingService2012BImpl - .
 */
public class PingService2012BImpl extends BaseServiceMessageTransport
     implements PingService
{
    /**
     * Default constructor.
     */
    public PingService2012BImpl()
    {
        super();
    }
    /**
     * Constructor.
     */
    public PingService2012BImpl(RecordOwnerParent parent, Rec recordMain, Map<String, Object> properties)
    {
        this();
        this.init(parent, recordMain, properties);
    }
    /**
     * Initialize class fields.
     */
    public void init(RecordOwnerParent parent, Rec recordMain, Map<String, Object> properties)
    {
        super.init(parent, recordMain, properties);
    }
    /**
     * Ping Method.
     */
    public PingRS ping(PingRQ request)
    {
        return (PingRS)this.processMessage(request);
    }

}

package com.tourapp.tour.message.service._2011B.ping;

import java.util.Map;

import org.jbundle.base.db.Record;
import org.jbundle.base.message.service.BaseServiceMessageTransport;
import org.jbundle.model.RecordOwnerParent;
import org.jibx.schema.org.opentravel._2011B.ping.PingRQ;
import org.jibx.schema.org.opentravel._2011B.ping.PingRS;
import org.jibx.schema.org.opentravel._2011B.ping.ws.PingService;

public class PingService2011BImpl extends BaseServiceMessageTransport
	implements PingService
{
    /**
     * Constructor.
     */
    public PingService2011BImpl()
    {
        super();
    }
    /**
     * Constructor.
     * @param application The parent application.
     * @param strParams The task properties.
     */
    public PingService2011BImpl(RecordOwnerParent task, Record recordMain, Map<String, Object> properties)
    {
        this();
        this.init(task, recordMain, properties);
    }
    /**
     * Initialization.
     * @param taskParent Optional task param used to get parent's properties, etc.
     * @param recordMain Optional main record.
     * @param properties Optional properties object (note you can add properties later).
     */
    public void init(RecordOwnerParent task, Record recordMain, Map<String, Object> properties)
    {
        super.init(task, recordMain, properties);
    }
    /**
     * Free all the resources belonging to this task.
     */
    public void free()
    {
    	super.free();
    }
	
	/**
	 * Service the ping request.
	 * @param request
	 * @return
	 */
	public PingRS ping(PingRQ request)
	{
	    return (PingRS)this.processMessage(request);
	}
}

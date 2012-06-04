package com.tourapp.tour.message.service._2011B.ping;

import java.util.Map;

import org.jbundle.base.message.app.MessageApplication;
import org.jbundle.base.model.MessageApp;
import org.jbundle.model.App;
import org.jibx.schema.org.opentravel._2011B.base.Errors;
import org.jibx.schema.org.opentravel._2011B.base.Success;
import org.jibx.schema.org.opentravel._2011B.ping.PingRQ;
import org.jibx.schema.org.opentravel._2011B.ping.PingRS;
import org.jibx.schema.org.opentravel._2011B.ping.PingRS.Sequence;
import org.jibx.schema.org.opentravel._2011B.ping.ws.PingService;
import org.jibx.schema.org.opentravel._2011B.ping.ws.impl.DefaultPingService;

public class PingService2011BImpl extends BaseService
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
    public PingService2011BImpl(App application, String strParams, Map<String, Object> properties)
    {
        this();
        this.init(application, strParams, properties);
    }
    /**
     * Constructor.
     * @param application The parent application.
     * @param strParams The task properties.
     */
    public void init(App application, String strParams, Map<String, Object> properties)
    {
    	super.init(application, strParams, properties);
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
		PingRS response = new PingRS();
		response.setPayloadStdAttributes(DefaultPingService.createStandardPayload());
		DefaultPingService.movePayloadData(request.getPayloadStdAttributes(), response.getPayloadStdAttributes());
		
        if (request.getEchoData() == null || request.getEchoData().length() == 0) {
        	Errors errors = DefaultPingService.addError(null, "Error, empty echo data", null);
        	response.setErrors(errors);
        	return response;
        }

        Sequence sequence = new Sequence();
        sequence.setSuccess(new Success());
        sequence.setEchoData(request.getEchoData());
        response.addSuccess(sequence);
		
        return response;
	}

}

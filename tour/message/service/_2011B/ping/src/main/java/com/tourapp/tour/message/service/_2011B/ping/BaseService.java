package com.tourapp.tour.message.service._2011B.ping;

import java.util.Map;

import org.jbundle.model.App;
import org.jbundle.thin.base.thread.AutoTask;

public class BaseService extends AutoTask
{

    /**
     * Constructor.
     */
    public BaseService()
    {
        super();
    }
    /**
     * Constructor.
     * @param application The parent application.
     * @param strParams The task properties.
     */
    public BaseService(App application, String strParams, Map<String, Object> properties)
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

}

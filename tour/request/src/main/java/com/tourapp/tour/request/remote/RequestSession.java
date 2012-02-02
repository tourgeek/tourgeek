/**
 * @(#)RequestSession.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.request.remote;

import java.awt.*;
import java.util.*;

import org.jbundle.base.db.*;
import org.jbundle.thin.base.util.*;
import org.jbundle.thin.base.db.*;
import org.jbundle.base.db.event.*;
import org.jbundle.base.db.filter.*;
import org.jbundle.base.field.*;
import org.jbundle.base.field.convert.*;
import org.jbundle.base.field.event.*;
import org.jbundle.base.screen.model.*;
import org.jbundle.base.screen.model.util.*;
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import org.jbundle.base.remote.db.*;
import com.tourapp.tour.request.db.*;
import org.jbundle.thin.base.remote.*;
import org.jbundle.base.remote.*;
import com.tourapp.tour.base.db.shared.*;

/**
 *  RequestSession - Remote side of the thin brochure request session.
 */
public class RequestSession extends Session
{
    /**
     * Default constructor.
     */
    public RequestSession() throws RemoteException
    {
        super();
    }
    /**
     * RequestSession Method.
     */
    public RequestSession(BaseSession parentSessionObject, Record record, Map<String, Object> objectID) throws RemoteException
    {
        this();
        this.init(parentSessionObject, record, objectID);
    }
    /**
     * Initialize class fields.
     */
    public void init(BaseSession parentSessionObject, Record record, Map<String, Object> objectID)
    {
        super.init(parentSessionObject, record, objectID);
    }
    /**
     * Override this to open the main file for this session.
     */
    public Record openMainRecord()
    {
        return new Request(this);
    }
    /**
     * Override this to open other session files.
     */
    public void openOtherRecords()
    {
        super.openOtherRecords();
        new RequestDetail(this);
        // The following files are needed to set up the brochure query
        new RequestControl(this);
        new BundleDetail(this);
        new Brochure(this);
        new RequestInput(this);     // Memory-based file
    }
    /**
     * Add behaviors to this session.
     */
    public void addListeners()
    {
        this.getRecord(RequestDetail.REQUEST_DETAIL_FILE).addListener(new SubFileFilter(this.getRecord(Request.REQUEST_FILE)));
        
        RequestControl recControl = (RequestControl)this.getRecord(RequestControl.REQUEST_CONTROL_FILE);
        RequestInput recRequestInput = (RequestInput)this.getRecord(RequestInput.REQUEST_INPUT_FILE);
        Brochure recItem = (Brochure)this.getRecord(Brochure.BROCHURE_FILE);
        BundleDetail recBundleDetail = (BundleDetail)this.getRecord(BundleDetail.BUNDLE_DETAIL_FILE);
        try   {
            recControl.open();      // Read control record
            ReferenceField fldBundleID = (ReferenceField)recControl.getField(RequestControl.THIN_BUNDLE_ID);
            Bundle recBundle = (Bundle)fldBundleID.getReference();  // Current reference record
            recRequestInput.addBundleBehaviors(recBundle, recBundleDetail, recItem);
        } catch (DBException ex)    {
            ex.printStackTrace();
        }
    }
    /**
     * Override this to do an action sent from the client.
     * @param strCommand The command to execute
     * @param properties The properties for the command
     * @returns Object Return a Boolean.TRUE for success, Boolean.FALSE for failure.
     */
    public Object doRemoteCommand(String strCommand, Map<String,Object> properties) throws RemoteException, DBException
    {
        if (strCommand.equals("RebuildRequestInput"))
        {
            RequestInput recRequestInput = (RequestInput)this.getRecord(RequestInput.REQUEST_INPUT_FILE);
            Brochure recItem = (Brochure)this.getRecord(Brochure.BROCHURE_FILE);
            BundleDetail recBundleDetail = (BundleDetail)this.getRecord(BundleDetail.BUNDLE_DETAIL_FILE);
            Bundle recBundle = (Bundle)this.getRecord(Bundle.BUNDLE_FILE);
            BaseField fldBundleID = recBundle.getField(Bundle.ID);
            recRequestInput.addBundle(fldBundleID, recBundleDetail, recItem, null);
        }
        return super.doRemoteCommand(strCommand, properties);
    }

}

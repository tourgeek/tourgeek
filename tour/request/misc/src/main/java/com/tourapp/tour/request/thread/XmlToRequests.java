/**
  * @(#)XmlToRequests.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.tour.request.thread;

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
import org.jbundle.base.thread.*;
import java.util.*;
import com.tourapp.tour.request.db.*;
import org.jbundle.thin.base.screen.*;
import org.jbundle.base.db.xmlutil.*;
import com.tourapp.tour.base.db.shared.*;

/**
 *  XmlToRequests - Parse the "Requests" xml file and add it to the Requests table.
To Use, just pass:
?task=xx.xx.ProcessRunner&process=xx.xx.XmlToRequests&import=myxmlfile.xml
See the help for other params and samples..
 */
public class XmlToRequests extends BaseProcess
{
    /**
     * Default constructor.
     */
    public XmlToRequests()
    {
        super();
    }
    /**
     * Constructor.
     */
    public XmlToRequests(RecordOwnerParent taskParent, Record recordMain, Map<String,Object> properties)
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
     * Open the main file.
     */
    public Record openMainRecord()
    {
        return new Request(this);
    }
    /**
     * Open the other files.
     */
    public void openOtherRecords()
    {
        super.openOtherRecords();
        new RequestControl(this);
        new RequestDetail(this);
        new Brochure(this);
    }
    /**
     * Add the behaviors.
     */
    public void addListeners()
    {
        super.addListeners();
        Record recRequest = this.getRecord(Request.REQUEST_FILE);
        Record recRequestDetail = this.getRecord(RequestDetail.REQUEST_DETAIL_FILE);
        Record recRequestControl = this.getRecord(RequestControl.REQUEST_CONTROL_FILE);
        try   {
            recRequestControl.open();
        } catch (DBException ex)    {
            ex.printStackTrace();
        }
        recRequest.getField(Request.SEND_VIA_CODE).addListener(new InitFieldHandler(recRequestControl.getField(RequestControl.SEND_VIA_CODE)));
        
        recRequestDetail.addListener(new SubFileFilter(recRequest));
    }
    /**
     * Run Method.
     */
    public void run()
    {
        Map<String,Object> properties = m_properties;
        if (properties != null)
        {
            XmlInOut xmlParser = new XmlInOut(null, null, properties);      // Parse the properties
            xmlParser.run();
        // Now, do the stuff
            Record recXmlRequest = xmlParser.getRecord("Request");
            recXmlRequest.close();
            Record recXmlRequestDetail = xmlParser.getRecord("RequestDetail");
            recXmlRequestDetail.close();
            Record recXmlItem = xmlParser.getRecord("Item");
            recXmlItem.close();
            try   {
                if (recXmlRequest != null)
                {
                    if (recXmlRequestDetail != null)
                    {
                        recXmlRequestDetail.addListener(new SubFileFilter(recXmlRequest));
                        if (recXmlItem != null) if (recXmlRequestDetail.getField("BrochureID") != null)
                        {
                            recXmlRequestDetail.getField("BrochureID").addListener(new ReadSecondaryHandler(recXmlItem));
                        }
                    }
                    // Now that I've parsed the XML file, read through it and write it to the request detail
                    Record recRequest = this.getRecord(Request.REQUEST_FILE);
                    recRequest.setOpenMode(recRequest.getOpenMode() | DBConstants.OPEN_REFRESH_AND_LOCK_ON_CHANGE_STRATEGY);    // So I can update the detail
                    Record recRequestDetail = this.getRecord(RequestDetail.REQUEST_DETAIL_FILE);
                    Record recItem = this.getRecord(Brochure.BROCHURE_FILE);
        
                    ResourceBundle resRequestLookup = new RequestConversionTable();
                    ResourceBundle resRequestDetailLookup = new RequestDetailConversionTable();
                    while (recXmlRequest.next() != null)
                    {
                        recRequest.addNew();
                        recRequest.moveFields(recXmlRequest, resRequestLookup, true, DBConstants.SCREEN_MOVE, true, false, false, false);
                        if (recXmlRequestDetail != null)
                        {
                            recXmlRequestDetail.close();
                            while (recXmlRequestDetail.next() != null)
                            {
                                recRequestDetail.addNew();
                                recRequestDetail.moveFields(recXmlRequestDetail, resRequestDetailLookup, true, DBConstants.SCREEN_MOVE, true, false, false, false);
                                if (recXmlItem != null)
                                    if (recXmlRequestDetail.getField("BrochureID") != null)
                                        if (recRequestDetail.getField(RequestDetail.BROCHURE_DESC).isNull())
                                {
                                    recRequestDetail.getField(RequestDetail.BROCHURE_DESC).moveFieldToThis(recXmlItem.getField("Description"));
                                }
                                recRequestDetail.add();
                            }
                        }
                        recRequest.add();
                    }
        
                }
            } catch (DBException ex)    {
                ex.printStackTrace();
            }
        // *** End of do stuff
            xmlParser.free();
        }
    }

}

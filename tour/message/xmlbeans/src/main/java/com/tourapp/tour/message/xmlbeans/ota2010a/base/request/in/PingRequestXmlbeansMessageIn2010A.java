/**
 *  @(#)PingRequestXmlbeansMessageIn2010A.
 *  Copyright © 2010 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.message.xmlbeans.ota2010a.base.request.in;

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
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.base.message.trx.message.external.convert.xmlbeans.*;
import org.jbundle.thin.base.message.*;
import javax.xml.soap.*;
import java.io.*;
import net.sourceforge.ota_tools.x2010a.ping.*;
import net.sourceforge.ota_tools.x2010a.ping.OTAPingRQDocument.*;
import com.tourapp.tour.message.base.request.in.*;
import org.jbundle.base.message.trx.message.external.*;
import org.apache.xmlbeans.*;

/**
 *  PingRequestXmlbeansMessageIn2010A - .
 */
public class PingRequestXmlbeansMessageIn2010A extends XmlbeansConvertToMessage
{
    /**
     * Default constructor.
     */
    public PingRequestXmlbeansMessageIn2010A()
    {
        super();
    }
    /**
     * Initialize new BaseTrxMessage.
     * This is used for outgoing EC transactions where you have the jaxb message and you need to convert it.
     * @param objRawMessage The (optional) raw data of the message.
     */
    public PingRequestXmlbeansMessageIn2010A(ExternalTrxMessageIn message)
    {
        this();
        this.init(message);
    }
    /**
     * Initialize class fields.
     */
    public void init(ExternalTrxMessageIn message)
    {
        super.init(message);
    }
    /**
     * Create the root element for this message.
     * You must override this.
     * @return The root element.
     */
    public Object unmarshalRootElement(Reader inStream, BaseXmlTrxMessageIn soapTrxMessage) throws Exception
    {
        // Bind the incoming XML to an XMLBeans type.
        OTAPingRQ ping = null;
        OTAPingRQDocument doc = OTAPingRQDocument.Factory.parse(inStream);
        ping = doc.getOTAPingRQ();
        return ping;
    }
    /**
     * Create the root element for this message.
     * You SHOULD override this if the unmarshaller has a native method to unmarshall a dom node.
     * @return The root element.
     */
    public Object unmarshalRootElement(Node node, BaseXmlTrxMessageIn soapTrxMessage) throws Exception
    {
        // Bind the incoming XML to an XMLBeans type.
        OTAPingRQ ping = null;
        OTAPingRQDocument doc = OTAPingRQDocument.Factory.parse(node);
        ping = doc.getOTAPingRQ();
        return ping;
    }
    /**
     * Convert the external form to the internal message form.
     * You must override this method.
     * @param root The root object of the marshallable object.
     * @param recordOwner The recordowner
     * @return The error code.
     */
    public int convertMarshallableObjectToInternal(Object root, RecordOwner recordOwner)
    {
        if (root instanceof OTAPingRQ)
        {       // Always
            OTAPingRQ msg = (OTAPingRQ)root;
            String strMessage = msg.getEchoData();
            
            BaseMessage message = this.getMessage();
            message.put(PingRequestMessageInProcessor.MESSAGE_PARAM, strMessage);
        
        //+    this.addPayloadProperties(msg, message);
            return DBConstants.NORMAL_RETURN;
        }
        else
            return DBConstants.ERROR_RETURN;
    }

}

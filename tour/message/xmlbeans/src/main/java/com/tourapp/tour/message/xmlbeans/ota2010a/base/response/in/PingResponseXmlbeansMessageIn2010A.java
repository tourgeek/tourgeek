/**
 *  @(#)PingResponseXmlbeansMessageIn2010A.
 *  Copyright © 2010 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.message.xmlbeans.ota2010a.base.response.in;

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
import net.sourceforge.ota_tools.x2010a.ping.*;
import net.sourceforge.ota_tools.x2010a.ping.OTAPingRSDocument.*;
import org.jbundle.thin.base.message.*;
import javax.xml.soap.*;
import java.io.*;
import com.tourapp.tour.message.base.request.in.*;
import org.jbundle.base.message.trx.message.external.*;

/**
 *  PingResponseXmlbeansMessageIn2010A - .
 */
public class PingResponseXmlbeansMessageIn2010A extends XmlbeansConvertToMessage
{
    /**
     * Default constructor.
     */
    public PingResponseXmlbeansMessageIn2010A()
    {
        super();
    }
    /**
     * Initialize new BaseTrxMessage.
     * This is used for outgoing EC transactions where you have the jaxb message and you need to convert it.
     * @param objRawMessage The (optional) raw data of the message.
     */
    public PingResponseXmlbeansMessageIn2010A(ExternalTrxMessageIn message)
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
        OTAPingRSDocument doc = OTAPingRSDocument.Factory.parse(inStream);
        OTAPingRS ping = doc.getOTAPingRS();
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
        OTAPingRSDocument doc = OTAPingRSDocument.Factory.parse(node);
        OTAPingRS ping = doc.getOTAPingRS();
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
        if (root instanceof OTAPingRS)
        {       // Always
            OTAPingRS msg = (OTAPingRS)root;
            String strMessage = null;
            boolean bSuccess = true;    // Who uses this?
        
            SuccessType[] rgSuccessType = msg.getSuccessArray();
            String[] rgEchoData = msg.getEchoDataArray();
            if (rgSuccessType.length > 0)
                bSuccess = true;
            if (rgEchoData.length > 0)
                strMessage = rgEchoData[0];
            
            BaseMessage message = this.getMessage();
            message.put(PingRequestMessageInProcessor.MESSAGE_PARAM, strMessage);
            
        //+    this.addPayloadProperties(msg, message);
        }
        return DBConstants.NORMAL_RETURN;
    }

}

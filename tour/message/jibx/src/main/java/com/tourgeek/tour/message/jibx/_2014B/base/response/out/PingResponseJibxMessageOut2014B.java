/**
  * @(#)PingResponseJibxMessageOut2012B.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.tour.message.jibx._2014B.base.response.out;

import org.jbundle.base.model.*;
import com.tourgeek.tour.message.jibx._2014B.base.request.out.*;
import org.jibx.schema.org.opentravel._2014B.ping.*;
import org.jibx.schema.org.opentravel._2014B.base.*;
import org.jbundle.base.message.trx.message.external.*;
import org.jbundle.thin.base.message.*;
import com.tourgeek.tour.message.base.response.*;
import com.tourgeek.tour.message.base.request.in.*;

/**
 *  PingResponseJibxMessageOut2012B - .
 */
public class PingResponseJibxMessageOut2014B extends BaseJibxMessageOut2014B
{
    /**
     * Default constructor.
     */
    public PingResponseJibxMessageOut2014B()
    {
        super();
    }
    /**
     * This is the base class for a transaction which is sent externally.
     * The two main sub-classes of this class are InternalTrxMessage and ExternalTrxMessage.
     * An InternalTrxMessage is the data I create internally to send to the destination. It
     * usually contains all the relative information needed to send to the destination.
     * An ExternalTrxMessage is the message converted to a format that the receiver can
     * understand (such as ebXML).
     */
    public PingResponseJibxMessageOut2014B(ExternalTrxMessageOut message)
    {
        this();
        this.init(message);
    }
    /**
     * Initialize class fields.
     */
    public void init(ExternalTrxMessageOut message)
    {
        super.init(message);
    }
    /**
     * Convert this source message to the ECXML format.
     * Typically you do not override this method, you override the getTransformer method
     * to supply a XSLT document to do the conversion.
     * @param recordOwner TODO
     * @param source The source XML document.
     * @return The XML tree that conforms to the ECXML format.
     */
    public Object convertInternalToMarshallableObject(RecordOwner recordOwner)
    {
        BaseMessage message = this.getMessage();
        String strMessage = message.getString(PingRequestMessageInProcessor.MESSAGE_PARAM);
        
        PingRS root = new PingRS();
        if (strMessage != null)
        {
            PingRS.Sequence item = new PingRS.Sequence();
            item.setSuccess(new Success());
            item.setEchoData(strMessage);
            root.addSuccess(item);
        }
        else
        {
            String strErrorMessage = null;
            if (message.getMessageDataDesc(null) instanceof BaseProductResponse)
            {   // Always
                BaseProductResponse productResponse = (BaseProductResponse)message.getMessageDataDesc(null);
                if (productResponse.getMessageDataStatus() == MessageDataDesc.ERROR)
                {   // Always
                    strErrorMessage = productResponse.getMessageDataError();
                }
            }
            if (strErrorMessage == null)
                strErrorMessage = "Error - No data to PING";
        
            Errors errors = new Errors();
            _Error item = new _Error();
            item.setType("1");
            item.setString(strErrorMessage);
            item.setLanguage("en");    // TODO
            errors.addError(item);
            root.setErrors(errors);
        }
        
        OTAPayloadStdAttributes payloadStdAttributes = new OTAPayloadStdAttributes();
        this.setPayloadProperties(message, payloadStdAttributes);
        root.setPayloadStdAttributes(payloadStdAttributes);
        
        return root;
    }

}

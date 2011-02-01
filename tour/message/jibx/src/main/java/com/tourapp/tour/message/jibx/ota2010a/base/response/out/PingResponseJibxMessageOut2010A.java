/**
 *  @(#)PingResponseJibxMessageOut2010A.
 *  Copyright © 2010 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.message.jibx.ota2010a.base.response.out;

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
import com.tourapp.tour.message.jibx.ota2010a.base.request.out.*;
import org.jibx.ota.ping.*;
import org.jibx.ota.base.*;
import org.jbundle.thin.base.message.*;
import org.jbundle.base.message.trx.message.external.*;
import com.tourapp.tour.message.base.request.in.*;
import com.tourapp.tour.message.base.response.*;

/**
 *  PingResponseJibxMessageOut2010A - .
 */
public class PingResponseJibxMessageOut2010A extends BaseJibxMessageOut2010A
{
    /**
     * Default constructor.
     */
    public PingResponseJibxMessageOut2010A()
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
    public PingResponseJibxMessageOut2010A(ExternalTrxMessageOut message)
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
            FreeText freeText = new FreeText();
            freeText.setString(strErrorMessage);
            freeText.setLanguage("en");    // TODO
            item.setFreeText(freeText);
            errors.addError(item);
            root.setErrors(errors);
        }
        
        OTAPayloadStdAttributes payloadStdAttributes = new OTAPayloadStdAttributes();
        this.setPayloadProperties(message, payloadStdAttributes);
        root.setPayloadStdAttributes(payloadStdAttributes);
        
        return root;
    }

}

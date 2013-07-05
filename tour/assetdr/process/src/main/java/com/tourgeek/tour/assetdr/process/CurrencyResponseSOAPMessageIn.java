/**
  * @(#)CurrencyResponseSOAPMessageIn.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.tour.assetdr.process;

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
import org.jbundle.base.message.trx.message.external.convert.jibx.*;
import org.jibx.schema.net.webservicex.currencyconvertor.*;
import org.jbundle.thin.base.message.*;
import org.jbundle.base.message.core.trx.internal.*;
import org.jbundle.base.message.trx.message.external.*;

/**
 *  CurrencyResponseSOAPMessageIn - .
 */
public class CurrencyResponseSOAPMessageIn extends JibxConvertToMessage
{
    /**
     * Default constructor.
     */
    public CurrencyResponseSOAPMessageIn()
    {
        super();
    }
    /**
     * Initialize new BaseTrxMessage.
     * This is used for outgoing EC transactions where you have the jaxb message and you need to convert it.
     * @param objRawMessage The (optional) raw data of the message.
     */
    public CurrencyResponseSOAPMessageIn(ExternalTrxMessageIn message)
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
     * Convert the external form to the internal message form.
     * You must override this method.
     * @param root The root object of the marshallable object.
     * @param recordOwner The recordowner
     * @return The error code.
     */
    public int convertMarshallableObjectToInternal(Object root, RecordOwner recordOwner)
    {
        if (root instanceof ConversionRateResponse)
        {       // Always
            ConversionRateResponse msg = (ConversionRateResponse)root;
            double rate = msg.getConversionRateResult();
        
            BaseMessage message = this.getMessage();
            message.put(ManualMessage.MESSAGE_PARAM, rate);
        }
        
        return DBConstants.NORMAL_RETURN;
    }

}

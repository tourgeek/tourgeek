/**
 * @(#)CurrencyRequestSOAPMessageOut.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.assetdr.process;

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
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import org.jbundle.base.message.trx.message.external.convert.jibx.*;
import org.jbundle.thin.base.message.*;
import org.jibx.schema.net.webservicex.currencyconvertor.*;
import org.jbundle.base.message.trx.message.external.*;
import org.jbundle.base.message.core.trx.internal.*;

/**
 *  CurrencyRequestSOAPMessageOut - .
 */
public class CurrencyRequestSOAPMessageOut extends JibxConvertToNative
{
    /**
     * Default constructor.
     */
    public CurrencyRequestSOAPMessageOut()
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
    public CurrencyRequestSOAPMessageOut(ExternalTrxMessageOut message)
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
        String currencyCode = message.getString(ManualMessage.MESSAGE_PARAM);
        
        ConversionRate root = new ConversionRate();
        org.jibx.schema.net.webservicex.currencyconvertor.Currency currency = org.jibx.schema.net.webservicex.currencyconvertor.Currency.valueOf(currencyCode);
        if (currency == null)
            return null;
        root.setFromCurrency(org.jibx.schema.net.webservicex.currencyconvertor.Currency.USD);
        root.setToCurrency(currency);
        
        return root;
    }

}

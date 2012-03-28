/**
 * @(#)CurrencyUpdateProcess.
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
import org.jbundle.base.thread.*;
import org.jbundle.thin.base.screen.*;
import com.tourapp.tour.base.db.*;
import org.jibx.schema.net.webservicex.currencyconvertor.*;
import org.jbundle.base.message.core.trx.*;
import org.jbundle.thin.base.message.*;
import org.jbundle.base.message.trx.transport.soap.*;
import org.jbundle.base.message.trx.message.external.convert.jibx.*;
import org.jbundle.base.message.trx.message.external.*;

/**
 *  CurrencyUpdateProcess - .
 */
public class CurrencyUpdateProcess extends BaseProcess
{
    /**
     * Default constructor.
     */
    public CurrencyUpdateProcess()
    {
        super();
    }
    /**
     * Constructor.
     */
    public CurrencyUpdateProcess(RecordOwnerParent taskParent, Record recordMain, Map<String,Object> properties)
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
        return new Currencys(this);
    }
    /**
     * Run Method.
     */
    public void run()
    {
        this.updateAllCurrencies();
    }
    /**
     * UpdateAllCurrencies Method.
     */
    public void updateAllCurrencies()
    {
        Record record = this.getMainRecord();
        record.close();
        try {
            while (record.hasNext())
            {
                record.next();
                if (record.getField(Currencys.DELETED).getState() == true)
                    continue;
                record.edit();
                
                double dRate = this.getConversionRate(record.getField(Currencys.CURRENCY_CODE).toString());
                if (dRate != 0.0)
                    record.getField(Currencys.LAST_RATE).setValue(1 / dRate);
                
                record.set();
            }
        } catch (DBException e) {
            e.printStackTrace();
        }
    }
    /**
     * GetConversionRate Method.
     */
    public double getConversionRate(String currencyCode)
    {
        ConversionRate root = new ConversionRate();
        org.jibx.schema.net.webservicex.currencyconvertor.Currency currency = null;
        try {
            currency = org.jibx.schema.net.webservicex.currencyconvertor.Currency.valueOf(currencyCode);
        } catch (Exception e) {
            currency = null;    // Ignore
        }
        if (currency == null)
            return 0.0;
        root.setFromCurrency(org.jibx.schema.net.webservicex.currencyconvertor.Currency.USD);
        root.setToCurrency(currency);
        
        TrxMessageHeader messageHeader = new TrxMessageHeader(null, null);
        BaseMessage message = new TreeMessage(messageHeader, null);
        messageHeader.put(SOAPMessageTransport.SOAP_PACKAGE, "net.webservicex.currencyconverter");
        messageHeader.put(TrxMessageHeader.MESSAGE_MARSHALLER_CLASS, JibxConvertToNative.class.getName());
        String strDest = "http://www.webservicex.com/CurrencyConvertor.asmx";
        messageHeader.put(TrxMessageHeader.DESTINATION_PARAM, strDest);
        messageHeader.put("SOAPAction", "http://www.webserviceX.NET/ConversionRate");
        
        new SoapTrxMessageOut(message, root);
        SOAPMessageTransport transport = new SOAPMessageTransport(this.getTask());
        BaseMessage messageIn = transport.sendMessageRequest(message);
        if (messageIn.getMessageHeader() == null)   // Yes
            messageIn.setMessageHeader(new TrxMessageHeader(null, null));
        SoapTrxMessageIn externalMessageIn = (SoapTrxMessageIn)messageIn.getExternalMessage();
        TrxMessageHeader messageHeaderIn = (TrxMessageHeader)messageIn.getMessageHeader();
        messageHeaderIn.put(SOAPMessageTransport.SOAP_PACKAGE, "net.webservicex.currencyconverter");
        messageHeaderIn.put(TrxMessageHeader.MESSAGE_MARSHALLER_CLASS, JibxConvertToMessage.class.getName());
        
        ConversionRateResponse rootIn = (ConversionRateResponse)externalMessageIn.convertToMessage();
        if (rootIn == null)
            return 0.0;
        return rootIn.getConversionRateResult();
    }

}

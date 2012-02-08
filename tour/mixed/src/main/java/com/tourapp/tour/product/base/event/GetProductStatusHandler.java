/**
 * @(#)GetProductStatusHandler.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.product.base.event;

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
import org.jbundle.base.db.grid.*;
import org.jbundle.main.msg.db.*;
import org.jbundle.base.message.core.trx.*;
import org.jbundle.thin.base.message.*;
import com.tourapp.tour.message.base.request.*;
import org.jbundle.main.db.base.*;
import org.jbundle.model.message.*;
import com.tourapp.tour.product.base.db.*;

/**
 *  GetProductStatusHandler - .
 */
public class GetProductStatusHandler extends RecordCacheHandler
{
    protected ProductScreenRecord m_recProductVars = null;
    protected Integer m_intRegistryID = null;
    /**
     * Default constructor.
     */
    public GetProductStatusHandler()
    {
        super();
    }
    /**
     * GetProductStatusHandler Method.
     */
    public GetProductStatusHandler(Record recProductVars, Integer intRegistryID)
    {
        this();
        this.init(recProductVars, intRegistryID);
    }
    /**
     * Initialize class fields.
     */
    public void init(Record recProductVars, Integer intRegistryID)
    {
        m_recProductVars = null;
        m_recProductVars = (ProductScreenRecord)recProductVars;
        m_intRegistryID = intRegistryID;
        super.init(null);
    }
    /**
     * Do valid record, but only the first time the record is displayed.
     */
    public void doFirstValidRecord(boolean bDisplayOption)
    {
        super.doFirstValidRecord(bDisplayOption);
        if (this.isQueryComplete())
        {
            Product recProduct = (Product)this.getOwner();
            RecordOwner recordOwner = recProduct.getRecordOwner();
            String strMessageInfoType = MessageInfoType.REQUEST;
            String strContactType = recProduct.getTableNames(false);
            String strRequestType = this.getRequestType();
            String strMessageProcessType = MessageType.MESSAGE_OUT;
            String strProcessType = ProcessType.INFO;
            String strMessageTransport = null;
            TrxMessageHeader trxMessageHeader = recProduct.createProcessMessage(strMessageInfoType, strContactType, strRequestType, strMessageProcessType, strProcessType, strMessageTransport);
            if (trxMessageHeader == null)
                return;
            BaseMessage message = (BaseMessage)BaseMessage.createMessage(trxMessageHeader);
            if (message == null)
                return;
            Record screenRecord = (Record)recordOwner.getScreenRecord();
            this.setLookupProperties(message, recProduct, screenRecord);
            if ((MessageTransportTypeField.DIRECT.equalsIgnoreCase((String)trxMessageHeader.get(MessageTransport.TRANSPORT_TYPE_PARAM)))
                || (trxMessageHeader.get(MessageTransport.TRANSPORT_TYPE_PARAM) == null))
            {
                int iStatus = this.getDirectProductInfo(recProduct, message);
                this.setupScreenStatus(recProduct, iStatus);
            }
            else if ((MessageTransportTypeField.AUTO_RESPONSE.equalsIgnoreCase((String)trxMessageHeader.get(MessageTransport.TRANSPORT_TYPE_PARAM)))
                || (MessageTransportTypeField.LOCAL.equalsIgnoreCase((String)trxMessageHeader.get(MessageTransport.TRANSPORT_TYPE_PARAM))))
            {   // The user wants to do remote price lookups.
                if (screenRecord.getField(ProductScreenRecord.REMOTE_QUERY_ENABLED).getState() == true)
                {
                    if (m_intRegistryID != null)
                        trxMessageHeader.put(TrxMessageHeader.REGISTRY_ID, m_intRegistryID);    // The return Queue ID
        
                    MessageManager messageManager = ((Application)recordOwner.getTask().getApplication()).getMessageManager();
                    this.setupScreenStatus(recProduct, BaseStatus.REQUEST_SENT);
                    if (messageManager != null)
                        messageManager.sendMessage(message);
                }
                else
                    this.setupScreenStatus(recProduct, BaseStatus.NO_STATUS);
            }
            else if (MessageTransportTypeField.MANUAL_RESPONSE.equalsIgnoreCase((String)trxMessageHeader.get(MessageTransport.TRANSPORT_TYPE_PARAM)))
                this.setupScreenStatus(recProduct, BaseStatus.MANUAL_REQUEST_REQUIRED);
            else if (MessageTransportTypeField.MANUAL_COMMUNICATION.equalsIgnoreCase((String)trxMessageHeader.get(MessageTransport.TRANSPORT_TYPE_PARAM)))
            {
                // For manual messages, the status is set manually (don't change it)
            }
            else
                this.setupScreenStatus(recProduct, BaseStatus.NO_STATUS);
        }
    }
    /**
     * GetRequestType Method.
     */
    public String getRequestType()
    {
        return RequestType.PRICE;
    }
    /**
     * Get the cost or inventory information from the product now.
     * @return The message status.
     */
    public int getDirectProductInfo(Product recProduct, BaseMessage message)
    {
        return BaseStatus.NOT_VALID;        // Override this!
    }
    /**
     * If all the data in the screen record that is required for a query is there,
     * return true. If not, false.
     */
    public boolean isQueryComplete()
    {
        return true;    // Override this!
    }
    /**
     * Get the lookup properties from the ProductScreenRecord and the
     * Product record and set them in this map.
     * (Override this to set the loopup properties).
     */
    public void setLookupProperties(BaseMessage map, Record recProduct, Record screenRecord)
    {
        // Override this!
    }
    /**
     * Move this product cost from to virtual fields to the ProductCost
     * field in recProduct. Also move the status to the product cost status.
     * (Override this to set the correct fields!).
     */
    public void setupScreenStatus(Record recProduct, int iStatus)
    {
        // Override this!
    }

}

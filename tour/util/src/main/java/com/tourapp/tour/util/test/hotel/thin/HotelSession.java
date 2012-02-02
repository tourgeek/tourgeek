/**
 * @(#)HotelSession.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.util.test.hotel.thin;

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
import org.jbundle.base.remote.*;
import org.jbundle.thin.base.remote.*;
import org.jbundle.thin.base.message.*;
import com.tourapp.tour.util.test.hotel.*;
import com.tourapp.tour.product.hotel.db.*;
import org.jbundle.base.message.trx.message.*;
import org.jbundle.main.msg.db.*;
import com.tourapp.tour.product.base.db.*;
import com.tourapp.tour.message.hotel.response.*;
import org.jbundle.thin.base.message.session.*;
import com.tourapp.tour.message.base.request.*;
import com.tourapp.tour.booking.detail.db.*;
import com.tourapp.tour.message.base.response.*;
import com.tourapp.tour.message.hotel.response.data.*;
import org.jbundle.model.message.*;

/**
 *  HotelSession - .
 */
public class HotelSession extends Session
{
    protected Integer m_intRegistryID = null;
    /**
     * Default constructor.
     */
    public HotelSession() throws RemoteException
    {
        super();
    }
    /**
     * HotelSession Method.
     */
    public HotelSession(BaseSession parentSessionObject, Record record, Map<String, Object> objectID) throws RemoteException
    {
        this();
        this.init(parentSessionObject, record, objectID);
    }
    /**
     * Initialize class fields.
     */
    public void init(BaseSession parentSessionObject, Record record, Map<String, Object> objectID)
    {
        m_intRegistryID = null;
        super.init(parentSessionObject, record, objectID);
    }
    /**
     * Override this to open the main file for this session.
     */
    public Record openMainRecord()
    {
        return null;//???ew TestTable(this);
    }
    /**
     * Add behaviors to this session.
     */
    public void addListeners()
    {
        super.addListeners();
        
        //        BaseMessageFilter remoteFilter = new ServerSessionMessageFilter(messageFilter.getQueueName(), messageFilter.getQueueType(), null, this);
        //System.out.println("Remote filter " + remoteFilter);
        BaseMessageFilter filterForSession = new BaseMessageFilter(MessageConstants.TRX_RETURN_QUEUE, MessageConstants.INTERNET_QUEUE, null, null);
        MessageManager messageManager = ((Application)this.getTask().getApplication()).getMessageManager();
        messageManager.addMessageFilter(filterForSession);
        filterForSession.addMessageListener(this);
        
        m_intRegistryID = filterForSession.getRegistryID();
    }
    /**
     * Add the screen record to this session.
     */
    public Record addScreenRecord()
    {
        return new TestHotelRateScreenRecord(this);
    }
    /**
     * Override this to do an action sent from the client.
     * @param strCommand The command to execute
     * @param properties The properties for the command
     * @returns Object Return a Boolean.TRUE for success, Boolean.FALSE for failure.
     */
    public Object doRemoteCommand(String strCommand, Map<String,Object> properties) throws RemoteException, DBException
    {
        if ("getrate".equalsIgnoreCase(strCommand))
        {
            Object objHotelID = properties.get("ProductID");
            this.getScreenRecord().getField(TestHotelRateScreenRecord.PRODUCT_ID).setString(objHotelID.toString());
            Hotel recHotel = (Hotel)((ReferenceField)this.getScreenRecord().getField(TestHotelRateScreenRecord.PRODUCT_ID)).getReference();
            if ((recHotel.getEditMode() == DBConstants.EDIT_CURRENT)
                || (recHotel.getEditMode() == DBConstants.EDIT_IN_PROGRESS))
            {
                String strMessageInfoType = MessageInfoType.REQUEST;
                String strContactType = recHotel.getTableNames(false);
                String strRequestType = RequestType.PRICE;
                String strMessageType = MessageType.MESSAGE_OUT;
                String strProcessType = ProcessType.INFO;
                String strMessageTransport = null;
                TrxMessageHeader trxMessageHeader = recHotel.createProcessMessage(strMessageInfoType, strContactType, strRequestType, strMessageType, strProcessType, strMessageTransport);
                if (trxMessageHeader == null)
                    return null;
                BaseMessage messageOut = BaseMessage.createMessage(trxMessageHeader);
                ProductRequest message = (ProductRequest)messageOut.getMessageDataDesc(null);
                if (message == null)
                    return null;
        
                trxMessageHeader = (TrxMessageHeader)messageOut.getMessageHeader();
                trxMessageHeader.put(MessageTransport.MANUAL_RESPONSE_PARAM, DBConstants.TRUE);    // For testing, allow manual requests
                if (m_intRegistryID != null)
                    trxMessageHeader.put(TrxMessageHeader.REGISTRY_ID, m_intRegistryID);    // The return Queue ID
        
                Record record = this.getScreenRecord();
                message.put(BookingDetail.PRODUCT_ID, properties.get("ProductID"));
                message.put(BookingDetail.RATE_ID, properties.get("RateID"));
                message.put(BookingDetail.CLASS_ID, properties.get("ClassID"));
                message.put(BookingDetail.DETAIL_DATE, properties.get("DetailDate"));
        
                if (MessageTransport.DIRECT.equalsIgnoreCase((String)trxMessageHeader.get(MessageTransport.SEND_MESSAGE_BY_PARAM)))
                {
                    BaseProductResponse messageReply = (BaseProductResponse)recHotel.processCostRequestInMessage(messageOut, null).getMessageDataDesc(null);
                    double dProductCost = recHotel.getField(Product.PRODUCT_COST).getValue();
                    this.getScreenRecord().getField(TestHotelRateScreenRecord.TOTAL_COST).setValue(dProductCost);
                }
                else
                {
                    MessageManager messageManager = ((Application)this.getTask().getApplication()).getMessageManager();
                    if (messageManager != null)
                        messageManager.sendMessage(messageOut);
                }
            }
        }
        return super.doRemoteCommand(strCommand, properties);
    }
    /**
     * A record with this datasource handle changed, notify any behaviors that are checking.
     * NOTE: Be very careful as this code is running in an independent thread
     * (synchronize to the task before calling record calls).
     * NOTE: For now, you are only notified of the main record changes.
     * @param message The message to handle.
     * @return The error code.
     */
    public int handleMessage(BaseMessage message)
    {
        System.out.println("---------------------------------------------------------- HotelSession/86");
        if (message instanceof BaseMessage)
        {
            Double dblRate = (Double)message.get(BookingDetail.TOTAL_COST);
            if (message.get(HotelRateResponse.PRODUCT_RESPONSE_MESSAGE) instanceof HotelRateResponseMessageData)
            {
                dblRate = new Double(((HotelRateResponseMessageData)message.get(HotelRateResponse.PRODUCT_RESPONSE_MESSAGE)).getRoomCost(PaxCategory.DOUBLE_ID));
            }
        
            MessageManager messageManager = ((Application)this.getTask().getApplication()).getMessageManager();
            BaseMessageHeader messageFilter = message.getMessageHeader();
            BaseMessageHeader messageHeader = new SessionMessageHeader(null, this);
            Map<String,Object> properties = new Hashtable<String,Object>();
            properties.put("hotelCost", dblRate);
            BaseMessage messageTableUpdate = new MapMessage(messageHeader, properties);
            messageManager.sendMessage(messageTableUpdate);
        }
        return super.handleMessage(message);
    }

}

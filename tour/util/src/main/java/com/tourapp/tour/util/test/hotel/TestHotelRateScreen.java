/**
 *  @(#)TestHotelRateScreen.
 *  Copyright © 2010 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.util.test.hotel;

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
import com.tourapp.tour.product.hotel.db.*;
import org.jbundle.thin.base.message.*;
import org.jbundle.base.message.trx.message.*;
import org.jbundle.base.message.trx.processor.*;
import com.tourapp.tour.acctpay.db.*;
import com.tourapp.tour.message.hotel.request.out.*;
import com.tourapp.tour.product.base.db.*;
import org.jbundle.main.msg.db.*;
import com.tourapp.tour.message.hotel.request.*;
import com.tourapp.tour.message.base.request.*;
import com.tourapp.tour.booking.detail.db.*;
import com.tourapp.tour.message.base.response.*;
import com.tourapp.tour.message.hotel.response.*;
import com.tourapp.tour.message.hotel.response.data.*;
import com.tourapp.tour.message.base.request.data.*;
import org.jbundle.base.message.trx.transport.local.*;
import com.tourapp.tour.message.base.request.in.*;

/**
 *  TestHotelRateScreen - Test XML messaging to retrieve a hotel rate..
 */
public class TestHotelRateScreen extends Screen
{
    protected Integer m_intRegistryID = null;
    /**
     * Default constructor.
     */
    public TestHotelRateScreen()
    {
        super();
    }
    /**
     * Constructor.
     * @param record The main record for this screen.
     * @param itsLocation The location of this component within the parent.
     * @param parentScreen The parent screen.
     * @param fieldConverter The field this screen field is linked to.
     * @param iDisplayFieldDesc Do I display the field desc?
     * @param properties Addition properties to pass to the screen.
     */
    public TestHotelRateScreen(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
    {
        this();
        this.init(record, itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, properties);
    }
    /**
     * Initialize class fields.
     */
    public void init(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
    {
        m_intRegistryID = null;
        super.init(record, itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, properties);
    }
    /**
     * Get the screen display title.
     */
    public String getTitle()
    {
        return "Test XML messaging to retrieve a hotel rate.";
    }
    /**
     * Free Method.
     */
    public void free()
    {
        super.free();
        BaseMessageManager messageManager = ((Application)this.getTask().getApplication()).getMessageManager(false);
        if (messageManager != null)
            messageManager.freeFiltersWithListener(this);  // Free all filters listening for me.
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        BaseMessageManager messageManager = ((Application)this.getTask().getApplication()).getMessageManager();
        if (messageManager != null)
        {
            Object source = this;
            BaseMessageFilter filter = new BaseMessageFilter(MessageConstants.TRX_RETURN_QUEUE, MessageConstants.INTERNET_QUEUE, source, null);
            filter.addMessageListener(this);
            messageManager.addMessageFilter(filter);
            m_intRegistryID = filter.getRegistryID();
        }
    }
    /**
     * Override this to open the main file.
     * <p />You should pass this record owner to the new main file (ie., new MyNewTable(thisRecordOwner)).
     * @return The new record.
     */
    public Record openMainRecord()
    {
        return new Hotel(this);   // ? Is this necessary?
    }
    /**
     * Add the screen fields.
     * Override this to create (and return) the screen record for this recordowner.
     * @return The screen record.
     */
    public Record addScreenRecord()
    {
        return new TestHotelRateScreenRecord(this);
    }
    /**
     * Process the command.
     * <br />Step 1 - Process the command if possible and return true if processed.
     * <br />Step 2 - If I can't process, pass to all children (with me as the source).
     * <br />Step 3 - If children didn't process, pass to parent (with me as the source).
     * <br />Note: Never pass to a parent or child that matches the source (to avoid an endless loop).
     * @param strCommand The command to process.
     * @param sourceSField The source screen field (to avoid echos).
     * @param iCommandOptions If this command creates a new screen, create in a new window?
     * @return true if success.
     */
    public boolean doCommand(String strCommand, ScreenField sourceSField, int iCommandOptions)
    {
        if ("getrate".equalsIgnoreCase(strCommand))
        {
            Hotel recHotel = (Hotel)((ReferenceField)this.getScreenRecord().getField(TestHotelRateScreenRecord.kProductID)).getReference();
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
                    return false;
                BaseMessage messageOut = BaseMessage.createMessage(trxMessageHeader);
                ProductRequest message = (ProductRequest)messageOut.getMessageDataDesc(null);
                if (message == null)
                    return false;
        
                trxMessageHeader = (TrxMessageHeader)messageOut.getMessageHeader();
                trxMessageHeader.put(MessageTransport.MANUAL_RESPONSE_PARAM, DBConstants.TRUE);    // For testing, allow manual requests
                if (m_intRegistryID != null)
                    trxMessageHeader.put(TrxMessageHeader.REGISTRY_ID, m_intRegistryID);    // The return Queue ID
        
                Record record = this.getScreenRecord();
                ((MessageRecordDesc)message.getMessageDataDesc(ProductRequest.PRODUCT_MESSAGE)).putRawFieldData(record.getField(TestHotelRateScreenRecord.kProductID));
                ((MessageRecordDesc)message.getMessageDataDesc(ProductRequest.PRODUCT_MESSAGE)).putRawFieldData(record.getField(TestHotelRateScreenRecord.kRateID));
                ((MessageRecordDesc)message.getMessageDataDesc(ProductRequest.PRODUCT_MESSAGE)).putRawFieldData(record.getField(TestHotelRateScreenRecord.kClassID));
                ((MessageRecordDesc)message.getMessageDataDesc(ProductRequest.PRODUCT_MESSAGE)).putRawFieldData(record.getField(TestHotelRateScreenRecord.kDetailDate));
        
                ((PassengerMessageData)message.getMessageDataDesc(ProductRequest.PASSENGER_MESSAGE)).setPaxInRoom(PaxCategory.DOUBLE_ID, (short)2);
                ((PassengerMessageData)message.getMessageDataDesc(ProductRequest.PASSENGER_MESSAGE)).setTargetPax((short)2);
        
                if (MessageTransport.DIRECT.equalsIgnoreCase((String)trxMessageHeader.get(MessageTransport.SEND_MESSAGE_BY_PARAM)))
                {
                    String strMessageCode = (String)trxMessageHeader.get(LocalMessageTransport.LOCAL_PROCESSOR);
                    if (trxMessageHeader.getMessageInfoMap() != null)
                    {
                        trxMessageHeader.getMessageInfoMap().remove(TrxMessageHeader.MESSAGE_PROCESSOR_CLASS);
                        trxMessageHeader.getMessageInfoMap().remove(TrxMessageHeader.BASE_PACKAGE);
                    }
                    MessageProcessInfo recMessageProcessInfo = new MessageProcessInfo(this);
                    recMessageProcessInfo.setupMessageHeaderFromCode(message.getMessage(), strMessageCode, null);
                    recMessageProcessInfo.free();
                    String strDefaultProcessorClass = BaseMessageInProcessor.class.getName();
                    BaseExternalMessageProcessor messageInProcessor = (BaseExternalMessageProcessor)BaseMessageProcessor.getMessageProcessor(this.getTask(), (BaseMessage)message.getMessage(), strDefaultProcessorClass);
                    BaseMessage baseMessageReply = null;
                    BaseProductResponse messageReply = null;
                    if (messageInProcessor != null)
                    {   // Always
                            // Note: Since recProduct is already read you may be able to speed things up by passing recProduct to this process..
                        baseMessageReply = messageInProcessor.processMessage((BaseMessage)message.getMessage());
                        messageReply = (BaseProductResponse)baseMessageReply.getMessageDataDesc(null);
                        messageInProcessor.free();
                    }
                    int iStatus = BaseDataStatus.NOT_VALID;
                    if (messageReply != null)
                        this.handleMessage(baseMessageReply);
                    else
                        this.getRecord(TestHotelRateScreenRecord.kTestHotelRateScreenRecordFile).getField(TestHotelRateScreenRecord.kDisplayCostStatusID).setValue(iStatus);
                }
                else
                {
                    BaseMessageManager messageManager = ((Application)this.getTask().getApplication()).getMessageManager();
                    if (messageManager != null)
                        messageManager.sendMessage(messageOut);
                }
            }
        }
        if ("ping".equalsIgnoreCase(strCommand))
        {
            Hotel recHotel = (Hotel)((ReferenceField)this.getScreenRecord().getField(TestHotelRateScreenRecord.kProductID)).getReference();
            if ((recHotel.getEditMode() == DBConstants.EDIT_CURRENT)
                || (recHotel.getEditMode() == DBConstants.EDIT_IN_PROGRESS))
            {
                MessageProcessInfo recMessageProcessInfo = new MessageProcessInfo(this);
                recMessageProcessInfo.setKeyArea(MessageProcessInfo.kCodeKey);
                recMessageProcessInfo.getField(MessageProcessInfo.kCode).setString("PingOutRQ");
                try {
                    if (recMessageProcessInfo.seek(null))
                    {
                        TrxMessageHeader trxMessageHeader = recMessageProcessInfo.createProcessMessageHeader(recHotel, null);
                        if (trxMessageHeader == null)
                            return false;
                        trxMessageHeader.put(MessageTransport.MANUAL_RESPONSE_PARAM, DBConstants.TRUE);    // For testing, allow manual requests
                        if (m_intRegistryID != null)
                            trxMessageHeader.put(TrxMessageHeader.REGISTRY_ID, m_intRegistryID);    // The return Queue ID
                        BaseMessage message = new TreeMessage(trxMessageHeader, null);
                        message.put(PingRequestMessageInProcessor.MESSAGE_PARAM, "Hello");
                        if (!MessageTransport.DIRECT.equalsIgnoreCase((String)trxMessageHeader.get(MessageTransport.SEND_MESSAGE_BY_PARAM)))
                        {
                            BaseMessageManager messageManager = ((Application)this.getTask().getApplication()).getMessageManager();
                            if (messageManager != null)
                                messageManager.sendMessage(message);
                        }
                    }
                } catch (DBException e) {
                    e.printStackTrace();
                } finally {
                    recMessageProcessInfo.free();
                }
            }
        }
        if ("Currency".equalsIgnoreCase(strCommand))
        {
            Hotel recHotel = (Hotel)((ReferenceField)this.getScreenRecord().getField(TestHotelRateScreenRecord.kProductID)).getReference();
            if ((recHotel.getEditMode() == DBConstants.EDIT_CURRENT)
                || (recHotel.getEditMode() == DBConstants.EDIT_IN_PROGRESS))
            {
                MessageProcessInfo recMessageProcessInfo = new MessageProcessInfo(this);
                recMessageProcessInfo.setKeyArea(MessageProcessInfo.kCodeKey);
                recMessageProcessInfo.getField(MessageProcessInfo.kCode).setString("CurrencyRateOut");
                try {
                    if (recMessageProcessInfo.seek(null))
                    {
                        TrxMessageHeader trxMessageHeader = recMessageProcessInfo.createProcessMessageHeader(recHotel, null);
                        if (trxMessageHeader == null)
                            return false;
                        trxMessageHeader.put(MessageTransport.MANUAL_RESPONSE_PARAM, DBConstants.TRUE);    // For testing, allow manual requests
                        if (m_intRegistryID != null)
                            trxMessageHeader.put(TrxMessageHeader.REGISTRY_ID, m_intRegistryID);    // The return Queue ID
                        BaseMessage message = new TreeMessage(trxMessageHeader, null);
                        message.put(PingRequestMessageInProcessor.MESSAGE_PARAM, "CHF");
                        if (!MessageTransport.DIRECT.equalsIgnoreCase((String)trxMessageHeader.get(MessageTransport.SEND_MESSAGE_BY_PARAM)))
                        {
                            BaseMessageManager messageManager = ((Application)this.getTask().getApplication()).getMessageManager();
                            if (messageManager != null)
                                messageManager.sendMessage(message);
                        }
                    }
                } catch (DBException e) {
                    e.printStackTrace();
                } finally {
                    recMessageProcessInfo.free();
                }
            }
        }
        return super.doCommand(strCommand, sourceSField, iCommandOptions);
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
        if (message != null)
            if (message.getMessageHeader().getRegistryIDMatch() != null)    // My private message
        {
            if (message.get(PingRequestMessageInProcessor.MESSAGE_PARAM) == null)
            {
                HotelRateResponse msgHotelRateResponse = new HotelRateResponse(message, null);
                HotelRateResponseMessageData responseMessageData = (HotelRateResponseMessageData)msgHotelRateResponse.getMessageDataDesc(ProductRateResponse.PRODUCT_RESPONSE_MESSAGE);
            
                Double dblRate = (Double)responseMessageData.get(BookingDetail.TOTAL_COST);
                if (dblRate != null)    // Display the rate
                    this.getRecord(TestHotelRateScreenRecord.kTestHotelRateScreenRecordFile).getField(TestHotelRateScreenRecord.kTotalCost).setValue(dblRate.doubleValue());
                int iStatus = ((BaseProductResponse)message.getMessageDataDesc(null)).getMessageDataStatus();
                this.getRecord(TestHotelRateScreenRecord.kTestHotelRateScreenRecordFile).getField(TestHotelRateScreenRecord.kDisplayCostStatusID).setValue(iStatus);
            }
            else
            {
                String strMessage = (String)message.get(PingRequestMessageInProcessor.MESSAGE_PARAM);
                System.out.println("Message: " + strMessage);
                if ("Hello".equalsIgnoreCase(strMessage))
                    this.getRecord(TestHotelRateScreenRecord.kTestHotelRateScreenRecordFile).getField(TestHotelRateScreenRecord.kDisplayCostStatusID).setValue(BaseStatus.OKAY);
            }
        }
        return super.handleMessage(message);
    }
    /**
     * Set up all the screen fields.
     */
    public void setupSFields()
    {
        this.getRecord(TestHotelRateScreenRecord.kTestHotelRateScreenRecordFile).getField(TestHotelRateScreenRecord.kProductID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TestHotelRateScreenRecord.kTestHotelRateScreenRecordFile).getField(TestHotelRateScreenRecord.kRateID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TestHotelRateScreenRecord.kTestHotelRateScreenRecordFile).getField(TestHotelRateScreenRecord.kClassID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TestHotelRateScreenRecord.kTestHotelRateScreenRecordFile).getField(TestHotelRateScreenRecord.kDetailDate).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TestHotelRateScreenRecord.kTestHotelRateScreenRecordFile).getField(TestHotelRateScreenRecord.kTotalCost).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TestHotelRateScreenRecord.kTestHotelRateScreenRecordFile).getField(TestHotelRateScreenRecord.kDisplayCostStatusID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        new SCannedBox(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), this, null, ScreenConstants.DEFAULT_DISPLAY, "Ping");
        new SCannedBox(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), this, null, ScreenConstants.DEFAULT_DISPLAY, "Currency");
        new SCannedBox(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), this, null, ScreenConstants.DEFAULT_DISPLAY, "GetRate");
    }

}

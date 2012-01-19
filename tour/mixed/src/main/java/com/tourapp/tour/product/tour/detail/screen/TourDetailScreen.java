/**
 * @(#)TourDetailScreen.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.product.tour.detail.screen;

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
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import org.jbundle.base.message.record.*;
import org.jbundle.thin.base.message.*;
import org.jbundle.thin.base.screen.message.*;
import org.jbundle.main.screen.*;
import com.tourapp.tour.product.tour.db.*;
import com.tourapp.tour.product.tour.screen.*;
import com.tourapp.tour.product.tour.detail.db.*;
import org.jbundle.main.msg.db.*;

/**
 *  TourDetailScreen - .
 */
public class TourDetailScreen extends DetailScreen
{
    /**
     * Default constructor.
     */
    public TourDetailScreen()
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
    public TourDetailScreen(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
    {
        this();
        this.init(record, itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, properties);
    }
    /**
     * Initialize class fields.
     */
    public void init(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
    {
        super.init(record, itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, properties);
    }
    /**
     * Open the header record.
     * @return The new header record.
     */
    public Record openHeaderRecord()
    {
        return new TourHeaderOption(this);
    }
    /**
     * If there is a header record, return it, otherwise, return the main record.
     * The header record is the (optional) main record on gridscreens and is sometimes used
     * to enter data in a sub-record when a header is required.
     * @return The header record.
     */
    public Record getHeaderRecord()
    {
        return this.getRecord(TourHeaderOption.kTourHeaderOptionFile);
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        if (this.getMainRecord() instanceof TourHeaderDetail)
        {
            String strManualTransportID = Integer.toString(((ReferenceField)this.getMainRecord().getField(TourHeaderDetail.kInfoMessageTransportID)).getIDFromCode(MessageTransport.MANUAL));
            this.getMainRecord().getField(TourHeaderDetail.kInfoMessageTransportID).addListener(new DisableOnFieldHandler(this.getMainRecord().getField(TourHeaderDetail.kInfoStatusID), strManualTransportID, false));
            this.getMainRecord().getField(TourHeaderDetail.kInventoryMessageTransportID).addListener(new DisableOnFieldHandler(this.getMainRecord().getField(TourHeaderDetail.kInventoryStatusID), strManualTransportID, false));
            this.getMainRecord().getField(TourHeaderDetail.kCostMessageTransportID).addListener(new DisableOnFieldHandler(this.getMainRecord().getField(TourHeaderDetail.kCostStatusID), strManualTransportID, false));
            this.getMainRecord().getField(TourHeaderDetail.kProductMessageTransportID).addListener(new DisableOnFieldHandler(this.getMainRecord().getField(TourHeaderDetail.kProductStatusID), strManualTransportID, false));
            
            Converter convCheckMark = new RadioConverter(this.getMainRecord().getField(TourHeaderDetail.kInfoMessageTransportID), strManualTransportID, false);
            this.getMainRecord().getField(TourHeaderDetail.kInfoMessageTransportID).addListener(new RemoveConverterOnFreeHandler(convCheckMark));
            this.getMainRecord().getField(TourHeaderDetail.kInfoMessageTransportID).addListener(new CopyDataHandler(this.getMainRecord().getField(TourHeaderDetail.kInfoStatusID), null, convCheckMark));
            this.getMainRecord().getField(TourHeaderDetail.kInventoryMessageTransportID).addListener(new CopyDataHandler(this.getMainRecord().getField(TourHeaderDetail.kInventoryStatusID), null, convCheckMark));
            this.getMainRecord().getField(TourHeaderDetail.kCostMessageTransportID).addListener(new CopyDataHandler(this.getMainRecord().getField(TourHeaderDetail.kCostStatusID), null, convCheckMark));
            this.getMainRecord().getField(TourHeaderDetail.kProductMessageTransportID).addListener(new CopyDataHandler(this.getMainRecord().getField(TourHeaderDetail.kProductStatusID), null, convCheckMark));
        }
    }
    /**
     * AddStandardScreenFields Method.
     */
    public void addStandardScreenFields(boolean bTopNext)
    {
        this.getMainRecord().getField(TourHeaderDetail.kInfoStatusID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getMainRecord().getField(TourHeaderDetail.kInfoMessageTransportID).setupDefaultView(this.getNextLocation(ScreenConstants.RIGHT_WITH_DESC, ScreenConstants.DONT_SET_ANCHOR), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getMainRecord().getField(TourHeaderDetail.kInventoryStatusID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getMainRecord().getField(TourHeaderDetail.kInventoryMessageTransportID).setupDefaultView(this.getNextLocation(ScreenConstants.RIGHT_WITH_DESC, ScreenConstants.DONT_SET_ANCHOR), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getMainRecord().getField(TourHeaderDetail.kCostStatusID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getMainRecord().getField(TourHeaderDetail.kCostMessageTransportID).setupDefaultView(this.getNextLocation(ScreenConstants.RIGHT_WITH_DESC, ScreenConstants.DONT_SET_ANCHOR), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getMainRecord().getField(TourHeaderDetail.kProductStatusID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getMainRecord().getField(TourHeaderDetail.kProductMessageTransportID).setupDefaultView(this.getNextLocation(ScreenConstants.RIGHT_WITH_DESC, ScreenConstants.DONT_SET_ANCHOR), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getMainRecord().getField(TourHeaderDetail.kModifyCode).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getMainRecord().getField(TourHeaderDetail.kModifyID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
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
        if (ModifyTourSubField.SELECT_QUEUE.equals(message.getMessageHeader().getQueueName()))
        {
            String strRecord = (String)message.get(RecordMessageConstants.TABLE_NAME);
            Record recordToSync = ((ReferenceField)this.getMainRecord().getField(TourHeaderDetail.kModifyID)).getReferenceRecord();
            if ((strRecord != null) && (strRecord.equals(recordToSync.getTableNames(false))))
            {
                String bookmark = (String)message.get(DBConstants.STRING_OBJECT_ID_HANDLE);
                int iRecordMessageType = DBConstants.SELECT_TYPE;
                Record source = recordToSync; //????
                RecordMessageHeader messageHeader = new RecordMessageHeader(source, bookmark, null, iRecordMessageType, null);
                RecordMessage recordMessage = new RecordMessage(messageHeader);
                boolean bUpdateOnSelect = false;
                recordMessage.put(RecordMessageHeader.UPDATE_ON_SELECT, new Boolean(bUpdateOnSelect));
                recordMessage.put(RecordMessageHeader.RECORD_TO_UPDATE, recordToSync);
                ((ScreenField)recordToSync.getRecordOwner()).getScreenFieldView().handleMessage(recordMessage);
                return DBConstants.NORMAL_RETURN; // Handled
            }
        }
        return super.handleMessage(message);
    }
    /**
     * Make a sub-screen.
     * @return the new sub-screen.
     */
    public BasePanel makeSubScreen()
    {
        return new TourHeaderOptionHeaderScreen(null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC);
    }

}

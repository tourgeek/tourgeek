/**
 * @(#)TourDetailGridScreen.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.product.tour.detail.screen;

import java.util.Hashtable;
import java.util.Map;

import org.jbundle.base.db.Record;
import org.jbundle.base.db.event.SubCountHandler;
import org.jbundle.base.message.record.RecordMessageConstants;
import org.jbundle.base.model.DBConstants;
import org.jbundle.base.model.MenuConstants;
import org.jbundle.base.model.ScreenConstants;
import org.jbundle.base.screen.model.BasePanel;
import org.jbundle.base.screen.model.DetailGridScreen;
import org.jbundle.base.screen.model.SCannedBox;
import org.jbundle.base.screen.model.ScreenField;
import org.jbundle.base.screen.model.util.ScreenLocation;
import org.jbundle.model.DBException;
import org.jbundle.model.Task;
import org.jbundle.model.message.MessageManager;
import org.jbundle.thin.base.db.Converter;
import org.jbundle.thin.base.message.BaseMessage;
import org.jbundle.thin.base.message.BaseMessageHeader;
import org.jbundle.thin.base.message.MapMessage;
import org.jbundle.thin.base.message.MessageConstants;
import org.jbundle.thin.base.util.Application;

import com.tourapp.tour.product.tour.db.TourHeaderOption;
import com.tourapp.tour.product.tour.detail.db.TourHeaderAirHeader;
import com.tourapp.tour.product.tour.detail.db.TourHeaderDetail;
import com.tourapp.tour.product.tour.detail.db.TourHeaderLine;
import com.tourapp.tour.product.tour.screen.TourHeaderOptionHeaderScreen;

/**
 *  TourDetailGridScreen - .
 */
public class TourDetailGridScreen extends DetailGridScreen
{
    /**
     * Default constructor.
     */
    public TourDetailGridScreen()
    {
        super();
    }
    /**
     * Constructor.
     * @param record The main record for this screen.
     * @param itsLocation The location of this component within the parent.
     * @param parentScreen The parent screen.
     * @param fieldConverter The field this screen field is linked to.
     * @param iDisplayFieldDesc Do I display the field desc?.
     */
    public TourDetailGridScreen(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
     * TourDetailGridScreen Method.
     */
    public TourDetailGridScreen(Record recHeader, Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object>
     properties)
    {
        this();
        this.init(recHeader, record, itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, properties);
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        String iFieldSeq = TourHeaderOption.DETAIL_TOUR_DETAIL_COUNT;
        if (TourHeaderLine.TOUR_HEADER_LINE_FILE.equals(this.getMainRecord().getTableNames(false)))
            iFieldSeq = TourHeaderOption.DETAIL_PRICE_COUNT;
        if (TourHeaderAirHeader.TOUR_HEADER_AIR_HEADER_FILE.equals(this.getMainRecord().getTableNames(false)))
            iFieldSeq = TourHeaderOption.DETAIL_AIR_HEADER_COUNT;
        this.getMainRecord().addListener(new SubCountHandler(this.getHeaderRecord().getField(iFieldSeq), false, true));
    }
    /**
     * Add the navigation button(s) to the left of the grid row.
     */
    public void addNavButtons()
    {
        super.addNavButtons();
        String strMessage = this.getProperty(MessageConstants.QUEUE_NAME);
        String strTableName = this.getProperty(RecordMessageConstants.TABLE_NAME);
        if (strMessage != null)
            if ((strTableName != null) && (strTableName.equals(this.getMainRecord().getTableNames(false))))
        {
            new SCannedBox(this.getNextLocation(ScreenConstants.FIRST_SCREEN_LOCATION, ScreenConstants.SET_ANCHOR), this, null, ScreenConstants.DEFAULT_DISPLAY, null, null, MenuConstants.SELECT, MenuConstants.CLOSE, null)
            {
                public boolean doCommand(String strCommand, ScreenField sourceSField, int iCommandOptions)
                {
                    Task task = getTask();
                    Application application = (Application)task.getApplication();
                    String strQueueName = getProperty(MessageConstants.QUEUE_NAME);
                    MessageManager messageManager = application.getMessageManager();
                    Map<String,Object> propHeader = new Hashtable<String,Object>();
                    Map<String,Object> propMessage = new Hashtable<String,Object>();
                    Record record = getParentScreen().getMainRecord();
                    if ((record.getEditMode() == DBConstants.EDIT_CURRENT) || (record.getEditMode() == DBConstants.EDIT_IN_PROGRESS))
                    {
                        String bookmark = null;
                        try {
                            bookmark = record.getHandle(DBConstants.OBJECT_ID_HANDLE).toString();
                        } catch (DBException ex) {
                            ex.printStackTrace();
                        }
                        propMessage.put(RecordMessageConstants.TABLE_NAME, record.getTableNames(false));
                        propMessage.put(DBConstants.STRING_OBJECT_ID_HANDLE, bookmark);
                        BaseMessageHeader messageHeader = new BaseMessageHeader(strQueueName, MessageConstants.INTRANET_QUEUE, this, propHeader);
                        BaseMessage message = new MapMessage(messageHeader, propMessage);
                        messageManager.sendMessage(message);
                    }
                    return super.doCommand(strCommand, sourceSField, iCommandOptions);
                }
            };
        }
    }
    /**
     * OpenHeaderRecord Method.
     */
    public Record openHeaderRecord()
    {
        return new TourHeaderOption(this);
    }
    /**
     * SetupSFields Method.
     */
    public void setupSFields()
    {
        // Override this for other fields.
        this.getMainRecord().getField(TourHeaderDetail.DAY).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getMainRecord().getField(TourHeaderDetail.PRODUCT_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
    }
    /**
     * Make a sub-screen.
     * @return the new sub-screen.
     */
    public BasePanel makeSubScreen()
    {
        return new TourHeaderOptionHeaderScreen(null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, null);
    }

}

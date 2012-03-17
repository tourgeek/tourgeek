/**
 * @(#)ModifyTourSubField.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.product.tour.detail.db;

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
import com.tourapp.tour.product.tour.db.*;
import com.tourapp.tour.product.tour.screen.*;
import org.jbundle.thin.base.message.*;
import org.jbundle.thin.base.screen.message.*;
import org.jbundle.base.message.record.*;
import com.tourapp.tour.product.base.db.*;
import org.jbundle.thin.base.screen.*;
import org.jbundle.model.message.*;

/**
 *  ModifyTourSubField - Tour sub field to modify.
 */
public class ModifyTourSubField extends ReferenceField
{
    public static final String SELECT_QUEUE = "selectTourHeaderDetail";
    /**
     * Default constructor.
     */
    public ModifyTourSubField()
    {
        super();
    }
    /**
     * Constructor.
     * @param record The parent record.
     * @param strName The field name.
     * @param iDataLength The maximum string length (pass -1 for default).
     * @param strDesc The string description (usually pass null, to use the resource file desc).
     * @param strDefault The default value (if object, this value is the default value, if string, the string is the default).
     */
    public ModifyTourSubField(Record record, String strName, int iDataLength, String strDesc, Object strDefault)
    {
        this();
        this.init(record, strName, iDataLength, strDesc, strDefault);
    }
    /**
     * Initialize class fields.
     */
    public void init(Record record, String strName, int iDataLength, String strDesc, Object strDefault)
    {
        super.init(record, strName, iDataLength, strDesc, strDefault);
    }
    /**
     * Get (or make) the current record for this reference.
     */
    public Record makeReferenceRecord(RecordOwner recordOwner)
    {
        try {
            return (Record)this.getRecord().clone();
        } catch (CloneNotSupportedException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    /**
     * Set up the default screen control for this field.
     * @param itsLocation Location of this component on screen (ie., GridBagConstraint).
     * @param targetScreen Where to place this component (ie., Parent screen or GridBagLayout).
     * @param converter The converter to set the screenfield to.
     * @param iDisplayFieldDesc Display the label? (optional).
     * @param properties Extra properties
     * @return Return the component or ScreenField that is created for this field.
     */
    public ScreenComponent setupDefaultView(ScreenLoc itsLocation, ComponentParent targetScreen, Convert converter, int iDisplayFieldDesc, Map<String, Object> properties)
    {
        ScreenComponent sField = null;
        Record record = this.makeReferenceRecord();
        int fsField = record.getFieldSeq(TourHeaderLine.DESCRIPTION);
        if (record instanceof TourHeaderAirHeader)
            fsField = record.getFieldSeq(TourHeaderAirHeader.AIRLINE_DESC);
        if (record instanceof TourHeaderDetail)
        {
            Record recProduct = ((ReferenceField)record.getField(TourHeaderDetail.PRODUCT_ID)).getReferenceRecord();
            if (recProduct != null)
            {
                fsField = record.getFieldCount();   // This will be the sequence of the new field
                BaseField field = new StringField(record, "Description", 30, null, null);
                field.setVirtual(true);     // Just being careful
                record.getField(TourHeaderDetail.PRODUCT_ID).addListener(new ReadSecondaryHandler(recProduct));
                BaseField fldProductDesc = recProduct.getField(Product.DESCRIPTION);
                recProduct.addListener(new MoveOnValidHandler(field, fldProductDesc));
            }
            else
                fsField = record.getFieldSeq(TourHeaderDetail.DAY);    // Never (just in case)
        }
        sField = this.setupTableLookup(itsLocation, targetScreen, converter, iDisplayFieldDesc, record, -1, fsField, true, false);
        for (int i = 0; ; i++)
        {
            ScreenComponent screenField = this.getComponent(i);
            if (screenField instanceof SSelectBox)
            {
                ((SSelectBox)screenField).free();
                new SSelectBox((ScreenLocation)targetScreen.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), (BasePanel)targetScreen, (Converter)converter, ScreenConstants.DONT_DISPLAY_DESC, record)
                {
                    public boolean doCommand(String strCommand, ScreenField sourceSField, int iComandOptions)
                    {
                        Task task = null;
                        if (m_record.getRecordOwner() != null)
                            task = m_record.getRecordOwner().getTask();
                        if (task == null)
                            task = BaseApplet.getSharedInstance();
                        Application application = (Application)task.getApplication();
        
                        BaseField fldTourSubID = (BaseField)this.getConverter().getField();
                        Record recTourSub = fldTourSubID.getRecord();
                        Record recTourHeaderOption = null;
                        Record recTourHeader = null;
                        try {
                            RecordOwner recordOwner = this.getRecord().findRecordOwner();
                            recTourHeaderOption = new TourHeaderOption(recordOwner);
                            recordOwner.removeRecord(recTourHeaderOption);
                            recTourHeaderOption.getField(TourHeaderOption.ID).moveFieldToThis(recTourSub.getField(TourSub.TOUR_HEADER_OPTION_ID));
                            while (recTourHeaderOption.seek(null) == true)
                            {
                                if (TourHeaderOption.TOUR.equals(recTourHeaderOption.getField(TourHeaderOption.TOUR_OR_OPTION).toString()))
                                {   // Finally made it to the tour.
                                    recTourHeader = new TourHeader(recordOwner);
                                    recordOwner.removeRecord(recTourHeader); // This is belong to the new option screen
                                    recTourHeader.getField(TourHeader.ID).moveFieldToThis(recTourHeaderOption.getField(TourHeaderOption.TOUR_OR_OPTION_ID));
                                    boolean bSuccess = recTourHeader.seek(DBConstants.EQUALS);
                                    break;
                                }
                                recTourHeaderOption.getField(TourHeaderOption.ID).moveFieldToThis(recTourHeaderOption.getField(TourHeaderOption.TOUR_OR_OPTION_ID));
                            }
                            if (recTourHeader != null)
                            {
                                BasePanel parentScreen = Screen.makeWindow(application);
                                String strQueueName = ModifyTourSubField.SELECT_QUEUE;   // This is my private queue
                                parentScreen.setProperty(MessageConstants.QUEUE_NAME, strQueueName);
                                parentScreen.setProperty(RecordMessageConstants.TABLE_NAME, recTourSub.getTableNames(false));
                                GridScreen screen = new TourHeaderOptionGridScreen(recTourHeader, null, null, parentScreen, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, null);
                                MessageManager messageManager = application.getMessageManager();
                                BaseMessageReceiver receiver = (BaseMessageReceiver)messageManager.getMessageQueue(strQueueName, MessageConstants.INTRANET_QUEUE).getMessageReceiver();
                                BaseScreen screenTarget = (BaseScreen)sourceSField.getParentScreen();
                                receiver.createDefaultFilter(screenTarget);
                            }
                        } catch (DBException ex) {
                            ex.printStackTrace();
                        } finally {
                            if (recTourHeaderOption != null)
                                recTourHeaderOption.free();
                            recTourHeaderOption = null;
                        }
                        return true;    // Handled
                    }
                };
                break;
            }
            if (screenField == null)
                break;  // Just being careful.
        }
        return sField;
    }

}

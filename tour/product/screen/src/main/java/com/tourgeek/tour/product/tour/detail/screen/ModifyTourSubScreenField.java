/**
  * @(#)ModifyTourSubScreenField.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.tour.product.tour.detail.screen;

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
import org.jbundle.thin.base.screen.*;
import com.tourgeek.tour.product.tour.db.*;
import com.tourgeek.tour.product.tour.detail.db.*;
import org.jbundle.thin.base.message.*;
import com.tourgeek.tour.product.tour.screen.*;
import org.jbundle.base.message.record.*;
import org.jbundle.model.message.*;

/**
 *  ModifyTourSubScreenField - .
 */
public class ModifyTourSubScreenField extends SCannedBox
{
    /**
     * Default constructor.
     */
    public ModifyTourSubScreenField()
    {
        super();
    }
    /**
     * Constructor.
     * @param itsLocation The location of this component within the parent.
     * @param parentScreen The parent screen.
     * @param fieldConverter The field this screen field is linked to.
     * @param iDisplayFieldDesc Do I display the field desc?.
     */
    public ModifyTourSubScreenField(ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
    {
        this();
        this.init(itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, properties);
    }
    /**
     * Initialize class fields.
     */
    public void init(ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
    {
        super.init(itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, properties);
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

}

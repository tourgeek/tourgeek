/**
 * @(#)TourHeaderAirHeaderField.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.product.tour.detail.db;

import java.util.Hashtable;
import java.util.Map;

import org.jbundle.base.db.Record;
import org.jbundle.base.db.RecordOwner;
import org.jbundle.base.field.BaseField;
import org.jbundle.base.field.ReferenceField;
import org.jbundle.base.screen.model.BasePanel;
import org.jbundle.base.screen.model.Screen;
import org.jbundle.base.screen.model.ScreenField;
import org.jbundle.base.screen.model.util.SSelectBox;
import org.jbundle.base.screen.model.util.ScreenLocation;
import org.jbundle.base.util.DBParams;
import org.jbundle.base.util.ScreenConstants;
import org.jbundle.model.Task;
import org.jbundle.model.screen.ScreenComponent;
import org.jbundle.thin.base.db.Converter;
import org.jbundle.thin.base.screen.BaseApplet;
import org.jbundle.thin.base.util.Application;

/**
 *  TourHeaderAirHeaderField - .
 */
public class TourHeaderAirHeaderField extends ReferenceField
{
    /**
     * Default constructor.
     */
    public TourHeaderAirHeaderField()
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
    public TourHeaderAirHeaderField(Record record, String strName, int iDataLength, String strDesc, Object strDefault)
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
        return new TourHeaderAirHeader(recordOwner);
    }
    /**
     * Set up the default screen control for this field.
     * @param itsLocation Location of this component on screen (ie., GridBagConstraint).
     * @param targetScreen Where to place this component (ie., Parent screen or GridBagLayout).
     * @param converter The converter to set the screenfield to.
     * @param iDisplayFieldDesc Display the label? (optional).
     * @return Return the component or ScreenField that is created for this field.
     */
    public ScreenField setupDefaultView(ScreenLocation itsLocation, BasePanel targetScreen, Converter converter, int iDisplayFieldDesc)
    {
Record record = this.makeReferenceRecord();
ScreenField sField = this.setupTableLookup(itsLocation, targetScreen, converter, iDisplayFieldDesc, record, -1, TourHeaderAirHeader.kAirlineDesc, true, true);
for (int i = 0; ; i++)
{
    ScreenComponent screenField = this.getComponent(i);
    if (screenField instanceof SSelectBox)
    {
        ((SSelectBox)screenField).free();
        new SSelectBox(targetScreen.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), targetScreen, converter, ScreenConstants.DONT_DISPLAY_DESC, record)
        {
            public boolean doCommand(String strCommand, ScreenField sourceSField, int iCommandOptions)
            {
                BaseField fldTourSubID = (BaseField)this.getConverter().getField();
                Record recTourHeaderAir = fldTourSubID.getRecord();
                Record recTourHeaderAirHeader = makeReferenceRecord();

                String strOptionID = recTourHeaderAir.getField(TourSub.kTourHeaderOptionID).toString();
                ScreenLocation itsLocation = null;

                Task task = null;
                if (recTourHeaderAirHeader.getRecordOwner() != null)
                    task = recTourHeaderAirHeader.getRecordOwner().getTask();
                if (task == null)
                    task = BaseApplet.getSharedInstance();
                Application application = (Application)task.getApplication();

                BasePanel parent = Screen.makeWindow(application);
                int iDocMode = ScreenConstants.SELECT_MODE;
                boolean bCloneThisQuery = true;
                boolean bReadCurrentRecord = true;
                boolean bUseBaseTable = true;
                boolean bLinkGridToQuery = true;
                Map<String,Object> properties = new Hashtable<String,Object>();
                properties.put(DBParams.HEADER_OBJECT_ID, strOptionID);
                recTourHeaderAirHeader.makeScreen(itsLocation, parent, iDocMode, bCloneThisQuery, bReadCurrentRecord, bUseBaseTable, bLinkGridToQuery, properties);

                return true;    // Handled
            }
        };
    }
    if (screenField == null)
        break;  // Just being careful.
}
return sField;
    }

}

/**
  * @(#)TrxStatusField.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.tour.genled.db;

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

/**
 *  TrxStatusField - .
 */
public class TrxStatusField extends ReferenceField
{
    protected Boolean m_boolPopup = null;
    protected String m_strDesc;
    protected String m_strSystem;
    /**
     * Default constructor.
     */
    public TrxStatusField()
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
    public TrxStatusField(Record record, String strName, int iDataLength, String strDesc, Object strDefault)
    {
        this();
        this.init(record, strName, iDataLength, strDesc, strDefault);
    }
    /**
     * Initialize class fields.
     */
    public void init(Record record, String strName, int iDataLength, String strDesc, Object strDefault)
    {
        m_strDesc = "";
        m_strSystem = "";
        super.init(record, strName, iDataLength, strDesc, strDefault);
    }
    /**
     * GetDesc Method.
     */
    public String getDesc()
    {
        if ((m_strDesc != null) && (m_strDesc.length() > 0))
            return m_strDesc;
        Record record = this.getRecord();
        if (record instanceof Trx)
            return record.getTableNames(false);
        return null;
    }
    /**
     * GetSystem Method.
     */
    public String getSystem()
    {
        if ((m_strSystem != null) && (m_strSystem.length() < 0))
            return m_strSystem;
        Record record = this.getRecord();
        if (record != null)
            return record.getDatabaseName();
        return null;
    }
    /**
     * Get (or make) the current record for this reference.
     */
    public Record makeReferenceRecord(RecordOwner recordOwner)
    {
        Record record = new TrxStatus(recordOwner);
        String strSystem = this.getSystem();
        String strDesc = this.getDesc();
        if ((strSystem != null) && (strDesc != null))
        {
            record.setKeyArea(TrxStatus.SYSTEM_CODE_KEY);
            record.addListener(new StringSubFileFilter(strSystem, TrxStatus.SYSTEM_CODE, strDesc, TrxStatus.DESC_CODE, null, null));
        }
        return record;
    }
    /**
     * SetDesc Method.
     */
    public void setDesc(String strDesc)
    {
        m_strDesc = strDesc;
    }
    /**
     * SetSystem Method.
     */
    public void setSystem(String strSystem)
    {
        m_strSystem = strSystem;
    }
    /**
     * Enable/Disable the associated control(s).
     * @param bEnable If false, disable all this field's screen fields.
     */
    public void setEnabled(boolean bEnable)
    {
        super.setEnabled(bEnable);
        if (this.getReferenceRecord(null, false) != null)
            this.getReferenceRecord().getField(TrxStatus.STATUS_DESC).setEnabled(bEnable);
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
        Record record = this.getReferenceRecord();  // Get/make the record that describes the referenced class.
        if (this.isPopupControl())
            return this.setupTablePopup(itsLocation, targetScreen, converter, iDisplayFieldDesc, record, TrxStatus.SYSTEM_CODE_KEY, TrxStatus.STATUS_DESC, false, false);
        return this.setupTableLookup(itsLocation, targetScreen, converter, iDisplayFieldDesc, record, null, TrxStatus.STATUS_DESC, false, false);
    }
    /**
     * Is this screen control suppose to be a popup control?.
     */
    public boolean isPopupControl()
    {
        if (m_boolPopup != null)
            return m_boolPopup.booleanValue();
        Record record = this.getRecord();  // Get/make the record that describes the referenced class.
        //if (record.getListener(StringSubFileFilter.class.getName()) != null)
        //    return true;
        if (record.getClass().getName().indexOf("Batch") != -1)
            return true;
        return false;
    }
    /**
     * SetPopupControl Method.
     */
    public void setPopupControl(boolean bPopup)
    {
        m_boolPopup = new Boolean(bPopup);
    }

}

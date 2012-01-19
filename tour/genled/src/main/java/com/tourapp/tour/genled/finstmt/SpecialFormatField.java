/**
 * @(#)SpecialFormatField.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.genled.finstmt;

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
import com.tourapp.tour.genled.db.*;

/**
 *  SpecialFormatField - Special formats:
This special format is added as an XML tag on F/S output..
 */
public class SpecialFormatField extends StringField
{
    protected SpecialFormat m_recSpecialFormat = null;
    /**
     * Default constructor.
     */
    public SpecialFormatField()
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
    public SpecialFormatField(Record record, String strName, int iDataLength, String strDesc, Object strDefault)
    {
        this();
        this.init(record, strName, iDataLength, strDesc, strDefault);
    }
    /**
     * Initialize class fields.
     */
    public void init(Record record, String strName, int iDataLength, String strDesc, Object strDefault)
    {
        m_recSpecialFormat = null;
        super.init(record, strName, iDataLength, strDesc, strDefault);
    }
    /**
     * Free Method.
     */
    public void free()
    {
        m_recSpecialFormat = null;  // QueryConverter closed this
        super.free();
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
        converter = new FieldLengthConverter((Converter)converter, 25);
        if (m_recSpecialFormat == null)
        {
             m_recSpecialFormat = new SpecialFormat(Utility.getRecordOwner(this.getRecord()));
             if (m_recSpecialFormat.getRecordOwner() != null)
                m_recSpecialFormat.getRecordOwner().removeRecord(m_recSpecialFormat);
        }
        FieldConverter convert = new QueryConverter((Converter)converter, m_recSpecialFormat, SpecialFormat.kName, true);
        return createScreenComponent(ScreenModel.COMBO_BOX, itsLocation, targetScreen, convert, iDisplayFieldDesc, properties);
    }

}

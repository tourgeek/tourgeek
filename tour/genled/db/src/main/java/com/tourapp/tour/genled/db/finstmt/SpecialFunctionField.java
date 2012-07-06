/**
  * @(#)SpecialFunctionField.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.tour.genled.db.finstmt;

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
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import com.tourapp.tour.genled.db.*;

/**
 *  SpecialFunctionField - Special functions.
 */
public class SpecialFunctionField extends StringField
{
    protected SpecialFunction m_recSpecialFunction = null;
    /**
     * Default constructor.
     */
    public SpecialFunctionField()
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
    public SpecialFunctionField(Record record, String strName, int iDataLength, String strDesc, Object strDefault)
    {
        this();
        this.init(record, strName, iDataLength, strDesc, strDefault);
    }
    /**
     * Initialize class fields.
     */
    public void init(Record record, String strName, int iDataLength, String strDesc, Object strDefault)
    {
        m_recSpecialFunction = null;
        super.init(record, strName, iDataLength, strDesc, strDefault);
    }
    /**
     * Free Method.
     */
    public void free()
    {
        m_recSpecialFunction = null;    // QueryConverter closed this
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
        if (m_recSpecialFunction == null)
         {
             m_recSpecialFunction = new SpecialFunction(this.getRecord().findRecordOwner());
            if (m_recSpecialFunction.getRecordOwner() != null)
                m_recSpecialFunction.getRecordOwner().removeRecord(m_recSpecialFunction);
        }
        FieldConverter convert = new QueryConverter((Converter)converter, m_recSpecialFunction, SpecialFunction.NAME, true);
        return createScreenComponent(ScreenModel.COMBO_BOX, itsLocation, targetScreen, convert, iDisplayFieldDesc, properties);
    }

}

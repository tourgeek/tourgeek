/**
 * @(#)NumberFormatField.
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
import com.tourapp.tour.genled.db.*;

/**
 *  NumberFormatField - .
 */
public class NumberFormatField extends StringField
{
    protected NumberFormat m_recNumberFormat = null;
    /**
     * Default constructor.
     */
    public NumberFormatField()
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
    public NumberFormatField(Record record, String strName, int iDataLength, String strDesc, Object strDefault)
    {
        this();
        this.init(record, strName, iDataLength, strDesc, strDefault);
    }
    /**
     * Initialize class fields.
     */
    public void init(Record record, String strName, int iDataLength, String strDesc, Object strDefault)
    {
        m_recNumberFormat = null;
        super.init(record, strName, iDataLength, strDesc, strDefault);
    }
    /**
     * Free Method.
     */
    public void free()
    {
        m_recNumberFormat = null; // QueryConverter closed this
        super.free();
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
        converter = new FieldLengthConverter(converter, 25);
        if (m_recNumberFormat == null)
        {
            m_recNumberFormat = new NumberFormat(Utility.getRecordOwner(this.getRecord()));
            if (m_recNumberFormat.getRecordOwner() != null)
                m_recNumberFormat.getRecordOwner().removeRecord(m_recNumberFormat);
        }
        FieldConverter convert = new QueryConverter(converter, m_recNumberFormat, NumberFormat.kName, true);
        return new SComboBox(itsLocation, targetScreen, convert, iDisplayFieldDesc);
    }

}

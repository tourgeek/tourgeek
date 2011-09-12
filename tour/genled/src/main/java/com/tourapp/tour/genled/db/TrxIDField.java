/**
 * @(#)TrxIDField.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.genled.db;

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
import java.util.*;
import com.tourapp.tour.genled.screen.detail.*;

/**
 *  TrxIDField - Special field to display a G/L Detail Dist. Transaction ID.
 */
public class TrxIDField extends ReferenceField
{
    /**
     * Default constructor.
     */
    public TrxIDField()
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
    public TrxIDField(Record record, String strName, int iDataLength, String strDesc, Object strDefault)
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
     * Enable/Disable the associated control(s).
     * @param bEnable If false, disable all this field's screen fields.
     */
    public void setEnabled(boolean bEnable)
    {
        if (!bEnable)
            super.setEnabled(bEnable);
        else
        {
            for (int iPosition = 0; ; iPosition++)
            {
                ScreenField sField = (ScreenField)this.getComponent(iPosition);
                if (sField == null)
                    break;
                if (sField instanceof SEditText)
                    continue;   // Don't enable this control
                sField.setEnabled(bEnable);
            }
        }
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
        ScreenField sField = new SEditText(itsLocation, targetScreen, converter, iDisplayFieldDesc);
        TrxIDSField trxField = new TrxIDSField(targetScreen.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), targetScreen, this, ScreenConstants.DONT_DISPLAY_FIELD_DESC);
        return sField;
    }

}

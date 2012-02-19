/**
 * @(#)RoomTypeField.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.booking.db;

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
import com.tourapp.tour.product.base.db.*;
import com.tourapp.tour.booking.entry.pax.*;
import com.tourapp.tour.profile.db.*;
import com.tourapp.tour.booking.entry.base.*;
import com.tourapp.tour.booking.detail.db.*;

/**
 *  RoomTypeField - Room Code Popup.
 */
public class RoomTypeField extends ShortField
{
    /**
     * Default constructor.
     */
    public RoomTypeField()
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
    public RoomTypeField(Record record, String strName, int iDataLength, String strDesc, Object strDefault)
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
     * ConvertValueToIndex Method.
     */
    public int convertValueToIndex(double value)
    {
        return (int)(value);
    }
    /**
     * ConvertIndexToValue Method.
     */
    public double convertIndexToValue(int index)
    {
        return index;
    }
    /**
     * Convert this index to a string.
     * This method is usually overidden by popup fields.
     * @param index The index to convert.
     * @return The display string.
     */
    public String convertIndexToString(int index)
    {
        String tempString = Constants.BLANK;
        switch((int)this.convertIndexToValue(index))
        {
            case PaxCategory.SINGLE_ID:
                tempString = PaxCategory.SINGLE;break;
            case PaxCategory.DOUBLE_ID:
                tempString = PaxCategory.DOUBLE;break;
            case PaxCategory.TRIPLE_ID:
                tempString = PaxCategory.TRIPLE;break;
            case PaxCategory.QUAD_ID:
                tempString = PaxCategory.QUAD;break;
            case PaxCategory.CHILD_ID:
                tempString = PaxCategory.CHILD;break;
            default:
                tempString = DBConstants.BLANK;break;
        }
        return tempString;
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
        return createScreenComponent(ScreenModel.POPUP_BOX, itsLocation, targetScreen, this, iDisplayFieldDesc, properties);
    }

}

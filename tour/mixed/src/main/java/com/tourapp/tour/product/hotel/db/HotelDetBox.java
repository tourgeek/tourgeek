/**
 * @(#)HotelDetBox.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.product.hotel.db;

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
import com.tourapp.tour.product.hotel.screen.*;
import java.util.*;

/**
 *  HotelDetBox - .
 */
public class HotelDetBox extends SButtonBox
{
    /**
     * Default constructor.
     */
    public HotelDetBox()
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
    public HotelDetBox(ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, String strValue, String strDesc, String strImage,
     String strCommand, String strToolTip)
    {
        this();
        this.init(itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, strValue, strDesc, strImage, strCommand, strToolTip);
    }
    /**
     * Initialize class fields.
     */
    public void init(ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, String strValue, String strDesc, String strImage,
     String strCommand, String strToolTip)
    {
        super.init(itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, strValue, strDesc, strImage, strCommand, strToolTip);
    }
    /**
     * Move the control's value to the field.
     * @return An error value.
     */
    public int controlToField()
    {
        Hotel query = (Hotel)this.getParentScreen().getRecord(Hotel.HOTEL_FILE);
        //+CQBkHotelDet screen2 = new CQBkHotelDet(null, query);
        //+screen2.makeWindow();
        return DBConstants.NORMAL_RETURN;
    }

}

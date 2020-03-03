/**
  * @(#)LandDetBox.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.tour.product.land.screen;

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
import org.jbundle.base.screen.model.*;
import org.jbundle.base.screen.model.util.*;
import com.tourgeek.tour.product.base.db.*;
import com.tourgeek.tour.product.land.db.*;
import java.util.*;
import com.tourgeek.tour.base.db.*;
import java.text.*;
import com.tourgeek.tour.product.land.event.*;
import com.tourgeek.tour.message.land.request.data.*;
import com.tourgeek.model.tour.booking.detail.db.*;
import org.bson.*;

/**
 *  LandDetBox - Land Detail Maint Button.
 */
public class LandDetBox extends SButtonBox
{
    /**
     * Default constructor.
     */
    public LandDetBox()
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
    public LandDetBox(ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, String strValue, String strDesc, String strImage,
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
        //?m_ButtonUp = "";
        //?m_ButtonDown = "";
        //?m_ButtonText = "Land Rates";
        //?m_DisplayDesc = (displayFieldDesc != ScreenConstants.DONT_DISPLAY_FIELD_DESC);
        //?this.Create(itsLocation, parentScreen, fieldConverter, displayFieldDesc);
        super.init(itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, strValue, strDesc, strImage, strCommand, strToolTip);
    }
    /**
     * Move the control's value to the field.
     * @return An error value.
     */
    public int controlToField()
    {
        Land query = (Land)this.getParentScreen().getRecord(Land.LAND_FILE);
        //?CQBkLandDet screen2 = new CQBkLandDet(null, query);
        //?screen2.makeWindow();
        return DBConstants.NORMAL_RETURN;
    }

}

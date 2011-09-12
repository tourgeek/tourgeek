/**
 * @(#)SwitchBookingScreenHandler.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.booking.lookup;

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

/**
 *  SwitchBookingScreenHandler - .
 */
public class SwitchBookingScreenHandler extends FieldListener
{
    /**
     * Default constructor.
     */
    public SwitchBookingScreenHandler()
    {
        super();
    }
    /**
     * SwitchBookingScreenHandler Method.
     */
    public SwitchBookingScreenHandler(BaseField field)
    {
        this();
        this.init(field);
    }
    /**
     * Init Method.
     */
    public void init(BaseField field)
    {
        super.init(field);
        this.setRespondsToMode(DBConstants.INIT_MOVE, false);
    }
    /**
     * FieldChanged Method.
     */
    public int fieldChanged(boolean bDisplayOption, int iMoveMode)
    {
        BaseField field = this.getOwner();
        int iScreenNo = (int)field.getValue();
        Record screenRecord = field.getRecord();
        BaseScreen screen = (BaseScreen)screenRecord.getRecordOwner();
        
        BasePanel parentScreen = screen.getParentScreen();
        boolean bUseSameWindow = true;
        ScreenLocation itsLocation = screen.getScreenLocation();
        
        screen.removeRecord(screenRecord);
        screen.free();        // Remove this screen
        parentScreen.popHistory(1, false);  // Don't go back
        this.getSubScreen(screenRecord, itsLocation, parentScreen, null, iScreenNo);
        return super.fieldChanged(bDisplayOption, iMoveMode);
    }
    /**
     * GetSubScreen Method.
     */
    public BasePanel getSubScreen(Record record, ScreenLocation screenLocation, BasePanel parentScreen, Map properties, int iScreenNo)
    {
        switch(iScreenNo)
        {
        case DisplayTypeField.BOOKING_DISPLAY:   // Menu
            return new BookingGridScreen(record, screenLocation, parentScreen, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        case DisplayTypeField.TOUR_DISPLAY:  // Pax Maint
            return new TourGridScreen(record, screenLocation, parentScreen, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        case DisplayTypeField.TOUR_HEADER_DISPLAY: // Pax display
            return new TourHeaderGridScreen(record, screenLocation, parentScreen, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        default:
            break;
        };
        return null;
    }

}

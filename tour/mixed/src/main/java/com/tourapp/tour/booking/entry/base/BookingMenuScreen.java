/**
 * @(#)BookingMenuScreen.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.booking.entry.base;

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
import com.tourapp.tour.product.tour.db.*;
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.product.base.db.*;
import com.tourapp.tour.profile.db.*;
import com.tourapp.tour.booking.db.event.*;
import com.tourapp.tour.booking.entry.acctrec.*;
import com.tourapp.tour.booking.entry.pax.*;
import com.tourapp.tour.booking.entry.tour.*;
import com.tourapp.tour.booking.entry.detail.hotel.*;
import com.tourapp.tour.booking.entry.detail.land.*;
import com.tourapp.tour.booking.entry.acctpay.*;
import com.tourapp.tour.booking.entry.calendar.*;
import com.tourapp.tour.booking.entry.detail.detail.*;
import com.tourapp.tour.booking.entry.itin.*;
import com.tourapp.tour.booking.entry.detail.car.*;
import com.tourapp.tour.booking.entry.detail.trans.*;
import com.tourapp.tour.booking.entry.detail.cruise.*;
import com.tourapp.tour.booking.entry.detail.air.*;
import com.tourapp.tour.booking.entry.detail.item.*;
import com.tourapp.tour.booking.entry.detail.tour.*;

/**
 *  BookingMenuScreen - Booking entry.
 */
public class BookingMenuScreen extends MenuScreen
{
    /**
     * Default constructor.
     */
    public BookingMenuScreen()
    {
        super();
    }
    /**
     * Constructor.
     * @param record The main record for this screen.
     * @param itsLocation The location of this component within the parent.
     * @param parentScreen The parent screen.
     * @param fieldConverter The field this screen field is linked to.
     * @param iDisplayFieldDesc Do I display the field desc?
     * @param properties Addition properties to pass to the screen.
     */
    public BookingMenuScreen(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
    {
        this();
        this.init(record, itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, properties);
    }
    /**
     * Open the files and setup the screen.
     * @param record The main record for this screen.
     * @param itsLocation The location of this component within the parent.
     * @param parentScreen The parent screen.
     * @param fieldConverter The field this screen field is linked to.
     * @param iDisplayFieldDesc Do I display the field desc?
     * @param properties Additional properties to pass to the screen.
     */
    public void init(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
    {
        parentScreen.setProperty(DBParams.MENU, "booking");
        parentScreen.setProperty(BookingScreenHandler.SUB_SCREEN_PARAM, Integer.toString(BookingScreenHandler.MENU_SCREEN));
        if (!(parentScreen instanceof BookingHeaderScreen))
        {
            Record recMain = null;
            if (record instanceof Booking)
            {
                recMain = record;
                record = null;
            }
            parentScreen = new BookingHeaderScreen(recMain, itsLocation, parentScreen, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, null);
            itsLocation = parentScreen.getNextLocation(ScreenConstants.FLUSH_LEFT, ScreenConstants.FILL_REMAINDER);
        }
        super.init(record, itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, properties);
    }
    /**
     * Get the screen display title.
     */
    public String getTitle()
    {
        return "Booking entry";
    }
    /**
     * Add the toolbars that belong with this screen.
     * @return The new toolbar.
     */
    public ToolScreen addToolbars()
    {
        ToolScreen toolbar = super.addToolbars();
        ((BookingHeaderScreen)this.getParentScreen()).addHeaderToolbars(this);
        return toolbar;    // No toolbar (not even the default toolbars
    }
    /**
     * Add button(s) to the toolbar.
     */
    public void addToolbarButtons(ToolScreen toolScreen)
    {
        super.addToolbarButtons(toolScreen);
        BaseApplication application = (BaseApplication)this.getTask().getApplication();
        String strDesc = application.getResources(ResourceConstants.MENU_RESOURCE, true).getString(MenuConstants.GRID);
        new SCannedBox(toolScreen.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), toolScreen, null, ScreenConstants.DEFAULT_DISPLAY, null, strDesc, MenuConstants.GRID, MenuConstants.GRID, null);
    }
    /**
     * Process the command.
     * <br />Step 1 - Process the command if possible and return true if processed.
     * <br />Step 2 - If I can't process, pass to all children (with me as the source).
     * <br />Step 3 - If children didn't process, pass to parent (with me as the source).
     * <br />Note: Never pass to a parent or child that matches the source (to avoid an endless loop).
     * @param strCommand The command to process.
     * @param sourceSField The source screen field (to avoid echos).
     * @param iCommandOptions If this command creates a new screen, create in a new window?
     * @return true if success.
     */
    public boolean doCommand(String strCommand, ScreenField sourceSField, int iCommandOptions)
    {
        if (MenuConstants.GRID.equals(strCommand))
        {
            BasePanel parentScreen = this.getParentScreen();
            return (parentScreen.onForm(null, ScreenConstants.DISPLAY_MODE, false, iCommandOptions, null) != null);
        }
        else
        {
            Map<String,Object> properties = new Hashtable<String,Object>();
            Utility.parseArgs(properties, strCommand);
            String strItem = (String)properties.get("item");
            if (strItem != null)
            { // Let the screen listener change the screen.
                this.getScreenRecord().getField(BookingScreenRecord.kBkSubScreen).setString(strItem);
                return true;
            }
        }
        return false; // Don't call inherited
    }

}

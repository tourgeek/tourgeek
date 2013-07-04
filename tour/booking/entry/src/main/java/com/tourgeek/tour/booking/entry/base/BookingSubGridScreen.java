
package com.tourgeek.tour.booking.entry.base;

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
import com.tourgeek.tour.booking.db.*;

/**
 *  BookingSubGridScreen - The sub-grid screen..
 */
public class BookingSubGridScreen extends GridScreen
{
    /**
     * Default constructor.
     */
    public BookingSubGridScreen()
    {
        super();
    }
    /**
     * Constructor.
     * @param record The main record for this screen.
     * @param itsLocation The location of this component within the parent.
     * @param parentScreen The parent screen.
     * @param fieldConverter The field this screen field is linked to.
     * @param iDisplayFieldDesc Do I display the field desc?.
     */
    public BookingSubGridScreen(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
    {
        this();
        this.init(record, itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, properties);
    }
    /**
     * Constructor.
     * @param record The main record for this screen.
     * @param itsLocation The location of this component within the parent.
     * @param parentScreen The parent screen.
     * @param fieldConverter The field this screen field is linked to.
     * @param iDisplayFieldDesc Do I display the field desc?.
     */
    public void init(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
    {
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
        return "The sub-grid screen.";
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
     * If there is a header record, return it, otherwise, return the main record.
     * The header record is the (optional) main record on gridscreens and is sometimes used
     * to enter data in a sub-record when a header is required.
     * @return The header record.
     */
    public Record getHeaderRecord()
    {
        return this.getRecord(Booking.BOOKING_FILE);
    }
    /**
     * Read the current file in the header record given the current detail record.
     */
    public void syncHeaderToMain()
    {
        if ((this.getHeaderRecord().getEditMode() == DBConstants.EDIT_ADD)
            || (this.getHeaderRecord().getEditMode() == DBConstants.EDIT_NONE))
        { // Only call this if there is not a valid booking
            super.syncHeaderToMain();
        }
    }

}


package com.tourgeek.tour.profile.screen.detail;

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
import com.tourgeek.tour.profile.db.*;
import com.tourgeek.model.tour.profile.db.*;

/**
 *  ProfileDetailBaseGridScreen - Profile detail screens.
 */
public class ProfileDetailBaseGridScreen extends DetailGridScreen
{
    /**
     * Default constructor.
     */
    public ProfileDetailBaseGridScreen()
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
    public ProfileDetailBaseGridScreen(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
    {
        this();
        this.init(record, itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, properties);
    }
    /**
     * Initialize class fields.
     */
    public void init(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
    {
        super.init(record, itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, properties);
    }
    /**
     * Get the screen display title.
     */
    public String getTitle()
    {
        return "Profile detail screens";
    }
    /**
     * ProfileDetailBaseGridScreen Method.
     */
    public ProfileDetailBaseGridScreen(Record recProfile, Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object>
     properties)
    {
        this();
        this.init(recProfile, record, itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, properties);
    }
    /**
     * OpenHeaderRecord Method.
     */
    public Record openHeaderRecord()
    {
        return new Profile(this);
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        this.getHeaderRecord().getField(Profile.ID).addListener(new FieldReSelectHandler(this));  // Reselect on file change
    }
    /**
     * Add button(s) to the toolbar.
     */
    public void addToolbarButtons(ToolScreen toolScreen)
    {
        new SCannedBox(toolScreen.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), toolScreen, null, ScreenConstants.DEFAULT_DISPLAY, null, "Passenger Detail", MenuConstants.FORMDETAIL, "Detail Passenger Detail", null);
        new SCannedBox(toolScreen.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), toolScreen, null, ScreenConstants.DEFAULT_DISPLAY, null, "Request History", MenuConstants.FORMDETAIL, "Detail Request History", null);
        new SCannedBox(toolScreen.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), toolScreen, null, ScreenConstants.DEFAULT_DISPLAY, null, "Electronic Addresses", MenuConstants.FORMDETAIL, "Detail Electronic Addresses", null);
        new SCannedBox(toolScreen.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), toolScreen, null, ScreenConstants.DEFAULT_DISPLAY, null, "Extensions", MenuConstants.FORMDETAIL, "Detail Extensions", null);
        new SCannedBox(toolScreen.getNextLocation(ScreenConstants.FLUSH_LEFT, ScreenConstants.SET_ANCHOR), toolScreen, null, ScreenConstants.DEFAULT_DISPLAY, null, "Memberships", MenuConstants.FORMDETAIL, "Detail Memberships", null);
        new SCannedBox(toolScreen.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), toolScreen, null, ScreenConstants.DEFAULT_DISPLAY, null, "Certifications", MenuConstants.FORMDETAIL, "Detail Certifications", null);
        new SCannedBox(toolScreen.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), toolScreen, null, ScreenConstants.DEFAULT_DISPLAY, null, "Credit cards", MenuConstants.FORMDETAIL, "Detail Credit cards", null);
        new SCannedBox(toolScreen.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), toolScreen, null, ScreenConstants.DEFAULT_DISPLAY, null, "Travel interest", MenuConstants.FORMDETAIL, "Detail Travel interest", null);
        new SCannedBox(toolScreen.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), toolScreen, null, ScreenConstants.DEFAULT_DISPLAY, null, "Documents", MenuConstants.FORMDETAIL, "Detail Documents", null);
    }
    /**
     * Make a sub-screen.
     * @return the new sub-screen.
     */
    public BasePanel makeSubScreen()
    {
        return new ProfileHeaderScreen(null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, null);
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
        BaseScreen screen = null;
        ScreenLocation itsLocation = null;
        BasePanel parentScreen = this.getParentScreen();
        int iDocMode = m_iDisplayFieldDesc;
        Record recHeader = this.getHeaderRecord();
        if ((strCommand != null) && (strCommand.startsWith("Detail ")))
        {
            this.removeRecord(recHeader);
            this.free();
            if ("Detail Passenger Detail".equalsIgnoreCase(strCommand))
                screen = new ProfileDetailGridScreen(recHeader, null, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, null);
            if ("Detail Request History".equalsIgnoreCase(strCommand))
                screen = (BaseScreen)Record.makeNewScreen(ProfileModel.REQUEST_HISTORY_GRID_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, null, recHeader, true);
            if ("Detail Electronic Addresses".equalsIgnoreCase(strCommand))
                screen = new ElectronicAddressGridScreen(recHeader, null, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, null);
            if ("Detail Extensions".equalsIgnoreCase(strCommand))
                screen = new ExtensionGridScreen(recHeader, null, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, null);
            if ("Detail Memberships".equalsIgnoreCase(strCommand))
                screen = new MembershipsGridScreen(recHeader, null, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, null);
            if ("Detail Certifications".equalsIgnoreCase(strCommand))
                screen = new ProfileCertificationGridScreen(recHeader, null, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, null);
            if ("Detail Credit cards".equalsIgnoreCase(strCommand))
                screen = new ProfileCreditCardGridScreen(recHeader, null, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, null);
            if ("Detail Travel interest".equalsIgnoreCase(strCommand))
                screen = new InterestGridScreen(recHeader, null, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, null);
            if ("Detail Documents".equalsIgnoreCase(strCommand))
                screen = new DocumentGridScreen(recHeader, null, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, null);
        
            parentScreen.popHistory(1, false);      // Don't maintain the stack for these screens.
        
            return true;
        }
        return super.doCommand(strCommand, sourceSField, iCommandOptions);
    }

}

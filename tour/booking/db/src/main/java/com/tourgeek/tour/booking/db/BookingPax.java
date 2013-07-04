
package com.tourgeek.tour.booking.db;

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
import com.tourgeek.tour.product.base.db.*;
import com.tourgeek.tour.profile.db.*;
import com.tourgeek.tour.booking.db.event.*;
import org.jbundle.base.screen.model.util.*;
import com.tourgeek.tour.base.field.*;
import com.tourgeek.model.tour.booking.db.*;

/**
 *  BookingPax - Passenger Booking Detail.
 */
public class BookingPax extends VirtualRecord
     implements BookingPaxModel
{
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor.
     */
    public BookingPax()
    {
        super();
    }
    /**
     * Constructor.
     */
    public BookingPax(RecordOwner screen)
    {
        this();
        this.init(screen);
    }
    /**
     * Initialize class fields.
     */
    public void init(RecordOwner screen)
    {
        super.init(screen);
    }
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(BOOKING_PAX_FILE, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "Passenger";
    }
    /**
     * Get the Database Name.
     */
    public String getDatabaseName()
    {
        return "booking";
    }
    /**
     * Is this a local (vs remote) file?.
     */
    public int getDatabaseType()
    {
        return DBConstants.REMOTE | DBConstants.USER_DATA;
    }
    /**
     * Make a default screen.
     */
    public ScreenParent makeScreen(ScreenLoc itsLocation, ComponentParent parentScreen, int iDocMode, Map<String,Object> properties)
    {
        ScreenParent screen = null;
        if ((iDocMode & ScreenConstants.MAINT_MODE) == ScreenConstants.MAINT_MODE)
            screen = Record.makeNewScreen(BOOKING_PAX_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        else if ((iDocMode & ScreenConstants.DISPLAY_MODE) == ScreenConstants.DISPLAY_MODE)
            screen = Record.makeNewScreen(BOOKING_PAX_GRID_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        else
            screen = super.makeScreen(itsLocation, parentScreen, iDocMode, properties);
        return screen;
    }
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        if (iFieldSeq == 0)
        {
            field = new CounterField(this, ID, 5, null, null);
            field.setHidden(true);
        }
        //if (iFieldSeq == 1)
        //{
        //  field = new RecordChangedField(this, LAST_CHANGED, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //  field.setHidden(true);
        //}
        //if (iFieldSeq == 2)
        //{
        //  field = new BooleanField(this, DELETED, Constants.DEFAULT_FIELD_LENGTH, null, new Boolean(false));
        //  field.setHidden(true);
        //}
        if (iFieldSeq == 3)
            field = new BookingField(this, BOOKING_ID, 6, null, null);
        if (iFieldSeq == 4)
            field = new ShortField(this, SEQUENCE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 5)
            field = new StringField(this, REMOTE_REFERENCE_NO, 60, null, null);
        if (iFieldSeq == 6)
            field = new PaxCategoryField(this, PAX_CATEGORY_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 7)
            field = new ProfileField(this, PROFILE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 8)
        {
            field = new IntegerField(this, SHARE_BOOKING_PAX_ID, 6, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 9)
            field = new MemoField(this, COMMENTS, 9999, null, null);
        if (iFieldSeq == 10)
            field = new TitleField(this, NAME_PREFIX, 4, null, null);
        if (iFieldSeq == 11)
            field = new StringField(this, FIRST_NAME, 20, null, null);
        if (iFieldSeq == 12)
            field = new StringField(this, MIDDLE_NAME, 20, null, null);
        if (iFieldSeq == 13)
            field = new StringField(this, SUR_NAME, 20, null, null);
        if (iFieldSeq == 14)
            field = new DateField(this, DATE_OF_BIRTH, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 15)
            field = new GenderField(this, GENDER, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 16)
            field = new SmokerField(this, SMOKER, 1, null, null);
        if (field == null)
            field = super.setupField(iFieldSeq);
        return field;
    }
    /**
     * Add this key area description to the Record.
     */
    public KeyArea setupKey(int iKeyArea)
    {
        KeyArea keyArea = null;
        if (iKeyArea == 0)
        {
            keyArea = this.makeIndex(DBConstants.UNIQUE, ID_KEY);
            keyArea.addKeyField(ID, DBConstants.ASCENDING);
        }
        if (iKeyArea == 1)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, BOOKING_ID_KEY);
            keyArea.addKeyField(BOOKING_ID, DBConstants.ASCENDING);
            keyArea.addKeyField(SEQUENCE, DBConstants.ASCENDING);
        }
        if (iKeyArea == 2)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, SUR_NAME_KEY);
            keyArea.addKeyField(SUR_NAME, DBConstants.ASCENDING);
        }
        if (iKeyArea == 3)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, PROFILE_ID_KEY);
            keyArea.addKeyField(PROFILE_ID, DBConstants.ASCENDING);
        }
        if (keyArea == null)
            keyArea = super.setupKey(iKeyArea);     
        return keyArea;
    }
    /**
     * Add the booking toolbar buttons to sync the profile records.
     */
    public void addToolbarButtons(ComponentParent toolbar, Record recProfile)
    {
        ResourceBundle resources = ((BaseApplication)recProfile.getRecordOwner().getTask().getApplication()).getResources(ResourceConstants.BOOKING_RESOURCE, true);
        String strKey = "Select Passengers";
        Converter converter = null;
        
        String strCommand = Utility.addURLParam(null, DBParams.COMMAND, MenuConstants.LOOKUP);
        ReferenceField fldProfileTypeID = (ReferenceField)recProfile.getField(Profile.PROFILE_TYPE_ID);
        String strFieldName = fldProfileTypeID.getFieldName();
        String strValue = Integer.toString(fldProfileTypeID.getIDFromCode("Contact"));
        if (strValue != null)
            strCommand = Utility.addURLParam(strCommand, strFieldName, strValue);
        Map<String,Object> properties = new HashMap<String,Object>();
        properties.put(ScreenModel.DESC, resources.getString(strKey));
        properties.put(ScreenModel.IMAGE, MenuConstants.LOOKUP);
        properties.put(ScreenModel.COMMAND, strCommand);
        properties.put(ScreenModel.TOOLTIP, resources.getString(strKey + "Tip"));
        properties.put(ScreenModel.RECORD, recProfile);
        BaseField.createScreenComponent(ScreenModel.CANNED_BOX, toolbar.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.ANCHOR_DEFAULT), toolbar, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        
        strKey = "Add to Profile";
        converter = new AddPaPaxConverter(null);
        properties = new HashMap<String,Object>();
        properties.put(ScreenModel.DESC, resources.getString(strKey));
        properties.put(ScreenModel.IMAGE, Booking.BUTTON_LOCATION + "Passenger");
        properties.put(ScreenModel.TOOLTIP, resources.getString(strKey + "Tip"));
        BaseField.createScreenComponent(ScreenModel.BUTTON_BOX, toolbar.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.ANCHOR_DEFAULT), toolbar, converter, ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);

    }
    /**
     * Add the pax count screen fields to this toolbar.
     */
    public void addToolbarFields(ComponentParent toolbar, Record recBooking)
    {
        Converter converter = null;
        converter = recBooking.getField(Booking.PAX);
        ScreenComponent sField = converter.setupDefaultView(toolbar.getNextLocation(ScreenConstants.NEXT_INPUT_LOCATION, ScreenConstants.ANCHOR_DEFAULT), toolbar, ScreenConstants.DEFAULT_DISPLAY);
        sField.setEnabled(false);
        converter = recBooking.getField(Booking.SINGLE_PAX);
        sField = converter.setupDefaultView(toolbar.getNextLocation(ScreenConstants.RIGHT_WITH_DESC, ScreenConstants.ANCHOR_DEFAULT), toolbar, ScreenConstants.DEFAULT_DISPLAY);
        sField.setEnabled(false);
        converter = new RoomConverter(recBooking.getField(Booking.DOUBLE_PAX), (short)2);
        sField = converter.setupDefaultView(toolbar.getNextLocation(ScreenConstants.RIGHT_WITH_DESC, ScreenConstants.ANCHOR_DEFAULT), toolbar, ScreenConstants.DEFAULT_DISPLAY);
        sField.setEnabled(false);
        converter = new RoomConverter(recBooking.getField(Booking.TRIPLE_PAX), (short)3);
        sField = converter.setupDefaultView(toolbar.getNextLocation(ScreenConstants.RIGHT_WITH_DESC, ScreenConstants.ANCHOR_DEFAULT), toolbar, ScreenConstants.DEFAULT_DISPLAY);
        sField.setEnabled(false);
        converter = new RoomConverter(recBooking.getField(Booking.QUAD_PAX), (short)4);
        sField = converter.setupDefaultView(toolbar.getNextLocation(ScreenConstants.RIGHT_WITH_DESC, ScreenConstants.ANCHOR_DEFAULT), toolbar, ScreenConstants.DEFAULT_DISPLAY);
        sField.setEnabled(false);
        converter = recBooking.getField(Booking.CHILDREN);
        sField = converter.setupDefaultView(toolbar.getNextLocation(ScreenConstants.RIGHT_WITH_DESC, ScreenConstants.ANCHOR_DEFAULT), toolbar, ScreenConstants.DEFAULT_DISPLAY);
        sField.setEnabled(false);
    }
    /**
     * Add the behaviors that go with a screen that has access to the booking file.
     */
    public void addBookingBehaviors(RecordOwner recordOwner)
    {
        Record recBookingPax = (Record)recordOwner.getRecord(BookingPax.BOOKING_PAX_FILE);
        Record recBooking = (Record)recordOwner.getRecord(Booking.BOOKING_FILE);
        
        recBookingPax.addListener(new SubFileFilter(recBooking, true));
        
        recBookingPax.getField(BookingPax.PAX_CATEGORY_ID).addListener(new InitFieldHandler(recordOwner.getRecord(BookingControl.BOOKING_CONTROL_FILE).getField(BookingControl.PAX_CATEGORY_ID)));
        recBookingPax.getField(BookingPax.PAX_CATEGORY_ID).addListener(new InitOnceFieldHandler(null));
        
        if (recBooking.getEditMode() == DBConstants.EDIT_ADD)
            if (!recBooking.getField(Booking.PAX).isModified())
            if (recBooking.getField(Booking.PAX).getValue() != 0)
        {   // These are going to be initialized anyway, so set them to 0 so change record is not tripped.
            boolean[] rgbEnabled = recBooking.getField(Booking.PAX).setEnableListeners(false);
            recBooking.getField(Booking.PAX).setValue(0, DBConstants.DISPLAY, DBConstants.INIT_MOVE);
            recBooking.getField(Booking.PAX).setModified(false);
            recBooking.getField(Booking.PAX).setEnableListeners(rgbEnabled);
            rgbEnabled = recBooking.getField(Booking.DOUBLE_PAX).setEnableListeners(false);
            recBooking.getField(Booking.DOUBLE_PAX).setValue(0, DBConstants.DISPLAY, DBConstants.INIT_MOVE);
            recBooking.getField(Booking.DOUBLE_PAX).setModified(false);
            recBooking.getField(Booking.DOUBLE_PAX).setEnableListeners(rgbEnabled);
            rgbEnabled = recBooking.getField(Booking.SINGLE_PAX).setEnableListeners(false);
            recBooking.getField(Booking.SINGLE_PAX).setValue(0, DBConstants.DISPLAY, DBConstants.INIT_MOVE);
            recBooking.getField(Booking.SINGLE_PAX).setModified(false);
            recBooking.getField(Booking.SINGLE_PAX).setEnableListeners(rgbEnabled);
        }
        
        recBookingPax.addListener(new PaxSelectHandler(recBooking, (Record)recordOwner.getRecord(PaxCategory.PAX_CATEGORY_FILE)));
        
        recBookingPax.getField(BookingPax.SEQUENCE).addListener(new InitFieldHandler(recBooking.getField(Booking.PAX)));
        
        if (recordOwner instanceof ScreenParent)
        {
            Profile recProfile = (Profile)recordOwner.getRecord(Profile.PROFILE_FILE);
            Profile recProfileDetail = (Profile)((ReferenceField)this.getField(BookingPax.PROFILE_ID)).getReferenceRecord();
            FileListener selectListener = new PaxDetailSelectHandler((BookingPax)recordOwner.getRecord(BookingPax.BOOKING_PAX_FILE), recProfileDetail);
            recProfileDetail.addListener(selectListener);
            ((Record)recordOwner.getScreenRecord()).addListener(new FileRemoveBOnCloseHandler(selectListener));
            
            FileListener paxSelectListener = new PaPaxSelectHandler((BookingPax)recBookingPax, recProfile, recProfileDetail);
            recProfile.addListener(paxSelectListener);
            
            if (!(recordOwner instanceof GridScreenParent))
            {   // Screen
                FieldListener fieldListener = new CopyFieldHandler((BaseField)((ScreenParent)recordOwner).getScreenRecord().getField(BookingScreenRecord.LAST_LAST_NAME), null);
                fieldListener.setRespondsToMode(DBConstants.INIT_MOVE, false);
                recBookingPax.getField(BookingPax.SUR_NAME).addListener(fieldListener);
                
                fieldListener = new MoveOnChangeHandler(recBookingPax.getField(BookingPax.SUR_NAME), (BaseField)((ScreenParent)recordOwner).getScreenRecord().getField(BookingScreenRecord.LAST_LAST_NAME), false, false);
                fieldListener.setRespondsToMode(DBConstants.SCREEN_MOVE, false);
                fieldListener.setRespondsToMode(DBConstants.INIT_MOVE, true);
                fieldListener.setRespondsToMode(DBConstants.READ_MOVE, false);
                recBookingPax.getField(BookingPax.SUR_NAME).addListener(fieldListener);
            }
                    
            if (recordOwner instanceof GridScreenParent)
            {
                FieldListener reSelect = new FieldReSelectHandler((GridScreenParent)recordOwner);
                recBooking.getField(Booking.ID).addListener(reSelect);
                this.addListener(new FileRemoveBOnCloseHandler(reSelect));
            }
        }
    }
    /**
     * AddPaPax Method.
     */
    public void addPaPax(Record recProfile, Record recProfileDetail)
    {
        if ((recProfile.getEditMode() != Constants.EDIT_IN_PROGRESS) && (recProfile.getEditMode() != Constants.EDIT_CURRENT))
            return;
        BaseField fldProfileID = this.getField(BookingPax.PROFILE_ID);
        boolean[] rgbEnabled = fldProfileID.setEnableListeners(false);
        FileListener subFileBeh = new SubFileFilter(recProfile.getField(Profile.ID), Profile.MAIN_PROFILE_ID, null, null, null, null);
        recProfileDetail.addListener(subFileBeh);
        recProfileDetail.setKeyArea(Profile.MAIN_PROFILE_ID_KEY);
        boolean bMainRecordAdded = false;
        try   {
            recProfileDetail.close();
            while (recProfileDetail.hasNext())
            {
                recProfileDetail.next();
                if (recProfileDetail.getHandle(DBConstants.BOOKMARK_HANDLE).equals(recProfile.getHandle(DBConstants.BOOKMARK_HANDLE)))
                    bMainRecordAdded = true;    // Good the main record was part of the detail
                this.addNew();
                this.movePaPaxDetail(recProfileDetail);
                this.add();
            }
            if (!bMainRecordAdded)
            {   // Main record was not part of the detail, so add it now.
                this.addNew();
                this.movePaPaxDetail(recProfile);
                this.add();
            }
            this.addNew();
        } catch (DBException ex) {
            ex.printStackTrace();
        } finally { // Set it back
            fldProfileID.setEnableListeners(rgbEnabled);
            recProfileDetail.removeListener(subFileBeh, true);
            recProfileDetail.setKeyArea(Profile.ID_KEY);
        }
    }
    /**
     * MovePaPaxDetail Method.
     */
    public void movePaPaxDetail(Record recProfile)
    {
        this.getField(BookingPax.PROFILE_ID).moveFieldToThis(recProfile.getField(Profile.ID), DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
        this.getField(BookingPax.NAME_PREFIX).moveFieldToThis(recProfile.getField(Profile.NAME_PREFIX), DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
        this.getField(BookingPax.FIRST_NAME).moveFieldToThis(recProfile.getField(Profile.NAME_FIRST), DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
        this.getField(BookingPax.MIDDLE_NAME).moveFieldToThis(recProfile.getField(Profile.NAME_MIDDLE), DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
        this.getField(BookingPax.SUR_NAME).moveFieldToThis(recProfile.getField(Profile.NAME_SUR), DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
        this.getField(BookingPax.SMOKER).moveFieldToThis(recProfile.getField(Profile.SMOKER), DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
    }
    /**
     * Add these booking passengers to the profile.
     */
    public int addPaxDetail(Record recBookingPax, Record recProfile)
    {
        int iErrorCode = DBConstants.NORMAL_RETURN;
        int iOldOpenMode = recProfile.getOpenMode();
        recProfile.setOpenMode(iOldOpenMode & ~DBConstants.OPEN_READ_ONLY);
        if (!(recBookingPax.getTable() instanceof GridTable))
            {new GridTable(null, recBookingPax);} // Never (just being careful)
        
        try
        {
            Object bookmarkMain = null;
            for (int i = 0; ; i++)
            {
                recBookingPax = (Record)recBookingPax.getTable().get(i);
                if (recBookingPax == null)
                    break;
                if (!recBookingPax.getField(BookingPax.PROFILE_ID).isNull())
                {
                    if (bookmarkMain == null)
                    {
                        recProfile = ((ReferenceField)recBookingPax.getField(BookingPax.PROFILE_ID)).getReference();
                        if (recProfile != null)
                            bookmarkMain = recProfile.getField(Profile.MAIN_PROFILE_ID).getData();
                    }
                    continue;
                }
        
                recProfile.addNew();
                recProfile.getField(Profile.NAME_PREFIX).moveFieldToThis(recBookingPax.getField(BookingPax.NAME_PREFIX));
                recProfile.getField(Profile.NAME_FIRST).moveFieldToThis(recBookingPax.getField(BookingPax.FIRST_NAME));
                recProfile.getField(Profile.NAME_MIDDLE).moveFieldToThis(recBookingPax.getField(BookingPax.MIDDLE_NAME));
                recProfile.getField(Profile.NAME_SUR).moveFieldToThis(recBookingPax.getField(BookingPax.SUR_NAME));
                recProfile.getField(Profile.SMOKER).moveFieldToThis(recBookingPax.getField(BookingPax.SMOKER));
                recProfile.getField(Profile.MAIN_PROFILE_ID).setData(bookmarkMain);
                recProfile.add();
        
                Object bookmark = recProfile.getLastModified(DBConstants.BOOKMARK_HANDLE);
                if (bookmarkMain == null)
                {
                    bookmarkMain = bookmark;
                    if (recProfile.setHandle(bookmark, DBConstants.BOOKMARK_HANDLE) != null)    // always
                    {
                        recProfile.edit();
                        recProfile.getField(Profile.MAIN_PROFILE_ID).setData(bookmarkMain);
                        recProfile.set();
                    }
                }
        
                recBookingPax.edit();
                recBookingPax.getField(BookingPax.PROFILE_ID).setData(bookmark);
                recBookingPax.set();
            }
            if (bookmarkMain != null) // Return the main profile entry
                recProfile.setHandle(bookmarkMain, DBConstants.BOOKMARK_HANDLE);
        } catch (DBException ex) {
            iErrorCode = ex.getErrorCode();
        } finally {
            recProfile.setOpenMode(iOldOpenMode);
        }
        return iErrorCode;
    }

}

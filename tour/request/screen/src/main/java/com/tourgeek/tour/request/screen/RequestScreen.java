/**
  * @(#)RequestScreen.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.tour.request.screen;

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
import com.tourgeek.tour.request.db.*;
import com.tourgeek.tour.profile.db.*;
import com.tourgeek.tour.base.db.shared.*;

/**
 *  RequestScreen - Brochure Requests.
 */
public class RequestScreen extends Screen
{
    /**
     * Default constructor.
     */
    public RequestScreen()
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
    public RequestScreen(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
        return "Brochure Requests";
    }
    /**
     * Override this to open the main file.
     * <p />You should pass this record owner to the new main file (ie., new MyNewTable(thisRecordOwner)).
     * @return The new record.
     */
    public Record openMainRecord()
    {
        return new Request(this);
    }
    /**
     * Override this to open the other files in the query.
     */
    public void openOtherRecords()
    {
        super.openOtherRecords();
        new RequestControl(this);
        new Profile(this);
        
        new RequestDetail(this);
        new BundleDetail(this);
        new Brochure(this);
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        Request recRequest = (Request)this.getRecord(Request.REQUEST_FILE);
        Profile recProfile = (Profile)this.getRecord(Profile.PROFILE_FILE);
        RequestDetail recRequestDetail = (RequestDetail)this.getRecord(RequestDetail.REQUEST_DETAIL_FILE);
        BundleDetail recBundleDetail = (BundleDetail)this.getRecord(BundleDetail.BUNDLE_DETAIL_FILE);
        Brochure recItem = (Brochure)this.getRecord(Brochure.BROCHURE_FILE);
        RequestControl recRequestControl = (RequestControl)this.getRecord(RequestControl.REQUEST_CONTROL_FILE);
        GridScreen gsBrocDetail = (GridScreen)this.getSField(this.getSFieldCount() - 1);
        RequestInput recItemReqInput = (RequestInput)gsBrocDetail.getRecord(RequestInput.REQUEST_INPUT_FILE);
        
        recRequest.getField(Request.SEND_VIA_CODE).addListener(new InitOnceFieldHandler(null));
        recRequest.getField(Request.SEND_VIA_CODE).addListener(new InitFieldHandler(recRequestControl.getField(RequestControl.SEND_VIA_CODE)));
        recRequest.getField(Request.BUNDLE_ID).addListener(new InitFieldHandler(recRequestControl.getField(RequestControl.BUNDLE_ID)));
        recRequest.getField(Request.BUNDLE_QTY).addListener(new InitFieldHandler(recRequestControl.getField(RequestControl.BROCHURE_QTY)));
        // Here's the code to move the agency info on select
        ReadSecondaryHandler pSecondaryBehavior = new ReadSecondaryHandler(recProfile, Profile.PROFILE_CODE_KEY);
        pSecondaryBehavior.setRespondsToMode(DBConstants.READ_MOVE, false);
        recRequest.getField(Request.PROFILE_CODE).addListener(pSecondaryBehavior);
        
        pSecondaryBehavior.addFieldSeqPair(Request.PROFILE_ID);
        pSecondaryBehavior.addFieldSeqPair(Request.PROFILE_CODE, Profile.PROFILE_CODE, DBConstants.MOVE_TO_DEPENDENT, DBConstants.DONT_MOVE_DEPENDENT_BACK);
        pSecondaryBehavior.addFieldSeqPair(Request.GENERIC_NAME, Profile.GENERIC_NAME, DBConstants.MOVE_TO_DEPENDENT, DBConstants.DONT_MOVE_DEPENDENT_BACK);
        pSecondaryBehavior.addFieldSeqPair(Request.ADDRESS_LINE_1, Profile.ADDRESS_LINE_1, DBConstants.MOVE_TO_DEPENDENT, DBConstants.DONT_MOVE_DEPENDENT_BACK);
        pSecondaryBehavior.addFieldSeqPair(Request.ADDRESS_LINE_2, Profile.ADDRESS_LINE_2, DBConstants.MOVE_TO_DEPENDENT, DBConstants.DONT_MOVE_DEPENDENT_BACK);
        pSecondaryBehavior.addFieldSeqPair(Request.CITY_OR_TOWN, Profile.CITY_OR_TOWN, DBConstants.MOVE_TO_DEPENDENT, DBConstants.DONT_MOVE_DEPENDENT_BACK);
        pSecondaryBehavior.addFieldSeqPair(Request.STATE_OR_REGION, Profile.STATE_OR_REGION, DBConstants.MOVE_TO_DEPENDENT, DBConstants.DONT_MOVE_DEPENDENT_BACK);
        pSecondaryBehavior.addFieldSeqPair(Request.POSTAL_CODE, Profile.POSTAL_CODE, DBConstants.MOVE_TO_DEPENDENT, DBConstants.DONT_MOVE_DEPENDENT_BACK);
        pSecondaryBehavior.addFieldSeqPair(Request.COUNTRY, Profile.COUNTRY, DBConstants.MOVE_TO_DEPENDENT, DBConstants.DONT_MOVE_DEPENDENT_BACK);
        pSecondaryBehavior.addFieldSeqPair(Request.ATTENTION, Profile.NAME_ORDERED, DBConstants.MOVE_TO_DEPENDENT, DBConstants.DONT_MOVE_DEPENDENT_BACK);
        pSecondaryBehavior.addFieldSeqPair(Request.EMAIL, Profile.EMAIL, DBConstants.MOVE_TO_DEPENDENT, DBConstants.DONT_MOVE_DEPENDENT_BACK);
        
        recRequest.getField(Request.PROFILE_CODE).addListener(new ChangeFocusOnChangeHandler(recRequest.getField(Request.ATTENTION)));
        
        recBundleDetail.setKeyArea(BundleDetail.BUNDLE_ID_KEY);
        recBundleDetail.addListener(new SubFileFilter(recRequest.getField(Request.BUNDLE_ID), BundleDetail.BUNDLE_ID, null, null, null, null));
        
        recRequestDetail.addListener(new SubFileFilter(recRequest));
        // Set up the initial detail
        
        recItemReqInput.getField(RequestInput.BROCHURE_QTY).addListener(new InitFieldHandler(recRequest.getField(Request.BUNDLE_QTY)));
        // These will guarantee that update calls the SetupBrocDetail listener
        recItemReqInput.getField(RequestInput.BROCHURE_QTY).addListener(new SetDirtyOnChangeHandler(recRequest.getField(Request.BUNDLE_ID), false, true));
        recItemReqInput.getField(RequestInput.BROCHURE_ID).addListener(new SetDirtyOnChangeHandler(recRequest.getField(Request.BUNDLE_ID), false, true));
        
        if ((recRequest.getEditMode() == Constants.EDIT_NONE) || (recRequest.getEditMode() == Constants.EDIT_ADD))
        {
            recRequest.getField(Request.BUNDLE_ID).initField(DBConstants.DISPLAY);
            recRequest.getField(Request.BUNDLE_QTY).initField(DBConstants.DISPLAY);
            this.setupBrocDetail(recRequest, recRequestDetail, recBundleDetail, recItem, recItemReqInput);
        }
        
        recRequest.getField(Request.BUNDLE_ID).addListener(new SetBrocDetailHandler(this, gsBrocDetail, recRequest, recRequestDetail, recBundleDetail, recItem, recItemReqInput));
        recRequest.getField(Request.BUNDLE_QTY).addListener(new SetBrocDetailHandler(this, gsBrocDetail, recRequest, recRequestDetail, recBundleDetail, recItem, recItemReqInput));
        
        recRequest.getField(Request.ID).addListener(new FieldReSelectHandler(gsBrocDetail)); // Reselect on file change
        
        recRequest.addListener(new SetupBrocDetailHandler(recRequest, recRequestDetail, recBundleDetail, recItem, recItemReqInput));
        
        recRequest.setOpenMode(recRequest.getOpenMode() | DBConstants.OPEN_REFRESH_AND_LOCK_ON_CHANGE_STRATEGY);
        
        InitOnceFieldHandler listener = null;
        recRequest.getField(Request.BUNDLE_ID).addListener(listener = new InitOnceFieldHandler(null));
        listener.setFirstTime(false);   // Don't allow any more changes
        recRequest.getField(Request.BUNDLE_QTY).addListener(listener = new InitOnceFieldHandler(null));
        listener.setFirstTime(false);
    }
    /**
     * Setup the fields.
     */
    public void setupSFields()
    {
        Request pAmRequests = (Request)this.getRecord(Request.REQUEST_FILE);
        this.getRecord(Request.REQUEST_FILE).getField(Request.PROFILE_CODE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        new SSelectBox(this.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), this, this.getRecord(Request.REQUEST_FILE).getField(Request.PROFILE_CODE), ScreenConstants.DONT_DISPLAY_DESC, this.getRecord(Profile.PROFILE_FILE));
        new SCannedBox(this.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), this, this.getRecord(Request.REQUEST_FILE).getField(Request.PROFILE_CODE), MenuConstants.FORM, ScreenConstants.DONT_DISPLAY_FIELD_DESC, this.getRecord(Profile.PROFILE_FILE));
        this.getRecord(Request.REQUEST_FILE).getField(Request.GENERIC_NAME).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Request.REQUEST_FILE).getField(Request.ADDRESS_LINE_1).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Request.REQUEST_FILE).getField(Request.ADDRESS_LINE_2).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Request.REQUEST_FILE).getField(Request.CITY_OR_TOWN).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Request.REQUEST_FILE).getField(Request.STATE_OR_REGION).setupDefaultView(this.getNextLocation(ScreenConstants.RIGHT_WITH_DESC, ScreenConstants.DONT_SET_ANCHOR), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Request.REQUEST_FILE).getField(Request.POSTAL_CODE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Request.REQUEST_FILE).getField(Request.COUNTRY).setupDefaultView(this.getNextLocation(ScreenConstants.RIGHT_WITH_DESC, ScreenConstants.DONT_SET_ANCHOR), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Request.REQUEST_FILE).getField(Request.ATTENTION).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        new SCannedBox(this.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), this, null, ScreenConstants.DEFAULT_DISPLAY, null, "Request Detail", MenuConstants.FORMDETAIL, "Request Detail", null);
        this.getRecord(Request.REQUEST_FILE).getField(Request.EMAIL).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Request.REQUEST_FILE).getField(Request.SEND_VIA_CODE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Request.REQUEST_FILE).getField(Request.BROCHURE_TEXT).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Request.REQUEST_FILE).getField(Request.BUNDLE_QTY).setupDefaultView(this.getNextLocation(ScreenConstants.TOP_NEXT, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Request.REQUEST_FILE).getField(Request.BUNDLE_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        new RequestInputDisplay(null, this.getNextLocation(ScreenConstants.BELOW_LAST_DESC, ScreenConstants.ANCHOR_DEFAULT), this, null, ScreenConstants.DEFAULT_DISPLAY, null);
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
        if ("Request Detail".equalsIgnoreCase(strCommand))
        {
            Record recProfile = this.getRecord(Profile.PROFILE_FILE);
            ScreenLocation itsLocation = null;
            int iDocMode = ScreenConstants.DETAIL_MODE;
            boolean bCloneThisQuery = false;
            boolean bReadCurrentRecord = true;
            boolean bUseBaseTable = true;
            boolean bLinkGridToQuery = true;
            BasePanel parentScreen = Screen.makeWindow(this.getTask().getApplication());
            recProfile.makeScreen(itsLocation, parentScreen, iDocMode, bCloneThisQuery, bReadCurrentRecord, bUseBaseTable, bLinkGridToQuery, null);
            return true;
        }
        return super.doCommand(strCommand, sourceSField, iCommandOptions);
    }
    /**
     * SetupBrocDetail Method.
     */
    public void setupBrocDetail(Request recRequest, RequestDetail recRequestDetail, BundleDetail recBundleDetail, Brochure recItem, RequestInput recRequestInput)
    {
        recRequestInput.addBundle(recRequest.getField(Request.BUNDLE_ID), recBundleDetail, null, recRequest.getField(Request.BUNDLE_QTY));
    }

}

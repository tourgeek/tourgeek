/**
 * @(#)RequestScreen.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.request.screen;

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
import com.tourapp.tour.request.db.*;
import com.tourapp.tour.profile.db.*;
import com.tourapp.tour.base.db.shared.*;

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
        Request recRequest = (Request)this.getRecord(Request.kRequestFile);
        Profile recProfile = (Profile)this.getRecord(Profile.kProfileFile);
        RequestDetail recRequestDetail = (RequestDetail)this.getRecord(RequestDetail.kRequestDetailFile);
        BundleDetail recBundleDetail = (BundleDetail)this.getRecord(BundleDetail.kBundleDetailFile);
        Brochure recItem = (Brochure)this.getRecord(Brochure.kBrochureFile);
        RequestControl recRequestControl = (RequestControl)this.getRecord(RequestControl.kRequestControlFile);
        GridScreen gsBrocDetail = (GridScreen)this.getSField(this.getSFieldCount() - 1);
        RequestInput recItemReqInput = (RequestInput)gsBrocDetail.getRecord(RequestInput.kRequestInputFile);
        
        recRequest.getField(Request.kSendViaCode).addListener(new InitOnceFieldHandler(null));
        recRequest.getField(Request.kSendViaCode).addListener(new InitFieldHandler(recRequestControl.getField(RequestControl.kSendViaCode)));
        recRequest.getField(Request.kBundleID).addListener(new InitFieldHandler(recRequestControl.getField(RequestControl.kBundleID)));
        recRequest.getField(Request.kBundleQty).addListener(new InitFieldHandler(recRequestControl.getField(RequestControl.kBrochureQty)));
        // Here's the code to move the agency info on select
        ReadSecondaryHandler pSecondaryBehavior = new ReadSecondaryHandler(recProfile, Profile.kProfileCodeKey);
        pSecondaryBehavior.setRespondsToMode(DBConstants.READ_MOVE, false);
        recRequest.getField(Request.kProfileCode).addListener(pSecondaryBehavior);
        
        pSecondaryBehavior.addFieldSeqPair(Request.kProfileID);
        pSecondaryBehavior.addFieldSeqPair(Request.kProfileCode, Profile.kProfileCode, DBConstants.MOVE_TO_DEPENDENT, DBConstants.DONT_MOVE_DEPENDENT_BACK);
        pSecondaryBehavior.addFieldSeqPair(Request.kGenericName, Profile.kGenericName, DBConstants.MOVE_TO_DEPENDENT, DBConstants.DONT_MOVE_DEPENDENT_BACK);
        pSecondaryBehavior.addFieldSeqPair(Request.kAddressLine1, Profile.kAddressLine1, DBConstants.MOVE_TO_DEPENDENT, DBConstants.DONT_MOVE_DEPENDENT_BACK);
        pSecondaryBehavior.addFieldSeqPair(Request.kAddressLine2, Profile.kAddressLine2, DBConstants.MOVE_TO_DEPENDENT, DBConstants.DONT_MOVE_DEPENDENT_BACK);
        pSecondaryBehavior.addFieldSeqPair(Request.kCityOrTown, Profile.kCityOrTown, DBConstants.MOVE_TO_DEPENDENT, DBConstants.DONT_MOVE_DEPENDENT_BACK);
        pSecondaryBehavior.addFieldSeqPair(Request.kStateOrRegion, Profile.kStateOrRegion, DBConstants.MOVE_TO_DEPENDENT, DBConstants.DONT_MOVE_DEPENDENT_BACK);
        pSecondaryBehavior.addFieldSeqPair(Request.kPostalCode, Profile.kPostalCode, DBConstants.MOVE_TO_DEPENDENT, DBConstants.DONT_MOVE_DEPENDENT_BACK);
        pSecondaryBehavior.addFieldSeqPair(Request.kCountry, Profile.kCountry, DBConstants.MOVE_TO_DEPENDENT, DBConstants.DONT_MOVE_DEPENDENT_BACK);
        pSecondaryBehavior.addFieldSeqPair(Request.kAttention, Profile.kNameOrdered, DBConstants.MOVE_TO_DEPENDENT, DBConstants.DONT_MOVE_DEPENDENT_BACK);
        pSecondaryBehavior.addFieldSeqPair(Request.kEmail, Profile.kEmail, DBConstants.MOVE_TO_DEPENDENT, DBConstants.DONT_MOVE_DEPENDENT_BACK);
        
        recRequest.getField(Request.kProfileCode).addListener(new ChangeFocusOnChangeHandler(recRequest.getField(Request.kAttention)));
        
        recBundleDetail.setKeyArea(BundleDetail.kBundleIDKey);
        recBundleDetail.addListener(new SubFileFilter(recRequest.getField(Request.kBundleID), BundleDetail.kBundleID, null, -1, null, -1));
        
        recRequestDetail.addListener(new SubFileFilter(recRequest));
        // Set up the initial detail
        
        recItemReqInput.getField(RequestInput.kBrochureQty).addListener(new InitFieldHandler(recRequest.getField(Request.kBundleQty)));
        // These will guarantee that update calls the SetupBrocDetail listener
        recItemReqInput.getField(RequestInput.kBrochureQty).addListener(new SetDirtyOnChangeHandler(recRequest.getField(Request.kBundleID), false, true));
        recItemReqInput.getField(RequestInput.kBrochureID).addListener(new SetDirtyOnChangeHandler(recRequest.getField(Request.kBundleID), false, true));
        
        if ((recRequest.getEditMode() == Constants.EDIT_NONE) || (recRequest.getEditMode() == Constants.EDIT_ADD))
        {
            recRequest.getField(Request.kBundleID).initField(DBConstants.DISPLAY);
            recRequest.getField(Request.kBundleQty).initField(DBConstants.DISPLAY);
            this.setupBrocDetail(recRequest, recRequestDetail, recBundleDetail, recItem, recItemReqInput);
        }
        
        recRequest.getField(Request.kBundleID).addListener(new SetBrocDetailHandler(this, gsBrocDetail, recRequest, recRequestDetail, recBundleDetail, recItem, recItemReqInput));
        recRequest.getField(Request.kBundleQty).addListener(new SetBrocDetailHandler(this, gsBrocDetail, recRequest, recRequestDetail, recBundleDetail, recItem, recItemReqInput));
        
        recRequest.getField(Request.kID).addListener(new FieldReSelectHandler(gsBrocDetail)); // Reselect on file change
        
        recRequest.addListener(new SetupBrocDetailHandler(recRequest, recRequestDetail, recBundleDetail, recItem, recItemReqInput));
        
        recRequest.setOpenMode(recRequest.getOpenMode() | DBConstants.OPEN_REFRESH_AND_LOCK_ON_CHANGE_STRATEGY);
        
        InitOnceFieldHandler listener = null;
        recRequest.getField(Request.kBundleID).addListener(listener = new InitOnceFieldHandler(null));
        listener.setFirstTime(false);   // Don't allow any more changes
        recRequest.getField(Request.kBundleQty).addListener(listener = new InitOnceFieldHandler(null));
        listener.setFirstTime(false);
    }
    /**
     * Setup the fields.
     */
    public void setupSFields()
    {
        Request pAmRequests = (Request)this.getRecord(Request.kRequestFile);
        this.getRecord(Request.kRequestFile).getField(Request.kProfileCode).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        new SSelectBox(this.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), this, this.getRecord(Request.kRequestFile).getField(Request.kProfileCode), ScreenConstants.DONT_DISPLAY_DESC, this.getRecord(Profile.kProfileFile));
        new SCannedBox(this.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), this, this.getRecord(Request.kRequestFile).getField(Request.kProfileCode), MenuConstants.FORM, ScreenConstants.DONT_DISPLAY_FIELD_DESC, this.getRecord(Profile.kProfileFile));
        this.getRecord(Request.kRequestFile).getField(Request.kGenericName).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Request.kRequestFile).getField(Request.kAddressLine1).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Request.kRequestFile).getField(Request.kAddressLine2).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Request.kRequestFile).getField(Request.kCityOrTown).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Request.kRequestFile).getField(Request.kStateOrRegion).setupDefaultView(this.getNextLocation(ScreenConstants.RIGHT_WITH_DESC, ScreenConstants.DONT_SET_ANCHOR), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Request.kRequestFile).getField(Request.kPostalCode).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Request.kRequestFile).getField(Request.kCountry).setupDefaultView(this.getNextLocation(ScreenConstants.RIGHT_WITH_DESC, ScreenConstants.DONT_SET_ANCHOR), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Request.kRequestFile).getField(Request.kAttention).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        new SCannedBox(this.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), this, null, ScreenConstants.DEFAULT_DISPLAY, null, "Request Detail", MenuConstants.FORMDETAIL, "Request Detail", null);
        this.getRecord(Request.kRequestFile).getField(Request.kEmail).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Request.kRequestFile).getField(Request.kSendViaCode).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Request.kRequestFile).getField(Request.kBrochureText).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Request.kRequestFile).getField(Request.kBundleQty).setupDefaultView(this.getNextLocation(ScreenConstants.TOP_NEXT, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Request.kRequestFile).getField(Request.kBundleID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
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
            Record recProfile = this.getRecord(Profile.kProfileFile);
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
        recRequestInput.addBundle(recRequest.getField(Request.kBundleID), recBundleDetail, null, recRequest.getField(Request.kBundleQty));
    }

}

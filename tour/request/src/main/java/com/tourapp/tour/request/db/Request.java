/**
 * @(#)Request.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.request.db;

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
import com.tourapp.tour.request.screen.*;
import com.tourapp.tour.request.html.*;
import com.tourapp.tour.profile.db.*;
import com.tourapp.model.tour.request.db.*;

/**
 *  Request - Brochure Requests.
 */
public class Request extends VirtualRecord
     implements RequestModel
{
    private static final long serialVersionUID = 1L;

    //public static final int kID = kID;
    public static final int kProfileID = kVirtualRecordLastField + 1;
    public static final int kProfileCode = kProfileID + 1;
    public static final int kGenericName = kProfileCode + 1;
    public static final int kAddressLine1 = kGenericName + 1;
    public static final int kAddressLine2 = kAddressLine1 + 1;
    public static final int kCityOrTown = kAddressLine2 + 1;
    public static final int kStateOrRegion = kCityOrTown + 1;
    public static final int kPostalCode = kStateOrRegion + 1;
    public static final int kCountry = kPostalCode + 1;
    public static final int kAttention = kCountry + 1;
    public static final int kEmail = kAttention + 1;
    public static final int kSendViaCode = kEmail + 1;
    public static final int kBundleID = kSendViaCode + 1;
    public static final int kBundleQty = kBundleID + 1;
    public static final int kBrochureText = kBundleQty + 1;
    public static final int kPrintNow = kBrochureText + 1;
    public static final int kHistReprint = kPrintNow + 1;
    public static final int kRequestLastField = kHistReprint;
    public static final int kRequestFields = kHistReprint - DBConstants.MAIN_FIELD + 1;

    public static final int kIDKey = DBConstants.MAIN_KEY_FIELD;
    public static final int kPostalCodeKey = kIDKey + 1;
    public static final int kProfileCodeKey = kPostalCodeKey + 1;
    public static final int kRequestLastKey = kProfileCodeKey;
    public static final int kRequestKeys = kProfileCodeKey - DBConstants.MAIN_KEY_FIELD + 1;
    /**
     * Default constructor.
     */
    public Request()
    {
        super();
    }
    /**
     * Constructor.
     */
    public Request(RecordOwner screen)
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

    public static final String kRequestFile = "Request";
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(kRequestFile, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "Brochure request";
    }
    /**
     * Get the Database Name.
     */
    public String getDatabaseName()
    {
        return "request";
    }
    /**
     * Is this a local (vs remote) file?.
     */
    public int getDatabaseType()
    {
        return DBConstants.REMOTE | DBConstants.USER_DATA;
    }
    /**
     * MakeScreen Method.
     */
    public BaseScreen makeScreen(ScreenLocation itsLocation, BasePanel parentScreen, int iDocMode, Map<String,Object> properties)
    {
        BaseScreen screen = null;
        if ((iDocMode & ScreenConstants.MAINT_MODE) == ScreenConstants.MAINT_MODE)
        {
            if (ScreenConstants.HTML_SCREEN_TYPE.equalsIgnoreCase(parentScreen.getViewFactory().getViewSubpackage()))
                screen = new RequestHtmlScreen(this, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
            else
                screen = new RequestScreen(this, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        }
        else
            screen = new RequestGridScreen(this, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        return screen;
    }
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        //if (iFieldSeq == kID)
        //{
        //  field = new CounterField(this, "ID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //  field.setHidden(true);
        //}
        if (iFieldSeq == kProfileID)
            field = new ProfileField(this, "ProfileID", 8, null, null);
        if (iFieldSeq == kProfileCode)
            field = new StringField(this, "ProfileCode", 16, null, null);
        if (iFieldSeq == kGenericName)
            field = new StringField(this, "GenericName", 30, null, null);
        if (iFieldSeq == kAddressLine1)
            field = new StringField(this, "AddressLine1", 40, null, null);
        if (iFieldSeq == kAddressLine2)
            field = new StringField(this, "AddressLine2", 40, null, null);
        if (iFieldSeq == kCityOrTown)
            field = new StringField(this, "CityOrTown", 15, null, null);
        if (iFieldSeq == kStateOrRegion)
            field = new StringField(this, "StateOrRegion", 15, null, null);
        if (iFieldSeq == kPostalCode)
            field = new StringField(this, "PostalCode", 10, null, null);
        if (iFieldSeq == kCountry)
            field = new StringField(this, "Country", 15, null, null);
        if (iFieldSeq == kAttention)
            field = new StringField(this, "Attention", 24, null, null);
        if (iFieldSeq == kEmail)
            field = new EMailField(this, "Email", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kSendViaCode)
            field = new SendViaField(this, "SendViaCode", 4, null, null);
        if (iFieldSeq == kBundleID)
            field = new BundleFilter(this, "BundleID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kBundleQty)
            field = new ShortField(this, "BundleQty", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kBrochureText)
            field = new StringField(this, "BrochureText", 255, null, null);
        if (iFieldSeq == kPrintNow)
            field = new BooleanField(this, "PrintNow", Constants.DEFAULT_FIELD_LENGTH, null, new Boolean(false));
        if (iFieldSeq == kHistReprint)
            field = new BooleanField(this, "HistReprint", Constants.DEFAULT_FIELD_LENGTH, null, new Boolean(false));
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kRequestLastField)
                field = new EmptyField(this);
        }
        return field;
    }
    /**
     * Add this key area description to the Record.
     */
    public KeyArea setupKey(int iKeyArea)
    {
        KeyArea keyArea = null;
        if (iKeyArea == kIDKey)
        {
            keyArea = this.makeIndex(DBConstants.UNIQUE, "PrimaryKey");
            keyArea.addKeyField(kID, DBConstants.ASCENDING);
        }
        if (iKeyArea == kPostalCodeKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "PostalCode");
            keyArea.addKeyField(kPostalCode, DBConstants.ASCENDING);
        }
        if (iKeyArea == kProfileCodeKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "ProfileCode");
            keyArea.addKeyField(kProfileCode, DBConstants.ASCENDING);
        }
        if (keyArea == null) if (iKeyArea < kRequestLastKey)
        {
            keyArea = super.setupKey(iKeyArea);     
            if (keyArea == null) if (iKeyArea < kRequestLastKey)
                keyArea = new EmptyKey(this);
        }
        return keyArea;
    }

}

/**
  * @(#)Request.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
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
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import com.tourapp.tour.profile.db.*;
import com.tourapp.model.tour.request.db.*;

/**
 *  Request - Brochure Requests.
 */
public class Request extends VirtualRecord
     implements RequestModel
{
    private static final long serialVersionUID = 1L;

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
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(REQUEST_FILE, bAddQuotes) : super.getTableNames(bAddQuotes);
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
    public ScreenParent makeScreen(ScreenLoc itsLocation, ComponentParent parentScreen, int iDocMode, Map<String,Object> properties)
    {
        ScreenParent screen = null;
        if ((iDocMode & ScreenConstants.MAINT_MODE) == ScreenConstants.MAINT_MODE)
        {
            if (ScreenConstants.HTML_SCREEN_TYPE.equalsIgnoreCase(((ComponentParent)parentScreen).getViewType()))
                screen = Record.makeNewScreen(REQUEST_HTML_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
            else
                screen = Record.makeNewScreen(REQUEST_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        }
        else
            screen = Record.makeNewScreen(REQUEST_GRID_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        return screen;
    }
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        //if (iFieldSeq == 0)
        //{
        //  field = new CounterField(this, ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //  field.setHidden(true);
        //}
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
            field = new ProfileField(this, PROFILE_ID, 8, null, null);
        if (iFieldSeq == 4)
            field = new StringField(this, PROFILE_CODE, 16, null, null);
        if (iFieldSeq == 5)
            field = new StringField(this, GENERIC_NAME, 30, null, null);
        if (iFieldSeq == 6)
            field = new StringField(this, ADDRESS_LINE_1, 40, null, null);
        if (iFieldSeq == 7)
            field = new StringField(this, ADDRESS_LINE_2, 40, null, null);
        if (iFieldSeq == 8)
            field = new StringField(this, CITY_OR_TOWN, 15, null, null);
        if (iFieldSeq == 9)
            field = new StringField(this, STATE_OR_REGION, 15, null, null);
        if (iFieldSeq == 10)
            field = new StringField(this, POSTAL_CODE, 10, null, null);
        if (iFieldSeq == 11)
            field = new StringField(this, COUNTRY, 15, null, null);
        if (iFieldSeq == 12)
            field = new StringField(this, ATTENTION, 24, null, null);
        if (iFieldSeq == 13)
            field = new EMailField(this, EMAIL, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 14)
            field = new SendViaField(this, SEND_VIA_CODE, 4, null, null);
        if (iFieldSeq == 15)
            field = new BundleFilter(this, BUNDLE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 16)
            field = new ShortField(this, BUNDLE_QTY, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 17)
            field = new StringField(this, BROCHURE_TEXT, 255, null, null);
        if (iFieldSeq == 18)
            field = new BooleanField(this, PRINT_NOW, Constants.DEFAULT_FIELD_LENGTH, null, new Boolean(false));
        if (iFieldSeq == 19)
            field = new BooleanField(this, HIST_REPRINT, Constants.DEFAULT_FIELD_LENGTH, null, new Boolean(false));
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
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, POSTAL_CODE_KEY);
            keyArea.addKeyField(POSTAL_CODE, DBConstants.ASCENDING);
        }
        if (iKeyArea == 2)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, PROFILE_CODE_KEY);
            keyArea.addKeyField(PROFILE_CODE, DBConstants.ASCENDING);
        }
        if (keyArea == null)
            keyArea = super.setupKey(iKeyArea);     
        return keyArea;
    }

}

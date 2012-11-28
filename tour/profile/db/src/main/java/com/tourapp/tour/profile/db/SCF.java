/**
  * @(#)SCF.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.tour.profile.db;

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
import com.tourapp.model.tour.profile.db.*;

/**
 *  SCF - SCF Control.
 */
public class SCF extends VirtualRecord
     implements SCFModel
{
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor.
     */
    public SCF()
    {
        super();
    }
    /**
     * Constructor.
     */
    public SCF(RecordOwner screen)
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
        return (m_tableName == null) ? Record.formatTableNames(SCF_FILE, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "SCF";
    }
    /**
     * Get the Database Name.
     */
    public String getDatabaseName()
    {
        return "profile";
    }
    /**
     * Is this a local (vs remote) file?.
     */
    public int getDatabaseType()
    {
        return DBConstants.LOCAL | DBConstants.SHARED_DATA | DBConstants.LOCALIZABLE;
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
        {
            field = new StringField(this, SCF_FROM, 3, null, null);
            field.setNullable(false);
        }
        if (iFieldSeq == 4)
        {
            field = new StringField(this, SCF_TO, 3, null, null);
            field.setNullable(false);
        }
        if (iFieldSeq == 5)
            field = new StringField(this, DESCRIPTION, 25, null, null);
        if (iFieldSeq == 6)
        {
            field = new SalespersonField(this, SALESPERSON_ID, 3, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 7)
        {
            field = new SalesRegionField(this, SALES_REGION_ID, 4, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 8)
        {
            field = new StringField(this, UPS_ZONE, 1, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 9)
        {
            field = new StringField(this, ZIP_ZONE, 1, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
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
            keyArea = this.makeIndex(DBConstants.UNIQUE, SCF_TO_KEY);
            keyArea.addKeyField(SCF_TO, DBConstants.ASCENDING);
            keyArea.addKeyField(SCF_FROM, DBConstants.ASCENDING);
        }
        if (keyArea == null)
            keyArea = super.setupKey(iKeyArea);     
        return keyArea;
    }
    /**
     * AddMasterListeners Method.
     */
    public void addMasterListeners()
    {
        super.addMasterListeners();
        this.getField(SCF.SCF_FROM).addListener(new ScfToHandler(null));
        this.getField(SCF.SCF_FROM).addListener(new ScfFromHandler(null));
        this.getField(SCF.SCF_TO).addListener(new ScfToHandler(null));
    }
    /**
     * Get the default key index for grid screens and popup displays.
     * The default key area for grid screens is the first non-unique key that is a string.
     * Override this to supply a different key area.
     * @return The key area to use for screens and popups.
     */
    public String getDefaultScreenKeyArea()
    {
        return SCF.SCF_TO_KEY;
    }

}

/**
  * @(#)RequestInput.
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
import com.tourapp.tour.base.db.shared.*;
import com.tourapp.model.tour.request.db.*;

/**
 *  RequestInput - Input Fields.
 */
public class RequestInput extends VirtualRecord
     implements RequestInputModel
{
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor.
     */
    public RequestInput()
    {
        super();
    }
    /**
     * Constructor.
     */
    public RequestInput(RecordOwner screen)
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
        return (m_tableName == null) ? Record.formatTableNames(REQUEST_INPUT_FILE, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "Brochure request Input";
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
        return DBConstants.UNSHAREABLE_MEMORY;
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
            field = new ShortField(this, BROCHURE_QTY, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 4)
            field = new BrochureField(this, BROCHURE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 5)
            field = new StringField(this, BROCHURE_DESC, 30, null, null);
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
        if (keyArea == null)
            keyArea = super.setupKey(iKeyArea);     
        return keyArea;
    }
    /**
     * Add the behaviors needed to read the bundles in the addBundle method.
     */
    public void addBundleBehaviors(Record recBundle, Record recBundleDetail, Record recItem)
    {
        if (recItem != null)
            recBundleDetail.getField(BundleDetail.BROCHURE_ID).addListener(new ReadSecondaryHandler(recItem));
        recBundleDetail.addListener(new SubFileFilter(recBundle));
    }
    /**
     * Add the brochure detail that is contained in this bundle to this file.
     * If there is a field that contains the default quantity, pass it.
     * If the bundle record does not have a current record, then:
     *    - If the item record is not null, all the items are added
     *    - If the item record is null, no items are added.
     */
    public void addBundle(BaseField fldBundleID, Record recBundleDetail, Record recItem, BaseField fldDefaultQty)
    {
        // First, delete the current detail
        try   {
            this.close();
            while (this.next() != null)
            {
                this.remove();
            }
        // Next add back the new detail
            if ((fldBundleID != null) && (!fldBundleID.isNull()))
            {   // Valid bundle ID, fill the RequestItems with this bundle
                recBundleDetail.close();
                while (recBundleDetail.hasNext())
                {
                    recBundleDetail.next();
                    this.addNew();
                    if (fldDefaultQty != null)
                        this.getField(RequestInput.BROCHURE_QTY).moveFieldToThis(fldDefaultQty, DBConstants.DONT_DISPLAY, DBConstants.SCREEN_MOVE);
                    this.getField(RequestInput.BROCHURE_ID).moveFieldToThis(recBundleDetail.getField(BundleDetail.BROCHURE_ID), DBConstants.DONT_DISPLAY, DBConstants.SCREEN_MOVE);
                    if (recItem != null)
                        this.getField(RequestInput.BROCHURE_DESC).moveFieldToThis(recItem.getField(Brochure.DESCRIPTION), DBConstants.DONT_DISPLAY, DBConstants.SCREEN_MOVE);
                    if (!this.getField(RequestInput.BROCHURE_ID).isNull())
                        this.add();
                }
            }
            else if (recItem != null)
            {   // Not a valid bundle ID, set up all the items
                recItem.close();
                recItem.setKeyArea(recItem.getDefaultScreenKeyArea());
                BaseListener listener = recBundleDetail.getField(BundleDetail.BROCHURE_ID).getListener(ReadSecondaryHandler.class.getName());
                if (listener != null)
                    listener.setEnabledListener(false);   // Don't want this listener kicking in
                while (recItem.hasNext())
                {
                    recItem.next();
                    if (recItem.getField(Brochure.DISCONTINUED).getState() == true)
                        continue;
                    this.addNew();
                    if (fldDefaultQty != null)
                        this.getField(RequestInput.BROCHURE_QTY).moveFieldToThis(fldDefaultQty, DBConstants.DONT_DISPLAY, DBConstants.SCREEN_MOVE);
                    this.getField(RequestInput.BROCHURE_ID).moveFieldToThis(recItem.getField(Brochure.ID), DBConstants.DONT_DISPLAY, DBConstants.SCREEN_MOVE);
                    this.getField(RequestInput.BROCHURE_DESC).moveFieldToThis(recItem.getField(Brochure.DESCRIPTION), DBConstants.DONT_DISPLAY, DBConstants.SCREEN_MOVE);
                    if (!this.getField(RequestInput.BROCHURE_ID).isNull())
                        this.add();
                }
                if (listener != null)
                    listener.setEnabledListener(true);
            }
            this.close();
        } catch (DBException ex)    {
            ex.printStackTrace();
        }
    }

}

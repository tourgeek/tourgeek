/**
 * @(#)RequestInput.
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

    //public static final int kID = kID;
    public static final int kBrochureQty = kVirtualRecordLastField + 1;
    public static final int kBrochureID = kBrochureQty + 1;
    public static final int kBrochureDesc = kBrochureID + 1;
    public static final int kRequestInputLastField = kBrochureDesc;
    public static final int kRequestInputFields = kBrochureDesc - DBConstants.MAIN_FIELD + 1;

    public static final int kIDKey = DBConstants.MAIN_KEY_FIELD;
    public static final int kRequestInputLastKey = kIDKey;
    public static final int kRequestInputKeys = kIDKey - DBConstants.MAIN_KEY_FIELD + 1;
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

    public static final String kRequestInputFile = "RequestInput";
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(kRequestInputFile, bAddQuotes) : super.getTableNames(bAddQuotes);
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
        //if (iFieldSeq == kID)
        //{
        //  field = new CounterField(this, "ID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //  field.setHidden(true);
        //}
        if (iFieldSeq == kBrochureQty)
            field = new ShortField(this, "BrochureQty", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kBrochureID)
            field = new BrochureField(this, "BrochureID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kBrochureDesc)
            field = new StringField(this, "BrochureDesc", 30, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kRequestInputLastField)
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
        if (keyArea == null) if (iKeyArea < kRequestInputLastKey)
        {
            keyArea = super.setupKey(iKeyArea);     
            if (keyArea == null) if (iKeyArea < kRequestInputLastKey)
                keyArea = new EmptyKey(this);
        }
        return keyArea;
    }
    /**
     * Add the behaviors needed to read the bundles in the addBundle method.
     */
    public void addBundleBehaviors(Record recBundle, Record recBundleDetail, Record recItem)
    {
        if (recItem != null)
            recBundleDetail.getField(BundleDetail.kBrochureID).addListener(new ReadSecondaryHandler(recItem));
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
                        this.getField(RequestInput.kBrochureQty).moveFieldToThis(fldDefaultQty, DBConstants.DONT_DISPLAY, DBConstants.SCREEN_MOVE);
                    this.getField(RequestInput.kBrochureID).moveFieldToThis(recBundleDetail.getField(BundleDetail.kBrochureID), DBConstants.DONT_DISPLAY, DBConstants.SCREEN_MOVE);
                    if (recItem != null)
                        this.getField(RequestInput.kBrochureDesc).moveFieldToThis(recItem.getField(Brochure.kDescription), DBConstants.DONT_DISPLAY, DBConstants.SCREEN_MOVE);
                    if (!this.getField(RequestInput.kBrochureID).isNull())
                        this.add();
                }
            }
            else if (recItem != null)
            {   // Not a valid bundle ID, set up all the items
                recItem.close();
                recItem.setKeyArea(recItem.getDefaultScreenKeyArea());
                BaseListener listener = recBundleDetail.getField(BundleDetail.kBrochureID).getListener(ReadSecondaryHandler.class.getName());
                if (listener != null)
                    listener.setEnabledListener(false);   // Don't want this listener kicking in
                while (recItem.hasNext())
                {
                    recItem.next();
                    if (recItem.getField(Brochure.kDiscontinued).getState() == true)
                        continue;
                    this.addNew();
                    if (fldDefaultQty != null)
                        this.getField(RequestInput.kBrochureQty).moveFieldToThis(fldDefaultQty, DBConstants.DONT_DISPLAY, DBConstants.SCREEN_MOVE);
                    this.getField(RequestInput.kBrochureID).moveFieldToThis(recItem.getField(Brochure.kID), DBConstants.DONT_DISPLAY, DBConstants.SCREEN_MOVE);
                    this.getField(RequestInput.kBrochureDesc).moveFieldToThis(recItem.getField(Brochure.kDescription), DBConstants.DONT_DISPLAY, DBConstants.SCREEN_MOVE);
                    if (!this.getField(RequestInput.kBrochureID).isNull())
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

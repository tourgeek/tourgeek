/**
 * @(#)ModifyTourSubField.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.product.tour.detail.db;

import java.util.HashMap;
import java.util.Map;

import org.jbundle.base.db.Record;
import org.jbundle.base.db.event.MoveOnValidHandler;
import org.jbundle.base.field.BaseField;
import org.jbundle.base.field.ReferenceField;
import org.jbundle.base.field.StringField;
import org.jbundle.base.field.event.ReadSecondaryHandler;
import org.jbundle.base.model.RecordOwner;
import org.jbundle.base.model.ScreenConstants;
import org.jbundle.base.model.ScreenModel;
import org.jbundle.model.db.Convert;
import org.jbundle.model.screen.ComponentParent;
import org.jbundle.model.screen.ScreenComponent;
import org.jbundle.model.screen.ScreenLoc;
import org.jbundle.thin.base.util.ThinMenuConstants;

import com.tourapp.tour.product.base.db.Product;

/**
 *  ModifyTourSubField - Tour sub field to modify.
 */
public class ModifyTourSubField extends ReferenceField
{
    public static final String SELECT_QUEUE = "selectTourHeaderDetail";
    /**
     * Default constructor.
     */
    public ModifyTourSubField()
    {
        super();
    }
    /**
     * Constructor.
     * @param record The parent record.
     * @param strName The field name.
     * @param iDataLength The maximum string length (pass -1 for default).
     * @param strDesc The string description (usually pass null, to use the resource file desc).
     * @param strDefault The default value (if object, this value is the default value, if string, the string is the default).
     */
    public ModifyTourSubField(Record record, String strName, int iDataLength, String strDesc, Object strDefault)
    {
        this();
        this.init(record, strName, iDataLength, strDesc, strDefault);
    }
    /**
     * Initialize class fields.
     */
    public void init(Record record, String strName, int iDataLength, String strDesc, Object strDefault)
    {
        super.init(record, strName, iDataLength, strDesc, strDefault);
    }
    /**
     * Get (or make) the current record for this reference.
     */
    public Record makeReferenceRecord(RecordOwner recordOwner)
    {
        try {
            return (Record)this.getRecord().clone();
        } catch (CloneNotSupportedException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    /**
     * Set up the default screen control for this field.
     * @param itsLocation Location of this component on screen (ie., GridBagConstraint).
     * @param targetScreen Where to place this component (ie., Parent screen or GridBagLayout).
     * @param converter The converter to set the screenfield to.
     * @param iDisplayFieldDesc Display the label? (optional).
     * @param properties Extra properties
     * @return Return the component or ScreenField that is created for this field.
     */
    public ScreenComponent setupDefaultView(ScreenLoc itsLocation, ComponentParent targetScreen, Convert converter, int iDisplayFieldDesc, Map<String, Object> properties)
    {
        ScreenComponent sField = null;
        Record record = this.makeReferenceRecord();
        int fsField = record.getFieldSeq(TourHeaderLine.DESCRIPTION);
        if (record instanceof TourHeaderAirHeader)
            fsField = record.getFieldSeq(TourHeaderAirHeader.AIRLINE_DESC);
        if (record instanceof TourHeaderDetail)
        {
            Record recProduct = ((ReferenceField)record.getField(TourHeaderDetail.PRODUCT_ID)).getReferenceRecord();
            if (recProduct != null)
            {
                fsField = record.getFieldCount();   // This will be the sequence of the new field
                BaseField field = new StringField(record, "Description", 30, null, null);
                field.setVirtual(true);     // Just being careful
                record.getField(TourHeaderDetail.PRODUCT_ID).addListener(new ReadSecondaryHandler(recProduct));
                BaseField fldProductDesc = recProduct.getField(Product.DESCRIPTION);
                recProduct.addListener(new MoveOnValidHandler(field, fldProductDesc));
            }
            else
                fsField = record.getFieldSeq(TourHeaderDetail.DAY);    // Never (just in case)
        }
        sField = this.setupTableLookup(itsLocation, targetScreen, converter, iDisplayFieldDesc, record, -1, fsField, true, false);
        ScreenComponent sfTemp = BaseField.createScreenComponent(ScreenModel.CANNED_BOX, targetScreen.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), targetScreen, null, ScreenConstants.DEFAULT_DISPLAY, properties);
        for (int i = 0; ; i++)
        {
            ScreenComponent screenField = this.getComponent(i);
            if (screenField == null)
                break;  // Just being careful.
            if (screenField.getClass().isAssignableFrom(sfTemp.getClass()))
            {
                screenField.free();
                properties = new HashMap<String,Object>();
                properties.put(ScreenModel.RECORD, record);
                properties.put(ScreenModel.COMMAND, ThinMenuConstants.LOOKUP);
                properties.put(ScreenModel.IMAGE, ThinMenuConstants.LOOKUP);
                screenField = createScreenComponent(TourSub.MODIFY_TOUR_SUB_SCREEN_FIELD_CLASS, targetScreen.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), targetScreen, converter, ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
                break;
            }
        }
        sfTemp.free();
        return sField;
    }

}

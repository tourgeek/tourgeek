/**
  * @(#)TourLookupQuery.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.tour.booking.lookup;

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
import com.tourgeek.tour.booking.db.*;
import com.tourgeek.tour.product.tour.db.*;
import com.tourgeek.tour.product.base.db.*;
import com.tourgeek.tour.booking.inventory.db.*;
import com.tourgeek.model.tour.booking.lookup.*;

/**
 *  TourLookupQuery - This is the query used to lookup tours.
NOTE: This is a manual query, because Tour and TourHeader are in different databases..
 */
public class TourLookupQuery extends QueryRecord
     implements TourLookupQueryModel
{
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor.
     */
    public TourLookupQuery()
    {
        super();
    }
    /**
     * Constructor.
     */
    public TourLookupQuery(RecordOwner screen)
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

    public static final String TOUR_LOOKUP_QUERY_FILE = null; // Screen field
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
        if (field == null)
            field = super.setupField(iFieldSeq);
        return field;
    }
    /**
     * GetDatabaseType Method.
     */
    public int getDatabaseType()
    {
        return DBConstants.MANUAL_QUERY;    // Since Tour and TourHeader are in different databases
    }
    /**
     * Override this to Setup all the records for this query.
     * Only used for querys and abstract-record queries.
     * Actually adds records not tables, but the records aren't physically
     * added here, the record's tables are added to my table.
     * @param The recordOwner to pass to the records that are added.
     * @see addTable.
     */
    public void addTables(RecordOwner recordOwner)
    {
        this.addTable(new Tour(recordOwner));
        this.addTable(new TourHeader(recordOwner)); 
        this.addTable(new Inventory(recordOwner));
    }
    /**
     * SelectFields Method.
     */
    public void selectFields()
    {
        this.setSelected(false);
        super.selectFields();
        this.getField(Tour.TOUR_FILE, Tour.ID).setSelected(true);
        this.getField(Tour.TOUR_FILE, Tour.DESCRIPTION).setSelected(true);
        this.getField(Tour.TOUR_FILE, Tour.DEPARTURE_DATE).setSelected(true);
        this.getField(Tour.TOUR_FILE, Tour.TOUR_STATUS_ID).setSelected(true);
        this.getField(TourHeader.TOUR_HEADER_FILE, TourHeader.ID).setSelected(true);
        this.getField(TourHeader.TOUR_HEADER_FILE, TourHeader.DESCRIPTION).setSelected(true);
        this.getField(TourHeader.TOUR_HEADER_FILE, TourHeader.TOUR_TYPE).setSelected(true);
        this.getField(TourHeader.TOUR_HEADER_FILE, TourHeader.CODE).setSelected(true);
        this.getField(Inventory.INVENTORY_FILE, Inventory.BLOCKED).setSelected(true);
        this.getField(Inventory.INVENTORY_FILE, Inventory.USED).setSelected(true);
        this.getField(Inventory.INVENTORY_FILE, Inventory.AVAILABLE).setSelected(true);
        this.getField(Inventory.INVENTORY_FILE, Inventory.OVERSELL).setSelected(true);
    }
    /**
     * Setup this key area.
     */
    public KeyArea setupKey(int iKeyArea)
    {
        KeyArea keyArea = null;
        if (iKeyArea == DBConstants.MAIN_KEY_AREA)
        {
            keyArea = this.makeIndex(DBConstants.UNIQUE, "PrimaryKey");
            keyArea.addKeyField(this.getField(Tour.TOUR_FILE, Tour.ID), DBConstants.ASCENDING);
        }
        return keyArea;
    }
    /**
     * SetupRelationships Method.
     */
    public void setupRelationships()
    {
        this.addRelationship(DBConstants.LEFT_OUTER, this.getRecord(Tour.TOUR_FILE), this.getRecord(TourHeader.TOUR_HEADER_FILE), Tour.TOUR_HEADER_ID, TourHeader.ID);
        TableLink link = new TableLink(this, DBConstants.LEFT_OUTER, this.getRecord(Tour.TOUR_FILE), this.getRecord(Inventory.INVENTORY_FILE));
        this.getRecord(Inventory.INVENTORY_FILE).setKeyArea(Inventory.INV_DATE_KEY);
        Record recProductType = new ProductType(this.findRecordOwner());
        recProductType.setKeyArea(ProductType.CODE_KEY);
        recProductType.getField(ProductType.CODE).setString(ProductType.TOUR_CODE);
        try {
            recProductType.seek("=");
            link.addLink(recProductType.getCounterField().toString(), Inventory.PRODUCT_TYPE_ID);
        } catch (DBException ex)    {
            ex.printStackTrace();
        } finally {
            recProductType.free();
        }
        link.addLink(Tour.TOUR_HEADER_ID, Inventory.PRODUCT_ID);
        link.addLink("0", Inventory.RATE_ID);
        link.addLink("0", Inventory.CLASS_ID);
        link.addLink("0", Inventory.OTHER_ID);
        link.addLink(Tour.DEPARTURE_DATE, Inventory.INV_DATE);
        this.getRecord(Inventory.INVENTORY_FILE).getField(Inventory.BLOCKED).removeListener(this.getRecord(Inventory.INVENTORY_FILE).getField(Inventory.BLOCKED).getListener(InitOnceFieldHandler.class.getName()), true);
        this.getRecord(Inventory.INVENTORY_FILE).getField(Inventory.USED).removeListener(this.getRecord(Inventory.INVENTORY_FILE).getField(Inventory.USED).getListener(InitOnceFieldHandler.class.getName()), true);
        this.getRecord(Inventory.INVENTORY_FILE).getField(Inventory.AVAILABLE).removeListener(this.getRecord(Inventory.INVENTORY_FILE).getField(Inventory.AVAILABLE).getListener(InitOnceFieldHandler.class.getName()), true);
    }

}

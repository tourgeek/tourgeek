/**
 * @(#)TourLookupQuery.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.booking.lookup;

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
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.product.tour.db.*;
import com.tourapp.tour.product.base.db.*;
import com.tourapp.tour.booking.inventory.db.*;

/**
 *  TourLookupQuery - This is the query used to lookup tours.
NOTE: This is a manual query, because Tour and TourHeader are in different databases..
 */
public class TourLookupQuery extends QueryRecord
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

    public static final String kTourLookupQueryFile = null;   // Screen field
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
        this.getField(Tour.kTourFile, Tour.kID).setSelected(true);
        this.getField(Tour.kTourFile, Tour.kDescription).setSelected(true);
        this.getField(Tour.kTourFile, Tour.kDepartureDate).setSelected(true);
        this.getField(Tour.kTourFile, Tour.kTourStatusID).setSelected(true);
        this.getField(TourHeader.kTourHeaderFile, TourHeader.kID).setSelected(true);
        this.getField(TourHeader.kTourHeaderFile, TourHeader.kDescription).setSelected(true);
        this.getField(TourHeader.kTourHeaderFile, TourHeader.kTourType).setSelected(true);
        this.getField(TourHeader.kTourHeaderFile, TourHeader.kCode).setSelected(true);
        this.getField(Inventory.kInventoryFile, Inventory.kBlocked).setSelected(true);
        this.getField(Inventory.kInventoryFile, Inventory.kUsed).setSelected(true);
        this.getField(Inventory.kInventoryFile, Inventory.kAvailable).setSelected(true);
        this.getField(Inventory.kInventoryFile, Inventory.kOversell).setSelected(true);
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
            keyArea.addKeyField(this.getField(Tour.kTourFile, Tour.kID), DBConstants.ASCENDING);
        }
        return keyArea;
    }
    /**
     * SetupRelationships Method.
     */
    public void setupRelationships()
    {
        this.addRelationship(DBConstants.LEFT_OUTER, this.getRecord(Tour.kTourFile), this.getRecord(TourHeader.kTourHeaderFile), Tour.kTourHeaderID, TourHeader.kID);
        TableLink link = new TableLink(this, DBConstants.LEFT_OUTER, this.getRecord(Tour.kTourFile), this.getRecord(Inventory.kInventoryFile));
        this.getRecord(Inventory.kInventoryFile).setKeyArea(Inventory.kInvDateKey);
        Record recProductType = new ProductType(Utility.getRecordOwner(this));
        recProductType.setKeyArea(ProductType.kCodeKey);
        recProductType.getField(ProductType.kCode).setString(ProductType.TOUR_CODE);
        try {
            recProductType.seek("=");
            link.addLink(recProductType.getCounterField().toString(), Inventory.kProductTypeID);
        } catch (DBException ex)    {
            ex.printStackTrace();
        } finally {
            recProductType.free();
        }
        link.addLink(Tour.kTourHeaderID, Inventory.kProductID);
        link.addLink("0", Inventory.kRateID);
        link.addLink("0", Inventory.kClassID);
        link.addLink("0", Inventory.kOtherID);
        link.addLink(Tour.kDepartureDate, Inventory.kInvDate);
        this.getRecord(Inventory.kInventoryFile).getField(Inventory.kBlocked).removeListener(this.getRecord(Inventory.kInventoryFile).getField(Inventory.kBlocked).getListener(InitOnceFieldHandler.class.getName()), true);
        this.getRecord(Inventory.kInventoryFile).getField(Inventory.kUsed).removeListener(this.getRecord(Inventory.kInventoryFile).getField(Inventory.kUsed).getListener(InitOnceFieldHandler.class.getName()), true);
        this.getRecord(Inventory.kInventoryFile).getField(Inventory.kAvailable).removeListener(this.getRecord(Inventory.kInventoryFile).getField(Inventory.kAvailable).getListener(InitOnceFieldHandler.class.getName()), true);
    }

}

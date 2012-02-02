/**
 * @(#)LandList.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.product.land.db;

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
import com.tourapp.tour.product.base.db.*;
import com.tourapp.tour.booking.detail.db.*;
import com.tourapp.tour.product.land.screen.*;
import java.util.*;
import com.tourapp.tour.base.db.*;
import com.tourapp.thin.app.booking.entry.search.*;
import org.jbundle.thin.base.message.*;
import org.jbundle.base.message.trx.message.*;
import com.tourapp.tour.message.land.request.*;
import com.tourapp.tour.booking.inventory.db.*;
import com.tourapp.tour.message.base.response.*;
import com.tourapp.tour.product.tour.db.*;
import org.jbundle.main.msg.db.*;
import com.tourapp.tour.message.base.request.*;
import com.tourapp.tour.message.land.request.data.*;
import com.tourapp.tour.message.land.response.*;
import com.tourapp.tour.message.land.response.data.*;
import com.tourapp.tour.message.base.request.data.*;
import org.jbundle.main.db.base.*;
import com.tourapp.tour.acctpay.db.*;
import com.tourapp.model.tour.product.land.db.*;

/**
 *  LandList - Used for Product display.
 */
public class LandList extends QueryRecord
     implements LandListModel
{
    private static final long serialVersionUID = 1L;
    /**
     * Default constructor.
     */
    public LandList()
    {
        super();
    }
    /**
     * Constructor.
     */
    public LandList(RecordOwner screen)
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

    public static final String kLandListFile = "LandList";
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(kLandListFile, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "Land";
    }
    /**
     * Get the Database Name.
     */
    public String getDatabaseName()
    {
        return "product";
    }
    /**
     * Is this a local (vs remote) file?.
     */
    public int getDatabaseType()
    {
        return DBConstants.LOCAL;
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
        this.addTable(new Land(recordOwner));
        this.addTable(new City(recordOwner));
        this.addTable(new Vendor(recordOwner));
    }
    /**
     * SelectFields Method.
     */
    public void selectFields()
    {
        this.setSelected(false);
        super.selectFields();
        this.getField(Land.LAND_FILE, Land.ID).setSelected(true);
        this.getField(Land.LAND_FILE, Land.DESCRIPTION).setSelected(true);
        this.getField(Land.LAND_FILE, Land.CODE).setSelected(true);
        this.getField(Land.LAND_FILE, Land.VENDOR_ID).setSelected(true);
        this.getField(Land.LAND_FILE, Land.CITY_ID).setSelected(true);
        this.getField(City.CITY_FILE, City.DESCRIPTION).setSelected(true);
        this.getField(City.CITY_FILE, City.COUNTRY_ID).setSelected(true);
        this.getField(Vendor.VENDOR_FILE, Vendor.VENDOR_NAME).setSelected(true);
        this.getField(Land.LAND_FILE, Land.DESC_SORT).setSelected(true);
        this.getField(Vendor.VENDOR_FILE, Vendor.NAME_SORT).setSelected(true);
        this.getField(City.CITY_FILE, City.TICKET_CITY_DESC).setSelected(true);
    }
    /**
     * SetupRelationships Method.
     */
    public void setupRelationships()
    {
        this.addRelationship(DBConstants.LEFT_OUTER, this.getRecord(Land.LAND_FILE), this.getRecord(City.CITY_FILE), Land.CITY_ID, City.ID);
        this.addRelationship(DBConstants.LEFT_OUTER, this.getRecord(Land.LAND_FILE), this.getRecord(Vendor.VENDOR_FILE), Land.VENDOR_ID, Vendor.ID);
    }

}

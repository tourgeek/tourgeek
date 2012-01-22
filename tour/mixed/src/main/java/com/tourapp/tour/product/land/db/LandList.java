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
        this.getField(Land.kLandFile, Land.kID).setSelected(true);
        this.getField(Land.kLandFile, Land.kDescription).setSelected(true);
        this.getField(Land.kLandFile, Land.kCode).setSelected(true);
        this.getField(Land.kLandFile, Land.kVendorID).setSelected(true);
        this.getField(Land.kLandFile, Land.kCityID).setSelected(true);
        this.getField(City.kCityFile, City.kDescription).setSelected(true);
        this.getField(City.kCityFile, City.kCountryID).setSelected(true);
        this.getField(Vendor.kVendorFile, Vendor.kVendorName).setSelected(true);
        this.getField(Land.kLandFile, Land.kDescSort).setSelected(true);
        this.getField(Vendor.kVendorFile, Vendor.kNameSort).setSelected(true);
        this.getField(City.kCityFile, City.kTicketCityDesc).setSelected(true);
    }
    /**
     * SetupRelationships Method.
     */
    public void setupRelationships()
    {
        this.addRelationship(DBConstants.LEFT_OUTER, this.getRecord(Land.kLandFile), this.getRecord(City.kCityFile), Land.kCityID, City.kID);
        this.addRelationship(DBConstants.LEFT_OUTER, this.getRecord(Land.kLandFile), this.getRecord(Vendor.kVendorFile), Land.kVendorID, Vendor.kID);
    }

}

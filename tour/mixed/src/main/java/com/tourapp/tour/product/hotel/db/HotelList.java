/**
 * @(#)HotelList.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.product.hotel.db;

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
import com.tourapp.tour.product.base.db.*;
import com.tourapp.tour.product.hotel.screen.*;
import com.tourapp.tour.booking.detail.db.*;
import com.tourapp.tour.base.db.*;
import java.util.*;
import com.tourapp.tour.acctpay.db.*;
import java.text.*;
import com.tourapp.thin.app.booking.entry.search.*;
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.message.hotel.request.*;
import org.jbundle.thin.base.message.*;
import org.jbundle.base.message.trx.message.*;
import com.tourapp.tour.message.base.response.*;
import com.tourapp.tour.product.tour.db.*;
import org.jbundle.thin.base.screen.*;
import com.tourapp.tour.booking.inventory.db.*;
import org.jbundle.main.msg.db.*;
import com.tourapp.tour.message.base.request.*;
import com.tourapp.tour.message.hotel.request.data.*;
import com.tourapp.tour.message.hotel.response.data.*;
import com.tourapp.tour.message.hotel.response.*;
import com.tourapp.tour.message.base.request.data.*;
import com.tourapp.tour.message.base.response.data.*;
import org.jbundle.main.msg.db.base.*;

/**
 *  HotelList - For Product display.
 */
public class HotelList extends QueryRecord
{
    private static final long serialVersionUID = 1L;
    /**
     * Default constructor.
     */
    public HotelList()
    {
        super();
    }
    /**
     * Constructor.
     */
    public HotelList(RecordOwner screen)
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

    public static final String kHotelListFile = "HotelList";
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(kHotelListFile, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "Hotel";
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
        this.addTable(new Hotel(recordOwner));
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
        this.getField(Hotel.kHotelFile, Hotel.kID).setSelected(true);
        this.getField(Hotel.kHotelFile, Hotel.kDescription).setSelected(true);
        this.getField(Hotel.kHotelFile, Hotel.kCode).setSelected(true);
        this.getField(Hotel.kHotelFile, Hotel.kVendorID).setSelected(true);
        this.getField(Hotel.kHotelFile, Hotel.kCityID).setSelected(true);
        this.getField(City.kCityFile, City.kDescription).setSelected(true);
        this.getField(City.kCityFile, City.kCountryID).setSelected(true);
        this.getField(Vendor.kVendorFile, Vendor.kVendorName).setSelected(true);
        this.getField(Hotel.kHotelFile, Hotel.kDescSort).setSelected(true);
        this.getField(Vendor.kVendorFile, Vendor.kNameSort).setSelected(true);
        this.getField(City.kCityFile, City.kTicketCityDesc).setSelected(true);
    }
    /**
     * SetupRelationships Method.
     */
    public void setupRelationships()
    {
        this.addRelationship(DBConstants.LEFT_OUTER, this.getRecord(Hotel.kHotelFile), this.getRecord(City.kCityFile), Hotel.kCityID, City.kID);
        this.addRelationship(DBConstants.LEFT_OUTER, this.getRecord(Hotel.kHotelFile), this.getRecord(Vendor.kVendorFile), Hotel.kVendorID, Vendor.kID);
    }

}

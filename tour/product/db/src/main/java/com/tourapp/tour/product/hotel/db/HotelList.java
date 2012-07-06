/**
  * @(#)HotelList.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
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
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import com.tourapp.tour.product.base.db.*;
import com.tourapp.tour.base.db.*;
import java.util.*;
import com.tourapp.tour.acctpay.db.*;
import java.text.*;
import com.tourapp.thin.app.booking.entry.search.*;
import com.tourapp.tour.message.hotel.request.*;
import org.jbundle.thin.base.message.*;
import org.jbundle.base.message.core.trx.*;
import com.tourapp.tour.message.base.response.*;
import com.tourapp.tour.product.tour.db.*;
import org.jbundle.thin.base.screen.*;
import org.jbundle.main.msg.db.*;
import com.tourapp.tour.message.base.request.*;
import com.tourapp.tour.message.hotel.request.data.*;
import com.tourapp.tour.message.hotel.response.data.*;
import com.tourapp.tour.message.hotel.response.*;
import com.tourapp.tour.message.base.request.data.*;
import com.tourapp.tour.message.base.response.data.*;
import org.jbundle.main.db.base.*;
import org.jbundle.model.message.*;
import com.tourapp.model.tour.booking.detail.db.*;
import com.tourapp.model.tour.booking.inventory.db.*;
import com.tourapp.model.tour.booking.db.*;
import com.tourapp.model.tour.product.hotel.db.*;

/**
 *  HotelList - For Product display.
 */
public class HotelList extends QueryRecord
     implements HotelListModel
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
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(HOTEL_LIST_FILE, bAddQuotes) : super.getTableNames(bAddQuotes);
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
        this.getField(Hotel.HOTEL_FILE, Hotel.ID).setSelected(true);
        this.getField(Hotel.HOTEL_FILE, Hotel.DESCRIPTION).setSelected(true);
        this.getField(Hotel.HOTEL_FILE, Hotel.CODE).setSelected(true);
        this.getField(Hotel.HOTEL_FILE, Hotel.VENDOR_ID).setSelected(true);
        this.getField(Hotel.HOTEL_FILE, Hotel.CITY_ID).setSelected(true);
        this.getField(City.CITY_FILE, City.DESCRIPTION).setSelected(true);
        this.getField(City.CITY_FILE, City.COUNTRY_ID).setSelected(true);
        this.getField(Vendor.VENDOR_FILE, Vendor.VENDOR_NAME).setSelected(true);
        this.getField(Hotel.HOTEL_FILE, Hotel.DESC_SORT).setSelected(true);
        this.getField(Vendor.VENDOR_FILE, Vendor.NAME_SORT).setSelected(true);
        this.getField(City.CITY_FILE, City.TICKET_CITY_DESC).setSelected(true);
    }
    /**
     * SetupRelationships Method.
     */
    public void setupRelationships()
    {
        this.addRelationship(DBConstants.LEFT_OUTER, this.getRecord(Hotel.HOTEL_FILE), this.getRecord(City.CITY_FILE), Hotel.CITY_ID, City.ID);
        this.addRelationship(DBConstants.LEFT_OUTER, this.getRecord(Hotel.HOTEL_FILE), this.getRecord(Vendor.VENDOR_FILE), Hotel.VENDOR_ID, Vendor.ID);
    }

}

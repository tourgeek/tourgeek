/**
 * @(#)TourList.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.product.tour.db;

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
import org.jbundle.thin.base.message.*;
import org.jbundle.base.message.core.trx.*;
import com.tourapp.tour.profile.db.*;
import com.tourapp.tour.message.base.response.*;
import com.tourapp.tour.message.tour.response.*;
import org.jbundle.main.msg.db.*;
import com.tourapp.tour.message.base.request.*;
import com.tourapp.tour.message.tour.request.data.*;
import com.tourapp.tour.message.base.response.data.*;
import com.tourapp.tour.message.base.request.data.*;
import com.tourapp.tour.product.base.event.*;
import org.jbundle.main.db.base.*;
import com.tourapp.tour.product.tour.detail.db.*;
import com.tourapp.model.tour.booking.inventory.db.*;
import com.tourapp.model.tour.booking.db.*;
import com.tourapp.model.tour.booking.detail.db.*;
import com.tourapp.tour.base.db.*;
import com.tourapp.tour.acctpay.db.*;
import com.tourapp.model.tour.product.tour.db.*;

/**
 *  TourList - Tour List for Display.
 */
public class TourList extends QueryRecord
     implements TourListModel
{
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor.
     */
    public TourList()
    {
        super();
    }
    /**
     * Constructor.
     */
    public TourList(RecordOwner screen)
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
        return (m_tableName == null) ? Record.formatTableNames(TOUR_LIST_FILE, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "Tour";
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
        this.addTable(new TourHeader(recordOwner));
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
        this.getField(TourHeader.TOUR_HEADER_FILE, TourHeader.ID).setSelected(true);
        this.getField(TourHeader.TOUR_HEADER_FILE, TourHeader.DESCRIPTION).setSelected(true);
        this.getField(TourHeader.TOUR_HEADER_FILE, TourHeader.ID).setSelected(true);
        this.getField(TourHeader.TOUR_HEADER_FILE, TourHeader.VENDOR_ID).setSelected(true);
        this.getField(TourHeader.TOUR_HEADER_FILE, TourHeader.CITY_ID).setSelected(true);
        this.getField(City.CITY_FILE, City.DESCRIPTION).setSelected(true);
        this.getField(City.CITY_FILE, City.COUNTRY_ID).setSelected(true);
        this.getField(Vendor.VENDOR_FILE, Vendor.VENDOR_NAME).setSelected(true);
        this.getField(TourHeader.TOUR_HEADER_FILE, TourHeader.DESC_SORT).setSelected(true);
        this.getField(Vendor.VENDOR_FILE, Vendor.NAME_SORT).setSelected(true);
        this.getField(City.CITY_FILE, City.TICKET_CITY_DESC).setSelected(true);
    }
    /**
     * SetupRelationships Method.
     */
    public void setupRelationships()
    {
        this.addRelationship(DBConstants.LEFT_OUTER, this.getRecord(TourHeader.TOUR_HEADER_FILE), this.getRecord(City.CITY_FILE), TourHeader.CITY_ID, City.ID);
        this.addRelationship(DBConstants.LEFT_OUTER, this.getRecord(TourHeader.TOUR_HEADER_FILE), this.getRecord(Vendor.VENDOR_FILE), TourHeader.VENDOR_ID, Vendor.ID);
    }

}

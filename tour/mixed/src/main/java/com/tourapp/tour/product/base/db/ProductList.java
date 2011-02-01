/**
 *  @(#)ProductList.
 *  Copyright © 2010 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.product.base.db;

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
import com.tourapp.tour.product.hotel.db.*;
import com.tourapp.tour.product.land.db.*;
import com.tourapp.tour.product.tour.db.*;

/**
 *  ProductList - Product Merge Table.
 */
public class ProductList extends VirtualRecord
{
    private static final long serialVersionUID = 1L;
    /**
     * Default constructor.
     */
    public ProductList()
    {
        super();
    }
    /**
     * Constructor.
     */
    public ProductList(RecordOwner screen)
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

    public static final String kProductListFile = "ProductList";
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(kProductListFile, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "Product";
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
        return DBConstants.MEMORY | DBConstants.BASE_TABLE_CLASS | DBConstants.USER_DATA;
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
        HotelList pBkHotelList = new HotelList(recordOwner);
        this.addTable(pBkHotelList);
        LandList pBkLandList = new LandList(recordOwner);
        this.addTable(pBkLandList);
        TourList pBkTourList = new TourList(recordOwner);
        this.addTable(pBkTourList);
        // Add Trans, etc. here
        this.setKeyArea(Product.kDescSortKey);
    }
    /**
     * SelectFields Method.
     */
    public void selectFields()
    {
        super.selectFields();
    }

}

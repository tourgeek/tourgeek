/**
 * @(#)ProductDescConverter.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.booking.db;

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
import com.tourapp.tour.booking.detail.db.*;

/**
 *  ProductDescConverter - Convert product name to long.
 */
public class ProductDescConverter extends FieldConverter
{
    protected Record m_MergeRecord = null;
    /**
     * Default constructor.
     */
    public ProductDescConverter()
    {
        super();
    }
    /**
     * Return the Product description (from product.getProductDesc).
     */
    public ProductDescConverter(Record mergeRecord)
    {
        this();
        this.init(mergeRecord);
    }
    /**
     * Initialize class fields.
     */
    public void init(Record mergeRecord)
    {
        m_MergeRecord = null;
        m_MergeRecord = mergeRecord;
        super.init(null);
    }
    /**
     * GetMaxLength Method.
     */
    public int getMaxLength()
    {
        return 40;
    }
    /**
     * GetString Method.
     */
    public String getString()
    {
        String string = Constants.BLANK;
        BookingDetail productDetail = null;
        if (m_MergeRecord.getTable().getCurrentTable() != null)
            productDetail = (BookingDetail)m_MergeRecord.getTable().getCurrentTable().getRecord();
        if (productDetail != null)
            string = productDetail.getProductDesc();
        return string;
    }

}

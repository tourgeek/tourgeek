/**
  * @(#)BookingControl.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.tour.product.base.db;

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
import com.tourgeek.tour.profile.db.*;
import com.tourgeek.tour.product.tour.db.*;
import com.tourgeek.model.tour.product.base.db.*;

/**
 *  BookingControl - Control file.
 */
public class BookingControl extends ControlRecord
     implements BookingControlModel
{
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor.
     */
    public BookingControl()
    {
        super();
    }
    /**
     * Constructor.
     */
    public BookingControl(RecordOwner screen)
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
        return (m_tableName == null) ? Record.formatTableNames(BOOKING_CONTROL_FILE, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "Control";
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
        return DBConstants.LOCAL | DBConstants.USER_DATA;
    }
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        if (iFieldSeq == 0)
        {
            field = new CounterField(this, ID, 4, null, null);
            field.setHidden(true);
        }
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
        if (iFieldSeq == 3)
            field = new BooleanField(this, AUTO_BOOKING_CODE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 4)
            field = new PercentField(this, AGENCY_COMM, 5, null, new Float(0.10));
        if (iFieldSeq == 5)
            field = new ShortField(this, DEPOSIT_DAYS, 3, null, new Short((short)10));
        if (iFieldSeq == 6)
            field = new ShortField(this, ACCEPT_DAYS, Constants.DEFAULT_FIELD_LENGTH, null, new Short((short)10));
        if (iFieldSeq == 7)
            field = new ShortField(this, FINAL_DAYS, 3, null, new Short((short)30));
        if (iFieldSeq == 8)
            field = new ShortField(this, FINALIZATION_DAYS, 3, null, new Short((short)30));
        if (iFieldSeq == 9)
            field = new ShortField(this, FINAL_DOC_DAYS, 3, null, new Short((short)15));
        if (iFieldSeq == 10)
            field = new ShortField(this, TICKETING_DAYS, 3, null, new Short((short)5));
        if (iFieldSeq == 11)
            field = new ProfileTypeFilter(this, PROFILE_TYPE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 12)
            field = new BookingStatusField(this, BOOKING_STATUS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 13)
            field = new BookingStatusField(this, XLD_BOOKING_STATUS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 14)
            field = new PaxCategoryField(this, PAX_CATEGORY_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 15)
            field = new TourStatusField(this, TOUR_STATUS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 16)
            field = new TourStatusField(this, XLD_TOUR_STATUS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 17)
            field = new TourClassField(this, TOUR_CLASS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 18)
            field = new ProductCategoryField(this, PRODUCT_CATEGORY_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 19)
            field = new TourHeaderField(this, TOUR_HEADER_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 20)
            field = new TourHeaderField(this, THIN_TOUR_HEADER_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 21)
            field = new TourHeaderField(this, REMOTE_TOUR_HEADER_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 22)
            field = new IntegerField(this, REMOTE_WAIT_TIME, Constants.DEFAULT_FIELD_LENGTH, null, new Integer(10));
        if (iFieldSeq == 23)
            field = new ShortField(this, PAX, 2, null, null);
        if (iFieldSeq == 24)
            field = new ShortField(this, SINGLE_PAX, 2, null, null);
        if (iFieldSeq == 25)
            field = new ShortField(this, DOUBLE_PAX, 2, null, null);
        if (iFieldSeq == 26)
            field = new ShortField(this, NIGHTS, 2, null, new Short((short)1));
        if (iFieldSeq == 27)
            field = new PercentField(this, MARKUP, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 28)
            field = new PricingTypeField(this, TOUR_PRICING_TYPE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 29)
            field = new PricingTypeField(this, NON_TOUR_PRICING_TYPE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 30)
            field = new TourTypeField(this, SERIES_TOUR_TYPE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 31)
            field = new TourTypeField(this, TOUR_HEADER_TOUR_TYPE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 32)
            field = new TourTypeField(this, MODULE_TOUR_TYPE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 33)
            field = new TourTypeField(this, THIN_TOUR_TYPE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
            field = super.setupField(iFieldSeq);
        return field;
    }
    /**
     * Add this key area description to the Record.
     */
    public KeyArea setupKey(int iKeyArea)
    {
        KeyArea keyArea = null;
        if (iKeyArea == 0)
        {
            keyArea = this.makeIndex(DBConstants.UNIQUE, ID_KEY);
            keyArea.addKeyField(ID, DBConstants.ASCENDING);
        }
        if (keyArea == null)
            keyArea = super.setupKey(iKeyArea);     
        return keyArea;
    }

}

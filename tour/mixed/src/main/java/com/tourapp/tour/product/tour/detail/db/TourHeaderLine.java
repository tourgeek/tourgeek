/**
 * @(#)TourHeaderLine.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.product.tour.detail.db;

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
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import com.tourapp.tour.product.tour.detail.screen.*;
import com.tourapp.tour.product.tour.db.*;
import com.tourapp.tour.product.base.db.*;
import com.tourapp.tour.base.db.*;
import com.tourapp.model.tour.product.tour.detail.db.*;

/**
 *  TourHeaderLine - Tour Pricing.
 */
public class TourHeaderLine extends TourSub
     implements TourHeaderLineModel
{
    private static final long serialVersionUID = 1L;

    //public static final int kID = kID;
    //public static final int kTourHeaderOptionID = kTourHeaderOptionID;
    //public static final int kModifyCode = kModifyCode;
    //public static final int kModifyID = kModifyID;
    public static final int kSequence = kTourSubLastField + 1;
    public static final int kPaxCategoryID = kSequence + 1;
    public static final int kDescription = kPaxCategoryID + 1;
    public static final int kPrice = kDescription + 1;
    public static final int kCommissionable = kPrice + 1;
    public static final int kCommissionRate = kCommissionable + 1;
    public static final int kPayAt = kCommissionRate + 1;
    public static final int kCost = kPayAt + 1;
    public static final int kProductTermsID = kCost + 1;
    public static final int kTourHeaderLineLastField = kProductTermsID;
    public static final int kTourHeaderLineFields = kProductTermsID - DBConstants.MAIN_FIELD + 1;

    public static final int kIDKey = DBConstants.MAIN_KEY_FIELD;
    public static final int kTourHeaderOptionIDKey = kIDKey + 1;
    public static final int kTourHeaderLineLastKey = kTourHeaderOptionIDKey;
    public static final int kTourHeaderLineKeys = kTourHeaderOptionIDKey - DBConstants.MAIN_KEY_FIELD + 1;
    /**
     * Default constructor.
     */
    public TourHeaderLine()
    {
        super();
    }
    /**
     * Constructor.
     */
    public TourHeaderLine(RecordOwner screen)
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

    public static final String kTourHeaderLineFile = "TourHeaderLine";
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(kTourHeaderLineFile, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "Tour pricing";
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
     * Make a default screen.
     */
    public ScreenParent makeScreen(ScreenLoc itsLocation, ComponentParent parentScreen, int iDocMode, Map<String,Object> properties)
    {
        ScreenParent screen = null;
        if ((iDocMode & ScreenConstants.MAINT_MODE) == ScreenConstants.MAINT_MODE)
            screen = Record.makeNewScreen(TOUR_HEADER_LINE_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        if ((iDocMode & ScreenConstants.DISPLAY_MODE) == ScreenConstants.DISPLAY_MODE)
            screen = Record.makeNewScreen(TOUR_HEADER_LINE_GRID_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        else
            screen = super.makeScreen(itsLocation, parentScreen, iDocMode, properties);
        return screen;
    }
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        //if (iFieldSeq == kID)
        //{
        //  field = new CounterField(this, "ID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //  field.setHidden(true);
        //}
        //if (iFieldSeq == kTourHeaderOptionID)
        //  field = new TourHeaderOptionField(this, "TourHeaderOptionID", 8, null, null);
        if (iFieldSeq == kSequence)
        {
            field = new ShortField(this, "Sequence", 4, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kPaxCategoryID)
            field = new PaxBaseCategoryField(this, "PaxCategoryID", 1, null, new Integer(6) /* PaxCategory.ALL_ID */);
        if (iFieldSeq == kDescription)
            field = new StringField(this, "Description", 45, null, null);
        if (iFieldSeq == kPrice)
            field = new FullCurrencyField(this, "Price", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kCommissionable)
            field = new BooleanField(this, "Commissionable", 1, null, new Boolean(true));
        if (iFieldSeq == kCommissionRate)
        {
            field = new PercentField(this, "CommissionRate", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kPayAt)
            field = new PayAtField(this, "PayAt", 1, null, "PayAtField.FINAL_PAY_DATE");
        if (iFieldSeq == kCost)
            field = new FullCurrencyField(this, "Cost", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kProductTermsID)
            field = new ProductTermsField(this, "ProductTermsID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kModifyCode)
        //  field = new ModifyCodeField(this, "ModifyCode", 1, null, null);
        //if (iFieldSeq == kModifyID)
        //  field = new ModifyTourSubField(this, "ModifyID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kTourHeaderLineLastField)
                field = new EmptyField(this);
        }
        return field;
    }
    /**
     * Add this key area description to the Record.
     */
    public KeyArea setupKey(int iKeyArea)
    {
        KeyArea keyArea = null;
        if (iKeyArea == kIDKey)
        {
            keyArea = this.makeIndex(DBConstants.UNIQUE, "PrimaryKey");
            keyArea.addKeyField(kID, DBConstants.ASCENDING);
        }
        if (iKeyArea == kTourHeaderOptionIDKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "TourHeaderOptionID");
            keyArea.addKeyField(kTourHeaderOptionID, DBConstants.ASCENDING);
            keyArea.addKeyField(kPaxCategoryID, DBConstants.ASCENDING);
            keyArea.addKeyField(kSequence, DBConstants.ASCENDING);
        }
        if (keyArea == null) if (iKeyArea < kTourHeaderLineLastKey)
        {
            keyArea = super.setupKey(iKeyArea);     
            if (keyArea == null) if (iKeyArea < kTourHeaderLineLastKey)
                keyArea = new EmptyKey(this);
        }
        return keyArea;
    }

}

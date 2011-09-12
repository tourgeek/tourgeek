/**
 * @(#)TransportationPricing.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.product.trans.db;

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
import com.tourapp.tour.product.trans.screen.*;
import com.tourapp.tour.base.db.*;

/**
 *  TransportationPricing - Transportation pricing.
 */
public class TransportationPricing extends ProductPricing
{
    private static final long serialVersionUID = 1L;

    //public static final int kID = kID;
    //public static final int kProductID = kProductID;
    //public static final int kStartDate = kStartDate;
    //public static final int kEndDate = kEndDate;
    //public static final int kProductTermsID = kProductTermsID;
    //public static final int kClassID = kClassID;
    //public static final int kRateID = kRateID;
    //public static final int kCost = kCost;
    public static final int kFromPax = kProductPricingLastField + 1;
    public static final int kToPax = kFromPax + 1;
    public static final int kTransportationPricingLastField = kToPax;
    public static final int kTransportationPricingFields = kToPax - DBConstants.MAIN_FIELD + 1;

    public static final int kIDKey = DBConstants.MAIN_KEY_FIELD;
    public static final int kProductIDKey = kIDKey + 1;
    public static final int kTransportationPricingLastKey = kProductIDKey;
    public static final int kTransportationPricingKeys = kProductIDKey - DBConstants.MAIN_KEY_FIELD + 1;
    /**
     * Default constructor.
     */
    public TransportationPricing()
    {
        super();
    }
    /**
     * Constructor.
     */
    public TransportationPricing(RecordOwner screen)
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

    public static final String kTransportationPricingFile = "TransportationPricing";
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(kTransportationPricingFile, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "Transportation pricing";
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
    public BaseScreen makeScreen(ScreenLocation itsLocation, BasePanel parentScreen, int iDocMode, Map<String,Object> properties)
    {
        BaseScreen screen = null;
        if ((iDocMode & ScreenConstants.MAINT_MODE) != 0)
            screen = new TransportationPricingScreen(this, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if ((iDocMode & ScreenConstants.DISPLAY_MODE) != 0)
            screen = new TransportationPricingGridScreen(this, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
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
        if (iFieldSeq == kProductID)
            field = new TransportationField(this, "ProductID", 10, null, null);
        //if (iFieldSeq == kStartDate)
        //{
        //  field = new DateField(this, "StartDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //  field.addListener(new InitOnceFieldHandler(null));
        //}
        //if (iFieldSeq == kEndDate)
        //{
        //  field = new DateField(this, "EndDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //  field.addListener(new InitOnceFieldHandler(null));
        //}
        if (iFieldSeq == kFromPax)
        {
            field = new ShortField(this, "FromPax", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kToPax)
        {
            field = new ShortField(this, "ToPax", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kProductTermsID)
        {
            field = new ProductTermsField(this, "ProductTermsID", 1, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kClassID)
        {
            field = new TransportationClassField(this, "ClassID", Constants.DEFAULT_FIELD_LENGTH, null, new Integer(0));
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kRateID)
        {
            field = new TransportationRateField(this, "RateID", Constants.DEFAULT_FIELD_LENGTH, null, new Integer(0));
            field.addListener(new InitOnceFieldHandler(null));
        }
        //if (iFieldSeq == kCost)
        //  field = new FullCurrencyField(this, "Cost", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kTransportationPricingLastField)
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
        if (iKeyArea == kProductIDKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "ProductID");
            keyArea.addKeyField(kProductID, DBConstants.ASCENDING);
            keyArea.addKeyField(kRateID, DBConstants.ASCENDING);
            keyArea.addKeyField(kClassID, DBConstants.ASCENDING);
            keyArea.addKeyField(kEndDate, DBConstants.ASCENDING);
            keyArea.addKeyField(kToPax, DBConstants.ASCENDING);
        }
        if (keyArea == null) if (iKeyArea < kTransportationPricingLastKey)
        {
            keyArea = super.setupKey(iKeyArea);     
            if (keyArea == null) if (iKeyArea < kTransportationPricingLastKey)
                keyArea = new EmptyKey(this);
        }
        return keyArea;
    }
    /**
     * GetTransportationCost Method.
     */
    public TransportationPricing getTransportationCost(Transportation recTransportation, Date dateTarget, short sTargetPax, int iClassID)
    {
        if (dateTarget == null)
            return null;
        dateTarget = Converter.convertTimeToDate(dateTarget);
        if ((this.getEditMode() == Constants.EDIT_IN_PROGRESS) || (this.getEditMode() == Constants.EDIT_CURRENT))
        {
            if (this.getField(ProductPricing.kProductID).equals(recTransportation.getField(Product.kID)))
            if (this.getField(ProductPricing.kClassID).getValue() == iClassID)
            if (this.getField(ProductPricing.kStartDate).getValue() <= dateTarget.getTime()) // Start <= thisDate
            if (this.getField(ProductPricing.kEndDate).getValue() >= dateTarget.getTime())   // End >= thisDate
            if (this.getField(TransportationPricing.kFromPax).getValue() <= sTargetPax)    // Start <= thisDate
            if (this.getField(TransportationPricing.kToPax).getValue() >= sTargetPax)  // End >= thisDate
                return this;        // Valid price
        }
        
        this.getField(ProductPricing.kProductID).moveFieldToThis(recTransportation.getField(Product.kID));
        this.getField(ProductPricing.kClassID).setValue(iClassID);
        ((DateTimeField)this.getField(ProductPricing.kEndDate)).setDate(dateTarget, DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
        this.getField(TransportationPricing.kToPax).setValue(sTargetPax);
        this.getField(ProductPricing.kID).setValue(0);   // Since it reads from current.
        
        this.setKeyArea(ProductPricing.kProductIDKey);
        FileListener behavior1 = new SubCurrentFilter(true, false);
        this.addListener(behavior1);
        
        try   {
            this.close();
            while (this.hasNext())
            {   // Loop until found or not
                this.next();
                if (!this.getField(ProductPricing.kProductID).equals(recTransportation.getField(Product.kID)))
                    break;
                if (this.getField(ProductPricing.kClassID).getValue() != iClassID)
                    break;
                if (this.getField(ProductPricing.kStartDate).getValue() > dateTarget.getTime())    // Start > thisDate
                    break;
                if (this.getField(ProductPricing.kEndDate).getValue() >= dateTarget.getTime())   // End >= thisDate
                {
                    if (this.getField(TransportationPricing.kFromPax).getValue() <= sTargetPax)    // Start > thisDate
                        if (this.getField(TransportationPricing.kToPax).getValue() >= sTargetPax)  // End >= thisDate
                            return this;        // Valid price
                }
            }
        } catch (DBException ex)   {
            ex.printStackTrace();
        } finally {
            this.removeListener(behavior1, true);
        }
        return null;    // Rate not found if loop finished
    }

}

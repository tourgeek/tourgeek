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
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import com.tourapp.tour.product.base.db.*;
import com.tourapp.tour.product.trans.screen.*;
import com.tourapp.model.tour.product.trans.db.*;

/**
 *  TransportationPricing - Transportation pricing.
 */
public class TransportationPricing extends ProductPricing
     implements TransportationPricingModel
{
    private static final long serialVersionUID = 1L;

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
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(TRANSPORTATION_PRICING_FILE, bAddQuotes) : super.getTableNames(bAddQuotes);
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
    public ScreenParent makeScreen(ScreenLoc itsLocation, ComponentParent parentScreen, int iDocMode, Map<String,Object> properties)
    {
        ScreenParent screen = null;
        if ((iDocMode & ScreenConstants.MAINT_MODE) == ScreenConstants.MAINT_MODE)
            screen = Record.makeNewScreen(TRANSPORTATION_PRICING_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        if ((iDocMode & ScreenConstants.DISPLAY_MODE) == ScreenConstants.DISPLAY_MODE)
            screen = Record.makeNewScreen(TRANSPORTATION_PRICING_GRID_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
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
        if (iFieldSeq == 3)
            field = new TransportationField(this, PRODUCT_ID, 10, null, null);
        //if (iFieldSeq == 4)
        //{
        //  field = new PaxBaseCategoryField(this, PAX_CATEGORY_ID, Constants.DEFAULT_FIELD_LENGTH, null, new Integer(PaxCategory.ALL_ID));
        //  field.addListener(new InitOnceFieldHandler(null));
        //}
        if (iFieldSeq == 5)
        {
            field = new TransportationRateField(this, RATE_ID, Constants.DEFAULT_FIELD_LENGTH, null, new Integer(0));
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 6)
        {
            field = new TransportationClassField(this, CLASS_ID, Constants.DEFAULT_FIELD_LENGTH, null, new Integer(0));
            field.addListener(new InitOnceFieldHandler(null));
        }
        //if (iFieldSeq == 7)
        //{
        //  field = new DateField(this, START_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //  field.addListener(new InitOnceFieldHandler(null));
        //}
        //if (iFieldSeq == 8)
        //{
        //  field = new DateField(this, END_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //  field.addListener(new InitOnceFieldHandler(null));
        //}
        //if (iFieldSeq == 9)
        //  field = new FullCurrencyField(this, COST, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 10)
        {
            field = new ProductTermsField(this, PRODUCT_TERMS_ID, 1, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        //if (iFieldSeq == 11)
        //  field = new CurrencyField(this, PRICE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 12)
        //{
        //  field = new BooleanField(this, COMMISSIONABLE, Constants.DEFAULT_FIELD_LENGTH, null, new Boolean(true));
        //  field.addListener(new InitOnceFieldHandler(null));
        //}
        //if (iFieldSeq == 13)
        //{
        //  field = new PercentField(this, COMMISSION_RATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //  field.addListener(new InitOnceFieldHandler(null));
        //}
        //if (iFieldSeq == 14)
        //  field = new PayAtField(this, PAY_AT, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 15)
        {
            field = new ShortField(this, FROM_PAX, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 16)
        {
            field = new ShortField(this, TO_PAX, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
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
            keyArea = this.makeIndex(DBConstants.UNIQUE, "ID");
            keyArea.addKeyField(ID, DBConstants.ASCENDING);
        }
        if (iKeyArea == 1)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "ProductID");
            keyArea.addKeyField(PRODUCT_ID, DBConstants.ASCENDING);
            keyArea.addKeyField(RATE_ID, DBConstants.ASCENDING);
            keyArea.addKeyField(CLASS_ID, DBConstants.ASCENDING);
            keyArea.addKeyField(END_DATE, DBConstants.ASCENDING);
            keyArea.addKeyField(TO_PAX, DBConstants.ASCENDING);
        }
        if (keyArea == null)
            keyArea = super.setupKey(iKeyArea);     
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
            if (this.getField(ProductPricing.PRODUCT_ID).equals(recTransportation.getField(Product.ID)))
            if (this.getField(ProductPricing.CLASS_ID).getValue() == iClassID)
            if (this.getField(ProductPricing.START_DATE).getValue() <= dateTarget.getTime()) // Start <= thisDate
            if (this.getField(ProductPricing.END_DATE).getValue() >= dateTarget.getTime())   // End >= thisDate
            if (this.getField(TransportationPricing.FROM_PAX).getValue() <= sTargetPax)    // Start <= thisDate
            if (this.getField(TransportationPricing.TO_PAX).getValue() >= sTargetPax)  // End >= thisDate
                return this;        // Valid price
        }
        
        this.getField(ProductPricing.PRODUCT_ID).moveFieldToThis(recTransportation.getField(Product.ID));
        this.getField(ProductPricing.CLASS_ID).setValue(iClassID);
        ((DateTimeField)this.getField(ProductPricing.END_DATE)).setDate(dateTarget, DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
        this.getField(TransportationPricing.TO_PAX).setValue(sTargetPax);
        this.getField(ProductPricing.ID).setValue(0);   // Since it reads from current.
        
        this.setKeyArea(ProductPricing.PRODUCT_ID_KEY);
        FileListener behavior1 = new SubCurrentFilter(true, false);
        this.addListener(behavior1);
        
        try   {
            this.close();
            while (this.hasNext())
            {   // Loop until found or not
                this.next();
                if (!this.getField(ProductPricing.PRODUCT_ID).equals(recTransportation.getField(Product.ID)))
                    break;
                if (this.getField(ProductPricing.CLASS_ID).getValue() != iClassID)
                    break;
                if (this.getField(ProductPricing.START_DATE).getValue() > dateTarget.getTime())    // Start > thisDate
                    break;
                if (this.getField(ProductPricing.END_DATE).getValue() >= dateTarget.getTime())   // End >= thisDate
                {
                    if (this.getField(TransportationPricing.FROM_PAX).getValue() <= sTargetPax)    // Start > thisDate
                        if (this.getField(TransportationPricing.TO_PAX).getValue() >= sTargetPax)  // End >= thisDate
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

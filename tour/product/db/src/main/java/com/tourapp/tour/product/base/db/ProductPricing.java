/**
  * @(#)ProductPricing.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.tour.product.base.db;

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
import com.tourapp.tour.base.db.*;
import com.tourapp.model.tour.product.base.db.*;

/**
 *  ProductPricing - Product Cost Detail.
 */
public class ProductPricing extends VirtualRecord
     implements ProductPricingModel
{
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor.
     */
    public ProductPricing()
    {
        super();
    }
    /**
     * Constructor.
     */
    public ProductPricing(RecordOwner screen)
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

    public static final String PRODUCT_PRICING_FILE = null;   // Screen field
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
        {
            field = new ReferenceField(this, PRODUCT_ID, 8, null, null);
            field.setNullable(false);
        }
        if (iFieldSeq == 4)
        {
            field = new PaxBaseCategoryField(this, PAX_CATEGORY_ID, Constants.DEFAULT_FIELD_LENGTH, null, new Integer(PaxCategory.ALL_ID));
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 5)
        {
            field = new ReferenceField(this, RATE_ID, Constants.DEFAULT_FIELD_LENGTH, null, new Integer(0));
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 6)
        {
            field = new ReferenceField(this, CLASS_ID, Constants.DEFAULT_FIELD_LENGTH, null, new Integer(0));
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 7)
        {
            field = new DateField(this, START_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 8)
        {
            field = new DateField(this, END_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 9)
            field = new FullCurrencyField(this, COST, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 10)
        {
            field = new ProductTermsField(this, PRODUCT_TERMS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 11)
            field = new CurrencyField(this, PRICE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 12)
        {
            field = new BooleanField(this, COMMISSIONABLE, Constants.DEFAULT_FIELD_LENGTH, null, new Boolean(true));
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 13)
        {
            field = new PercentField(this, COMMISSION_RATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 14)
            field = new PayAtField(this, PAY_AT, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
            field = super.setupField(iFieldSeq);
        return field;
    }
    /**
     * Retrieve this cost field and adjust it using this record's terms.
     */
    public double getCost(String iFieldSeq, ProductTerms recProductTerms)
    {
        if (iFieldSeq == null)
            iFieldSeq = ProductPricing.COST;
        double dCost = this.getField(iFieldSeq).getValue();
        if (this.getField(ProductPricing.PRODUCT_TERMS_ID).getLength() != 0)
        { // Get the markup/down
            ProductTerms recNewProductTerms = null;
            if (recProductTerms == null)
                recNewProductTerms = recProductTerms = new ProductTerms(this.findRecordOwner());
            dCost = recProductTerms.calcNetCost(dCost, this.getField(ProductPricing.PRODUCT_TERMS_ID));
            if (recNewProductTerms != null)
                recNewProductTerms.free();
        }
        return dCost;
    }
    /**
     * GetProductCost Method.
     */
    public ProductPricing getProductCost(Product recProduct, Date dateTarget, int iRateID, int iClassID)
    {
        if (dateTarget == null)
            return null;
        dateTarget = Converter.convertTimeToDate(dateTarget);
        if ((this.getEditMode() == Constants.EDIT_IN_PROGRESS) || (this.getEditMode() == Constants.EDIT_CURRENT))
        {
            if (this.getField(ProductPricing.PRODUCT_ID).equals(recProduct.getField(Product.ID)))
            if (this.getField(ProductPricing.RATE_ID).getValue() == iRateID)
            if (this.getField(ProductPricing.CLASS_ID).getValue() == iClassID)
            if (this.getField(ProductPricing.START_DATE).getValue() <= dateTarget.getTime()) // Start <= thisDate
            if (this.getField(ProductPricing.END_DATE).getValue() >= dateTarget.getTime())   // End >= thisDate
                return this;        // Valid price
        }
        
        this.getField(ProductPricing.PRODUCT_ID).moveFieldToThis(recProduct.getField(Product.ID));
        this.getField(ProductPricing.RATE_ID).setValue(iRateID);
        this.getField(ProductPricing.CLASS_ID).setValue(iClassID);
        ((DateTimeField)this.getField(ProductPricing.END_DATE)).setDate(dateTarget, DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
        this.getField(ProductPricing.ID).setValue(0);   // Since it reads from current.
        
        this.setKeyArea(ProductPricing.PRODUCT_ID_KEY);
        FileListener behavior = new SubCurrentFilter(true, false);
        this.addListener(behavior);
        
        try   {
            this.close();
            while (this.hasNext())
            {   // Loop until found or not
                this.next();
                if (!this.getField(ProductPricing.PRODUCT_ID).equals(recProduct.getField(Product.ID)))
                    break;
                if (this.getField(ProductPricing.RATE_ID).getValue() != iRateID)
                    break;
                if (this.getField(ProductPricing.CLASS_ID).getValue() != iClassID)
                    break;
                if (this.getField(ProductPricing.START_DATE).getValue() > dateTarget.getTime())    // Start > thisDate
                    break;
                if (this.getField(ProductPricing.END_DATE).getValue() >= dateTarget.getTime())   // End >= thisDate
                    return this;        // Valid price
            }
        } catch (DBException ex)   {
            ex.printStackTrace();
        } finally {
            this.removeListener(behavior, true);
        }
        return null;    // Rate not found if loop finished
    }

}

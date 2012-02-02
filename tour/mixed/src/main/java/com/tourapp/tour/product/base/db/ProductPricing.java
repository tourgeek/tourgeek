/**
 * @(#)ProductPricing.
 * Copyright © 2011 tourapp.com. All rights reserved.
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

    //public static final int kID = kID;
    public static final int kProductID = kVirtualRecordLastField + 1;
    public static final int kPaxCategoryID = kProductID + 1;
    public static final int kRateID = kPaxCategoryID + 1;
    public static final int kClassID = kRateID + 1;
    public static final int kStartDate = kClassID + 1;
    public static final int kEndDate = kStartDate + 1;
    public static final int kCost = kEndDate + 1;
    public static final int kProductTermsID = kCost + 1;
    public static final int kPrice = kProductTermsID + 1;
    public static final int kCommissionable = kPrice + 1;
    public static final int kCommissionRate = kCommissionable + 1;
    public static final int kPayAt = kCommissionRate + 1;
    public static final int kProductPricingLastField = kPayAt;
    public static final int kProductPricingFields = kPayAt - DBConstants.MAIN_FIELD + 1;

    public static final int kIDKey = DBConstants.MAIN_KEY_FIELD;
    public static final int kProductIDKey = kIDKey + 1;
    public static final int kProductPricingLastKey = kProductIDKey;
    public static final int kProductPricingKeys = kProductIDKey - DBConstants.MAIN_KEY_FIELD + 1;
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

    public static final String kProductPricingFile = null;  // Screen field
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
        {
            field = new ReferenceField(this, "ProductID", 8, null, null);
            field.setNullable(false);
        }
        if (iFieldSeq == kPaxCategoryID)
        {
            field = new PaxBaseCategoryField(this, "PaxCategoryID", Constants.DEFAULT_FIELD_LENGTH, null, new Integer(PaxCategory.ALL_ID));
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kRateID)
        {
            field = new ReferenceField(this, "RateID", Constants.DEFAULT_FIELD_LENGTH, null, new Integer(0));
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kClassID)
        {
            field = new ReferenceField(this, "ClassID", Constants.DEFAULT_FIELD_LENGTH, null, new Integer(0));
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kStartDate)
        {
            field = new DateField(this, "StartDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kEndDate)
        {
            field = new DateField(this, "EndDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kCost)
            field = new FullCurrencyField(this, "Cost", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kProductTermsID)
        {
            field = new ProductTermsField(this, "ProductTermsID", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kPrice)
            field = new CurrencyField(this, "Price", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kCommissionable)
        {
            field = new BooleanField(this, "Commissionable", Constants.DEFAULT_FIELD_LENGTH, null, new Boolean(true));
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kCommissionRate)
        {
            field = new PercentField(this, "CommissionRate", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kPayAt)
            field = new PayAtField(this, "PayAt", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kProductPricingLastField)
                field = new EmptyField(this);
        }
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

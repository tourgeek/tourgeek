/**
 * @(#)ProductTerms.
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
import com.tourapp.tour.product.base.screen.*;
import com.tourapp.model.tour.product.base.db.*;

/**
 *  ProductTerms - Terms.
 */
public class ProductTerms extends VirtualRecord
     implements ProductTermsModel
{
    private static final long serialVersionUID = 1L;

    //public static final int kID = kID;
    public static final int kDescription = kVirtualRecordLastField + 1;
    public static final int kTaxRate = kDescription + 1;
    public static final int kServiceChargeRate = kTaxRate + 1;
    public static final int kCommissionRate = kServiceChargeRate + 1;
    public static final int kProductTermsLastField = kCommissionRate;
    public static final int kProductTermsFields = kCommissionRate - DBConstants.MAIN_FIELD + 1;

    public static final int kIDKey = DBConstants.MAIN_KEY_FIELD;
    public static final int kDescriptionKey = kIDKey + 1;
    public static final int kProductTermsLastKey = kDescriptionKey;
    public static final int kProductTermsKeys = kDescriptionKey - DBConstants.MAIN_KEY_FIELD + 1;
    /**
     * Default constructor.
     */
    public ProductTerms()
    {
        super();
    }
    /**
     * Constructor.
     */
    public ProductTerms(RecordOwner screen)
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

    public static final String kProductTermsFile = "ProductTerms";
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(kProductTermsFile, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "Term";
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
        return DBConstants.TABLE | DBConstants.SHARED_DATA | DBConstants.LOCALIZABLE;
    }
    /**
     * Make a default screen.
     */
    public ScreenParent makeScreen(ScreenLoc itsLocation, ComponentParent parentScreen, int iDocMode, Map<String,Object> properties)
    {
        ScreenParent screen = null;
        if ((iDocMode & ScreenConstants.MAINT_MODE) == ScreenConstants.MAINT_MODE)
            screen = Record.makeNewScreen(PRODUCT_TERMS_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        if ((iDocMode & ScreenConstants.DISPLAY_MODE) == ScreenConstants.DISPLAY_MODE)
            screen = Record.makeNewScreen(PRODUCT_TERMS_GRID_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
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
        if (iFieldSeq == kID)
        {
            field = new CounterField(this, "ID", 4, null, null);
            field.setHidden(true);
        }
        if (iFieldSeq == kDescription)
            field = new StringField(this, "Description", 20, null, null);
        if (iFieldSeq == kTaxRate)
            field = new PercentField(this, "TaxRate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kServiceChargeRate)
            field = new PercentField(this, "ServiceChargeRate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kCommissionRate)
            field = new PercentField(this, "CommissionRate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kProductTermsLastField)
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
        if (iKeyArea == kDescriptionKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "Description");
            keyArea.addKeyField(kDescription, DBConstants.ASCENDING);
        }
        if (keyArea == null) if (iKeyArea < kProductTermsLastKey)
        {
            keyArea = super.setupKey(iKeyArea);     
            if (keyArea == null) if (iKeyArea < kProductTermsLastKey)
                keyArea = new EmptyKey(this);
        }
        return keyArea;
    }
    /**
     * Read this markup line and calc new cost.
     */
    public double calcNetCost(double dOrigCost, BaseField fldMarkupCode)
    {
        double dNewCost = dOrigCost;
        if (fldMarkupCode != null) if (this.getField(ProductTerms.kID).compareTo(fldMarkupCode) != 0)
        {
            this.getField(ProductTerms.kID).moveFieldToThis(fldMarkupCode, DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
            boolean bSuccess = false;
            try   {
                bSuccess = this.seek("=");
            } catch (DBException e)   {
                bSuccess = false;
            }
            if (!bSuccess)
                return dOrigCost;
        }
        double dTax = this.getField(ProductTerms.kTaxRate).getValue();
        double dSvcChg = this.getField(ProductTerms.kServiceChargeRate).getValue();
        double dComm = this.getField(ProductTerms.kCommissionRate).getValue();
        dNewCost = Math.floor(dOrigCost * (1.00 + dTax + dSvcChg - dComm) * 100.00 + 0.5) / 100.00;
        return dNewCost;
    }

}

/**
 * @(#)LandPricing.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.product.land.db;

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
import org.jbundle.base.screen.model.*;
import org.jbundle.base.screen.model.util.*;
import com.tourapp.tour.product.base.db.*;
import java.util.*;
import com.tourapp.tour.base.db.*;
import java.text.*;
import com.tourapp.tour.product.land.event.*;
import com.tourapp.tour.message.land.request.data.*;
import com.tourapp.model.tour.booking.detail.db.*;
import com.tourapp.model.tour.product.land.db.*;

/**
 *  LandPricing - Land pricing.
 */
public class LandPricing extends ProductPricing
     implements LandPricingModel
{
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor.
     */
    public LandPricing()
    {
        super();
    }
    /**
     * Constructor.
     */
    public LandPricing(RecordOwner screen)
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
        return (m_tableName == null) ? Record.formatTableNames(LAND_PRICING_FILE, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "Land pricing";
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
            screen = Record.makeNewScreen(LAND_PRICING_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        if ((iDocMode & ScreenConstants.DISPLAY_MODE) == ScreenConstants.DISPLAY_MODE)
            screen = Record.makeNewScreen(LAND_PRICING_GRID_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
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
            field = new ReferenceField(this, PRODUCT_ID, 8, null, null);
        //if (iFieldSeq == 4)
        //{
        //  field = new PaxBaseCategoryField(this, PAX_CATEGORY_ID, Constants.DEFAULT_FIELD_LENGTH, null, new Integer(PaxCategory.ALL_ID));
        //  field.addListener(new InitOnceFieldHandler(null));
        //}
        if (iFieldSeq == 5)
        {
            field = new LandRateField(this, RATE_ID, Constants.DEFAULT_FIELD_LENGTH, null, new Integer(0));
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 6)
        {
            field = new LandClassField(this, CLASS_ID, Constants.DEFAULT_FIELD_LENGTH, null, new Integer(0));
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 7)
        {
            field = new DateField(this, START_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        //if (iFieldSeq == 8)
        //{
        //  field = new DateField(this, END_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //  field.addListener(new InitOnceFieldHandler(null));
        //}
        if (iFieldSeq == 9)
            field = new FullCurrencyField(this, COST, Constants.DEFAULT_FIELD_LENGTH, null, null);
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
            field = new ShortField(this, FROM_PAX, 3, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 16)
        {
            field = new ShortField(this, TO_PAX, 3, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 17)
        {
            field = new LandVariesField(this, LAND_VARIES_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 18)
            field = new StringField(this, VARIES_DESC, 16, null, null);
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
            keyArea.addKeyField(CLASS_ID, DBConstants.ASCENDING);
            keyArea.addKeyField(END_DATE, DBConstants.ASCENDING);
            keyArea.addKeyField(TO_PAX, DBConstants.ASCENDING);
        }
        if (keyArea == null)
            keyArea = super.setupKey(iKeyArea);     
        return keyArea;
    }
    /**
     * AddMasterListeners Method.
     */
    public void addMasterListeners()
    {
        super.addMasterListeners();
        this.getField(LandPricing.LAND_VARIES_ID).addListener(new ManualVariesDefaultCost(null));
    }
    /**
     * GetQuantity Method.
     */
    public int getQuantity(Task task, short sTargetPax, LandMessageData mapProperties, Map<String, Object> mapSurvey)
    {
        int iQuantity = 0;
        int iPaxCategory = (int)this.getField(LandPricing.PAX_CATEGORY_ID).getValue();
        if (iPaxCategory == PaxCategory.ALL_ID)
            iQuantity = sTargetPax;
        else
        {
            try {
                iQuantity = ((Integer)Converter.convertObjectToDatatype(mapProperties.get(Product.ROOM_TYPE_PARAM + Integer.toString(iPaxCategory)), Integer.class, IntegerField.ZERO)).intValue();
            } catch (Exception ex) {
            }
        }
        LandVaries recVariesOn = (LandVaries)((ReferenceField)this.getField(LandPricing.LAND_VARIES_ID)).getReference();
        if (recVariesOn != null)
            if ((recVariesOn.getEditMode() == DBConstants.EDIT_CURRENT) || (recVariesOn.getEditMode() == DBConstants.EDIT_IN_PROGRESS))
        {
            String strVariesBy = recVariesOn.getField(LandVaries.VARIES_BY).toString();
            if ((strVariesBy == null) || (strVariesBy.length() == 0))
                strVariesBy = VariesByField.AUTO_PER_PERSON;
            else if ((VariesByField.AUTO_PER_PERSON.equals(strVariesBy)) || (VariesByField.MANUAL_PER_PERSON.equals(strVariesBy)))
            {
                    // Cost is already per person
            }
            else if ((VariesByField.AUTO_FIXED.equals(strVariesBy)) || (VariesByField.MANUAL_FIXED.equals(strVariesBy)))
            {
                iQuantity = 1; // Divide fixed cost by number of pax
            }
            else if ((VariesByField.AUTO_PER_ROOM.equals(strVariesBy)) || (VariesByField.MANUAL_PER_ROOM.equals(strVariesBy)))
            {
                int iRooms = 0;
                for (int i = PaxCategory.SINGLE_ID; i <= PaxCategory.QUAD_ID; i++)
                {
                    if ((iPaxCategory == PaxCategory.ALL_ID)
                        || (iPaxCategory == i))
                    {
                        try {
                            iRooms += ((Integer)Converter.convertObjectToDatatype(mapProperties.get(Product.ROOM_TYPE_PARAM + Integer.toString(i)), Integer.class, IntegerField.ZERO)).intValue() / (i - PaxCategory.SINGLE_ID + 1);
                        } catch (Exception ex) {
                        }
                    }
                }
                if (iRooms > 0)
                    iQuantity = iRooms;
            }
            if ((VariesByField.MANUAL_PER_PERSON.equals(strVariesBy)) || (VariesByField.MANUAL_FIXED.equals(strVariesBy)) || (VariesByField.MANUAL_PER_ROOM.equals(strVariesBy)))
            { // Cost comes from a param
                String strKey = BookingDetailModel.COST_PARAM + '.' + BookingDetailModel.MESSAGE_PARAM + '.' + ThinUtil.fixDOMElementName(recVariesOn.getVariesParam(this.getField(LandPricing.VARIES_DESC).toString()));
                if (mapProperties.get(strKey) == null)
                {
                    String PARAM_REQUIRED = "Param required";
                    String strError = strKey;
                    if (mapSurvey != null)
                    { // Name of param that is missing
                        mapSurvey.put(strKey + '.' + recVariesOn.getField(LandVaries.DESCRIPTION).getFieldName(), recVariesOn.getField(LandVaries.DESCRIPTION).toString());
                        strError = recVariesOn.getField(LandVaries.DESCRIPTION).toString();
                        if (!this.getField(LandPricing.VARIES_DESC).isNull())
                        {
                            mapSurvey.put(strKey + '.' + this.getField(LandPricing.VARIES_DESC).getFieldName(), this.getField(LandPricing.VARIES_DESC).toString());
                            strError = strError +  "(" + this.getField(LandPricing.VARIES_DESC).toString() + ")";
                        }
                    }
                    if (task != null)
                        return task.setLastError(MessageFormat.format(task.getApplication().getResources(ResourceConstants.BOOKING_RESOURCE, true).getString(PARAM_REQUIRED), strError));
                    return DBConstants.ERROR_RETURN;
                }
                try {
                    iQuantity = iQuantity * ((Double)Converter.convertObjectToDatatype(mapProperties.get(strKey), Double.class, DoubleField.ZERO)).intValue();
                } catch (Exception ex) {
                }           
            }
        }
        return iQuantity;
    }
    /**
     * SetupLandFilter Method.
     */
    public FileListener setupLandFilter(Land recLand, Date dateTarget, short sTargetPax, int iSicPmc)
    {
        this.setKeyArea(LandPricing.PRODUCT_ID_KEY);
        FileListener listener = new LandPricingFilter(recLand, dateTarget, sTargetPax, iSicPmc);
        return listener;
    }

}

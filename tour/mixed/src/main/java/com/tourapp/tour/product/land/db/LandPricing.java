/**
 *  @(#)LandPricing.
 *  Copyright © 2010 tourapp.com. All rights reserved.
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
import org.jbundle.base.screen.model.*;
import org.jbundle.base.screen.model.util.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.util.Util;

import com.tourapp.tour.product.base.db.*;
import com.tourapp.tour.product.land.screen.*;
import java.util.*;
import com.tourapp.tour.base.db.*;
import java.text.*;
import com.tourapp.tour.booking.detail.db.*;
import com.tourapp.tour.product.land.event.*;
import com.tourapp.tour.message.land.request.data.*;

/**
 *  LandPricing - Land pricing.
 */
public class LandPricing extends ProductPricing
{
    private static final long serialVersionUID = 1L;

    //public static final int kID = kID;
    //public static final int kProductID = kProductID;
    //public static final int kPaxCategoryID = kPaxCategoryID;
    //public static final int kRateID = kRateID;
    //public static final int kClassID = kClassID;
    //public static final int kStartDate = kStartDate;
    //public static final int kEndDate = kEndDate;
    //public static final int kProductTermsID = kProductTermsID;
    //public static final int kCost = kCost;
    public static final int kFromPax = kProductPricingLastField + 1;
    public static final int kToPax = kFromPax + 1;
    public static final int kLandVariesID = kToPax + 1;
    public static final int kVariesDesc = kLandVariesID + 1;
    public static final int kLandPricingLastField = kVariesDesc;
    public static final int kLandPricingFields = kVariesDesc - DBConstants.MAIN_FIELD + 1;

    public static final int kIDKey = DBConstants.MAIN_KEY_FIELD;
    public static final int kProductIDKey = kIDKey + 1;
    public static final int kLandPricingLastKey = kProductIDKey;
    public static final int kLandPricingKeys = kProductIDKey - DBConstants.MAIN_KEY_FIELD + 1;
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

    public static final String kLandPricingFile = "LandPricing";
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(kLandPricingFile, bAddQuotes) : super.getTableNames(bAddQuotes);
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
    public BaseScreen makeScreen(ScreenLocation itsLocation, BasePanel parentScreen, int iDocMode, Map<String,Object> properties)
    {
        BaseScreen screen = null;
        if ((iDocMode & ScreenConstants.MAINT_MODE) != 0)
            screen = new LandPricingScreen(this, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if ((iDocMode & ScreenConstants.DISPLAY_MODE) != 0)
            screen = new LandPricingGridScreen(this, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
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
            field = new ReferenceField(this, "ProductID", 8, null, null);
        //if (iFieldSeq == kPaxCategoryID)
        //{
        //  field = new PaxBaseCategoryField(this, "PaxCategoryID", Constants.DEFAULT_FIELD_LENGTH, null, new Integer(PaxCategory.ALL_ID));
        //  field.addListener(new InitOnceFieldHandler(null));
        //}
        if (iFieldSeq == kRateID)
        {
            field = new LandRateField(this, "RateID", Constants.DEFAULT_FIELD_LENGTH, null, new Integer(0));
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kClassID)
        {
            field = new LandClassField(this, "ClassID", Constants.DEFAULT_FIELD_LENGTH, null, new Integer(0));
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kStartDate)
        {
            field = new DateField(this, "StartDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        //if (iFieldSeq == kEndDate)
        //{
        //  field = new DateField(this, "EndDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //  field.addListener(new InitOnceFieldHandler(null));
        //}
        if (iFieldSeq == kFromPax)
        {
            field = new ShortField(this, "FromPax", 3, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kToPax)
        {
            field = new ShortField(this, "ToPax", 3, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kProductTermsID)
        {
            field = new ProductTermsField(this, "ProductTermsID", 1, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kLandVariesID)
        {
            field = new LandVariesField(this, "LandVariesID", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kVariesDesc)
            field = new StringField(this, "VariesDesc", 16, null, null);
        if (iFieldSeq == kCost)
            field = new FullCurrencyField(this, "Cost", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kLandPricingLastField)
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
            keyArea.addKeyField(kClassID, DBConstants.ASCENDING);
            keyArea.addKeyField(kEndDate, DBConstants.ASCENDING);
            keyArea.addKeyField(kToPax, DBConstants.ASCENDING);
        }
        if (keyArea == null) if (iKeyArea < kLandPricingLastKey)
        {
            keyArea = super.setupKey(iKeyArea);     
            if (keyArea == null) if (iKeyArea < kLandPricingLastKey)
                keyArea = new EmptyKey(this);
        }
        return keyArea;
    }
    /**
     * AddMasterListeners Method.
     */
    public void addMasterListeners()
    {
        super.addMasterListeners();
        this.getField(LandPricing.kLandVariesID).addListener(new ManualVariesDefaultCost(null));
    }
    /**
     * GetQuantity Method.
     */
    public int getQuantity(Task task, short sTargetPax, LandMessageData mapProperties, Map<String, Object> mapSurvey)
    {
        int iQuantity = 0;
        int iPaxCategory = (int)this.getField(LandPricing.kPaxCategoryID).getValue();
        if (iPaxCategory == PaxCategory.ALL_ID)
            iQuantity = sTargetPax;
        else
        {
            try {
                iQuantity = ((Integer)Converter.convertObjectToDatatype(mapProperties.get(Product.ROOM_TYPE_PARAM + Integer.toString(iPaxCategory)), Integer.class, IntegerField.ZERO)).intValue();
            } catch (Exception ex) {
            }
        }
        LandVaries recVariesOn = (LandVaries)((ReferenceField)this.getField(LandPricing.kLandVariesID)).getReference();
        if (recVariesOn != null)
            if ((recVariesOn.getEditMode() == DBConstants.EDIT_CURRENT) || (recVariesOn.getEditMode() == DBConstants.EDIT_IN_PROGRESS))
        {
            String strVariesBy = recVariesOn.getField(LandVaries.kVariesBy).toString();
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
                String strKey = BookingDetail.COST_PARAM + '.' + BookingDetail.MESSAGE_PARAM + '.' + Util.fixDOMElementName(recVariesOn.getVariesParam(this.getField(LandPricing.kVariesDesc).toString()));
                if (mapProperties.get(strKey) == null)
                {
                    String PARAM_REQUIRED = "Param required";
                    String strError = strKey;
                    if (mapSurvey != null)
                    { // Name of param that is missing
                        mapSurvey.put(strKey + '.' + recVariesOn.getField(LandVaries.kDescription).getFieldName(), recVariesOn.getField(LandVaries.kDescription).toString());
                        strError = recVariesOn.getField(LandVaries.kDescription).toString();
                        if (!this.getField(LandPricing.kVariesDesc).isNull())
                        {
                            mapSurvey.put(strKey + '.' + this.getField(LandPricing.kVariesDesc).getFieldName(), this.getField(LandPricing.kVariesDesc).toString());
                            strError = strError +  "(" + this.getField(LandPricing.kVariesDesc).toString() + ")";
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
        this.setKeyArea(LandPricing.kProductIDKey);
        FileListener listener = new LandPricingFilter(recLand, dateTarget, sTargetPax, iSicPmc);
        return listener;
    }

}

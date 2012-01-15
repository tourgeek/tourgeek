/**
 * @(#)Airline.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.product.air.db;

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
import com.tourapp.tour.product.air.screen.*;
import com.tourapp.tour.base.db.*;
import com.tourapp.tour.acctpay.db.*;
import com.tourapp.tour.genled.db.*;
import com.tourapp.model.tour.product.air.db.*;

/**
 *  Airline - Airline.
 */
public class Airline extends VirtualRecord
     implements AirlineModel
{
    private static final long serialVersionUID = 1L;

    //public static final int kID = kID;
    public static final int kDescription = kVirtualRecordLastField + 1;
    public static final int kShortDesc = kDescription + 1;
    public static final int kAirlineCode = kShortDesc + 1;
    public static final int kICAOCode = kAirlineCode + 1;
    public static final int kCountryID = kICAOCode + 1;
    public static final int kAirlineNo = kCountryID + 1;
    public static final int kVendorID = kAirlineNo + 1;
    public static final int kContact = kVendorID + 1;
    public static final int kContactTitle = kContact + 1;
    public static final int kAcceptMCOs = kContactTitle + 1;
    public static final int kMcoSvcChg = kAcceptMCOs + 1;
    public static final int kMcoRecAccountID = kMcoSvcChg + 1;
    public static final int kMcoVarAccountID = kMcoRecAccountID + 1;
    public static final int kOverrideAccountID = kMcoVarAccountID + 1;
    public static final int kORVarAccountID = kOverrideAccountID + 1;
    public static final int kLogo = kORVarAccountID + 1;
    public static final int kAirlineLastField = kLogo;
    public static final int kAirlineFields = kLogo - DBConstants.MAIN_FIELD + 1;

    public static final int kIDKey = DBConstants.MAIN_KEY_FIELD;
    public static final int kDescriptionKey = kIDKey + 1;
    public static final int kAirlineCodeKey = kDescriptionKey + 1;
    public static final int kAirlineNoKey = kAirlineCodeKey + 1;
    public static final int kVendorIDKey = kAirlineNoKey + 1;
    public static final int kAirlineLastKey = kVendorIDKey;
    public static final int kAirlineKeys = kVendorIDKey - DBConstants.MAIN_KEY_FIELD + 1;
    /**
     * Default constructor.
     */
    public Airline()
    {
        super();
    }
    /**
     * Constructor.
     */
    public Airline(RecordOwner screen)
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

    public static final String kAirlineFile = "Airline";
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(kAirlineFile, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "Airline";
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
        return DBConstants.LOCAL | DBConstants.SHARED_DATA | DBConstants.LOCALIZABLE;
    }
    /**
     * Make a default screen.
     */
    public BaseScreen makeScreen(ScreenLocation itsLocation, BasePanel parentScreen, int iDocMode, Map<String,Object> properties)
    {
        BaseScreen screen = null;
        if ((iDocMode & ScreenConstants.MAINT_MODE) == ScreenConstants.MAINT_MODE)
            screen = BaseScreen.makeNewScreen(AIRLINE_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        if ((iDocMode & ScreenConstants.DISPLAY_MODE) == ScreenConstants.DISPLAY_MODE)
            screen = BaseScreen.makeNewScreen(AIRLINE_GRID_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
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
        if (iFieldSeq == kDescription)
            field = new StringField(this, "Description", 40, null, null);
        if (iFieldSeq == kShortDesc)
            field = new StringField(this, "ShortDesc", 16, null, null);
        if (iFieldSeq == kAirlineCode)
            field = new StringField(this, "AirlineCode", 2, null, null);
        if (iFieldSeq == kICAOCode)
            field = new StringField(this, "ICAOCode", 4, null, null);
        if (iFieldSeq == kCountryID)
            field = new CountryField(this, "CountryID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kAirlineNo)
            field = new ShortField(this, "AirlineNo", 4, null, null);
        if (iFieldSeq == kVendorID)
            field = new VendorSelect(this, "VendorID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kContact)
            field = new StringField(this, "Contact", 30, null, null);
        if (iFieldSeq == kContactTitle)
            field = new StringField(this, "ContactTitle", 30, null, null);
        if (iFieldSeq == kAcceptMCOs)
        {
            field = new BooleanField(this, "AcceptMCOs", 1, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kMcoSvcChg)
        {
            field = new PercentField(this, "McoSvcChg", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kMcoRecAccountID)
        {
            field = new AccountField(this, "McoRecAccountID", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kMcoVarAccountID)
        {
            field = new AccountField(this, "McoVarAccountID", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kOverrideAccountID)
        {
            field = new AccountField(this, "OverrideAccountID", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kORVarAccountID)
        {
            field = new AccountField(this, "ORVarAccountID", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kLogo)
            field = new ImageField(this, "Logo", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kAirlineLastField)
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
        if (iKeyArea == kAirlineCodeKey)
        {
            keyArea = this.makeIndex(DBConstants.SECONDARY_KEY, "AirlineCode");
            keyArea.addKeyField(kAirlineCode, DBConstants.ASCENDING);
        }
        if (iKeyArea == kAirlineNoKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "AirlineNo");
            keyArea.addKeyField(kAirlineNo, DBConstants.ASCENDING);
        }
        if (iKeyArea == kVendorIDKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "VendorID");
            keyArea.addKeyField(kVendorID, DBConstants.ASCENDING);
        }
        if (keyArea == null) if (iKeyArea < kAirlineLastKey)
        {
            keyArea = super.setupKey(iKeyArea);     
            if (keyArea == null) if (iKeyArea < kAirlineLastKey)
                keyArea = new EmptyKey(this);
        }
        return keyArea;
    }

}

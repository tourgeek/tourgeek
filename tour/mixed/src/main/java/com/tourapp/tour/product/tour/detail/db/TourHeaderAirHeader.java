/**
 * @(#)TourHeaderAirHeader.
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
import org.jbundle.thin.base.screen.*;
import com.tourapp.tour.product.tour.db.*;
import com.tourapp.model.tour.product.tour.detail.db.*;

/**
 *  TourHeaderAirHeader - Tour Ticket Header Detail.
 */
public class TourHeaderAirHeader extends TourSub
     implements TourHeaderAirHeaderModel
{
    private static final long serialVersionUID = 1L;

    //public static final int kID = kID;
    //public static final int kTourHeaderOptionID = kTourHeaderOptionID;
    //public static final int kModifyCode = kModifyCode;
    //public static final int kModifyID = kModifyID;
    public static final int kAirlineCode = kTourSubLastField + 1;
    public static final int kAirlineIATA = kAirlineCode + 1;
    public static final int kAirlineDesc = kAirlineIATA + 1;
    public static final int kConjunction = kAirlineDesc + 1;
    public static final int kEndorsements = kConjunction + 1;
    public static final int kOriginDest = kEndorsements + 1;
    public static final int kBookingReference = kOriginDest + 1;
    public static final int kTourCode = kBookingReference + 1;
    public static final int kTotalFareBasis = kTourCode + 1;
    public static final int kFare = kTotalFareBasis + 1;
    public static final int kEquivalent = kFare + 1;
    public static final int kCurrencyCode = kEquivalent + 1;
    public static final int kTaxPercent = kCurrencyCode + 1;
    public static final int kTax1 = kTaxPercent + 1;
    public static final int kTax1Desc = kTax1 + 1;
    public static final int kTax2 = kTax1Desc + 1;
    public static final int kTax2Desc = kTax2 + 1;
    public static final int kTotal = kTax2Desc + 1;
    public static final int kCommission = kTotal + 1;
    public static final int kTax = kCommission + 1;
    public static final int kCommissionRate = kTax + 1;
    public static final int kAgent = kCommissionRate + 1;
    public static final int kInternational = kAgent + 1;
    public static final int kCommPercent = kInternational + 1;
    public static final int kCommAmount = kCommPercent + 1;
    public static final int kTicketBy = kCommAmount + 1;
    public static final int kNetFare = kTicketBy + 1;
    public static final int kOverridePercent = kNetFare + 1;
    public static final int kOverrideAmount = kOverridePercent + 1;
    public static final int kNetCost = kOverrideAmount + 1;
    public static final int kTkOrColl = kNetCost + 1;
    public static final int kARCCost = kTkOrColl + 1;
    public static final int kPNR = kARCCost + 1;
    public static final int kVoid = kPNR + 1;
    public static final int kVoidDate = kVoid + 1;
    public static final int kExchTicket = kVoidDate + 1;
    public static final int kDepDate = kExchTicket + 1;
    public static final int kCredit = kDepDate + 1;
    public static final int kComment1 = kCredit + 1;
    public static final int kComment2 = kComment1 + 1;
    public static final int kComment3 = kComment2 + 1;
    public static final int kCRSConf = kComment3 + 1;
    public static final int kCRSStatus = kCRSConf + 1;
    public static final int kFreqFlier = kCRSStatus + 1;
    public static final int kFare1 = kFreqFlier + 1;
    public static final int kFare2 = kFare1 + 1;
    public static final int kFare3 = kFare2 + 1;
    public static final int kTourHeaderAirHeaderLastField = kFare3;
    public static final int kTourHeaderAirHeaderFields = kFare3 - DBConstants.MAIN_FIELD + 1;

    public static final int kIDKey = DBConstants.MAIN_KEY_FIELD;
    public static final int kTourHeaderOptionIDKey = kIDKey + 1;
    public static final int kTourHeaderAirHeaderLastKey = kTourHeaderOptionIDKey;
    public static final int kTourHeaderAirHeaderKeys = kTourHeaderOptionIDKey - DBConstants.MAIN_KEY_FIELD + 1;
    /**
     * Default constructor.
     */
    public TourHeaderAirHeader()
    {
        super();
    }
    /**
     * Constructor.
     */
    public TourHeaderAirHeader(RecordOwner screen)
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

    public static final String kTourHeaderAirHeaderFile = "TourHeaderAirHeader";
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(kTourHeaderAirHeaderFile, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "Tour ticket header";
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
            screen = Record.makeNewScreen(TOUR_HEADER_AIR_HEADER_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        if ((iDocMode & ScreenConstants.DISPLAY_MODE) == ScreenConstants.DISPLAY_MODE)
            screen = Record.makeNewScreen(TOUR_HEADER_AIR_HEADER_GRID_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
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
        //if (iFieldSeq == kModifyCode)
        //  field = new ModifyCodeField(this, "ModifyCode", 1, null, null);
        //if (iFieldSeq == kModifyID)
        //  field = new ModifyTourSubField(this, "ModifyID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kAirlineCode)
        {
            field = new StringField(this, "AirlineCode", 2, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kAirlineIATA)
            field = new ShortField(this, "AirlineIATA", 4, null, null);
        if (iFieldSeq == kAirlineDesc)
            field = new StringField(this, "AirlineDesc", 16, null, null);
        if (iFieldSeq == kConjunction)
            field = new ShortField(this, "Conjunction", 1, null, null);
        if (iFieldSeq == kEndorsements)
        {
            field = new StringField(this, "Endorsements", 29, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kOriginDest)
        {
            field = new StringField(this, "OriginDest", 13, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kBookingReference)
            field = new StringField(this, "BookingReference", 13, null, null);
        if (iFieldSeq == kTourCode)
        {
            field = new StringField(this, "TourCode", 14, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kTotalFareBasis)
        {
            field = new BooleanField(this, "TotalFareBasis", 1, null, new Boolean(false));
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kFare)
        {
            field = new FloatField(this, "Fare", 9, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kEquivalent)
        {
            field = new FloatField(this, "Equivalent", 8, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kCurrencyCode)
        {
            field = new StringField(this, "CurrencyCode", 3, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kTaxPercent)
        {
            field = new FloatField(this, "TaxPercent", 5, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kTax1)
        {
            field = new FloatField(this, "Tax1", 9, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kTax1Desc)
        {
            field = new StringField(this, "Tax1Desc", 2, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kTax2)
        {
            field = new FloatField(this, "Tax2", 9, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kTax2Desc)
        {
            field = new StringField(this, "Tax2Desc", 2, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kTotal)
        {
            field = new FloatField(this, "Total", 9, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kCommission)
            field = new StringField(this, "Commission", 10, null, "      10   ");
        if (iFieldSeq == kTax)
            field = new StringField(this, "Tax", 10, null, "      8   ");
        if (iFieldSeq == kCommissionRate)
            field = new StringField(this, "CommissionRate", 5, null, "  10 ");
        if (iFieldSeq == kAgent)
            field = new StringField(this, "Agent", 10, null, " AGENT");
        if (iFieldSeq == kInternational)
            field = new StringField(this, "International", 3, null, "X/");
        if (iFieldSeq == kCommPercent)
        {
            field = new FloatField(this, "CommPercent", 5, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kCommAmount)
            field = new FloatField(this, "CommAmount", 9, null, null);
        if (iFieldSeq == kTicketBy)
            field = new StringField(this, "TicketBy", 1, null, "U");
        if (iFieldSeq == kNetFare)
        {
            field = new FloatField(this, "NetFare", 9, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kOverridePercent)
        {
            field = new FloatField(this, "OverridePercent", 5, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kOverrideAmount)
        {
            field = new FloatField(this, "OverrideAmount", 9, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kNetCost)
        {
            field = new FloatField(this, "NetCost", 9, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kTkOrColl)
        {
            field = new FloatField(this, "TkOrColl", 9, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == kARCCost)
        {
            field = new FloatField(this, "ARCCost", 9, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == kPNR)
        {
            field = new StringField(this, "PNR", 15, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kVoid)
        {
            field = new BooleanField(this, "Void", 1, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kVoidDate)
        {
            field = new DateField(this, "VoidDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kExchTicket)
        {
            field = new StringField(this, "ExchTicket", 20, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kDepDate)
        {
            field = new DateField(this, "DepDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kCredit)
        {
            field = new BooleanField(this, "Credit", 1, null, new Boolean(false));
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kComment1)
        {
            field = new StringField(this, "Comment1", 20, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kComment2)
        {
            field = new StringField(this, "Comment2", 20, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kComment3)
        {
            field = new StringField(this, "Comment3", 20, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kCRSConf)
        {
            field = new StringField(this, "CRSConf", 20, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kCRSStatus)
        {
            field = new StringField(this, "CRSStatus", 2, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kFreqFlier)
        {
            field = new StringField(this, "FreqFlier", 20, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kFare1)
        {
            field = new StringField(this, "Fare1", 60, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kFare2)
        {
            field = new StringField(this, "Fare2", 60, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kFare3)
        {
            field = new StringField(this, "Fare3", 60, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kTourHeaderAirHeaderLastField)
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
        }
        if (keyArea == null) if (iKeyArea < kTourHeaderAirHeaderLastKey)
        {
            keyArea = super.setupKey(iKeyArea);     
            if (keyArea == null) if (iKeyArea < kTourHeaderAirHeaderLastKey)
                keyArea = new EmptyKey(this);
        }
        return keyArea;
    }

}

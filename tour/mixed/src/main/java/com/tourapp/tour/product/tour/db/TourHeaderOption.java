/**
 *  @(#)TourHeaderOption.
 *  Copyright © 2010 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.product.tour.db;

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
import com.tourapp.tour.product.tour.screen.*;
import com.tourapp.tour.product.tour.detail.screen.*;
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.product.tour.detail.db.*;
import com.tourapp.tour.product.base.db.*;

/**
 *  TourHeaderOption - Tour product options.
 */
public class TourHeaderOption extends VirtualRecord
{
    private static final long serialVersionUID = 1L;

    //public static final int kID = kID;
    public static final int kTourOrOption = kVirtualRecordLastField + 1;
    public static final int kTourOrOptionID = kTourOrOption + 1;
    public static final int kSequence = kTourOrOptionID + 1;
    public static final int kDescription = kSequence + 1;
    public static final int kComments = kDescription + 1;
    public static final int kAskForAnswer = kComments + 1;
    public static final int kAlwaysResolve = kAskForAnswer + 1;
    public static final int kStartDate = kAlwaysResolve + 1;
    public static final int kEndDate = kStartDate + 1;
    public static final int kDaysOfWeek = kEndDate + 1;
    public static final int kGateway = kDaysOfWeek + 1;
    public static final int kUseTourHeaderOptionID = kGateway + 1;
    public static final int kPaxCategoryID = kUseTourHeaderOptionID + 1;
    public static final int kDetailOptionCount = kPaxCategoryID + 1;
    public static final int kDetailPriceCount = kDetailOptionCount + 1;
    public static final int kDetailAirHeaderCount = kDetailPriceCount + 1;
    public static final int kDetailTourDetailCount = kDetailAirHeaderCount + 1;
    public static final int kTourClassID = kDetailTourDetailCount + 1;
    public static final int kInvoiceText = kTourClassID + 1;
    public static final int kItineraryDesc = kInvoiceText + 1;
    public static final int kTourHeaderOptionLastField = kItineraryDesc;
    public static final int kTourHeaderOptionFields = kItineraryDesc - DBConstants.MAIN_FIELD + 1;

    public static final int kIDKey = DBConstants.MAIN_KEY_FIELD;
    public static final int kTourOrOptionKey = kIDKey + 1;
    public static final int kTourHeaderOptionLastKey = kTourOrOptionKey;
    public static final int kTourHeaderOptionKeys = kTourOrOptionKey - DBConstants.MAIN_KEY_FIELD + 1;
    public static final int PRICING_GRID_SCREEN = ScreenConstants.LAST_MODE * 2;
    public static final int TOUR_GRID_SCREEN = ScreenConstants.LAST_MODE * 1024;
    public static final int AIR_GRID_SCREEN = ScreenConstants.LAST_MODE * 128;
    public static final int AIR_HEADER_GRID_SCREEN = ScreenConstants.LAST_MODE * 512;
    public static final int HOTEL_GRID_SCREEN = ScreenConstants.LAST_MODE * 4;
    public static final int LAND_GRID_SCREEN = ScreenConstants.LAST_MODE * 8;
    public static final int CAR_GRID_SCREEN = ScreenConstants.LAST_MODE * 32;
    public static final int TRANSPORTATION_GRID_SCREEN = ScreenConstants.LAST_MODE * 16;
    public static final int CRUISE_GRID_SCREEN = ScreenConstants.LAST_MODE * 64;
    public static final int ITEM_GRID_SCREEN = ScreenConstants.LAST_MODE * 256;
    public static final String OPTION = "O";
    public static final String TOUR = "T";
    /**
     * Default constructor.
     */
    public TourHeaderOption()
    {
        super();
    }
    /**
     * Constructor.
     */
    public TourHeaderOption(RecordOwner screen)
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

    public static final String kTourHeaderOptionFile = "TourHeaderOption";
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(kTourHeaderOptionFile, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "Question/Answer";
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
     * MakeScreen Method.
     */
    public BaseScreen makeScreen(ScreenLocation itsLocation, BasePanel parentScreen, int iDocMode, Map<String,Object> properties)
    {
        BaseScreen screen = null;
        if ((iDocMode & TourHeaderOption.PRICING_GRID_SCREEN) == TourHeaderOption.PRICING_GRID_SCREEN)
            screen = new TourHeaderLineGridScreen(this, null, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if ((iDocMode & TourHeaderOption.HOTEL_GRID_SCREEN) == TourHeaderOption.HOTEL_GRID_SCREEN)
            screen = new TourHeaderHotelGridScreen(this, null, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if ((iDocMode & TourHeaderOption.LAND_GRID_SCREEN) == TourHeaderOption.LAND_GRID_SCREEN)
            screen = new TourHeaderLandGridScreen(this, null, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if ((iDocMode & TourHeaderOption.TRANSPORTATION_GRID_SCREEN) == TourHeaderOption.TRANSPORTATION_GRID_SCREEN)
            screen = new TourHeaderTransportationGridScreen(this, null, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if ((iDocMode & TourHeaderOption.CAR_GRID_SCREEN) == TourHeaderOption.CAR_GRID_SCREEN)
            screen = new TourHeaderCarGridScreen(this, null, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if ((iDocMode & TourHeaderOption.CRUISE_GRID_SCREEN) == TourHeaderOption.CRUISE_GRID_SCREEN)
            screen = new TourHeaderCruiseGridScreen(this, null, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if ((iDocMode & TourHeaderOption.TOUR_GRID_SCREEN) == TourHeaderOption.TOUR_GRID_SCREEN)
            screen = new TourHeaderTourHeaderGridScreen(this, null, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if ((iDocMode & TourHeaderOption.AIR_GRID_SCREEN) == TourHeaderOption.AIR_GRID_SCREEN)
            screen = new TourHeaderAirGridScreen(this, null, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if ((iDocMode & TourHeaderOption.AIR_HEADER_GRID_SCREEN) == TourHeaderOption.AIR_HEADER_GRID_SCREEN)
            screen = new TourHeaderAirHeaderGridScreen(this, null, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if ((iDocMode & TourHeaderOption.ITEM_GRID_SCREEN) == TourHeaderOption.ITEM_GRID_SCREEN)
            screen = new TourHeaderItemGridScreen(this, null, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if ((iDocMode & ScreenConstants.MAINT_MODE) == ScreenConstants.MAINT_MODE)
            screen = new TourHeaderOptionScreen(this, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if ((iDocMode & ScreenConstants.DISPLAY_MODE) != 0)
            screen = new TourHeaderOptionGridScreen(this, null, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
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
            field = new CounterField(this, "ID", 8, null, null);
            field.setHidden(true);
        }
        if (iFieldSeq == kTourOrOption)
            field = new StringField(this, "TourOrOption", 1, null, null);
        if (iFieldSeq == kTourOrOptionID)
            field = new ReferenceField(this, "TourOrOptionID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kSequence)
            field = new ShortField(this, "Sequence", 4, null, null);
        if (iFieldSeq == kDescription)
            field = new StringField(this, "Description", 60, null, null);
        if (iFieldSeq == kComments)
            field = new MemoField(this, "Comments", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kAskForAnswer)
        {
            field = new BooleanField(this, "AskForAnswer", 1, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kAlwaysResolve)
        {
            field = new BooleanField(this, "AlwaysResolve", 1, null, new Boolean(false));
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kStartDate)
            field = new DateField(this, "StartDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kEndDate)
            field = new DateField(this, "EndDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kDaysOfWeek)
        {
            field = new DaysOfWeekField(this, "DaysOfWeek", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kGateway)
        {
            field = new StringField(this, "Gateway", 3, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kUseTourHeaderOptionID)
            field = new UseTourHeaderOptionField(this, "UseTourHeaderOptionID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kPaxCategoryID)
        {
            field = new PaxCategoryField(this, "PaxCategoryID", 8, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kDetailOptionCount)
            field = new IntegerField(this, "DetailOptionCount", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kDetailPriceCount)
            field = new IntegerField(this, "DetailPriceCount", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kDetailAirHeaderCount)
            field = new IntegerField(this, "DetailAirHeaderCount", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kDetailTourDetailCount)
            field = new IntegerField(this, "DetailTourDetailCount", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kTourClassID)
            field = new TourClassField(this, "TourClassID", 4, null, null);
        if (iFieldSeq == kInvoiceText)
            field = new XmlField(this, "InvoiceText", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kItineraryDesc)
            field = new XmlField(this, "ItineraryDesc", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kTourHeaderOptionLastField)
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
        if (iKeyArea == kTourOrOptionKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "TourOrOption");
            keyArea.addKeyField(kTourOrOption, DBConstants.ASCENDING);
            keyArea.addKeyField(kTourOrOptionID, DBConstants.ASCENDING);
            keyArea.addKeyField(kSequence, DBConstants.ASCENDING);
        }
        if (keyArea == null) if (iKeyArea < kTourHeaderOptionLastKey)
        {
            keyArea = super.setupKey(iKeyArea);     
            if (keyArea == null) if (iKeyArea < kTourHeaderOptionLastKey)
                keyArea = new EmptyKey(this);
        }
        return keyArea;
    }
    /**
     * Convert the command to the screen document type.
     * @param strCommand The command text.
     * @param The standard document type (MAINT/DISPLAY/SELECT/MENU/etc).
     */
    public int commandToDocType(String strCommand)
    {
        int iDocMode = ScreenConstants.MAINT_MODE;
        if (Product.PRICING_DETAIL.equalsIgnoreCase(strCommand))
            iDocMode = TourHeaderOption.PRICING_GRID_SCREEN;
        else if (ProductType.HOTEL.equalsIgnoreCase(strCommand))
            iDocMode = TourHeaderOption.HOTEL_GRID_SCREEN;
        else if (ProductType.LAND.equalsIgnoreCase(strCommand))
            iDocMode = TourHeaderOption.LAND_GRID_SCREEN;
        else if (ProductType.TRANSPORTATION.equalsIgnoreCase(strCommand))
            iDocMode = TourHeaderOption.TRANSPORTATION_GRID_SCREEN;
        else if (ProductType.CAR.equalsIgnoreCase(strCommand))
            iDocMode = TourHeaderOption.CAR_GRID_SCREEN;
        else if (ProductType.CRUISE.equalsIgnoreCase(strCommand))
            iDocMode = TourHeaderOption.CRUISE_GRID_SCREEN;
        else if (ProductType.AIR.equalsIgnoreCase(strCommand))
            iDocMode = TourHeaderOption.AIR_GRID_SCREEN;
        else if ((ProductType.AIR + "Header").equalsIgnoreCase(strCommand))
            iDocMode = TourHeaderOption.AIR_HEADER_GRID_SCREEN;
        else if (ProductType.ITEM.equalsIgnoreCase(strCommand))
            iDocMode = TourHeaderOption.ITEM_GRID_SCREEN;
        else if (ProductType.TOUR.equalsIgnoreCase(strCommand))
            iDocMode = TourHeaderOption.TOUR_GRID_SCREEN;
        else
            iDocMode = super.commandToDocType(strCommand);
        return iDocMode;
    }
    /**
     * Does this option apply to this booking/pax/date?.
     */
    public boolean isValid(Booking recBooking, BaseField fldPaxCategory, Date dateStart)
    {
        if (!this.getField(TourHeaderOption.kStartDate).isNull())
            if (this.getField(TourHeaderOption.kStartDate).compareTo(dateStart) > 0)
                return false;   // departure date is before start date.
        if (!this.getField(TourHeaderOption.kEndDate).isNull())
            if (this.getField(TourHeaderOption.kEndDate).compareTo(dateStart) < 0)
                return false;   // departure date is after end date.
        if (!((DaysOfWeekField)this.getField(TourHeaderOption.kDaysOfWeek)).isValidDate(dateStart))
            return false;
        if (!this.getField(TourHeaderOption.kGateway).isNull())
        {
            if (recBooking != null)
                if (!this.getField(TourHeaderOption.kGateway).equals(recBooking.getField(Booking.kGateway)))
                    return false;
        }
        if (!this.getField(TourHeaderOption.kPaxCategoryID).isNull())
        {
            if (fldPaxCategory != null)
                if (!this.getField(TourHeaderOption.kPaxCategoryID).equals(fldPaxCategory))
                    return false;
        }
        return true;
    }
    /**
     * This will add methods that make sure the sub-records are deleted with the main record.
     * These listeners are not added automatically, since there is such a big overhead.
     */
    public void addSubFileIntegrityHandlers()
    {
        this.addListener(new SubFileIntegrityHandler(TourHeaderDetail.class.getName(), true));
        this.addListener(new SubFileIntegrityHandler(TourHeaderLine.class.getName(), true));
        this.addListener(new SubFileIntegrityHandler(TourHeaderAirHeader.class.getName(), true));
        this.addListener(new SubFileIntegrityHandler(TourHeaderOption.class.getName(), true)
        {
            public Record getSubRecord()
            {
                if (m_recDependent == null)
                    m_recDependent = this.createSubRecord();
                if (m_recDependent != null)
                {
                    m_recDependent.setKeyArea(TourHeaderOption.kTourOrOptionKey);
                    StringField fldTourOrOption = new StringField(null, TourHeaderOptionScreen.TOUR_OR_OPTION, 1, null, null);
                    m_recDependent.addListener(new FreeOnFreeHandler(fldTourOrOption));
                    fldTourOrOption.setString(TourHeaderOption.OPTION);
                    if (m_recDependent.getListener(SubFileFilter.class.getName()) == null)
                        m_recDependent.addListener(new SubFileFilter(fldTourOrOption, TourHeaderOption.kTourOrOption, (BaseField)this.getOwner().getCounterField(), TourHeaderOption.kTourOrOptionID, null, -1));
                }
                return m_recDependent;
            }
            public Record createSubRecord()
            {
                TourHeaderOption record = (TourHeaderOption)super.createSubRecord();
                record.addSubFileIntegrityHandlers();
                return record;
            }
        });
    }

}

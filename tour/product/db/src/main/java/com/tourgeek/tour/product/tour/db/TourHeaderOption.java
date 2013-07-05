/**
  * @(#)TourHeaderOption.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.tour.product.tour.db;

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
import com.tourgeek.model.tour.booking.db.*;
import com.tourgeek.model.tour.product.tour.detail.db.*;
import com.tourgeek.tour.product.base.db.*;
import com.tourgeek.model.tour.product.tour.db.*;

/**
 *  TourHeaderOption - Tour product options.
 */
public class TourHeaderOption extends VirtualRecord
     implements TourHeaderOptionModel
{
    private static final long serialVersionUID = 1L;

    public static final int PRICING_GRID_SCREEN = ScreenConstants.DETAIL_MODE | ScreenConstants.LAST_MODE * 2;
    public static final int TOUR_GRID_SCREEN = ScreenConstants.DETAIL_MODE | ScreenConstants.LAST_MODE * 1024;
    public static final int AIR_GRID_SCREEN = ScreenConstants.DETAIL_MODE | ScreenConstants.LAST_MODE * 128;
    public static final int AIR_HEADER_GRID_SCREEN = ScreenConstants.DETAIL_MODE | ScreenConstants.LAST_MODE * 512;
    public static final int HOTEL_GRID_SCREEN = ScreenConstants.DETAIL_MODE | ScreenConstants.LAST_MODE * 4;
    public static final int LAND_GRID_SCREEN = ScreenConstants.DETAIL_MODE | ScreenConstants.LAST_MODE * 8;
    public static final int CAR_GRID_SCREEN = ScreenConstants.DETAIL_MODE | ScreenConstants.LAST_MODE * 32;
    public static final int TRANSPORTATION_GRID_SCREEN = ScreenConstants.DETAIL_MODE | ScreenConstants.LAST_MODE * 16;
    public static final int CRUISE_GRID_SCREEN = ScreenConstants.DETAIL_MODE | ScreenConstants.LAST_MODE * 64;
    public static final int ITEM_GRID_SCREEN = ScreenConstants.DETAIL_MODE | ScreenConstants.LAST_MODE * 256;
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
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(TOUR_HEADER_OPTION_FILE, bAddQuotes) : super.getTableNames(bAddQuotes);
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
    public ScreenParent makeScreen(ScreenLoc itsLocation, ComponentParent parentScreen, int iDocMode, Map<String,Object> properties)
    {
        ScreenParent screen = null;
        if ((iDocMode & TourHeaderOption.PRICING_GRID_SCREEN) == TourHeaderOption.PRICING_GRID_SCREEN)
            screen = Record.makeNewScreen(TourHeaderLineModel.TOUR_HEADER_LINE_GRID_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        else if ((iDocMode & TourHeaderOption.HOTEL_GRID_SCREEN) == TourHeaderOption.HOTEL_GRID_SCREEN)
            screen = Record.makeNewScreen(TourHeaderHotelModel.TOUR_HEADER_HOTEL_GRID_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        else if ((iDocMode & TourHeaderOption.LAND_GRID_SCREEN) == TourHeaderOption.LAND_GRID_SCREEN)
            screen = Record.makeNewScreen(TourHeaderLandModel.TOUR_HEADER_LAND_GRID_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        else if ((iDocMode & TourHeaderOption.TRANSPORTATION_GRID_SCREEN) == TourHeaderOption.TRANSPORTATION_GRID_SCREEN)
            screen = Record.makeNewScreen(TourHeaderTransportationModel.TOUR_HEADER_TRANSPORTATION_GRID_SCREEN_C, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        else if ((iDocMode & TourHeaderOption.CAR_GRID_SCREEN) == TourHeaderOption.CAR_GRID_SCREEN)
            screen = Record.makeNewScreen(TourHeaderCarModel.TOUR_HEADER_CAR_GRID_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        else if ((iDocMode & TourHeaderOption.CRUISE_GRID_SCREEN) == TourHeaderOption.CRUISE_GRID_SCREEN)
            screen = Record.makeNewScreen(TourHeaderCruiseModel.TOUR_HEADER_CRUISE_GRID_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        else if ((iDocMode & TourHeaderOption.TOUR_GRID_SCREEN) == TourHeaderOption.TOUR_GRID_SCREEN)
            screen = Record.makeNewScreen(TourHeaderTourModel.TOUR_HEADER_TOUR_HEADER_GRID_SCREEN_CLAS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        else if ((iDocMode & TourHeaderOption.AIR_GRID_SCREEN) == TourHeaderOption.AIR_GRID_SCREEN)
            screen = Record.makeNewScreen(TourHeaderAirModel.TOUR_HEADER_AIR_GRID_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        else if ((iDocMode & TourHeaderOption.AIR_HEADER_GRID_SCREEN) == TourHeaderOption.AIR_HEADER_GRID_SCREEN)
            screen = Record.makeNewScreen(TourHeaderAirHeaderModel.TOUR_HEADER_AIR_HEADER_GRID_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        else if ((iDocMode & TourHeaderOption.ITEM_GRID_SCREEN) == TourHeaderOption.ITEM_GRID_SCREEN)
            screen = Record.makeNewScreen(TourHeaderItemModel.TOUR_HEADER_ITEM_GRID_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        else if ((iDocMode & ScreenConstants.MAINT_MODE) == ScreenConstants.MAINT_MODE)
            screen = Record.makeNewScreen(TOUR_HEADER_OPTION_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        else if ((iDocMode & ScreenConstants.DISPLAY_MODE) != 0)
            screen = Record.makeNewScreen(TOUR_HEADER_OPTION_GRID_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
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
        if (iFieldSeq == 0)
        {
            field = new CounterField(this, ID, 8, null, null);
            field.setHidden(true);
        }
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
            field = new StringField(this, TOUR_OR_OPTION, 1, null, null);
        if (iFieldSeq == 4)
            field = new ReferenceField(this, TOUR_OR_OPTION_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 5)
            field = new ShortField(this, SEQUENCE, 4, null, null);
        if (iFieldSeq == 6)
            field = new StringField(this, DESCRIPTION, 60, null, null);
        if (iFieldSeq == 7)
            field = new MemoField(this, COMMENTS, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 8)
        {
            field = new BooleanField(this, ASK_FOR_ANSWER, 1, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 9)
        {
            field = new BooleanField(this, ALWAYS_RESOLVE, 1, null, new Boolean(false));
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 10)
            field = new DateField(this, START_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 11)
            field = new DateField(this, END_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 12)
        {
            field = new DaysOfWeekField(this, DAYS_OF_WEEK, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 13)
        {
            field = new StringField(this, GATEWAY, 3, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 14)
            field = new UseTourHeaderOptionField(this, USE_TOUR_HEADER_OPTION_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 15)
        {
            field = new PaxCategoryField(this, PAX_CATEGORY_ID, 8, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 16)
            field = new IntegerField(this, DETAIL_OPTION_COUNT, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 17)
            field = new IntegerField(this, DETAIL_PRICE_COUNT, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 18)
            field = new IntegerField(this, DETAIL_AIR_HEADER_COUNT, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 19)
            field = new IntegerField(this, DETAIL_TOUR_DETAIL_COUNT, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 20)
            field = new TourClassField(this, TOUR_CLASS_ID, 4, null, null);
        if (iFieldSeq == 21)
            field = new XmlField(this, INVOICE_TEXT, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 22)
            field = new XmlField(this, ITINERARY_DESC, Constants.DEFAULT_FIELD_LENGTH, null, null);
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
            keyArea = this.makeIndex(DBConstants.UNIQUE, ID_KEY);
            keyArea.addKeyField(ID, DBConstants.ASCENDING);
        }
        if (iKeyArea == 1)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, TOUR_OR_OPTION_KEY);
            keyArea.addKeyField(TOUR_OR_OPTION, DBConstants.ASCENDING);
            keyArea.addKeyField(TOUR_OR_OPTION_ID, DBConstants.ASCENDING);
            keyArea.addKeyField(SEQUENCE, DBConstants.ASCENDING);
        }
        if (keyArea == null)
            keyArea = super.setupKey(iKeyArea);     
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
    public boolean isValid(BookingModel recBooking, BaseField fldPaxCategory, Date dateStart)
    {
        if (!this.getField(TourHeaderOption.START_DATE).isNull())
            if (this.getField(TourHeaderOption.START_DATE).compareTo(dateStart) > 0)
                return false;   // departure date is before start date.
        if (!this.getField(TourHeaderOption.END_DATE).isNull())
            if (this.getField(TourHeaderOption.END_DATE).compareTo(dateStart) < 0)
                return false;   // departure date is after end date.
        if (!((DaysOfWeekField)this.getField(TourHeaderOption.DAYS_OF_WEEK)).isValidDate(dateStart))
            return false;
        if (!this.getField(TourHeaderOption.GATEWAY).isNull())
        {
            if (recBooking != null)
                if (!this.getField(TourHeaderOption.GATEWAY).equals(recBooking.getField(BookingModel.GATEWAY)))
                    return false;
        }
        if (!this.getField(TourHeaderOption.PAX_CATEGORY_ID).isNull())
        {
            if (fldPaxCategory != null)
                if (!this.getField(TourHeaderOption.PAX_CATEGORY_ID).equals(fldPaxCategory))
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
        this.addListener(new SubFileIntegrityHandler(TourHeaderDetailModel.THICK_CLASS, true));
        this.addListener(new SubFileIntegrityHandler(TourHeaderLineModel.THICK_CLASS, true));
        this.addListener(new SubFileIntegrityHandler(TourHeaderAirHeaderModel.THICK_CLASS, true));
        this.addListener(new SubFileIntegrityHandler(TourHeaderOptionModel.THICK_CLASS, true)
        {
            public Record getSubRecord()
            {
                if (m_recDependent == null)
                    m_recDependent = this.createSubRecord();
                if (m_recDependent != null)
                {
                    m_recDependent.setKeyArea(TourHeaderOption.TOUR_OR_OPTION_KEY);
                    StringField fldTourOrOption = new StringField(null, TourHeaderOption.TOUR_OR_OPTION, 1, null, null);
                    m_recDependent.addListener(new FreeOnFreeHandler(fldTourOrOption));
                    fldTourOrOption.setString(TourHeaderOption.OPTION);
                    if (m_recDependent.getListener(SubFileFilter.class.getName()) == null)
                        m_recDependent.addListener(new SubFileFilter(fldTourOrOption, TourHeaderOption.TOUR_OR_OPTION, (BaseField)this.getOwner().getCounterField(), TourHeaderOption.TOUR_OR_OPTION_ID, null, null));
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


package com.tourgeek.tour.product.tour.detail.db;

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
import com.tourgeek.tour.product.base.db.*;
import com.tourgeek.tour.product.tour.db.*;
import org.jbundle.main.msg.db.*;
import com.tourgeek.tour.base.db.*;
import com.tourgeek.tour.base.field.*;
import com.tourgeek.model.tour.product.tour.detail.db.*;

/**
 *  TourHeaderDetail - Tour Header detail.
 */
public class TourHeaderDetail extends TourSub
     implements TourHeaderDetailModel
{
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor.
     */
    public TourHeaderDetail()
    {
        super();
    }
    /**
     * Constructor.
     */
    public TourHeaderDetail(RecordOwner screen)
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
        return (m_tableName == null) ? Record.formatTableNames(TOUR_HEADER_DETAIL_FILE, bAddQuotes) : super.getTableNames(bAddQuotes);
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
        return DBConstants.REMOTE | DBConstants.BASE_TABLE_CLASS | DBConstants.SHARED_TABLE | DBConstants.USER_DATA;
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
        {
            field = new TourHeaderOptionField(this, TOUR_HEADER_OPTION_ID, 8, null, null);
            field.setNullable(false);
        }
        //if (iFieldSeq == 4)
        //  field = new ModifyCodeField(this, MODIFY_CODE, 1, null, null);
        //if (iFieldSeq == 5)
        //  field = new ModifyTourSubField(this, MODIFY_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 6)
            field = new ShortField(this, DAY, 2, null, new Short((short)1));
        if (iFieldSeq == 7)
            field = new TimeField(this, ETD, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 8)
            field = new ProductTypeField(this, PRODUCT_TYPE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 9)
            field = new ReferenceField(this, PRODUCT_ID, 8, null, null);
        if (iFieldSeq == 10)
            field = new BaseRateField(this, RATE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 11)
            field = new BaseClassField(this, CLASS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 12)
            field = new MessageTransportSelect(this, INFO_MESSAGE_TRANSPORT_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 13)
            field = new InfoStatusSelect(this, INFO_STATUS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 14)
            field = new MessageTransportSelect(this, COST_MESSAGE_TRANSPORT_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 15)
            field = new CostStatusSelect(this, COST_STATUS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 16)
            field = new MessageTransportSelect(this, INVENTORY_MESSAGE_TRANSPORT_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 17)
            field = new InventoryStatusSelect(this, INVENTORY_STATUS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 18)
            field = new MessageTransportSelect(this, PRODUCT_MESSAGE_TRANSPORT_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 19)
            field = new ProductStatusSelect(this, PRODUCT_STATUS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 20)
            field = new ShortField(this, ACK_DAYS, 2, null, null);
        if (iFieldSeq == 21)
            field = new MemoField(this, COMMENTS, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 22)
            field = new XmlField(this, ITINERARY_DESC, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 23)
            field = new ShortField(this, NIGHTS, 2, null, new Short((short)1));
        if (iFieldSeq == 24)
            field = new MealPlanField(this, MEAL_1, 2, null, null);
        if (iFieldSeq == 25)
            field = new ShortField(this, MEAL_QTY_1, 2, null, null);
        if (iFieldSeq == 26)
            field = new MealDays(this, MEAL_DAYS_1, 2, null, null);
        if (iFieldSeq == 27)
            field = new MealPlanField(this, MEAL_2, 2, null, null);
        if (iFieldSeq == 28)
            field = new ShortField(this, MEAL_QTY_2, 2, null, null);
        if (iFieldSeq == 29)
            field = new MealDays(this, MEAL_DAYS_2, 2, null, null);
        if (iFieldSeq == 30)
            field = new MealPlanField(this, MEAL_3, 2, null, null);
        if (iFieldSeq == 31)
            field = new ShortField(this, MEAL_QTY_3, 2, null, null);
        if (iFieldSeq == 32)
            field = new MealDays(this, MEAL_DAYS_3, 2, null, null);
        if (iFieldSeq == 33)
            field = new MealPlanField(this, MEAL_4, 2, null, null);
        if (iFieldSeq == 34)
            field = new ShortField(this, MEAL_QTY_4, 2, null, null);
        if (iFieldSeq == 35)
            field = new MealDays(this, MEAL_DAYS_4, 2, null, null);
        if (iFieldSeq == 36)
            field = new ShortField(this, PMC_CUTOFF, 3, null, null);
        if (iFieldSeq == 37)
            field = new CityField(this, CITY_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 38)
            field = new StringField(this, CITY_CODE, 3, null, null);
        if (iFieldSeq == 39)
            field = new CityField(this, TO_CITY_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 40)
            field = new StringField(this, TO_CITY_CODE, 3, null, null);
        if (iFieldSeq == 41)
            field = new TourHeaderAirHeaderField(this, TOUR_HEADER_AIR_HEADER_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 42)
        {
            field = new TimeField(this, GMT_TIME, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 43)
        {
            field = new StringField(this, CITY_DESC, 17, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 44)
        {
            field = new StringField(this, TO_CITY_DESC, 17, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 45)
            field = new ReferenceField(this, TICKET_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 46)
        {
            field = new StringField(this, XO, 1, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 47)
        {
            field = new StringField(this, CARRIER, 2, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 48)
            field = new AirlineField(this, AIRLINE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 49)
        {
            field = new StringField(this, FLIGHT_NO, 4, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 50)
        {
            field = new StringField(this, FLIGHT_CLASS, 1, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 51)
        {
            field = new TimeField(this, ARRIVE_TIME, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 52)
            field = new ShortField(this, ADD_DAYS, 2, null, null);
        if (iFieldSeq == 53)
            field = new StringField(this, ARC_STATUS, 2, null, "OK");
        if (iFieldSeq == 54)
        {
            field = new StringField(this, FARE_BASIS, 15, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 55)
        {
            field = new DateField(this, START_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 56)
        {
            field = new DateField(this, END_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 57)
        {
            field = new StringField(this, ALLOW, 3, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 58)
        {
            field = new DoubleField(this, DET_FARE, 10, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 59)
        {
            field = new StringField(this, CONFIRMED_BY, 16, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 60)
        {
            field = new StringField(this, CONFIRMATION_NO, 20, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 61)
        {
            field = new ShortField(this, COUPON, 1, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 62)
        {
            field = new StringField(this, SEAT, 5, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 63)
        {
            field = new StringField(this, GATE, 5, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 64)
        {
            field = new StringField(this, SEAT_PERF, 1, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 65)
        {
            field = new BooleanField(this, SMOKING, 1, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 66)
        {
            field = new StringField(this, MEALS, 2, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 67)
        {
            field = new FloatField(this, DAYS, Constants.DEFAULT_FIELD_LENGTH, null, new Float(1));
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 68)
            field = new PricingTypeSelect(this, PRICING_TYPE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
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
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, TOUR_HEADER_OPTION_ID_KEY);
            keyArea.addKeyField(TOUR_HEADER_OPTION_ID, DBConstants.ASCENDING);
            keyArea.addKeyField(DAY, DBConstants.ASCENDING);
            keyArea.addKeyField(ETD, DBConstants.ASCENDING);
        }
        if (iKeyArea == 2)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, PRODUCT_ID_KEY);
            keyArea.addKeyField(PRODUCT_ID, DBConstants.ASCENDING);
            keyArea.addKeyField(DAY, DBConstants.ASCENDING);
            keyArea.addKeyField(ETD, DBConstants.ASCENDING);
        }
        if (keyArea == null)
            keyArea = super.setupKey(iKeyArea);     
        return keyArea;
    }
    /**
     * Get the record type from the field that specifies the record type.
     * (Override this).
     * @return The record type (as an object).
     */
    public BaseField getSharedRecordTypeKey()
    {
        return this.getField(TourHeaderDetail.PRODUCT_TYPE_ID);
    }
    /**
     * Get the shared record that goes with this key.
     * (Always override this).
     * @param objKey The value that specifies the record type.
     * @return The correct (new) record for this type (or null if none).
     */
    public Record createSharedRecord(Object objKey, RecordOwner recordOwner)
    {
        if (objKey instanceof Integer)
        {
            int iProductType = ((Integer)objKey).intValue();
            if (iProductType == ProductType.HOTEL_ID)
                return new TourHeaderHotel(recordOwner);
            if (iProductType == ProductType.LAND_ID)
                return new TourHeaderLand(recordOwner);
            if (iProductType == ProductType.AIR_ID)
                return new TourHeaderAir(recordOwner);
            if (iProductType == ProductType.CAR_ID)
                return new TourHeaderCar(recordOwner);
            if (iProductType == ProductType.CRUISE_ID)
                return new TourHeaderCruise(recordOwner);
            if (iProductType == ProductType.ITEM_ID)
                return new TourHeaderItem(recordOwner);
            if (iProductType == ProductType.TOUR_ID)
                return new TourHeaderTour(recordOwner);
            if (iProductType == ProductType.TRANSPORTATION_ID)
                return new TourHeaderTransportation(recordOwner);
        }
        return null;
    }

}

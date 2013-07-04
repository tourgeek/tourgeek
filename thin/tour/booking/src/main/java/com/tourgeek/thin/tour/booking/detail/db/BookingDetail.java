
package com.tourgeek.thin.tour.booking.detail.db;

import java.util.*;
import org.jbundle.model.message.*;
import com.tourgeek.model.tour.product.base.db.*;
import com.tourgeek.model.tour.booking.db.*;
import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

import com.tourgeek.thin.tour.booking.detail.db.*;
import com.tourgeek.model.tour.booking.detail.db.*;

public class BookingDetail extends BookingSub
    implements BookingDetailModel
{
    private static final long serialVersionUID = 1L;


    public BookingDetail()
    {
        super();
    }
    public BookingDetail(Object recordOwner)
    {
        this();
        this.init(recordOwner);
    }
    public static final String BOOKING_DETAIL_FILE = "BookingDetail";
    /**
     *  Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? BookingDetail.BOOKING_DETAIL_FILE : super.getTableNames(bAddQuotes);
    }
    /**
     *  Get the Database Name.
     */
    public String getDatabaseName()
    {
        return "booking";
    }
    /**
     *  Is this a local (vs remote) file?.
     */
    public int getDatabaseType()
    {
        return Constants.REMOTE | Constants.BASE_TABLE_CLASS | Constants.SHARED_TABLE | Constants.USER_DATA;
    }
    /**
    * Set up the screen input fields.
    */
    public void setupFields()
    {
        FieldInfo field = null;
        field = new FieldInfo(this, ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field.setHidden(true);
        field = new FieldInfo(this, LAST_CHANGED, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Date.class);
        field.setHidden(true);
        field = new FieldInfo(this, DELETED, 10, null, new Boolean(false));
        field.setDataClass(Boolean.class);
        field.setHidden(true);
        field = new FieldInfo(this, BOOKING_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, BOOKING_PAX_ID, Constants.DEFAULT_FIELD_LENGTH, null, new Integer(0));
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, MODULE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, TOUR_HEADER_DETAIL_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, TOUR_HEADER_OPTION_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, MODULE_START_DATE, 25, null, null);
        field.setDataClass(Date.class);
        field = new FieldInfo(this, DESCRIPTION, 60, null, null);
        //field = new FieldInfo(this, PRODUCT_TYPE, 15, null, null);
        field = new FieldInfo(this, REMOTE_REFERENCE_NO, 60, null, null);
        field = new FieldInfo(this, PRODUCT_TYPE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, DETAIL_DATE, 25, null, null);
        field.setDataClass(Date.class);
        field = new FieldInfo(this, GMT_DATE, 25, null, null);
        field.setDataClass(Date.class);
        field = new FieldInfo(this, GMT_OFFSET, 18, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, PRODUCT_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, RATE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, CLASS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, MARKUP_FROM_LAST, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, VENDOR_ID, 8, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, TOUR_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, AP_TRX_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, PRICING_DETAIL_KEY, 128, null, null);
        field = new FieldInfo(this, TOTAL_COST, 18, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, EXCHANGE, 10, null, null);
        field.setDataClass(Double.class);
        field.setScale(-1);
        //field = new FieldInfo(this, CURRENCY_CODE, 3, null, null);
        field = new FieldInfo(this, TOTAL_COST_LOCAL, 18, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, TOTAL_PRICE_LOCAL, 18, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, INFO_MESSAGE_TRANSPORT_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, INFO_STATUS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, INFO_REQUEST_KEY, 128, null, null);
        //field = new FieldInfo(this, INFO_STATUS_REQUEST, 10, null, null);
        //field.setDataClass(Boolean.class);
        field = new FieldInfo(this, COST_MESSAGE_TRANSPORT_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, COST_STATUS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, COST_REQUEST_KEY, 128, null, null);
        //field = new FieldInfo(this, COST_STATUS_REQUEST, 10, null, null);
        //field.setDataClass(Boolean.class);
        field = new FieldInfo(this, INVENTORY_MESSAGE_TRANSPORT_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, INVENTORY_STATUS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, INVENTORY_REQUEST_KEY, 128, null, null);
        //field = new FieldInfo(this, INVENTORY_STATUS_REQUEST, 10, null, null);
        //field.setDataClass(Boolean.class);
        field = new FieldInfo(this, PRODUCT_MESSAGE_TRANSPORT_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, PRODUCT_STATUS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, PRODUCT_REQUEST_KEY, 128, null, null);
        //field = new FieldInfo(this, PRODUCT_STATUS_REQUEST, 10, null, null);
        //field.setDataClass(Boolean.class);
        field = new FieldInfo(this, REMOTE_BOOKING_NO, 127, null, null);
        field = new FieldInfo(this, ACK_DAYS, 4, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, DETAIL_END_DATE, 25, null, null);
        field.setDataClass(Date.class);
        field = new FieldInfo(this, GMT_END_DATE, 25, null, null);
        field.setDataClass(Date.class);
        //field = new FieldInfo(this, MEAL_SUMMARY, 255, null, null);
        //field = new FieldInfo(this, STATUS_SUMMARY, 20, null, null);
        //field.setDataClass(Integer.class);
        field = new FieldInfo(this, ITINERARY_DESC, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field = new FieldInfo(this, PROPERTIES, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field = new FieldInfo(this, ERROR_PROPERTIES, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field = new FieldInfo(this, PP_COST, 18, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, PP_PRICE_LOCAL, 18, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, NIGHTS, 2, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, MEAL_PLAN_1ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, MEAL_PLAN_1_QTY, 2, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, MEAL_PLAN_1_DAYS, 9, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, MEAL_PLAN_2ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, MEAL_PLAN_2_QTY, 2, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, MEAL_PLAN_2_DAYS, 9, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, MEAL_PLAN_3ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, MEAL_PLAN_3_QTY, 2, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, MEAL_PLAN_3_DAYS, 9, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, MEAL_PLAN_4ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, MEAL_PLAN_4_QTY, 2, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, MEAL_PLAN_4_DAYS, 9, null, null);
        field.setDataClass(Short.class);
        //field = new FieldInfo(this, SINGLE_PAX, 4, null, null);
        //field.setDataClass(Short.class);
        //field = new FieldInfo(this, DOUBLE_PAX, 2, null, null);
        //field.setDataClass(Short.class);
        //field = new FieldInfo(this, TRIPLE_PAX, 2, null, null);
        //field.setDataClass(Short.class);
        //field = new FieldInfo(this, QUAD_PAX, 2, null, null);
        //field.setDataClass(Short.class);
        field = new FieldInfo(this, CHILDREN, 5, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, SINGLE_COST, 18, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, DOUBLE_COST, 18, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, TRIPLE_COST, 18, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, QUAD_COST, 18, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, CHILDREN_COST, 18, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, ROOM_COST, 18, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, MEAL_COST, 18, null, null);
        field.setDataClass(Double.class);
        //field = new FieldInfo(this, ROOM_COST_LOCAL, 18, null, null);
        //field.setDataClass(Double.class);
        //field = new FieldInfo(this, MEAL_COST_LOCAL, 18, null, null);
        //field.setDataClass(Double.class);
        field = new FieldInfo(this, VARIES_CODE, 1, null, "");
        field = new FieldInfo(this, VARIES_QTY, 2, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, VARIES_COST, 9, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, PMC_CUTOFF, 3, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, PMC_COST, 18, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, SIC_COST, 18, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, BOOKING_AIR_HEADER_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, GMT_TIME, 10, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.TIME_ONLY);
        field = new FieldInfo(this, ETD, 10, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.TIME_ONLY);
        field = new FieldInfo(this, XO, 1, null, null);
        field = new FieldInfo(this, CITY_CODE, 3, null, null);
        field = new FieldInfo(this, CITY_DESC, 17, null, null);
        field = new FieldInfo(this, AIRLINE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, CARRIER, 2, null, null);
        field = new FieldInfo(this, FLIGHT_NO, 4, null, null);
        field = new FieldInfo(this, FLIGHT_CLASS, 1, null, null);
        field = new FieldInfo(this, TO_CITY_CODE, 3, null, null);
        field = new FieldInfo(this, TO_CITY_DESC, 17, null, null);
        field = new FieldInfo(this, ARRIVE_TIME, 10, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.TIME_ONLY);
        field = new FieldInfo(this, ADD_DAYS, 2, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, ARC_STATUS, 2, null, "OK");
        field = new FieldInfo(this, FARE_BASIS, 15, null, null);
        field = new FieldInfo(this, START_DATE, 5, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, END_DATE, 5, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, ALLOW, 3, null, null);
        field = new FieldInfo(this, DET_FARE, 10, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, SEGMENT_CONF_NO, 128, null, null);
        field = new FieldInfo(this, SEGMENT_CONFIRMED_BY, 50, null, null);
        field = new FieldInfo(this, COUPON, 1, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, SEAT, 5, null, null);
        field = new FieldInfo(this, GATE, 5, null, null);
        field = new FieldInfo(this, SEAT_PREF, 1, null, null);
        field = new FieldInfo(this, SMOKING, 1, null, null);
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, MEALS, 2, null, null);
        field = new FieldInfo(this, DAYS, 8, null, new Float(1));
        field.setDataClass(Float.class);
        field = new FieldInfo(this, QUANTITY, 5, null, new Short((short)1));
        field.setDataClass(Short.class);
        field = new FieldInfo(this, ASK_FOR_ANSWER, 10, null, new Boolean(false));
        field.setDataClass(Boolean.class);
        //field = new FieldInfo(this, ALWAYS_RESOLVE, 10, null, new Boolean(false));
        //field.setDataClass(Boolean.class);
        field = new FieldInfo(this, PRICING_TYPE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
    }
    /**
    * Set up the key areas.
    */
    public void setupKeys()
    {
        KeyAreaInfo keyArea = null;
        keyArea = new KeyAreaInfo(this, Constants.UNIQUE, ID_KEY);
        keyArea.addKeyField(ID, Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, BOOKING_ID_KEY);
        keyArea.addKeyField(BOOKING_ID, Constants.ASCENDING);
        keyArea.addKeyField(BOOKING_PAX_ID, Constants.ASCENDING);
        keyArea.addKeyField(DETAIL_DATE, Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, DETAIL_ACCESS_KEY);
        keyArea.addKeyField(BOOKING_ID, Constants.ASCENDING);
        keyArea.addKeyField(BOOKING_PAX_ID, Constants.ASCENDING);
        keyArea.addKeyField(MODULE_ID, Constants.ASCENDING);
        keyArea.addKeyField(TOUR_HEADER_DETAIL_ID, Constants.ASCENDING);
        keyArea.addKeyField(MODULE_START_DATE, Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, PRODUCT_ID_KEY);
        keyArea.addKeyField(PRODUCT_TYPE_ID, Constants.ASCENDING);
        keyArea.addKeyField(PRODUCT_ID, Constants.ASCENDING);
        keyArea.addKeyField(DETAIL_DATE, Constants.ASCENDING);
        keyArea.addKeyField(BOOKING_ID, Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, AP_TRX_ID_KEY);
        keyArea.addKeyField(AP_TRX_ID, Constants.ASCENDING);
        keyArea.addKeyField(DETAIL_DATE, Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, TOUR_ID_KEY);
        keyArea.addKeyField(TOUR_ID, Constants.ASCENDING);
        keyArea.addKeyField(VENDOR_ID, Constants.ASCENDING);
        keyArea.addKeyField(PRODUCT_TYPE_ID, Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, DETAIL_DATE_KEY);
        keyArea.addKeyField(DETAIL_DATE, Constants.ASCENDING);
        keyArea.addKeyField(PRODUCT_TYPE_ID, Constants.ASCENDING);
        keyArea.addKeyField(PRODUCT_ID, Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, VENDOR_ID_KEY);
        keyArea.addKeyField(VENDOR_ID, Constants.ASCENDING);
        keyArea.addKeyField(DETAIL_DATE, Constants.ASCENDING);
    }
    /**
     * No thin implementation.
     */
    public short getNoPax()
    {
        return -1;
    }
    /**
     * No thin implementation.
     */
    public int updateBookingLine(BookingLineModel recBookingLine, int iPricingType, int iPaxCategory,  int iQuantity, double dAmount, boolean bCommissionable, double dCommissionRate, String
     strPayAt, int iPricingStatusID, int iChangeType)
    {
        return -1;
    }
    /**
     * Get the product for this detail.
     */
    public ProductModel getProduct()
    {
        return null; // Empty implementation
    }
    /**
     * Get the start date and time for this product.
     * Return null if there is no date and time.
     */
    public Date getStartDate()
    {
        return null; // Empty implementation
    }
    /**
     * Get the end product date and time.
     * @return The date.
     */
    public Date getEndDate()
    {
        return null; // Empty implementation
    }
    /**
     * Get the description of the product for this line item.
     * Usually, you just get the description of the current product.
     * For manual lines, the manual description is returned.
     */
    public String getProductDesc()
    {
        return null; // Empty implementation
    }
    /**
     * From the current detail and product info,
     * setup the product description.
     * @return The product description (using the product).
     */
    public String setupProductDesc()
    {
        return null; // Empty implementation
    }
    /**
     * How many of this type of passenger (ie., single, double, etc.) are in this type of room?.
     */
    public int getPaxInRoom(int iRoomType)
    {
        return -1; // Empty implementation
    }
    /**
     * Pre-check to see if the minimal required params are set.
     * @return If okay, return 0, otherwise return the field that is required.
     */
    public String checkRequiredParams(String iStatusType)
    {
        return null; // Empty implementation
    }
    /**
     * Lookup the message for this status update.
     * If no update is required, return a null message.
     * @return The message to process (or null if no message).
     */
    public MessageDataParent checkMessageRequired(String iStatusType)
    {
        return null; // Empty implementation
    }
    /**
     * GetErrorMessage Method.
     */
    public String getErrorMessage(String iStatusType)
    {
        return null; // Empty implementation
    }
    /**
     * Set the error message in this record for this message type.
     */
    public void setErrorMessage(MessageDataParent messageData, String strError)
    {
        // Empty implementation
    }
    /**
     * Set the error message in this record for this status type.
     */
    public void setErrorMessage(String iStatusType, String strError)
    {
        // Empty implementation
    }
    /**
     * Add any message properties that are set in this record.
     */
    public void addMessageProperties(String strPrefix, boolean bDeleteProperties, MessageHeader messageHeader, Message message, String strNewPrefix)
    {
        // Empty implementation
    }

}

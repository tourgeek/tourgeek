
package com.tourgeek.tour.product.base.db;

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
import org.jbundle.thin.base.message.*;
import com.tourgeek.tour.product.land.db.*;
import com.tourgeek.tour.message.base.request.*;
import com.tourgeek.tour.message.base.request.data.*;
import com.tourgeek.model.tour.booking.detail.db.*;
import com.tourgeek.tour.base.db.*;
import com.tourgeek.tour.acctpay.db.*;
import com.tourgeek.tour.product.base.search.db.*;

/**
 *  ProductScreenRecord - Screen Record for Hotel search variables.
 */
public class ProductScreenRecord extends ScreenRecord
{
    private static final long serialVersionUID = 1L;

    public static final String PRODUCT_ID = "ProductID";
    public static final String START_DATE = "StartDate";
    public static final String END_DATE = "EndDate";
    public static final String DESCRIPTION = "Description";
    public static final String CITY_ID = "CityID";
    public static final String TO_CITY_ID = "ToCityID";
    public static final String CONTINENT_ID = "ContinentID";
    public static final String REGION_ID = "RegionID";
    public static final String COUNTRY_ID = "CountryID";
    public static final String STATE_ID = "StateID";
    public static final String VENDOR_ID = "VendorID";
    public static final String RATE_ID = "RateID";
    public static final String CLASS_ID = "ClassID";
    public static final String DETAIL_DATE = "DetailDate";
    public static final String PAX = "Pax";
    public static final String LAST_CHANGED = "LastChanged";
    public static final String REMOTE_QUERY_ENABLED = "RemoteQueryEnabled";
    public static final String BLOCKED = "Blocked";
    public static final String OVERSELL = "Oversell";
    public static final String CLOSED = "Closed";
    public static final String DELETE = "Delete";
    public static final String READ_ONLY = "ReadOnly";
    public static final String PRODUCT_SEARCH_TYPE_ID = "ProductSearchTypeID";
    public static final String PRODUCT_TYPE_ID = "ProductTypeID";
    /**
     * Default constructor.
     */
    public ProductScreenRecord()
    {
        super();
    }
    /**
     * Constructor.
     */
    public ProductScreenRecord(RecordOwner screen)
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

    public static final String PRODUCT_SCREEN_RECORD_FILE = null; // Screen field
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        if (iFieldSeq == 0)
            field = new ProductField(this, PRODUCT_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 1)
            field = new DateField(this, START_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 2)
            field = new DateField(this, END_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 3)
            field = new StringField(this, DESCRIPTION, 10, null, null);
        if (iFieldSeq == 4)
            field = new CityField(this, CITY_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 5)
            field = new CityField(this, TO_CITY_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 6)
            field = new ContinentField(this, CONTINENT_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 7)
            field = new RegionField(this, REGION_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 8)
            field = new CountryField(this, COUNTRY_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 9)
            field = new StateField(this, STATE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 10)
            field = new VendorField(this, VENDOR_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 11)
            field = new BaseRateField(this, RATE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 12)
            field = new BaseClassField(this, CLASS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 13)
            field = new DateField(this, DETAIL_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 14)
            field = new ShortField(this, PAX, Constants.DEFAULT_FIELD_LENGTH, null, new Short((short)2));
        if (iFieldSeq == 15)
            field = new ProductScreenRecord_LastChanged(this, LAST_CHANGED, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 16)
            field = new BooleanField(this, REMOTE_QUERY_ENABLED, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 17)
            field = new ShortField(this, BLOCKED, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 18)
            field = new ShortField(this, OVERSELL, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 19)
            field = new BooleanField(this, CLOSED, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 20)
            field = new BooleanField(this, DELETE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 21)
            field = new BooleanField(this, READ_ONLY, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 22)
            field = new ProductSearchTypeField(this, PRODUCT_SEARCH_TYPE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 23)
            field = new ProductTypeField(this, PRODUCT_TYPE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
            field = super.setupField(iFieldSeq);
        return field;
    }
    /**
     * Add all standard file & field behaviors.
     * Override this to add record listeners and filters.
     */
    public void addListeners()
    {
        super.addListeners();
        this.getField(ProductScreenRecord.DESCRIPTION).addListener(new FieldToUpperHandler(null));
        this.getField(ProductScreenRecord.DESCRIPTION).addListener(new CheckForTheHandler(null));
        this.addListener(new DateChangedHandler(ProductScreenRecord.LAST_CHANGED));
    }
    /**
     * Set up the properties for a price lookup.
     */
    public void setPriceProperties(BaseMessage message, Record recProduct)
    {
        ProductRequest productRequest = (ProductRequest)message.getMessageDataDesc(null);
        ProductMessageData productMessageData = (ProductMessageData)productRequest.getMessageDataDesc(ProductRequest.PRODUCT_MESSAGE);
        PassengerMessageData passengerMessageData = (PassengerMessageData)productRequest.getMessageDataDesc(ProductRequest.PASSENGER_MESSAGE);
        
        this.addMapProperty(productMessageData, BookingDetailModel.DETAIL_DATE, this, ProductScreenRecord.DETAIL_DATE);
        this.addMapProperty(productMessageData, BookingDetailModel.PRODUCT_ID, recProduct, Product.ID);
        this.addMapProperty(passengerMessageData, Product.PAX_PARAM, this, ProductScreenRecord.PAX);
        if (this.getField(ProductScreenRecord.PAX).getValue() == 2)  // Default
            passengerMessageData.put(Product.ROOM_TYPE_PARAM + Integer.toString(PaxCategory.DOUBLE_ID), new Short((short)2));   // Two pax in a double room
        
        try   {
            productRequest.put(DBConstants.STRING_OBJECT_ID_HANDLE, recProduct.getHandle(DBConstants.OBJECT_ID_HANDLE));
        } catch (DBException ex)    {
            ex.printStackTrace();
        }
        // Land only
        productMessageData.put(Land.SIC_PMC_PARAM, LandClass.PRIVATE_VEHICLE_CODE);
        // Others
        productRequest.put(DBParams.RECORD, recProduct.getTableNames(false));
        productRequest.put(DBParams.TIMESTAMP, Double.toString(this.getField(ProductScreenRecord.LAST_CHANGED).getValue()));
        this.addMapProperty(productMessageData, BookingDetailModel.RATE_ID, this, ProductScreenRecord.RATE_ID);
        this.addMapProperty(productMessageData, BookingDetailModel.CLASS_ID, this, ProductScreenRecord.CLASS_ID);
    }
    /**
     * Is this the correct message for this screen.
     * (Double-check to make sure the user still wants this price information)
     * @return True if the properties are correct.
     */
    public boolean checkPriceProperties(BaseMessage map, Record recProduct)
    {
        Object objData = map.get(DBParams.TIMESTAMP);
        Object objLast = Double.toString(this.getField(ProductScreenRecord.LAST_CHANGED).getValue());
        if (objData != null)
            if (objData.equals(objLast))
                return true;    // Same
        return false;
    }
    /**
     * AddMapProperty Method.
     */
    public void addMapProperty(MessageRecordDesc map, String strKey, Record record, String iFieldSeq)
    {
        if (record.getField(iFieldSeq).getData() != null)
            map.put(strKey, record.getField(iFieldSeq).getData());
    }

}

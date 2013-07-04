
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
import org.jbundle.main.db.base.*;
import com.tourgeek.tour.base.db.*;
import com.tourgeek.model.tour.product.base.db.*;

/**
 *  TransportProduct - A travel product that takes you from one location to another..
 */
public class TransportProduct extends Product
     implements TransportProductModel
{
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor.
     */
    public TransportProduct()
    {
        super();
    }
    /**
     * Constructor.
     */
    public TransportProduct(RecordOwner screen)
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

    public static final String TRANSPORT_PRODUCT_FILE = null; // Screen field
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        //if (iFieldSeq == 0)
        //{
        //  field = new CounterField(this, ID, 8, null, null);
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
        //if (iFieldSeq == 3)
        //  field = new ProductDesc(this, DESCRIPTION, 50, null, null);
        //if (iFieldSeq == 4)
        //  field = new StringField(this, CODE, 10, null, null);
        //if (iFieldSeq == 5)
        //{
        //  field = new VendorField(this, VENDOR_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //  field.addListener(new InitOnceFieldHandler(null));
        //}
        //if (iFieldSeq == 6)
        //  field = new StringField(this, OPERATORS_CODE, 20, null, null);
        //if (iFieldSeq == 7)
        //  field = new ProductChainField(this, PRODUCT_CHAIN_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 8)
        //  field = new CityField(this, CITY_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 9)
        //  field = new TimeField(this, ETD, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 10)
        //  field = new ShortField(this, ACK_DAYS, 2, null, null);
        //if (iFieldSeq == 11)
        //  field = new MemoField(this, COMMENTS, 32767, null, null);
        //if (iFieldSeq == 12)
        //  field = new PropertiesField(this, PROPERTIES, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 13)
        //  field = new XmlField(this, ITINERARY_DESC, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 14)
        //  field = new ProductDescSort(this, DESC_SORT, 10, null, null);
        if (iFieldSeq == 15)
        {
            field = new ProductTypeAutoField(this, PRODUCT_TYPE, 15, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == 16)
        {
            field = new FullCurrencyField(this, PRODUCT_COST, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == 17)
        {
            field = new CurrencyField(this, PRODUCT_COST_LOCAL, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setVirtual(true);
        }
        //if (iFieldSeq == 18)
        //  field = new MessageTransportSelect(this, PRODUCT_MESSAGE_TRANSPORT_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 19)
        {
            field = new InventoryStatusField(this, DISPLAY_INVENTORY_STATUS_ID, Constants.DEFAULT_FIELD_LENGTH, null, new Integer(BaseStatus.NO_STATUS));
            field.setVirtual(true);
        }
        if (iFieldSeq == 20)
        {
            field = new ShortField(this, INVENTORY_AVAILABILITY, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == 21)
        {
            field = new StringField(this, CURRENCY_CODE, 3, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == 22)
        {
            field = new StringField(this, CURRENCY_CODE_LOCAL, 3, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == 23)
        {
            field = new StringField(this, VENDOR_NAME, 30, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == 24)
        {
            field = new CostStatusField(this, DISPLAY_COST_STATUS_ID, Constants.DEFAULT_FIELD_LENGTH, null, new Integer(BaseStatus.NULL_STATUS));
            field.setVirtual(true);
        }
        if (iFieldSeq == 25)
        {
            field = new FullCurrencyField(this, PP_COST, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == 26)
        {
            field = new CurrencyField(this, PP_COST_LOCAL, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == 27)
        {
            field = new BaseRateField(this, RATE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == 28)
        {
            field = new BaseClassField(this, CLASS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == 29)
        {
            field = new CurrencyField(this, PRODUCT_PRICE_LOCAL, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == 30)
        {
            field = new CurrencyField(this, PP_PRICE_LOCAL, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == 31)
        {
            field = new StringField(this, CITY_CODE, 3, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 32)
            field = new CityField(this, TO_CITY_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 33)
        {
            field = new StringField(this, TO_CITY_CODE, 3, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (field == null)
            field = super.setupField(iFieldSeq);
        return field;
    }
    /**
     * Override this to add record listeners and filters to every screen where this is the main record.
     * @param screen The screen these listeners will be in.
     */
    public void addScreenListeners(RecordOwner screen)
    {
        super.addScreenListeners(screen);
        
        Record recCity = ((ReferenceField)this.getField(TransportProduct.CITY_ID)).getReferenceRecord();
        recCity.getField(City.CITY_CODE).addListener(new MainReadOnlyHandler(City.CITY_CODE_KEY));
        this.getField(TransportProduct.CITY_ID).addListener(new MoveOnChangeHandler(this.getField(TransportProduct.CITY_CODE), recCity.getField(City.CITY_CODE)));
        this.getField(TransportProduct.CITY_CODE).addListener(new MoveOnChangeHandler(recCity.getField(City.CITY_CODE), this.getField(TransportProduct.CITY_CODE)));
        
        recCity = ((ReferenceField)this.getField(TransportProduct.TO_CITY_ID)).getReferenceRecord();
        recCity.getField(City.CITY_CODE).addListener(new MainReadOnlyHandler(City.CITY_CODE_KEY));
        this.getField(TransportProduct.TO_CITY_ID).addListener(new MoveOnChangeHandler(this.getField(TransportProduct.TO_CITY_CODE), recCity.getField(City.CITY_CODE)));
        this.getField(TransportProduct.TO_CITY_CODE).addListener(new MoveOnChangeHandler(recCity.getField(City.CITY_CODE), this.getField(TransportProduct.TO_CITY_CODE)));
    }

}

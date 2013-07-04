
package com.tourgeek.tour.message.hotel.screen;

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
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import com.tourgeek.tour.product.base.db.*;
import com.tourgeek.tour.booking.detail.db.*;
import org.jbundle.thin.base.message.*;
import com.tourgeek.tour.message.base.response.*;
import com.tourgeek.tour.message.base.response.data.*;

/**
 *  HotelRateAvailResponseScreen - Combined Rate and availability screen.
 */
public class HotelRateAvailResponseScreen extends HotelAvailResponseScreen
{
    /**
     * Default constructor.
     */
    public HotelRateAvailResponseScreen()
    {
        super();
    }
    /**
     * Constructor.
     * @param record The main record for this screen.
     * @param itsLocation The location of this component within the parent.
     * @param parentScreen The parent screen.
     * @param fieldConverter The field this screen field is linked to.
     * @param iDisplayFieldDesc Do I display the field desc?
     * @param properties Addition properties to pass to the screen.
     */
    public HotelRateAvailResponseScreen(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
    {
        this();
        this.init(record, itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, properties);
    }
    /**
     * Initialize class fields.
     */
    public void init(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
    {
        super.init(record, itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, properties);
    }
    /**
     * Get the screen display title.
     */
    public String getTitle()
    {
        return "Combined Rate and availability screen";
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        
        this.getScreenRecord().getField(HotelInfoScreenRecord.TOTAL_COST).setEnabled(true);
    }
    /**
     * Set up all the screen fields.
     */
    public void setupSFields()
    {
        super.setupSFields();
        this.getRecord(HotelInfoScreenRecord.HOTEL_INFO_SCREEN_RECORD_FILE).getField(HotelInfoScreenRecord.TOTAL_COST).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
    }
    /**
     * Move to entered fields to the return message.
     */
    public void moveScreenParamsToMessage(BaseMessage message)
    {
        super.moveScreenParamsToMessage(message);
        ProductResponseMessageData messageData = (ProductResponseMessageData)message.getMessageDataDesc(BaseProductResponse.PRODUCT_RESPONSE_MESSAGE);
        Double dblHotelCost = (Double)this.getScreenRecord().getField(HotelInfoScreenRecord.TOTAL_COST).getData();
        messageData.put(BookingDetail.TOTAL_COST, dblHotelCost);
    }

}

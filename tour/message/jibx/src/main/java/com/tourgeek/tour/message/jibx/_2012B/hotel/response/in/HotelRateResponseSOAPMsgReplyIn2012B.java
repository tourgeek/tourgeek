
package com.tourgeek.tour.message.jibx._2012B.hotel.response.in;

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
import org.jbundle.base.message.trx.message.external.convert.jibx.*;
import org.jbundle.base.message.trx.message.external.*;
import com.tourgeek.tour.message.hotel.request.*;
import com.tourgeek.tour.product.base.db.*;
import com.tourgeek.tour.booking.detail.db.*;
import org.jibx.schema.org.opentravel._2012B.hotel.*;
import com.tourgeek.tour.message.base.response.*;
import com.tourgeek.tour.message.base.response.data.*;
import org.jbundle.thin.base.message.*;

/**
 *  HotelRateResponseSOAPMsgReplyIn2012B - .
 */
public class HotelRateResponseSOAPMsgReplyIn2012B extends JibxConvertToMessage
{
    /**
     * Default constructor.
     */
    public HotelRateResponseSOAPMsgReplyIn2012B()
    {
        super();
    }
    /**
     * Initialize new BaseTrxMessage.
     * This is used for outgoing EC transactions where you have the jaxb message and you need to convert it.
     * @param objRawMessage The (optional) raw data of the message.
     */
    public HotelRateResponseSOAPMsgReplyIn2012B(ExternalTrxMessageIn message)
    {
        this();
        this.init(message);
    }
    /**
     * Initialize class fields.
     */
    public void init(ExternalTrxMessageIn message)
    {
        super.init(message);
    }
    /**
     * Convert the external form to the internal message form.
     * You must override this method.
     * @param root The root object of the marshallable object.
     * @param recordOwner The recordowner
     * @return The error code.
     */
    public int convertMarshallableObjectToInternal(Object root, RecordOwner recordOwner)
    {
        if (root instanceof AvailRS)
        {
            AvailRS msg = (AvailRS)root;
        
        //        if (GET_HOTEL_RATE.equalsIgnoreCase(strMessageType))
        //        {
        //            properties.put(DESTINATION_MESSAGE_PARAM, "/receiver"); // The URL extension
        
        //            Header header = msg.getHeader();
        //            String strTo = header.getToURI();
        //            String strFrom = header.getFromURI();
        //            String strReplyTo = header.getReplyToURI();
        
        //            Body body = msg.getBody();
        //            HITISOperation operation = body.getHITISOperation();
        //            String strOperationName = operation.getOperationName();    // Need a constant for this
            String strPrice = msg.getOTAPayloadStdAttributes().getEchoToken();
            Double dblPrice = new Double(strPrice);
        //            AvailabilityResponse availability = operation.getAvailabilityResponse();
        //            QuotedRateAmount ratePlan = availability.getQuotedRateAmount();
        //            double dPrice = ratePlan.getCurrency();
        
            ProductRateResponse productResponseData = (ProductRateResponse)this.getMessage().getMessageDataDesc(null);
            ProductResponseMessageData productMessageData = (ProductResponseMessageData)this.getMessage().getMessageDataDesc(BaseProductResponse.PRODUCT_RESPONSE_MESSAGE);
            productMessageData.put(BookingDetail.TOTAL_COST, dblPrice);
            if (dblPrice != null)
                if (dblPrice != 0)
                    productResponseData.setMessageDataStatus(MessageDataDesc.VALID);
        }
        return DBConstants.NORMAL_RETURN;
    }

}

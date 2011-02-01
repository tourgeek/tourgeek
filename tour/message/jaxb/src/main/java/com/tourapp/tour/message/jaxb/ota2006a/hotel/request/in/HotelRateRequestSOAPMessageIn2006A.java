/**
 *  @(#)HotelRateRequestSOAPMessageIn2006A.
 *  Copyright © 2010 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.message.jaxb.ota2006a.hotel.request.in;

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
import org.jbundle.base.message.trx.message.external.convert.jaxb.*;
import net.sourceforge.ota_tools.jaxb.ota2006a.custom.*;
import org.jbundle.base.message.trx.message.*;
import javax.xml.soap.*;
import org.jbundle.thin.base.message.*;
import com.tourapp.tour.product.base.db.*;
import com.tourapp.tour.product.hotel.db.*;
import com.tourapp.tour.message.hotel.request.*;
import com.tourapp.tour.message.base.request.data.*;
import com.tourapp.tour.booking.detail.db.*;
import org.jbundle.base.message.trx.message.external.*;

/**
 *  HotelRateRequestSOAPMessageIn2006A - .
 */
public class HotelRateRequestSOAPMessageIn2006A extends JaxbConvertToMessage
{
    /**
     * Default constructor.
     */
    public HotelRateRequestSOAPMessageIn2006A()
    {
        super();
    }
    /**
     * Initialize new BaseTrxMessage.
     * This is used for outgoing EC transactions where you have the jaxb message and you need to convert it.
     * @param objRawMessage The (optional) raw data of the message.
     */
    public HotelRateRequestSOAPMessageIn2006A(ExternalTrxMessageIn message)
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
        if (root instanceof OTA_HotelAvailRQ)
        {       // Always
            OTA_HotelAvailRQ msg = (OTA_HotelAvailRQ)root;
        
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
        //            AvailabilityQuery availability = operation.getAvailabilityQuery();
            AvailRequestSegmentsType segments = msg.getAvailRequestSegments();
        
            java.util.List listSegments = segments.getAvailRequestSegment();
            AvailRequestSegmentsType.AvailRequestSegment segType = null;
            segType = (AvailRequestSegmentsType.AvailRequestSegment)listSegments.get(0);
        
            DateTimeSpanType dateTime = segType.getStayDateRange();
            Date dateStart = this.dateStringToDateFormat(dateTime.getStart());
        
            Date dateEnd = this.dateStringToDateFormat(dateTime.getEnd());
            long lmsChange = dateEnd.getTime() - dateStart.getTime();
            long lDuration = (lmsChange + 1000) / DBConstants.KMS_IN_A_DAY;
        
            RatePlanCandidatesType ratePlanCans = segType.getRatePlanCandidates();
            java.util.List listRatePlan = ratePlanCans.getRatePlanCandidate();
            RatePlanCandidatesType.RatePlanCandidate ratePlan = null;
            ratePlan = (RatePlanCandidatesType.RatePlanCandidate)listRatePlan.get(0);
            String strRatePlan = ratePlan.getRatePlanCode();
        
            String strHotelName = ratePlan.getPromotionCode();    // NO NO NO
        
            AvailRequestSegmentsType.AvailRequestSegment.RoomStayCandidates roomStayCands = segType.getRoomStayCandidates();
            java.util.List list = roomStayCands.getRoomStayCandidate();
            RoomStayCandidateType roomStayCand = null;
            roomStayCand = (RoomStayCandidateType)list.get(0);
        //            if (roomStays.length() > 3)
        //                roomStays = roomStays.substring(0, 3);  // Restriction on length!
            String roomStays = roomStayCand.getRoomCategory();
        
        //            properties.put(TrxMessageHeader.DESTINATION_PARAM, strTo);
        //            properties.put(TrxMessageHeader.SOURCE_PARAM, strFrom);
        //            properties.put(TrxMessageHeader.REPLY_TO_PARAM, strReplyTo);
        //x    HotelRateRequest hotelRateRequestIn = new HotelRateRequest(null, null);
        //x    BaseMessage messageIn = new TreeMessage(null, hotelRateRequestIn);
            ProductMessageData messageData = (ProductMessageData)this.getMessage().getMessageDataDesc(HotelRateRequest.PRODUCT_MESSAGE);
            messageData.put(BookingDetail.RATE_ID, strRatePlan);
            messageData.put(BookingDetail.CLASS_ID, roomStays);
            messageData.put(BookingDetail.DETAIL_DATE, dateStart);
            messageData.put(BookingHotel.NIGHTS, new Long(lDuration));
            if (!Utility.isNumeric(strHotelName))
                messageData.put(Product.CODE, strHotelName);
            else
                messageData.put(BookingDetail.PRODUCT_ID, strHotelName);
        //+ Fix this next line!
            PassengerMessageData passengerMessageData = (PassengerMessageData)this.getMessage().getMessageDataDesc(HotelRateRequest.PASSENGER_MESSAGE);
            int iRoomCategory = 2;
            passengerMessageData.put(Product.ROOM_TYPE_PARAM + Integer.toString(iRoomCategory), new Short((short)2));  // Twin ROOM HACK
        }
        return DBConstants.NORMAL_RETURN;
    }

}

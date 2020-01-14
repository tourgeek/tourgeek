/**
  * @(#)HotelRateRequestSOAPMessageIn2012B.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.tour.message.jibx._2014B.hotel.request.in;

import java.util.*;

import org.jbundle.base.model.*;
import com.tourgeek.tour.message.jibx._2014B.base.request.in.*;
import org.jbundle.base.message.trx.message.external.*;
import org.jibx.schema.org.opentravel._2014B.hotel.*;
import org.jibx.schema.org.opentravel._2014B.base.*;
import org.jibx.schema.org.opentravel._2014B.base.profilehotelres.*;
import com.tourgeek.tour.message.base.request.data.*;
import com.tourgeek.tour.booking.detail.db.*;
import com.tourgeek.tour.product.base.db.*;
import com.tourgeek.tour.message.hotel.request.*;

/**
 *  HotelRateRequestSOAPMessageIn2012B - .
 */
public class HotelRateRequestSOAPMessageIn2014B extends BaseJibxMessageIn2014B
{
    /**
     * Default constructor.
     */
    public HotelRateRequestSOAPMessageIn2014B()
    {
        super();
    }
    /**
     * Initialize new BaseTrxMessage.
     * This is used for outgoing EC transactions where you have the jaxb message and you need to convert it.
     * @param objRawMessage The (optional) raw data of the message.
     */
    public HotelRateRequestSOAPMessageIn2014B(ExternalTrxMessageIn message)
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
        if (root instanceof AvailRQ)
        {       // Always
            AvailRQ msg = (AvailRQ)root;
        
            AvailRequestSegments segments = msg.getAvailRequestSegmentsAvailRequestSegments();
            java.util.List<AvailRequestSegments.AvailRequestSegment> listSegments = segments.getAvailRequestSegmentList();
            AvailRequestSegments.AvailRequestSegment segType = listSegments.get(0);
            
            DateTimeSpan dateTimeSpan = segType.getStayDateRange();
            DateTimeSpanGroup dateTime = dateTimeSpan.getDateTimeSpanGroup();
            Date dateStart = this.dateStringToDateFormat(dateTime.getStart());
        
            Date dateEnd = this.dateStringToDateFormat(dateTime.getEnd());
            long lmsChange = dateEnd.getTime() - dateStart.getTime();
            long lDuration = (lmsChange + 1000) / DBConstants.KMS_IN_A_DAY;
        
            RatePlanCandidates ratePlanCans = segType.getRatePlanCandidates();
            RatePlanCandidates.RatePlanCandidate ratePlanCandidate = ratePlanCans.getRatePlanCandidate(0);
            RatePlanGroup ratePlanGroup = ratePlanCandidate.getRatePlanGroup();
            String strRatePlan = ratePlanGroup.getRatePlanCode();
            String strHotelName = ratePlanGroup.getPromotionCode();
        
            AvailRequestSegments.AvailRequestSegment.RoomStayCandidates roomStayCands = segType.getRoomStayCandidates();
            RoomStayCandidate roomStayCand = roomStayCands.getRoomStayCandidate(0);
            String roomStays = roomStayCand.getRoomGroup().getRoomCategory();
        
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

/**
 *  @(#)HotelRateRequestSOAPMessageIn2004B.
 *  Copyright © 2010 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.message.jaxb.ota2004b.hotel.request.in;

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
import org.jbundle.thin.base.message.*;
import javax.xml.soap.*;
import net.sourceforge.ota_tools.jaxb.ota2004b.custom.*;
import org.jbundle.base.message.trx.message.external.*;
import com.tourapp.tour.message.base.request.data.*;
import com.tourapp.tour.product.base.db.*;
import com.tourapp.tour.booking.detail.db.*;
import com.tourapp.tour.message.hotel.request.*;

/**
 *  HotelRateRequestSOAPMessageIn2004B - .
 */
public class HotelRateRequestSOAPMessageIn2004B extends JaxbConvertToMessage
{
    /**
     * Default constructor.
     */
    public HotelRateRequestSOAPMessageIn2004B()
    {
        super();
    }
    /**
     * Initialize new BaseTrxMessage.
     * This is used for outgoing EC transactions where you have the jaxb message and you need to convert it.
     * @param objRawMessage The (optional) raw data of the message.
     */
    public HotelRateRequestSOAPMessageIn2004B(ExternalTrxMessageIn message)
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
        if (root instanceof OTAHotelAvailRQ)
        {       // Always
            OTAHotelAvailRQ msg = (OTAHotelAvailRQ)root;
        
            AvailRequestSegmentsType segments = msg.getAvailRequestSegments();
        
            java.util.List<AvailRequestSegmentsType.AvailRequestSegment> listSegments = segments.getAvailRequestSegment();
            for (AvailRequestSegmentsType.AvailRequestSegment segType : listSegments)
            {
                if (!"Room".equalsIgnoreCase(segType.getAvailReqType()))
                    continue;
                DateTimeSpanType dateTime = segType.getStayDateRange();
                Date dateStart = this.dateStringToDateFormat(dateTime.getStart());
        
                Date dateEnd = this.dateStringToDateFormat(dateTime.getEnd());
                long lmsChange = dateEnd.getTime() - dateStart.getTime();
                long lDuration = (lmsChange + 1000) / DBConstants.KMS_IN_A_DAY;
            
                String adultCode = this.getOTACode("AQC", "Adult", "10");
                int pax = 0;
                int rooms = 0;
                String roomStays = null;
                String strRatePlan = null;
                AvailRequestSegmentsType.AvailRequestSegment.RoomStayCandidates roomStayCands = segType.getRoomStayCandidates();
                if (roomStayCands != null)
                {
                    java.util.List<RoomStayCandidateType> list = roomStayCands.getRoomStayCandidate();
                    for (RoomStayCandidateType roomStayCand : list)
                    {
                        rooms = roomStayCand.getQuantity();
                        GuestCountType guestCountType = roomStayCand.getGuestCounts();
                        java.util.List<GuestCountType.GuestCount> guestCounts = guestCountType.getGuestCount();
                        for (GuestCountType.GuestCount guestCount : guestCounts)
                        {
                            String ageCode = guestCount.getAgeQualifyingCode();
                            pax = pax + guestCount.getCount();
                        }
                        roomStays = roomStayCand.getRoomCategory();
                    }
                }
                //+ Fix this next line!
                PassengerMessageData passengerMessageData = (PassengerMessageData)this.getMessage().getMessageDataDesc(HotelRateRequest.PASSENGER_MESSAGE);
                int iRoomCategory = 2;
                passengerMessageData.put(Product.ROOM_TYPE_PARAM + Integer.toString(iRoomCategory), new Short((short)pax));  // Twin ROOM HACK
        
                RatePlanCandidatesType ratePlanCans = segType.getRatePlanCandidates();
                if (ratePlanCans != null)
                {
                    java.util.List<RatePlanCandidatesType.RatePlanCandidate> listRatePlan = ratePlanCans.getRatePlanCandidate();
                    for (RatePlanCandidatesType.RatePlanCandidate ratePlan : listRatePlan)
                    {
                        strRatePlan = ratePlan.getRatePlanCode();
                    }
                }
        
                ProductMessageData messageData = (ProductMessageData)this.getMessage().getMessageDataDesc(HotelRateRequest.PRODUCT_MESSAGE);
        
                String strHotelID = null;
                HotelSearchCriteriaType hotelSearchCriteriaType = segType.getHotelSearchCriteria();
                if (hotelSearchCriteriaType != null)
                {
                    java.util.List<HotelSearchCriterionType> criteria = hotelSearchCriteriaType.getCriterion();
                    for (HotelSearchCriterionType hotelSearchCriterionType : criteria)
                    {
                        ItemSearchCriterionType.HotelRef hotelRef = hotelSearchCriterionType.getHotelRef();
                        String strChainCode = hotelRef.getChainCode();
                        String strHotelCode = hotelRef.getHotelCode();
                        strHotelID = messageData.getProductID(recordOwner, strChainCode, strHotelCode);
                    }
                }
        
                messageData.put(BookingDetail.RATE_ID, strRatePlan);
                messageData.put(BookingDetail.CLASS_ID, roomStays);
                messageData.put(BookingDetail.DETAIL_DATE, dateStart);
                messageData.put(BookingHotel.NIGHTS, new Long(lDuration));
                messageData.put(BookingDetail.PRODUCT_ID, strHotelID);
            }
        }
        return DBConstants.NORMAL_RETURN;
    }

}

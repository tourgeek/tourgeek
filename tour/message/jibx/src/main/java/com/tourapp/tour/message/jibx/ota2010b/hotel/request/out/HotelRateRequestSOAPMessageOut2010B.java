/**
 * @(#)HotelRateRequestSOAPMessageOut2010B.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.message.jibx.ota2010b.hotel.request.out;

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
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import org.jbundle.base.message.trx.message.external.convert.jibx.*;
import org.jibx.schema.org.opentravel._2010B.base.*;
import com.tourapp.tour.product.base.db.*;
import org.jbundle.base.message.trx.message.*;
import org.jbundle.thin.base.message.*;
import javax.xml.bind.*;
import com.tourapp.tour.product.hotel.db.*;
import com.tourapp.tour.message.hotel.request.*;
import org.jbundle.base.message.trx.transport.soap.*;
import com.tourapp.tour.message.base.request.*;
import com.tourapp.tour.booking.detail.db.*;
import com.tourapp.tour.message.hotel.request.data.*;
import org.jbundle.base.message.trx.message.external.*;
import org.jibx.schema.org.opentravel._2010B.hotel.*;

/**
 *  HotelRateRequestSOAPMessageOut2010B - .
 */
public class HotelRateRequestSOAPMessageOut2010B extends JibxConvertToNative
{
    /**
     * Default constructor.
     */
    public HotelRateRequestSOAPMessageOut2010B()
    {
        super();
    }
    /**
     * This is the base class for a transaction which is sent externally.
     * The two main sub-classes of this class are InternalTrxMessage and ExternalTrxMessage.
     * An InternalTrxMessage is the data I create internally to send to the destination. It
     * usually contains all the relative information needed to send to the destination.
     * An ExternalTrxMessage is the message converted to a format that the receiver can
     * understand (such as ebXML).
     */
    public HotelRateRequestSOAPMessageOut2010B(ExternalTrxMessageOut message)
    {
        this();
        this.init(message);
    }
    /**
     * Initialize class fields.
     */
    public void init(ExternalTrxMessageOut message)
    {
        super.init(message);
    }
    /**
     * Convert this source message to the ECXML format.
     * Typically you do not override this method, you override the getTransformer method
     * to supply a XSLT document to do the conversion.
     * @param recordOwner TODO
     * @param source The source XML document.
     * @return The XML tree that conforms to the ECXML format.
     */
    public Object convertInternalToMarshallableObject(RecordOwner recordOwner)
    {
        BaseMessage message = this.getMessage();
            
        AvailRQ root = new AvailRQ();
        
        OTAPayloadStdAttributes payloadStdAttributes = new OTAPayloadStdAttributes();
        this.setPayloadProperties(message, payloadStdAttributes);
        root.setOTAPayloadStdAttributes(payloadStdAttributes);
        
        HotelRateRequest hotelRateRequestOut = (HotelRateRequest)message.getMessageDataDesc(null);
        HotelMessageData messageData = (HotelMessageData)hotelRateRequestOut.getMessageDataDesc(ProductRequest.PRODUCT_MESSAGE);
        TrxMessageHeader messageHeader = (TrxMessageHeader)message.getMessageHeader();
        String strMessageType = (String)messageHeader.get(TrxMessageHeader.MESSAGE_CODE);
        
        AvailRequestSegments availRequestSegmentsAvailRequestSegments = new AvailRequestSegments();
        root.setAvailRequestSegmentsAvailRequestSegments(availRequestSegmentsAvailRequestSegments);
        
        AvailRequestSegments.AvailRequestSegment availRequestSegment = new AvailRequestSegments.AvailRequestSegment();
        availRequestSegmentsAvailRequestSegments.addAvailRequestSegment(availRequestSegment);
        
        DateTimeSpan dateTime = new DateTimeSpan();
        availRequestSegment.setStayDateRange(dateTime);
        DateTimeSpanGroup dateTimeSpanGroup = new DateTimeSpanGroup();
        dateTime.setDateTimeSpanGroup(dateTimeSpanGroup);
        
        Date date = (Date)messageData.get(BookingDetail.DETAIL_DATE);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        dateTimeSpanGroup.setStart(this.dateToStringDateFormat(cal.getTime()));
        
        cal = Calendar.getInstance();
        cal.setTime(date);
        // The default HotelRateRequest.DURATION_DEFAULT is temporary.
        Short shNights = (Short)messageData.get(BookingHotel.NIGHTS);
        if (shNights == null)
            shNights = HotelMessageData.DURATION_DEFAULT;
        int iDuration = shNights.intValue(); 
        cal.add(Calendar.DATE, iDuration);
        dateTimeSpanGroup.setEnd(this.dateToStringDateFormat(cal.getTime()));
        //?            dateTime.setDuration(strDuration);
        
        //        header.setToURI(this.getMessageDestination(messageHeader));
        //        header.setFromURI(messageHeader.getSourceAddress());
        //        header.setReplyToURI(messageHeader.getReplyToAddress());
        //        operation.setOperationName((String)map.get(EXTERNAL_OPERATION));    // Need a constant for this
        RatePlanCandidates ratePlanCans = new RatePlanCandidates();
        availRequestSegment.setRatePlanCandidates(ratePlanCans);
        RatePlanCandidates.RatePlanCandidate ratePlan = new RatePlanCandidates.RatePlanCandidate();
        ratePlanCans.addRatePlanCandidate(ratePlan);
        RatePlanGroup ratePlanGroup = new RatePlanGroup();
        ratePlan.setRatePlanGroup(ratePlanGroup);
        
        Integer intRatePlan = (Integer)messageData.get(BookingDetail.RATE_ID);
        if (intRatePlan != null)
            ratePlanGroup.setRatePlanCode(intRatePlan.toString());
        
        PromotionCodeGroup promotionCodeGroup = new PromotionCodeGroup();
        ratePlanGroup.setPromotionCodeGroup(promotionCodeGroup);
        String strHotelCode = null;
        if (messageData.get(Product.CODE) != null)
            strHotelCode = messageData.get(Product.CODE).toString();
        if ((strHotelCode == null) || (strHotelCode.length() == 0))
            strHotelCode = ((Integer)messageData.get(BookingDetail.PRODUCT_ID)).toString();
        promotionCodeGroup.setPromotionCode(strHotelCode);    // NO NO NO
        
        AvailRequestSegments.AvailRequestSegment.RoomStayCandidates roomStayCandidates = new AvailRequestSegments.AvailRequestSegment.RoomStayCandidates();
        availRequestSegment.setRoomStayCandidates(roomStayCandidates);
        AvailRequestSegments.AvailRequestSegment.RoomStayCandidates.RoomStayCandidate roomStayCandidate = new AvailRequestSegments.AvailRequestSegment.RoomStayCandidates.RoomStayCandidate();
        roomStayCandidates.addRoomStayCandidate(roomStayCandidate);
        RoomGroup roomGroup = new RoomGroup();
        roomStayCandidate.setRoomGroup(roomGroup);
        
        Integer intRoomStays = (Integer)messageData.get(BookingDetail.CLASS_ID);
        String roomStays = DBConstants.BLANK;
        if (intRoomStays != null)
            roomStays = intRoomStays.toString();
        // NEXT LINES are LAME LAME LAME!
        if (roomStays.length() > 3)
            roomStays = roomStays.substring(0, 3);  // Restriction on length!
        roomStays = roomStays.toUpperCase();
        roomGroup.setRoomCategory(roomStays);
        // create a Marshaller and marshal to System.out
        //            Marshaller m = jc.createMarshaller();
        //            m.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE );
        //            m.marshal( po, System.out );
        return root;
    }

}

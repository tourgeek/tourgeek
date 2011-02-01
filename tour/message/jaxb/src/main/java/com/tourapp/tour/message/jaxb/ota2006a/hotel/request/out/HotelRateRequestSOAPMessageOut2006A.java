/**
 *  @(#)HotelRateRequestSOAPMessageOut2006A.
 *  Copyright © 2010 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.message.jaxb.ota2006a.hotel.request.out;

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

/**
 *  HotelRateRequestSOAPMessageOut2006A - .
 */
public class HotelRateRequestSOAPMessageOut2006A extends JaxbConvertToNative
{
    /**
     * Default constructor.
     */
    public HotelRateRequestSOAPMessageOut2006A()
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
    public HotelRateRequestSOAPMessageOut2006A(ExternalTrxMessageOut message)
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
        BaseMessage trxMessage = this.getMessage();
        HotelRateRequest hotelRateRequestOut = (HotelRateRequest)trxMessage.getMessageDataDesc(null);
        HotelMessageData messageData = (HotelMessageData)hotelRateRequestOut.getMessageDataDesc(ProductRequest.PRODUCT_MESSAGE);
        TrxMessageHeader messageHeader = (TrxMessageHeader)trxMessage.getMessageHeader();
        String strMessageType = (String)messageHeader.get(TrxMessageHeader.MESSAGE_CODE);
        //xif (!MESSAGE_CODE.equalsIgnoreCase(strMessageType))
        //x    return null;    // Error, wrong type
        OTA_HotelAvailRQ root = null;
            // create a JAXBContext
            // create an empty PurchaseOrder
            ObjectFactory factory = new ObjectFactory();
            root = factory.createOTA_HotelAvailRQ();
        //            root = new OTAHotelAvailRQ();
            root.setVersion(new java.math.BigDecimal(1.23));
            OTA_HotelAvailRQ.AvailRequestSegments segments = null;
            root.setAvailRequestSegments(segments = factory.createOTA_HotelAvailRQAvailRequestSegments());
        
            java.util.List listSegments = segments.getAvailRequestSegment();
            AvailRequestSegmentsType.AvailRequestSegment segType = null;
            listSegments.add(segType = factory.createAvailRequestSegmentsTypeAvailRequestSegment());
        
            DateTimeSpanType dateTime = null;
            segType.setStayDateRange(dateTime = factory.createDateTimeSpanType());
            Date date = (Date)messageData.get(BookingDetail.DETAIL_DATE);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            dateTime.setStart(this.dateToStringDateFormat(cal.getTime()));
        
            cal = Calendar.getInstance();
            cal.setTime(date);
            // The default HotelRateRequest.DURATION_DEFAULT is temporary.
            Short shNights = (Short)messageData.get(BookingHotel.NIGHTS);
            if (shNights == null)
                shNights = HotelMessageData.DURATION_DEFAULT;
            int iDuration = shNights.intValue(); 
            cal.add(Calendar.DATE, iDuration);
            dateTime.setEnd(this.dateToStringDateFormat(cal.getTime()));
        //?            dateTime.setDuration(strDuration);
        
        //        header.setToURI(this.getMessageDestination(messageHeader));
        //        header.setFromURI(messageHeader.getSourceAddress());
        //        header.setReplyToURI(messageHeader.getReplyToAddress());
        //        operation.setOperationName((String)map.get(EXTERNAL_OPERATION));    // Need a constant for this
            RatePlanCandidatesType ratePlanCans = null;
            segType.setRatePlanCandidates(ratePlanCans = factory.createRatePlanCandidatesType());
            java.util.List listRatePlan = ratePlanCans.getRatePlanCandidate();
            RatePlanCandidatesType.RatePlanCandidate ratePlan = null;
            listRatePlan.add(ratePlan = factory.createRatePlanCandidatesTypeRatePlanCandidate());
            Integer intRatePlan = (Integer)messageData.get(BookingDetail.RATE_ID);
            if (intRatePlan != null)
                ratePlan.setRatePlanCode(intRatePlan.toString());
        
            String strHotelCode = null;
            if (messageData.get(Product.CODE) != null)
                strHotelCode = messageData.get(Product.CODE).toString();
            if ((strHotelCode == null) || (strHotelCode.length() == 0))
                strHotelCode = ((Integer)messageData.get(BookingDetail.PRODUCT_ID)).toString();
            ratePlan.setPromotionCode(strHotelCode);    // NO NO NO
        
            AvailRequestSegmentsType.AvailRequestSegment.RoomStayCandidates roomStayCands = null;
            segType.setRoomStayCandidates(roomStayCands = factory.createAvailRequestSegmentsTypeAvailRequestSegmentRoomStayCandidates());
            java.util.List list = roomStayCands.getRoomStayCandidate();
            RoomStayCandidateType roomStayCand = factory.createRoomStayCandidateType();
            list.add(roomStayCand);
            Integer intRoomStays = (Integer)messageData.get(BookingDetail.CLASS_ID);
            String roomStays = DBConstants.BLANK;
            if (intRoomStays != null)
                roomStays = intRoomStays.toString();
            // NEXT LINES are LAME LAME LAME!
            if (roomStays.length() > 3)
                roomStays = roomStays.substring(0, 3);  // Restriction on length!
            roomStays = roomStays.toUpperCase();
            roomStayCand.setRoomCategory(roomStays);
            // create a Marshaller and marshal to System.out
        //            Marshaller m = jc.createMarshaller();
        //            m.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE );
        //            m.marshal( po, System.out );
        return root;
    }

}

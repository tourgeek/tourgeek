/**
 *  @(#)HotelRateRequestSOAPMessageOut2004B.
 *  Copyright © 2010 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.message.jaxb.ota2004b.hotel.request.out;

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
import net.sourceforge.ota_tools.jaxb.ota2004b.custom.*;
import com.tourapp.tour.message.hotel.request.*;
import org.jbundle.base.message.trx.message.*;
import javax.xml.bind.*;
import org.jbundle.base.message.trx.transport.soap.*;
import com.tourapp.tour.message.hotel.request.data.*;
import com.tourapp.tour.booking.detail.db.*;
import com.tourapp.tour.message.base.request.*;
import org.jbundle.base.message.trx.message.external.*;
import com.tourapp.tour.message.base.request.data.*;
import com.tourapp.tour.product.base.db.*;
import com.tourapp.tour.product.hotel.db.*;

/**
 *  HotelRateRequestSOAPMessageOut2004B - .
 */
public class HotelRateRequestSOAPMessageOut2004B extends JaxbConvertToNative
{
    /**
     * Default constructor.
     */
    public HotelRateRequestSOAPMessageOut2004B()
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
    public HotelRateRequestSOAPMessageOut2004B(ExternalTrxMessageOut message)
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
        String strLogID = (String)messageHeader.get(TrxMessageHeader.LOG_TRX_ID);
        //xif (!MESSAGE_CODE.equalsIgnoreCase(strMessageType))
        //x    return null;    // Error, wrong type
        
        // create an empty HoteAvailRQ
        ObjectFactory factory = new ObjectFactory();
        OTAHotelAvailRQ root = factory.createOTAHotelAvailRQ();
                
        root.setVersion(new java.math.BigDecimal(1.003));
        root.setEchoToken(strLogID);
        
        POSType pos = factory.createPOSType();
        root.setPOS(pos);
        SourceType sourceType = factory.createSourceType();
        pos.getSource().add(sourceType);
        //+ UniqueID_Type uniqueID_Type = factory.createUniqueID_Type();
        //+ sourceType.setRequestorID(uniqueID_Type);
        //+ uniqueID_Type.setType(this.getOTACode("UIT", "Other", "18"));    // Other
        //+ uniqueID_Type.setID(strLogID);
        //+ uniqueID_Type.setURL("http://www.kayak.com");
        
        OTAHotelAvailRQ.AvailRequestSegments segments = factory.createOTAHotelAvailRQAvailRequestSegments();
        root.setAvailRequestSegments(segments);
            
        java.util.List<AvailRequestSegmentsType.AvailRequestSegment> listSegments = segments.getAvailRequestSegment();
        AvailRequestSegmentsType.AvailRequestSegment segType = factory.createAvailRequestSegmentsTypeAvailRequestSegment();
        listSegments.add(segType);
        
        segType.setAvailReqType("Room");
        
        DateTimeSpanType dateTime = factory.createDateTimeSpanType();
        segType.setStayDateRange(dateTime);
        Date date = (Date)messageData.get(BookingDetail.DETAIL_DATE);
        dateTime.setStart(this.dateToStringDateFormat(date));
        
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
            // The default HotelRateRequest.DURATION_DEFAULT is temporary.
        Short shNights = (Short)messageData.get(BookingHotel.NIGHTS);
        if (shNights == null)
            shNights = HotelMessageData.DURATION_DEFAULT;
        int iDuration = shNights.intValue(); 
        cal.add(Calendar.DATE, iDuration);
        dateTime.setEnd(this.dateToStringDateFormat(cal.getTime()));
        
        AvailRequestSegmentsType.AvailRequestSegment.RoomStayCandidates roomStayCands = factory.createAvailRequestSegmentsTypeAvailRequestSegmentRoomStayCandidates();
        segType.setRoomStayCandidates(roomStayCands);
        java.util.List<RoomStayCandidateType> list = roomStayCands.getRoomStayCandidate();
        
        RoomStayCandidateType roomStayCand = factory.createRoomStayCandidateType();
        list.add(roomStayCand);
        PassengerMessageData passengerMessageData = (PassengerMessageData)hotelRateRequestOut.getMessageDataDesc(HotelRateRequest.PASSENGER_MESSAGE);
        
        int rooms = 0;
        int adults = 0;
        int children = 0;
        for (int iRoomCategory = PaxCategory.SINGLE_ID, iFieldSeq = Hotel.kSingleCost, iPriceFieldSeq = Hotel.kSinglePriceLocal; iRoomCategory <= PaxCategory.CHILD_ID; iRoomCategory++, iFieldSeq++, iPriceFieldSeq++)
        {
            int iPaxInRoom = passengerMessageData.getPaxInRoom(iRoomCategory);
            int iRoomCapacity = iRoomCategory;
            if (iRoomCategory == PaxCategory.CHILD_ID)
            {
                iRoomCapacity = 1;
                children = children + iPaxInRoom;
            }
            else
                adults = adults + iPaxInRoom;
            rooms = rooms + (iPaxInRoom / iRoomCapacity);
        }
        roomStayCand.setQuantity(rooms);
        GuestCountType guestCountType = factory.createGuestCountType();
        roomStayCand.setGuestCounts(guestCountType);
        guestCountType.setIsPerRoom(false); // Constant
        
        java.util.List<GuestCountType.GuestCount> guestCounts = guestCountType.getGuestCount();
        GuestCountType.GuestCount guestCount = factory.createGuestCountTypeGuestCount();
        guestCounts.add(guestCount);
        guestCount.setCount(adults + children);
        guestCount.setAgeQualifyingCode(this.getOTACode("AQC", "Adult", "10"));  // Adult
            
        HotelSearchCriteriaType hotelSearchCriteriaType = factory.createHotelSearchCriteriaType();
        segType.setHotelSearchCriteria(hotelSearchCriteriaType);
        java.util.List<HotelSearchCriterionType> criteria = hotelSearchCriteriaType.getCriterion();
        HotelSearchCriterionType hotelSearchCriterionType = factory.createHotelSearchCriterionType();
        criteria.add(hotelSearchCriterionType);
        ItemSearchCriterionType.HotelRef hotelRef = factory.createItemSearchCriterionTypeHotelRef();
        hotelSearchCriterionType.setHotelRef(hotelRef);
            
        ProductMessageData productMessageData = (ProductMessageData)hotelRateRequestOut.getMessageDataDesc(HotelRateRequest.PRODUCT_MESSAGE);
        
        Hotel recHotel = (Hotel)recordOwner.getRecord(Hotel.kHotelFile);
        if (recHotel == null)
            recHotel = new Hotel(recordOwner);
        if (productMessageData.getProduct(recHotel))
        {
            hotelRef.setHotelCode(recHotel.getOperatorsCode());
            hotelRef.setChainCode(recHotel.getChainCode());
        }
        this.setPayloadProperties(trxMessage, root);
           
        return root;
    }

}

/**
 * @(#)HotelRateResponseSOAPMsgReplyOut2010B.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.message.jibx.ota2010b.hotel.response.out;

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
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import org.jbundle.base.message.trx.message.external.convert.jibx.*;
import org.jbundle.base.message.core.trx.*;
import org.jbundle.thin.base.message.*;
import com.tourapp.tour.message.hotel.response.*;
import org.jbundle.base.message.trx.transport.soap.*;
import javax.xml.bind.*;
import javax.xml.datatype.*;
import com.tourapp.tour.product.base.db.*;
import com.tourapp.tour.message.base.response.data.*;
import com.tourapp.tour.booking.detail.db.*;
import com.tourapp.tour.message.base.response.*;
import org.jbundle.base.message.trx.message.external.*;
import org.jibx.schema.org.opentravel._2010B.hotel.*;
import org.jibx.schema.org.opentravel._2010B.base.*;
import org.joda.time.*;

/**
 *  HotelRateResponseSOAPMsgReplyOut2010B - .
 */
public class HotelRateResponseSOAPMsgReplyOut2010B extends JibxConvertToNative
{
    /**
     * Default constructor.
     */
    public HotelRateResponseSOAPMsgReplyOut2010B()
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
    public HotelRateResponseSOAPMsgReplyOut2010B(ExternalTrxMessageOut message)
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
        HotelRateResponse hotelRateResponseOut = (HotelRateResponse)message.getMessageDataDesc(null);
        TrxMessageHeader messageHeader = (TrxMessageHeader)message.getMessageHeader();
        String strMessageType = (String)hotelRateResponseOut.get(TrxMessageHeader.MESSAGE_CODE);
        if (true)//GET_HOTEL_RATE.equalsIgnoreCase(strMessageType))
        {
            AvailRS root = new AvailRS();
        
            OTAPayloadStdAttributes payloadStdAttributes = new OTAPayloadStdAttributes();
            this.setPayloadProperties(message, payloadStdAttributes);
            root.setOTAPayloadStdAttributes(payloadStdAttributes);
            
            POS pos = new POS();
            root.setPOS(pos);
            Source source = new Source();
            pos.addSource(source);
        
        //+        SourceType.RequestorID uniqueId = null;
        //+        source.setRequestorID(uniqueId = factory.createSourceTypeRequestorID());
        //+        uniqueId.setID(messageHeader.get(TrxMessageHeader.LOG_TRX_ID).toString());
        //+        uniqueId.setType("12");
        //                source.setAgentSine("Test Agent");
        
            Success successType = new Success();
            root.setSuccess(successType);
        
        //                ErrorsTypeTypes errorsType = null;
        //                root.setErrors(errorsType = ObjectFactory.createErrorsTypeTypes());
        //            operation.setOperationName(AVAILABILITY_RESPONSE);    // Need a constant for this
        //            AvailabilityResponse availability = new AvailabilityResponse();
        //            operation.setAvailabilityResponse(availability);
        //            QuotedRateAmount rateAmount = new QuotedRateAmount();
        //            availability.setQuotedRateAmount(rateAmount);
            
            AvailRS.RoomStays roomStays = new AvailRS.RoomStays();
            root.setRoomStays(roomStays);
            
            roomStays.setMoreIndicator("No");
            AvailRS.RoomStays.RoomStay roomStay = new AvailRS.RoomStays.RoomStay();
            roomStays.addRoomStay(roomStay);
            
            roomStay.setIsAlternate(false);
            /*
            org.w3._2001.xmlschema.ObjectFactory w3Factory = new org.w3._2001.xmlschema.ObjectFactory();
            org.w3._2001.xmlschema.AnyType anyType = w3Factory.createAnyType();
            roomStay.setAlternateInfo(anyType);
            java.util.List listContent = anyType.getContent();
            BasicPropertyInfoTypez propInfo = factory.createBasicPropertyInfoTypez();
            roomStay.setBasicPropertyInfo(propInfo);
            propInfo.setHotelName("Test Hotel");
            //roomStay.setGuestCounts(value);
            */
                
            RoomStay.RatePlans ratePlans = new RoomStay.RatePlans();
            roomStay.setRatePlans(ratePlans);
            
            RatePlan ratePlan = new RatePlan();
            ratePlans.addRatePlan(ratePlan);
            
            ratePlan.setRatePlanCode("XYZ");
            ratePlan.setRateIndicator(RateIndicator.ON_REQUEST);
            
            RoomStay.RoomRates listRoomRate = new RoomStay.RoomRates();
            roomStay.setRoomRates(listRoomRate);
            
            RoomStay.RoomRates.RoomRate roomRate = new RoomStay.RoomRates.RoomRate();
            listRoomRate.addRoomRate(roomRate);
            
            Rate rates = new Rate();
            roomRate.setRates(rates);
            java.util.List<Rate.RateInner> listRates = rates.getRateList();
            Rate.RateInner rateType = new Rate.RateInner();
            listRates.add(rateType);
            
            String duration = "12345";
            rateType.setDuration(duration);
            AgeQualifyingGroup ageQualifyingGroup = new AgeQualifyingGroup();
            rateType.setAgeQualifyingGroup(ageQualifyingGroup);
            ageQualifyingGroup.setAgeQualifyingCode("AAA");
            ageQualifyingGroup.setAgeTimeUnit(TimeUnit.DAY);
            //rateType.setAmountAddlInfo(value);
            Total types = new Total();
            CurrencyCodeGroup currencyCodeGroup = new CurrencyCodeGroup();
            types.setCurrencyCodeGroup(currencyCodeGroup);
            currencyCodeGroup.setCurrencyCode("USD");
            Float bigAfter = new Float(20.00);
            types.setAmountAfterTax(bigAfter);
            rateType.setBase(types);
            String[] ids = TimeZone.getAvailableIDs(-8 * 60 * 60 * 1000);
            SimpleTimeZone pdt = new SimpleTimeZone(-8 * 60 * 60 * 1000, ids[0]);
            GregorianCalendar calendar = new GregorianCalendar(pdt);
            calendar.setTime(new Date());
            int iDuration2 = 2;
            EffectiveExpireOptionalDateGroup effectiveExpireOptionalDateGroup = new EffectiveExpireOptionalDateGroup();
            rateType.setEffectiveExpireOptionalDateGroup(effectiveExpireOptionalDateGroup);
            LocalDate effectiveDate = new LocalDate(calendar);
            effectiveExpireOptionalDateGroup.setEffectiveDate(effectiveDate);
            calendar.add(Calendar.DATE, iDuration2);
            LocalDate expireDate = new LocalDate(calendar);
            effectiveExpireOptionalDateGroup.setExpireDate(expireDate);
            
            ageQualifyingGroup.setMaxAge(20);
            ageQualifyingGroup.setMinAge(1);
            //rateType.setTPAExtensions(value);
            listRates.add(rateType);
            roomRate.setRates(rates);
            
            listRoomRate.addRoomRate(roomRate);
            //HotelAvailRSType.RoomStaysTypez.RoomRatesType roomRates = factory.createRoomStayTypezRoomRatesType();
            //roomStay.setRoomRates(value);
            
            //roomStay.setRoomStayAddlInfo(value);
            //roomStay.setRoomTypes(value);        
            DateTimeSpan timeSpan = new DateTimeSpan();
            roomStay.setTimeSpan(timeSpan);
            
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            DateTimeSpanGroup dateTimeSpanGroup = new DateTimeSpanGroup();
            timeSpan.setDateTimeSpanGroup(dateTimeSpanGroup);
            
            dateTimeSpanGroup.setStart(this.dateToStringDateFormat(cal.getTime()));
            int iDuration = 2;
            cal.add(Calendar.DATE, iDuration);
            dateTimeSpanGroup.setEnd(this.dateToStringDateFormat(cal.getTime()));
            
            ProductResponseMessageData messageData = (ProductResponseMessageData)hotelRateResponseOut.getMessageDataDesc(BaseProductResponse.PRODUCT_RESPONSE_MESSAGE);
            Double dblCurrency = (Double)messageData.get(BookingDetail.TOTAL_COST);
            
            root.getOTAPayloadStdAttributes().setEchoToken(dblCurrency.toString());
            //            double dCurrency = 0.00;
            //            if (dblCurrency != null)
            //                dCurrency = dblCurrency.doubleValue();
            //            rateAmount.setCurrency(dCurrency);        
            return root;
        }
        return null;
    }

}

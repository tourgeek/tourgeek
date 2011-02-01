/**
 *  @(#)HotelRateResponseSOAPMsgReplyOut2006A.
 *  Copyright © 2010 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.message.jaxb.ota2006a.hotel.response.out;

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
import org.jbundle.base.message.trx.message.*;
import org.jbundle.thin.base.message.*;
import com.tourapp.tour.message.hotel.response.*;
import net.sourceforge.ota_tools.jaxb.ota2006a.custom.*;
import org.jbundle.base.message.trx.transport.soap.*;
import javax.xml.bind.*;
import javax.xml.datatype.*;
import com.tourapp.tour.product.base.db.*;
import com.tourapp.tour.message.base.response.data.*;
import com.tourapp.tour.booking.detail.db.*;
import com.tourapp.tour.message.base.response.*;
import org.jbundle.base.message.trx.message.external.*;

/**
 *  HotelRateResponseSOAPMsgReplyOut2006A - .
 */
public class HotelRateResponseSOAPMsgReplyOut2006A extends JaxbConvertToNative
{
    /**
     * Default constructor.
     */
    public HotelRateResponseSOAPMsgReplyOut2006A()
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
    public HotelRateResponseSOAPMsgReplyOut2006A(ExternalTrxMessageOut message)
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
        HotelRateResponse hotelRateResponseOut = (HotelRateResponse)trxMessage.getMessageDataDesc(null);
        TrxMessageHeader messageHeader = (TrxMessageHeader)trxMessage.getMessageHeader();
        String strMessageType = (String)hotelRateResponseOut.get(TrxMessageHeader.MESSAGE_CODE);
        if (true)//GET_HOTEL_RATE.equalsIgnoreCase(strMessageType))
        {
            OTA_HotelAvailRS root = null;
        
                    // create an empty PurchaseOrder
                net.sourceforge.ota_tools.jaxb.ota2006a.custom.ObjectFactory factory = new net.sourceforge.ota_tools.jaxb.ota2006a.custom.ObjectFactory();
                root = factory.createOTA_HotelAvailRS();
        //            root = new HITISMessage();
        //            Header header = new Header();
        //            root.setHeader(header);
        //            header.setToURI(this.getDestinationAddress(messageHeader));
        //            header.setFromURI(messageHeader.getSourceAddress());
        //            header.setReplyToURI(messageHeader.getReplyToAddress());
        //            Body body = new Body();
        //            root.setBody(body);
        //            HITISOperation operation = new HITISOperation();
        //            body.setHITISOperation(operation);
                root.setVersion(new java.math.BigDecimal(1.23));
        
                POS_Type posTypes = null;
                root.setPOS(posTypes = factory.createPOS_Type());
                SourceType source = null;
                posTypes.getSource().add(source = factory.createSourceType());
        //+        SourceType.RequestorID uniqueId = null;
        //+        source.setRequestorID(uniqueId = factory.createSourceTypeRequestorID());
        //+        uniqueId.setID(messageHeader.get(TrxMessageHeader.LOG_TRX_ID).toString());
        //+        uniqueId.setType("12");
        //                source.setAgentSine("Test Agent");
        
                SuccessType successType = null;
                root.setSuccess(successType = factory.createSuccessType());
        
        //                ErrorsTypeTypes errorsType = null;
        //                root.setErrors(errorsType = ObjectFactory.createErrorsTypeTypes());
        //            operation.setOperationName(AVAILABILITY_RESPONSE);    // Need a constant for this
        //            AvailabilityResponse availability = new AvailabilityResponse();
        //            operation.setAvailabilityResponse(availability);
        //            QuotedRateAmount rateAmount = new QuotedRateAmount();
        //            availability.setQuotedRateAmount(rateAmount);
                OTA_HotelAvailRS.RoomStays roomStays = factory.createOTA_HotelAvailRSRoomStays();
                root.setRoomStays(roomStays);
                java.util.List listRoomStays = roomStays.getRoomStay();
        roomStays.setMoreIndicator("No");
                OTA_HotelAvailRS.RoomStays.RoomStay roomStay = factory.createOTA_HotelAvailRSRoomStaysRoomStay();
                listRoomStays.add(roomStay);
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
        
        RoomStayType.RatePlans ratePlans = factory.createRoomStayTypeRatePlans();
        roomStay.setRatePlans(ratePlans);
        
        java.util.List listRatePlan = ratePlans.getRatePlan();
        RatePlanType ratePlan = factory.createRatePlanType();
        listRatePlan.add(ratePlan);
        
        ratePlan.setRatePlanCode("XYZ");
        ratePlan.setRateIndicator(RateIndicatorType.ON_REQUEST);
        
        RoomStayType.RoomRates roomRates = factory.createRoomStayTypeRoomRates();
        java.util.List listRoomRate = roomRates.getRoomRate();
        RoomRateType roomRate = factory.createRoomRateType();
        RateType rates = factory.createRateType();
        //+        rates.setRateIndicator(RateIndicatorType.ON_REQUEST);
        java.util.List listRates = rates.getRate();
        RateType.Rate rateType = factory.createRateTypeRate();
        try {
            Duration duration = DatatypeFactory.newInstance().newDuration(12345);
            rateType.setDuration(duration);
            rateType.setAgeQualifyingCode("AAA");
            rateType.setAgeTimeUnit(TimeUnitType.DAY);
            //rateType.setAmountAddlInfo(value);
            TotalType types = factory.createTotalType();
            types.setCurrencyCode("USD");
            java.math.BigDecimal bigAfter = new java.math.BigDecimal("20.00");
            types.setAmountAfterTax(bigAfter);
            rateType.setBase(types);
            String[] ids = TimeZone.getAvailableIDs(-8 * 60 * 60 * 1000);
            SimpleTimeZone pdt = new SimpleTimeZone(-8 * 60 * 60 * 1000, ids[0]);
            GregorianCalendar calendar = new GregorianCalendar(pdt);
            calendar.setTime(new Date());
            int iDuration2 = 2;
            XMLGregorianCalendar xmlGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
            rateType.setEffectiveDate(xmlGregorianCalendar);
            calendar.add(Calendar.DATE, iDuration2);
            xmlGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
            rateType.setExpireDate(xmlGregorianCalendar);
        } catch (DatatypeConfigurationException ex) {
            ex.printStackTrace();
        }
        rateType.setGuaranteedInd(true);
        Integer bigMax = new Integer("20");
        rateType.setMaxAge(bigMax);
        Integer bigMin = new Integer("1");
        rateType.setMinAge(bigMin);
        //rateType.setTPAExtensions(value);
        listRates.add(rateType);
        roomRate.setRates(rates);
        listRoomRate.add(roomRate);
        roomStay.setRoomRates(roomRates);
        //HotelAvailRSType.RoomStaysTypez.RoomRatesType roomRates = factory.createRoomStayTypezRoomRatesType();
        //roomStay.setRoomRates(value);
        
        //roomStay.setRoomStayAddlInfo(value);
        //roomStay.setRoomTypes(value);
        DateTimeSpanType dateTime = factory.createDateTimeSpanType();
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        dateTime.setStart(this.dateToStringDateFormat(cal.getTime()));
        int iDuration = 2;
        cal.add(Calendar.DATE, iDuration);
        dateTime.setEnd(this.dateToStringDateFormat(cal.getTime()));
        roomStay.setTimeSpan(dateTime);
        ProductResponseMessageData messageData = (ProductResponseMessageData)hotelRateResponseOut.getMessageDataDesc(BaseProductResponse.PRODUCT_RESPONSE_MESSAGE);
        Double dblCurrency = (Double)messageData.get(BookingDetail.TOTAL_COST);
        root.setEchoToken(dblCurrency.toString());
        //            double dCurrency = 0.00;
        //            if (dblCurrency != null)
        //                dCurrency = dblCurrency.doubleValue();
        //            rateAmount.setCurrency(dCurrency);        
        return root;
        }
        return null;
    }

}

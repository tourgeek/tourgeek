/**
 *  @(#)HotelRateResponseSOAPMessageOut2004B.
 *  Copyright © 2010 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.message.jaxb.ota2004b.hotel.response.out;

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
import org.jbundle.base.message.trx.message.external.*;
import org.jbundle.base.message.trx.message.*;
import com.tourapp.tour.message.hotel.response.*;
import com.tourapp.tour.message.base.response.data.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import com.tourapp.tour.booking.detail.db.*;
import com.tourapp.tour.message.base.response.*;
import com.tourapp.tour.product.hotel.db.*;
import com.tourapp.tour.message.hotel.response.data.*;

/**
 *  HotelRateResponseSOAPMessageOut2004B - .
 */
public class HotelRateResponseSOAPMessageOut2004B extends JaxbConvertToNative
{
    /**
     * Default constructor.
     */
    public HotelRateResponseSOAPMessageOut2004B()
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
    public HotelRateResponseSOAPMessageOut2004B(ExternalTrxMessageOut message)
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
        HotelRateResponseMessageData responseMessageData =  (HotelRateResponseMessageData)hotelRateResponseOut.getMessageDataDesc(BaseProductResponse.PRODUCT_RESPONSE_MESSAGE);
        TrxMessageHeader messageHeader = (TrxMessageHeader)trxMessage.getMessageHeader();
        String strMessageType = (String)hotelRateResponseOut.get(TrxMessageHeader.MESSAGE_CODE);
        if (true)//GET_HOTEL_RATE.equalsIgnoreCase(strMessageType))
        {
            OTAHotelAvailRS root = null;
        
                // create an empty Hotel Avail Response
            ObjectFactory factory = new ObjectFactory();
            root = factory.createOTAHotelAvailRS();
            root.setVersion(new java.math.BigDecimal(1.003));
            // Note echo token is handled in payload setup
        
            POSType posTypes = factory.createPOSType();
            root.setPOS(posTypes);
            SourceType source = factory.createSourceType();
            posTypes.getSource().add(source);
            UniqueIDType uniqueId = factory.createUniqueIDType();
            source.setRequestorID(uniqueId);
            String strLogID = messageHeader.get(TrxMessageHeader.LOG_TRX_ID).toString();
            uniqueId.setType(this.getOTACode("UIT", "Other", "18"));    // Other
            uniqueId.setID(strLogID);
            uniqueId.setURL("http://www.kayak.com");
        
            boolean bSuccess = true;
            if (!bSuccess)  // Errors
            {
                ErrorsType errorsType = factory.createErrorsType();
                root.setErrors(errorsType);
                ErrorType errorType = factory.createErrorType();
                String code = null;
                errorType.setCode(code);
                String type = null;
                errorType.setType(type);
                String text = null;
                errorType.setShortText(text);
                errorsType.getError().add(errorType);
            }
            else if (false)         // Warnings
            {
                WarningsType warningsType = factory.createWarningsType();
                root.setWarnings(warningsType);
                WarningType warningType = factory.createWarningType();
                String code = null;
                warningType.setCode(code);
                String type = null;
                warningType.setType(type);
                String text = null;
                warningType.setShortText(text);
                warningsType.getWarning().add(warningType);
            }
            else
            {
                SuccessType successType = factory.createSuccessType();
                root.setSuccess(successType);
        
                OTAHotelAvailRS.RoomStays roomStays = factory.createOTAHotelAvailRSRoomStays();
                root.setRoomStays(roomStays);
                java.util.List<OTAHotelAvailRS.RoomStays.RoomStay> listRoomStays = roomStays.getRoomStay();
                OTAHotelAvailRS.RoomStays.RoomStay roomStay = factory.createOTAHotelAvailRSRoomStaysRoomStay();
                listRoomStays.add(roomStay);
                
                ProductResponseMessageData messageData = (ProductResponseMessageData)hotelRateResponseOut.getMessageDataDesc(BaseProductResponse.PRODUCT_RESPONSE_MESSAGE);
                Double dblCurrency = (Double)messageData.get(BookingDetail.TOTAL_COST);
                TotalType total = factory.createTotalType();
                roomStay.setTotal(total);
                total.setAmountBeforeTax(new java.math.BigDecimal(dblCurrency));
                total.setAmountAfterTax(new java.math.BigDecimal(dblCurrency));
                total.setCurrencyCode("USD");
                
                BasicPropertyInfoType basicPropertyInfoType = factory.createBasicPropertyInfoType();
                roomStay.setBasicPropertyInfo(basicPropertyInfoType);
                Hotel recHotel = (Hotel)responseMessageData.getProductRecord(recordOwner, true);
                if (responseMessageData.getProduct(recHotel))
                {
                    basicPropertyInfoType.setHotelCode(recHotel.getOperatorsCode());
                    basicPropertyInfoType.setChainCode(recHotel.getChainCode());
                }
                
                TPAExtensionsType tpa_Extensions_Type = factory.createTPAExtensionsType();
                roomStay.setTPAExtensions(tpa_Extensions_Type);
                
                DocumentBuilder db = Utility.getDocumentBuilder();
                Document doc = null;
                synchronized (db)
                {
                    doc = db.newDocument();
        
                    Element tree = (Element)doc.createElement("BookItLink");
                    doc.appendChild(tree);
                    tree.setAttribute("Method", "GET");
                    Element newChild = (Element)doc.createElement("BookItArguments");
                    tree.appendChild(newChild);
        
                    String myNamespace = null;
        //            QName qname = new QName(myNamespace, "Bob", "gmd");
        //            JAXBElement element = new JAXBElement(qname, Element.class, null);
                    tpa_Extensions_Type.getAny().add(tree);
        //            Utility.copyTreeToNode(tree, node);
        //            tpa_Extensions_Type.getAny().add(elRoot);
        //            new JAXBResult(tpa_Extensions_Type.getAny());
                }
                
                this.setPayloadProperties(trxMessage, root);
        
                return root;
            }
        }
        return null;
    }

}

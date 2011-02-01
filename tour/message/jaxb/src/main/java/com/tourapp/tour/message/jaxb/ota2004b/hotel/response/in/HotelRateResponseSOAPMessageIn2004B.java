/**
 *  @(#)HotelRateResponseSOAPMessageIn2004B.
 *  Copyright © 2010 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.message.jaxb.ota2004b.hotel.response.in;

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
import com.tourapp.tour.message.base.response.*;
import com.tourapp.tour.booking.detail.db.*;
import org.w3c.dom.*;
import com.tourapp.tour.message.base.response.data.*;

/**
 *  HotelRateResponseSOAPMessageIn2004B - .
 */
public class HotelRateResponseSOAPMessageIn2004B extends JaxbConvertToMessage
{
    /**
     * Default constructor.
     */
    public HotelRateResponseSOAPMessageIn2004B()
    {
        super();
    }
    /**
     * Initialize new BaseTrxMessage.
     * This is used for outgoing EC transactions where you have the jaxb message and you need to convert it.
     * @param objRawMessage The (optional) raw data of the message.
     */
    public HotelRateResponseSOAPMessageIn2004B(ExternalTrxMessageIn message)
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
        if (root instanceof OTAHotelAvailRS)
        {
            OTAHotelAvailRS msg = (OTAHotelAvailRS)root;
        
            POSType posTypes = msg.getPOS();
            for (SourceType source : posTypes.getSource())
            {
                UniqueIDType uniqueId = source.getRequestorID();
                String strLogID = uniqueId.getID();
                String strType = uniqueId.getType();
                String strURL = uniqueId.getURL();
            }
            
            SuccessType successType = msg.getSuccess();
            if (successType == null)
            {
                ErrorsType errorsType = msg.getErrors();
                if (errorsType != null)
                {
                    for (ErrorType errorType : errorsType.getError())
                    {
                        String strCode = errorType.getCode();
                        String strType = errorType.getType();
                        String strText = errorType.getShortText();
                    }
                }
                else
                {
                    WarningsType warningsType = msg.getWarnings();
                    for (WarningType warningType : warningsType.getWarning())
                    {
                        String strCode = warningType.getCode();
                        String strType = warningType.getType();
                        String strText = warningType.getShortText();
                    }
                }
                return DBConstants.ERROR_RETURN;    // Error return
            }
            
            OTAHotelAvailRS.RoomStays roomStays = msg.getRoomStays();
            for (OTAHotelAvailRS.RoomStays.RoomStay roomStay : roomStays.getRoomStay())
            {
                TotalType total = roomStay.getTotal();
                java.math.BigDecimal afterTax = total.getAmountAfterTax();
                java.math.BigDecimal beforeTax = total.getAmountBeforeTax();
                String strCurrency = total.getCurrencyCode();
                
                ProductRateResponse productResponseData = (ProductRateResponse)this.getMessage().getMessageDataDesc(null);
                ProductResponseMessageData productMessageData = (ProductResponseMessageData)this.getMessage().getMessageDataDesc(BaseProductResponse.PRODUCT_RESPONSE_MESSAGE);
                productMessageData.put(BookingDetail.TOTAL_COST, afterTax.doubleValue());
                if (afterTax != null)
                    if (afterTax.doubleValue() != 0)
                        productResponseData.setMessageDataStatus(MessageDataDesc.VALID);
        
                BasicPropertyInfoType basicPropertyInfoType = roomStay.getBasicPropertyInfo();
                String strChainCode = basicPropertyInfoType.getChainCode();
                String strProductCode = basicPropertyInfoType.getHotelCode();
                
                String strHotelID = productMessageData.getProductID(recordOwner, strChainCode, strProductCode);
                productMessageData.put(BookingDetail.PRODUCT_ID, strHotelID);
        
                TPAExtensionsType tpa_Extensions_Type = roomStay.getTPAExtensions();
                for (Element element : tpa_Extensions_Type.getAny())
                {
                    
                }
            }
        }
        return DBConstants.NORMAL_RETURN;
    }

}

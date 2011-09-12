/**
 * @(#)GetProductCostHandler.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.product.base.event;

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
import org.jbundle.main.msg.db.*;
import org.jbundle.thin.base.message.*;
import com.tourapp.tour.acctpay.db.*;
import org.jbundle.base.message.trx.message.*;
import com.tourapp.tour.product.base.db.*;
import com.tourapp.tour.message.base.request.*;
import com.tourapp.tour.message.base.response.*;
import com.tourapp.tour.product.tour.db.*;
import com.tourapp.tour.booking.db.*;
import org.jbundle.main.msg.db.base.*;

/**
 *  GetProductCostHandler - Get the product cost on a valid product record.
(Using the params from the ProductVars screenrecord)..
 */
public class GetProductCostHandler extends GetProductStatusHandler
{
    /**
     * Default constructor.
     */
    public GetProductCostHandler()
    {
        super();
    }
    /**
     * GetProductCostHandler Method.
     */
    public GetProductCostHandler(Record recProductVars, Integer intRegistryID)
    {
        this();
        this.init(recProductVars, intRegistryID);
    }
    /**
     * Initialize class fields.
     */
    public void init(Record recProductVars, Integer intRegistryID)
    {
        super.init(recProductVars, intRegistryID);
    }
    /**
     * Get the cost or inventory information from the product now.
     * @return The message status.
     */
    public int getDirectProductInfo(Product recProduct, BaseMessage message)
    {
        BaseProductResponse response = (BaseProductResponse)recProduct.processCostRequestInMessage(message, null).getMessageDataDesc(null);
        
        int iPricingType = PricingType.COMPONENT_PRICING | PricingType.COMPONENT_COST_PRICING;
        double dMarkup = 0.00;
        RecordOwner recordOwner = recProduct.getRecordOwner();
        if (recordOwner != null)
        {
            Record recBooking = recordOwner.getRecord(Booking.kBookingFile);
            if ((recBooking != null)
                && ((recBooking.getEditMode() == DBConstants.EDIT_CURRENT) || (recBooking.getEditMode() == DBConstants.EDIT_IN_PROGRESS)))
            {
                PricingType recPricingType = (PricingType)((ReferenceField)recBooking.getField(Booking.kNonTourPricingTypeID)).getReference();
                iPricingType = (int)recPricingType.getField(PricingType.kPricingCodes).getValue();
                dMarkup = recBooking.getField(Booking.kMarkup).getValue();
            }
            else
            {
                Record recBookingControl = recordOwner.getRecord(BookingControl.kBookingControlFile);
                if (recBookingControl == null)
                    recBookingControl = new BookingControl(recordOwner);
                PricingType recPricingType = (PricingType)((ReferenceField)recBookingControl.getField(BookingControl.kNonTourPricingTypeID)).getReference();
                iPricingType = (int)recPricingType.getField(PricingType.kPricingCodes).getValue();
                dMarkup = recBookingControl.getField(BookingControl.kMarkup).getValue();
            }
        
            boolean bMarkupOnlyIfNoPrice = false;
            if (((iPricingType & PricingType.COMPONENT_PRICING) != 0) && ((iPricingType & PricingType.COMPONENT_COST_PRICING) != 0))
                bMarkupOnlyIfNoPrice = true;
        
            if ((iPricingType & PricingType.OPTION_PRICING) != 0)
                recProduct.markupPriceFromCost(0, false);    // Never
            
            if ((iPricingType & PricingType.COMPONENT_COST_PRICING) != 0)
                recProduct.markupPriceFromCost(dMarkup, bMarkupOnlyIfNoPrice);
        
            recProduct.markupPriceFromCost(0, true);   // Null looks better than 0
        }
        
        if (response != null)
            return response.getMessageDataStatus();
        return BaseStatus.NOT_VALID;
    }
    /**
     * If all the data in the screen record that is required for a query is there,
     * return true. If not, false.
     */
    public boolean isQueryComplete()
    {
        if (!m_recProductVars.getField(ProductScreenRecord.kDetailDate).isNull())
            return true;
        return false;
    }
    /**
     * Get the lookup properties from the ProductScreenRecord and the
     * Product record and set them in this map.
     * (Override this to set the loopup properties).
     */
    public void setLookupProperties(BaseMessage map, Record recProduct, Record screenRecord)
    {
        ((ProductScreenRecord)screenRecord).setPriceProperties(map, (Product)recProduct);
    }
    /**
     * Move this product cost from to virtual fields to the ProductCost
     * field in recProduct. Also move the status to the product cost status.
     * (Override this to set the correct fields!).
     */
    public void setupScreenStatus(Record recProduct, int iStatus)
    {
        if (iStatus == CostStatus.VALID)
        {
        //    double dProductCost = recProduct.getField(Product.kProductCost).getValue();
        //    recProduct.getField(Product.kProductCost).setValue(dProductCost);
        }
        else
            recProduct.getField(Product.kProductCost).setData(null);
        recProduct.getField(Product.kDisplayCostStatusID).setValue(iStatus);
    }

}

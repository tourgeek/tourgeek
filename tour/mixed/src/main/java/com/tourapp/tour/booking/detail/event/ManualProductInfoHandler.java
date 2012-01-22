/**
 * @(#)ManualProductInfoHandler.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.booking.detail.event;

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
import com.tourapp.tour.booking.detail.db.*;
import com.tourapp.tour.product.base.db.*;

/**
 *  ManualProductInfoHandler - .
 */
public class ManualProductInfoHandler extends DisableOnFieldHandler
{
    protected ScreenField m_sField = null;
    /**
     * Default constructor.
     */
    public ManualProductInfoHandler()
    {
        super();
    }
    /**
     * ManualProductInfoHandler Method.
     */
    public ManualProductInfoHandler(BaseField fieldToDisable, String strCompareString, boolean bDisableIfMatch)
    {
        this();
        this.init(fieldToDisable, strCompareString, bDisableIfMatch);
    }
    /**
     * Initialize class fields.
     */
    public void init(BaseField fieldToDisable, String strCompareString, boolean bDisableIfMatch)
    {
        m_sField = null;
        super.init(null, fieldToDisable, strCompareString, bDisableIfMatch);
    }
    /**
     * GetComponent Method.
     */
    public ScreenField getComponent()
    {
        if (m_sField == null)
        {
            BaseField fldProductDesc = ((ReferenceField)this.getOwner().getRecord().getField(BookingDetail.kProductID)).getReferenceRecord().getField(Product.kDescription);
            m_sField = (ScreenField)fldProductDesc.getComponent(0);
        }
        return m_sField;
    }
    /**
     * FieldChanged Method.
     */
    public int fieldChanged(boolean bDisplayOption, int iMoveMode)
    {
        boolean bFlag = this.compareFieldToString();
        if (m_bDisableIfMatch)
            bFlag = !bFlag;
        String strProperty = "hotelName";
        BaseField fldTransportID = this.getOwner();
        BaseField oldFldToDisable = m_fldToDisable;
        BaseField fldProductDesc = ((ReferenceField)fldTransportID.getRecord().getField(BookingDetail.kProductID)).getReferenceRecord().getField(Product.kDescription);
        BaseField fldProperties = fldTransportID.getRecord().getField(BookingDetail.kProperties);
        if (bFlag)
            m_fldToDisable = fldProperties;
        else
            m_fldToDisable = fldProductDesc;
        if (oldFldToDisable != m_fldToDisable)
        {   // Need to switch Hotel display field
            ScreenField sField = this.getComponent();
            FieldConverter converter = (FieldConverter)sField.getConverter();  // Field length converter
                
            if (bFlag)
            {   // Enable (properties)
                PropertiesConverter propConverter = new PropertiesConverter(fldProperties, strProperty);
                converter.setNextConverter(propConverter);
            }
            else
            {   // Disable (Hotel display)
                if (converter instanceof FieldConverter)
                    if (((FieldConverter)converter).getNextConverter() instanceof PropertiesConverter)
                { // Always
                    ((FieldConverter)converter).getNextConverter().free();
                    converter.setNextConverter(fldProductDesc);
                }
            }
            oldFldToDisable.removeComponent(sField);
            m_fldToDisable.addComponent(sField);
            // sField.setConverter(converter); // No need to change the converter
        }
        return super.fieldChanged(bDisplayOption, iMoveMode);
    }

}

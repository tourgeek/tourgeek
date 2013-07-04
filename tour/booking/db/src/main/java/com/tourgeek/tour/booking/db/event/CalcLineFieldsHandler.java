
package com.tourgeek.tour.booking.db.event;

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
import com.tourgeek.tour.booking.detail.db.*;

/**
 *  CalcLineFieldsHandler - .
 */
public class CalcLineFieldsHandler extends FieldListener
{
    /**
     * Default constructor.
     */
    public CalcLineFieldsHandler()
    {
        super();
    }
    /**
     * CalcLineFieldsHandler Method.
     */
    public CalcLineFieldsHandler(BaseField field)
    {
        this();
        this.init(field);
    }
    /**
     * Initialize class fields.
     */
    public void init(BaseField field)
    {
        super.init(field);
    }
    /**
     * FieldChanged Method.
     */
    public int fieldChanged(boolean bDisplayOption, int iMoveMode)
    {
        Record recBookingLine = this.getOwner().getRecord();
        double dPrice = recBookingLine.getField(BookingLine.PRICE).getValue();
        double dQuantity = recBookingLine.getField(BookingLine.QUANTITY).getValue();
        double dCommissionRate = recBookingLine.getField(BookingLine.COMMISSION_RATE).getValue();
        double dGross = Math.floor(dPrice * dQuantity * 100 + 0.5) / 100;
        double dCommission = Math.floor(dGross * dCommissionRate * 100 + 0.5) / 100;
        double dNet = dGross - dCommission;
        recBookingLine.getField(BookingLine.GROSS).setValue(dGross);
        recBookingLine.getField(BookingLine.COMMISSION).setValue(dCommission);
        recBookingLine.getField(BookingLine.NET).setValue(dNet);
        return super.fieldChanged(bDisplayOption, iMoveMode);
    }

}

/**
 *  @(#)ApTrxClassField.
 *  Copyright © 2010 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.acctpay.screen.trx;

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
import com.tourapp.tour.acctpay.db.*;
import com.tourapp.tour.genled.db.*;

/**
 *  ApTrxClassField - A/P Transaction types to display.
 */
public class ApTrxClassField extends IntegerField
{
    public static final int ALL = 0;
    public static final int DEPARTURE_ESTIMATES = 2;
    public static final String DISPLAY_TYPE_PARAM = "DisplayType";
    public static final int INVOICES = 1;
    public static final int PREPAYMENTS = 3;
    /**
     * Default constructor.
     */
    public ApTrxClassField()
    {
        super();
    }
    /**
     * Constructor.
     * @param record The parent record.
     * @param strName The field name.
     * @param iDataLength The maximum string length (pass -1 for default).
     * @param strDesc The string description (usually pass null, to use the resource file desc).
     * @param strDefault The default value (if object, this value is the default value, if string, the string is the default).
     */
    public ApTrxClassField(Record record, String strName, int iDataLength, String strDesc, Object strDefault)
    {
        this();
        this.init(record, strName, iDataLength, strDesc, strDefault);
    }
    /**
     * Initialize class fields.
     */
    public void init(Record record, String strName, int iDataLength, String strDesc, Object strDefault)
    {
        super.init(record, strName, iDataLength, strDesc, strDefault);
    }
    /**
     * Set this field to its initial value.
     */
    public int initField(boolean bDisplayOption)
    {
        return this.setValue(ApTrxClassField.ALL, bDisplayOption, DBConstants.INIT_MOVE);
    }
    /**
     * Set up the default screen control for this field.
     * @param itsLocation Location of this component on screen (ie., GridBagConstraint).
     * @param targetScreen Where to place this component (ie., Parent screen or GridBagLayout).
     * @param converter The converter to set the screenfield to.
     * @param iDisplayFieldDesc Display the label? (optional).
     * @return Return the component or ScreenField that is created for this field.
     */
    public ScreenField setupDefaultView(ScreenLocation itsLocation, BasePanel targetScreen, Converter converter, int iDisplayFieldDesc)
    {
        ScreenField screenField = null;
        for (int iBitPosition = ALL; iBitPosition <= PREPAYMENTS; iBitPosition++)    // Calendar.SUNDAY -> Calendar.SATURDAY
        {
            FieldConverter convBit = new RadioConverter(converter, Integer.toString(iBitPosition), true);
            convBit = new FieldDescConverter(convBit, this.getBitDesc(iBitPosition));
            screenField = new SRadioButton(itsLocation, targetScreen, convBit, iDisplayFieldDesc);
            itsLocation = targetScreen.getNextLocation(ScreenConstants.RIGHT_WITH_DESC, ScreenConstants.DONT_SET_ANCHOR);
        }
        return screenField;
    }
    /**
     * GetBitDesc Method.
     */
    public String getBitDesc(int iBitPosition)
    {
        BaseApplication application = null;
        if (this.getRecord().getRecordOwner() != null)
            if (this.getRecord().getRecordOwner().getTask() != null)
                application = (BaseApplication)this.getRecord().getRecordOwner().getTask().getApplication();
        String string;
        switch (iBitPosition)
        {
            case ApTrxClassField.ALL:
                string = "All";break;
            case ApTrxClassField.INVOICES:
                string = "Invoices";break;
            case ApTrxClassField.PREPAYMENTS:
                string = "Prepayments";break;
            case ApTrxClassField.DEPARTURE_ESTIMATES:
            default:
                string = "Dep. est."; break;
        }
        if (application != null)
            string = application.getResources(ResourceConstants.ACCTPAY_RESOURCE, true).getString(string);
        return string;
    }

}

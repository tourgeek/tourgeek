/**
 * @(#)DepositTypeField.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.acctpay.db;

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

/**
 *  DepositTypeField - .
 */
public class DepositTypeField extends StringPopupField
{
    public static final String NONE = DBConstants.BLANK;
    public static final String PERCENTAGE = "P";
    public static final String AMOUNT = "A";
    public static final String IN_FULL = "F";
    public static final String NIGHTS = "N";
    /**
     * Default constructor.
     */
    public DepositTypeField()
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
    public DepositTypeField(Record record, String strName, int iDataLength, String strDesc, Object strDefault)
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
     * Get the default value.
     * @return The default value.
     */
    public Object getDefault()
    {
        Object objDefault = super.getDefault();
        if (objDefault == null)
            objDefault = DepositTypeField.NONE;
        return objDefault;
    }
    /**
     * Get the conversion Map.
     */
    public String[][] getPopupMap()
    {
        String string[][] = {
            {DepositTypeField.NONE, "None"},
            {DepositTypeField.PERCENTAGE, "Percentage"},
            {DepositTypeField.AMOUNT, "Amount"},
            {DepositTypeField.IN_FULL, "In full"},
            {DepositTypeField.NIGHTS, "Nights"},
        };
        return string;
    }

}

/**
 * @(#)TrxTypeConverter.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.genled.db;

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

/**
 *  TrxTypeConverter - .
 */
public class TrxTypeConverter extends FieldConverter
{
    /**
     * Default constructor.
     */
    public TrxTypeConverter()
    {
        super();
    }
    /**
     * Constructor.
     */
    public TrxTypeConverter(Converter converter)
    {
        this();
        this.init(converter);
    }
    /**
     * Initialize class fields.
     */
    public void init(BaseField field)
    {
        super.init(field);
    }
    /**
     * GetString Method.
     */
    public String getString()
    {
        String string = DBConstants.BLANK;
        Record record = ((BaseField)this.getField()).getRecord();
        String strGroup = record.getField(TransactionType.GROUP_DESC).toString();
        String strType = record.getField(TransactionType.TYPE_DESC).toString();
        String strTrx = record.getField(TransactionType.TYPE_CODE).toString();
        if (strTrx.length() == 0)
            string = strType;
        else
            string = strGroup + " - " + strType;
        //BaseField field = record.getField(TransactionType.TYPICAL_BALANCE);
        //String strField = field.convertIndexToDisStr(field.convertFieldToIndex());
        //if (strField.length() > 1)
        //    string += " [" + strField + "] ";
        //else
        //    string += "-";
        //string += record.getField(TransactionType.DESCRIPTION).toString() + "-" + record.getField(TransactionType.SYSTEM_DESC).toString();
        return string;
    }

}

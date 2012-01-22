/**
 * @(#)TrxTypeConverter.
 * Copyright © 2011 tourapp.com. All rights reserved.
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
import org.jbundle.base.screen.model.*;
import org.jbundle.base.screen.model.util.*;
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import com.tourapp.tour.genled.screen.trx.*;

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
        String strGroup = record.getField(TransactionType.kGroupDesc).toString();
        String strType = record.getField(TransactionType.kTypeDesc).toString();
        String strTrx = record.getField(TransactionType.kTypeCode).toString();
        if (strTrx.length() == 0)
            string = strType;
        else
            string = strGroup + " - " + strType;
        //BaseField field = record.getField(TransactionType.kTypicalBalance);
        //String strField = field.convertIndexToDisStr(field.convertFieldToIndex());
        //if (strField.length() > 1)
        //    string += " [" + strField + "] ";
        //else
        //    string += "-";
        //string += record.getField(TransactionType.kDescription).toString() + "-" + record.getField(TransactionType.kSystemDesc).toString();
        return string;
    }

}

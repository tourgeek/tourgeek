/**
 * @(#)VariesByField.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.product.land.db;

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
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;

/**
 *  VariesByField - .
 */
public class VariesByField extends StringPopupField
{
    public static final String AUTO_PER_PERSON = "P";
    public static final String AUTO_FIXED = "F";
    public static final String AUTO_PER_ROOM = "R";
    public static final String MANUAL_PER_PERSON = "D";
    public static final String MANUAL_FIXED = "Y";
    public final static String MANUAL_PER_ROOM = "H";
    /**
     * Default constructor.
     */
    public VariesByField()
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
    public VariesByField(Record record, String strName, int iDataLength, String strDesc, Object strDefault)
    {
        this();
        this.init(record, strName, iDataLength, strDesc, strDefault);
    }
    /**
     * Constructor.
     * @param record The parent record.
     * @param strName The field name.
     * @param iDataLength The maximum string length (pass -1 for default).
     * @param strDesc The string description (usually pass null, to use the resource file desc).
     * @param strDefault The default value (if object, this value is the default value, if string, the string is the default).
     */
    public void init(Record record, String strName, int iDataLength, String strDesc, Object strDefault)
    {
        super.init(record, strName, iDataLength, strDesc, strDefault);
        if (iDataLength == DBConstants.DEFAULT_FIELD_LENGTH)
            m_iMaxLength = 1;
    }
    /**
     * Get the default value.
     * @return The default value.
     */
    public Object getDefault()
    {
        Object objDefault = super.getDefault();
        if (objDefault == null)
            objDefault = AUTO_PER_PERSON;
        return objDefault;
    }
    /**
     * Get the conversion Map.
     */
    public String[][] getPopupMap()
    {
        String string[][] = {
            {AUTO_PER_PERSON, "Per person (auto)"}, 
            {AUTO_PER_ROOM, "Per room (auto)"}, 
            {AUTO_FIXED, "Fixed (auto)"}, 
            {MANUAL_PER_PERSON, "Per person (manual)"}, 
            {MANUAL_PER_ROOM, "Per room (manual)"}, 
            {MANUAL_FIXED, "Fixed (manual)"}, 
        };
        return string;
    }

}

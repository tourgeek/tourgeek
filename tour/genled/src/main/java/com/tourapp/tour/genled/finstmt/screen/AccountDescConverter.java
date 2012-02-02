/**
 * @(#)AccountDescConverter.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.genled.finstmt.screen;

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
import com.tourapp.tour.genled.db.*;

/**
 *  AccountDescConverter - If Manual Account Desc is null, return the Account's Description.
 */
public class AccountDescConverter extends MultipleFieldConverter
{
    /**
     * Default constructor.
     */
    public AccountDescConverter()
    {
        super();
    }
    /**
     * Constructor.
     */
    public AccountDescConverter(Converter converter, Converter converterAlt)
    {
        this();
        this.init(converter, converterAlt);
    }
    /**
     * Initialize class fields.
     */
    public void init(BaseField field)
    {
        super.init(field);
    }
    /**
     * Get the index of the converter to use (-1 means use the next converter on the chain).
     * Should I pass the alternate field (or the main field)?
     * @param bSetData If true I will be set(ing) the data of this field, if false I will be get(ing) the data.
     * @return index (-1)= next converter, 0 - n = List of converters.
     */
    public int getIndexOfConverterToPass(boolean bSetData)
    {
        int iIndex = -1;
        boolean bOldTranslation = this.setEnableTranslation(false);   // To avoid re-calling this method in getField()
        if (((BaseField)this.getField()).getRecord().getField(FinStmtDetail.ACCOUNT_ID).isNull())
            iIndex = -1;  // No account ID - use this one
        else if (bSetData)
            iIndex = -1;      // On data input, set the fin stmt desc.
        else if (this.getField().isNull())
            iIndex = 0; // Account Desc is null, return
        else
            iIndex = -1;  // Use alternate desc (Acct Desc was overidden)
        this.setEnableTranslation(bOldTranslation);
        return iIndex;
    }

}

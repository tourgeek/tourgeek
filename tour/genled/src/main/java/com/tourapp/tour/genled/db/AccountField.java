/**
 * @(#)AccountField.
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
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import com.tourapp.tour.genled.screen.misc.*;
import com.tourapp.tour.genled.screen.detail.*;

/**
 *  AccountField - G/L Account field.
 */
public class AccountField extends ReferenceField
{
    /**
     * Default constructor.
     */
    public AccountField()
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
    public AccountField(Record record, String strName, int iDataLength, String strDesc, Object strDefault)
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
     * Get (or make) the current record for this reference.
     */
    public Record makeReferenceRecord(RecordOwner recordOwner)
    {
        return new Account(recordOwner);
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
        Record recAccount = this.makeReferenceRecord();
        BaseField fldAcctNo = recAccount.getField(Account.kAccountNo);
        fldAcctNo.addListener(new MainReadOnlyHandler(Account.kAccountNoKey));
        Converter conv = fldAcctNo; // new GlConverter(fldAcctNo); (Don't need a converter since AccountNo has one already)
        conv = new FieldDescConverter(conv, this);  // Use the description for this field
        conv.setupDefaultView(itsLocation, targetScreen, conv, iDisplayFieldDesc);
        
        return this.setupTableLookup(targetScreen.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), targetScreen, ScreenConstants.DONT_DISPLAY_FIELD_DESC, recAccount, -1, Account.kDescription, true);
    }

}

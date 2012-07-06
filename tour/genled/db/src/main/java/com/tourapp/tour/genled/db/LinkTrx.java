/**
  * @(#)LinkTrx.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
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
import com.tourapp.model.tour.genled.db.*;

/**
 *  LinkTrx - Base for transactions that are linked to another transaction..
 */
public class LinkTrx extends BaseTrx
     implements LinkTrxModel
{
    private static final long serialVersionUID = 1L;

    public static final int LINK_DISTRIBUTION_SCREEN = ScreenConstants.DETAIL_MODE | 8192;
    /**
     * Default constructor.
     */
    public LinkTrx()
    {
        super();
    }
    /**
     * Constructor.
     */
    public LinkTrx(RecordOwner screen)
    {
        this();
        this.init(screen);
    }
    /**
     * Initialize class fields.
     */
    public void init(RecordOwner screen)
    {
        super.init(screen);
    }

    public static final String LINK_TRX_FILE = null;    // Screen field
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        //if (iFieldSeq == 0)
        //{
        //  field = new CounterField(this, ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //  field.setHidden(true);
        //}
        //if (iFieldSeq == 1)
        //{
        //  field = new RecordChangedField(this, LAST_CHANGED, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //  field.setHidden(true);
        //}
        //if (iFieldSeq == 2)
        //{
        //  field = new BooleanField(this, DELETED, Constants.DEFAULT_FIELD_LENGTH, null, new Boolean(false));
        //  field.setHidden(true);
        //}
        //if (iFieldSeq == 3)
        //  field = new TrxStatusField(this, TRX_STATUS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 4)
        //  field = new LinkTrx_TrxUserID(this, TRX_USER_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 5)
        //  field = new DateTimeField(this, TRX_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 6)
        //  field = new CurrencyField(this, AMOUNT_LOCAL, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 7)
        //  field = new LinkTrx_TrxEntry(this, TRX_ENTRY, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 8)
            field = new TrxField(this, LINKED_TRX_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 9)
            field = new TrxDescField(this, LINKED_TRX_DESC_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
            field = super.setupField(iFieldSeq);
        return field;
    }
    /**
     * Get the command to display the source transaction.
     */
    public String getSourceCommand()
    {
        String strCommand = DBConstants.BLANK;
        if ((this.getEditMode() == DBConstants.EDIT_IN_PROGRESS)
            || (this.getEditMode() == DBConstants.EDIT_CURRENT))
        {
            String bookmark = this.getField(LinkTrx.LINKED_TRX_ID).toString();
            if ((bookmark != null) && (bookmark.length() > 0))
            {
                TrxDesc recTrxDesc = (TrxDesc)((ReferenceField)this.getField(LinkTrx.LINKED_TRX_DESC_ID)).getReference();
                if (recTrxDesc != null)
                {
                    String strRecordSource = recTrxDesc.getField(TrxDesc.SOURCE_FILE).toString();
                    if ((strRecordSource != null) && (strRecordSource.length() > 0))
                    {
                        strCommand = Utility.addURLParam(strCommand, DBParams.RECORD, strRecordSource);
                        strCommand = Utility.addURLParam(strCommand, Constants.OBJECT_ID, bookmark.toString());
                        strCommand = Utility.addURLParam(strCommand, DBParams.COMMAND, MenuConstants.FORM);
                    }
                }
            }
        }
        return strCommand;
    }

}

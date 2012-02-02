/**
 * @(#)LinkTrx.
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
import com.tourapp.model.tour.genled.db.*;

/**
 *  LinkTrx - Base for transactions that are linked to another transaction..
 */
public class LinkTrx extends BaseTrx
     implements LinkTrxModel
{
    private static final long serialVersionUID = 1L;

    public static final int kLinkedTrxID = kBaseTrxLastField + 1;
    public static final int kLinkedTrxDescID = kLinkedTrxID + 1;
    public static final int kLinkTrxLastField = kLinkedTrxDescID;
    public static final int kLinkTrxFields = kLinkedTrxDescID - DBConstants.MAIN_FIELD + 1;

    public static final int kIDKey = DBConstants.MAIN_KEY_FIELD;
    public static final int kLinkedTrxIDKey = kIDKey + 1;
    public static final int kLinkTrxLastKey = kLinkedTrxIDKey;
    public static final int kLinkTrxKeys = kLinkedTrxIDKey - DBConstants.MAIN_KEY_FIELD + 1;
    public static final int LINK_DISTRIBUTION_SCREEN = ScreenConstants.DISPLAY_MODE | 8192;
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

    public static final String kLinkTrxFile = null;   // Screen field
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        if (iFieldSeq == kLinkedTrxID)
            field = new TrxField(this, "LinkedTrxID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kLinkedTrxDescID)
            field = new TrxDescField(this, "LinkedTrxDescID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kLinkTrxLastField)
                field = new EmptyField(this);
        }
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

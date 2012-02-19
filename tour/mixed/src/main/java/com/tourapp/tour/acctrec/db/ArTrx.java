/**
 * @(#)ArTrx.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.acctrec.db;

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
import com.tourapp.tour.acctrec.screen.misc.*;
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.acctrec.screen.refund.*;
import com.tourapp.tour.acctrec.db.event.*;
import com.tourapp.tour.booking.entry.acctrec.*;
import com.tourapp.tour.acctrec.screen.display.*;
import com.tourapp.model.tour.acctrec.db.*;

/**
 *  ArTrx - A/R Open File.
 */
public class ArTrx extends LinkTrx
     implements ArTrxModel
{
    private static final long serialVersionUID = 1L;

    public static final int CR_DR_SCREEN = ScreenConstants.DISPLAY_MODE | 192;
    public static final int REFUND_SCREEN = ScreenConstants.DISPLAY_MODE | 128;
    public static final int REFUND_PEND_SCREEN = ScreenConstants.DISPLAY_MODE | 256;
    public static final int REFUND_CHECK_POST = ScreenConstants.DISPLAY_MODE | 512;
    public static final int REFUND_CHECK_XML_PRINT = ScreenConstants.DISPLAY_MODE | 2048;
    public static final int REFUND_CHECK_CANNED_PRINT = ScreenConstants.DISPLAY_MODE | 4096;
    public static final int DISTRIBUTION_SCREEN = ScreenConstants.DETAIL_MODE | 8192;
    /**
     * Default constructor.
     */
    public ArTrx()
    {
        super();
    }
    /**
     * Constructor.
     */
    public ArTrx(RecordOwner screen)
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
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(AR_TRX_FILE, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "Open Item";
    }
    /**
     * Get the Database Name.
     */
    public String getDatabaseName()
    {
        return "acctrec";
    }
    /**
     * Is this a local (vs remote) file?.
     */
    public int getDatabaseType()
    {
        return DBConstants.REMOTE | DBConstants.USER_DATA;
    }
    /**
     * MakeScreen Method.
     */
    public ScreenParent makeScreen(ScreenLoc itsLocation, ComponentParent parentScreen, int iDocMode, Map<String,Object> properties)
    {
        ScreenParent screen = null;
        if ((iDocMode & ArTrx.LINK_DISTRIBUTION_SCREEN) == ArTrx.LINK_DISTRIBUTION_SCREEN)
            screen = new ArTrxLinkTrxGridScreen(null, this, (ScreenLocation)itsLocation, (BasePanel)parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if ((iDocMode == ScreenConstants.DETAIL_MODE)
            || (iDocMode == ArTrx.DISTRIBUTION_SCREEN))
                screen = new ArTrxDistGridScreen(this, null, (ScreenLocation)itsLocation, (BasePanel)parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if (iDocMode == ArTrx.CR_DR_SCREEN)
            screen = new CrDrScreen(this, (ScreenLocation)itsLocation, (BasePanel)parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if (iDocMode == ArTrx.REFUND_SCREEN)
            screen = new RefundScreen(this, (ScreenLocation)itsLocation, (BasePanel)parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if (iDocMode == ArTrx.REFUND_PEND_SCREEN)
            screen = new RefundPendScreen(this, (ScreenLocation)itsLocation, (BasePanel)parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if (iDocMode == ArTrx.REFUND_CHECK_POST)
            screen = new RefundCheckPost(this, (ScreenLocation)itsLocation, (BasePanel)parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if (iDocMode == ArTrx.REFUND_CHECK_XML_PRINT)
            screen = new RefundCheckXMLPrint(this, (ScreenLocation)itsLocation, (BasePanel)parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if (iDocMode == ArTrx.REFUND_CHECK_CANNED_PRINT)
            screen = new RefundCheckPrint(this, (ScreenLocation)itsLocation, (BasePanel)parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if ((iDocMode & ScreenConstants.MAINT_MODE) == ScreenConstants.MAINT_MODE)
            screen = new BookingArTrxScreen(this, (ScreenLocation)itsLocation, (BasePanel)parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else
            screen = new BookingArTrxGridScreen(this, (ScreenLocation)itsLocation, (BasePanel)parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        return screen;
    }
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
        if (iFieldSeq == 3)
            field = new TrxStatusField(this, TRX_STATUS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 4)
        //  field = new ArTrx_TrxUserID(this, TRX_USER_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 5)
            field = new ArTrx_TrxDate(this, TRX_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 6)
            field = new CurrencyField(this, AMOUNT, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 7)
        //  field = new ArTrx_TrxEntry(this, TRX_ENTRY, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 8)
        //  field = new TrxField(this, LINKED_TRX_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 9)
        //  field = new TrxDescField(this, LINKED_TRX_DESC_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 10)
            field = new BookingField(this, BOOKING_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 11)
            field = new StringField(this, COMMENTS, 30, null, null);
        if (iFieldSeq == 12)
            field = new DateField(this, DEPARTURE_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
            field = super.setupField(iFieldSeq);
        return field;
    }
    /**
     * Add this key area description to the Record.
     */
    public KeyArea setupKey(int iKeyArea)
    {
        KeyArea keyArea = null;
        if (iKeyArea == 0)
        {
            keyArea = this.makeIndex(DBConstants.UNIQUE, "ID");
            keyArea.addKeyField(ID, DBConstants.ASCENDING);
        }
        if (iKeyArea == 1)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "LinkedTrxID");
            keyArea.addKeyField(LINKED_TRX_ID, DBConstants.ASCENDING);
            keyArea.addKeyField(LINKED_TRX_DESC_ID, DBConstants.ASCENDING);
        }
        if (iKeyArea == 2)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "BookingID");
            keyArea.addKeyField(BOOKING_ID, DBConstants.ASCENDING);
            keyArea.addKeyField(TRX_DATE, DBConstants.ASCENDING);
            keyArea.addKeyField(TRX_STATUS_ID, DBConstants.ASCENDING);
        }
        if (iKeyArea == 3)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "TrxStatusID");
            keyArea.addKeyField(TRX_STATUS_ID, DBConstants.ASCENDING);
            keyArea.addKeyField(TRX_DATE, DBConstants.ASCENDING);
        }
        if (keyArea == null)
            keyArea = super.setupKey(iKeyArea);     
        return keyArea;
    }
    /**
     * AddMasterListeners Method.
     */
    public void addMasterListeners()
    {
        this.addListener(new NoDeleteModifyHandler(true, true));
    }
    /**
     * Add the booking detail behaviors.
     */
    public void addDetailBehaviors(Booking recBooking)
    {
        this.addListener(new SubFileFilter(recBooking, true));
        this.addListener(new InitArTrxHandler(recBooking));
        if (this.getRecordOwner() instanceof GridScreen)
            recBooking.getField(Booking.ID).addListener(new FieldReSelectHandler((GridScreen)this.getRecordOwner()));
        if (recBooking != null)
        {   // Sub counts must be first.
            this.addListener(new SubCountHandler(recBooking.getField(Booking.BALANCE), ArTrx.AMOUNT, true, true));
        
            this.addListener(new ArTrxInvoiceSubCountHandler(null, ArTrx.AMOUNT, true, true));
        }
        this.addListener(new CheckBookingStatusHandler(recBooking));
        this.addListener(new UpdateArTrxAcctDetailHandler(recBooking));
    }
    /**
     * Convert the command to the screen document type.
     * @param strCommand The command text.
     * @param The standard document type (MAINT/DISPLAY/SELECT/MENU/etc).
     */
    public int commandToDocType(String strCommand)
    {
        if (AcctDetailDist.DIST_DISTRIBUTION.equalsIgnoreCase(strCommand))
            return ArTrx.DISTRIBUTION_SCREEN;
        return super.commandToDocType(strCommand);
    }

}

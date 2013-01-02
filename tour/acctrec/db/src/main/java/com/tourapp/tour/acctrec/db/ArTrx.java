/**
  * @(#)ArTrx.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.tour.acctrec.db;

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
import com.tourapp.tour.genled.db.*;
import com.tourapp.tour.acctrec.db.event.*;
import com.tourapp.model.tour.booking.db.*;
import com.tourapp.tour.base.field.*;
import com.tourapp.model.tour.acctrec.db.*;

/**
 *  ArTrx - A/R Open File.
 */
public class ArTrx extends LinkTrx
     implements ArTrxModel
{
    private static final long serialVersionUID = 1L;

    public static final int CR_DR_SCREEN = ScreenConstants.DETAIL_MODE | 192;
    public static final int REFUND_SCREEN = ScreenConstants.DETAIL_MODE | 128;
    public static final int REFUND_PEND_SCREEN = ScreenConstants.DETAIL_MODE | 256;
    public static final int REFUND_CHECK_POST = ScreenConstants.DETAIL_MODE | 512;
    public static final int REFUND_CHECK_XML_PRINT = ScreenConstants.DETAIL_MODE | 2048;
    public static final int REFUND_CHECK_CANNED_PRINT = ScreenConstants.DETAIL_MODE | 4096;
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
            screen = Record.makeNewScreen(AR_TRX_LINK_TRX_GRID_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        else if ((iDocMode == ScreenConstants.DETAIL_MODE)
            || (iDocMode == ArTrx.DISTRIBUTION_SCREEN))
                screen = Record.makeNewScreen(AR_TRX_DIST_GRID_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        else if (iDocMode == ArTrx.CR_DR_SCREEN)
            screen = Record.makeNewScreen(CR_DR_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        else if (iDocMode == ArTrx.REFUND_SCREEN)
            screen = Record.makeNewScreen(REFUND_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        else if (iDocMode == ArTrx.REFUND_PEND_SCREEN)
            screen = Record.makeNewScreen(REFUND_PEND_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        else if (iDocMode == ArTrx.REFUND_CHECK_POST)
            screen = Record.makeNewScreen(REFUND_CHECK_POST_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        else if (iDocMode == ArTrx.REFUND_CHECK_XML_PRINT)
            screen = Record.makeNewScreen(REFUND_CHECK_XML_PRINT_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        else if (iDocMode == ArTrx.REFUND_CHECK_CANNED_PRINT)
            screen = Record.makeNewScreen(REFUND_CHECK_PRINT_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        else if ((iDocMode & ScreenConstants.MAINT_MODE) == ScreenConstants.MAINT_MODE)
            screen = Record.makeNewScreen(BOOKING_AR_TRX_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        else
            screen = Record.makeNewScreen(BOOKING_AR_TRX_GRID_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
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
            keyArea = this.makeIndex(DBConstants.UNIQUE, ID_KEY);
            keyArea.addKeyField(ID, DBConstants.ASCENDING);
        }
        if (iKeyArea == 1)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, LINKED_TRX_ID_KEY);
            keyArea.addKeyField(LINKED_TRX_ID, DBConstants.ASCENDING);
            keyArea.addKeyField(LINKED_TRX_DESC_ID, DBConstants.ASCENDING);
        }
        if (iKeyArea == 2)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, BOOKING_ID_KEY);
            keyArea.addKeyField(BOOKING_ID, DBConstants.ASCENDING);
            keyArea.addKeyField(TRX_DATE, DBConstants.ASCENDING);
            keyArea.addKeyField(TRX_STATUS_ID, DBConstants.ASCENDING);
        }
        if (iKeyArea == 3)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, TRX_STATUS_ID_KEY);
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
    public void addDetailBehaviors(Rec recBooking)
    {
        this.addListener(new SubFileFilter((Record)recBooking, true));
        this.addListener(new InitArTrxHandler((Record)recBooking));
        if (this.getRecordOwner() instanceof GridScreenParent)
            ((BaseField)recBooking.getField(BookingModel.ID)).addListener(new FieldReSelectHandler((GridScreenParent)this.getRecordOwner()));
        if (recBooking != null)
        {   // Sub counts must be first.
            this.addListener(new SubCountHandler((BaseField)recBooking.getField(BookingModel.BALANCE), ArTrx.AMOUNT, true, true));
        
            this.addListener(new ArTrxInvoiceSubCountHandler(null, ArTrx.AMOUNT, true, true));
        }
        this.addListener(new CheckBookingStatusHandler((BookingModel)recBooking));
        this.addListener(new UpdateArTrxAcctDetailHandler((Record)recBooking));
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

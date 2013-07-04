
package com.tourgeek.tour.product.tour.detail.db;

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
import org.jbundle.thin.base.screen.*;
import com.tourgeek.model.tour.product.tour.detail.db.*;

/**
 *  TourHeaderAirHeader - Tour Ticket Header Detail.
 */
public class TourHeaderAirHeader extends TourSub
     implements TourHeaderAirHeaderModel
{
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor.
     */
    public TourHeaderAirHeader()
    {
        super();
    }
    /**
     * Constructor.
     */
    public TourHeaderAirHeader(RecordOwner screen)
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
        return (m_tableName == null) ? Record.formatTableNames(TOUR_HEADER_AIR_HEADER_FILE, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "Tour ticket header";
    }
    /**
     * Get the Database Name.
     */
    public String getDatabaseName()
    {
        return "product";
    }
    /**
     * Is this a local (vs remote) file?.
     */
    public int getDatabaseType()
    {
        return DBConstants.LOCAL | DBConstants.USER_DATA;
    }
    /**
     * Make a default screen.
     */
    public ScreenParent makeScreen(ScreenLoc itsLocation, ComponentParent parentScreen, int iDocMode, Map<String,Object> properties)
    {
        ScreenParent screen = null;
        if ((iDocMode & ScreenConstants.MAINT_MODE) == ScreenConstants.MAINT_MODE)
            screen = Record.makeNewScreen(TOUR_HEADER_AIR_HEADER_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        else if ((iDocMode & ScreenConstants.DISPLAY_MODE) == ScreenConstants.DISPLAY_MODE)
            screen = Record.makeNewScreen(TOUR_HEADER_AIR_HEADER_GRID_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        else if ((iDocMode & ScreenConstants.MAINT_MODE) == ScreenConstants.MAINT_MODE)
            screen = Record.makeNewScreen(TOUR_HEADER_AIR_HEADER_SFIELD_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        else
            screen = super.makeScreen(itsLocation, parentScreen, iDocMode, properties);
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
        //if (iFieldSeq == 3)
        //  field = new TourHeaderOptionField(this, TOUR_HEADER_OPTION_ID, 8, null, null);
        //if (iFieldSeq == 4)
        //  field = new ModifyCodeField(this, MODIFY_CODE, 1, null, null);
        //if (iFieldSeq == 5)
        //  field = new ModifyTourSubField(this, MODIFY_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 6)
        {
            field = new StringField(this, AIRLINE_CODE, 2, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 7)
            field = new ShortField(this, AIRLINE_IATA, 4, null, null);
        if (iFieldSeq == 8)
            field = new StringField(this, AIRLINE_DESC, 16, null, null);
        if (iFieldSeq == 9)
            field = new ShortField(this, CONJUNCTION, 1, null, null);
        if (iFieldSeq == 10)
        {
            field = new StringField(this, ENDORSEMENTS, 29, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 11)
        {
            field = new StringField(this, ORIGIN_DEST, 13, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 12)
            field = new StringField(this, BOOKING_REFERENCE, 13, null, null);
        if (iFieldSeq == 13)
        {
            field = new StringField(this, TOUR_CODE, 14, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 14)
        {
            field = new BooleanField(this, TOTAL_FARE_BASIS, 1, null, new Boolean(false));
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 15)
        {
            field = new FloatField(this, FARE, 9, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 16)
        {
            field = new FloatField(this, EQUIVALENT, 8, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 17)
        {
            field = new StringField(this, CURRENCY_CODE, 3, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 18)
        {
            field = new FloatField(this, TAX_PERCENT, 5, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 19)
        {
            field = new FloatField(this, TAX_1, 9, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 20)
        {
            field = new StringField(this, TAX_1_DESC, 2, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 21)
        {
            field = new FloatField(this, TAX_2, 9, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 22)
        {
            field = new StringField(this, TAX_2_DESC, 2, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 23)
        {
            field = new FloatField(this, TOTAL, 9, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 24)
            field = new StringField(this, COMMISSION, 10, null, "      10   ");
        if (iFieldSeq == 25)
            field = new StringField(this, TAX, 10, null, "      8   ");
        if (iFieldSeq == 26)
            field = new StringField(this, COMMISSION_RATE, 5, null, "  10 ");
        if (iFieldSeq == 27)
            field = new StringField(this, AGENT, 10, null, " AGENT");
        if (iFieldSeq == 28)
            field = new StringField(this, INTERNATIONAL, 3, null, "X/");
        if (iFieldSeq == 29)
        {
            field = new FloatField(this, COMM_PERCENT, 5, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 30)
            field = new FloatField(this, COMM_AMOUNT, 9, null, null);
        if (iFieldSeq == 31)
            field = new StringField(this, TICKET_BY, 1, null, "U");
        if (iFieldSeq == 32)
        {
            field = new FloatField(this, NET_FARE, 9, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 33)
        {
            field = new FloatField(this, OVERRIDE_PERCENT, 5, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 34)
        {
            field = new FloatField(this, OVERRIDE_AMOUNT, 9, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 35)
        {
            field = new FloatField(this, NET_COST, 9, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 36)
        {
            field = new FloatField(this, TK_OR_COLL, 9, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == 37)
        {
            field = new FloatField(this, ARC_COST, 9, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == 38)
        {
            field = new StringField(this, PNR, 15, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 39)
        {
            field = new BooleanField(this, VOID, 1, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 40)
        {
            field = new DateField(this, VOID_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 41)
        {
            field = new StringField(this, EXCH_TICKET, 20, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 42)
        {
            field = new DateField(this, DEP_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 43)
        {
            field = new BooleanField(this, CREDIT, 1, null, new Boolean(false));
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 44)
        {
            field = new StringField(this, COMMENT_1, 20, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 45)
        {
            field = new StringField(this, COMMENT_2, 20, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 46)
        {
            field = new StringField(this, COMMENT_3, 20, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 47)
        {
            field = new StringField(this, CRS_CONF, 20, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 48)
        {
            field = new StringField(this, CRS_STATUS, 2, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 49)
        {
            field = new StringField(this, FREQ_FLIER, 20, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 50)
        {
            field = new StringField(this, FARE_1, 60, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 51)
        {
            field = new StringField(this, FARE_2, 60, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 52)
        {
            field = new StringField(this, FARE_3, 60, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
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
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, TOUR_HEADER_OPTION_ID_KEY);
            keyArea.addKeyField(TOUR_HEADER_OPTION_ID, DBConstants.ASCENDING);
        }
        if (keyArea == null)
            keyArea = super.setupKey(iKeyArea);     
        return keyArea;
    }

}

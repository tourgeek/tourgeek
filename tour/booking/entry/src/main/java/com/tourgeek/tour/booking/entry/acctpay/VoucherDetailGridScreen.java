
package com.tourgeek.tour.booking.entry.acctpay;

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
import com.tourgeek.tour.booking.detail.db.*;
import com.tourgeek.tour.acctpay.db.*;
import com.tourgeek.tour.booking.db.event.*;

/**
 *  VoucherDetailGridScreen - Voucher product detail grid screen.
 */
public class VoucherDetailGridScreen extends DetailGridScreen
{
    /**
     * Default constructor.
     */
    public VoucherDetailGridScreen()
    {
        super();
    }
    /**
     * Constructor.
     * @param record The main record for this screen.
     * @param itsLocation The location of this component within the parent.
     * @param parentScreen The parent screen.
     * @param fieldConverter The field this screen field is linked to.
     * @param iDisplayFieldDesc Do I display the field desc?.
     */
    public VoucherDetailGridScreen(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
    {
        this();
        this.init(record, itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, properties);
    }
    /**
     * Initialize class fields.
     */
    public void init(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
    {
        super.init(record, itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, properties);
    }
    /**
     * Get the screen display title.
     */
    public String getTitle()
    {
        return "Voucher product detail grid screen";
    }
    /**
     * VoucherDetailGridScreen Method.
     */
    public VoucherDetailGridScreen(Record recHeader, Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object>
     properties)
    {
        this();
        this.init(recHeader, record, itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, properties);
    }
    /**
     * Override this to open the main file.
     * <p />You should pass this record owner to the new main file (ie., new MyNewTable(thisRecordOwner)).
     * @return The new record.
     */
    public Record openMainRecord()
    {
        return new BookingDetail(this);
    }
    /**
     * OpenHeaderRecord Method.
     */
    public Record openHeaderRecord()
    {
        return new ApTrx(this);
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        this.getHeaderRecord().getField(ApTrx.ID).addListener(new FieldReSelectHandler(this));
        
        this.setEnabled(false);   // Do not do setEdititing(false) as this will incorrectly optomize the query
        this.setAppending(false);
    }
    /**
     * SetupSFields Method.
     */
    public void setupSFields()
    {
        Record mergeTable = this.getMainRecord();
        this.addColumn(new MultipleTableFieldConverter(mergeTable, BookingDetail.STATUS_SUMMARY));
        
        this.addColumn(new MultipleTableFieldConverter(mergeTable, BookingDetail.DETAIL_DATE));
        this.addColumn(new ProductDescConverter(mergeTable));
        this.addColumn(new MultipleTableFieldConverter(mergeTable, BookingDetail.PRODUCT_STATUS_ID));
        
        this.addColumn(new MultipleTableFieldConverter(mergeTable, BookingDetail.TOTAL_COST_LOCAL));
    }
    /**
     * Make a sub-screen.
     * @return the new sub-screen.
     */
    public BasePanel makeSubScreen()
    {
        return new ApTrxHeaderScreen(null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, null);
    }

}

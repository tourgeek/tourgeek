/**
 * @(#)DepEstScreen.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.acctpay.screen.findepest;

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
import com.tourapp.tour.acctpay.db.*;
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.base.db.*;
import com.tourapp.tour.product.tour.db.*;
import com.tourapp.tour.genled.db.*;
import com.tourapp.tour.acctpay.screen.trx.*;

/**
 *  DepEstScreen - Departure estimates.
 */
public class DepEstScreen extends Screen
{
    /**
     * Default constructor.
     */
    public DepEstScreen()
    {
        super();
    }
    /**
     * Constructor.
     * @param record The main record for this screen.
     * @param itsLocation The location of this component within the parent.
     * @param parentScreen The parent screen.
     * @param fieldConverter The field this screen field is linked to.
     * @param iDisplayFieldDesc Do I display the field desc?
     * @param properties Addition properties to pass to the screen.
     */
    public DepEstScreen(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
        return "Departure estimates";
    }
    /**
     * Override this to open the main file.
     * <p />You should pass this record owner to the new main file (ie., new MyNewTable(thisRecordOwner)).
     * @return The new record.
     */
    public Record openMainRecord()
    {
        return new ApTrx(this);
    }
    /**
     * Override this to open the other files in the query.
     */
    public void openOtherRecords()
    {
        super.openOtherRecords();
        new TrxStatus(this);
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        // Don't call super.
        this.addMainKeyBehavior();
        TrxStatus recTrxStatus = (TrxStatus)this.getRecord(TrxStatus.TRX_STATUS_FILE);
        
        EnableScreenHandler behavior = new EnableScreenHandler(ApTrx.TRX_STATUS_ID);
        this.getMainRecord().addListener(behavior);
        recTrxStatus.getTrxStatusID(TransactionType.ACCTPAY, ApTrx.AP_TRX_FILE, ApTrx.DEPARTURE_EST_MANUAL);
        behavior.addComparison(recTrxStatus.getField(TrxStatus.ID).getData());
        recTrxStatus.getTrxStatusID(TransactionType.ACCTPAY, ApTrx.AP_TRX_FILE, ApTrx.DEP_ESTIMATE);
        behavior.addComparison(recTrxStatus.getField(TrxStatus.ID).getData());
        
        recTrxStatus.getTrxStatusID(TransactionType.ACCTPAY, ApTrx.AP_TRX_FILE, ApTrx.DEPARTURE_EST_MANUAL);
        this.getMainRecord().getField(ApTrx.TRX_STATUS_ID).addListener(new InitFieldHandler(recTrxStatus.getField(TrxStatus.ID)));
        this.getMainRecord().getField(ApTrx.TRX_STATUS_ID).setEnabled(false);
        
        this.getMainRecord().addListener(new EnableOnValidHandler(ApTrx.VENDOR_ID, false, true));    // Don't allow changes here
        this.getMainRecord().addListener(new EnableOnValidHandler(ApTrx.TOUR_ID, false, true));    // Don't allow changes here        
        
        this.getMainRecord().getField(ApTrx.TOUR_ID).addListener(new InitOnceFieldHandler(null));
        this.getMainRecord().addListener(new UpdateDepEstHandler(null));
        
        this.getMainRecord().getField(ApTrx.DEPARTURE_ESTIMATE).addListener(new MoveOnChangeHandler(this.getMainRecord().getField(ApTrx.TRX_STATUS_ID), recTrxStatus.getField(TransactionType.ID)));
        this.getMainRecord().getField(ApTrx.DEPARTURE_EXCHANGE).addListener(new MoveOnChangeHandler(this.getMainRecord().getField(ApTrx.TRX_STATUS_ID), recTrxStatus.getField(TransactionType.ID)));
        this.getMainRecord().getField(ApTrx.DEPARTURE_ESTIMATE_LOCAL).addListener(new MoveOnChangeHandler(this.getMainRecord().getField(ApTrx.TRX_STATUS_ID), recTrxStatus.getField(TransactionType.ID)));
        
        Record recVendor = ((ReferenceField)this.getMainRecord().getField(ApTrx.VENDOR_ID)).getReferenceRecord(this);
        Record recCurrencys = ((ReferenceField)recVendor.getField(Vendor.CURRENCYS_ID)).getReferenceRecord(this);
        this.getMainRecord().getField(ApTrx.VENDOR_ID).addListener(new MoveOnChangeHandler(this.getMainRecord().getField(ApTrx.DEPARTURE_EXCHANGE), recCurrencys.getField(Currencys.LAST_RATE)));
        this.getMainRecord().getField(ApTrx.DEPARTURE_ESTIMATE).addListener(new MoveOnChangeHandler(this.getMainRecord().getField(ApTrx.DEPARTURE_EXCHANGE), recCurrencys.getField(Currencys.LAST_RATE)));
        this.getMainRecord().getField(ApTrx.DEPARTURE_ESTIMATE).addListener(new CalcBalanceHandler(this.getMainRecord().getField(ApTrx.DEPARTURE_ESTIMATE_LOCAL), this.getMainRecord().getField(ApTrx.DEPARTURE_EXCHANGE), this.getMainRecord().getField(ApTrx.DEPARTURE_ESTIMATE), CalcBalanceHandler.MULTIPLY, true));
        this.getMainRecord().getField(ApTrx.DEPARTURE_EXCHANGE).addListener(new CalcBalanceHandler(this.getMainRecord().getField(ApTrx.DEPARTURE_ESTIMATE_LOCAL), this.getMainRecord().getField(ApTrx.DEPARTURE_EXCHANGE), this.getMainRecord().getField(ApTrx.DEPARTURE_ESTIMATE), CalcBalanceHandler.MULTIPLY, true));
        this.getMainRecord().getField(ApTrx.DEPARTURE_ESTIMATE_LOCAL).addListener(new CalcBalanceHandler(this.getMainRecord().getField(ApTrx.DEPARTURE_EXCHANGE), this.getMainRecord().getField(ApTrx.DEPARTURE_ESTIMATE_LOCAL), this.getMainRecord().getField(ApTrx.DEPARTURE_ESTIMATE), CalcBalanceHandler.DIVIDE, true));
        
        Record recTour = ((ReferenceField)this.getMainRecord().getField(ApTrx.TOUR_ID)).getReferenceRecord(this);
        this.getMainRecord().getField(ApTrx.TOUR_ID).addListener(new MoveOnChangeHandler(this.getMainRecord().getField(ApTrx.START_SERVICE_DATE), recTour.getField(Tour.DEPARTURE_DATE)));
        this.getMainRecord().getField(ApTrx.START_SERVICE_DATE).addListener(new MoveOnChangeHandler(this.getMainRecord().getField(ApTrx.END_SERVICE_DATE), this.getMainRecord().getField(ApTrx.START_SERVICE_DATE)));
        
        this.getMainRecord().addListener(new ValidateFieldHandler(ApTrx.TOUR_ID, null, false));
    }
    /**
     * Set up all the screen fields.
     */
    public void setupSFields()
    {
        Record recVendor = ((ReferenceField)this.getMainRecord().getField(ApTrx.VENDOR_ID)).getReferenceRecord(this);
        if (recVendor != null)
        {
            Record recCurrencys = ((ReferenceField)recVendor.getField(Vendor.CURRENCYS_ID)).getReferenceRecord(this);
            recVendor.getField(Vendor.CURRENCYS_ID).addListener(new ReadSecondaryHandler(recCurrencys));
        }
        ScreenField screenField = null;
        this.getRecord(ApTrx.kApTrxFile).getField(ApTrx.kCode).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        BaseApplication application = (BaseApplication)this.getTask().getApplication();
        String ESTIMATE_LOOKUP = "Estimate lookup";
        screenField = new SButtonBox(this.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, null, application.getResources(ResourceConstants.ACCTPAY_RESOURCE, true).getString(ESTIMATE_LOOKUP), MenuConstants.DISPLAY, MenuConstants.DISPLAY, null);
        this.getRecord(ApTrx.kApTrxFile).getField(ApTrx.kVendorID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.kApTrxFile).getField(ApTrx.kTourID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.kApTrxFile).getField(ApTrx.kTrxStatusID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.kApTrxFile).getField(ApTrx.kDescription).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.kApTrxFile).getField(ApTrx.kStartServiceDate).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.kApTrxFile).getField(ApTrx.kEndServiceDate).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.kApTrxFile).getField(ApTrx.kDepartureEstimate).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.kApTrxFile).getField(ApTrx.kDepartureExchange).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.kApTrxFile).getField(ApTrx.kDepartureEstimateLocal).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
    }
    /**
     * Enable or disable this control.
     * @param bEnable If true, enable this field.
     */
    public void setEnabled(boolean bEnable)
    {
        // Don't call inherrited
        for (int i = 0; i < this.getMainRecord().getFieldCount(); i++)
        {
            this.getMainRecord().getField(i).setEnabled(bEnable);
        }
        this.getMainRecord().getField(ApTrx.TRX_STATUS_ID).setEnabled(false);
        this.getMainRecord().getField(ApTrx.ID).setEnabled(true);
    }
    /**
     * Returns the Component that should receive the focus after aComponent.
     * @param sfCurrent Currently focused control.
     * @param iSelectField Type of field to select next (first, next, prev, last).
     * @return Control to select next.
     */
    public ScreenField getComponentAfter(ScreenField sfCurrent, int iSelectField)
    {
        if (iSelectField == DBConstants.SELECT_FIRST_FIELD)
        {
            if (this.getMainRecord().getField(ApTrx.DESCRIPTION).getComponent(0) != null)
                if (((ScreenField)this.getMainRecord().getField(ApTrx.DESCRIPTION).getComponent(0)).isEnabled())
            {   // The screen is enabled, focus on the vendor code
                return (ScreenField)((ReferenceField)this.getMainRecord().getField(ApTrx.VENDOR_ID)).getReferenceRecord().getField(Vendor.CODE).getComponent(0);
            }
        }
        return super.getComponentAfter(sfCurrent, iSelectField);
    }
    /**
     * Process the command.
     * <br />Step 1 - Process the command if possible and return true if processed.
     * <br />Step 2 - If I can't process, pass to all children (with me as the source).
     * <br />Step 3 - If children didn't process, pass to parent (with me as the source).
     * <br />Note: Never pass to a parent or child that matches the source (to avoid an endless loop).
     * @param strCommand The command to process.
     * @param sourceSField The source screen field (to avoid echos).
     * @param iCommandOptions If this command creates a new screen, create in a new window?
     * @return true if success.
     */
    public boolean doCommand(String strCommand, ScreenField sourceSField, int iCommandOptions)
    {
        if (MenuConstants.DISPLAY.equalsIgnoreCase(strCommand))
        {
            iCommandOptions = ScreenConstants.USE_NEW_WINDOW | ScreenConstants.DONT_PUSH_TO_BROSWER;
            boolean bReadCurrentRecord = false;
            int iDocMode = ApTrx.TOUR_AP_SCREEN | ScreenConstants.SELECT_MODE;
            Map<String,Object> properties = new Hashtable<String,Object>();
            properties.put(ApTrxClassField.DISPLAY_TYPE_PARAM, Integer.toString(ApTrxClassField.ALL));
            if (!this.getMainRecord().getField(ApTrx.TOUR_ID).isNull())
                properties.put(DBParams.HEADER_OBJECT_ID, this.getMainRecord().getField(ApTrx.TOUR_ID).toString());
        
            return (this.onForm(null, iDocMode, bReadCurrentRecord, iCommandOptions, properties) != null);
        }
        return super.doCommand(strCommand, sourceSField, iCommandOptions);
    }

}

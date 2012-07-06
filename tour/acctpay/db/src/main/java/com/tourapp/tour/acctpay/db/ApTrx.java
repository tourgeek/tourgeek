/**
  * @(#)ApTrx.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.tour.acctpay.db;

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
import com.tourapp.tour.genled.db.*;
import com.tourapp.tour.acctpay.db.event.*;
import com.tourapp.model.tour.product.base.db.*;
import com.tourapp.model.tour.booking.db.*;
import com.tourapp.model.tour.product.tour.db.*;
import com.tourapp.model.tour.booking.detail.db.*;
import com.tourapp.tour.base.field.*;
import com.tourapp.tour.base.db.*;
import com.tourapp.model.tour.acctpay.db.*;

/**
 *  ApTrx - Accounts Payable transaction file.
 */
public class ApTrx extends Trx
     implements ApTrxModel
{
    private static final long serialVersionUID = 1L;

    public static final int AP_TRX_TYPE = 0;
    public static final int TICKET_TRX_TYPE = ProductTypeModel.AIR_ID;
    public static final int BROKER_DIST_SCREEN = ScreenConstants.DETAIL_MODE | 32768;
    public static final int BROKER_DIST_GRID_SCREEN = ScreenConstants.DETAIL_MODE | 16384;
    public static final int TOUR_AP_SCREEN = ScreenConstants.DETAIL_MODE | 4096;
    public static final int VENDOR_AP_SCREEN = ScreenConstants.DETAIL_MODE | 8192;
    public static final int PAYMENT_HISTORY = ScreenConstants.DETAIL_MODE | 65536;
    public static final int PAYMENT_DISTRIBUTION = ScreenConstants.DETAIL_MODE | 262144;
    public static final int DISTRIBUTION_SCREEN = ScreenConstants.DETAIL_MODE | 131072;
    /**
     * Default constructor.
     */
    public ApTrx()
    {
        super();
    }
    /**
     * Constructor.
     */
    public ApTrx(RecordOwner screen)
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
        return (m_tableName == null) ? Record.formatTableNames(AP_TRX_FILE, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "A/P Open Item";
    }
    /**
     * Get the Database Name.
     */
    public String getDatabaseName()
    {
        return "acctpay";
    }
    /**
     * Is this a local (vs remote) file?.
     */
    public int getDatabaseType()
    {
        return DBConstants.REMOTE | DBConstants.BASE_TABLE_CLASS | DBConstants.SHARED_TABLE | DBConstants.USER_DATA;
    }
    /**
     * MakeScreen Method.
     */
    public ScreenParent makeScreen(ScreenLoc itsLocation, ComponentParent parentScreen, int iDocMode, Map<String,Object> properties)
    {
        ScreenParent screen = null;
        if ((iDocMode & ApTrx.BROKER_DIST_GRID_SCREEN) == ApTrx.BROKER_DIST_GRID_SCREEN)
            screen = Record.makeNewScreen(BROKER_DIST_GRID_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        else if ((iDocMode & ApTrx.BROKER_DIST_SCREEN) == ApTrx.BROKER_DIST_SCREEN)
            screen = Record.makeNewScreen(BROKER_DIST_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        else if ((iDocMode & ApTrx.TOUR_AP_SCREEN) == ApTrx.TOUR_AP_SCREEN)
            screen = Record.makeNewScreen(TOUR_AP_TRX_GRID_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        else if ((iDocMode & ApTrx.VENDOR_AP_SCREEN) == ApTrx.VENDOR_AP_SCREEN)
            screen = Record.makeNewScreen(VENDOR_AP_TRX_GRID_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        else if ((iDocMode & ApTrx.PAYMENT_HISTORY) == ApTrx.PAYMENT_HISTORY)
            screen = Record.makeNewScreen(PAYMENT_HISTORY_GRID_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        else if ((iDocMode & ApTrx.PAYMENT_DISTRIBUTION) == ApTrx.PAYMENT_DISTRIBUTION)
            screen = Record.makeNewScreen(PAYMENT_HISTORY_LINK_TRX_GRID_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        else if ((iDocMode & ApTrx.DISTRIBUTION_SCREEN) == ApTrx.DISTRIBUTION_SCREEN)
            screen = Record.makeNewScreen(AP_TRX_DIST_GRID_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        else if ((iDocMode & ScreenConstants.DETAIL_MODE) == ScreenConstants.DETAIL_MODE)
            screen = Record.makeNewScreen(VOUCHER_DETAIL_GRID_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        else if ((iDocMode & ScreenConstants.MAINT_MODE) == ScreenConstants.MAINT_MODE)
            screen = Record.makeNewScreen(AP_TRX_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        else
            screen = Record.makeNewScreen(VENDOR_AP_TRX_GRID_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
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
        //  field = new TrxStatusField(this, TRX_STATUS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == 4)
        //  field = new ApTrx_TrxUserID(this, TRX_USER_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 5)
            field = new StringField(this, CODE, 28, null, null);
        if (iFieldSeq == 6)
            field = new StringField(this, DESCRIPTION, 50, null, null);
        if (iFieldSeq == 7)
        {
            field = new IntegerField(this, AP_TRX_TYPE_ID, Constants.DEFAULT_FIELD_LENGTH, null, new Integer(ApTrx.AP_TRX_TYPE));
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 8)
            field = new BooleanField(this, ACTIVE_TRX, Constants.DEFAULT_FIELD_LENGTH, null, new Boolean(true));
        if (iFieldSeq == 9)
            field = new VendorField(this, VENDOR_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 10)
            field = new TourField(this, TOUR_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 11)
            field = new ReferenceField(this, PRODUCT_TYPE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 12)
        {
            field = new DateField(this, START_SERVICE_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 13)
        {
            field = new DateField(this, END_SERVICE_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 14)
        {
            field = new DateField(this, FINALIZATION_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 15)
            field = new DateField(this, ORDER_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 16)
        {
            field = new DateField(this, ACKNOWLEDGE_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 17)
        {
            field = new DateField(this, ACKNOWLEDGED_ON, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 18)
            field = new ReferenceField(this, ACKNOWLEDGED_BY, 16, null, null);
        if (iFieldSeq == 19)
        {
            field = new StringField(this, VENDOR_CONFIRMATION_NO, 20, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 20)
            field = new StringField(this, VENDOR_CONF_STATUS, 2, null, null);
        if (iFieldSeq == 21)
            field = new FullCurrencyField(this, DEPARTURE_ESTIMATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 22)
        {
            field = new RealField(this, DEPARTURE_EXCHANGE, 10, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 23)
            field = new CurrencyField(this, DEPARTURE_ESTIMATE_LOCAL, 10, null, null);
        if (iFieldSeq == 24)
        {
            field = new DateField(this, DEPARTURE_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setVirtual(true);
        }
        if (iFieldSeq == 25)
            field = new StringField(this, INVOICE_NO, 28, null, null);
        if (iFieldSeq == 26)
            field = new DateField(this, INVOICE_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 27)
            field = new FullCurrencyField(this, INVOICE_AMOUNT, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 28)
            field = new CurrencyField(this, INVOICE_LOCAL, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 29)
            field = new FullCurrencyField(this, INVOICE_BALANCE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 30)
            field = new CurrencyField(this, INVOICE_BALANCE_LOCAL, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 31)
            field = new FullCurrencyField(this, AMOUNT_SELECTED, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 32)
            field = new VendorField(this, DRAFT_VENDOR_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 33)
            field = new ApTrxField(this, PREPAYMENT_AP_TRX_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 34)
            field = new DateField(this, VOUCHER_BASED_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 35)
            field = new DateTimeField(this, TRX_ENTRY, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 36)
            field = new AccountField(this, ACCOUNT_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 37)
        {
            field = new StringField(this, TICKET_NUMBER, 28, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 38)
        {
            field = new DateField(this, ISSUE_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 39)
            field = new AirlineField(this, AIRLINE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 40)
            field = new DateField(this, ARC_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 41)
            field = new DateField(this, ARC_PAY, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 42)
            field = new StringField(this, INTL, 1, null, null);
        if (iFieldSeq == 43)
        {
            field = new BooleanField(this, CREDIT_CARD, 1, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 44)
            field = new CurrencyField(this, TOTAL, 10, null, null);
        if (iFieldSeq == 45)
            field = new CurrencyField(this, FARE, 10, null, null);
        if (iFieldSeq == 46)
            field = new CurrencyField(this, COMM_AMOUNT, 9, null, null);
        if (iFieldSeq == 47)
            field = new PercentField(this, COMM_PERCENT, 5, null, null);
        if (iFieldSeq == 48)
            field = new CurrencyField(this, TAX_AMOUNT, 9, null, null);
        if (iFieldSeq == 49)
        {
            field = new PercentField(this, TAX_PERCENT, 5, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 50)
            field = new CurrencyField(this, NET_FARE, 10, null, null);
        if (iFieldSeq == 51)
            field = new CurrencyField(this, COST_AMOUNT, 10, null, null);
        if (iFieldSeq == 52)
            field = new PercentField(this, OVERRIDE_PERCENT, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 53)
            field = new CurrencyField(this, OVERRIDE_AMOUNT, 10, null, null);
        if (iFieldSeq == 54)
            field = new DateField(this, OVERRIDE_PAID_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 55)
            field = new CurrencyField(this, OVERRIDE_PAID, 10, null, null);
        if (iFieldSeq == 56)
            field = new DateField(this, VOID_DATE, Constants.DEFAULT_FIELD_LENGTH, null, null);
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
            keyArea = this.makeIndex(DBConstants.SECONDARY_KEY, "Code");
            keyArea.addKeyField(CODE, DBConstants.ASCENDING);
        }
        if (iKeyArea == 2)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "VendorID");
            keyArea.addKeyField(VENDOR_ID, DBConstants.ASCENDING);
            keyArea.addKeyField(ACTIVE_TRX, DBConstants.ASCENDING);
            keyArea.addKeyField(START_SERVICE_DATE, DBConstants.ASCENDING);
        }
        if (iKeyArea == 3)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "TourID");
            keyArea.addKeyField(TOUR_ID, DBConstants.ASCENDING);
            keyArea.addKeyField(VENDOR_ID, DBConstants.ASCENDING);
            keyArea.addKeyField(PRODUCT_TYPE_ID, DBConstants.ASCENDING);
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
        super.addMasterListeners();
        this.getField(ApTrx.TRX_STATUS_ID).addListener(new UpdateActiveTrxStatus(this.getField(ApTrx.ACTIVE_TRX)));
        this.getField(ApTrx.DEPARTURE_DATE).addListener(new GetDepartureDateHandler((TourField)this.getField(ApTrx.TOUR_ID)));
        this.addListener(new NoDeleteModifyHandler(true, false));
        Record recApControl = null;
        RecordOwner recordOwner = this.findRecordOwner();
        if (recordOwner != null)
            recApControl = (Record)recordOwner.getRecord(ApControl.AP_CONTROL_FILE);
        if (recApControl == null)
        {
            recApControl = new ApControl(recordOwner);
            this.addListener(new FreeOnFreeHandler(recApControl));
        }
        if (recApControl.getField(ApControl.AUTO_AP_CODE).getState() == true)
            this.addListener(new MoveIDToCodeHandler((String)null));
        this.addListener(new ApTrxStatusHandler(null));
    }
    /**
     * Get the record type from the field that specifies the record type.
     * (Override this).
     * @return The record type (as an object).
     */
    public BaseField getSharedRecordTypeKey()
    {
        return this.getField(ApTrx.AP_TRX_TYPE_ID);
    }
    /**
     * Get the shared record that goes with this key.
     * (Always override this).
     * @param objKey The value that specifies the record type.
     * @return The correct (new) record for this type (or null if none).
     */
    public Record createSharedRecord(Object objKey, RecordOwner recordOwner)
    {
        if (objKey instanceof Integer)
        {
            int iApTrxTypeID = ((Integer)objKey).intValue();
            if (iApTrxTypeID == ApTrx.AP_TRX_TYPE)
                return this;
            if (iApTrxTypeID == ApTrx.TICKET_TRX_TYPE)
                return new TicketTrx(recordOwner);
        }
        return null;
    }
    /**
     * Get the product category for this tour.
     */
    public ProductCategoryModel getProductCategory()
    {
        if (this.getField(ApTrx.TOUR_ID).isNull())
            return null;    // No tour
        Record recTour = ((ReferenceField)this.getField(ApTrx.TOUR_ID)).getReference();
        if (recTour == null)
            return null;
        TourHeaderModel recTourHeader = (TourHeaderModel)((ReferenceField)recTour.getField(TourModel.TOUR_HEADER_ID)).getReference();
        if (recTourHeader == null)
            return null;
        ProductCategoryModel recProductCategory = (ProductCategoryModel)((ReferenceField)recTourHeader.getField(TourHeaderModel.PRODUCT_CAT_ID)).getReference();
        return recProductCategory;
    }
    /**
     * Convert the command to the screen document type.
     * @param strCommand The command text.
     * @param The standard document type (MAINT/DISPLAY/SELECT/MENU/etc).
     */
    public int commandToDocType(String strCommand)
    {
        if (PaymentHistory.PAYMENT_HISTORY_FILE.equalsIgnoreCase(strCommand))
            return ApTrx.PAYMENT_HISTORY;
        if (PaymentHistory.PAYMENT_DISTRIBUTION.equalsIgnoreCase(strCommand))
            return ApTrx.PAYMENT_DISTRIBUTION;
        if (AcctDetailDist.DIST_DISTRIBUTION.equalsIgnoreCase(strCommand))
            return ApTrx.DISTRIBUTION_SCREEN;
        return super.commandToDocType(strCommand);
    }
    /**
     * Add a new voucher for this product detail
     * @param tblBookingDetail Product detail file.
     * @param fldTourID Tour ID (field).
     * @param fldVendorID Vendor ID (field).
     * @param fldVoucherType Unique ID (field).
     * @return An error code.
     */
    public ApTrx addNewApTrx(BaseField fldTourID, Vendor recVendor, int iProductTypeID)
    {
        ApTrx recApTrx = null;
        try   {
            this.getField(ApTrx.AP_TRX_TYPE_ID).setValue(ApTrx.AP_TRX_TYPE);                
            if (iProductTypeID == ProductTypeModel.AIR_ID)
                this.getField(ApTrx.AP_TRX_TYPE_ID).setValue(ApTrx.TICKET_TRX_TYPE);                
            this.addNew();
            recApTrx = (ApTrx)this.getTable().getCurrentTable().getRecord();
            recApTrx.getField(ApTrx.TOUR_ID).moveFieldToThis(fldTourID);
            recApTrx.getField(ApTrx.VENDOR_ID).moveFieldToThis(recVendor.getField(Vendor.ID));
            recApTrx.getField(ApTrx.PRODUCT_TYPE_ID).setValue(iProductTypeID);
        
            TrxStatus recTrxStatus = (TrxStatus)((ReferenceField)recApTrx.getField(ApTrx.TRX_STATUS_ID)).getReferenceRecord();
            int iTrxStatusID = recTrxStatus.getTrxStatusID(TransactionType.ACCTPAY, ApTrx.AP_TRX_FILE, ApTrx.DEP_ESTIMATE);
            recApTrx.getField(ApTrx.TRX_STATUS_ID).setValue(iTrxStatusID);
            recApTrx.add();
            Object bookmark = recApTrx.getLastModified(DBConstants.DATA_SOURCE_HANDLE);
            recApTrx.setHandle(bookmark, DBConstants.DATA_SOURCE_HANDLE);
        } catch (DBException ex)   {
            ex.printStackTrace();
        }
        return recApTrx;
    }
    /**
     * Update the voucher for this product detail
     * @param tblBookingDetail Product detail file.
     * @param fldTourID Tour ID (field).
     * @param fldVendorID Vendor ID (field).
     * @param fldVoucherType Unique ID (field).
     * @return An error code.
     */
    public int updateThisApTrx(BaseTable tblBookingDetail, Record recTour, Vendor recVendor, int iProductTypeID)
    {
        int iErrorCode = DBConstants.NORMAL_RETURN;
        
        this.getField(ApTrx.DESCRIPTION).setString(DBConstants.BLANK);
        ((DateField)this.getField(ApTrx.START_SERVICE_DATE)).setData(null);
        ((DateField)this.getField(ApTrx.END_SERVICE_DATE)).setData(null);
        this.getField(ApTrx.DEPARTURE_ESTIMATE).setValue(0.00);
        this.getField(ApTrx.DEPARTURE_ESTIMATE_LOCAL).setValue(0.00);
        
        BookingDetailModel recBookingDetail = (BookingDetailModel)tblBookingDetail.getCurrentTable().getRecord();
        int iOldOpenMode = this.getOpenMode();
        try {
            if (this.getListener(UpdateDepEstHandler.class) == null)
                this.addListener(new UpdateDepEstHandler(null));
            this.setOpenMode(iOldOpenMode & ~DBConstants.OPEN_READ_ONLY);   // Allow write (I don't know where this file has been)
            this.edit();
        
            while (recBookingDetail != null)
            {
                boolean bProcessThisRecord = true;
                if (!recBookingDetail.getField(BookingDetailModel.AP_TRX_ID).isNull())
                    if (!recBookingDetail.getField(BookingDetailModel.AP_TRX_ID).equals(this.getField(ApTrx.ID)))
                        bProcessThisRecord = false;   // If this is already tacked to another ApTrx, skip it.
                if (!this.getField(ApTrx.TOUR_ID).equals(recBookingDetail.getField(BookingDetailModel.TOUR_ID)))
                    break;
                if (this.getField(ApTrx.VENDOR_ID).compareTo(recBookingDetail.getField(BookingDetailModel.VENDOR_ID)) != 0)
                    break;
                if (recVendor.getEditMode() == DBConstants.EDIT_CURRENT)
                    if (OperationTypeField.LIKE_TOGETHER_CODE.equalsIgnoreCase(recVendor.getField(Vendor.OPERATION_TYPE_CODE).toString()))
                        if (this.getField(ApTrx.PRODUCT_TYPE_ID).compareTo(recBookingDetail.getField(BookingDetailModel.PRODUCT_TYPE_ID)) != 0)
                            break;  // Each A/P Trx contains all detail items in this product type
        
                if (bProcessThisRecord)
                {
                    this.addBookingDetailInfo(recBookingDetail);
            
                    try
                    {
                        recBookingDetail.getTable().edit();
                        ((BaseField)recBookingDetail.getField(BookingDetailModel.AP_TRX_ID)).moveFieldToThis(this.getField(ApTrx.ID));
                        if (recBookingDetail.getEditMode() == DBConstants.EDIT_IN_PROGRESS)
                            recBookingDetail.getTable().set(recBookingDetail);   // Possible that the listeners re-wrote this record already.
                    }
                    catch (DBException ex)
                    {
                        iErrorCode = ex.getErrorCode();
                        return iErrorCode;
                    }
                }
        
                recBookingDetail = (BookingDetailModel)tblBookingDetail.next();
                if (recVendor.getEditMode() == DBConstants.EDIT_CURRENT)
                    if (OperationTypeField.INDIVIDUALLY_CODE.equalsIgnoreCase(recVendor.getField(Vendor.OPERATION_TYPE_CODE).toString()))
                        break;  // Each A/P Trx contains one detail item
            }
        
            this.set();
        }
        catch (DBException ex) {
            iErrorCode = ex.getErrorCode();
        } finally {
            this.setOpenMode(iOldOpenMode);   // Set it back
        }
        return iErrorCode;
    }
    /**
     * Add the information from this BookingDetail to this ApTrx.
     */
    public void addBookingDetailInfo(BookingDetailModel recBookingDetail)
    {
        double dProductCost = this.getField(ApTrx.DEPARTURE_ESTIMATE).getValue();
        dProductCost += recBookingDetail.getField(BookingDetailModel.TOTAL_COST).getValue();
        this.getField(ApTrx.DEPARTURE_ESTIMATE).setValue(dProductCost);
        double dProductCostLocal = this.getField(ApTrx.DEPARTURE_ESTIMATE_LOCAL).getValue();
        dProductCostLocal += recBookingDetail.getField(BookingDetailModel.TOTAL_COST_LOCAL).getValue();
        this.getField(ApTrx.DEPARTURE_ESTIMATE_LOCAL).setValue(dProductCostLocal);
        if (dProductCostLocal != 0)
            this.getField(ApTrx.DEPARTURE_EXCHANGE).setValue(dProductCost / dProductCostLocal);
        
        String strServiceDesc = this.getField(ApTrx.DESCRIPTION).getString();
        if (strServiceDesc.length() > 0)
            strServiceDesc += ", ";
        strServiceDesc += recBookingDetail.getProductDesc();
        this.getField(ApTrx.DESCRIPTION).setString(strServiceDesc);
        
        Date dateStart = ((DateField)this.getField(ApTrx.START_SERVICE_DATE)).getDateTime();
        Date dateEnd = ((DateField)this.getField(ApTrx.END_SERVICE_DATE)).getDateTime();
        Date date = recBookingDetail.getStartDate();
        if ((dateStart == null) || (date.getTime() < dateStart.getTime()))
            dateStart = date;
        date = recBookingDetail.getEndDate();
        if ((dateEnd == null) || (date.getTime() > dateEnd.getTime()))
            dateEnd = date;
        ((DateField)this.getField(ApTrx.START_SERVICE_DATE)).setDate(dateStart, DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
        ((DateField)this.getField(ApTrx.END_SERVICE_DATE)).setDate(dateEnd, DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
        
        this.getField(ApTrx.FINALIZATION_DATE).setValue(DateTimeField.currentTime());
        
        if (this.getField(ApTrx.VENDOR_ID).isNull())
            if (dProductCost == 0.0)
        {   // Special case - This booking detail is a place holder such as a tour component
            TrxStatus recTrxStatus = (TrxStatus)((ReferenceField)this.getField(ApTrx.TRX_STATUS_ID)).getReferenceRecord();
            int iTrxStatusID = recTrxStatus.getTrxStatusID(TransactionType.ACCTPAY, ApTrx.AP_TRX_FILE, ApTrx.NO_VOUCHER);
            this.getField(ApTrx.TRX_STATUS_ID).setValue(iTrxStatusID);
        }
    }
    /**
     * Read or Create the ApTrx record for this BookingDetail
     * and link the BookingDetail to it.
     */
    public int linkBookingDetailToApTrx(BookingDetailModel recBookingDetail)
    {
        if ((recBookingDetail == null)
            || (recBookingDetail.getEditMode() == DBConstants.EDIT_ADD)
            || (recBookingDetail.getEditMode() == DBConstants.EDIT_NONE)
            || (!recBookingDetail.getField(BookingDetailModel.AP_TRX_ID).isNull()))
                return DBConstants.ERROR_RETURN;
        // Record is current and aptrx is null.
        Vendor recVendor = (Vendor)((ReferenceField)recBookingDetail.getField(BookingDetailModel.VENDOR_ID)).getReference();
        if ((recVendor == null)
            || (recVendor.getEditMode() == DBConstants.EDIT_ADD)
            || (recVendor.getEditMode() == DBConstants.EDIT_NONE))
                return DBConstants.ERROR_RETURN;
        
        int iOldOpenMode = this.getOpenMode();
        try {
            if (this.getListener(UpdateDepEstHandler.class) == null)
                this.addListener(new UpdateDepEstHandler(null));
            this.setOpenMode(iOldOpenMode & ~DBConstants.OPEN_READ_ONLY);   // Allow write (I don't know where this file has been)
            this.addNew();  // Each A/P Trx contains one detail item
            if (OperationTypeField.INDIVIDUALLY_CODE.equalsIgnoreCase(recVendor.getField(Vendor.OPERATION_TYPE_CODE).toString()))
                this.addNew();  // Each A/P Trx contains one detail item
            else
            { // OperationTypeField.ALL_TOGETHER_CODE or OperationTypeField.LIKE_TOGETHER_CODE
                this.getField(ApTrx.TOUR_ID).moveFieldToThis((BaseField)recBookingDetail.getField(BookingDetailModel.TOUR_ID));
                this.getField(ApTrx.VENDOR_ID).moveFieldToThis(recVendor.getField(Vendor.ID));
                if (OperationTypeField.LIKE_TOGETHER_CODE.equalsIgnoreCase(recVendor.getField(Vendor.OPERATION_TYPE_CODE).toString()))
                    this.getField(ApTrx.PRODUCT_TYPE_ID).moveFieldToThis((BaseField)recBookingDetail.getField(BookingDetailModel.PRODUCT_TYPE_ID));
                if ((this.seek(">="))
                    && (this.getField(ApTrx.TOUR_ID).equals(recBookingDetail.getField(BookingDetailModel.TOUR_ID)))
                        && (this.getField(ApTrx.VENDOR_ID).equals(recVendor.getField(Vendor.ID))))
                {
                    if ((OperationTypeField.LIKE_TOGETHER_CODE.equalsIgnoreCase(recVendor.getField(Vendor.OPERATION_TYPE_CODE).toString()))
                        && (!this.getField(ApTrx.PRODUCT_TYPE_ID).equals(recBookingDetail.getField(BookingDetailModel.PRODUCT_TYPE_ID))))
                            this.addNew();
                }
                else
                    this.addNew();
            }
            if (this.getEditMode() == DBConstants.EDIT_ADD)
                this.addNewApTrx((BaseField)recBookingDetail.getField(BookingDetailModel.TOUR_ID), recVendor, (int)recBookingDetail.getField(BookingDetailModel.PRODUCT_TYPE_ID).getValue());
        
            recBookingDetail.getTable().edit();
            ((BaseField)recBookingDetail.getField(BookingDetailModel.AP_TRX_ID)).moveFieldToThis(this.getField(ApTrx.ID));
            ((Record)recBookingDetail).writeAndRefresh();   // Possible that the listeners re-wrote this record already.
        
            this.edit();
            this.addBookingDetailInfo(recBookingDetail);
            this.set();
            
        } catch (DBException ex) {
            ex.printStackTrace();
        } finally {
            this.setOpenMode(iOldOpenMode);   // Set it back
        }
        return DBConstants.NORMAL_RETURN;
    }

}

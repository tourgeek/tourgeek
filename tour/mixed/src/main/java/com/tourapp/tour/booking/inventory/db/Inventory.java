/**
 * @(#)Inventory.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.booking.inventory.db;

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
import com.tourapp.tour.product.hotel.screen.*;
import com.tourapp.tour.product.land.screen.*;
import com.tourapp.tour.product.trans.screen.*;
import com.tourapp.tour.product.car.screen.*;
import com.tourapp.tour.product.cruise.screen.*;
import com.tourapp.tour.product.air.screen.*;
import com.tourapp.tour.product.item.screen.*;
import com.tourapp.tour.product.tour.other.screen.*;
import com.tourapp.tour.product.base.screen.*;
import com.tourapp.tour.product.base.db.*;

/**
 *  Inventory - Inventory file.
 */
public class Inventory extends VirtualRecord
{
    private static final long serialVersionUID = 1L;

    //public static final int kID = kID;
    public static final int kProductTypeID = kVirtualRecordLastField + 1;
    public static final int kProductID = kProductTypeID + 1;
    public static final int kRateID = kProductID + 1;
    public static final int kClassID = kRateID + 1;
    public static final int kOtherID = kClassID + 1;
    public static final int kInvDate = kOtherID + 1;
    public static final int kBlocked = kInvDate + 1;
    public static final int kUsed = kBlocked + 1;
    public static final int kAvailable = kUsed + 1;
    public static final int kOversell = kAvailable + 1;
    public static final int kClosed = kOversell + 1;
    public static final int kInventoryLastField = kClosed;
    public static final int kInventoryFields = kClosed - DBConstants.MAIN_FIELD + 1;

    public static final int kIDKey = DBConstants.MAIN_KEY_FIELD;
    public static final int kInvDateKey = kIDKey + 1;
    public static final int kInventoryLastKey = kInvDateKey;
    public static final int kInventoryKeys = kInvDateKey - DBConstants.MAIN_KEY_FIELD + 1;
    protected InventoryDetail m_recInventoryDetail = null;
    public static final int NO_INVENTORY = -1;
    public static final String NO_OTHER = "0";
    public static final String NO_CLASS = "0";
    public static final String NO_RATE = "0";
    /**
     * Default constructor.
     */
    public Inventory()
    {
        super();
    }
    /**
     * Constructor.
     */
    public Inventory(RecordOwner screen)
    {
        this();
        this.init(screen);
    }
    /**
     * Initialize class fields.
     */
    public void init(RecordOwner screen)
    {
        m_recInventoryDetail = null;
        super.init(screen);
    }

    public static final String kInventoryFile = "Inventory";
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(kInventoryFile, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the Database Name.
     */
    public String getDatabaseName()
    {
        return "booking";
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
    public BaseScreen makeScreen(ScreenLocation itsLocation, BasePanel parentScreen, int iDocMode, Map<String,Object> properties)
    {
        ProductType recProductType = (ProductType)((ReferenceField)this.getField(kProductTypeID)).getReference();
        BaseScreen screen = null;
        if (recProductType != null)
        {
            if ((iDocMode & ScreenConstants.MAINT_MODE) == ScreenConstants.MAINT_MODE)
            {
                if (ProductType.HOTEL_CODE.equalsIgnoreCase(recProductType.getField(ProductType.kCode).toString()))
                    screen = new HotelInventoryScreen(this, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
                else if (ProductType.LAND_CODE.equalsIgnoreCase(recProductType.getField(ProductType.kCode).toString()))
                    screen = new LandInventoryScreen(this, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
                else if (ProductType.TRANSPORTATION_CODE.equalsIgnoreCase(recProductType.getField(ProductType.kCode).toString()))
                    screen = new TransportationInventoryScreen(this, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
                else if (ProductType.CAR_CODE.equalsIgnoreCase(recProductType.getField(ProductType.kCode).toString()))
                    screen = new CarInventoryScreen(this, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
                else if (ProductType.CRUISE_CODE.equalsIgnoreCase(recProductType.getField(ProductType.kCode).toString()))
                    screen = new CruiseInventoryScreen(this, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
                else if (ProductType.AIR_CODE.equalsIgnoreCase(recProductType.getField(ProductType.kCode).toString()))
                    screen = new AirInventoryScreen(this, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
                else if (ProductType.TOUR_CODE.equalsIgnoreCase(recProductType.getField(ProductType.kCode).toString()))
                    screen = new TourHeaderInventoryScreen(this, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
                else if (ProductType.ITEM_CODE.equalsIgnoreCase(recProductType.getField(ProductType.kCode).toString()))
                    screen = new ItemInventoryScreen(this, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
            }
            else if ((iDocMode & ScreenConstants.DETAIL_MODE) == ScreenConstants.DETAIL_MODE)
            {
                screen = new InventoryDetailGridScreen(this, null, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
            }
            else if ((iDocMode & ScreenConstants.DISPLAY_MODE) == ScreenConstants.DISPLAY_MODE)
            {
                if (ProductType.HOTEL_CODE.equalsIgnoreCase(recProductType.getField(ProductType.kCode).toString()))
                    screen = new HotelInventoryGridScreen(this, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
                else if (ProductType.LAND_CODE.equalsIgnoreCase(recProductType.getField(ProductType.kCode).toString()))
                    screen = new LandInventoryGridScreen(this, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
                else if (ProductType.TRANSPORTATION_CODE.equalsIgnoreCase(recProductType.getField(ProductType.kCode).toString()))
                    screen = new TransportationInventoryGridScreen(this, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
                else if (ProductType.CAR_CODE.equalsIgnoreCase(recProductType.getField(ProductType.kCode).toString()))
                    screen = new CarInventoryGridScreen(this, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
                else if (ProductType.CRUISE_CODE.equalsIgnoreCase(recProductType.getField(ProductType.kCode).toString()))
                    screen = new CruiseInventoryGridScreen(this, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
                else if (ProductType.AIR_CODE.equalsIgnoreCase(recProductType.getField(ProductType.kCode).toString()))
                    screen = new AirInventoryGridScreen(this, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
                else if (ProductType.TOUR_CODE.equalsIgnoreCase(recProductType.getField(ProductType.kCode).toString()))
                    screen = new TourHeaderInventoryGridScreen(this, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
                else if (ProductType.ITEM_CODE.equalsIgnoreCase(recProductType.getField(ProductType.kCode).toString()))
                    screen = new ItemInventoryGridScreen(this, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
            }
        }
        if (screen == null)
            screen = super.makeScreen(itsLocation, parentScreen, iDocMode, properties);
        return screen;
    }
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        if (iFieldSeq == kID)
        {
            field = new CounterField(this, "ID", 10, null, null);
            field.setHidden(true);
        }
        if (iFieldSeq == kProductTypeID)
        {
            field = new ProductTypeField(this, "ProductTypeID", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setNullable(false);
        }
        if (iFieldSeq == kProductID)
        {
            field = new ProductField(this, "ProductID", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setNullable(false);
        }
        if (iFieldSeq == kRateID)
        {
            field = new ReferenceField(this, "RateID", Constants.DEFAULT_FIELD_LENGTH, null, new Integer(Inventory.NO_RATE));
            field.setNullable(false);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kClassID)
        {
            field = new ReferenceField(this, "ClassID", Constants.DEFAULT_FIELD_LENGTH, null, new Integer(Inventory.NO_CLASS));
            field.setNullable(false);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kOtherID)
        {
            field = new ReferenceField(this, "OtherID", Constants.DEFAULT_FIELD_LENGTH, null, new Integer(Inventory.NO_OTHER));
            field.setNullable(false);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kInvDate)
            field = new DateField(this, "InvDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kBlocked)
        {
            field = new ShortField(this, "Blocked", 3, null, null);
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kUsed)
            field = new ShortField(this, "Used", 3, null, new Short((short)0));
        if (iFieldSeq == kAvailable)
            field = new ShortField(this, "Available", 3, null, null);
        if (iFieldSeq == kOversell)
            field = new ShortField(this, "Oversell", 3, null, null);
        if (iFieldSeq == kClosed)
            field = new BooleanField(this, "Closed", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kInventoryLastField)
                field = new EmptyField(this);
        }
        return field;
    }
    /**
     * Add this key area description to the Record.
     */
    public KeyArea setupKey(int iKeyArea)
    {
        KeyArea keyArea = null;
        if (iKeyArea == kIDKey)
        {
            keyArea = this.makeIndex(DBConstants.UNIQUE, "PrimaryKey");
            keyArea.addKeyField(kID, DBConstants.ASCENDING);
        }
        if (iKeyArea == kInvDateKey)
        {
            keyArea = this.makeIndex(DBConstants.UNIQUE, "InvDate");
            keyArea.addKeyField(kProductID, DBConstants.ASCENDING);
            keyArea.addKeyField(kProductTypeID, DBConstants.ASCENDING);
            keyArea.addKeyField(kInvDate, DBConstants.ASCENDING);
            keyArea.addKeyField(kRateID, DBConstants.ASCENDING);
            keyArea.addKeyField(kClassID, DBConstants.ASCENDING);
            keyArea.addKeyField(kOtherID, DBConstants.ASCENDING);
        }
        if (keyArea == null) if (iKeyArea < kInventoryLastKey)
        {
            keyArea = super.setupKey(iKeyArea);     
            if (keyArea == null) if (iKeyArea < kInventoryLastKey)
                keyArea = new EmptyKey(this);
        }
        return keyArea;
    }
    /**
     * AddMasterListeners Method.
     */
    public void addMasterListeners()
    {
        super.addMasterListeners();
        this.getField(Inventory.kBlocked).addListener(new CalcBalanceHandler(this.getField(Inventory.kAvailable), this.getField(Inventory.kBlocked), this.getField(Inventory.kUsed), CalcBalanceHandler.MINUS, false));
        this.getField(Inventory.kUsed).addListener(new CalcBalanceHandler(this.getField(Inventory.kAvailable), this.getField(Inventory.kBlocked), this.getField(Inventory.kUsed), CalcBalanceHandler.MINUS, false));
    }
    /**
     * Override this to add record listeners and filters to every screen where this is the main record.
     * @param screen The screen these listeners will be in.
     */
    public void addScreenListeners(RecordOwner screen)
    {
        super.addScreenListeners(screen);
        this.getField(Inventory.kUsed).setEnabled(false);
        this.getField(Inventory.kAvailable).setEnabled(false);
    }
    /**
     * Free Method.
     */
    public void free()
    {
        if (m_recInventoryDetail != null)
            m_recInventoryDetail.free();
        m_recInventoryDetail = null;
        super.free();
    }
    /**
     * GetAvailability Method.
     */
    public Inventory getAvailability(Product recProduct, Date dateTarget, int iRateID, int iClassID, int iOtherID)
    {
        int iOldKeyOrder = this.getDefaultOrder();
        try {
            this.addNew();
            this.setKeyArea(Inventory.kInvDateKey);
            ProductType recProductType = (ProductType)((ReferenceField)this.getField(Inventory.kProductTypeID)).getReferenceRecord();
            int iProductTypeID = recProductType.getProductTypeID(recProduct);
            this.getField(Inventory.kProductTypeID).setValue(iProductTypeID);
            this.getField(Inventory.kProductID).moveFieldToThis((BaseField)recProduct.getCounterField());
            ((DateField)this.getField(Inventory.kInvDate)).setDate(dateTarget, DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
            this.getField(Inventory.kRateID).setValue(0);
            this.getField(Inventory.kClassID).setValue(0);
            this.getField(Inventory.kOtherID).setValue(0);
            this.close();
            while (this.seek(FileFilter.GREATER_THAN_EQUAL))
            {
                if (this.getField(Inventory.kProductTypeID).getValue() != iProductTypeID)
                    break;
                if (!this.getField(Inventory.kProductID).equals(recProduct.getCounterField()))
                    break;
                if (((DateField)this.getField(Inventory.kInvDate)).compareTo(dateTarget) > 0)
                    break;
                if (this.getField(Inventory.kRateID).getValue() != 0)
                    if (this.getField(Inventory.kRateID).getValue() != iRateID)
                        continue;
                if (this.getField(Inventory.kClassID).getValue() != 0)
                    if (this.getField(Inventory.kClassID).getValue() != iClassID)
                        continue;
                if (this.getField(Inventory.kOtherID).getValue() != 0)
                    if (this.getField(Inventory.kOtherID).getValue() != iOtherID)
                        continue;
                // Great, this one matches
                return this;
            }
        } catch (DBException ex) {
            ex.printStackTrace();
        } finally {
            this.setKeyArea(iOldKeyOrder);
        }
        return null;    // No inventory
    }
    /**
     * Get all the transaction IDs for this BookingDetail trx ID.
     */
    public Set<Integer> surveyInventory(BaseField fldTrxID)
    {
        if (fldTrxID == null)
            return null;
        Set<Integer> setSurvey = new HashSet<Integer>();
        InventoryDetail recInventoryDetail = this.getInventoryDetail();
        recInventoryDetail.setKeyArea(InventoryDetail.kBookingDetailIDKey);
        SubFileFilter listener = null;
        recInventoryDetail.addListener(listener = new SubFileFilter(fldTrxID, InventoryDetail.kBookingDetailID, null, -1, null, -1));
        try {
            recInventoryDetail.close();
            while (recInventoryDetail.hasNext())
            {
                recInventoryDetail.next();
                
                setSurvey.add((Integer)recInventoryDetail.getCounterField().getData());
            }
        } catch (DBException ex) {
            ex.printStackTrace();
        } finally {
            recInventoryDetail.removeListener(listener, true);
        }
        return setSurvey;
    }
    /**
     * For the current record, update the availability using this amount
     * @param iTargetAmount Amount to reduce the inventory by.
     * @param fldTrxID The (BookingDetail) trx to tie this inventory to
     * @param iType Option type (ie., room type)
     * @param bDelete If true, delete this inventory
     * @param mapSurvey If a changed transaction is contained in this map, remove it
     * @return Error code.
     */
    public int updateAvailability(int iTargetAmount, BaseField fldTrxID, int iType, boolean bDelete, Set<Integer> setSurvey)
    {
        Task task = null;
        if (this.getRecordOwner() != null)
            task = this.getRecordOwner().getTask();
        if ((this.getEditMode() == DBConstants.EDIT_NONE) || (this.getEditMode() == DBConstants.EDIT_ADD))
        {
            if (task != null)
                return task.setLastError(task.getApplication().getResources(ResourceConstants.ERROR_RESOURCE, true).getString("No current record")); // Must have a current record
            else
                return DBConstants.ERROR_RETURN;
        }
        int iOldOpenMode = this.getOpenMode();
        try {
            this.setOpenMode(iOldOpenMode & ~DBConstants.OPEN_READ_ONLY);   // Allow write (I don't know where this file has been)
            this.edit();
            int iOldTotal = (int)this.getField(Inventory.kUsed).getValue();
            int iOldAmount = 0;
            if (bDelete)
                iTargetAmount = 0;
            InventoryDetail recInventoryDetail = this.getInventoryDetail();
            
            recInventoryDetail.addNew();
            recInventoryDetail.setKeyArea(InventoryDetail.kInventoryIDKey);
            recInventoryDetail.getField(InventoryDetail.kInventoryID).moveFieldToThis((BaseField)this.getCounterField());
            recInventoryDetail.getField(InventoryDetail.kBookingDetailID).moveFieldToThis(fldTrxID);
            recInventoryDetail.getField(InventoryDetail.kType).setValue(iType);
            boolean bFound = recInventoryDetail.seek(null);
            if (bFound)
                iOldAmount = (int)recInventoryDetail.getField(InventoryDetail.kAmount).getValue();
            if (this.getField(Inventory.kAvailable).getValue() < (iTargetAmount - iOldAmount))
            {
                if (task != null)
                    return task.setLastError(task.getApplication().getResources(ResourceConstants.BOOKING_RESOURCE, true).getString("Not sufficent inventory"));   // Insufficient inventory
                else
                    return DBConstants.ERROR_RETURN;
            }
            if (bFound)
            {
                if (setSurvey != null)
                    setSurvey.remove((Integer)recInventoryDetail.getCounterField().getData());
                recInventoryDetail.edit();
                recInventoryDetail.getField(InventoryDetail.kAmount).setValue(iTargetAmount);
                if ((bDelete) || (iTargetAmount == 0))
                    recInventoryDetail.remove();
                else
                    recInventoryDetail.set();
            }
            else
            {
                recInventoryDetail.addNew();
                recInventoryDetail.getField(InventoryDetail.kInventoryID).moveFieldToThis((BaseField)this.getCounterField());
                recInventoryDetail.getField(InventoryDetail.kBookingDetailID).moveFieldToThis(fldTrxID);
                recInventoryDetail.getField(InventoryDetail.kType).setValue(iType);
                recInventoryDetail.getField(InventoryDetail.kAmount).setValue(iTargetAmount);
                if (!bDelete)
                    recInventoryDetail.add();
            }
        
            this.getField(Inventory.kUsed).setValue(iOldTotal - iOldAmount + iTargetAmount);
            this.set();
        } catch (DBException ex) {
            ex.printStackTrace();
        } finally {
            this.setOpenMode(iOldOpenMode);   // Set it back
        }
        return DBConstants.NORMAL_RETURN;
    }
    /**
     * Remove the transactions contained in this map.
     */
    public int removeTrxs(BaseField fldTrxID, Set<Integer> setSurvey)
    {
        int iErrorCode = DBConstants.NORMAL_RETURN;
        if (setSurvey != null)
            if (setSurvey.size() > 0)
        {
            Iterator<Integer> iterator = setSurvey.iterator();
            InventoryDetail recInventoryDetail = this.getInventoryDetail();
            while (iterator.hasNext())
            {
                try {
                    recInventoryDetail.setKeyArea(InventoryDetail.kIDKey);
                    recInventoryDetail.addNew();
                    recInventoryDetail.getField(InventoryDetail.kID).setData(iterator.next());
                    if (recInventoryDetail.seek(null))
                    {
                        int iTargetAmount = 0;
                        int iType = (int)recInventoryDetail.getField(InventoryDetail.kType).getValue();
                        boolean bDelete = true;
                        Inventory recInventory = (Inventory)((ReferenceField)recInventoryDetail.getField(InventoryDetail.kInventoryID)).getReference();
                        iErrorCode = recInventory.updateAvailability(iTargetAmount, fldTrxID, iType, bDelete, null);
                        if (iErrorCode != DBConstants.NORMAL_RETURN)
                            return iErrorCode;
                    }
                } catch (DBException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return iErrorCode;
    }
    /**
     * GetInventoryDetail Method.
     */
    public InventoryDetail getInventoryDetail()
    {
        if (m_recInventoryDetail == null)
        {
            m_recInventoryDetail = new InventoryDetail(Utility.getRecordOwner(this));
        }
        return m_recInventoryDetail;
    }
    /**
     * Remove the inventory for this BookingDetail trxID.
     * (Deletes the links, and adds the inventory back).
     */
    public int removeInventory(BaseField fldTrxID)
    {
        int iErrorCode = DBConstants.NORMAL_RETURN;
        Task task = null;
        if (this.getRecordOwner() != null)
            task = this.getRecordOwner().getTask();
        
        InventoryDetail recInventoryDetail = this.getInventoryDetail();
        recInventoryDetail.setKeyArea(InventoryDetail.kBookingDetailIDKey);
        SubFileFilter listener = null;
        recInventoryDetail.addListener(listener = new SubFileFilter(fldTrxID, InventoryDetail.kBookingDetailID, null, -1, null, -1));
        try {
            recInventoryDetail.close();
            while (recInventoryDetail.hasNext())
            {
                recInventoryDetail.next();
                recInventoryDetail.edit();
                
                this.setKeyArea(Inventory.kIDKey);
                this.getField(Inventory.kID).moveFieldToThis(recInventoryDetail.getField(InventoryDetail.kInventoryID));
                if (!this.seek(null))
                { // Never
                    if (task != null)
                        return task.setLastError(task.getApplication().getResources(ResourceConstants.BOOKING_RESOURCE, true).getString("Could not remove inventory"));   // Insufficient inventory
                    else
                        return DBConstants.ERROR_RETURN;
                }
                int iTargetAmount = (int)recInventoryDetail.getField(InventoryDetail.kAmount).getValue();
                this.edit();
                this.getField(Inventory.kUsed).setValue(this.getField(Inventory.kUsed).getValue() - iTargetAmount);    
                this.set();
        
                
                recInventoryDetail.remove();
            }
        } catch (DBException ex) {
            ex.printStackTrace();
        } finally {
            recInventoryDetail.removeListener(listener, true);
        }
        return iErrorCode;
    }
    /**
     * Get the record type from the field that specifies the record type.
     * (Override this).
     * @return The record type (as an object).
     */
    public BaseField getSharedRecordTypeKey()
    {
        return this.getField(Inventory.kProductTypeID);
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
            int iProductType = ((Integer)objKey).intValue();
            if (iProductType == ProductType.HOTEL_ID)
                return new HotelInventory(recordOwner);
            if (iProductType == ProductType.LAND_ID)
                return new LandInventory(recordOwner);
            if (iProductType == ProductType.AIR_ID)
                return new AirInventory(recordOwner);
            if (iProductType == ProductType.CAR_ID)
                return new CarInventory(recordOwner);
            if (iProductType == ProductType.CRUISE_ID)
                return new CruiseInventory(recordOwner);
            if (iProductType == ProductType.ITEM_ID)
                return new ItemInventory(recordOwner);
            if (iProductType == ProductType.TOUR_ID)
                return new TourHeaderInventory(recordOwner);
            if (iProductType == ProductType.TRANSPORTATION_ID)
                return new TransportationInventory(recordOwner);
        }
        return null;
    }

}

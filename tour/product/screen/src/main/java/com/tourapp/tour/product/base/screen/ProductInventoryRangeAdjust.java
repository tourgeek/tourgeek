/**
  * @(#)ProductInventoryRangeAdjust.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.tour.product.base.screen;

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
import org.jbundle.main.screen.*;
import com.tourapp.tour.booking.inventory.db.*;
import com.tourapp.tour.product.base.db.*;

/**
 *  ProductInventoryRangeAdjust - Add or Adjust a range of block space.
 */
public class ProductInventoryRangeAdjust extends DetailScreen
{
    public static final String ADJUST_RANGE = "Adjust range";
    /**
     * Default constructor.
     */
    public ProductInventoryRangeAdjust()
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
    public ProductInventoryRangeAdjust(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
        return "Add or Adjust a range of block space";
    }
    /**
     * ProductInventoryRangeAdjust Method.
     */
    public ProductInventoryRangeAdjust(Record recProduct, Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object>
     properties)
    {
        this();
        this.init(recProduct, record, itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, properties);
    }
    /**
     * Override this to open the main file.
     * <p />You should pass this record owner to the new main file (ie., new MyNewTable(thisRecordOwner)).
     * @return The new record.
     */
    public Record openMainRecord()
    {
        return new Inventory(this);
    }
    /**
     * Add the screen fields.
     * Override this to create (and return) the screen record for this recordowner.
     * @return The screen record.
     */
    public Record addScreenRecord()
    {
        return new ProductScreenRecord(this); // Override this if you need more
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
    }
    /**
     * Add button(s) to the toolbar.
     */
    public void addToolbarButtons(ToolScreen toolScreen)
    {
        BaseApplication app = (BaseApplication)this.getTask().getApplication();
        new SCannedBox(toolScreen.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), toolScreen, null, ScreenConstants.DEFAULT_DISPLAY, null, app.getResources(ResourceConstants.ASSETDR_RESOURCE, true).getString(ADJUST_RANGE), MenuConstants.POST, ADJUST_RANGE, null);
    }
    /**
     * Add the toolbars that belong with this screen.
     * @return The new toolbar.
     */
    public ToolScreen addToolbars()
    {
        return new MenuToolbar(null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, null);
    }
    /**
     * Set up all the screen fields.
     */
    public void setupSFields()
    {
        this.getRecord(ProductScreenRecord.PRODUCT_SCREEN_RECORD_FILE).getField(ProductScreenRecord.START_DATE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ProductScreenRecord.PRODUCT_SCREEN_RECORD_FILE).getField(ProductScreenRecord.END_DATE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ProductScreenRecord.PRODUCT_SCREEN_RECORD_FILE).getField(ProductScreenRecord.BLOCKED).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ProductScreenRecord.PRODUCT_SCREEN_RECORD_FILE).getField(ProductScreenRecord.OVERSELL).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ProductScreenRecord.PRODUCT_SCREEN_RECORD_FILE).getField(ProductScreenRecord.CLOSED).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ProductScreenRecord.PRODUCT_SCREEN_RECORD_FILE).getField(ProductScreenRecord.DELETE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        BaseApplication app = (BaseApplication)this.getTask().getApplication();
        new SCannedBox(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), this, null, ScreenConstants.DEFAULT_DISPLAY, null, app.getResources(ResourceConstants.ASSETDR_RESOURCE, true).getString(ADJUST_RANGE), MenuConstants.POST, ADJUST_RANGE, null);
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
        if (ADJUST_RANGE.equalsIgnoreCase(strCommand))
            return this.doUpdateRange();
        return super.doCommand(strCommand, sourceSField, iCommandOptions);
    }
    /**
     * Update the current Inventory range.
     */
    public boolean doUpdateRange()
    {
        Record recInventory = this.getMainRecord();
        try {
            this.getScreenRecord().getField(ProductScreenRecord.DETAIL_DATE).moveFieldToThis(this.getScreenRecord().getField(ProductScreenRecord.START_DATE));
            while (true)
            {
                if (this.getScreenRecord().getField(ProductScreenRecord.DETAIL_DATE).compareTo(this.getScreenRecord().getField(ProductScreenRecord.END_DATE)) > 0)
                    break;  // End of range
                recInventory.addNew();  // This will set the product and product type fields.
                this.setInvKey(recInventory, this.getScreenRecord().getField(ProductScreenRecord.DETAIL_DATE));
                boolean bSuccess = recInventory.seek("=");
                if (bSuccess)
                {   // Modify the current inventory
                    if (recInventory.edit() != DBConstants.NORMAL_RETURN)
                    {   // Locked, process error
                    }
                    if (!this.getScreenRecord().getField(ProductScreenRecord.BLOCKED).isNull())
                        recInventory.getField(Inventory.BLOCKED).moveFieldToThis(this.getScreenRecord().getField(ProductScreenRecord.BLOCKED));
                    if (!this.getScreenRecord().getField(ProductScreenRecord.OVERSELL).isNull())
                        recInventory.getField(Inventory.OVERSELL).moveFieldToThis(this.getScreenRecord().getField(ProductScreenRecord.OVERSELL));
                    if (this.getScreenRecord().getField(ProductScreenRecord.CLOSED).getState())
                        recInventory.getField(Inventory.CLOSED).moveFieldToThis(this.getScreenRecord().getField(ProductScreenRecord.CLOSED));
                    if (this.getScreenRecord().getField(ProductScreenRecord.DELETE).getState())
                        recInventory.remove();
                    else
                        recInventory.set();
                }
                else
                {   // Add New inventory
                    recInventory.addNew();  // This will set the product and product type fields.
                    this.setInvKey(recInventory, this.getScreenRecord().getField(ProductScreenRecord.DETAIL_DATE));
        
                    recInventory.getField(Inventory.USED).setValue(0);
                    recInventory.getField(Inventory.BLOCKED).moveFieldToThis(this.getScreenRecord().getField(ProductScreenRecord.BLOCKED));
                    recInventory.getField(Inventory.OVERSELL).moveFieldToThis(this.getScreenRecord().getField(ProductScreenRecord.OVERSELL));
                    recInventory.getField(Inventory.CLOSED).moveFieldToThis(this.getScreenRecord().getField(ProductScreenRecord.CLOSED));
        
                    if (!this.getScreenRecord().getField(ProductScreenRecord.DELETE).getState())
                        recInventory.add();
                }
        
                Calendar calTarget = ((DateField)this.getScreenRecord().getField(ProductScreenRecord.DETAIL_DATE)).getCalendar();
                calTarget.add(Calendar.DATE, 1);    // Next day.
                ((DateField)this.getScreenRecord().getField(ProductScreenRecord.DETAIL_DATE)).setDate(calTarget.getTime(), true, DBConstants.INIT_MOVE);
            }
        } catch (DBException ex)    {
            ex.printStackTrace();
            return false;
        }
        return true;    // Success
    }
    /**
     * Setup the key for the inventory record.
     * Note: The product ID and type should already be set by SubFilter.
     * @param recInventory The inv record.
     * @param fldTargetDate The date to set.
     */
    public void setInvKey(Record recInventory, BaseField fldTargetDate)
    {
        recInventory.getField(Inventory.INV_DATE).moveFieldToThis(fldTargetDate);
        recInventory.getField(Inventory.RATE_ID).initField(true);
        recInventory.getField(Inventory.CLASS_ID).initField(true);
        recInventory.getField(Inventory.OTHER_ID).initField(true);
    }
    /**
     * Make a sub-screen.
     * @return the new sub-screen.
     */
    public BasePanel makeSubScreen()
    {
        return new ProductHeaderScreen(null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, null);
    }

}

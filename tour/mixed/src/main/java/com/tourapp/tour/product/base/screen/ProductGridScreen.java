/**
 *  @(#)ProductGridScreen.
 *  Copyright © 2010 tourapp.com. All rights reserved.
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
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import com.tourapp.tour.product.base.db.*;
import com.tourapp.thin.app.booking.entry.*;
import org.jbundle.thin.base.message.*;
import com.tourapp.tour.product.base.event.*;
import org.jbundle.main.msg.db.*;

/**
 *  ProductGridScreen - Base Screen to display/search product.
 */
public class ProductGridScreen extends GridScreen
{
    /**
     * Default constructor.
     */
    public ProductGridScreen()
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
    public ProductGridScreen(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
        return "Base Screen to display/search product";
    }
    /**
     * Add the screen fields.
     * Override this to create (and return) the screen record for this recordowner.
     * @return The screen record.
     */
    public Record addScreenRecord()
    {
        return new ProductScreenRecord(this);
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        this.setEditing(false);
        this.getMainRecord().setKeyArea(Product.kDescSortKey);
        
        // Redisplay if any of these change
        this.getScreenRecord().getField(ProductScreenRecord.kDescription).addListener(new FieldReSelectHandler(this));
        this.getScreenRecord().getField(ProductScreenRecord.kCityID).addListener(new FieldReSelectHandler(this));
        
        this.getScreenRecord().getField(ProductScreenRecord.kDetailDate).addListener(new FieldReSelectHandler(this));
        this.getScreenRecord().getField(ProductScreenRecord.kRateID).addListener(new FieldReSelectHandler(this));
        this.getScreenRecord().getField(ProductScreenRecord.kClassID).addListener(new FieldReSelectHandler(this));
        this.getScreenRecord().getField(ProductScreenRecord.kRemoteQueryEnabled).addListener(new FieldReSelectHandler(this));
        
        this.addRateMessageListeners((Product)this.getMainRecord(), (ProductScreenRecord)this.getScreenRecord());
        
        this.setThisProperty(ProductScreenRecord.kDetailDate);
        this.setThisProperty(ProductScreenRecord.kClassID);
        this.setThisProperty(ProductScreenRecord.kRateID);
        this.setThisProperty(ProductScreenRecord.kPax);
        this.setThisProperty(ProductScreenRecord.kCityID);
        
        ((BaseStatusField)this.getMainRecord().getField(Product.kDisplayCostStatusID)).getIconField(null).addListener(new CostStatusUpdateHandler(null));
    }
    /**
     * Set this field to the property with the same name.
     * @param iScreenField Screen field sequence with the same name.
     */
    public void setThisProperty(int iScreenField)
    {
        if (this.getProperty(this.getScreenRecord().getField(iScreenField).getFieldName()) != null)
            this.getScreenRecord().getField(iScreenField).setString(this.getProperty(this.getScreenRecord().getField(iScreenField).getFieldName()));        
    }
    /**
     * Read the current file in the header record given the current detail record.
     */
    public void syncHeaderToMain()
    {
        super.syncHeaderToMain();
        
        this.restoreScreenParam(ProductScreenRecord.kRateID);
        this.restoreScreenParam(ProductScreenRecord.kClassID);
        this.restoreScreenParam(ProductScreenRecord.kDetailDate);
        this.restoreScreenParam(ProductScreenRecord.kRemoteQueryEnabled);
    }
    /**
     * Add the listeners and message queues for rate lookups.
     * (todo - Don't set this up until they are required).
     */
    public void addRateMessageListeners(Product recProduct, ProductScreenRecord screenRecord)
    {
        // Override this to add the listeners and message queues (remember to call super)
        this.getMainRecord().getField(Product.kProductCost).setSelected(true);  // Now you can calc the USD amount (since you have this local amount)
        this.getMainRecord().getField(Product.kProductCost).addListener(new CalcProductAmountHome(this.getMainRecord().getField(Product.kProductCostLocal)));
        this.getMainRecord().getField(Product.kPPCost).addListener(new CalcProductAmountHome(this.getMainRecord().getField(Product.kPPCostLocal)));
        // Create a private messageReceiver and listen for changes
        BaseMessageManager messageManager = ((Application)this.getTask().getApplication()).getMessageManager();
        Integer intRegistryID = null;
        if (messageManager != null)
        {
            Object source = this;
            BaseMessageFilter messageFilter = new BaseMessageFilter(MessageConstants.TRX_RETURN_QUEUE, MessageConstants.INTERNET_QUEUE, source, null);
            messageManager.addMessageFilter(messageFilter);
            this.addProductRateMessageFilter(recProduct, messageFilter);
            recProduct.addListener(new FreeOnFreeHandler(messageFilter));
            intRegistryID = messageFilter.getRegistryID();
            recProduct.addListener(this.getProductCostHandler(screenRecord, intRegistryID));
        }
    }
    /**
     * AddProductRateMessageFilter Method.
     */
    public ProductRateMessageListener addProductRateMessageFilter(Product recProduct, BaseMessageFilter messageFilter)
    {
        return new ProductRateMessageListener(null, recProduct, false, messageFilter);
    }
    /**
     * GetProductCostHandler Method.
     */
    public GetProductCostHandler getProductCostHandler(ProductScreenRecord screenRecord, Integer intRegistryID)
    {
        return new GetProductCostHandler(screenRecord, intRegistryID);

    }
    /**
     * Add button(s) to the toolbar.
     */
    public void addToolbarButtons(ToolScreen toolScreen)
    {
        BaseApplication application = (BaseApplication)this.getTask().getApplication();
        new SCannedBox(toolScreen.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), toolScreen, null, ScreenConstants.DEFAULT_DISPLAY, null, application.getResources(ResourceConstants.PRODUCT_RESOURCE, true).getString(Product.PRICING_DETAIL), BookingConstants.BUTTON_LOCATION + Product.PRICING_DETAIL, Product.PRICING_DETAIL, null);
        new SCannedBox(toolScreen.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), toolScreen, null, ScreenConstants.DEFAULT_DISPLAY, null, application.getResources(ResourceConstants.PRODUCT_RESOURCE, true).getString(Product.INVENTORY_DETAIL), BookingConstants.BUTTON_LOCATION + Product.INVENTORY_DETAIL, Product.INVENTORY_DETAIL, null); 
    }
    /**
     * Add the navigation button(s) to the left of the grid row.
     */
    public void addNavButtons()
    {
        BaseApplication application = (BaseApplication)this.getTask().getApplication();
        new SCannedBox(this.getNextLocation(ScreenConstants.FIRST_SCREEN_LOCATION, ScreenConstants.SET_ANCHOR), this, null, ScreenConstants.DEFAULT_DISPLAY, null, null, BookingConstants.BUTTON_LOCATION + Product.INVENTORY_DETAIL, Product.INVENTORY_DETAIL, application.getResources(ResourceConstants.PRODUCT_RESOURCE, true).getString(Product.INVENTORY_DETAIL));
        new SCannedBox(this.getNextLocation(ScreenConstants.FIRST_SCREEN_LOCATION, ScreenConstants.SET_ANCHOR), this, null, ScreenConstants.DEFAULT_DISPLAY, null, null, BookingConstants.BUTTON_LOCATION + Product.PRICING_DETAIL, Product.PRICING_DETAIL, application.getResources(ResourceConstants.PRODUCT_RESOURCE, true).getString(Product.PRICING_DETAIL));
        super.addNavButtons();  // Next buttons will be "First!"
    }
    /**
     * Get the command string to restore screen.
     */
    public String getScreenURL()
    {
        String strCommand = super.getScreenURL();
        
        strCommand = this.saveProductParam(strCommand, ProductScreenRecord.kRateID);
        strCommand = this.saveProductParam(strCommand, ProductScreenRecord.kClassID);
        strCommand = this.saveProductParam(strCommand, ProductScreenRecord.kDetailDate);
        strCommand = this.saveProductParam(strCommand, ProductScreenRecord.kRemoteQueryEnabled);
        
        return strCommand;
    }
    /**
     * SaveProductParam Method.
     */
    public String saveProductParam(String strCommand, int iFieldSeq)
    {
        if (!this.getScreenRecord().getField(iFieldSeq).isNull())
            strCommand = Utility.addURLParam(strCommand, this.getScreenRecord().getField(iFieldSeq).getFieldName(), this.getScreenRecord().getField(iFieldSeq).toString());
        return strCommand;
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
        if ((MenuConstants.FORM.equalsIgnoreCase(strCommand)) || (MenuConstants.FORMLINK.equalsIgnoreCase(strCommand)))
        {   // Add the header field to this screen, so on return the same sub records will display
            BasePanel parentScreen = this.getParentScreen();
            if (parentScreen != null)
            {
                parentScreen.popHistory(1, false);
                parentScreen.pushHistory(this.getScreenURL(), false);  // Push this screen onto history stack
            }
        }
        if (strCommand.equalsIgnoreCase(Product.PRICING_DETAIL))
            return (this.onForm(null, Product.PRICING_GRID_SCREEN, true, iCommandOptions, null) != null);
        else if (strCommand.equalsIgnoreCase(Product.INVENTORY_DETAIL))
            return (this.onForm(null, Product.INVENTORY_GRID_SCREEN, true, iCommandOptions, null) != null);
        else
            return super.doCommand(strCommand, sourceSField, iCommandOptions);
    }

}

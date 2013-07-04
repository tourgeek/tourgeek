
package com.tourgeek.tour.product.base.screen;

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
import com.tourgeek.tour.product.base.db.*;
import com.tourgeek.tour.acctpay.db.*;

/**
 *  ProductPricingGridScreen - .
 */
public class ProductPricingGridScreen extends ProductDetailGridScreen
{
    /**
     * Default constructor.
     */
    public ProductPricingGridScreen()
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
    public ProductPricingGridScreen(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
     * ProductPricingGridScreen Method.
     */
    public ProductPricingGridScreen(Record recProduct, Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object>
     properties)
    {
        this();
        this.init(recProduct, record, itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, properties);
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
        // Link the screen field to the passed in record
        ((ReferenceField)this.getScreenRecord().getField(ProductScreenRecord.PRODUCT_ID)).syncReference(this.getHeaderRecord());
        // Sub file stuff
        FileListener subFileBehavior = new SubFileFilter(this.getHeaderRecord());
        this.getMainRecord().addListener(subFileBehavior);
        this.getHeaderRecord().getField(Product.ID).addListener(new FieldReSelectHandler(this)); 
        
        this.getMainRecord().addListener(new CompareFileFilter(ProductPricing.END_DATE, this.getScreenRecord().getField(ProductScreenRecord.START_DATE), FileFilter.GREATER_THAN_EQUAL, null, true));
        this.getScreenRecord().getField(ProductScreenRecord.START_DATE).addListener(new FieldReSelectHandler(this));
        if (Boolean.TRUE.toString().equalsIgnoreCase(this.getProperty(this.getScreenRecord().getField(ProductScreenRecord.READ_ONLY).getFieldName())))
        {
            this.setAppending(false);
            this.setEditing(false);
        }
    }
    /**
     * Add the toolbars that belong with this screen.
     * @return The new toolbar.
     */
    public ToolScreen addToolbars()
    {
        ToolScreen screen = super.addToolbars();
        
        this.getScreenRecord().getField(ProductScreenRecord.START_DATE).setupDefaultView(screen.getNextLocation(ScreenConstants.NEXT_INPUT_LOCATION, ScreenConstants.ANCHOR_DEFAULT), screen, ScreenConstants.DEFAULT_DISPLAY);
        
        return screen;
    }
    /**
     * SetupSFields Method.
     */
    public void setupSFields()
    {
        Record recVendor = ((ReferenceField)this.getHeaderRecord().getField(Product.VENDOR_ID)).getReferenceRecord(this);
        Record recCurrency = ((ReferenceField)recVendor.getField(Vendor.CURRENCYS_ID)).getReferenceRecord(this);
        this.getMainRecord().getField(ProductPricing.START_DATE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getMainRecord().getField(ProductPricing.END_DATE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
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

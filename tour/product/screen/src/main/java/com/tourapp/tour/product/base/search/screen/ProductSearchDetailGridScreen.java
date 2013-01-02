/**
  * @(#)ProductSearchDetailGridScreen.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.tour.product.base.search.screen;

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
import com.tourapp.tour.product.base.screen.*;
import com.tourapp.tour.product.base.search.db.*;
import com.tourapp.tour.product.base.db.*;

/**
 *  ProductSearchDetailGridScreen - .
 */
public class ProductSearchDetailGridScreen extends ProductDetailGridScreen
{
    /**
     * Default constructor.
     */
    public ProductSearchDetailGridScreen()
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
    public ProductSearchDetailGridScreen(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
     * ProductSearchDetailGridScreen Method.
     */
    public ProductSearchDetailGridScreen(Record recProduct, Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object>
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
        return new ProductSearchDetail(this); // Never
    }
    /**
     * OpenHeaderRecord Method.
     */
    public Record openHeaderRecord()
    {
        Record recProductSearchDetail = this.getMainRecord();
        String strProductType = this.getProperty(Product.PRODUCT_FILE);
        if (strProductType == null)
        {
            Record recProductType = ((ReferenceField)recProductSearchDetail.getField(ProductSearchDetail.PRODUCT_TYPE_ID)).getReference();
            strProductType = recProductType.getField(ProductType.DESCRIPTION).toString();
        }
        Product recProduct = Product.getProductRecord(strProductType, this);
        return recProduct;
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
        this.getMainRecord().setKeyArea(ProductSearchDetail.PRODUCT_ID_KEY);
        Product recProduct = (Product)this.getHeaderRecord();
        ProductType recProductType = (ProductType)((ReferenceField)this.getScreenRecord().getField(ProductScreenRecord.PRODUCT_TYPE_ID)).getReferenceRecord();
        int iProductTypeID = recProductType.getProductTypeID(recProduct);
        this.getScreenRecord().getField(ProductScreenRecord.PRODUCT_TYPE_ID).setValue(iProductTypeID);
        this.getMainRecord().addListener(new SubFileFilter(this.getHeaderRecord().getField(Product.ID), ProductSearchDetail.PRODUCT_ID, this.getScreenRecord().getField(ProductScreenRecord.PRODUCT_TYPE_ID), ProductSearchDetail.PRODUCT_TYPE_ID, this.getScreenRecord().getField(ProductScreenRecord.PRODUCT_SEARCH_TYPE_ID), ProductSearchDetail.PRODUCT_SEARCH_TYPE_ID));
        
        if (Boolean.TRUE.toString().equalsIgnoreCase(this.getProperty(this.getScreenRecord().getField(ProductScreenRecord.READ_ONLY).getFieldName())))
        {
            this.setAppending(false);
            this.setEditing(false);
        }
        
        this.addProductTypeFilter((ReferenceField)this.getRecord(ProductSearchDetail.PRODUCT_SEARCH_DETAIL_FILE).getField(ProductSearchDetail.PRODUCT_SEARCH_TYPE_ID));    
        SPopupBox control = (SPopupBox)this.getRecord(ProductSearchDetail.PRODUCT_SEARCH_DETAIL_FILE).getField(ProductSearchDetail.PRODUCT_SEARCH_TYPE_ID).getComponent(0);
        control.reSelectRecords();
        this.getRecord(ProductSearchDetail.PRODUCT_SEARCH_DETAIL_FILE).getField(ProductSearchDetail.PRODUCT_SEARCH_TYPE_ID).addListener(new InitFieldHandler(this.getScreenRecord().getField(ProductScreenRecord.PRODUCT_SEARCH_TYPE_ID)));
        
        this.getHeaderRecord().getField(Product.ID).addListener(new FieldReSelectHandler(this));
        this.getScreenRecord().getField(ProductScreenRecord.PRODUCT_SEARCH_TYPE_ID).addListener(new FieldReSelectHandler(this));
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
     * Add the toolbars that belong with this screen.
     * @return The new toolbar.
     */
    public ToolScreen addToolbars()
    {
        ToolScreen screen = super.addToolbars();
        
        this.addProductTypeFilter((ReferenceField)this.getScreenRecord().getField(ProductScreenRecord.PRODUCT_SEARCH_TYPE_ID));
        this.getScreenRecord().getField(ProductScreenRecord.PRODUCT_SEARCH_TYPE_ID).setupDefaultView(screen.getNextLocation(ScreenConstants.NEXT_INPUT_LOCATION, ScreenConstants.ANCHOR_DEFAULT), screen, ScreenConstants.DEFAULT_DISPLAY);
        
        return screen;
    }
    /**
     * SetupSFields Method.
     */
    public void setupSFields()
    {
        this.getRecord(ProductSearchDetail.PRODUCT_SEARCH_DETAIL_FILE).getField(ProductSearchDetail.PRODUCT_SEARCH_TYPE_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);

        Record recProductSearchType = ((ReferenceField)this.getRecord(ProductSearchDetail.PRODUCT_SEARCH_DETAIL_FILE).getField(ProductSearchDetail.PRODUCT_SEARCH_TYPE_ID)).getReferenceRecord();
        Record recProductSearchCategory = ((ReferenceField)this.getRecord(ProductSearchDetail.PRODUCT_SEARCH_DETAIL_FILE).getField(ProductSearchDetail.PRODUCT_SEARCH_CATEGORY_ID)).getReferenceRecord();
        this.getScreenRecord().getField(ProductSearchDetail.PRODUCT_SEARCH_CATEGORY_ID).setupTableLookup(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY, recProductSearchCategory, null, ProductSearchCategory.DESCRIPTION, false);
        recProductSearchCategory.addListener(new CompareFileFilter(ProductSearchCategory.PRODUCT_SEARCH_TYPE_ID, recProductSearchType.getField(ProductSearchType.ID), DBConstants.EQUALS, null, true));
        
        this.getRecord(ProductSearchDetail.PRODUCT_SEARCH_DETAIL_FILE).getField(ProductSearchDetail.SEARCH_DATA).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
    }
    /**
     * AddProductTypeFilter Method.
     */
    public void addProductTypeFilter(ReferenceField field)
    {
        Record recProductSearchType = field.getReferenceRecord();
        String strProductType = this.getHeaderRecord().getTableNames(false);
        BaseField fldToCheck = recProductSearchType.getField(strProductType);
        BaseField fldToCompare = new BooleanField(null, "TrueField", -1, null, null);
        fldToCompare.setState(true);
        recProductSearchType.addListener(new FreeOnFreeHandler(fldToCompare));
        recProductSearchType.addListener(new CompareFileFilter(fldToCheck, fldToCompare, DBConstants.EQUALS));
    }
    /**
     * Get the command string to restore screen.
     */
    public String getScreenURL()
    {
        String strURL = super.getScreenURL();
        strURL = Utility.addURLParam(strURL, Product.PRODUCT_FILE, this.getHeaderRecord().getTableNames(false));
        return strURL;
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

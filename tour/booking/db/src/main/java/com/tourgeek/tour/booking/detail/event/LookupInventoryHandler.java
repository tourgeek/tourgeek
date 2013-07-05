/**
  * @(#)LookupInventoryHandler.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.tour.booking.detail.event;

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
import com.tourgeek.tour.booking.inventory.db.*;

/**
 *  LookupInventoryHandler - Lookup inv on valid rec.
 */
public class LookupInventoryHandler extends FileListener
{
    protected BaseField m_fldClassID = null;
    protected BaseField m_fldDate = null;
    protected BaseField m_fldID = null;
    protected BaseField m_fldRateID = null;
    protected int m_iProductType;
    protected Inventory m_recInventory = null;
    /**
     * Default constructor.
     */
    public LookupInventoryHandler()
    {
        super();
    }
    /**
     * Lookup Inventory.
     */
    public LookupInventoryHandler(Inventory recInventory, int iProductType, BaseField fldID, BaseField fldDate, BaseField fldRateID, BaseField fldClassID)
    {
        this();
        this.init(recInventory, iProductType, fldID, fldDate, fldRateID, fldClassID);
    }
    /**
     * Initialize class fields.
     */
    public void init(Inventory recInventory, int iProductType, BaseField fldID, BaseField fldDate, BaseField fldRateID, BaseField fldClassID)
    {
        m_fldClassID = null;
        m_fldDate = null;
        m_fldID = null;
        m_fldRateID = null;
        m_iProductType = 0;
        m_recInventory = null;
        m_recInventory = recInventory;
        m_iProductType = iProductType;
        m_fldID = fldID;
        m_fldDate = fldDate;
        m_fldRateID = fldRateID;
        m_fldClassID = fldClassID;
        
        recInventory.addListener(new FileRemoveBOnCloseHandler(this));
        super.init(null);
    }
    /**
     * Called when a valid record is read from the table/query.
     * @param bDisplayOption If true, display any changes.
     */
    public void doValidRecord(boolean bDisplayOption)
    {
        int iOldKeyArea = m_recInventory.getDefaultOrder();
        try   {
            m_recInventory.setKeyArea(Inventory.INV_DATE_KEY);
            m_recInventory.addNew();
            if ((m_fldID.isNull()) || (m_fldDate.isNull()))
                return;
            m_recInventory.getField(Inventory.PRODUCT_TYPE_ID).setValue(m_iProductType);
            m_recInventory.getField(Inventory.PRODUCT_ID).moveFieldToThis(m_fldID);
            m_recInventory.getField(Inventory.INV_DATE).moveFieldToThis(m_fldDate);
            if (m_fldRateID != null)
                m_recInventory.getField(Inventory.RATE_ID).moveFieldToThis(m_fldRateID);
            if (m_fldClassID != null)
                m_recInventory.getField(Inventory.CLASS_ID).moveFieldToThis(m_fldClassID);
            if (!m_recInventory.seek(DBConstants.EQUALS))
                m_recInventory.addNew();
        } catch (DBException ex)    {
            ex.printStackTrace();
        } finally {
            m_recInventory.setKeyArea(iOldKeyArea);
        }
    }

}

/**
 * @(#)DistributionConverter.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.assetdr.screen.batch.dist;

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
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import com.tourapp.tour.assetdr.db.*;
import com.tourapp.tour.genled.db.*;

/**
 *  DistributionConverter - .
 */
public class DistributionConverter extends FieldConverter
{
    public static final String DEFAULT = "-- default --";
    protected Hashtable<Object,Object> m_htCache = new Hashtable<Object,Object>();
    protected Hashtable<Object,Object> m_htCacheAccountNo = new Hashtable<Object,Object>();
    protected BankTrxBatchDist m_recBankTrxBatchDist = null;
    public static final String SPLIT = "-- split --";
    /**
     * Default constructor.
     */
    public DistributionConverter()
    {
        super();
    }
    /**
     * Constructor.
     */
    public DistributionConverter(Converter converter)
    {
        this();
        this.init(converter);
    }
    /**
     * Initialize class fields.
     */
    public void init(BaseField field)
    {
        m_recBankTrxBatchDist = null;
        super.init(field);
    }
    /**
     * Free Method.
     */
    public void free()
    {
        if (m_recBankTrxBatchDist != null)
            m_recBankTrxBatchDist.free();
        m_recBankTrxBatchDist = null;
        super.free();
    }
    /**
     * Called from the listener to tell me that the field changed.
     */
    public void fieldChanged(BaseField referenceField)
    {
        boolean bRefreshIfChanged = false;
        if (this.getField().getComponent(0) != null)
            if (((ScreenField)this.getField().getComponent(0)).getParentScreen() instanceof GridScreen)
                bRefreshIfChanged = true;
        Object bookmarkKey = this.getCurrentBookmark(bRefreshIfChanged);
        if (bookmarkKey != null)
        {
            // First, delete the old distribution
            try {
                Record recBankTrxBatchDetail = this.getDetailRecord();
                if (m_recBankTrxBatchDist == null)
                    m_recBankTrxBatchDist = this.makeDistRecord(null, recBankTrxBatchDetail);
                m_recBankTrxBatchDist.close();
                while (m_recBankTrxBatchDist.hasNext())
                {
                    m_recBankTrxBatchDist.next();
                    m_recBankTrxBatchDist.edit();
                    m_recBankTrxBatchDist.remove();
                }
                this.createNewDist(referenceField);
                this.addCurrentToCache(bookmarkKey);
            } catch (DBException ex)    {
                ex.printStackTrace();
            }
        }
    }
    /**
     * Add the current dist record to the cache under this key.
     * @return The description for display.
     */
    public String addCurrentToCache(Object bookmarkKey)
    {
        // Then, update the cache
        String strResult = this.getDisplayField(null).toString();
        String strAccountNo = ((ReferenceField)m_recBankTrxBatchDist.getField(BankTrxBatchDist.kAccountID)).getReference().getField(Account.kAccountNo).toString();
        m_htCache.put(bookmarkKey, strResult);
        m_htCacheAccountNo.put(bookmarkKey, strAccountNo);
        return strResult;
    }
    /**
     * Create a new dist record using the detail record information.
     */
    public void createNewDist(BaseField referenceField) throws DBException
    {
        // Now create the new distribution
        Record recBankTrxBatchDetail = this.getDetailRecord();
        m_recBankTrxBatchDist.addNew();
        m_recBankTrxBatchDist.getField(BankTrxBatchDist.kAccountID).moveFieldToThis(referenceField);
        m_recBankTrxBatchDist.getField(BankTrxBatchDist.kAmount).moveFieldToThis(recBankTrxBatchDetail.getField(BankTrxBatchDetail.kAmount));
        m_recBankTrxBatchDist.add();
    }
    /**
     * Create the dist record and set up the sub-file.
     */
    public BankTrxBatchDist makeDistRecord(RecordOwner recordOwner, Record recBatchDetail)
    {
        RecordOwner ro = recordOwner;
        if (recordOwner == null)
            if (this.getField() != null)
                ro = Utility.getRecordOwner(((BaseField)this.getField()).getRecord());
        BankTrxBatchDist recBankTrxBatchDist = new BankTrxBatchDist(ro);
        if (recordOwner == null)
        {
            if (recBankTrxBatchDist.getRecordOwner() != null)
                recBankTrxBatchDist.getRecordOwner().removeRecord(recBankTrxBatchDist);
            if (this.getField() != null)
                if (this.getField().getRecord() != null)
                    ((BaseField)this.getField()).getRecord().addListener(new FreeOnFreeHandler(recBankTrxBatchDist));
        }
        recBankTrxBatchDist.setKeyArea(BankTrxBatchDist.kBankTrxBatchDetailIDKey);
        recBankTrxBatchDist.addListener(new SubFileFilter(recBatchDetail));
        return recBankTrxBatchDist;
    }
    /**
     * Get the detail record (dist's header) for this record.
     */
    public Record getDetailRecord()
    {
        Record recBankTrxBatchDetail = ((BaseField)this.getField()).getRecord().getRecordOwner().getRecord(BankTrxBatchDetail.kBankTrxBatchDetailFile);
        return recBankTrxBatchDetail;
    }
    /**
     * Get the description display field.
     * @recSecondary The (optional) secondary record.
     * @return the field.
     */
    public BaseField getDisplayField(Record recSecondary)
    {
        if (recSecondary == null)
            recSecondary = ((ReferenceField)m_recBankTrxBatchDist.getField(BankTrxBatchDist.kAccountID)).getReference();
        return recSecondary.getField(Account.kDescription);
    }
    /**
     * GetCurrentBookmark Method.
     */
    public Object getCurrentBookmark(boolean bRefreshIfNew)
    {
        Record recBankTrxBatchDetail = this.getDetailRecord();
        if (recBankTrxBatchDetail.getCounterField().isNull())
        {
            if ((bRefreshIfNew)
                && (recBankTrxBatchDetail.getEditMode() == DBConstants.EDIT_ADD))
            {
                try   {
                // Step 2a - Write the transaction.
                    recBankTrxBatchDetail.add();
                    Object objectID = recBankTrxBatchDetail.getLastModified(DBConstants.DATA_SOURCE_HANDLE);
                    recBankTrxBatchDetail.setHandle(objectID, DBConstants.DATA_SOURCE_HANDLE);
                } catch (DBException ex)    {
                    ex.printStackTrace();
                    return null;
                }
            }
            else
                    return null;
        }
        return recBankTrxBatchDetail.getCounterField().getData();
    }
    /**
     * GetAccountNoString Method.
     */
    public String getAccountNoString()
    {
        String string = this.getString();
        if (string == DEFAULT)
            return DBConstants.BLANK;
        if (string == SPLIT)
            return DBConstants.BLANK;
        Object bookmarkKey = this.getCurrentBookmark(false);
        if (bookmarkKey == null)
            return DBConstants.BLANK;
        return (String)m_htCacheAccountNo.get(bookmarkKey);
    }
    /**
     * GetString Method.
     */
    public String getString()
    {
        Record recBankTrxBatchDetail = this.getDetailRecord();
        if (recBankTrxBatchDetail.getCounterField().isNull())
            return DBConstants.BLANK;
        Object bookmarkKey = recBankTrxBatchDetail.getCounterField().getData();
        if (m_htCache.get(bookmarkKey) != null)
            return (String)m_htCache.get(bookmarkKey);
        if (m_recBankTrxBatchDist == null)
            m_recBankTrxBatchDist = this.makeDistRecord(null, recBankTrxBatchDetail);
        String strResult = DBConstants.BLANK;
        String strAccountNo = null;
        try {
            int iCount = 0;
            m_recBankTrxBatchDist.close();
            while (m_recBankTrxBatchDist.hasNext())
            {
                m_recBankTrxBatchDist.next();
                iCount++;
            }
            if (iCount == 0)
            {
                strResult = DEFAULT;
                m_htCache.put(bookmarkKey, strResult);
            }
            else if (iCount > 1)
            {
                strResult = SPLIT;
                m_htCache.put(bookmarkKey, strResult);
            }
            else
                strResult = this.addCurrentToCache(bookmarkKey);
        } catch (DBException ex)    {
            ex.printStackTrace();
        }
        return strResult;
    }
    /**
     * Set up the default control for this field.
     * @param  itsLocation     Location of this component on screen (ie., GridBagConstraint).
     * @param  targetScreen    Where to place this component (ie., Parent screen or GridBagLayout).
     * @param  iDisplayFieldDesc Display the label? (optional).
     * @return   Return the component or ScreenField that is created for this field.
     */
    public ScreenComponent setupDefaultView(ScreenLoc itsLocation, ComponentParent targetScreen, Convert converter, int iDisplayFieldDesc, Map<String, Object> properties)
    {
        ScreenComponent sField = super.setupDefaultView(itsLocation, targetScreen, converter, iDisplayFieldDesc, properties);
        
        Record recAccount = ((ReferenceField)converter.getField()).getReferenceRecord();
        ScreenComponent sFieldNo = recAccount.getField(Account.kAccountNo).getComponent(0);
        Converter fldConverter = (Converter)sFieldNo.getConverter();   // Should be the GlConverter
        BaseField field = (BaseField)fldConverter.getField();
        if (fldConverter != field)
            fldConverter.free();   // GlConverter is not necessary anymore.
        sFieldNo.setConverter(new GlConverter(new AccountNoDistConverter(field, this)));
        ScreenComponent sFieldDesc = this.getDisplayField(recAccount).getComponent(0);
        sFieldDesc.setConverter(new AccountDescDistConverter((Converter)sFieldDesc.getConverter(), this));
        new SCannedBox((ScreenLocation)targetScreen.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), (BasePanel)targetScreen, null, ScreenConstants.DEFAULT_DISPLAY, null, null, MenuConstants.FORMDETAIL, MenuConstants.FORMDETAIL, null);
        ((BaseField)this.getField()).addListener(new ReferenceChangedHandler(this));
        
        ((BaseField)this.getField()).getRecord().addListener(new AddNewDistHandler(null));
        return sField;
    }

}

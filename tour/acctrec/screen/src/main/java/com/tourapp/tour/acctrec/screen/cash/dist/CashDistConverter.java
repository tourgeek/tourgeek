/**
  * @(#)CashDistConverter.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.tour.acctrec.screen.cash.dist;

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
import com.tourapp.tour.assetdr.screen.batch.dist.*;
import com.tourapp.tour.acctrec.db.*;
import com.tourapp.tour.assetdr.db.*;
import com.tourapp.tour.booking.db.*;
import org.jbundle.base.screen.model.*;

/**
 *  CashDistConverter - Distribution display.
 */
public class CashDistConverter extends DistributionConverter
{
    /**
     * Default constructor.
     */
    public CashDistConverter()
    {
        super();
    }
    /**
     * Constructor.
     */
    public CashDistConverter(Converter converter)
    {
        this();
        this.init(converter);
    }
    /**
     * Initialize class fields.
     */
    public void init(BaseField field)
    {
        super.init(field);
    }
    /**
     * Create a new dist record using the detail record information.
     */
    public void createNewDist(BaseField referenceField) throws DBException
    {
        // Now create the new distribution
        Record recCashBatchDetail = this.getDetailRecord();
        m_recBankTrxBatchDist.addNew();
        m_recBankTrxBatchDist.getField(CashBatchDist.BOOKING_ID).moveFieldToThis(referenceField);
        m_recBankTrxBatchDist.getField(CashBatchDist.AMOUNT).moveFieldToThis(recCashBatchDetail.getField(CashBatchDetail.AMOUNT));
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
                ro = ((BaseField)this.getField()).getRecord().findRecordOwner();
        CashBatchDist recCashBatchDist = this.createDistRecord(ro);
        if (recordOwner == null)
        {
            if (recCashBatchDist.getRecordOwner() != null)
                recCashBatchDist.getRecordOwner().removeRecord(recCashBatchDist);
            if (this.getField() != null)
                if (this.getField().getRecord() != null)
                    ((BaseField)this.getField()).getRecord().addListener(new FreeOnFreeHandler(recCashBatchDist));
        }
        recCashBatchDist.setKeyArea(CashBatchDist.CASH_BATCH_DETAIL_ID_KEY);
        recCashBatchDist.addListener(new SubFileFilter(recBatchDetail));
        return recCashBatchDist;
    }
    /**
     * CreateDistRecord Method.
     */
    public CashBatchDist createDistRecord(RecordOwner recordOwner)
    {
        return new CashBatchDist(recordOwner);
    }
    /**
     * Add the current dist record to the cache under this key.
     * @return The description for display.
     */
    public String addCurrentToCache(Object bookmarkKey)
    {
        // Then, update the cache
        String strResult = SPLIT;
        if (!m_recBankTrxBatchDist.getField(CashBatchDist.BOOKING_ID).isNull())
        {
            strResult = this.getDisplayField(null).toString();
            m_htCache.put(bookmarkKey, strResult);
            String strAccountNo = ((ReferenceField)m_recBankTrxBatchDist.getField(CashBatchDist.BOOKING_ID)).getReference().getField(Booking.ID).toString();
            m_htCacheAccountNo.put(bookmarkKey, strAccountNo);
        }
        return strResult;
    }
    /**
     * Get the detail record (dist's header) for this record.
     */
    public Record getDetailRecord()
    {
        Record recCashBatchDetail = (Record)((BaseField)this.getField()).getRecord().getRecordOwner().getRecord(CashBatchDetail.CASH_BATCH_DETAIL_FILE);
        return recCashBatchDetail;
    }
    /**
     * Get the description display field.
     * @recSecondary The (optional) secondary record.
     * @return the field.
     */
    public BaseField getDisplayField(Record recSecondary)
    {
        if (recSecondary == null)
            recSecondary = ((ReferenceField)m_recBankTrxBatchDist.getField(CashBatchDist.BOOKING_ID)).getReference();
        return recSecondary.getField(Booking.DESCRIPTION);
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
        ScreenComponent sField = (ScreenField)converter.getField().setupDefaultView(itsLocation, targetScreen, converter, iDisplayFieldDesc, properties);
        
        Record recBooking = ((ReferenceField)converter.getField()).getReferenceRecord();
        
        // Don't want to display the booking number if code doesn't exist (too confusing)
        //ScreenField sFieldNo = recBooking.getField(Booking.CODE).getSFieldAt(0);
        //Converter fldConverter = sFieldNo.getConverter();
        //BaseField field = (BaseField)fldConverter.getField();
        //if (fldConverter != field)
        //    fldConverter.free();   // not necessary anymore.
        //sFieldNo.setConverter(new AccountNoDistConverter(field, this));
        
        ScreenComponent sFieldDesc = this.getDisplayField(recBooking).getComponent(0);
        sFieldDesc.setConverter(new AccountDescDistConverter((Converter)sFieldDesc.getConverter(), this));
        
        properties = new HashMap<String,Object>();
        properties.put(ScreenModel.COMMAND, MenuConstants.FORMDETAIL);
        properties.put(ScreenModel.IMAGE, MenuConstants.FORMDETAIL);
        BaseField.createScreenComponent(ScreenModel.CANNED_BOX, targetScreen.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), targetScreen, null, ScreenConstants.DEFAULT_DISPLAY, properties);
        ((BaseField)this.getField()).addListener(new ReferenceChangedHandler(this));
        
        ((BaseField)this.getField()).getRecord().addListener(new AddNewCashDistHandler(null));
        return sField;
    }

}

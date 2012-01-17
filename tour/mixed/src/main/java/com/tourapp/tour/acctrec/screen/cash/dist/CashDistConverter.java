/**
 * @(#)CashDistConverter.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.acctrec.screen.cash.dist;

import org.jbundle.base.db.Record;
import org.jbundle.base.db.RecordOwner;
import org.jbundle.base.db.event.FreeOnFreeHandler;
import org.jbundle.base.db.filter.SubFileFilter;
import org.jbundle.base.field.BaseField;
import org.jbundle.base.field.ReferenceField;
import org.jbundle.base.screen.model.BasePanel;
import org.jbundle.base.screen.model.SCannedBox;
import org.jbundle.base.screen.model.ScreenField;
import org.jbundle.base.screen.model.util.ScreenLocation;
import org.jbundle.base.util.MenuConstants;
import org.jbundle.base.util.ScreenConstants;
import org.jbundle.base.util.Utility;
import org.jbundle.model.DBException;
import org.jbundle.model.screen.ScreenComponent;
import org.jbundle.thin.base.db.Converter;

import com.tourapp.tour.acctrec.db.CashBatchDetail;
import com.tourapp.tour.acctrec.db.CashBatchDist;
import com.tourapp.tour.assetdr.db.BankTrxBatchDist;
import com.tourapp.tour.assetdr.screen.batch.dist.AccountDescDistConverter;
import com.tourapp.tour.assetdr.screen.batch.dist.DistributionConverter;
import com.tourapp.tour.assetdr.screen.batch.dist.ReferenceChangedHandler;
import com.tourapp.tour.booking.db.Booking;

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
        m_recBankTrxBatchDist.getField(CashBatchDist.kBookingID).moveFieldToThis(referenceField);
        m_recBankTrxBatchDist.getField(CashBatchDist.kAmount).moveFieldToThis(recCashBatchDetail.getField(CashBatchDetail.kAmount));
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
        CashBatchDist recCashBatchDist = this.createDistRecord(ro);
        if (recordOwner == null)
        {
            if (recCashBatchDist.getRecordOwner() != null)
                recCashBatchDist.getRecordOwner().removeRecord(recCashBatchDist);
            if (this.getField() != null)
                if (this.getField().getRecord() != null)
                    ((BaseField)this.getField()).getRecord().addListener(new FreeOnFreeHandler(recCashBatchDist));
        }
        recCashBatchDist.setKeyArea(CashBatchDist.kCashBatchDetailIDKey);
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
        if (!m_recBankTrxBatchDist.getField(CashBatchDist.kBookingID).isNull())
        {
            strResult = this.getDisplayField(null).toString();
            m_htCache.put(bookmarkKey, strResult);
            String strAccountNo = ((ReferenceField)m_recBankTrxBatchDist.getField(CashBatchDist.kBookingID)).getReference().getField(Booking.kID).toString();
            m_htCacheAccountNo.put(bookmarkKey, strAccountNo);
        }
        return strResult;
    }
    /**
     * Get the detail record (dist's header) for this record.
     */
    public Record getDetailRecord()
    {
        Record recCashBatchDetail = ((BaseField)this.getField()).getRecord().getRecordOwner().getRecord(CashBatchDetail.kCashBatchDetailFile);
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
            recSecondary = ((ReferenceField)m_recBankTrxBatchDist.getField(CashBatchDist.kBookingID)).getReference();
        return recSecondary.getField(Booking.kDescription);
    }
    /**
     * Set up the default control for this field.
     * @param  itsLocation     Location of this component on screen (ie., GridBagConstraint).
     * @param  targetScreen    Where to place this component (ie., Parent screen or GridBagLayout).
     * @param  iDisplayFieldDesc Display the label? (optional).
     * @return   Return the component or ScreenField that is created for this field.
     */
    public ScreenField setupDefaultView(ScreenLocation itsLocation, BasePanel targetScreen, Converter converter, int iDisplayFieldDesc)
    {
ScreenField sField = (ScreenField)converter.getField().setupDefaultView(itsLocation, targetScreen, converter, iDisplayFieldDesc);

Record recBooking = ((ReferenceField)converter.getField()).getReferenceRecord();

// Don't want to display the booking number if code doesn't exist (too confusing)
//ScreenField sFieldNo = recBooking.getField(Booking.kCode).getSFieldAt(0);
//Converter fldConverter = sFieldNo.getConverter();
//BaseField field = (BaseField)fldConverter.getField();
//if (fldConverter != field)
//    fldConverter.free();   // not necessary anymore.
//sFieldNo.setConverter(new AccountNoDistConverter(field, this));

ScreenComponent sFieldDesc = this.getDisplayField(recBooking).getComponent(0);
sFieldDesc.setConverter(new AccountDescDistConverter((Converter)sFieldDesc.getConverter(), this));
new SCannedBox(targetScreen.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), targetScreen, null, ScreenConstants.DEFAULT_DISPLAY, null, null, MenuConstants.FORMDETAIL, MenuConstants.FORMDETAIL, null);
((BaseField)this.getField()).addListener(new ReferenceChangedHandler(this));

((BaseField)this.getField()).getRecord().addListener(new AddNewCashDistHandler(null));
return sField;
    }

}

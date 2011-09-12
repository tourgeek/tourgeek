/**
 * @(#)UpdateArTrxAcctDetailHandler.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.acctrec.db.event;

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
import com.tourapp.tour.genled.db.*;
import com.tourapp.tour.product.tour.db.*;
import com.tourapp.tour.acctrec.db.*;
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.product.base.db.*;

/**
 *  UpdateArTrxAcctDetailHandler - Update the G/L Detail when a booking adds an A/R modification..
 */
public class UpdateArTrxAcctDetailHandler extends UpdateAcctDetailHandler
{
    protected Record m_recBooking = null;
    /**
     * Default constructor.
     */
    public UpdateArTrxAcctDetailHandler()
    {
        super();
    }
    /**
     * Constructor.
     */
    public UpdateArTrxAcctDetailHandler(Record recBooking)
    {
        this();
        this.init(recBooking);
    }
    /**
     * Initialize class fields.
     */
    public void init(Record recBooking)
    {
        m_recBooking = null;
        m_recBooking = recBooking;
        super.init(null);
    }
    /**
     * Override this method and call recAcctDetailDist.addDetailTrx(...) to
     * add the G/L transaction.
     * @param recTransactionType TransactionType record
     * @param recAcctDetailDist AcctDetailDist record
     * @param recAcctDetail AcctDetail record
     * @param recPeriod Period record
     * @param dCurrentBalance The current balance for this type of transaction.
     */
    public void addDetailTrx(TransactionType recTransactionType, AcctDetailDist recAcctDetailDist, AcctDetail recAcctDetail, Period recPeriod, double dCurrentBalance)
    {
        Record recArTrx = this.getOwner();
        double dAmount = recArTrx.getField(ArTrx.kAmount).getValue();
        ReferenceField fldAccount = null;
        if (Account.DEBIT.equalsIgnoreCase(recTransactionType.getField(TransactionType.kTypicalBalance).toString()))
            fldAccount = (ReferenceField)this.getDrAccount();
        else
        {
            fldAccount = (ReferenceField)this.getCrAccount();
            dAmount = -dAmount;
        }
        DateTimeField fldTrxDate = (DateTimeField)recArTrx.getField(ArTrx.kTrxDate);
        if ("DepTrx".equalsIgnoreCase(recTransactionType.getField(TransactionType.kTypeCode).toString()))
        {
            fldTrxDate = (DateTimeField)recArTrx.getField(ArTrx.kDepartureDate);
            fldAccount = (ReferenceField)this.getDepartureDrAccount();
        }
        if ("DepDist".equalsIgnoreCase(recTransactionType.getField(TransactionType.kTypeCode).toString()))
        {
            fldTrxDate = (DateTimeField)recArTrx.getField(ArTrx.kDepartureDate);
            fldAccount = (ReferenceField)this.getDepartureCrAccount();
        }
        DateTimeField fldEntryDate = null;  // Now
        BaseField fldTrxTypeID = recArTrx.getField(ArTrx.kID);
        int iUserID = Integer.parseInt(((BaseApplication)this.getOwner().getRecordOwner().getTask().getApplication()).getUserID());
        recAcctDetailDist.addDetailTrx(fldAccount, fldTrxDate, fldTrxTypeID, recTransactionType, fldEntryDate, dAmount, iUserID, recAcctDetail, recPeriod);
    }
    /**
     * Get the product category for this booking.
     */
    public ProductCategory getProductCategory()
    {
        // Booking->Tour->TourHeader->ProductCat P/P  vs  A/R
        Tour recTour = (Tour)((ReferenceField)m_recBooking.getField(Booking.kTourID)).getReference();
        TourHeader recTourHeader = (TourHeader)((ReferenceField)recTour.getField(Tour.kTourHeaderID)).getReference();
        ProductCategory recProductCategory = (ProductCategory)((ReferenceField)recTourHeader.getField(TourHeader.kProductCatID)).getReference();
        return recProductCategory;
    }
    /**
     * Get the Debit Account field.
     * @return The debit account field.
     */
    public ReferenceField getDrAccount()
    {
        return (ReferenceField)this.getProductCategory().getField(ProductCategory.kArAccountID);
    }
    /**
     * Get the Credit Account field.
     * @return The credit account field.
     */
    public ReferenceField getCrAccount()
    {
        return (ReferenceField)this.getProductCategory().getField(ProductCategory.kPPAccountID);
    }
    /**
     * GetDepartureDrAccount Method.
     */
    public ReferenceField getDepartureDrAccount()
    {
        return (ReferenceField)this.getProductCategory().getField(ProductCategory.kPPAccountID);
    }
    /**
     * GetDepartureCrAccount Method.
     */
    public ReferenceField getDepartureCrAccount()
    {
        return (ReferenceField)this.getProductCategory().getField(ProductCategory.kIncomeAccountID);
    }

}

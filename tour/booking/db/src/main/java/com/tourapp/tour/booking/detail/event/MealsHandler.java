/**
  * @(#)MealsHandler.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.tour.booking.detail.event;

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
import com.tourapp.tour.booking.detail.db.*;
import com.tourapp.tour.product.base.db.*;

/**
 *  MealsHandler - Set up the special meals string that summarized all the meals for this product..
 */
public class MealsHandler extends FieldListener
{
    protected MealPlan m_recMealPlan = null;
    /**
     * Default constructor.
     */
    public MealsHandler()
    {
        super();
    }
    /**
     * MealsHandler Method.
     */
    public MealsHandler(BaseField field)
    {
        this();
        this.init(field);
    }
    /**
     * Initialize class fields.
     */
    public void init(BaseField field)
    {
        m_recMealPlan = null;
        super.init(field);
    }
    /**
     * Free the resources.
     */
    public void free()
    {
        m_recMealPlan = null;
        super.free();
    }
    /**
     * Set the object that owns this listener.
     * @owner The object that this listener is being added to (if null, this listener is being removed).
     */
    public void setOwner(ListenerOwner owner)
    {
        super.setOwner(owner);
        if (this.getOwner() != null)
        {
            RecordOwner screen = this.getOwner().getRecord().getRecordOwner();
            if (screen != null) if (screen instanceof GridScreenParent)
            {
                this.getOwner().setSelected(false);
            }
        }
    }
    /**
     * Get the meals in a special format:
     * M1 M2
     * M1
     * This is primarly used to pass to thin.
     */
    public Object doGetData()
    {
        if (!this.getOwner().isNull())
            return super.doGetData();   // Already cached
        if (m_recMealPlan == null)
        {
            RecordOwner recordOwner = this.getOwner().getRecord().findRecordOwner();
            m_recMealPlan = new MealPlan(recordOwner);
            if (recordOwner != null)
                recordOwner.removeRecord(m_recMealPlan);
            this.getOwner().getRecord().addListener(new FreeOnFreeHandler(m_recMealPlan));
        }
        BookingDetail recCustSaleDetail = (BookingDetail)this.getOwner().getRecord();
        String strMeals = Constants.BLANK;
        Date dateStart = recCustSaleDetail.getStartDate();
        Date dateEnd = recCustSaleDetail.getEndDate();
        if ((dateStart == null) || (dateEnd == null))
            return strMeals;
        Calendar calendar = Converter.gCalendar;
        calendar.setTime(dateStart);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        dateStart = calendar.getTime();
        calendar.setTime(dateEnd);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        dateEnd = calendar.getTime();
        int iDays = (int)((dateEnd.getTime() - dateStart.getTime()) / Constants.KMS_IN_A_DAY) + 2;
        if (iDays <= 0)
            return null;
        String[] rgstrMeals = new String[iDays];
        Date date = new Date(dateStart.getTime());
        for (int iDay = 0; iDay < iDays; iDay++)
        {
            rgstrMeals[iDay] = recCustSaleDetail.getMealDesc(date, false, m_recMealPlan);
            date.setTime(date.getTime() + Constants.KMS_IN_A_DAY);
        
            if (rgstrMeals[iDay] == null)
                rgstrMeals[iDay] = Constants.BLANK;
            strMeals += rgstrMeals[iDay] + Constants.RETURN;
        }
        this.getOwner().setData(strMeals);
        return super.doGetData();
    }

}

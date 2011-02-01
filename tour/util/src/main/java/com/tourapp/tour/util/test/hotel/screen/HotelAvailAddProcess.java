/**
 *  @(#)HotelAvailAddProcess.
 *  Copyright © 2010 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.util.test.hotel.screen;

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
import org.jbundle.base.thread.*;
import org.jbundle.thin.base.screen.*;
import com.tourapp.tour.util.test.hotel.db.*;

/**
 *  HotelAvailAddProcess - Add records and update amounts.
 */
public class HotelAvailAddProcess extends BaseProcess
{
    /**
     * Default constructor.
     */
    public HotelAvailAddProcess()
    {
        super();
    }
    /**
     * Constructor.
     */
    public HotelAvailAddProcess(RecordOwnerParent taskParent, Record recordMain, Map<String,Object> properties)
    {
        this();
        this.init(taskParent, recordMain, properties);
    }
    /**
     * Initialize class fields.
     */
    public void init(RecordOwnerParent taskParent, Record recordMain, Map<String, Object> properties)
    {
        super.init(taskParent, recordMain, properties);
    }
    /**
     * Run Method.
     */
    public void run()
    {
        int iCount = Integer.parseInt(this.getProperty("count"));
        String strTableName = this.getProperty(DBParams.TABLE);
        HotelAvail recHotelAvail = new HotelAvail();
        recHotelAvail.setTableNames(strTableName);
        recHotelAvail.init(this);
        
        try {
            recHotelAvail.addNew();
            
            recHotelAvail.getField(HotelAvail.kAmountBeforeTax).setValue(Math.random());
            recHotelAvail.getField(HotelAvail.kAmountAfterTax).setValue(iCount);
            
            recHotelAvail.add();
        } catch (DBException e) {
            e.printStackTrace();
        }
    }

}

/**
 * @(#)PrintCheckToolbar.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.acctpay.screen.check;

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
import com.tourapp.tour.acctrec.screen.refund.*;
import org.jbundle.base.screen.model.report.*;
import com.tourapp.tour.acctpay.db.*;
import com.tourapp.tour.assetdr.db.*;
import com.tourapp.tour.genled.db.*;

/**
 *  PrintCheckToolbar - .
 */
public class PrintCheckToolbar extends ReportToolbar
{
    /**
     * Default constructor.
     */
    public PrintCheckToolbar()
    {
        super();
    }
    /**
     * Constructor.
     * @param itsLocation The location of this component within the parent.
     * @param parentScreen The parent screen.
     * @param fieldConverter The field this screen field is linked to.
     * @param iDisplayFieldDesc Do I display the field desc?.
     */
    public PrintCheckToolbar(ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc)
    {
        this();
        this.init(itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc);
    }
    /**
     * Initialize class fields.
     */
    public void init(ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc)
    {
        super.init(itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc);
    }
    /**
     * SetupSFields Method.
     */
    public void setupSFields()
    {
        super.setupSFields();
        //if (this.isPrintReport())
        //  return;   // Don't display params if entered
        this.getRecord(PrintCheckScreenRecord.kPrintCheckScreenRecordFile).getField(PrintCheckScreenRecord.kBankAcctID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_INPUT_LOCATION, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(PrintCheckScreenRecord.kPrintCheckScreenRecordFile).getField(PrintCheckScreenRecord.kChecksToPrint).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_INPUT_LOCATION, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(PrintCheckScreenRecord.kPrintCheckScreenRecordFile).getField(PrintCheckScreenRecord.kCheckDate).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_INPUT_LOCATION, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(PrintCheckScreenRecord.kPrintCheckScreenRecordFile).getField(PrintCheckScreenRecord.kNextCheckNo).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_INPUT_LOCATION, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
    }

}

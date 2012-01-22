/**
 * @(#)RefundScreenRecord.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.acctrec.screen.refund;

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
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import com.tourapp.tour.acctrec.db.*;
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.genled.db.*;
import com.tourapp.tour.assetdr.db.*;

/**
 *  RefundScreenRecord - Fields for the refund screens.
 */
public class RefundScreenRecord extends CheckScreenRecord
{
    private static final long serialVersionUID = 1L;

    //public static final int kReportDate = kReportDate;
    //public static final int kReportUserID = kReportUserID;
    //public static final int kReportTime = kReportTime;
    //public static final int kReportPage = kReportPage;
    //public static final int kReportTotal = kReportTotal;
    //public static final int kReportCount = kReportCount;
    //public static final int kCheckNo = kCheckNo;
    //public static final int kCheckDate = kCheckDate;
    public static final int kNextCheckNo = kCheckScreenRecordLastField + 1;
    public static final int kStartTrxStatusID = kNextCheckNo + 1;
    public static final int kEndTrxStatusID = kStartTrxStatusID + 1;
    public static final int kBankAcctID = kEndTrxStatusID + 1;
    public static final int kSubmittedTrxStatusID = kBankAcctID + 1;
    public static final int kHeldTrxStatusID = kSubmittedTrxStatusID + 1;
    public static final int kPayTrxStatusID = kHeldTrxStatusID + 1;
    public static final int kRefundScreenRecordLastField = kPayTrxStatusID;
    public static final int kRefundScreenRecordFields = kPayTrxStatusID - DBConstants.MAIN_FIELD + 1;
    /**
     * Default constructor.
     */
    public RefundScreenRecord()
    {
        super();
    }
    /**
     * Constructor.
     */
    public RefundScreenRecord(RecordOwner screen)
    {
        this();
        this.init(screen);
    }
    /**
     * Initialize class fields.
     */
    public void init(RecordOwner screen)
    {
        super.init(screen);
    }

    public static final String kRefundScreenRecordFile = null;  // Screen field
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        //if (iFieldSeq == kReportDate)
        //  field = new RefundScreenRecord_ReportDate(this, "ReportDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kReportUserID)
            field = new RefundScreenRecord_ReportUserID(this, "ReportUserID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kReportTime)
        //  field = new RefundScreenRecord_ReportTime(this, "ReportTime", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kReportPage)
        //  field = new ShortField(this, "ReportPage", Constants.DEFAULT_FIELD_LENGTH, null, new Short((short)1));
        //if (iFieldSeq == kReportTotal)
        //  field = new CurrencyField(this, "ReportTotal", Constants.DEFAULT_FIELD_LENGTH, null, new Double(0));
        //if (iFieldSeq == kReportCount)
        //  field = new IntegerField(this, "ReportCount", Constants.DEFAULT_FIELD_LENGTH, null, new Integer(0));
        if (iFieldSeq == kNextCheckNo)
            field = new IntegerField(this, "NextCheckNo", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kCheckNo)
        //  field = new IntegerField(this, "CheckNo", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //if (iFieldSeq == kCheckDate)
        //  field = new RefundScreenRecord_CheckDate(this, "CheckDate", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kStartTrxStatusID)
            field = new TrxStatusField(this, "StartTrxStatusID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kEndTrxStatusID)
            field = new TrxStatusField(this, "EndTrxStatusID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kBankAcctID)
            field = new BankAcctField(this, "BankAcctID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kSubmittedTrxStatusID)
            field = new TrxStatusField(this, "SubmittedTrxStatusID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kHeldTrxStatusID)
            field = new TrxStatusField(this, "HeldTrxStatusID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kPayTrxStatusID)
            field = new TrxStatusField(this, "PayTrxStatusID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kRefundScreenRecordLastField)
                field = new EmptyField(this);
        }
        return field;
    }

}

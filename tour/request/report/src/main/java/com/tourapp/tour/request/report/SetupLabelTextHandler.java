/**
  * @(#)SetupLabelTextHandler.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.tour.request.report;

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
import com.tourapp.tour.request.db.*;
import com.tourapp.tour.base.db.shared.*;

/**
 *  SetupLabelTextHandler - Setup the free text for the labels.
 */
public class SetupLabelTextHandler extends FileListener
{
    /**
     * Default constructor.
     */
    public SetupLabelTextHandler()
    {
        super();
    }
    /**
     * SetupLabelTextHandler Method.
     */
    public SetupLabelTextHandler(Record record)
    {
        this();
        this.init(record);
    }
    /**
     * Initialize class fields.
     */
    public void init(Record record)
    {
        super.init(record);
    }
    /**
     * Called when a valid record is read from the table/query.
     * @param bDisplayOption If true, display any changes.
     */
    public void doValidRecord(boolean bDisplayOption)
    {
        super.doValidRecord(bDisplayOption); 
        Record recRequest = this.getOwner();
        RecordOwner recordOwner = recRequest.getRecordOwner();
        Record recScreen = (Record)recordOwner.getScreenRecord();
        Record recRequestDetail = (Record)recordOwner.getRecord(RequestDetail.REQUEST_DETAIL_FILE);
        Record recItem = (Record)recordOwner.getRecord(Brochure.BROCHURE_FILE);
        BaseField fldFullAddress = recScreen.getField(RequestLabelsScreenRecord.FULL_ADDRESS);
        BaseField fldRequestText = recScreen.getField(RequestLabelsScreenRecord.REQUEST_TEXT);
        fldFullAddress.initField(false);
        fldRequestText.initField(false);
        
        String strFullAddress = Constants.BLANK;
        strFullAddress = this.addOptionalString(strFullAddress, recRequest, Request.ATTENTION, "\n");
        strFullAddress = this.addOptionalString(strFullAddress, recRequest, Request.GENERIC_NAME, "\n");
        strFullAddress = this.addOptionalString(strFullAddress, recRequest, Request.ADDRESS_LINE_1, "\n");
        strFullAddress = this.addOptionalString(strFullAddress, recRequest, Request.ADDRESS_LINE_2, "\n");
        strFullAddress = this.addOptionalString(strFullAddress, recRequest, Request.CITY_OR_TOWN, ", ");
        strFullAddress = this.addOptionalString(strFullAddress, recRequest, Request.STATE_OR_REGION, "  ");
        strFullAddress = this.addOptionalString(strFullAddress, recRequest, Request.POSTAL_CODE, null);
        strFullAddress += '\n';
        strFullAddress = this.addOptionalString(strFullAddress, recRequest, Request.COUNTRY, null);
        fldFullAddress.setString(strFullAddress);
        
        String strRequestText = Constants.BLANK;
        recRequestDetail.close();
        try   {
            while (recRequestDetail.hasNext())
            {
                recRequestDetail.next();
                strRequestText += '(';
                strRequestText = this.addOptionalString(strRequestText, recRequestDetail, RequestDetail.BROCHURE_QTY, ")");
                if ((recRequestDetail.getField(RequestDetail.BROCHURE_ID).isNull()) || (recItem.getField(Brochure.DESCRIPTION).isNull()))
                    strRequestText = this.addOptionalString(strRequestText, recRequestDetail, RequestDetail.BROCHURE_DESC, null);
                else
                    strRequestText = this.addOptionalString(strRequestText, recItem, Brochure.DESCRIPTION, null);
                strRequestText += ' ';
            }
        } catch (DBException ex)    {
            ex.printStackTrace();
        } 
        strRequestText += recRequest.getField(Request.BROCHURE_TEXT).toString();
        fldRequestText.setString(strRequestText);
    }
    /**
     * Add this field's string to the current string and if the string is not null add the optional string.
     */
    public String addOptionalString(String strFullName, Record record, String iFieldSeq, String strOptional)
    {
        String string = record.getField(iFieldSeq).toString();
        if ((string != null) && (string.length() > 0))
        {
            strFullName += string;
            if (strOptional != null)
                strFullName += strOptional;
        }
        return strFullName;
    }

}

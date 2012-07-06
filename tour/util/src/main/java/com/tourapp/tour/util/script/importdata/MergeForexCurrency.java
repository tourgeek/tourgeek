/**
  * @(#)MergeForexCurrency.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.tour.util.script.importdata;

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
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import org.jbundle.app.program.script.data.importfix.base.*;
import com.tourapp.tour.base.db.*;

/**
 *  MergeForexCurrency - .
 */
public class MergeForexCurrency extends MergeHtml
{
    /**
     * Default constructor.
     */
    public MergeForexCurrency()
    {
        super();
    }
    /**
     * Constructor.
     */
    public MergeForexCurrency(RecordOwnerParent taskParent, Record recordMain, Map<String,Object> properties)
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
     * Open the main file.
     */
    public Record openMainRecord()
    {
        return new Currencys(this);
    }
    /**
     * Open the other files.
     */
    public void openOtherRecords()
    {
        super.openOtherRecords();
        new Country(this);
    }
    /**
     * Merge this source record with the destination record.
     * @param recSource
     * @param recDest.
     */
    public void mergeSourceData(Record recSource, Record recDest, boolean bFound)
    {
        if ((recSource.getField("Alphabetic code").isNull())
                || (recSource.getField("Currency").isNull())
                || (!Character.isLetter(recSource.getField("Currency").toString().charAt(0))))
        {
            try {
                recDest.addNew();
            } catch (DBException e) {
            }
            return;
        }
        if (recDest.getField(Currencys.CURRENCY_CODE).isNull())
            recDest.getField(Currencys.CURRENCY_CODE).moveFieldToThis(recSource.getField("Alphabetic code"));
        if (recDest.getField(Currencys.DESCRIPTION).isNull())
            recDest.getField(Currencys.DESCRIPTION).moveFieldToThis(recSource.getField("Currency"));
        if (!recSource.getField("Symbol").isNull())
            recDest.getField(Currencys.SIGN).moveFieldToThis(recSource.getField("Symbol"));
        if (!recSource.getField("Subdivision").isNull())
        {
            String strSubdivision = recSource.getField("Subdivision").toString();
            String strDesc = null;
            if (strSubdivision.indexOf(' ') != -1)
            {
                strDesc = strSubdivision.substring(0, strSubdivision.indexOf(' '));
                strSubdivision = strSubdivision.substring(strSubdivision.indexOf(' ') + 1);
            }
            if (strDesc != null)
                recDest.getField(Currencys.FRACTION_AMOUNT).setString(strDesc);
            recDest.getField(Currencys.FRACTION_DESC).setString(strSubdivision);
        }
        if (!recSource.getField("Currency").isNull())
            recDest.getField(Currencys.INTEGER_DESC).moveFieldToThis(recSource.getField("Currency"));
        
        String strCountry = recSource.getField("Country").toString();
        Record recCountry = this.getRecord(Country.COUNTRY_FILE);
        recCountry.setKeyArea(Country.NAME_KEY);
        recCountry.getField(Country.NAME).setString(strCountry);
        try {
            if (recDest.getCounterField().isNull())
            {
                recDest.setAutoSequence(false);  // Disable autoseq temporarily
                recDest.getField(Currencys.ID).moveFieldToThis(recSource.getField("Numeric code"));
                recDest.add();
                recDest.setKeyArea(Currencys.ID_KEY);
                recDest.seek(DBConstants.EQUALS);
                recDest.setAutoSequence(true);
            }
            if (recCountry.seek(DBConstants.EQUALS))
            {   // Default currency
                recCountry.edit();
                recCountry.getField(Country.CURRENCYS_ID).moveFieldToThis(recDest.getCounterField());
                recCountry.set();
            }
        } catch (DBException e) {
            e.printStackTrace();
        }
    }
    /**
     * Given this source record, read the destination record.
     * @param recSource The source record
     * @param recDest The destination record
     * @return True if found.
     */
    public boolean readDestRecord(FieldList recSource, Record recDest)
    {
        recDest.getField(Currencys.CURRENCY_CODE).moveFieldToThis(recSource.getField("Alphabetic code"));
        recDest.setKeyArea(Currencys.CURRENCY_CODE_KEY);
        try {
            return (recDest.seek(DBConstants.EQUALS));
        } catch (DBException e) {
            e.printStackTrace();
        }
        return false;
    }

}

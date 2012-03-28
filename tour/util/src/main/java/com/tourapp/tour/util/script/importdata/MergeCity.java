/**
 * @(#)MergeCity.
 * Copyright © 2012 tourapp.com. All rights reserved.
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
import org.jbundle.thin.base.screen.*;
import com.tourapp.tour.base.db.*;

/**
 *  MergeCity - .
 */
public class MergeCity extends MergeData
{
    /**
     * Default constructor.
     */
    public MergeCity()
    {
        super();
    }
    /**
     * Constructor.
     */
    public MergeCity(RecordOwnerParent taskParent, Record recordMain, Map<String,Object> properties)
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
        return new City(this);
    }
    /**
     * Given this source record, read the destination record.
     * @param recSource The source record
     * @param recDest The destination record
     * @return True if found.
     */
    public boolean readDestRecord(FieldList recSource, Record recDest)
    {
        boolean bFound = super.readDestRecord(recSource, recDest);
        if (!bFound)
            return bFound;
        if (!recSource.getField(City.COUNTRY_ID).isNull())
            if (!recDest.getField(City.COUNTRY_ID).isNull())
                if (!recSource.getField(City.COUNTRY_ID).equals(recDest.getField(City.COUNTRY_ID)))
                    return false;   // Not a match
        return bFound;
    }
    /**
     * Merge this source record with the destination record.
     * @param recSource
     * @param recDest.
     */
    public void mergeSourceData(Record recSource, Record recDest, boolean bFound)
    {
        super.mergeSourceData(recSource, recDest, bFound);
        if (!bFound)
            recDest.getField(City.CITY_TYPE).setString(CityTypeField.CITY);
        if ((recDest.getField(City.TICKET_CITY_DESC).isNull()) || (recDest.getField(City.TICKET_CITY_DESC).toString().equalsIgnoreCase(recSource.getField(City.TICKET_CITY_DESC).toString())))
            recDest.getField(City.TICKET_CITY_DESC).moveFieldToThis(recSource.getField(City.TICKET_CITY_DESC));
    }

}

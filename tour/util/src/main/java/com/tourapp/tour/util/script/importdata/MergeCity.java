/**
 * @(#)MergeCity.
 * Copyright © 2011 tourapp.com. All rights reserved.
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
import org.jbundle.base.screen.model.*;
import org.jbundle.base.screen.model.util.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
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
        if (!recSource.getField(City.kCountryID).isNull())
            if (!recDest.getField(City.kCountryID).isNull())
                if (!recSource.getField(City.kCountryID).equals(recDest.getField(City.kCountryID)))
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
            recDest.getField(City.kCityType).setString(CityTypeField.CITY);
        if ((recDest.getField(City.kTicketCityDesc).isNull()) || (recDest.getField(City.kTicketCityDesc).toString().equalsIgnoreCase(recSource.getField(City.kTicketCityDesc).toString())))
            recDest.getField(City.kTicketCityDesc).moveFieldToThis(recSource.getField(City.kTicketCityDesc));
    }

}

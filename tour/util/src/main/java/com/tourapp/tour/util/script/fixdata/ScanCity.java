/**
 *  @(#)ScanCity.
 *  Copyright © 2010 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.util.script.fixdata;

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
 *  ScanCity - .
 */
public class ScanCity extends ScanData
{
    /**
     * Default constructor.
     */
    public ScanCity()
    {
        super();
    }
    /**
     * Constructor.
     */
    public ScanCity(RecordOwnerParent taskParent, Record recordMain, Map<String,Object> properties)
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
     * FixRecord Method.
     */
    public void fixRecord(Record record)
    {
        super.fixRecord(record);
        this.fixCapitalization(record.getField(City.kDescription));
        this.fixCapitalization(record.getField(City.kTicketCityDesc));
        record.getField(City.kCityType).setString(CityTypeField.AIRPORT);
    }

}

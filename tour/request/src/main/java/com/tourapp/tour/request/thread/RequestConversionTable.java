/**
 * @(#)RequestConversionTable.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.request.thread;

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
import java.util.*;

/**
 *  RequestConversionTable - Conversion table of the request xml file into the Request table.
 */
public class RequestConversionTable extends ListResourceBundle
{
    protected Object[][] contents = {
        {"ProfileCode","ProfileCode"},
        {"GenericName","GenericName"},
        {"AddressLine1","AddressLine1"},
        {"AddressLine2","AddressLine2"},
        {"CityOrTown","CityOrTown"},
        {"StateOrRegion","StateOrRegion"},
        {"PostalCode","PostalCode"},
        {"Country","Country"},
        {"Attention","Attention"},
        {"Email","Email"},
        {"BrochureText","BrochureText"},
    };
    /**
     * Get the resource table.
     */
    public Object[][] getContents()
    {
        return contents;
    }

}

/**
  * @(#)RequestDetailConversionTable.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.tour.request.thread;

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
 *  RequestDetailConversionTable - Field Conversion table.
 */
public class RequestDetailConversionTable extends ListResourceBundle
{
    protected Object[][] contents = {
        {"BrochureID","BrochureID"},
        {"BrochureQty","BrochureQty"},
        {"BrochureDesc","Description"},
    };
    /**
     * Get the resource table.
     */
    public Object[][] getContents()
    {
        return contents;
    }

}

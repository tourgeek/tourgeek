/**
  * @(#)FedRadioConverter.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.tour.payroll.screen.tax;

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

/**
 *  FedRadioConverter - Radio Converter.
 */
public class FedRadioConverter extends RadioConverter
{
    /**
     * Default constructor.
     */
    public FedRadioConverter()
    {
        super();
    }
    /**
     * Constructor.
     */
    public FedRadioConverter(BaseField field, String strTargetValue, boolean bTrueIfMatch)
    {
        this();
        this.init(field, strTargetValue, bTrueIfMatch);
    }
    /**
     * Initialize class fields.
     */
    public void init(BaseField field, String strTargetValue, boolean bTrueIfMatch)
    {
        super.init(field, strTargetValue, bTrueIfMatch);
    }
    /**
     * GetFieldDesc Method.
     */
    public String getFieldDesc()
    {
        return "Federal Taxes";
    }

}

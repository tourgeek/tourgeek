/**
  * @(#)StateRadioConverter.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.tour.payroll.screen.tax;

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
 *  StateRadioConverter - State Tax Code Radio Button.
 */
public class StateRadioConverter extends RadioConverter
{
    /**
     * Default constructor.
     */
    public StateRadioConverter()
    {
        super();
    }
    /**
     * Constructor.
     */
    public StateRadioConverter(BaseField field, String strTargetValue, boolean bTrueIfMatch)
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
        return "State Taxes";
    }

}

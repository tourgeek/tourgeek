/**
  * @(#)DisableOnSignHandler.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.tour.assetdr.screen.batch;

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
import org.jbundle.base.screen.model.*;
import com.tourgeek.tour.assetdr.db.*;
import com.tourgeek.tour.base.db.*;
import org.jbundle.main.screen.*;
import com.tourgeek.tour.assetdr.screen.trx.*;
import com.tourgeek.tour.assetdr.screen.batch.dist.*;
import com.tourgeek.tour.genled.db.*;

/**
 *  DisableOnSignHandler - .
 */
public class DisableOnSignHandler extends DisableOnFieldHandler
{
    public static final String NEGATIVE = "-";
    public static final String POSITIVE = "+";
    protected String m_strSign;
    /**
     * Default constructor.
     */
    public DisableOnSignHandler()
    {
        super();
    }
    /**
     * Constructor.
     * @param fieldToDisable The field to disable when this listener's owner matches the target string.
     * @param strSign If field is this sign, disable.
     */
    public DisableOnSignHandler(BaseField fieldToDisable, String strSign)
    {
        this();
        this.init(fieldToDisable, strSign);
    }
    /**
     * Initialize class fields.
     */
    public void init(BaseField fieldToDisable, String strSign)
    {
        m_strSign = "";
        m_strSign = strSign;
        super.init(null, fieldToDisable, null, true);
    }
    /**
     * Compare the field to the string.
     * @return true if match.
     */
    public boolean compareFieldToString()
    {
        boolean bSign = true;
        if (this.getOwner().getValue() < 0)
            bSign = false;
        if (DisableOnSignHandler.NEGATIVE.equalsIgnoreCase(m_strSign))
            bSign = !bSign;
        return bSign;
    }

}

/**
 * @(#)ReferenceChangedHandler.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.assetdr.screen.batch.dist;

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
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import com.tourapp.tour.assetdr.db.*;
import com.tourapp.tour.genled.db.*;

/**
 *  ReferenceChangedHandler - .
 */
public class ReferenceChangedHandler extends FieldListener
{
    protected DistributionConverter m_distConverter = null;
    /**
     * Default constructor.
     */
    public ReferenceChangedHandler()
    {
        super();
    }
    /**
     * ReferenceChangedHandler Method.
     */
    public ReferenceChangedHandler(DistributionConverter distConverter)
    {
        this();
        this.init(distConverter);
    }
    /**
     * Initialize class fields.
     */
    public void init(DistributionConverter distConverter)
    {
        m_distConverter = null;
        super.init(null);
        m_distConverter = distConverter;
        m_bScreenMove = true;   // Only respond to user change
        m_bInitMove = false;
        m_bReadMove = false;
    }
    /**
     * FieldChanged Method.
     */
    public int fieldChanged(boolean bDisplayOption, int iMoveMode)
    {
        m_distConverter.fieldChanged(this.getOwner());
        return super.fieldChanged(bDisplayOption, iMoveMode);
    }

}

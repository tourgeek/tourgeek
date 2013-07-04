/**
  * @(#)RoomConverter.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.tour.booking.db.event;

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
import com.tourapp.tour.product.base.db.*;
import com.tourapp.tour.profile.db.*;
import org.jbundle.base.screen.model.util.*;

/**
 *  RoomConverter - Convert Pax in room to rooms.
 */
public class RoomConverter extends FieldConverter
{
    protected short m_PaxInRoom;
    /**
     * Default constructor.
     */
    public RoomConverter()
    {
        super();
    }
    /**
     * Constructor.
     */
    public RoomConverter(BaseField field, short paxInRoom)
    {
        this();
        this.init(field, paxInRoom);
    }
    /**
     * Initialize class fields.
     */
    public void init(BaseField field, short paxInRoom)
    {
        m_PaxInRoom = 0;
        m_PaxInRoom = paxInRoom;
        super.init(field);
    }
    /**
     * GetString Method.
     */
    public String getString()
    {
        String string = Constants.BLANK;
        if (this.getNextConverter().getLength() != 0)
        {
            double doubleValue = ((NumberField)this.getNextConverter()).getValue();
            if (doubleValue != 0)
                doubleValue = (doubleValue + m_PaxInRoom - 1) / m_PaxInRoom;
            string = Short.toString((short)doubleValue);
        }
        return string;
    }

}

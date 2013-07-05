/**
  * @(#)SetFlightCodeHandler.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.tour.product.air.db;

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
 *  SetFlightCodeHandler - .
 */
public class SetFlightCodeHandler extends FieldListener
{
    /**
     * Default constructor.
     */
    public SetFlightCodeHandler()
    {
        super();
    }
    /**
     * SetFlightCodeHandler Method.
     */
    public SetFlightCodeHandler(BaseField field)
    {
        this();
        this.init(field);
    }
    /**
     * Init Method.
     */
    public void init(BaseField field)
    {
        super.init(field);
        this.setRespondsToMode(DBConstants.INIT_MOVE, false);
        this.setRespondsToMode(DBConstants.READ_MOVE, false);
    }
    /**
     * FieldChanged Method.
     */
    public int fieldChanged(boolean bDisplayOption, int iMoveMode)
    {
        String strProductDesc = DBConstants.BLANK;
        Record recAir = this.getOwner().getRecord();
        Record recAirline = ((ReferenceField)recAir.getField(Air.AIRLINE_ID)).getReference();
        if (recAirline != null)
            strProductDesc += recAirline.getField(Airline.AIRLINE_CODE).toString();
        strProductDesc += recAir.getField(Air.FLIGHT_NO).toString() + ' ';
        recAir.getField(Air.CODE).setString(strProductDesc, bDisplayOption, iMoveMode);
        return super.fieldChanged(bDisplayOption, iMoveMode);
    }

}

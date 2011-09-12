/**
 * @(#)SetFlightCodeHandler.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.product.air.db;

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
        Record recAirline = ((ReferenceField)recAir.getField(Air.kAirlineID)).getReference();
        if (recAirline != null)
            strProductDesc += recAirline.getField(Airline.kAirlineCode).toString();
        strProductDesc += recAir.getField(Air.kFlightNo).toString() + ' ';
        recAir.getField(Air.kCode).setString(strProductDesc, bDisplayOption, iMoveMode);
        return super.fieldChanged(bDisplayOption, iMoveMode);
    }

}

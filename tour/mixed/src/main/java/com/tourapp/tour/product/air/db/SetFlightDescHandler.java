/**
 * @(#)SetFlightDescHandler.
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
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;

/**
 *  SetFlightDescHandler - .
 */
public class SetFlightDescHandler extends FieldListener
{
    /**
     * Default constructor.
     */
    public SetFlightDescHandler()
    {
        super();
    }
    /**
     * SetFlightDescHandler Method.
     */
    public SetFlightDescHandler(BaseField field)
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
        strProductDesc += recAir.getField(Air.kCityCode).toString() + '/' + recAir.getField(Air.kToCityCode).toString() + ' ';
        strProductDesc += recAir.getField(Air.kEtd).toString() + '-' + recAir.getField(Air.kArriveTime).toString();
        if (!recAir.getField(Air.kAddDays).isNull())
            strProductDesc += " " + recAir.getField(Air.kAddDays).toString();
        recAir.getField(Air.kDescription).setString(strProductDesc, bDisplayOption, iMoveMode);
        return super.fieldChanged(bDisplayOption, iMoveMode);
    }

}

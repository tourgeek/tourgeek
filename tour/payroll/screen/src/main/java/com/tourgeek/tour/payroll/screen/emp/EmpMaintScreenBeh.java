
package com.tourgeek.tour.payroll.screen.emp;

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
import org.jbundle.base.screen.model.util.*;

/**
 *  EmpMaintScreenBeh - Employee Maintenance.
 */
public class EmpMaintScreenBeh extends SwitchSubScreenHandler
{
    /**
     * Default constructor.
     */
    public EmpMaintScreenBeh()
    {
        super();
    }
    /**
     * EmpMaintScreenBeh Method.
     */
    public EmpMaintScreenBeh(BaseField field, BasePanel screenParent, BasePanel subScreen)
    {
        this();
        this.init(field, screenParent, subScreen);
    }
    /**
     * Initialize class fields.
     */
    public void init(Record record)
    {
        super.init(record);
    }
    /**
     * GetSubScreen Method.
     */
    public BasePanel getSubScreen(BasePanel parentScreen, ScreenLocation screenLocation, Map<String,Object> properties, int screenNo)
    {
        BaseScreen screen = null;
        switch(screenNo)
        {
        case 1:
            screen = new MEmpAddress(null, screenLocation, parentScreen, null, ScreenConstants.DONT_DISPLAY_DESC, properties);break;
        case 2:
            screen = new MEmpTaxes(null, screenLocation, parentScreen, null, ScreenConstants.DONT_DISPLAY_DESC, properties);break;
        case 3:
            screen = new MEmpSalary(null, screenLocation, parentScreen, null, ScreenConstants.DONT_DISPLAY_DESC, properties);break;
        case 4:
            screen = new MEmpDedEarn(null, screenLocation, parentScreen, null, ScreenConstants.DONT_DISPLAY_DESC, properties);break;
        case 5:
            screen = new MEmpTaxDis(null, screenLocation, parentScreen, null, ScreenConstants.DONT_DISPLAY_DESC, properties);break;
        default:
            screen = null;
        }
        return screen;
    }

}


package com.tourgeek.tour.acctrec.screen.display;

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
import com.tourgeek.tour.profile.db.*;
import com.tourgeek.tour.acctrec.db.*;
import com.tourgeek.tour.booking.db.*;
import com.tourgeek.tour.genled.db.*;
import com.tourgeek.tour.booking.detail.db.*;
import org.jbundle.base.screen.model.util.*;

/**
 *  ArTrxAgentScreenRecord - Fields for Agency display screen.
 */
public class ArTrxAgentScreenRecord extends ScreenRecord
{
    private static final long serialVersionUID = 1L;

    public static final String PROFILE_ID = "ProfileID";
    public static final String BALANCE = "Balance";
    /**
     * Default constructor.
     */
    public ArTrxAgentScreenRecord()
    {
        super();
    }
    /**
     * Constructor.
     */
    public ArTrxAgentScreenRecord(RecordOwner screen)
    {
        this();
        this.init(screen);
    }
    /**
     * Initialize class fields.
     */
    public void init(RecordOwner screen)
    {
        super.init(screen);
    }

    public static final String AR_TRX_AGENT_SCREEN_RECORD_FILE = null;  // Screen field
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        if (iFieldSeq == 0)
            field = new ProfileField(this, PROFILE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 1)
            field = new CurrencyField(this, BALANCE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
            field = super.setupField(iFieldSeq);
        return field;
    }

}

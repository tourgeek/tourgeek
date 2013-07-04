
package com.tourgeek.tour.request.screen.bundle;

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
import com.tourgeek.tour.request.db.*;
import org.jbundle.base.screen.model.util.*;

/**
 *  BundleDetailScreenRecord - .
 */
public class BundleDetailScreenRecord extends ScreenRecord
{
    private static final long serialVersionUID = 1L;

    public static final String BUNDLE_ID = "BundleID";
    /**
     * Default constructor.
     */
    public BundleDetailScreenRecord()
    {
        super();
    }
    /**
     * Constructor.
     */
    public BundleDetailScreenRecord(RecordOwner screen)
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

    public static final String BUNDLE_DETAIL_SCREEN_RECORD_FILE = null;   // Screen field
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        if (iFieldSeq == 0)
            field = new BundleField(this, BUNDLE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
            field = super.setupField(iFieldSeq);
        return field;
    }

}


package com.tourgeek.tour.product.tour.db;

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
import com.tourgeek.tour.product.base.db.*;
import org.jbundle.thin.base.message.*;
import org.jbundle.base.message.core.trx.*;
import com.tourgeek.tour.profile.db.*;
import com.tourgeek.tour.message.base.response.*;
import com.tourgeek.tour.message.tour.response.*;
import org.jbundle.main.msg.db.*;
import com.tourgeek.tour.message.base.request.*;
import com.tourgeek.tour.message.tour.request.data.*;
import com.tourgeek.tour.message.base.response.data.*;
import com.tourgeek.tour.message.base.request.data.*;
import com.tourgeek.tour.product.base.event.*;
import org.jbundle.main.db.base.*;
import org.jbundle.model.message.*;
import com.tourgeek.tour.product.tour.detail.db.*;
import com.tourgeek.model.tour.booking.inventory.db.*;
import com.tourgeek.model.tour.booking.db.*;
import com.tourgeek.model.tour.booking.detail.db.*;
import com.tourgeek.tour.base.db.*;
import com.tourgeek.tour.acctpay.db.*;

/**
 *  TourHeaderField - Tour Header Field.
 */
public class TourHeaderField extends ProductField
{
    /**
     * Default constructor.
     */
    public TourHeaderField()
    {
        super();
    }
    /**
     * Constructor.
     * @param record The parent record.
     * @param strName The field name.
     * @param iDataLength The maximum string length (pass -1 for default).
     * @param strDesc The string description (usually pass null, to use the resource file desc).
     * @param strDefault The default value (if object, this value is the default value, if string, the string is the default).
     */
    public TourHeaderField(Record record, String strName, int iDataLength, String strDesc, Object strDefault)
    {
        this();
        this.init(record, strName, iDataLength, strDesc, strDefault);
    }
    /**
     * Initialize class fields.
     */
    public void init(Record record, String strName, int iDataLength, String strDesc, Object strDefault)
    {
        super.init(record, strName, iDataLength, strDesc, strDefault);
    }
    /**
     * Get (or make) the current record for this reference.
     */
    public Record makeReferenceRecord(RecordOwner recordOwner)
    {
        return new TourHeader(recordOwner);
    }
    /**
     * Set up the default screen control for this field.
     * @param itsLocation Location of this component on screen (ie., GridBagConstraint).
     * @param targetScreen Where to place this component (ie., Parent screen or GridBagLayout).
     * @param converter The converter to set the screenfield to.
     * @param iDisplayFieldDesc Display the label? (optional).
     * @param properties Extra properties
     * @return Return the component or ScreenField that is created for this field.
     */
    public ScreenComponent setupDefaultView(ScreenLoc itsLocation, ComponentParent targetScreen, Convert converter, int iDisplayFieldDesc, Map<String, Object> properties)
    {
        return this.setupTablePopup(itsLocation, targetScreen, iDisplayFieldDesc, this.makeReferenceRecord(), TourHeader.DESCRIPTION, false);
    }

}

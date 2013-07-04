
package com.tourgeek.tour.acctpay.screen.invoice;

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
import com.tourgeek.tour.acctpay.db.*;
import com.tourgeek.tour.base.db.*;
import com.tourgeek.tour.product.tour.db.*;
import com.tourgeek.tour.genled.db.*;
import com.tourgeek.tour.booking.db.*;
import com.tourgeek.tour.acctpay.screen.findepest.*;
import com.tourgeek.tour.acctpay.screen.trx.*;

/**
 *  ValidateInvoiceHandler - Make sure this is a valid Invoice.
 */
public class ValidateInvoiceHandler extends FileListener
{
    /**
     * Default constructor.
     */
    public ValidateInvoiceHandler()
    {
        super();
    }
    /**
     * ValidateInvoiceHandler Method.
     */
    public ValidateInvoiceHandler(Record record)
    {
        this();
        this.init(record);
    }
    /**
     * Initialize class fields.
     */
    public void init(Record record)
    {
        super.init(record);
    }

}

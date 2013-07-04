
package com.tourgeek.tour.assetdr.screen.batch.dist;

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
import com.tourgeek.tour.assetdr.db.*;
import com.tourgeek.tour.genled.db.*;
import org.jbundle.base.screen.model.*;
import org.jbundle.base.screen.model.util.*;

/**
 *  AccountNoDistConverter - .
 */
public class AccountNoDistConverter extends FieldConverter
{
    protected DistributionConverter m_distConverter = null;
    /**
     * Default constructor.
     */
    public AccountNoDistConverter()
    {
        super();
    }
    /**
     * Constructor.
     */
    public AccountNoDistConverter(Converter converter, DistributionConverter distConverter)
    {
        this();
        this.init(converter, distConverter);
    }
    /**
     * Initialize class fields.
     */
    public void init(Converter converter, DistributionConverter distConverter)
    {
        m_distConverter = null;
        m_distConverter = distConverter;
        super.init(converter);
    }
    /**
     * GetString Method.
     */
    public String getString()
    {
        String string = m_distConverter.getAccountNoString();
        if ((string == null) || (string.length() == 0))
            return super.getString();
        return string;
    }

}

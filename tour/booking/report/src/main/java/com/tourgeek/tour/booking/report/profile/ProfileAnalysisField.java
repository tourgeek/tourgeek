
package com.tourgeek.tour.booking.report.profile;

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
 *  ProfileAnalysisField - .
 */
public class ProfileAnalysisField extends StringPopupField
{
    public static final String AFFILIATION = "Affiliation";
    public static final String CITY_TOWN = "City or Town";
    public static final String COUNTRY = "Country";
    public static final String NONE = "--- none ---";
    public static final String PROFILE_CLASS = "Profile Class";
    public static final String PROFILE_STATUS = "Profile status";
    public static final String PROFILE_TYPE = "Profile type";
    public static final String SALES_REGION = "Sales region";
    public static final String SALESPERSON = "Salesperson";
    public static final String STATE_REGION = "State or Region";
    /**
     * Default constructor.
     */
    public ProfileAnalysisField()
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
    public ProfileAnalysisField(Record record, String strName, int iDataLength, String strDesc, Object strDefault)
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
     * Get the default value.
     * @return The default value.
     */
    public Object getDefault()
    {
        Object objDefault = super.getDefault();
        if (objDefault == null)
            objDefault = NONE;
        return objDefault;
    }
    /**
     * Get the conversion Map.
     */
    public String[][] getPopupMap()
    {
        String string[][] = {
            {NONE, NONE}, 
            {PROFILE_STATUS, PROFILE_STATUS}, 
            {PROFILE_CLASS, PROFILE_CLASS},
            {PROFILE_TYPE, PROFILE_TYPE},
            {CITY_TOWN, CITY_TOWN},
            {STATE_REGION, STATE_REGION},
            {COUNTRY, COUNTRY},
            {AFFILIATION, AFFILIATION},
            {SALESPERSON, SALESPERSON},
            {SALES_REGION, SALES_REGION},
        };
        return string;
    }

}

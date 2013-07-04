
package com.tourgeek.thin.tour.profile.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

import com.tourgeek.model.tour.profile.db.*;

public class Affiliation extends FieldList
    implements AffiliationModel
{
    private static final long serialVersionUID = 1L;


    public Affiliation()
    {
        super();
    }
    public Affiliation(Object recordOwner)
    {
        this();
        this.init(recordOwner);
    }
    public static final String AFFILIATION_FILE = "Affiliation";
    /**
     *  Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Affiliation.AFFILIATION_FILE : super.getTableNames(bAddQuotes);
    }
    /**
     *  Get the Database Name.
     */
    public String getDatabaseName()
    {
        return "profile";
    }
    /**
     *  Is this a local (vs remote) file?.
     */
    public int getDatabaseType()
    {
        return Constants.LOCAL;
    }
    /**
    * Set up the screen input fields.
    */
    public void setupFields()
    {
        FieldInfo field = null;
        field = new FieldInfo(this, ID, 9, null, null);
        field.setDataClass(Integer.class);
        field.setHidden(true);
        field = new FieldInfo(this, LAST_CHANGED, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Date.class);
        field.setHidden(true);
        field = new FieldInfo(this, DELETED, 10, null, new Boolean(false));
        field.setDataClass(Boolean.class);
        field.setHidden(true);
        field = new FieldInfo(this, CODE, 4, null, null);
        field = new FieldInfo(this, DESCRIPTION, 30, null, null);
        field = new FieldInfo(this, AGENT_COMM, 5, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, AFFILIATION_COMM, 5, null, null);
        field.setDataClass(Float.class);
        field = new FieldInfo(this, VENDOR_ID, 6, null, null);
        field.setDataClass(Integer.class);
    }
    /**
    * Set up the key areas.
    */
    public void setupKeys()
    {
        KeyAreaInfo keyArea = null;
        keyArea = new KeyAreaInfo(this, Constants.UNIQUE, ID_KEY);
        keyArea.addKeyField(ID, Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.SECONDARY_KEY, CODE_KEY);
        keyArea.addKeyField(CODE, Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, DESCRIPTION_KEY);
        keyArea.addKeyField(DESCRIPTION, Constants.ASCENDING);
    }

}

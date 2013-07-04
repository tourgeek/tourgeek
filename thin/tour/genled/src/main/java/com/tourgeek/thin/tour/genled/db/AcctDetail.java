
package com.tourgeek.thin.tour.genled.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

import com.tourgeek.thin.tour.genled.db.*;
import com.tourgeek.model.tour.genled.db.*;

public class AcctDetail extends BaseTrx
    implements AcctDetailModel
{
    private static final long serialVersionUID = 1L;


    public AcctDetail()
    {
        super();
    }
    public AcctDetail(Object recordOwner)
    {
        this();
        this.init(recordOwner);
    }
    public static final String ACCT_DETAIL_FILE = "AcctDetail";
    /**
     *  Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? AcctDetail.ACCT_DETAIL_FILE : super.getTableNames(bAddQuotes);
    }
    /**
     *  Get the Database Name.
     */
    public String getDatabaseName()
    {
        return "genled";
    }
    /**
     *  Is this a local (vs remote) file?.
     */
    public int getDatabaseType()
    {
        return Constants.REMOTE | Constants.USER_DATA;
    }
    /**
    * Set up the screen input fields.
    */
    public void setupFields()
    {
        FieldInfo field = null;
        field = new FieldInfo(this, ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field.setHidden(true);
        field = new FieldInfo(this, LAST_CHANGED, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Date.class);
        field.setHidden(true);
        field = new FieldInfo(this, DELETED, 10, null, new Boolean(false));
        field.setDataClass(Boolean.class);
        field.setHidden(true);
        field = new FieldInfo(this, TRX_TYPE_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, TRX_USER_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, TRX_DATE, 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, AMOUNT_LOCAL, 18, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, TRX_ENTRY, 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, ACCOUNT_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, SOURCE, 10, null, null);
        field = new FieldInfo(this, COMMENTS, 30, null, null);
        field = new FieldInfo(this, VERSION_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
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
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, ACCOUNT_ID_KEY);
        keyArea.addKeyField(ACCOUNT_ID, Constants.ASCENDING);
        keyArea.addKeyField(TRX_DATE, Constants.ASCENDING);
        keyArea.addKeyField(TRX_TYPE_ID, Constants.ASCENDING);
        keyArea.addKeyField(TRX_ENTRY, Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, TRX_DATE_KEY);
        keyArea.addKeyField(TRX_DATE, Constants.ASCENDING);
        keyArea.addKeyField(TRX_ENTRY, Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, SOURCE_KEY);
        keyArea.addKeyField(SOURCE, Constants.ASCENDING);
        keyArea.addKeyField(TRX_DATE, Constants.ASCENDING);
        keyArea.addKeyField(TRX_ENTRY, Constants.ASCENDING);
    }

}

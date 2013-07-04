
package com.tourgeek.thin.tour.genled.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

import com.tourgeek.model.tour.genled.db.*;

public class AccountBudget extends FieldList
    implements AccountBudgetModel
{
    private static final long serialVersionUID = 1L;


    public AccountBudget()
    {
        super();
    }
    public AccountBudget(Object recordOwner)
    {
        this();
        this.init(recordOwner);
    }
    public static final String ACCOUNT_BUDGET_FILE = "AccountBudget";
    /**
     *  Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? AccountBudget.ACCOUNT_BUDGET_FILE : super.getTableNames(bAddQuotes);
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
        return Constants.LOCAL | Constants.USER_DATA;
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
        field = new FieldInfo(this, ACCOUNT_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, BUD_COM_CODE, Constants.DEFAULT_FIELD_LENGTH, null, "B");
        field = new FieldInfo(this, DETAIL_DATE, 12, null, null);
        field.setDataClass(Date.class);
        field.setScale(Constants.DATE_ONLY);
        field = new FieldInfo(this, AMOUNT, 18, null, null);
        field.setDataClass(Double.class);
    }
    /**
    * Set up the key areas.
    */
    public void setupKeys()
    {
        KeyAreaInfo keyArea = null;
        keyArea = new KeyAreaInfo(this, Constants.UNIQUE, ID_KEY);
        keyArea.addKeyField(ID, Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, BUD_COM_CODE_KEY);
        keyArea.addKeyField(BUD_COM_CODE, Constants.ASCENDING);
        keyArea.addKeyField(ACCOUNT_ID, Constants.ASCENDING);
        keyArea.addKeyField(DETAIL_DATE, Constants.ASCENDING);
    }

}

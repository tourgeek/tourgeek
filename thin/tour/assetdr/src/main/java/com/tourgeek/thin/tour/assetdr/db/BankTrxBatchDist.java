
package com.tourgeek.thin.tour.assetdr.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

import com.tourgeek.model.tour.assetdr.db.*;

public class BankTrxBatchDist extends FieldList
    implements BankTrxBatchDistModel
{
    private static final long serialVersionUID = 1L;


    public BankTrxBatchDist()
    {
        super();
    }
    public BankTrxBatchDist(Object recordOwner)
    {
        this();
        this.init(recordOwner);
    }
    public static final String BANK_TRX_BATCH_DIST_FILE = "BankTrxBatchDist";
    /**
     *  Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? BankTrxBatchDist.BANK_TRX_BATCH_DIST_FILE : super.getTableNames(bAddQuotes);
    }
    /**
     *  Get the Database Name.
     */
    public String getDatabaseName()
    {
        return "assetdr";
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
        field = new FieldInfo(this, BANK_TRX_BATCH_DETAIL_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, ACCOUNT_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
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
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, BANK_TRX_BATCH_DETAIL_ID_KEY);
        keyArea.addKeyField(BANK_TRX_BATCH_DETAIL_ID, Constants.ASCENDING);
        keyArea.addKeyField(ACCOUNT_ID, Constants.ASCENDING);
    }

}

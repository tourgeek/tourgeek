/**
  * @(#)FinStmtDetail.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.thin.tour.genled.db.finstmt;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

import com.tourapp.model.tour.genled.db.finstmt.*;

public class FinStmtDetail extends FieldList
    implements FinStmtDetailModel
{
    private static final long serialVersionUID = 1L;


    public FinStmtDetail()
    {
        super();
    }
    public FinStmtDetail(Object recordOwner)
    {
        this();
        this.init(recordOwner);
    }
    public static final String FIN_STMT_DETAIL_FILE = "FinStmtDetail";
    /**
     *  Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? FinStmtDetail.FIN_STMT_DETAIL_FILE : super.getTableNames(bAddQuotes);
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
        field = new FieldInfo(this, FIN_STMT_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, SEQUENCE, 5, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, ACCOUNT_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, ACCOUNT_DESC, 60, null, null);
        field = new FieldInfo(this, INDENT, 1, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, INVISIBLE, 10, null, null);
        field.setDataClass(Boolean.class);
        field = new FieldInfo(this, TYPICAL_BALANCE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field = new FieldInfo(this, SUB_TOTAL_LEVEL, 1, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, DATA_COLUMN, 5, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, SPECIAL_FORMAT, 128, null, null);
        field = new FieldInfo(this, NUMBER_FORMAT, 128, null, null);
        field = new FieldInfo(this, SPECIAL_FUNCTION, 128, null, null);
    }
    /**
    * Set up the key areas.
    */
    public void setupKeys()
    {
        KeyAreaInfo keyArea = null;
        keyArea = new KeyAreaInfo(this, Constants.UNIQUE, ID_KEY);
        keyArea.addKeyField(ID, Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, FIN_STMT_ID_KEY);
        keyArea.addKeyField(FIN_STMT_ID, Constants.ASCENDING);
        keyArea.addKeyField(SEQUENCE, Constants.ASCENDING);
        keyArea.addKeyField(ACCOUNT_ID, Constants.ASCENDING);
    }

}

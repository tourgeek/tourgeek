/**
  * @(#)FinStmt.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.thin.tour.genled.db.finstmt;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

import com.tourgeek.model.tour.genled.db.finstmt.*;

public class FinStmt extends FieldList
    implements FinStmtModel
{
    private static final long serialVersionUID = 1L;


    public FinStmt()
    {
        super();
    }
    public FinStmt(Object recordOwner)
    {
        this();
        this.init(recordOwner);
    }
    public static final String FIN_STMT_FILE = "FinStmt";
    /**
     *  Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? FinStmt.FIN_STMT_FILE : super.getTableNames(bAddQuotes);
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
        field = new FieldInfo(this, STATEMENT_DESC, 60, null, null);
        field = new FieldInfo(this, FIN_STMT_HEADER_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, STMT_SEQUENCE, 5, null, null);
        field.setDataClass(Short.class);
        field = new FieldInfo(this, STATEMENT_TYPE, 1, null, null);
        field = new FieldInfo(this, STATEMENT_FORMAT, 1, null, null);
        field = new FieldInfo(this, STATEMENT_NUMBER, 1, null, null);
    }
    /**
    * Set up the key areas.
    */
    public void setupKeys()
    {
        KeyAreaInfo keyArea = null;
        keyArea = new KeyAreaInfo(this, Constants.UNIQUE, ID_KEY);
        keyArea.addKeyField(ID, Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, FIN_STMT_HEADER_ID_KEY);
        keyArea.addKeyField(FIN_STMT_HEADER_ID, Constants.ASCENDING);
        keyArea.addKeyField(STMT_SEQUENCE, Constants.ASCENDING);
    }

}

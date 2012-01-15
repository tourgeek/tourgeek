/**
 * @(#)FinStmt.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.genled.db;

import java.awt.*;
import java.util.*;

import org.jbundle.base.db.*;
import org.jbundle.thin.base.util.*;
import org.jbundle.thin.base.db.*;
import org.jbundle.base.db.event.*;
import org.jbundle.base.db.filter.*;
import org.jbundle.base.field.*;
import org.jbundle.base.field.convert.*;
import org.jbundle.base.field.event.*;
import org.jbundle.base.screen.model.*;
import org.jbundle.base.screen.model.util.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import com.tourapp.tour.genled.finstmt.screen.*;
import com.tourapp.tour.genled.finstmt.*;
import com.tourapp.model.tour.genled.db.*;

/**
 *  FinStmt - Financial Statement.
 */
public class FinStmt extends VirtualRecord
     implements FinStmtModel
{
    private static final long serialVersionUID = 1L;

    //public static final int kID = kID;
    public static final int kStatementDesc = kVirtualRecordLastField + 1;
    public static final int kFinStmtHeaderID = kStatementDesc + 1;
    public static final int kStmtSequence = kFinStmtHeaderID + 1;
    public static final int kStatementType = kStmtSequence + 1;
    public static final int kStatementFormat = kStatementType + 1;
    public static final int kStatementNumber = kStatementFormat + 1;
    public static final int kFinStmtLastField = kStatementNumber;
    public static final int kFinStmtFields = kStatementNumber - DBConstants.MAIN_FIELD + 1;

    public static final int kIDKey = DBConstants.MAIN_KEY_FIELD;
    public static final int kFinStmtHeaderIDKey = kIDKey + 1;
    public static final int kFinStmtLastKey = kFinStmtHeaderIDKey;
    public static final int kFinStmtKeys = kFinStmtHeaderIDKey - DBConstants.MAIN_KEY_FIELD + 1;
    public static final int FIN_STMT_DETAIL_GRID_SCREEN = ScreenConstants.DETAIL_MODE;
    /**
     * Default constructor.
     */
    public FinStmt()
    {
        super();
    }
    /**
     * Constructor.
     */
    public FinStmt(RecordOwner screen)
    {
        this();
        this.init(screen);
    }
    /**
     * Initialize class fields.
     */
    public void init(RecordOwner screen)
    {
        super.init(screen);
    }

    public static final String kFinStmtFile = "FinStmt";
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(kFinStmtFile, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "Statement";
    }
    /**
     * Get the Database Name.
     */
    public String getDatabaseName()
    {
        return "genled";
    }
    /**
     * Is this a local (vs remote) file?.
     */
    public int getDatabaseType()
    {
        return DBConstants.LOCAL | DBConstants.USER_DATA;
    }
    /**
     * MakeScreen Method.
     */
    public BaseScreen makeScreen(ScreenLocation itsLocation, BasePanel parentScreen, int iDocMode, Map<String,Object> properties)
    {
        BaseScreen screen = null;
        if ((iDocMode & ScreenConstants.DOC_MODE_MASK) == FinStmt.FIN_STMT_DETAIL_GRID_SCREEN)
            screen = new FinStmtDetailGridScreen(this, null, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if ((iDocMode & ScreenConstants.MAINT_MODE) == ScreenConstants.MAINT_MODE)
            screen = new FinStmtScreen(this, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else
            screen = new FinStmtGridScreen(this, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        return screen;
    }
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        //if (iFieldSeq == kID)
        //{
        //  field = new CounterField(this, "ID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //  field.setHidden(true);
        //}
        if (iFieldSeq == kStatementDesc)
            field = new StringField(this, "StatementDesc", 60, null, null);
        if (iFieldSeq == kFinStmtHeaderID)
        {
            field = new ReferenceField(this, "FinStmtHeaderID", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setNullable(false);
        }
        if (iFieldSeq == kStmtSequence)
            field = new ShortField(this, "StmtSequence", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kStatementType)
            field = new StatementTypeField(this, "StatementType", 1, null, null);
        if (iFieldSeq == kStatementFormat)
            field = new StatementFormatField(this, "StatementFormat", 1, null, null);
        if (iFieldSeq == kStatementNumber)
            field = new StatementNumberField(this, "StatementNumber", 1, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kFinStmtLastField)
                field = new EmptyField(this);
        }
        return field;
    }
    /**
     * Add this key area description to the Record.
     */
    public KeyArea setupKey(int iKeyArea)
    {
        KeyArea keyArea = null;
        if (iKeyArea == kIDKey)
        {
            keyArea = this.makeIndex(DBConstants.UNIQUE, "PrimaryKey");
            keyArea.addKeyField(kID, DBConstants.ASCENDING);
        }
        if (iKeyArea == kFinStmtHeaderIDKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "FinStmtHeaderID");
            keyArea.addKeyField(kFinStmtHeaderID, DBConstants.ASCENDING);
            keyArea.addKeyField(kStmtSequence, DBConstants.ASCENDING);
        }
        if (keyArea == null) if (iKeyArea < kFinStmtLastKey)
        {
            keyArea = super.setupKey(iKeyArea);     
            if (keyArea == null) if (iKeyArea < kFinStmtLastKey)
                keyArea = new EmptyKey(this);
        }
        return keyArea;
    }

}

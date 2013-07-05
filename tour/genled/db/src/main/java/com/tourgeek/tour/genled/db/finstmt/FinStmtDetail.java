/**
  * @(#)FinStmtDetail.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourgeek.tour.genled.db.finstmt;

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
import com.tourgeek.tour.genled.db.*;
import com.tourgeek.model.tour.genled.db.finstmt.*;

/**
 *  FinStmtDetail - Financial Statement Detail.
 */
public class FinStmtDetail extends VirtualRecord
     implements FinStmtDetailModel
{
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor.
     */
    public FinStmtDetail()
    {
        super();
    }
    /**
     * Constructor.
     */
    public FinStmtDetail(RecordOwner screen)
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
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(FIN_STMT_DETAIL_FILE, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "Statement detail";
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
     * Make a default screen.
     */
    public ScreenParent makeScreen(ScreenLoc itsLocation, ComponentParent parentScreen, int iDocMode, Map<String,Object> properties)
    {
        ScreenParent screen = null;
        if ((iDocMode & ScreenConstants.MAINT_MODE) == ScreenConstants.MAINT_MODE)
            screen = Record.makeNewScreen(FIN_STMT_DETAIL_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        else if ((iDocMode & ScreenConstants.DISPLAY_MODE) == ScreenConstants.DISPLAY_MODE)
            screen = Record.makeNewScreen(FIN_STMT_DETAIL_GRID_SCREEN_CLASS, itsLocation, parentScreen, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties, this, true);
        else
            screen = super.makeScreen(itsLocation, parentScreen, iDocMode, properties);
        return screen;
    }
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        //if (iFieldSeq == 0)
        //{
        //  field = new CounterField(this, ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //  field.setHidden(true);
        //}
        //if (iFieldSeq == 1)
        //{
        //  field = new RecordChangedField(this, LAST_CHANGED, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //  field.setHidden(true);
        //}
        //if (iFieldSeq == 2)
        //{
        //  field = new BooleanField(this, DELETED, Constants.DEFAULT_FIELD_LENGTH, null, new Boolean(false));
        //  field.setHidden(true);
        //}
        if (iFieldSeq == 3)
        {
            field = new ReferenceField(this, FIN_STMT_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setNullable(false);
        }
        if (iFieldSeq == 4)
            field = new ShortField(this, SEQUENCE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 5)
            field = new AccountField(this, ACCOUNT_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 6)
            field = new StringField(this, ACCOUNT_DESC, 60, null, null);
        if (iFieldSeq == 7)
            field = new ShortField(this, INDENT, 1, null, null);
        if (iFieldSeq == 8)
            field = new BooleanField(this, INVISIBLE, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 9)
        {
            field = new PreferredBalanceField(this, TYPICAL_BALANCE, Constants.DEFAULT_FIELD_LENGTH, null, "PreferredBalanceField.DEBIT");
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == 10)
            field = new ShortField(this, SUB_TOTAL_LEVEL, 1, null, null);
        if (iFieldSeq == 11)
            field = new ShortField(this, DATA_COLUMN, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == 12)
            field = new SpecialFormatField(this, SPECIAL_FORMAT, 128, null, null);
        if (iFieldSeq == 13)
            field = new NumberFormatField(this, NUMBER_FORMAT, 128, null, null);
        if (iFieldSeq == 14)
            field = new SpecialFunctionField(this, SPECIAL_FUNCTION, 128, null, null);
        if (field == null)
            field = super.setupField(iFieldSeq);
        return field;
    }
    /**
     * Add this key area description to the Record.
     */
    public KeyArea setupKey(int iKeyArea)
    {
        KeyArea keyArea = null;
        if (iKeyArea == 0)
        {
            keyArea = this.makeIndex(DBConstants.UNIQUE, ID_KEY);
            keyArea.addKeyField(ID, DBConstants.ASCENDING);
        }
        if (iKeyArea == 1)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, FIN_STMT_ID_KEY);
            keyArea.addKeyField(FIN_STMT_ID, DBConstants.ASCENDING);
            keyArea.addKeyField(SEQUENCE, DBConstants.ASCENDING);
            keyArea.addKeyField(ACCOUNT_ID, DBConstants.ASCENDING);
        }
        if (keyArea == null)
            keyArea = super.setupKey(iKeyArea);     
        return keyArea;
    }
    /**
     * Renumber this statement by tens.
     */
    public void renumber(Record recFinStmt)
    {
        int iNextSeq = 10;
        HashSet hsBookmarks = new HashSet();
        this.close();
        FileListener subFilter = new SubFileFilter(recFinStmt);
        this.addListener(subFilter);
        try {
            while (this.hasNext())
            {
                this.next();
                Object bookmark = this.getHandle(DBConstants.BOOKMARK_HANDLE);
                if (hsBookmarks.contains(bookmark))
                    continue;       // Already renumbered this one.
                hsBookmarks.add(bookmark);
                this.edit();
                this.getField(FinStmtDetail.SEQUENCE).setValue(iNextSeq);
                iNextSeq = iNextSeq + 10;
                this.set();
            }
        } catch (DBException ex)    {
            ex.printStackTrace();
        } finally   {
            this.removeListener(subFilter, true);
        }
    }
    /**
     * Make sure all the Chart of Accounts are contained in these statements.
     */
    public void validate(Record recFinStmtHeader)
    {
        HashSet hsBookmarks = new HashSet();
        
        FinStmt recFinStmt = new FinStmt(this.findRecordOwner());
        recFinStmt.addListener(new SubFileFilter(recFinStmtHeader));
        
        FileListener subFilter = new SubFileFilter(recFinStmt);
        this.addListener(subFilter);
        
        Account recAccount = new Account(this.findRecordOwner());
        recAccount.setKeyArea(Account.ACCOUNT_NO_KEY);
        
        Object bookmarkIS = null;       // Default income statemet statement
        int iMaxDefaultSequence = 0;
        Object bookmarkBS = null;       // Default balance sheet statement
        int iMaxBSSequence = 0;
        Object bookmarkDefault = null;  // Default statement
        int iMaxISSequence = 0;
        
        try {
            // First, go through all the statements and get the accounts in use
            while (recFinStmt.hasNext())
            {
                recFinStmt.next();
                if (bookmarkDefault == null)
                    bookmarkDefault = recFinStmt.getHandle(DBConstants.BOOKMARK_HANDLE);
                if (bookmarkIS == null)
                    if (StatementTypeField.INCOME_STATEMENT.equalsIgnoreCase(recFinStmt.getField(FinStmt.STATEMENT_TYPE).toString()))
                        bookmarkIS = recFinStmt.getHandle(DBConstants.BOOKMARK_HANDLE);
                if (bookmarkBS == null)
                    if (StatementTypeField.BALANCE_SHEET.equalsIgnoreCase(recFinStmt.getField(FinStmt.STATEMENT_TYPE).toString()))
                        bookmarkBS = recFinStmt.getHandle(DBConstants.BOOKMARK_HANDLE);
                int iMaxSequence = 0;
                this.close();
                while (this.hasNext())
                {
                    this.next();
                    if ((this.getField(FinStmtDetail.ACCOUNT_ID).isNull())
                        || (this.getField(FinStmtDetail.ACCOUNT_ID).getValue() == 0))
                            continue;   // No account, skip it.
                    Record recAccountSecond = ((ReferenceField)this.getField(FinStmtDetail.ACCOUNT_ID)).getReference();
                    Object bookmark = null;
                    if (recAccountSecond != null)
                        bookmark = recAccountSecond.getHandle(DBConstants.BOOKMARK_HANDLE);
                    if ((recAccountSecond == null)
                        || (hsBookmarks.contains(bookmark)))
                    {       // This account doesn't exist or is a duplicate entry, delete the entry
                        this.edit();
                        this.remove();
                        continue;
                    }
                    hsBookmarks.add(bookmark);      // This account is included in a statement
                    iMaxSequence = (int)this.getField(FinStmtDetail.SEQUENCE).getValue();
                }
                if (bookmarkDefault == recFinStmt.getHandle(DBConstants.BOOKMARK_HANDLE))
                    iMaxDefaultSequence = iMaxSequence;
                if (bookmarkIS == recFinStmt.getHandle(DBConstants.BOOKMARK_HANDLE))
                    iMaxISSequence = iMaxSequence;
                if (bookmarkBS == recFinStmt.getHandle(DBConstants.BOOKMARK_HANDLE))
                    iMaxBSSequence = iMaxSequence;
            }
        
            if (bookmarkDefault == null)
                return;     // Must have at least one statment
            if (bookmarkIS == null)
            {
                bookmarkIS = bookmarkDefault;
                iMaxISSequence = iMaxDefaultSequence;
            }
            if (bookmarkBS == null)
            {
                bookmarkBS = bookmarkDefault;
                iMaxBSSequence = iMaxDefaultSequence;
            }
        
            this.removeListener(subFilter, true);   // If I don't do this, The FIN_STMT_ID field will be auto-set.
            subFilter = null;
            // Now run through the accounts and make sure they are all in the statements... if not, add them.
            while (recAccount.hasNext())
            {
                recAccount.next();
        
                Object bookmark = recAccount.getHandle(DBConstants.BOOKMARK_HANDLE);
                if (hsBookmarks.contains(bookmark))
                    continue;   // Good, already in a statement
                // Add this to the appropriate statement
                this.addNew();
        
                if (recAccount.getField(Account.CLOSE_YEAR_END).getState() == true)
                {
                    this.getField(FinStmtDetail.FIN_STMT_ID).setData(bookmarkIS);
                    iMaxISSequence = iMaxISSequence + 10;
                    this.getField(FinStmtDetail.SEQUENCE).setValue(iMaxISSequence);
                }
                else
                {
                    this.getField(FinStmtDetail.FIN_STMT_ID).setData(bookmarkBS);
                    iMaxBSSequence = iMaxBSSequence + 10;
                    this.getField(FinStmtDetail.SEQUENCE).setValue(iMaxBSSequence);
                }
                if (bookmarkIS == bookmarkBS)
                    iMaxISSequence = iMaxBSSequence = Math.max(iMaxISSequence, iMaxBSSequence);
                this.getField(FinStmtDetail.ACCOUNT_ID).moveFieldToThis(recAccount.getField(Account.ID));
                this.getField(FinStmtDetail.TYPICAL_BALANCE).moveFieldToThis(recAccount.getField(Account.TYPICAL_BALANCE));
        
                this.add();
            }
        
        } catch (DBException ex)    {
            ex.printStackTrace();
        } finally   {
            if (subFilter != null)
                this.removeListener(subFilter, true);
            recFinStmt.free();  // This will also remove the listener.
            recAccount.free();
        }
    }

}

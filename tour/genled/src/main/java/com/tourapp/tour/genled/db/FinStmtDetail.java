/**
 * @(#)FinStmtDetail.
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

/**
 *  FinStmtDetail - Financial Statement Detail.
 */
public class FinStmtDetail extends VirtualRecord
{
    private static final long serialVersionUID = 1L;

    //public static final int kID = kID;
    public static final int kFinStmtID = kVirtualRecordLastField + 1;
    public static final int kSequence = kFinStmtID + 1;
    public static final int kAccountID = kSequence + 1;
    public static final int kAccountDesc = kAccountID + 1;
    public static final int kIndent = kAccountDesc + 1;
    public static final int kInvisible = kIndent + 1;
    public static final int kTypicalBalance = kInvisible + 1;
    public static final int kSubTotalLevel = kTypicalBalance + 1;
    public static final int kDataColumn = kSubTotalLevel + 1;
    public static final int kSpecialFormat = kDataColumn + 1;
    public static final int kNumberFormat = kSpecialFormat + 1;
    public static final int kSpecialFunction = kNumberFormat + 1;
    public static final int kFinStmtDetailLastField = kSpecialFunction;
    public static final int kFinStmtDetailFields = kSpecialFunction - DBConstants.MAIN_FIELD + 1;

    public static final int kIDKey = DBConstants.MAIN_KEY_FIELD;
    public static final int kFinStmtIDKey = kIDKey + 1;
    public static final int kFinStmtDetailLastKey = kFinStmtIDKey;
    public static final int kFinStmtDetailKeys = kFinStmtIDKey - DBConstants.MAIN_KEY_FIELD + 1;
    public static final String RENUMBER = "Renumber";
    public static final String VALIDATE = "Validate";
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

    public static final String kFinStmtDetailFile = "FinStmtDetail";
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(kFinStmtDetailFile, bAddQuotes) : super.getTableNames(bAddQuotes);
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
    public BaseScreen makeScreen(ScreenLocation itsLocation, BasePanel parentScreen, int iDocMode, Map<String,Object> properties)
    {
        BaseScreen screen = null;
        if ((iDocMode & ScreenConstants.MAINT_MODE) != 0)
            screen = new FinStmtDetailScreen(this, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
        else if ((iDocMode & ScreenConstants.DISPLAY_MODE) != 0)
            screen = new FinStmtDetailGridScreen(this, itsLocation, parentScreen, null, iDocMode | ScreenConstants.DONT_DISPLAY_FIELD_DESC, properties);
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
        //if (iFieldSeq == kID)
        //{
        //  field = new CounterField(this, "ID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        //  field.setHidden(true);
        //}
        if (iFieldSeq == kFinStmtID)
        {
            field = new ReferenceField(this, "FinStmtID", Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setNullable(false);
        }
        if (iFieldSeq == kSequence)
            field = new ShortField(this, "Sequence", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kAccountID)
            field = new AccountField(this, "AccountID", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kAccountDesc)
            field = new StringField(this, "AccountDesc", 60, null, null);
        if (iFieldSeq == kIndent)
            field = new ShortField(this, "Indent", 1, null, null);
        if (iFieldSeq == kInvisible)
            field = new BooleanField(this, "Invisible", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kTypicalBalance)
        {
            field = new PreferredBalanceField(this, "TypicalBalance", Constants.DEFAULT_FIELD_LENGTH, null, "PreferredBalanceField.DEBIT");
            field.addListener(new InitOnceFieldHandler(null));
        }
        if (iFieldSeq == kSubTotalLevel)
            field = new ShortField(this, "SubTotalLevel", 1, null, null);
        if (iFieldSeq == kDataColumn)
            field = new ShortField(this, "DataColumn", Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (iFieldSeq == kSpecialFormat)
            field = new SpecialFormatField(this, "SpecialFormat", 128, null, null);
        if (iFieldSeq == kNumberFormat)
            field = new NumberFormatField(this, "NumberFormat", 128, null, null);
        if (iFieldSeq == kSpecialFunction)
            field = new SpecialFunctionField(this, "SpecialFunction", 128, null, null);
        if (field == null)
        {
            field = super.setupField(iFieldSeq);
            if (field == null) if (iFieldSeq < kFinStmtDetailLastField)
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
        if (iKeyArea == kFinStmtIDKey)
        {
            keyArea = this.makeIndex(DBConstants.NOT_UNIQUE, "FinStmtID");
            keyArea.addKeyField(kFinStmtID, DBConstants.ASCENDING);
            keyArea.addKeyField(kSequence, DBConstants.ASCENDING);
            keyArea.addKeyField(kAccountID, DBConstants.ASCENDING);
        }
        if (keyArea == null) if (iKeyArea < kFinStmtDetailLastKey)
        {
            keyArea = super.setupKey(iKeyArea);     
            if (keyArea == null) if (iKeyArea < kFinStmtDetailLastKey)
                keyArea = new EmptyKey(this);
        }
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
                this.getField(FinStmtDetail.kSequence).setValue(iNextSeq);
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
        
        FinStmt recFinStmt = new FinStmt(Utility.getRecordOwner(this));
        recFinStmt.addListener(new SubFileFilter(recFinStmtHeader));
        
        FileListener subFilter = new SubFileFilter(recFinStmt);
        this.addListener(subFilter);
        
        Account recAccount = new Account(Utility.getRecordOwner(this));
        recAccount.setKeyArea(Account.kAccountNoKey);
        
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
                    if (StatementTypeField.INCOME_STATEMENT.equalsIgnoreCase(recFinStmt.getField(FinStmt.kStatementType).toString()))
                        bookmarkIS = recFinStmt.getHandle(DBConstants.BOOKMARK_HANDLE);
                if (bookmarkBS == null)
                    if (StatementTypeField.BALANCE_SHEET.equalsIgnoreCase(recFinStmt.getField(FinStmt.kStatementType).toString()))
                        bookmarkBS = recFinStmt.getHandle(DBConstants.BOOKMARK_HANDLE);
                int iMaxSequence = 0;
                this.close();
                while (this.hasNext())
                {
                    this.next();
                    if ((this.getField(FinStmtDetail.kAccountID).isNull())
                        || (this.getField(FinStmtDetail.kAccountID).getValue() == 0))
                            continue;   // No account, skip it.
                    Record recAccountSecond = ((ReferenceField)this.getField(FinStmtDetail.kAccountID)).getReference();
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
                    iMaxSequence = (int)this.getField(FinStmtDetail.kSequence).getValue();
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
        
            this.removeListener(subFilter, true);   // If I don't do this, The kFinStmtID field will be auto-set.
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
        
                if (recAccount.getField(Account.kCloseYearEnd).getState() == true)
                {
                    this.getField(FinStmtDetail.kFinStmtID).setData(bookmarkIS);
                    iMaxISSequence = iMaxISSequence + 10;
                    this.getField(FinStmtDetail.kSequence).setValue(iMaxISSequence);
                }
                else
                {
                    this.getField(FinStmtDetail.kFinStmtID).setData(bookmarkBS);
                    iMaxBSSequence = iMaxBSSequence + 10;
                    this.getField(FinStmtDetail.kSequence).setValue(iMaxBSSequence);
                }
                if (bookmarkIS == bookmarkBS)
                    iMaxISSequence = iMaxBSSequence = Math.max(iMaxISSequence, iMaxBSSequence);
                this.getField(FinStmtDetail.kAccountID).moveFieldToThis(recAccount.getField(Account.kID));
                this.getField(FinStmtDetail.kTypicalBalance).moveFieldToThis(recAccount.getField(Account.kTypicalBalance));
        
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

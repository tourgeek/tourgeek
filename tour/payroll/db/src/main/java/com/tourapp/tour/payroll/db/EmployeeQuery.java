/**
  * @(#)EmployeeQuery.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.tour.payroll.db;

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
import org.jbundle.main.db.*;
import com.tourapp.model.tour.payroll.db.*;

/**
 *  EmployeeQuery - Employee Manual Query.
 */
public class EmployeeQuery extends QueryRecord
     implements EmployeeQueryModel
{
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor.
     */
    public EmployeeQuery()
    {
        super();
    }
    /**
     * Constructor.
     */
    public EmployeeQuery(RecordOwner screen)
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
        return (m_tableName == null) ? Record.formatTableNames(EMPLOYEE_QUERY_FILE, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the Database Name.
     */
    public String getDatabaseName()
    {
        return "payroll";
    }
    /**
     * Is this a local (vs remote) file?.
     */
    public int getDatabaseType()
    {
        return DBConstants.LOCAL;
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
        if (field == null)
            field = super.setupField(iFieldSeq);
        return field;
    }
    /**
     * Override this to Setup all the records for this query.
     * Only used for querys and abstract-record queries.
     * Actually adds records not tables, but the records aren't physically
     * added here, the record's tables are added to my table.
     * @param The recordOwner to pass to the records that are added.
     * @see addTable.
     */
    public void addTables(RecordOwner recordOwner)
    {
        Employee employee = new Employee(recordOwner);
        this.addTable(employee);
        Deduction prDedEarn = new Deduction(recordOwner);
        this.addTable(prDedEarn);
        //?employee.setGridFile(employee, DBConstants.MAIN_KEY_AREA);
    }
    /**
     * SelectFields Method.
     */
    public void selectFields()
    {
        this.setSelected(false);
        super.selectFields();
        this.getField(Employee.ID).setSelected(true);
        this.getField(Employee.NAME_SORT).setSelected(true);
        this.getField(Employee.NAME).setSelected(true);
        this.getField(Employee.HIRE_DATE).setSelected(true);
        this.getField(Employee.DATE_CHANGED).setSelected(true);
        this.getField(Employee.POSTAL_CODE_SORT).setSelected(true);
        this.getField(Employee.FIRST_NAME).setSelected(true);
        this.getField(Employee.DEDUCTION_ID1).setSelected(true);
        this.getField(Deduction.DEDUCTION_FILE, Deduction.DESCRIPTION).setSelected(true);
    }
    /**
     * SetupRelationships Method.
     */
    public void setupRelationships()
    {
        this.addRelationship(DBConstants.LEFT_OUTER, this.getRecord(Employee.EMPLOYEE_FILE), this.getRecord(Deduction.DEDUCTION_FILE), Employee.DEDUCTION_ID1, Deduction.ID);
    }

}

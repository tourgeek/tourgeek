/**
 * @(#)EmployeeQuery.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.payroll.db;

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
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import org.jbundle.main.db.*;
import com.tourapp.tour.payroll.screen.emp.*;
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

    public static final String kEmployeeQueryFile = "EmployeeQuery";
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(kEmployeeQueryFile, bAddQuotes) : super.getTableNames(bAddQuotes);
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
        this.getField(Employee.kID).setSelected(true);
        this.getField(Employee.kNameSort).setSelected(true);
        this.getField(Employee.kName).setSelected(true);
        this.getField(Employee.kHireDate).setSelected(true);
        this.getField(Employee.kDateChanged).setSelected(true);
        this.getField(Employee.kPostalCodeSort).setSelected(true);
        this.getField(Employee.kFirstName).setSelected(true);
        this.getField(Employee.kDeductionID1).setSelected(true);
        this.getField(Deduction.kDeductionFile, Deduction.kDescription).setSelected(true);
    }
    /**
     * SetupRelationships Method.
     */
    public void setupRelationships()
    {
        this.addRelationship(DBConstants.LEFT_OUTER, this.getRecord(Employee.kEmployeeFile), this.getRecord(Deduction.kDeductionFile), Employee.kDeductionID1, Deduction.kID);
    }

}

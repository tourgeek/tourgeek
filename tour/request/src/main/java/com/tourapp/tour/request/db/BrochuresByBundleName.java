/**
 * @(#)BrochuresByBundleName.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.request.db;

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
import com.tourapp.tour.request.screen.bundle.*;
import com.tourapp.tour.base.db.shared.*;
import com.tourapp.model.tour.request.db.*;

/**
 *  BrochuresByBundleName - Brochures by Bundle.
 */
public class BrochuresByBundleName extends QueryRecord
     implements BrochuresByBundleNameModel
{
    private static final long serialVersionUID = 1L;
    /**
     * Default constructor.
     */
    public BrochuresByBundleName()
    {
        super();
    }
    /**
     * Constructor.
     */
    public BrochuresByBundleName(RecordOwner screen)
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

    public static final String kBrochuresByBundleNameFile = "BrochuresByBundleName";
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(kBrochuresByBundleNameFile, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "Brochure";
    }
    /**
     * Get the Database Name.
     */
    public String getDatabaseName()
    {
        return "request";
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
        this.addTable(new Bundle(recordOwner));
        this.addTable(new BundleDetail(recordOwner));
        this.addTable(new Brochure(recordOwner));
    }
    /**
     * SelectFields Method.
     */
    public void selectFields()
    {
        BundleDetail pAmBrocDetail = (BundleDetail)this.getRecord(BundleDetail.kBundleDetailFile);
        Bundle pAmBrocBundle = (Bundle)this.getRecord(Bundle.kBundleFile);
        this.setGridFile(pAmBrocBundle, Bundle.kDescriptionKey);
        
        this.setSelected(false);    // de-select all
        super.selectFields();
        this.getField(Bundle.kBundleFile, Bundle.kID).setSelected(true);
        this.getField(Bundle.kBundleFile, Bundle.kDescription).setSelected(true);
        this.getField(Brochure.kBrochureFile, Brochure.kDescription).setSelected(true);
    }
    /**
     * SetupRelationships Method.
     */
    public void setupRelationships()
    {
        this.addRelationship(DBConstants.LEFT_INNER, this.getRecord(Bundle.kBundleFile), this.getRecord(BundleDetail.kBundleDetailFile), Bundle.kID, BundleDetail.kBundleID);
        this.addRelationship(DBConstants.LEFT_INNER, this.getRecord(BundleDetail.kBundleDetailFile), this.getRecord(Brochure.kBrochureFile), BundleDetail.kBrochureID, Brochure.kID);
    }

}

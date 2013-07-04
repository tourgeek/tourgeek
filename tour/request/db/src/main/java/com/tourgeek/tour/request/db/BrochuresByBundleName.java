
package com.tourgeek.tour.request.db;

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
import com.tourgeek.tour.base.db.shared.*;
import com.tourgeek.model.tour.request.db.*;

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
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(BROCHURES_BY_BUNDLE_NAME_FILE, bAddQuotes) : super.getTableNames(bAddQuotes);
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
        this.addTable(new Bundle(recordOwner));
        this.addTable(new BundleDetail(recordOwner));
        this.addTable(new Brochure(recordOwner));
    }
    /**
     * SelectFields Method.
     */
    public void selectFields()
    {
        BundleDetail pAmBrocDetail = (BundleDetail)this.getRecord(BundleDetail.BUNDLE_DETAIL_FILE);
        Bundle pAmBrocBundle = (Bundle)this.getRecord(Bundle.BUNDLE_FILE);
        this.setGridFile(pAmBrocBundle, Bundle.DESCRIPTION_KEY);
        
        this.setSelected(false);    // de-select all
        super.selectFields();
        this.getField(Bundle.BUNDLE_FILE, Bundle.ID).setSelected(true);
        this.getField(Bundle.BUNDLE_FILE, Bundle.DESCRIPTION).setSelected(true);
        this.getField(Brochure.BROCHURE_FILE, Brochure.DESCRIPTION).setSelected(true);
    }
    /**
     * SetupRelationships Method.
     */
    public void setupRelationships()
    {
        this.addRelationship(DBConstants.LEFT_INNER, this.getRecord(Bundle.BUNDLE_FILE), this.getRecord(BundleDetail.BUNDLE_DETAIL_FILE), Bundle.ID, BundleDetail.BUNDLE_ID);
        this.addRelationship(DBConstants.LEFT_INNER, this.getRecord(BundleDetail.BUNDLE_DETAIL_FILE), this.getRecord(Brochure.BROCHURE_FILE), BundleDetail.BROCHURE_ID, Brochure.ID);
    }

}

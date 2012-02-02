/**
 * @(#)MergeCodeTableSearch.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.util.script.merge;

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
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import org.jbundle.thin.base.screen.*;
import com.tourapp.tour.product.base.search.db.*;
import com.tourapp.tour.product.base.ota.db.*;

/**
 *  MergeCodeTableSearch - Merge the code table with the product search category.
 */
public class MergeCodeTableSearch extends MergeCodeTable
{
    /**
     * Default constructor.
     */
    public MergeCodeTableSearch()
    {
        super();
    }
    /**
     * Constructor.
     */
    public MergeCodeTableSearch(RecordOwnerParent taskParent, Record recordMain, Map<String,Object> properties)
    {
        this();
        this.init(taskParent, recordMain, properties);
    }
    /**
     * Initialize class fields.
     */
    public void init(RecordOwnerParent taskParent, Record recordMain, Map<String, Object> properties)
    {
        super.init(taskParent, recordMain, properties);
    }
    /**
     * Open the main file.
     */
    public Record openMainRecord()
    {
        return new ProductSearchCategory(this);
    }
    /**
     * Given this source record, read the destination record.
     * @param recSource The source record
     * @param recDest The destination record
     * @return True if found.
     */
    public boolean readDestRecord(FieldList recSource, Record recDest)
    {
        try {
            String strCode = this.getProperty("code");
            if (strCode == null)
                return false;   // Never
            Record recOTACodeTable = this.getRecord(OTACodeTable.OTA_CODE_TABLE_FILE);
            recOTACodeTable.setKeyArea(OTACodeTable.NAME_CODE_KEY);
            recOTACodeTable.getField(OTACodeTable.NAME_CODE).setString(strCode);
            if (!recOTACodeTable.seek(DBConstants.EQUALS))
                return false;   // Never
            String strCodeName = recOTACodeTable.getField(OTACodeTable.NAME).toString();
            
            Record recProductSearchType = this.getRecord(ProductSearchType.PRODUCT_SEARCH_TYPE_FILE);
            recProductSearchType.setKeyArea(ProductSearchType.DESCRIPTION_KEY);
            recProductSearchType.getField(ProductSearchType.DESCRIPTION).setString(strCodeName);
            if (!recProductSearchType.seek(DBConstants.EQUALS))
            {
                recProductSearchType.addNew();
                recProductSearchType.getField(ProductSearchType.DESCRIPTION).setString(strCodeName);
                recProductSearchType.writeAndRefresh();
            }
            
            recDest.getField(ProductSearchCategory.DESCRIPTION).moveFieldToThis(recSource.getField(OTACode.NAME));
            recDest.getField(ProductSearchCategory.PRODUCT_SEARCH_TYPE_ID).moveFieldToThis(recProductSearchType.getField(ProductSearchType.ID));
            recDest.setKeyArea(ProductSearchCategory.PRODUCT_SEARCH_TYPE_ID_KEY);
        
            return recDest.seek(DBConstants.EQUALS);
        } catch (DBException e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * Merge this source record with the destination record.
     * @param recSource
     * @param recDest.
     */
    public void mergeSourceData(Record recSource, Record recDest, boolean bFound)
    {
        recDest.getField(ProductSearchCategory.DESCRIPTION).moveFieldToThis(recSource.getField(OTACode.NAME));
        Record recProductSearchType = this.getRecord(ProductSearchType.PRODUCT_SEARCH_TYPE_FILE);
        recDest.getField(ProductSearchCategory.PRODUCT_SEARCH_TYPE_ID).moveFieldToThis(recProductSearchType.getField(ProductSearchType.ID));
        recDest.getField(ProductSearchCategory.VALUE).moveFieldToThis(recSource.getField(OTACode.ID));
    }
    /**
     * Open the other files.
     */
    public void openOtherRecords()
    {
        super.openOtherRecords();
        new OTACodeTable(this);
        new ProductSearchType(this);
    }

}

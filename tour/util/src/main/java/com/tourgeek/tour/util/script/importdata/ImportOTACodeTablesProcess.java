
package com.tourgeek.tour.util.script.importdata;

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
import org.jbundle.base.thread.*;
import com.tourgeek.tour.product.base.ota.db.*;
import org.jibx.schema.org.opentravel._2012B.codetable.*;
import javax.xml.bind.*;
import java.io.*;
import javax.xml.datatype.*;
import org.jibx.schema.org.opentravel._2012B.base.*;
import org.jibx.runtime.*;
import org.joda.time.*;

/**
 *  ImportOTACodeTablesProcess - .
 */
public class ImportOTACodeTablesProcess extends BaseProcess
{
    /**
     * Default constructor.
     */
    public ImportOTACodeTablesProcess()
    {
        super();
    }
    /**
     * Constructor.
     */
    public ImportOTACodeTablesProcess(RecordOwnerParent taskParent, Record recordMain, Map<String,Object> properties)
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
        return new OTACodeTable(this);
    }
    /**
     * Open the other files.
     */
    public void openOtherRecords()
    {
        super.openOtherRecords();
        new OTACodes(this);
    }
    /**
     * Run Method.
     */
    public void run()
    {
        try {
            OTACodeTable recOTACodeTable = (OTACodeTable)this.getMainRecord();
            OTACodes recOTACodes = (OTACodes)this.getRecord(OTACodes.OTA_CODES_FILE);
            
            String strFilePath = this.getProperty("filepath");
            String NONE = "none";
            String VALUE_PARAM = ".value";
            
            IBindingFactory jc = BindingDirectory.getFactory(CodeTables.class);
            IUnmarshallingContext unmarshaller = jc.createUnmarshallingContext();
            InputStream inStream = new FileInputStream( strFilePath );
            CodeTables codeTables = (CodeTables)unmarshaller.unmarshalDocument( inStream, Constants.URL_ENCODING);
            
            if (codeTables.getCodeTableList() != null)
            for (CodeTables.CodeTable table : codeTables.getCodeTableList())
            {
                String strName = table.getName();
                String strNameCode = table.getNameCode();
                LocalDate calCreate = table.getCreationDate();
                LocalDate calDeletion = table.getMarkedForDeletionDate();
                CodeTables.CodeTable.Descriptions descriptions = table.getDescriptions();
                Map<String,Object> properties = null;
                if (descriptions != null)
                    for (FreeText desc : descriptions.getDescriptionList())
                    {
                        String strLanguage = desc.getLanguage();
                        String strValue = desc.getString();
                        if ((strLanguage == null) || (strLanguage.length() == 0))
                            strLanguage = NONE;
                        if (properties == null)
                            properties = new HashMap<String,Object>();
                        properties.put(strLanguage, strValue);
                    }
                
                recOTACodeTable.addNew();
                recOTACodeTable.getField(OTACodeTable.NAME).setString(strName);
                recOTACodeTable.getField(OTACodeTable.NAME_CODE).setString(strNameCode);
                if (calCreate != null)
                    ((DateField)recOTACodeTable.getField(OTACodeTable.CREATION_DATE)).setDate(calCreate.toDateMidnight().toDate(), true, DBConstants.SCREEN_MOVE);
                if (calDeletion != null)
                    ((DateField)recOTACodeTable.getField(OTACodeTable.DELETION_DATE)).setDate(calDeletion.toDateMidnight().toDate(), true, DBConstants.SCREEN_MOVE);
                ((PropertiesField)recOTACodeTable.getField(OTACodeTable.PROPERTIES)).setProperties(properties);
                recOTACodeTable.add();
                Object bookmark = recOTACodeTable.getLastModified(DBConstants.BOOKMARK_HANDLE);
                
                if (table.getCodes() != null)
                    if (table.getCodes().getCodeList() != null)
                for (CodeTables.CodeTable.Codes.Code code : table.getCodes().getCodeList())
                {
                    String strValue2 = code.getValue();
                    LocalDate calCreate2 = code.getCreationDate();
                    LocalDate calDeletion2 = code.getMarkedForDeletionDate();
                    java.util.List<CodeContent> contents = code.getCodeContentList();
                    
                    String strNameDefault = null;
                    properties = null;
                    if (contents != null)
                    for (CodeContent contentType : contents)
                    {
                        String strLanguage = contentType.getLanguage();
                        String strName3 = contentType.getName();
                        String strValue3 = contentType.getString();
                        
                        if (((strLanguage == null) || (strLanguage.length() == 0))
                            || (strNameDefault == null))
                        strNameDefault = strName3;
                        if ((strLanguage == null) || (strLanguage.length() == 0))
                            strLanguage = NONE;
                        if (properties == null)
                            properties = new HashMap<String,Object>();
                        if ((strName3 != null) && (strName3.length() > 0))
                            properties.put(strLanguage, strName3);
                        if ((strValue3 != null) && (strValue3.length() > 0))
                            properties.put(strLanguage + VALUE_PARAM, strValue3);                        
                    }
                
                    recOTACodes.addNew();
                    recOTACodes.getField(OTACodes.OTA_CODE_TABLE_ID).setString(bookmark.toString());
                    recOTACodes.getField(OTACodes.VALUE).setString(strValue2);
                    recOTACodes.getField(OTACodes.NAME).setString(strNameDefault);
                    if (calCreate2 != null)
                        ((DateField)recOTACodes.getField(OTACodes.CREATION_DATE)).setDate(calCreate2.toDateMidnight().toDate(), true, DBConstants.SCREEN_MOVE);
                    if (calDeletion2 != null)
                        ((DateField)recOTACodes.getField(OTACodes.DELETION_DATE)).setDate(calDeletion2.toDateMidnight().toDate(), true, DBConstants.SCREEN_MOVE);
                    ((PropertiesField)recOTACodes.getField(OTACodes.PROPERTIES)).setProperties(properties);
                    recOTACodes.add();
        
                }
            }
            
        } catch (DBException ex) {
            ex.printStackTrace();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (JiBXException e) {
            e.printStackTrace();
        }
    }

}

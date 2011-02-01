/**
 *  @(#)ImportOTACodeTablesProcess.
 *  Copyright © 2010 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.util.script.importdata;

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
import org.jbundle.base.thread.*;
import com.tourapp.tour.product.base.ota.db.*;
import net.sourceforge.ota_tools.jaxb.ota2010a.codetable.*;
import javax.xml.bind.*;
import java.io.*;
import javax.xml.datatype.*;

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
            OTACodes recOTACodes = (OTACodes)this.getRecord(OTACodes.kOTACodesFile);
            
            String strFilePath = this.getProperty("filepath");
            String PACKAGE = OTACodeTables.class.getPackage().getName();
            String NONE = "none";
            String VALUE_PARAM = ".value";
            
            JAXBContext jc = JAXBContext.newInstance( PACKAGE );
            
            Unmarshaller u = jc.createUnmarshaller();
            OTACodeTables codeTables = (OTACodeTables)u.unmarshal(new FileInputStream( strFilePath ) );
            
            if (codeTables.getOTACodeTable() != null)
            for (OTACodeTables.OTACodeTable table : codeTables.getOTACodeTable())
            {
                String strName = table.getName();
                String strNameCode = table.getNameCode();
                XMLGregorianCalendar calCreate = table.getCreationDate();
                XMLGregorianCalendar calDeletion = table.getMarkedForDeletionDate();
                OTACodeTables.OTACodeTable.Descriptions descriptions = table.getDescriptions();
                Properties properties = null;
                if (descriptions != null)
                    for (FreeTextType desc : descriptions.getDescription())
                    {
                        String strLanguage = desc.getLanguage();
                        String strValue = desc.getValue();
                        if ((strLanguage == null) || (strLanguage.length() == 0))
                            strLanguage = NONE;
                        if (properties == null)
                            properties = new Properties();
                        properties.setProperty(strLanguage, strValue);
                    }
                
                recOTACodeTable.addNew();
                recOTACodeTable.getField(OTACodeTable.kName).setString(strName);
                recOTACodeTable.getField(OTACodeTable.kNameCode).setString(strNameCode);
                if (calCreate != null)
                    ((DateField)recOTACodeTable.getField(OTACodeTable.kCreationDate)).setCalendar(calCreate.toGregorianCalendar(), true, DBConstants.SCREEN_MOVE);
                if (calDeletion != null)
                    ((DateField)recOTACodeTable.getField(OTACodeTable.kDeletionDate)).setCalendar(calDeletion.toGregorianCalendar(), true, DBConstants.SCREEN_MOVE);
                ((PropertiesField)recOTACodeTable.getField(OTACodeTable.kProperties)).setProperties(properties);
                recOTACodeTable.add();
                Object bookmark = recOTACodeTable.getLastModified(DBConstants.BOOKMARK_HANDLE);
                
                if (table.getCodes() != null)
                    if (table.getCodes().getCode() != null)
                for (OTACodeTables.OTACodeTable.Codes.Code code : table.getCodes().getCode())
                {
                    String strValue2 = code.getValue();
                    XMLGregorianCalendar calCreate2 = code.getCreationDate();
                    XMLGregorianCalendar calDeletion2 = code.getMarkedForDeletionDate();
                    OTACodeTables.OTACodeTable.Codes.Code.CodeContents contents = code.getCodeContents();
                    
                    String strNameDefault = null;
                    properties = null;
                    if (contents.getCodeContent() != null)
                    for (CodeContentType contentType : contents.getCodeContent())
                    {
                        String strLanguage = contentType.getLanguage();
                        String strName3 = contentType.getName();
                        String strValue3 = contentType.getValue();
                        
                        if (((strLanguage == null) || (strLanguage.length() == 0))
                            || (strNameDefault == null))
                        strNameDefault = strName3;
                        if ((strLanguage == null) || (strLanguage.length() == 0))
                            strLanguage = NONE;
                        if (properties == null)
                            properties = new Properties();
                        if ((strName3 != null) && (strName3.length() > 0))
                            properties.setProperty(strLanguage, strName3);
                        if ((strValue3 != null) && (strValue3.length() > 0))
                            properties.setProperty(strLanguage + VALUE_PARAM, strValue3);                        
                    }
                
                    recOTACodes.addNew();
                    recOTACodes.getField(OTACodes.kOTACodeTableID).setString(bookmark.toString());
                    recOTACodes.getField(OTACodes.kValue).setString(strValue2);
                    recOTACodes.getField(OTACodes.kName).setString(strNameDefault);
                    if (calCreate2 != null)
                        ((DateField)recOTACodes.getField(OTACodes.kCreationDate)).setCalendar(calCreate2.toGregorianCalendar(), true, DBConstants.SCREEN_MOVE);
                    if (calDeletion2 != null)
                        ((DateField)recOTACodes.getField(OTACodes.kDeletionDate)).setCalendar(calDeletion2.toGregorianCalendar(), true, DBConstants.SCREEN_MOVE);
                    ((PropertiesField)recOTACodes.getField(OTACodes.kProperties)).setProperties(properties);
                    recOTACodes.add();
        
                }
            }
            
            
        
        } catch (DBException ex) {
            ex.printStackTrace();
        } catch (JAXBException ex) {
            ex.printStackTrace();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }

}

/**
  * @(#)MergeSkymap.
  * Copyright © 2013 tourgeek.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
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
import java.io.*;
import com.tourgeek.tour.base.db.*;

/**
 *  MergeSkymap - .
 */
public class MergeSkymap extends MergeCity
{
    /**
     * Default constructor.
     */
    public MergeSkymap()
    {
        super();
    }
    /**
     * Constructor.
     */
    public MergeSkymap(RecordOwnerParent taskParent, Record recordMain, Map<String,Object> properties)
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
     * Main Method.
     */
    public static void main(String[] args)
    {
        Map<String,Object> properties = null;
        if (args != null)
        {
            properties = new Hashtable<String,Object>();
            Utility.parseArgs(properties, args);
        }
        MergeSkymap convert = new MergeSkymap(null, null, properties);
        convert.run();
    }
    /**
     * GetSource Method.
     */
    public Iterator<Record> getSource()
    {
        String strSource = this.getProperty("source");
        if (strSource == null)
            strSource = System.getProperty("user.home") + "/workspace/tourgeek/data/java/tour/externaldata/skymap/Location.dat";
        File file = new File(strSource);
        
        LineNumberReader reader = null;
        try {
            FileInputStream fileIn = new FileInputStream(file);
            InputStreamReader inStream = new InputStreamReader(fileIn, "8859_1");
            reader = new LineNumberReader(inStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        
        Record record = new City(this);
        return new SkymapSource(reader, record);
    }

}

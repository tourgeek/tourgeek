/**
 * @(#)TidyHtml.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.util.script.util;

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
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import org.jbundle.base.thread.*;
import org.w3c.tidy.*;
import java.io.*;
import java.net.*;

/**
 *  TidyHtml - Clean up html.
 */
public class TidyHtml extends BaseProcess
{
    /**
     * Default constructor.
     */
    public TidyHtml()
    {
        super();
    }
    /**
     * Constructor.
     */
    public TidyHtml(RecordOwnerParent taskParent, Record recordMain, Map<String,Object> properties)
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
     * Run Method.
     */
    public void run()
    {
        boolean xmlOut = true;
        String errOutFileName = this.getProperty("errorOutFilename");
        if (errOutFileName == null)
            errOutFileName = "/tmp/errorout";   // NO
        String url = this.getProperty("source");
        String outFileName = this.getProperty("destination");
        URL u;
        BufferedInputStream in;
        FileOutputStream out;
        Tidy tidy = new Tidy();
        tidy.setXmlOut(xmlOut);
        try { 
            tidy.setErrout(new PrintWriter(new FileWriter(errOutFileName), true)); 
            u = new URL(url);
            in = new BufferedInputStream(u.openStream());
            out = new FileOutputStream(outFileName);
            tidy.parse(in, out);
        } catch ( IOException e ) {
            System.out.println( this.toString() + e.toString() );
        }
    }

}

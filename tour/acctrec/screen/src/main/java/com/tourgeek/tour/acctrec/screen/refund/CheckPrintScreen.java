
package com.tourgeek.tour.acctrec.screen.refund;

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
import org.jbundle.base.screen.model.report.*;
import com.tourgeek.tour.assetdr.db.*;
import javax.swing.text.*;

/**
 *  CheckPrintScreen - Base screen for printing checks.
 */
public class CheckPrintScreen extends CustomReportScreen
{
    /**
     * Default constructor.
     */
    public CheckPrintScreen()
    {
        super();
    }
    /**
     * Constructor.
     * @param record The main record for this screen.
     * @param itsLocation The location of this component within the parent.
     * @param parentScreen The parent screen.
     * @param fieldConverter The field this screen field is linked to.
     * @param iDisplayFieldDesc Do I display the field desc?
     * @param properties Addition properties to pass to the screen.
     */
    public CheckPrintScreen(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
    {
        this();
        this.init(record, itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, properties);
    }
    /**
     * Initialize class fields.
     */
    public void init(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
    {
        super.init(record, itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, properties);
    }
    /**
     * Get the screen display title.
     */
    public String getTitle()
    {
        return "Base screen for printing checks";
    }
    /**
     * SetupSFields Method.
     */
    public void setupSFields()
    {
        this.getRecord(CheckScreenRecord.CHECK_SCREEN_RECORD_FILE).getField(CheckScreenRecord.CHECK_DATE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(CheckScreenRecord.CHECK_SCREEN_RECORD_FILE).getField(CheckScreenRecord.CHECK_NO).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(CheckScreenRecord.CHECK_SCREEN_RECORD_FILE).getField(CheckScreenRecord.PAYEE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(CheckScreenRecord.CHECK_SCREEN_RECORD_FILE).getField(CheckScreenRecord.CHECK_AMOUNT).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(CheckScreenRecord.CHECK_SCREEN_RECORD_FILE).getField(CheckScreenRecord.CHECK_AMOUNT_TEXT).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
    }
    /**
     * Layout the special print control (usually a JPanel).
     * ***** NOTE: This method is obsolete *****
     * The base code is now in the swing implementation
     * (VCustomReportScreen) since I can't reference awt from here.
     */
    public void layoutPrintControl(Object control)
    {
        /*
        JPanel panel = (JPanel)control;
        int x = 0;
        int y = 0;
        int width = (int)(7.5 * 72);
        int height = (int)(3.5 * 72);
        panel.setBackground(Color.pink);
        panel.setOpaque(true);
        panel.setLayout(null);
        panel.setBounds(x, y, width, height);
        
        JTextComponent label = (JTextField)((SEditText)this.getScreenRecord().getField(CheckScreenRecord.PAYEE).getComponent(0)).getScreenFieldView().getControl();
        height = (int)(0.25 * 72);
        this.setupComponent(label, x, y, width, height);
        
        label = (JTextField)((SEditText)this.getScreenRecord().getField(CheckScreenRecord.CHECK_NO).getComponent(0)).getScreenFieldView().getControl();
        y = (int)(.5 * 72);
        this.setupComponent(label, x, y, width, height);
        
        label = (JTextField)((SEditText)this.getScreenRecord().getField(CheckScreenRecord.CHECK_DATE).getComponent(0)).getScreenFieldView().getControl();
        y = (int)(.75 * 72);
        this.setupComponent(label, x, y, width, height);
        
        label = (JTextField)((SEditText)this.getScreenRecord().getField(CheckScreenRecord.CHECK_AMOUNT).getComponent(0)).getScreenFieldView().getControl();
        y = (int)(1 * 72);
        this.setupComponent(label, x, y, width, height);
        
        label = (JTextArea)((SEditText)this.getScreenRecord().getField(CheckScreenRecord.CHECK_AMOUNT_TEXT).getComponent(0)).getScreenFieldView().getControl();
        y = (int)(1.5 * 72);
        height = (int)(0.5 * 72);
        this.setupComponent(label, x, y, width, height);
        
        panel.setLayout(null);
        */
    }

}

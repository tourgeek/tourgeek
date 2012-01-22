/**
 * @(#)CurrencysScreen.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.base.screen;

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
import com.tourapp.tour.base.db.*;

/**
 *  CurrencysScreen - Currencies.
 */
public class CurrencysScreen extends Screen
{
    /**
     * Default constructor.
     */
    public CurrencysScreen()
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
    public CurrencysScreen(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
        return "Currencies";
    }
    /**
     * Override this to open the main file.
     * <p />You should pass this record owner to the new main file (ie., new MyNewTable(thisRecordOwner)).
     * @return The new record.
     */
    public Record openMainRecord()
    {
        return new Currencys(this);
    }
    /**
     * Override this to open the other files in the query.
     */
    public void openOtherRecords()
    {
        super.openOtherRecords();
        //+new AssetDrControl(this);
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        this.addMainKeyBehavior();
        //+this.getMainRecord().getField(Currencys.kLanguageID).addListener(new InitFieldHandler(this.getRecord(AssetDrControl.kAssetDrControlFile).getField(AssetDrControl.kLanguageID)));
    }
    /**
     * Set up all the screen fields.
     */
    public void setupSFields()
    {
        this.getRecord(Currencys.kCurrencysFile).getField(Currencys.kCurrencyCode).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Currencys.kCurrencysFile).getField(Currencys.kDescription).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        Record query = this.getMainRecord();
        for (int fieldSeq = Currencys.kLastRate; fieldSeq <= Currencys.kLanguageID; fieldSeq++)
        {
            query.getField(fieldSeq).setupFieldView(this);
            if ((fieldSeq == Currencys.kLastRate) || (fieldSeq == Currencys.kCostingRate))
            {
                ScreenLocation lastFieldPosition = this.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR);
                RecipFieldConverter converter = new RecipFieldConverter((NumberField)query.getField(fieldSeq));
                SEditText nameView = new SEditText(lastFieldPosition, this, converter, ScreenConstants.DONT_DISPLAY_FIELD_DESC);
            }
        }
    }

}

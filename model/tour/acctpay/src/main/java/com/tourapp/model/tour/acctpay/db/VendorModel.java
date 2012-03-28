/**
 * @(#)VendorModel.
 * Copyright © 2012 tourapp.com. All rights reserved.
 */
package com.tourapp.model.tour.acctpay.db;

import org.jbundle.model.main.db.*;

public interface VendorModel extends CompanyModel
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    //public static final String CODE = CODE;
    public static final String VENDOR_NAME = NAME;
    //public static final String ADDRESS_LINE_1 = ADDRESS_LINE_1;
    //public static final String ADDRESS_LINE_2 = ADDRESS_LINE_2;
    //public static final String CITY_OR_TOWN = CITY_OR_TOWN;
    //public static final String STATE_OR_REGION = STATE_OR_REGION;
    //public static final String POSTAL_CODE = POSTAL_CODE;
    //public static final String COUNTRY = COUNTRY;
    //public static final String TEL = TEL;
    //public static final String FAX = FAX;
    //public static final String EMAIL = EMAIL;
    //public static final String WEB = WEB;
    //public static final String DATE_ENTERED = DATE_ENTERED;
    //public static final String DATE_CHANGED = DATE_CHANGED;
    //public static final String CHANGED_ID = CHANGED_ID;
    //public static final String COMMENTS = COMMENTS;
    //public static final String USER_ID = USER_ID;
    //public static final String PASSWORD = PASSWORD;
    //public static final String NAME_SORT = NAME_SORT;
    //public static final String POSTAL_CODE_SORT = POSTAL_CODE_SORT;
    //public static final String CONTACT = CONTACT;
    public static final String CONTACT_TITLE = "ContactTitle";
    public static final String COUNTRY_ID = "CountryID";
    public static final String CURRENCYS_ID = "CurrencysID";
    public static final String MESSAGE_TRANSPORT_ID = "MessageTransportID";
    public static final String MESSAGE_SERVER = "MessageServer";
    public static final String WSDL_PATH = "WSDLPath";
    public static final String PROPERTIES = "Properties";
    public static final String VENDOR_STATUS_ID = "VendorStatusID";
    public static final String PAYMENT_CYCLE_ID = "PaymentCycleID";
    public static final String PAYMENT_CODE_ID = "PaymentCodeID";
    public static final String PREPAY_TYPE_ID = "PrepayTypeID";
    public static final String DEPOSIT_ID = "DepositID";
    public static final String OPERATION_TYPE_CODE = "OperationTypeCode";
    public static final String DEFAULT_ACCOUNT_ID = "DefaultAccountID";
    public static final String SEND_1099 = "Send1099";
    public static final String TAX_ID = "TaxId";
    public static final String VENDORS_CUST_NO = "VendorsCustNo";
    public static final String AMOUNT_SELECTED = "AmountSelected";
    public static final String VENDOR_BALANCE = "VendorBalance";

    public static final String NAME_SORT_KEY = "NameSort";

    public static final String CURRENCYS_ID_KEY = "CurrencysID";
    public static final String VENDOR_SCREEN_CLASS = "com.tourapp.tour.acctpay.screen.vendor.VendorScreen";
    public static final String VENDOR_GRID_SCREEN_CLASS = "com.tourapp.tour.acctpay.screen.vendor.VendorGridScreen";
    public static final String BROKER_DIST_GRID_SCREEN_CLASS = "com.tourapp.tour.acctpay.screen.broker.BrokerDistGridScreen";
    public static final String MESSAGE_DETAIL_SCREEN = "Message Detail";
    public static final String MESSAGE_LOG_SCREEN = "Message Log";

    public static final String VENDOR_FILE = "Vendor";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.acctpay.db.Vendor";
    public static final String THICK_CLASS = "com.tourapp.tour.acctpay.db.Vendor";

}

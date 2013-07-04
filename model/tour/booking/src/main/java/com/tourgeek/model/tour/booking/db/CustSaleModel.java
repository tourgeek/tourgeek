
package com.tourgeek.model.tour.booking.db;

import org.jbundle.model.db.*;

public interface CustSaleModel extends Rec
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    public static final String CUST_SALE_DATE = "CustSaleDate";
    public static final String CUST_SALE_AGENT_ID = "CustSaleAgentID";
    public static final String CUST_SALE_CUST_ID = "CustSaleCustID";
    public static final String CUST_SALE_CUST_NO = "CustSaleCustNo";

    public static final String CUST_SALE_FILE = "CustSale";
    public static final String THIN_CLASS = "com.tourgeek.thin.tour.booking.db.CustSale";
    public static final String THICK_CLASS = "com.tourgeek.tour.booking.db.CustSale";

}

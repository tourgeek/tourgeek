/*
 * Copyright © 2012 jbundle.org. All rights reserved.
 */
package com.tourgeek.tour.util.manual;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.jbundle.base.db.Record;
import org.jbundle.base.field.BaseField;
import org.jbundle.base.field.ReferenceField;
import org.jbundle.base.model.DBConstants;
import org.jbundle.base.model.Utility;
import org.jbundle.base.thread.BaseProcess;
import org.jbundle.model.DBException;
import org.jbundle.model.RecordOwnerParent;

import com.tourgeek.tour.acctpay.db.ApTrx;
import com.tourgeek.tour.booking.db.Booking;
import com.tourgeek.tour.booking.db.Tour;
import com.tourgeek.tour.booking.detail.db.BookingDetail;

public class CheckAllRecords extends BaseProcess {
	
    /**
     * Initialization.
     */
    public CheckAllRecords()
    {
        super();
    }
    /**
     * Initialization.
     * @param taskParent Optional task param used to get parent's properties, etc.
     * @param recordMain Optional main record.
     * @param properties Optional properties object (note you can add properties later).
     */
    public CheckAllRecords(RecordOwnerParent taskParent, Record recordMain, Map<String, Object> properties)
    {
        this();
        this.init(taskParent, recordMain, properties);
    }
    /**
     * Initialization.
     * @param taskParent Optional task param used to get parent's properties, etc.
     * @param recordMain Optional main record.
     * @param properties Optional properties object (note you can add properties later).
     */
    public void init(RecordOwnerParent taskParent, Record recordMain, Map<String, Object> properties)
    {
        super.init(taskParent, recordMain, properties);
    }
    /**
     * Free the resources.
     */
    public void free()
    {
        super.free();
    }
    /**
     * Run the code in this process (you must override).
     */
    public void run()
    {
    	this.checkIt();
    }
    @Override
    public Record openMainRecord()
    {
    	return new BookingDetail(this);
    }
    public void openOtherRecords()
    {
    }
    public void checkIt()
    {
    	BookingDetail bookingDetail = (BookingDetail)this.getMainRecord();
    	
    	bookingDetail.setKeyArea(Booking.ID_KEY);
    	
    	try {
			while (bookingDetail.hasNext())
			{
				Record record = bookingDetail.next();
				
				Map<String,Object> refs = this.checkReferences(record);
				
				String str = record.getTableNames(false) + "(" + record.getCounterField().toString() + ")" + this.format(refs, 1);
				
				this.analyzeReferences(refs, refs);
			}
			
		} catch (DBException e) {
			e.printStackTrace();
		}
    }
    
    public Map<String,Object> checkReferences(Record record)
    {
    	Map<String,Object> refs = new HashMap<String,Object>();
    	
    	for (int i = 0; i < record.getFieldCount(); i++)
    	{
    		BaseField field = record.getField(i);
    		if (field instanceof ReferenceField)
    		{
    			if (!field.isNull())
    				if (field.getValue() != 0)
    			{
					Record referenceRecord = ((ReferenceField)field).getReference();
					if ((referenceRecord == null)
						|| (referenceRecord.getEditMode() == DBConstants.EDIT_NONE)
						|| (referenceRecord.getEditMode() == DBConstants.EDIT_ADD))
					{
						if (((ReferenceField)field).getReferenceRecord() != null)
							Utility.getLogger().warning("No reference id= " + record.getCounterField().toString() + " " + record.getTableNames(false) + "." + field.getFieldName() + " value: " + field.toString());
					}
					else
					{	// Valid record
						if (!skipRecord(referenceRecord))
							refs.put(referenceRecord.getTableNames(false), (int)referenceRecord.getCounterField().getValue());
						String key = record.getTableNames(false) + "->" + referenceRecord.getTableNames(false);
						if (!checkMap.contains(key))
							Utility.getLogger().info(key);
						checkMap.add(key);
						Map<String,Object> recs = this.checkReferences(referenceRecord);
						if (!skipRecord(referenceRecord))
							refs.put(referenceRecord.getTableNames(false) + "->", recs);
					}
    			}
    		}
    	}
    	
    	return refs;
    }
	
    public boolean skipRecord(Record record)
    {
    	if ("UserInfo".equalsIgnoreCase(record.getTableNames(false)))
    		return true;	// Skip it!
		if ((record.getDatabaseType() & DBConstants.TABLE_TYPE_MASK) == DBConstants.REMOTE)
			if ((record.getDatabaseType() & DBConstants.USER_DATA) == DBConstants.USER_DATA)
				return false;
		return true;	// Skip it!
    }
    
	public void analyzeReferences(Map<String,Object> refs, Map<String,Object> topRefs)
	{
		for (String key : refs.keySet())
		{
			if (refs.get(key) instanceof Map<?,?>)
			{
				this.analyzeReferences((Map)refs.get(key), topRefs);
			}
			else
			{
				this.checkDuplicates(key, (Integer)refs.get(key), topRefs);
			}
		}
	}
	public void checkDuplicates(String key, int id, Map<String,Object> refs)
	{
		for (String topKey : refs.keySet())
		{
			if (refs.get(topKey) instanceof Map<?,?>)
			{
				this.checkDuplicates(topKey, id, (Map)refs.get(topKey));
			}
			else
			{
				int topId = (Integer)refs.get(topKey);
				if (topKey.equals(key))
					if (topId != id)
						Utility.getLogger().warning("Relative mismatch " + key + "(" + id + ") != (" + topId + ") for " + this.getMainRecord().getTableNames(false) + "(" + this.getMainRecord().getCounterField().toString() + ")");
			}
		}		
	}
	
	public String format(Map<String,Object> refs, int i)
	{
		String str = "";
		for (String key : refs.keySet())
		{
			if (refs.get(key) instanceof Map<?,?>)
			{
//x				this.analyzeReferences((Map)refs.get(key), topRefs);
			}
			else
			{
				str = str + "\n";
				for (int x = 0; x < i; x++)
					str = str + "  ";
				str = str + key + "(" + refs.get(key).toString() + ")";
				if (refs.get(key + "->") != null)
					str = str + this.format((Map)refs.get(key + "->"), i + 1);
			}
		}		
		return str;
	}
	
    Set<String> checkMap = new HashSet<String>();
}
